package playplane.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import playplane.dao.CustomerDaoI;
import playplane.dao.UserDaoI;
import playplane.entity.Tcustomer;
import playplane.entity.Tmarket;
import playplane.entity.Tproduct;
import playplane.entity.Tuser;
import playplane.model.Customer;
import playplane.model.Market;
import playplane.model.SessionInfo;
import playplane.service.CustomerServiceI;

@Service
public class CustomerServiceImpl implements CustomerServiceI {

	@Autowired
	private CustomerDaoI customerDao;
	
	@Autowired
	private UserDaoI userDao;
	

	@Override
	public Customer show(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class, sessionInfo.getId());
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("tuser", tuser);
		Tcustomer tcustomer=customerDao.get("from Tcustomer where tuser=:tuser", params);
		if (tcustomer!=null) {
			Customer customer=new Customer();
			BeanUtils.copyProperties(tcustomer, customer);
			customer.setCreateTime(tuser.getCreateTime());
			customer.setLastTime(tuser.getLastTime());
			return customer;
		}else {
			return null;
		}
	}


	@Override
	public boolean editEmail(String oldEmail, String newEmail, SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser",tuser);
		Tcustomer tcustomer=new Tcustomer();
		if(StringUtils.isEmpty(oldEmail)){
			tcustomer=customerDao.get("from Tcustomer where tuser=:tuser",params);
		}else{
			params.put("email",oldEmail);
			tcustomer=customerDao.get("from Tcustomer where tuser=:tuser and email=:email",params);
		}
		if (tcustomer!=null) {
			tcustomer.setEmail(newEmail);
			return true;
		}else {
			return false;
		}
	}


	@Override
	public String getEmail(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class, sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser",tuser);
		Tcustomer tcustomer=customerDao.get("from Tcustomer where tuser=:tuser",params);
		return tcustomer.getEmail();
	}


	@Override
	public boolean editPhone(String oldPhone, String newPhone, SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser",tuser);
		Tcustomer tcustomer=new Tcustomer();
		if(StringUtils.isEmpty(oldPhone)){
			tcustomer=customerDao.get("from Tcustomer where tuser=:tuser",params);
		}else{
			params.put("phone",oldPhone);
			tcustomer=customerDao.get("from Tcustomer where tuser=:tuser and phone=:phone",params);
		}
		if (tcustomer!=null) {
			tcustomer.setPhone(newPhone);
			return true;
		}else {
			return false;
		}
	}


	@Override
	public String getPhone(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class, sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser",tuser);
		Tcustomer tcustomer=customerDao.get("from Tcustomer where tuser=:tuser",params);
		return tcustomer.getPhone();
	}
	
	@Override
	public List<Customer> listByAdmin(SessionInfo sessionInfo) {
		if(sessionInfo.getType()==0){
			List<Tcustomer> tcustomers=customerDao.find("from Tcustomer");
			List<Customer> customers=new ArrayList<Customer>();
			for(Tcustomer tcustomer:tcustomers){
				Customer customer=new Customer();
				BeanUtils.copyProperties(tcustomer, customer);
				customer.setCreateTime(tcustomer.getTuser().getCreateTime());
				customer.setLastTime(tcustomer.getTuser().getLastTime());
				customers.add(customer);
			}
			return customers;
		}else {
			return null;
		}
	}


	@Override
	public boolean editName(String name, SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class, sessionInfo.getId());
		Tcustomer tcustomer=tuser.getTcustomer();
		tcustomer.setName(name);
		sessionInfo.setName(name);
		return true;
	}
	
}
