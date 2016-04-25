package playplane.service;

import java.util.List;

import playplane.model.Apply;
import playplane.model.SessionInfo;

/**
 * 超市入驻申请表Service
 * 
 * @author chikara
 * 
 */
public interface ApplyServiceI {
	/**
	 * 根据楼栋id获取申请表
	 * 
	 */
	public Apply getByUnitId(String id);
	
	/**
	 * 存储提交过来的申请表
	 * 
	 */
	public void submit(Apply apply) throws Exception;
	
	/**
	 * 获取所有超市入驻申请表
	 * 
	 */
	public List<Apply> list(SessionInfo sessionInfo) throws Exception;
	
	/**
	 * 批准超市入驻
	 * 
	 */
	public void agree(Apply apply);
	
	/**
	 * 拒绝超市入驻
	 * 
	 */
	public void reject(Apply apply);

}
