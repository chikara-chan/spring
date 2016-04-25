package playplane.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import playplane.model.Address;
import playplane.model.Json;
import playplane.model.Order;
import playplane.model.Product;
import playplane.model.SessionInfo;
import playplane.model.Unit;
import playplane.service.MarketServiceI;
import playplane.service.ProductServiceI;
import playplane.util.ConfigUtil;
import playplane.util.FileUtil;

/**
 * 商品Controller
 * 
 * @author chikara
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductServiceI productService;
	
	@Autowired
	private MarketServiceI marketService;

	// 添加商品
	@RequestMapping("/add")
	public ModelAndView add(Product product, HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo=(SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> map=FileUtil.upload(request, session);
		if((Integer)map.get("error")==0){
			String pic=(String)map.get("url");
			product.setPic(pic);
			if(!productService.add(product,sessionInfo))
				mav.addObject("msg","添加失败");
			else
				mav.addObject("msg","添加成功");
		}else{
			mav.addObject("msg",map.get("message"));
		}
		mav.setViewName("market/addProduct");
		return mav;
	}
	
	// 删除商品
	@RequestMapping("/delete")
	public ModelAndView delete(Product product,HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo=(SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		if(productService.delete(product,sessionInfo)){
			mav.addObject("msg","删除成功");
		}else
			mav.addObject("msg","操作失败");
		mav.setViewName("redirect:listByMarket");
		return mav;
	}
	
	// 获取编辑商品页面
	@RequestMapping("/editPage")
	public ModelAndView editPage(Product product,HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo=(SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		product=productService.editPage(product,sessionInfo);
		mav.addObject("product",product);
		mav.setViewName("market/edit");		
		return mav;
	}
	
	// 编辑商品
	@RequestMapping("/edit")
	public ModelAndView edit(Product product,HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo=(SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		Map<String, Object> map=FileUtil.upload(request, session);
		if((Integer)map.get("error")==0){
			String pic=(String)map.get("url");
			product.setPic(pic);
			if(!productService.edit(product,sessionInfo))
				mav.addObject("msg","编辑失败");
			else
				mav.addObject("msg","编辑成功");
		}else if((Integer)map.get("error")==2){
			if(!productService.edit(product,sessionInfo))
				mav.addObject("msg","编辑失败");
			else
				mav.addObject("msg","编辑成功");;
		}else{
			mav.addObject("msg",map.get("message"));
		}
		mav.setViewName("redirect:listByMarket");
		return mav;
	}
	
	
	// 超市获取商品列表
	@RequestMapping("/listByMarket")
	public ModelAndView listByMarket(HttpSession session, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		SessionInfo sessionInfo=(SessionInfo)session.getAttribute(ConfigUtil.getSessionInfoName());
		List<Product> products=productService.listByMarket(sessionInfo);
		mav.addObject("products",products);
		mav.setViewName("market/product");
		return mav;
	}
	
	// 用户根据楼栋号获取商品列表
	@RequestMapping("/listByCustomer")
	public ModelAndView listByCustomer(Unit unit,HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try{
			List<Product> products=productService.listByCustomer(unit);
			Address address=productService.findAddress(unit);
			String broadcast=marketService.getBroadcastByCustomer(unit);
			List<Order> orders=marketService.getOrderByCustomer(unit);
			session.setAttribute("products", products);
			session.setAttribute("address", address);
			session.setAttribute("broadcast", broadcast);
			session.setAttribute("orders", orders);
		}catch (Exception e){
			e.printStackTrace();
			mav.setViewName("customer/location");
			return mav;
		}
		mav.setViewName("customer/index");
		return mav;
	}

	
	// 用户购物车提交
	@ResponseBody
	@RequestMapping("/submitCar")
	public Json submitCar(@RequestBody Product[] products,HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		List<Product> list=new ArrayList<Product>();
		for(Product product:products){
			if(product.getAdded()!=0)
				list.add(product);
		}
		List<Product> newList=productService.getInfo(list);
		double amount=productService.count(list);
		session.setAttribute("productlist", newList);
		session.setAttribute("amount", amount);
		Json json=new Json();
		json.setMsg("success");
		return json; 
	}
	
}
