package playplane.service.impl;

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

import playplane.dao.CustomerDaoI;
import playplane.dao.MarketDaoI;
import playplane.dao.RoleDaoI;
import playplane.dao.UserDaoI;
import playplane.entity.Tcustomer;
import playplane.entity.Tmarket;
import playplane.entity.Tresource;
import playplane.entity.Tuser;
import playplane.model.SessionInfo;
import playplane.model.User;
import playplane.service.UserServiceI;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserDaoI userDao;
	
	@Autowired
	private RoleDaoI roleDao;
	
	@Autowired
	private CustomerDaoI customerDao;
	
	@Autowired
	private MarketDaoI marketDao;

	@Override
	public User login(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		Tuser u = userDao.get("from Tuser t where t.username = :username and t.password = :password", params);
		if (u != null) {
			BeanUtils.copyProperties(u, user);
			u.setLastTime(new Date());
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("id", u);
			if(u.getTrole().getName().equals("普通用户")){
				Tcustomer c=customerDao.get("from Tcustomer t where t.tuser = :id", params1);
				user.setName(c.getName());
				user.setType(2);
			}else if(u.getTrole().getName().equals("超市")){
				Tmarket m=marketDao.get("from Tmarket t where t.tuser = :id", params1);
				user.setName(m.getName());
				user.setType(1);
			}else if(u.getTrole().getName().equals("管理员")){
				user.setType(0);
			}
			return user;
		}
		return null;
	}

	@Override
	synchronized public User reg(User user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", user.getUsername());
		if (userDao.count("select count(*) from Tuser t where t.username = :username", params) > 0) {
			return null;
		} else {
			Tuser u = new Tuser();
			u.setId(UUID.randomUUID().toString());
			u.setUsername(user.getUsername());
			u.setPassword(user.getPassword());
			u.setCreateTime(new Date());
			Map<String, Object> params1 = new HashMap<String, Object>();
			params1.put("name", "普通用户");
			u.setTrole(roleDao.get("from Trole t where t.name = :name", params1));
			userDao.save(u);
			Tcustomer c=new Tcustomer();
			c.setId(UUID.randomUUID().toString());
			c.setTuser(u);
			c.setName(user.getName());
			customerDao.save(c);
			BeanUtils.copyProperties(u, user);
			return user;
		}
		
	}


	@Override
	public List<String> resourceList(String id) {
		List<String> resourceList = new ArrayList<String>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		Tuser t = userDao.get(
				"from Tuser t where t.id = :id", params);
		if (t != null) {
			Set<Tresource> resources = t.getTrole().getTresources();
			if (resources != null && !resources.isEmpty()) {
				for (Tresource resource : resources) {
					if (resource != null && resource.getUrl() != null) {
						resourceList.add(resource.getUrl());
					}
				}
			}
		}
		return resourceList;
	}


	@Override
	public boolean editPwd(String oldPassword, String newPassword,SessionInfo sessionInfo) {
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("id",sessionInfo.getId());
		params.put("password", oldPassword);
		Tuser tuser=userDao.get("from Tuser where id=:id and password=:password",params);
		if (tuser!=null) {
			tuser.setPassword(newPassword);
			return true;
		}else {
			return false;
		}
	}

}
