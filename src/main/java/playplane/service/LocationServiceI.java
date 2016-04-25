package playplane.service;

import java.util.List;

import playplane.model.Unit;

/**
 * 定位Service
 * 
 * @author chikara
 * 
 */
public interface LocationServiceI {
	/**
	 * 获取省份
	 * 
	 */
	public List<String> province();
	/**
	 * 获取城市
	 * 
	 */
	public List<String> city(String province);
	/**
	 * 获取学校
	 * 
	 */
	public List<String> school(String city);
	/**
	 * 获取单元
	 * 
	 */
	public List<Unit> unit(String school);

}
