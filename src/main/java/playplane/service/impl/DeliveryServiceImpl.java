package playplane.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import playplane.dao.CustomerDaoI;
import playplane.dao.DeliveryDaoI;
import playplane.dao.UserDaoI;
import playplane.entity.Tcustomer;
import playplane.entity.Tdelivery;
import playplane.entity.Tuser;
import playplane.model.Delivery;
import playplane.model.SessionInfo;
import playplane.service.DeliveryServiceI;

@Service
public class DeliveryServiceImpl implements DeliveryServiceI {

	@Autowired
	private DeliveryDaoI deliveryDao;
	
	@Autowired
	private CustomerDaoI customerDao;
	
	@Autowired
	private UserDaoI userDao;
	
	
	@Override
	public boolean add(Delivery delivery,SessionInfo sessionInfo) {
		Tdelivery tdelivery=new Tdelivery();
		BeanUtils.copyProperties(delivery, tdelivery);
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser", tuser);
		Tcustomer tcustomer=customerDao.get("from Tcustomer where tuser=:tuser", params);
		tdelivery.setTcustomer(tcustomer);
		tdelivery.setId(UUID.randomUUID().toString());
		deliveryDao.save(tdelivery);
		return true;
	}

	@Override
	public List<Delivery> list(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser", tuser);
		Tcustomer tcustomer=customerDao.get("from Tcustomer where tuser=:tuser", params);
		Map<String, Object> params1= new HashMap<String, Object>();
		params1.put("tcustomer", tcustomer);
		List<Tdelivery> tdeliverys=deliveryDao.find("from Tdelivery where tcustomer=:tcustomer", params1);
		List<Delivery> deliverys=new ArrayList<Delivery>();
		for(Tdelivery t:tdeliverys){
			Delivery p=new Delivery();
			BeanUtils.copyProperties(t,p);
			deliverys.add(p);
		}
		return deliverys;
	}

	@Override
	public boolean delete(Delivery delivery,SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser", tuser);
		Tcustomer tcustomer=customerDao.get("from Tcustomer where tuser=:tuser", params);
		Map<String, Object> params1= new HashMap<String, Object>();
		params1.put("tcustomer", tcustomer);
		params1.put("id", delivery.getId());
		Tdelivery tdelivery=deliveryDao.get("from Tdelivery where tcustomer=:tcustomer and id=:id", params1);
		if(tdelivery!=null){
			deliveryDao.delete(tdelivery);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Delivery editPage(Delivery delivery, SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser", tuser);
		Tcustomer tcustomer=customerDao.get("from Tcustomer where tuser=:tuser", params);
		Map<String, Object> params1= new HashMap<String, Object>();
		params1.put("tcustomer", tcustomer);
		params1.put("id", delivery.getId());
		Tdelivery tdelivery=deliveryDao.get("from Tdelivery where tcustomer=:tcustomer and id=:id", params1);
		if(tdelivery!=null){
			BeanUtils.copyProperties(tdelivery, delivery);
			return delivery;
		}else {
			return null;
		}
	}

	@Override
	public boolean edit(Delivery delivery, SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser", tuser);
		Tcustomer tcustomer=customerDao.get("from Tcustomer where tuser=:tuser", params);
		Map<String, Object> params1= new HashMap<String, Object>();
		params1.put("tcustomer", tcustomer);
		params1.put("id", delivery.getId());
		Tdelivery tdelivery=deliveryDao.get("from Tdelivery where tcustomer=:tcustomer and id=:id", params1);
		if(tdelivery!=null){
			BeanUtils.copyProperties(delivery, tdelivery);
			return true;
		}else {
			return false;
		}
	}

}
