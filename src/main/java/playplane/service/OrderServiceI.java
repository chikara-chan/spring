package playplane.service;

import java.util.List;

import playplane.model.Order;
import playplane.model.Product;
import playplane.model.SessionInfo;

/**
 * 订单Service
 * 
 * @author chikara
 * 
 */
public interface OrderServiceI {
	/**
	 * 用户提交订单
	 * 
	 */
	public Order submitOrder(Order order,SessionInfo sessionInfo);
	/**
	 * 用户获得订单
	 * 
	 */
	public Order getOrder(Order order,SessionInfo sessionInfo);
	/**
	 * 用户取消订单
	 * 
	 */
	public void cancelOrder(Order order,SessionInfo sessionInfo);
	
	/**
	 * 用户确认收货
	 * 
	 */
	public void finishOrder(Order order,SessionInfo sessionInfo);
	
	/**
	 * 用户评价订单
	 * 
	 */
	public void reviewOrder(Order order,SessionInfo sessionInfo);
	
	/**
	 * 用户订单管理
	 * 
	 */
	public List<Order> listOrder(String key,SessionInfo sessionInfo);

}
