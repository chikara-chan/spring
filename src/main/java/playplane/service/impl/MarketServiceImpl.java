package playplane.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import playplane.dao.MarketDaoI;
import playplane.dao.OrderBranchDaoI;
import playplane.dao.OrderDaoI;
import playplane.dao.UnitDaoI;
import playplane.dao.UserDaoI;
import playplane.entity.Tcustomer;
import playplane.entity.Tmarket;
import playplane.entity.Torder;
import playplane.entity.TorderBranch;
import playplane.entity.Tproduct;
import playplane.entity.Tunit;
import playplane.entity.Tuser;
import playplane.model.Market;
import playplane.model.Order;
import playplane.model.OrderMessage;
import playplane.model.Product;
import playplane.model.SessionInfo;
import playplane.model.Unit;
import playplane.service.MarketServiceI;

@Service
public class MarketServiceImpl implements MarketServiceI {

	@Autowired
	private MarketDaoI marketDao;
	
	@Autowired
	private UserDaoI userDao;
	
	@Autowired
	private UnitDaoI unitDao;
	
	@Autowired
	private OrderDaoI orderDao;
	
	@Autowired
	private OrderBranchDaoI orderBranchDao;

	@Override
	public Market show(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class, sessionInfo.getId());
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("tuser", tuser);
		Tmarket tmarket=marketDao.get("from Tmarket where tuser=:tuser", params);
		if (tmarket!=null) {
			Market market=new Market();
			BeanUtils.copyProperties(tmarket, market);
			market.setCreateTime(tuser.getCreateTime());
			market.setLastTime(tuser.getLastTime());
			return market;
		}else {
			return null;
		}
	}


	@Override
	public boolean editEmail(String oldEmail, String newEmail, SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser",tuser);
		Tmarket tmarket=new Tmarket();
		if(StringUtils.isEmpty(oldEmail)){
			tmarket=marketDao.get("from Tmarket where tuser=:tuser",params);
		}else{
			params.put("email",oldEmail);
			tmarket=marketDao.get("from Tmarket where tuser=:tuser and email=:email",params);
		}
		if (tmarket!=null) {
			tmarket.setEmail(newEmail);
			return true;
		}else {
			return false;
		}
	}


	@Override
	public String getEmail(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class, sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser",tuser);
		Tmarket tmarket=marketDao.get("from Tmarket where tuser=:tuser",params);
		return tmarket.getEmail();
	}


	@Override
	public boolean editPhone(String oldPhone, String newPhone, SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser",tuser);
		Tmarket tmarket=new Tmarket();
		if(StringUtils.isEmpty(oldPhone)){
			tmarket=marketDao.get("from Tmarket where tuser=:tuser",params);
		}else{
			params.put("phone",oldPhone);
			tmarket=marketDao.get("from Tmarket where tuser=:tuser and phone=:phone",params);
		}
		if (tmarket!=null) {
			tmarket.setPhone(newPhone);
			return true;
		}else {
			return false;
		}
	}


	@Override
	public String getPhone(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class, sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser",tuser);
		Tmarket tmarket=marketDao.get("from Tmarket where tuser=:tuser",params);
		return tmarket.getPhone();
	}


	@Override
	public List<Order> getOrder(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Tmarket tmarket=tuser.getTmarket();
		Map<String, Object> params=new HashMap<String, Object>(0);
		params.put("tmarket", tmarket);
		List<Torder> torders=orderDao.find("from Torder where tmarket=:tmarket order by createTime desc", params);
		List<Order> orders=new ArrayList<Order>();
		for(Torder torder:torders){
			torder.setOrderRead(true);
			Order order=new Order();
			BeanUtils.copyProperties(torder, order);
			Map<String, Object> params1=new HashMap<String, Object>(0);
			params1.put("torder", torder);
			List<TorderBranch> torderBranchs=orderBranchDao.find("from TorderBranch where torder=:torder order by added",params1);
			List<Product> products=new ArrayList<Product>();
			for(TorderBranch torderBranch:torderBranchs){
				Tproduct tproduct=torderBranch.getTproduct();
				Product product=new Product();
				BeanUtils.copyProperties(tproduct, product);
				product.setAdded(torderBranch.getAdded());
				products.add(product);
			}
			order.setProducts((Product[])products.toArray(new Product[0]));
			orders.add(order);
		}
		return orders;
	}
	
	@Override
	public List<Order> getOrderByCustomer(Unit unit) {
		Tunit tunit=unitDao.get(Tunit.class,unit.getId());
		Tmarket tmarket=tunit.getTmarket();
		Map<String, Object> params=new HashMap<String, Object>(0);
		params.put("tmarket", tmarket);
		List<Torder> torders=orderDao.find("from Torder where tmarket=:tmarket and review is not null order by createTime desc", params);
		List<Order> orders=new ArrayList<Order>();
		for(Torder torder:torders){
			Order order=new Order();
			BeanUtils.copyProperties(torder, order);
			orders.add(order);
		}
		return orders;
	}
	
	@Override
	public List<Order> getUnhandledOrder(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Tmarket tmarket=tuser.getTmarket();
		Map<String, Object> params=new HashMap<String, Object>(0);
		params.put("tmarket", tmarket);
		params.put("status", "待接单");
		List<Torder> torders=orderDao.find("from Torder where tmarket=:tmarket and status=:status order by createTime desc", params);
		List<Order> orders=new ArrayList<Order>();
		for(Torder torder:torders){
			Order order=new Order();
			torder.setOrderRead(true);
			BeanUtils.copyProperties(torder, order);
			Map<String, Object> params1=new HashMap<String, Object>(0);
			params1.put("torder", torder);
			List<TorderBranch> torderBranchs=orderBranchDao.find("from TorderBranch where torder=:torder order by added",params1);
			List<Product> products=new ArrayList<Product>();
			for(TorderBranch torderBranch:torderBranchs){
				Tproduct tproduct=torderBranch.getTproduct();
				Product product=new Product();
				BeanUtils.copyProperties(tproduct, product);
				product.setAdded(torderBranch.getAdded());
				products.add(product);
			}
			order.setProducts((Product[])products.toArray(new Product[0]));
			orders.add(order);
		}
			
		return orders;
	}


	@Override
	public List<Order> getUnreceivedOrder(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Tmarket tmarket=tuser.getTmarket();
		Map<String, Object> params=new HashMap<String, Object>(0);
		params.put("tmarket", tmarket);
		params.put("status", "已接单");
		List<Torder> torders=orderDao.find("from Torder where tmarket=:tmarket and status=:status order by createTime desc", params);
		List<Order> orders=new ArrayList<Order>();
		for(Torder torder:torders){
			Order order=new Order();
			BeanUtils.copyProperties(torder, order);
			Map<String, Object> params1=new HashMap<String, Object>(0);
			params1.put("torder", torder);
			List<TorderBranch> torderBranchs=orderBranchDao.find("from TorderBranch where torder=:torder order by added",params1);
			List<Product> products=new ArrayList<Product>();
			for(TorderBranch torderBranch:torderBranchs){
				Tproduct tproduct=torderBranch.getTproduct();
				Product product=new Product();
				BeanUtils.copyProperties(tproduct, product);
				product.setAdded(torderBranch.getAdded());
				products.add(product);
			}
			order.setProducts((Product[])products.toArray(new Product[0]));
			orders.add(order);
		}
			
		return orders;
	}


	@Override
	public List<Order> getFinishedOrder(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Tmarket tmarket=tuser.getTmarket();
		Map<String, Object> params=new HashMap<String, Object>(0);
		params.put("tmarket", tmarket);
		params.put("status1", "已收货");
		params.put("status2", "已评价");
		List<Torder> torders=orderDao.find("from Torder where tmarket=:tmarket and (status=:status1 or status=:status2) order by createTime desc", params);
		List<Order> orders=new ArrayList<Order>();
		for(Torder torder:torders){
			Order order=new Order();
			BeanUtils.copyProperties(torder, order);
			Map<String, Object> params1=new HashMap<String, Object>(0);
			params1.put("torder", torder);
			List<TorderBranch> torderBranchs=orderBranchDao.find("from TorderBranch where torder=:torder order by added",params1);
			List<Product> products=new ArrayList<Product>();
			for(TorderBranch torderBranch:torderBranchs){
				Tproduct tproduct=torderBranch.getTproduct();
				Product product=new Product();
				BeanUtils.copyProperties(tproduct, product);
				product.setAdded(torderBranch.getAdded());
				products.add(product);
			}
			order.setProducts((Product[])products.toArray(new Product[0]));
			orders.add(order);
		}
			
		return orders;
	}
	
	@Override
	public void cancelOrder(Order order, SessionInfo sessionInfo) {
		Torder torder=orderDao.get(Torder.class, order.getId());
		torder.setStatus("已拒接");
	}


	@Override
	public void acceptOrder(Order order, SessionInfo sessionInfo) {
		Torder torder=orderDao.get(Torder.class, order.getId());
		torder.setStatus("已接单");
	}


	@Override
	public List<Market> listByAdmin(SessionInfo sessionInfo) {
		if(sessionInfo.getType()==0){
			List<Tmarket> tmarkets=marketDao.find("from Tmarket");
			List<Market> markets=new ArrayList<Market>();
			for(Tmarket tmarket:tmarkets){
				Market market=new Market();
				BeanUtils.copyProperties(tmarket, market);
				market.setCreateTime(tmarket.getTuser().getCreateTime());
				market.setLastTime(tmarket.getTuser().getLastTime());
				markets.add(market);
			}
			return markets;
		}else {
			return null;
		}
	}
	
	@Override
	public boolean editName(String name, SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class, sessionInfo.getId());
		Tmarket tmarket=tuser.getTmarket();
		tmarket.setName(name);
		sessionInfo.setName(name);
		return true;
	}


	@Override
	public String getBroadcast(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Tmarket tmarket=tuser.getTmarket();
		return tmarket.getBroadcast();
	}


	@Override
	public String editBroadcast(String broadcast,SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Tmarket tmarket=tuser.getTmarket();
		tmarket.setBroadcast(broadcast);
		return broadcast;
	}


	@Override
	public List<Order> listReview(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Tmarket tmarket=tuser.getTmarket();
		Map<String, Object> params=new HashMap<String, Object>(0);
		params.put("tmarket", tmarket);
		List<Torder> torders=orderDao.find("from Torder where tmarket=:tmarket and review is not null order by createTime desc", params);
		List<Order> orders=new ArrayList<Order>();
		for(Torder torder:torders){
			Order order=new Order();
			torder.setReviewRead(true);
			BeanUtils.copyProperties(torder, order);
			orders.add(order);
		}
		return orders;
	}


	@Override
	public List<Order> listUnrepliedReview(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Tmarket tmarket=tuser.getTmarket();
		Map<String, Object> params=new HashMap<String, Object>(0);
		params.put("tmarket", tmarket);
		List<Torder> torders=orderDao.find("from Torder where tmarket=:tmarket and review is not null and replyReview is null order by createTime desc", params);
		List<Order> orders=new ArrayList<Order>();
		for(Torder torder:torders){
			Order order=new Order();
			torder.setReviewRead(true);
			BeanUtils.copyProperties(torder, order);
			orders.add(order);
		}
		return orders;
	}


	@Override
	public List<Order> listRepliedReview(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Tmarket tmarket=tuser.getTmarket();
		Map<String, Object> params=new HashMap<String, Object>(0);
		params.put("tmarket", tmarket);
		List<Torder> torders=orderDao.find("from Torder where tmarket=:tmarket and review is not null and replyReview is not null order by createTime desc", params);
		List<Order> orders=new ArrayList<Order>();
		for(Torder torder:torders){
			Order order=new Order();
			BeanUtils.copyProperties(torder, order);
			orders.add(order);
		}
		return orders;
	}


	@Override
	public void replyReview(Order order, SessionInfo sessionInfo) {
		Torder torder=orderDao.get(Torder.class, order.getId());
		torder.setReplyReview(order.getReplyReview());
	}


	@Override
	public OrderMessage getOrderMessage(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Tmarket tmarket=tuser.getTmarket();
		Map<String, Object> params=new HashMap<String, Object>(0);
		params.put("tmarket", tmarket);
		List<Torder> torders=orderDao.find("from Torder where tmarket=:tmarket order by createTime desc", params);
		int orderUnread=0;
		int reviewUnread=0;
		OrderMessage orderMessage=new OrderMessage();
		for(Torder torder:torders){
			if(!torder.isOrderRead())
				orderUnread+=1;
		}
		List<Torder> torders2=orderDao.find("from Torder where tmarket=:tmarket and review is not null order by createTime desc", params);
		for(Torder torder:torders2){
			if(!torder.isReviewRead())
				reviewUnread+=1;
		}
		orderMessage.setOrderUnread(orderUnread);
		orderMessage.setReviewUnread(reviewUnread);
		return orderMessage;
	}


	@Override
	public String getBroadcastByCustomer(Unit unit) {
		Tunit tunit=unitDao.get(Tunit.class, unit.getId());
		Tmarket tmarket=tunit.getTmarket();
		return tmarket.getBroadcast();
	}




}
