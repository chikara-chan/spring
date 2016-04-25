package playplane.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import playplane.model.Apply;
import playplane.model.SessionInfo;
import playplane.service.ApplyServiceI;
import playplane.util.ConfigUtil;

/**
 * 超市入驻申请表Controller
 * 
 * @author chikara
 */
@Controller
@RequestMapping("/apply")
public class ApplyController extends BaseController {

	@Autowired
	private ApplyServiceI applyService;

	// 获取超市申请入驻页面
	@RequestMapping("/applyPage")
	public ModelAndView applyPage(String unitId,HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Apply apply = applyService.getByUnitId(unitId);
		mav.setViewName("customer/apply");
		mav.addObject("apply", apply);
		return mav;
	}

	// 超市申请入驻表单提交处理
	@RequestMapping("/applySubmit")
	public ModelAndView applySubmit(Apply apply,  HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customer/location");
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		apply.setUserId(sessionInfo.getId());
		try {
			applyService.submit(apply);
			mav.addObject("msg", "提交成功,请耐心等待审核结果");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", e.getMessage());
		}

		return mav;
	}

	// 获取所有超市入驻申请表
	@RequestMapping("/list")
	public ModelAndView list(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("admin/index");
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Apply> applies=new ArrayList<Apply>();
		try {
			applies=applyService.list(sessionInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("applies", applies);
		return mav;
	}
	
	// 批准超市入驻
	@RequestMapping("/agree")
	public ModelAndView agree(Apply apply,HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:list");
		applyService.agree(apply);
		return mav;
	}
	
	// 拒绝超市入驻
	@RequestMapping("/reject")
	public ModelAndView reject(Apply apply,HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:list");
		applyService.reject(apply);
		return mav;
	}

}
