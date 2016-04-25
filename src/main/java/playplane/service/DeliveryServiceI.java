package playplane.service;

import java.util.List;

import playplane.model.Delivery;
import playplane.model.SessionInfo;

/**
 * 收货地址Service
 * 
 * @author chikara
 * 
 */
public interface DeliveryServiceI {
	/**
	 * 地址添加
	 * 
	 */
	public boolean add(Delivery delivery,SessionInfo sessionInfo);
	
	/**
	 * 获取地址列表
	 * 
	 */
	public List<Delivery> list(SessionInfo sessionInfo);
	
	/**
	 * 地址删除
	 * 
	 */
	public boolean delete(Delivery delivery,SessionInfo sessionInfo);
	
	/**
	 * 获取地址编辑页面
	 * 
	 */
	public Delivery editPage(Delivery delivery,SessionInfo sessionInfo);
	
	/**
	 * 地址编辑
	 * 
	 */
	public boolean edit(Delivery delivery,SessionInfo sessionInfo);
	

}
