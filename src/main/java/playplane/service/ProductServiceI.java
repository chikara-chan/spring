package playplane.service;

import java.util.List;

import playplane.model.Address;
import playplane.model.Product;
import playplane.model.SessionInfo;
import playplane.model.Unit;

/**
 * 商品Service
 * 
 * @author chikara
 * 
 */
public interface ProductServiceI {
	/**
	 * 商品添加
	 * 
	 */
	public boolean add(Product product,SessionInfo sessionInfo);
	
	/**
	 * 商家获取商品列表
	 * 
	 */
	public List<Product> listByMarket(SessionInfo sessionInfo);
	/**
	 * 用户获取商品列表
	 * 
	 */
	public List<Product> listByCustomer(Unit unit);
	
	/**
	 * 获取楼栋所在地址
	 * 
	 */
	public Address findAddress(Unit unit);
	
	/**
	 * 商品删除
	 * 
	 */
	public boolean delete(Product product,SessionInfo sessionInfo);
	
	/**
	 * 获取商品编辑页面
	 * 
	 */
	public Product editPage(Product product,SessionInfo sessionInfo);
	
	/**
	 * 商品编辑
	 * 
	 */
	public boolean edit(Product product,SessionInfo sessionInfo);
	
	/**
	 *  根据商品id从获取最新的商品信息
	 *  
	 */
	public List<Product> getInfo(List<Product> products);
	
	/**
	 *  根据商品id从获取最新的商品信息
	 *  
	 */
	public double count(List<Product> products);
	

}
