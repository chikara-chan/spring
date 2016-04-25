package playplane.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
 * 文件操作工具类
 * 
 * @author chikara
 * 
 */

public class FileUtil {
	
	//商品上传照片
	public static Map<String, Object> upload(HttpServletRequest request, HttpSession session) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("error", 1);
		m.put("message", "上传失败！");
		// 文件保存目录路径
		String savePath = session.getServletContext().getRealPath("/") + "attached/";

		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/attached/";

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", ConfigUtil.get("image"));

		long maxSize = Long.parseLong(ConfigUtil.get("maxFileSize")); // 允许上传最大文件大小(字节)
		
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			uploadDir.mkdirs();
		}

		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			m.put("error", 1);
			m.put("message", "上传目录没有写权限！");
			return m;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			m.put("error", 1);
			m.put("message", "目录名不正确！");
			return m;
		}

		// 创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat yearDf = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthDf = new SimpleDateFormat("MM");
		SimpleDateFormat dateDf = new SimpleDateFormat("dd");
		Date date = new Date();
		String ymd = yearDf.format(date) + "/" + monthDf.format(date) + "/" + dateDf.format(date) + "/";
		savePath += ymd;
		saveUrl += ymd;
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;     
        MultipartFile file = multipartRequest.getFile("product_picture");
		if (file.isEmpty()) {
			m.put("error", 2);
			m.put("message", "请选择文件！");
			return m;
		}
        try {
        	String fileName = file.getOriginalFilename();
        	// 检查文件大小
        	if (file.getSize() > maxSize) {
				m.put("error", 1);
				m.put("message", "上传文件大小超过限制！(允许最大[" + maxSize + "]字节，您上传了[" + file.getSize() + "]字节)");
				return m;
			}
        	// 检查扩展名
        	String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if (!Arrays.asList(extMap.get(dirName).split(",")).contains(fileExt)) {
				m.put("error", 1);
				m.put("message", "上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式！");
				return m;
			}
        	String newFileName = UUID.randomUUID().toString() + "." + fileExt;
        	File uploadedFile = new File(savePath, newFileName);
			file.transferTo(uploadedFile);
			m.put("error", 0);
			m.put("url", saveUrl + newFileName);
		} catch  (Exception e) {
			m.put("error", 1);
			m.put("message", "上传文件失败！");
			e.printStackTrace();
		}
	
		return m;
	}

}
