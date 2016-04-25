package playplane.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import playplane.dao.CityDaoI;
import playplane.dao.ProvinceDaoI;
import playplane.dao.SchoolDaoI;
import playplane.dao.UnitDaoI;
import playplane.entity.Tcity;
import playplane.entity.Tprovince;
import playplane.entity.Tschool;
import playplane.entity.Tunit;
import playplane.model.Unit;
import playplane.service.LocationServiceI;

@Service
public class LocationServiceImpl implements LocationServiceI {

	@Autowired
	private ProvinceDaoI provinceDao;

	@Autowired
	private CityDaoI cityDao;

	@Autowired
	private SchoolDaoI schoolDao;

	@Autowired
	private UnitDaoI unitDao;

	@Override
	public List<String> province() {
		List<Tprovince> tprovinces = provinceDao.find("from Tprovince");
		List<String> names = new ArrayList<String>();
		for (Tprovince tprovince : tprovinces) {
			names.add(tprovince.getName());
		}
		return names;
	}

	@Override
	public List<String> city(String province) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", province);
		Tprovince tprovince=provinceDao.get("from Tprovince where name=:name",params);
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("tprovince", tprovince);
		List<Tcity> tcities = cityDao.find("from Tcity where tprovince=:tprovince",params1);
		List<String> names = new ArrayList<String>();
		for (Tcity tcity:tcities) {
			names.add(tcity.getName());
		}
		return names;
	}

	@Override
	public List<String> school(String city) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", city);
		Tcity tcity=cityDao.get("from Tcity where name=:name",params);
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("tcity", tcity);
		List<Tschool> tschools = schoolDao.find("from Tschool where tcity=:tcity",params1);
		List<String> names = new ArrayList<String>();
		for (Tschool tschool:tschools) {
			names.add(tschool.getName());
		}
		return names;
	}

	@Override
	public List<Unit> unit(String school) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", school);
		Tschool tschool=schoolDao.get("from Tschool where name=:name",params);
		Map<String, Object> params1 = new HashMap<String, Object>();
		params1.put("tschool", tschool);
		List<Tunit> tunits = unitDao.find("from Tunit where tschool=:tschool order by name",params1);
		List<Unit> units = new ArrayList<Unit>();
		for (Tunit tunit:tunits) {
			Unit unit=new Unit();
			BeanUtils.copyProperties(tunit, unit);
			if(tunit.getTmarket()!=null)
				unit.setAvailable(true);
			units.add(unit);
		}
		return units;

	}
}
