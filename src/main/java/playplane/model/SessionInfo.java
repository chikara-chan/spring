package playplane.model;

import java.util.List;

/**
 * session信息模型
 * 
 * @author chikara
 * 
 */
public class SessionInfo {

	private String id;// 用户ID
	private String name;// 用户登录名
	private int type;// 用户类型  , 0->管理员 , 1->超市 , 2->普通用户

	private List<String> resourceList;// 用户可以访问的资源地址列表

	public List<String> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
