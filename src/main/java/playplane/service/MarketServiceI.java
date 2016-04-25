package playplane.service;

import java.util.List;

import playplane.model.Market;
import playplane.model.Order;
import playplane.model.OrderMessage;
import playplane.model.SessionInfo;
import playplane.model.Unit;

/**
 * 超市入驻申请表Service
 * 
 * @author chikara
 * 
 */
public interface MarketServiceI {
	/**
	 * 获得商家信息
	 * 
	 */
	public Market show(SessionInfo sessionInfo);

	/**
	 * 修改姓名
	 * 
	 */
	public boolean editName(String name, SessionInfo sessionInfo);

	/**
	 * 绑定邮箱
	 * 
	 */
	public boolean editEmail(String oldEmail, String newEmail, SessionInfo sessionInfo);

	/**
	 * 获取邮箱
	 * 
	 */
	public String getEmail(SessionInfo sessionInfo);

	/**
	 * 绑定手机
	 * 
	 */
	public boolean editPhone(String oldPhone, String newPhone, SessionInfo sessionInfo);

	/**
	 * 获取手机
	 * 
	 */
	public String getPhone(SessionInfo sessionInfo);

	/**
	 * 获取所有订单
	 * 
	 */
	public List<Order> getOrder(SessionInfo sessionInfo);

	/**
	 * 商家拒接订单
	 * 
	 */
	public void cancelOrder(Order order, SessionInfo sessionInfo);

	/**
	 * 商家接单
	 * 
	 */
	public void acceptOrder(Order order, SessionInfo sessionInfo);

	/**
	 * 管理员获取所有超市列表
	 * 
	 */
	public List<Market> listByAdmin(SessionInfo sessionInfo);

	/**
	 * 获取处于待接单状态订单
	 * 
	 */
	public List<Order> getUnhandledOrder(SessionInfo sessionInfo);

	/**
	 * 获取处于已接单状态订单
	 * 
	 */
	public List<Order> getUnreceivedOrder(SessionInfo sessionInfo);

	/**
	 * 获取处于已完成状态订单
	 * 
	 */
	public List<Order> getFinishedOrder(SessionInfo sessionInfo);
	/**
	 * 获取店铺公告
	 * 
	 */
	public String getBroadcast(SessionInfo sessionInfo);
	/**
	 * 修改店铺公告
	 * 
	 */
	public String editBroadcast(String broadcast,SessionInfo sessionInfo);
	/**
	 * 商家获取所有评价
	 * 
	 */
	public List<Order> listReview(SessionInfo sessionInfo);
	/**
	 * 商家获取未回复评价
	 * 
	 */
	public List<Order> listUnrepliedReview(SessionInfo sessionInfo);
	/**
	 * 商家获取回复评价
	 * 
	 */
	public List<Order> listRepliedReview(SessionInfo sessionInfo);
	/**
	 * 商家回复评价
	 * 
	 */
	public void replyReview(Order order, SessionInfo sessionInfo);
	/**
	 * 商家获取店铺动态信息
	 * 
	 */
	public OrderMessage getOrderMessage(SessionInfo sessionInfo);
	/**
	 * 用户获取店铺公告
	 * 
	 */
	public String getBroadcastByCustomer(Unit unit);
	/**
	 * 用户获取商家订单
	 * 
	 */
	public List<Order> getOrderByCustomer(Unit unit);
}
