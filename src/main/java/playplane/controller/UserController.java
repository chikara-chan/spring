package playplane.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import playplane.model.SessionInfo;
import playplane.model.User;
import playplane.service.UserServiceI;
import playplane.util.ConfigUtil;

/**
 * 用户Controller
 * 
 * @author chikara
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserServiceI userService;

	// 用户登录
	@RequestMapping("/login")
	public ModelAndView login(User user, String captcha, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/login");
		mav.addObject("user", user);
		if(!((String)session.getAttribute("captcha")).equalsIgnoreCase(captcha)){
			mav.addObject("msg", "验证码错误");
			return mav;
		}
		User u = userService.login(user);
		if (u != null) {
			SessionInfo sessionInfo = new SessionInfo();
			BeanUtils.copyProperties(u, sessionInfo);
			sessionInfo.setResourceList(userService.resourceList(u.getId()));
			session.setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
			if(sessionInfo.getType()==2)
				mav.setViewName("index");
			else if(sessionInfo.getType()==1)
				mav.setViewName("redirect:../market/home");
			else if(sessionInfo.getType()==0)
				mav.setViewName("redirect:../apply/list");
		} else {
			mav.addObject("msg", "账号或密码错误，请重新输入");
		}
		return mav;
	}

	// 用户注册
	@RequestMapping("/reg")
	public ModelAndView reg(User user, String captcha, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("account/reg");
		mav.addObject("user", user);
		if(!((String)session.getAttribute("captcha")).equalsIgnoreCase(captcha)){
			mav.addObject("msg", "验证码错误");
			return mav;
		}
		User u = userService.reg(user);
		if (u != null) {
			mav.setViewName("account/login");
			mav.addObject("user",user);
		} else {
			mav.addObject("msg", "用户名已存在");
		}
		return mav;
	}
	
	//退出登录
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if (session != null) {
			SessionInfo sessionInfo=(SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
			if(sessionInfo.getType()==0)
				mav.setViewName("index");
			else {
				mav.setViewName("customer/index");
			}
			session.invalidate();
		}
		return mav;
	}
	
	//修改密码
	@RequestMapping("/editPwd")
	public ModelAndView editPwd(String oldPassword,String newPassword,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo=(SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		if (userService.editPwd(oldPassword,newPassword,sessionInfo)) {
			mav.addObject("msgSuccess", "修改成功");
		}else{
			mav.addObject("msgError", "当前密码不正确");
		}
		if(sessionInfo.getType()==2)
			mav.setViewName("customer/setPwd");
		else if (sessionInfo.getType()==1) {
			mav.setViewName("market/setPwd");
		}
		return mav;
	}

}
