package playplane.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import playplane.dao.DeliveryDaoI;
import playplane.dao.MarketDaoI;
import playplane.dao.OrderBranchDaoI;
import playplane.dao.OrderDaoI;
import playplane.dao.ProductDaoI;
import playplane.dao.UnitDaoI;
import playplane.dao.UserDaoI;
import playplane.entity.Tmarket;
import playplane.entity.Torder;
import playplane.entity.TorderBranch;
import playplane.entity.Tproduct;
import playplane.entity.Tschool;
import playplane.entity.Tunit;
import playplane.entity.Tuser;
import playplane.model.Market;
import playplane.model.Order;
import playplane.model.Product;
import playplane.model.SessionInfo;
import playplane.service.OrderServiceI;

@Service
public class OrderServiceImpl implements OrderServiceI {

	@Autowired
	private ProductDaoI productDao;

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

	@Autowired
	private DeliveryDaoI deliveryDao;

	@Override
	public Order submitOrder(Order order, SessionInfo sessionInfo) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyHHMMmmddss");
		Torder torder = new Torder();
		BeanUtils.copyProperties(order, torder);
		torder.setId(simpleDateFormat.format(new Date()) + (int) (Math.random() * 10) + (int) (Math.random() * 10));
		orderDao.save(torder);
		double total = 0;
		for (Product product : order.getProducts()) {
			Tproduct tproduct = productDao.get(Tproduct.class, product.getId());
			tproduct.setNumber(tproduct.getNumber() - product.getAdded());
			if (tproduct.getNumber() < 0)
				return null;
			TorderBranch torderBranch = new TorderBranch();
			torderBranch.setId(UUID.randomUUID().toString());
			torderBranch.setAdded(product.getAdded());
			torderBranch.setTorder(torder);
			torderBranch.setTproduct(tproduct);
			orderBranchDao.save(torderBranch);
			total += tproduct.getPrice() * product.getAdded();
		}
		torder.setCreateTime(new Date());
		torder.setAddress(order.getAddress());
		torder.setTotal(total);
		Tuser tuser = userDao.get(Tuser.class, sessionInfo.getId());
		torder.setTuser(tuser);
		Tproduct tproduct = productDao.get(Tproduct.class, order.getProducts()[0].getId());
		Tmarket tmarket = tproduct.getTmarket();
		torder.setTmarket(tmarket);
		torder.setStatus("待接单");
		BeanUtils.copyProperties(torder, order);
		return order;
	}

	@Override
	public Order getOrder(Order order, SessionInfo sessionInfo) {
		Torder torder = orderDao.get(Torder.class, order.getId());
		BeanUtils.copyProperties(torder, order);
		Tmarket tmarket = torder.getTmarket();
		Market market = new Market();
		Tunit tunit = tmarket.getTunit();
		Tschool tschool = tunit.getTschool();
		market.setAddress(tschool.getName() + tunit.getName());
		BeanUtils.copyProperties(tmarket, market);
		order.setMarket(market);
		Set<TorderBranch> torderBranchs = torder.getTorderBranchs();
		List<Product> products = new ArrayList<Product>();
		for (TorderBranch torderBranch : torderBranchs) {
			Tproduct tproduct = torderBranch.getTproduct();
			Product product = new Product();
			BeanUtils.copyProperties(tproduct, product);
			product.setAdded(torderBranch.getAdded());
			products.add(product);
		}
		order.setProducts((Product[]) products.toArray(new Product[0]));
		return order;
	}

	@Override
	public void cancelOrder(Order order, SessionInfo sessionInfo) {
		Torder torder = orderDao.get(Torder.class, order.getId());
		torder.setOrderRead(true);
		Set<TorderBranch> torderBranchs = torder.getTorderBranchs();
		for (TorderBranch torderBranch : torderBranchs) {
			Tproduct tproduct = torderBranch.getTproduct();
			tproduct.setNumber(tproduct.getNumber() + torderBranch.getAdded());
		}
		torder.setStatus("已取消");
	}

	@Override
	public void finishOrder(Order order, SessionInfo sessionInfo) {
		Torder torder = orderDao.get(Torder.class, order.getId());
		torder.setStatus("已收货");
	}

	@Override
	public void reviewOrder(Order order, SessionInfo sessionInfo) {
		Torder torder = orderDao.get(Torder.class, order.getId());
		torder.setStatus("已评价");
		torder.setReview(order.getReview());
	}

	@Override
	public List<Order> listOrder(String key, SessionInfo sessionInfo) {
		Tuser tuser = userDao.get(Tuser.class, sessionInfo.getId());
		Map<String, Object> params = new HashMap<String, Object>(0);
		params.put("tuser", tuser);
		List<Torder> torders = new ArrayList<Torder>();
		if (key.equals("all")) {
			torders = orderDao.find("from Torder where tuser=:tuser order by createTime desc", params);
		} else if (key.equals("unhandled")) {
			params.put("status", "待接单");
			torders = orderDao.find("from Torder where tuser=:tuser and status=:status order by createTime desc",
					params);
		} else if (key.equals("unreceived")) {
			params.put("status", "待发货");
			torders = orderDao.find("from Torder where tuser=:tuser and status=:status order by createTime desc",
					params);
		} else if (key.equals("unreviewed")) {
			params.put("status", "已收货");
			torders = orderDao.find("from Torder where tuser=:tuser and status=:status order by createTime desc",
					params);
		} else if (key.equals("reviewed")) {
			params.put("status", "已评价");
			torders = orderDao.find("from Torder where tuser=:tuser and status=:status order by createTime desc",
					params);
		}
		List<Order> orders = new ArrayList<Order>();
		for (Torder torder : torders) {
			Order order = new Order();
			BeanUtils.copyProperties(torder, order);
			Map<String, Object> params1 = new HashMap<String, Object>(0);
			params1.put("torder", torder);
			List<TorderBranch> torderBranchs = orderBranchDao
					.find("from TorderBranch where torder=:torder order by added", params1);
			List<Product> products = new ArrayList<Product>();
			for (TorderBranch torderBranch : torderBranchs) {
				Tproduct tproduct = torderBranch.getTproduct();
				Product product = new Product();
				BeanUtils.copyProperties(tproduct, product);
				product.setAdded(torderBranch.getAdded());
				products.add(product);
			}
			order.setProducts((Product[]) products.toArray(new Product[0]));
			orders.add(order);
		}
		return orders;
	}

}
