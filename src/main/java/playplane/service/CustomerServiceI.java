package playplane.service;

import java.util.List;

import playplane.model.Customer;
import playplane.model.SessionInfo;

/**
 * 超市入驻申请表Service
 * 
 * @author chikara
 * 
 */
public interface CustomerServiceI {
	/**
	 * 获得顾客信息
	 * 
	 */
	public Customer show(SessionInfo sessionInfo);
	
	/** 
	 * 绑定邮箱
	 * 
	 */
	public boolean editEmail(String oldEmail,String newEmail,SessionInfo sessionInfo);
	
	
	/** 
	 * 获取邮箱
	 * 
	 */
	public String getEmail(SessionInfo sessionInfo);
	
	/** 
	 * 绑定手机
	 * 
	 */
	public boolean editPhone(String oldPhone,String newPhone,SessionInfo sessionInfo);
	
	
	/** 
	 * 获取手机
	 * 
	 */
	public String getPhone(SessionInfo sessionInfo);
	
	/** 
	 * 修改姓名
	 * 
	 */
	public boolean editName(String name,SessionInfo sessionInfo);

	/** 
	 * 管理员获取所有用户列表
	 * 
	 */
	public List<Customer> listByAdmin(SessionInfo sessionInfo);
}
