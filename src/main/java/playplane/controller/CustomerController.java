package playplane.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import playplane.model.Customer;
import playplane.model.Delivery;
import playplane.model.Json;
import playplane.model.Market;
import playplane.model.Order;
import playplane.model.SessionInfo;
import playplane.service.CustomerServiceI;
import playplane.service.DeliveryServiceI;
import playplane.service.OrderServiceI;
import playplane.util.ConfigUtil;

/**
 * 顾客Controller
 * 
 * @author chikara
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerServiceI customerService;

	@Autowired
	private DeliveryServiceI deliveryService;

	@Autowired
	private OrderServiceI orderService;

	// 获取顾客信息主页
	@RequestMapping("/set")
	public ModelAndView set(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		Customer customer = customerService.show(sessionInfo);
		if (customer != null) {
			mav.addObject("customer", customer);
		} else {
			mav.addObject("msg", "找不到该用户");
		}
		mav.setViewName("customer/set");
		return mav;
	}

	// 获取邮箱绑定页面
	@RequestMapping("/editEmailPage")
	public ModelAndView editEmailPage(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		String email = customerService.getEmail(sessionInfo);
		mav.addObject("email", email);
		mav.setViewName("customer/setEmail");
		return mav;
	}

	// 更改绑定邮箱
	@RequestMapping("/editEmail")
	public ModelAndView editEmail(String oldEmail, String captcha, String newEmail, HttpSession session,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		String email = customerService.getEmail(sessionInfo);
		mav.addObject("email", email);
		mav.setViewName("customer/setEmail");
		if (!((String) session.getAttribute("captcha")).equalsIgnoreCase(captcha)) {
			mav.addObject("msg", "验证码错误");
			return mav;
		}
		if (customerService.editEmail(oldEmail, newEmail, sessionInfo)) {
			mav.addObject("msgSuccess", "绑定成功");
			mav.addObject("email", newEmail);
		} else {
			mav.addObject("msgError", "当前邮箱不正确");
			mav.addObject("email", oldEmail);
		}
		return mav;
	}

	// 获取手机绑定页面
	@RequestMapping("/editPhonePage")
	public ModelAndView editPhonePage(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		String phone = customerService.getPhone(sessionInfo);
		mav.addObject("phone", phone);
		mav.setViewName("customer/setPhone");
		return mav;
	}

	// 更改手机
	@RequestMapping("/editPhone")
	public ModelAndView editPhone(String oldPhone, String newPhone, String captcha, HttpSession session,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		String phone = customerService.getPhone(sessionInfo);
		mav.addObject("phone", phone);
		mav.setViewName("customer/setPhone");
		if (!((String) session.getAttribute("captcha")).equalsIgnoreCase(captcha)) {
			mav.addObject("msg", "验证码错误");
			return mav;
		}
		if (customerService.editPhone(oldPhone, newPhone, sessionInfo)) {
			mav.addObject("msgSuccess", "绑定成功");
			mav.addObject("phone", newPhone);
		} else {
			mav.addObject("msgError", "当前手机不正确");
			mav.addObject("phone", oldPhone);
		}
		return mav;
	}

	// 收货地址查询
	@RequestMapping("/getDelivery")
	public ModelAndView getDelivery(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Delivery> deliverys = deliveryService.list(sessionInfo);
		mav.addObject("deliverys", deliverys);
		mav.setViewName("customer/setAddress");
		return mav;
	}

	// 购物车提交后收货地址查询
	@ResponseBody
	@RequestMapping("/getDeliveryJson")
	public List<Delivery> getDeliveryJson(HttpSession session, HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Delivery> deliverys = deliveryService.list(sessionInfo);
		return deliverys;
	}

	// 添加收货地址
	@RequestMapping("/addDelivery")
	public ModelAndView add(Delivery delivery, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		if (deliveryService.add(delivery, sessionInfo))
			mav.addObject("msg", "添加成功");
		else
			mav.addObject("msg", "delivery");
		mav.setViewName("redirect:getDelivery");
		return mav;
	}

	// 删除收货地址
	@RequestMapping("/deleteDelivery")
	public ModelAndView delete(Delivery delivery, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		if (deliveryService.delete(delivery, sessionInfo)) {
			mav.addObject("msg", "删除成功");
		} else
			mav.addObject("msg", "操作失败");
		mav.setViewName("redirect:getDelivery");
		return mav;
	}

	// 获取编辑地址页面
	@RequestMapping("/editDeliveryPage")
	public ModelAndView editPage(Delivery delivery, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		delivery = deliveryService.editPage(delivery, sessionInfo);
		mav.addObject("delivery", delivery);
		mav.setViewName("customer/setAddressEdit");
		return mav;
	}

	// 编辑地址
	@RequestMapping("/editDelivery")
	public ModelAndView edit(Delivery delivery, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		if (deliveryService.edit(delivery, sessionInfo))
			mav.addObject("msg", "编辑成功");
		else
			mav.addObject("msg", "操作失败");
		mav.setViewName("redirect:getDelivery");
		return mav;
	}

	// 修改姓名
	@RequestMapping("/editName")
	public ModelAndView editName(String name, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		if (customerService.editName(name, sessionInfo))
			mav.addObject("msg", "编辑成功");
		else
			mav.addObject("msg", "操作失败");
		mav.setViewName("redirect:set");
		return mav;
	}

	// 用户提交订单
	@ResponseBody
	@RequestMapping("/submitOrder")
	public Order submitOrder(@RequestBody Order order, HttpSession session, HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		order = orderService.submitOrder(order, sessionInfo);
		session.removeAttribute("productlist");
		session.removeAttribute("amount");
		return order;
	}

	// 订单管理
	@RequestMapping("/orderManage")
	public ModelAndView orderManage(String key, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		if (sessionInfo == null) {
			mav.setViewName("account/login");
			return mav;
		}
		List<Order> orders = orderService.listOrder(key, sessionInfo);
		mav.addObject("orders", orders);
		mav.addObject("key", key);
		mav.setViewName("customer/orderManage");
		return mav;
	}

	// 用户获取订单
	@RequestMapping("/getOrder")
	public ModelAndView getOrder(Order order, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customer/order");
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		order = orderService.getOrder(order, sessionInfo);
		mav.addObject("order", order);
		return mav;
	}

	// 用户取消订单
	@RequestMapping("/cancelOrder")
	public ModelAndView cancelOrder(Order order, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customer/order");
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		orderService.cancelOrder(order, sessionInfo);
		order = orderService.getOrder(order, sessionInfo);
		mav.addObject("order", order);
		return mav;
	}

	// 用户确认收货
	@RequestMapping("/finishOrder")
	public ModelAndView finishOrder(Order order, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customer/order");
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		orderService.finishOrder(order, sessionInfo);
		order = orderService.getOrder(order, sessionInfo);
		mav.addObject("order", order);
		return mav;
	}

	// 用户评价订单
	@RequestMapping("/reviewOrder")
	public ModelAndView reviewOrder(Order order, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("customer/order");
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		orderService.reviewOrder(order, sessionInfo);
		order = orderService.getOrder(order, sessionInfo);
		mav.addObject("order", order);
		return mav;
	}

	// 管理员获取所有用户列表
	@RequestMapping("/listByAdmin")
	public ModelAndView listByAdmin(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Customer> customers = customerService.listByAdmin(sessionInfo);
		mav.addObject("customers", customers);
		mav.setViewName("admin/customerManage");
		return mav;
	}

}
