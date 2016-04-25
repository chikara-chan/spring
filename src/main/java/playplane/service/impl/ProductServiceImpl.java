package playplane.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import playplane.dao.MarketDaoI;
import playplane.dao.ProductDaoI;
import playplane.dao.UnitDaoI;
import playplane.dao.UserDaoI;
import playplane.entity.Tmarket;
import playplane.entity.Tproduct;
import playplane.entity.Tschool;
import playplane.entity.Tunit;
import playplane.entity.Tuser;
import playplane.model.Address;
import playplane.model.Product;
import playplane.model.SessionInfo;
import playplane.model.Unit;
import playplane.service.ProductServiceI;

@Service
public class ProductServiceImpl implements ProductServiceI {

	@Autowired
	private ProductDaoI productDao;
	
	@Autowired
	private MarketDaoI marketDao;
	
	@Autowired
	private UserDaoI userDao;
	
	@Autowired
	private UnitDaoI unitDao;
	
	@Override
	public boolean add(Product product,SessionInfo sessionInfo) {
		Tproduct tproduct=new Tproduct();
		BeanUtils.copyProperties(product, tproduct);
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser", tuser);
		Tmarket tmarket=marketDao.get("from Tmarket where tuser=:tuser", params);
		tproduct.setTmarket(tmarket);
		tproduct.setId(UUID.randomUUID().toString());
		tproduct.setPic(product.getPic());
		productDao.save(tproduct);
		return true;
	}

	@Override
	public List<Product> listByMarket(SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser", tuser);
		Tmarket tmarket=marketDao.get("from Tmarket where tuser=:tuser", params);
		Map<String, Object> params1= new HashMap<String, Object>();
		params1.put("tmarket", tmarket);
		List<Tproduct> tproducts=productDao.find("from Tproduct where tmarket=:tmarket", params1);
		List<Product> products=new ArrayList<Product>();
		for(Tproduct t:tproducts){
			Product p=new Product();
			BeanUtils.copyProperties(t,p);
			products.add(p);
		}
		return products;
	}

	@Override
	public List<Product> listByCustomer(Unit unit) {
		Tunit tunit=unitDao.get(Tunit.class, unit.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tunit", tunit);
		Tmarket tmarket=marketDao.get("from Tmarket where tunit=:tunit", params);
		Map<String, Object> params1= new HashMap<String, Object>();
		params1.put("tmarket", tmarket);
		List<Tproduct> tproducts=productDao.find("from Tproduct where tmarket=:tmarket", params1);
		List<Product> products=new ArrayList<Product>();
		for(Tproduct t:tproducts){
			Product p=new Product();
			BeanUtils.copyProperties(t,p);
			products.add(p);
		}
		return products;
	}
	
	@Override
	public boolean delete(Product product,SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser", tuser);
		Tmarket tmarket=marketDao.get("from Tmarket where tuser=:tuser", params);
		Map<String, Object> params1= new HashMap<String, Object>();
		params1.put("tmarket", tmarket);
		params1.put("id", product.getId());
		Tproduct tproduct=productDao.get("from Tproduct where tmarket=:tmarket and id=:id", params1);
		if(tproduct!=null){
			productDao.delete(tproduct);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Product editPage(Product product, SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser", tuser);
		Tmarket tmarket=marketDao.get("from Tmarket where tuser=:tuser", params);
		Map<String, Object> params1= new HashMap<String, Object>();
		params1.put("tmarket", tmarket);
		params1.put("id", product.getId());
		Tproduct tproduct=productDao.get("from Tproduct where tmarket=:tmarket and id=:id", params1);
		if(tproduct!=null){
			BeanUtils.copyProperties(tproduct, product);
			return product;
		}else {
			return null;
		}
	}

	@Override
	public boolean edit(Product product, SessionInfo sessionInfo) {
		Tuser tuser=userDao.get(Tuser.class,sessionInfo.getId());
		Map<String, Object> params= new HashMap<String, Object>();
		params.put("tuser", tuser);
		Tmarket tmarket=marketDao.get("from Tmarket where tuser=:tuser", params);
		Map<String, Object> params1= new HashMap<String, Object>();
		params1.put("tmarket", tmarket);
		params1.put("id", product.getId());
		Tproduct tproduct=productDao.get("from Tproduct where tmarket=:tmarket and id=:id", params1);
		if(tproduct!=null){
			tproduct.setName(product.getName());
			tproduct.setNumber(product.getNumber());
			tproduct.setPrice(product.getPrice());
			if(product.getPic()!=null)
				tproduct.setPic(product.getPic());
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Address findAddress(Unit unit) {
		Tunit tunit=unitDao.get(Tunit.class, unit.getId());
		Tschool tschool=tunit.getTschool();
		Address address=new Address();
		address.setSchoolName(tschool.getName());
		address.setUnitName(tunit.getName());
		return address;
	}

	@Override
	public List<Product>getInfo(List<Product> products) {
		for(Product product:products){
			Tproduct tproduct=productDao.get(Tproduct.class,product.getId());
			BeanUtils.copyProperties(tproduct, product);
		}
		return products;
	}

	@Override
	public double count(List<Product> products) {
		double amount=0;
		for(Product product:products){
			Tproduct tproduct=productDao.get(Tproduct.class,product.getId());
			amount+=tproduct.getPrice()*product.getAdded();
		}
		return amount;
	}

}
