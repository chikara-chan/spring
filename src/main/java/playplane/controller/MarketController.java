package playplane.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import playplane.model.Market;
import playplane.model.Order;
import playplane.model.OrderMessage;
import playplane.model.SessionInfo;
import playplane.service.MarketServiceI;
import playplane.util.ConfigUtil;

/**
 * 超市Controller
 * 
 * @author chikara
 */
@Controller
@RequestMapping("/market")
public class MarketController extends BaseController {

	@Autowired
	private MarketServiceI marketService;

	// 返回商家主页
	@RequestMapping("/home")
	public ModelAndView home(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		OrderMessage orderMessage = marketService.getOrderMessage(sessionInfo);
		mav.addObject("orderMessage", orderMessage);
		mav.setViewName("market/index");
		return mav;
	}

	// 获取商家信息主页
	@RequestMapping("/set")
	public ModelAndView set(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Market market = marketService.show(sessionInfo);
		if (market != null) {
			mav.addObject("market", market);
		} else {
			mav.addObject("msg", "找不到该用户");
		}
		mav.setViewName("market/set");
		return mav;
	}

	// 修改姓名
	@RequestMapping("/editName")
	public ModelAndView editName(String name, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		if (marketService.editName(name, sessionInfo))
			mav.addObject("msg", "编辑成功");
		else
			mav.addObject("msg", "操作失败");
		mav.setViewName("redirect:set");
		return mav;
	}

	// 获取邮箱绑定页面
	@RequestMapping("/editEmailPage")
	public ModelAndView editEmailPage(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		String email = marketService.getEmail(sessionInfo);
		mav.addObject("email", email);
		mav.setViewName("market/setEmail");
		return mav;
	}

	// 更改绑定邮箱
	@RequestMapping("/editEmail")
	public ModelAndView editEmail(String oldEmail, String captcha, String newEmail, HttpSession session,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		String email = marketService.getEmail(sessionInfo);
		mav.addObject("email", email);
		mav.setViewName("market/setEmail");
		if (!((String) session.getAttribute("captcha")).equalsIgnoreCase(captcha)) {
			mav.addObject("msg", "验证码错误");
			return mav;
		}
		if (marketService.editEmail(oldEmail, newEmail, sessionInfo)) {
			mav.addObject("msgSuccess", "绑定成功");
		} else {
			mav.addObject("msgError", "当前邮箱不正确");
		}
		return mav;
	}

	// 获取手机绑定页面
	@RequestMapping("/editPhonePage")
	public ModelAndView editPhonePage(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		String phone = marketService.getPhone(sessionInfo);
		mav.addObject("phone", phone);
		mav.setViewName("market/setPhone");
		return mav;
	}

	// 更改手机
	@RequestMapping("/editPhone")
	public ModelAndView editPhone(String oldPhone, String newPhone, String captcha, HttpSession session,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		String phone = marketService.getPhone(sessionInfo);
		mav.addObject("phone", phone);
		mav.setViewName("market/setPhone");
		if (!((String) session.getAttribute("captcha")).equalsIgnoreCase(captcha)) {
			mav.addObject("msg", "验证码错误");
			return mav;
		}
		if (marketService.editPhone(oldPhone, newPhone, sessionInfo)) {
			mav.addObject("msgSuccess", "绑定成功");
		} else {
			mav.addObject("msgError", "当前手机不正确");
		}
		return mav;
	}

	// 订单管理
	@RequestMapping("/orderManage")
	public ModelAndView orderManage(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Order> orders = marketService.getOrder(sessionInfo);
		mav.addObject("orders", orders);
		mav.setViewName("market/orderManage");
		return mav;
	}
	

	// 获取处于待接单状态的订单
	@RequestMapping("/unhandledOrder")
	public ModelAndView unhandledOrder(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Order> orders = marketService.getUnhandledOrder(sessionInfo);
		mav.addObject("orders", orders);
		mav.setViewName("market/unhandledOrder");
		return mav;
	}

	// 获取处于已接单状态的订单
	@RequestMapping("/unreceivedOrder")
	public ModelAndView unreceivedOrder(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Order> orders = marketService.getUnreceivedOrder(sessionInfo);
		mav.addObject("orders", orders);
		mav.setViewName("market/unreceivedOrder");
		return mav;
	}

	// 获取处于已完成状态的订单
	@RequestMapping("/finishedOrder")
	public ModelAndView finishedOrder(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Order> orders = marketService.getFinishedOrder(sessionInfo);
		mav.addObject("orders", orders);
		mav.setViewName("market/finishedOrder");
		return mav;
	}

	// 商家接单
	@RequestMapping("/acceptOrder")
	public ModelAndView acceptOrder(Order order, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		marketService.acceptOrder(order, sessionInfo);
		List<Order> orders = marketService.getOrder(sessionInfo);
		mav.addObject("orders", orders);
		mav.setViewName("redirect:unhandledOrder");
		return mav;
	}

	// 商家拒接订单
	@RequestMapping("/cancelOrder")
	public ModelAndView cancelOrder(Order order, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		marketService.cancelOrder(order, sessionInfo);
		List<Order> orders = marketService.getOrder(sessionInfo);
		mav.addObject("orders", orders);
		mav.setViewName("redirect:unhandledOrder");
		return mav;
	}

	// 管理员获取所有超市列表
	@RequestMapping("/listByAdmin")
	public ModelAndView listByAdmin(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Market> markets = marketService.listByAdmin(sessionInfo);
		mav.addObject("markets", markets);
		mav.setViewName("admin/marketManage");
		return mav;
	}

	// 商家获取店铺公告
	@RequestMapping("/broadcast")
	public ModelAndView broadcast(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		String broadcast = marketService.getBroadcast(sessionInfo);
		mav.addObject("broadcast", broadcast);
		mav.setViewName("market/broadcast");
		return mav;
	}

	// 商家修改店铺公告
	@RequestMapping("/editBroadcast")
	public ModelAndView editBroadcast(String broadcast, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		broadcast = marketService.editBroadcast(broadcast, sessionInfo);
		mav.addObject("broadcast", broadcast);
		mav.addObject("msg", "发布成功");
		mav.setViewName("market/broadcast");
		return mav;
	}

	// 商家获取所有评价
	@RequestMapping("/listReview")
	public ModelAndView listReview(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Order> orders = marketService.listReview(sessionInfo);
		mav.addObject("orders", orders);
		mav.setViewName("market/review");
		return mav;
	}

	// 商家获取未回复评价
	@RequestMapping("/listUnrepliedReview")
	public ModelAndView listUnrepliedReview(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Order> orders = marketService.listUnrepliedReview(sessionInfo);
		mav.addObject("orders", orders);
		mav.setViewName("market/unrepliedReview");
		return mav;
	}

	// 商家获取已回复评价
	@RequestMapping("/listRepliedReview")
	public ModelAndView listRepliedReview(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Order> orders = marketService.listRepliedReview(sessionInfo);
		mav.addObject("orders", orders);
		mav.setViewName("market/repliedReview");
		return mav;
	}

	// 商家回复评价
	@RequestMapping("/replyReview")
	public ModelAndView replyReview(Order order, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		marketService.replyReview(order, sessionInfo);
		mav.addObject("msg", "回复成功");
		mav.setViewName("redirect:listReview");
		return mav;
	}

}
