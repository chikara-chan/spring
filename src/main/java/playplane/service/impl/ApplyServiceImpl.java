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

import playplane.dao.ApplyDaoI;
import playplane.dao.CustomerDaoI;
import playplane.dao.DeliveryDaoI;
import playplane.dao.MarketDaoI;
import playplane.dao.RoleDaoI;
import playplane.dao.UnitDaoI;
import playplane.dao.UserDaoI;
import playplane.entity.Tapply;
import playplane.entity.Tcustomer;
import playplane.entity.Tdelivery;
import playplane.entity.Tmarket;
import playplane.entity.Trole;
import playplane.entity.Tunit;
import playplane.entity.Tuser;
import playplane.model.Apply;
import playplane.model.SessionInfo;
import playplane.service.ApplyServiceI;

@Service
public class ApplyServiceImpl implements ApplyServiceI {

	@Autowired
	private UnitDaoI unitDao;
	
	@Autowired
	private UserDaoI userDao;
	
	@Autowired
	private RoleDaoI roleDao;
	
	@Autowired
	private CustomerDaoI customerDao;
	
	@Autowired
	private MarketDaoI marketDao;
	
	@Autowired
	private ApplyDaoI applyDao;
	
	@Autowired
	private DeliveryDaoI deliveryDao;
	
	@Override
	public Apply getByUnitId(String unitId) {
		Apply apply=new Apply();
		apply.setUnitId(unitId);
		Tunit tunit=unitDao.get(Tunit.class, unitId);
		apply.setAddress(tunit.getTschool().getTcity().getTprovince().getName()+tunit.getTschool().getTcity().getName()+tunit.getTschool().getName()+tunit.getName());
		return apply;
	}

	@Override
	public void submit(Apply apply) throws Exception{
		Tuser tuser=userDao.get(Tuser.class, apply.getUserId());
		Tunit tunit=unitDao.get(Tunit.class, apply.getUnitId());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tuser", tuser);
		if(applyDao.count("select count(*) from Tapply where tuser=:tuser",params)>0)
			throw new Exception("您已提交过,请耐心等待审核结果");
		Tapply tapply=new Tapply();
		BeanUtils.copyProperties(apply, tapply);
		tapply.setId(UUID.randomUUID().toString());
		tapply.setTuser(tuser);
		tapply.setTunit(tunit);
		tapply.setCreateTime(new Date());
		applyDao.save(tapply);
	}

	@Override
	public List<Apply> list(SessionInfo sessionInfo) throws Exception{
		if(sessionInfo.getType()!=0)
			throw new Exception("该用户无权限");
		List<Tapply> tapplies=applyDao.find("from Tapply order by createTime desc");
		List<Apply> applies=new ArrayList<Apply>();
		for (Tapply tapply:tapplies) {
			Apply apply=new Apply();
			BeanUtils.copyProperties(tapply, apply);
			Tunit tunit=tapply.getTunit();
			apply.setAddress(tunit.getTschool().getTcity().getTprovince().getName()+tunit.getTschool().getTcity().getName()+tunit.getTschool().getName()+tunit.getName());
			applies.add(apply);
		}
		return applies;
	}

	@Override
	public void agree(Apply apply) {
		Tapply tapply=applyDao.get(Tapply.class,apply.getId());
		tapply.setStatus("批准");
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("name", "超市");
		Trole trole=roleDao.get("from Trole where name=:name", params);
		Tuser tuser=tapply.getTuser();
		tuser.setTrole(trole);
		Tcustomer tcustomer=tuser.getTcustomer();
		Set<Tdelivery> tdeliveries=tcustomer.getTdeliveries();
		for(Tdelivery tdelivery:tdeliveries)
			deliveryDao.delete(tdelivery);
		customerDao.delete(tcustomer);
		Tmarket tmarket=new Tmarket();
		BeanUtils.copyProperties(tapply, tmarket);
		tmarket.setId(UUID.randomUUID().toString());
		marketDao.save(tmarket);
	}

	@Override
	public void reject(Apply apply) {
		Tapply tapply=applyDao.get(Tapply.class,apply.getId());
		tapply.setStatus("拒绝");
	}
}
