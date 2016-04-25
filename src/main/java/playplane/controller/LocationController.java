package playplane.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import playplane.model.Unit;
import playplane.service.LocationServiceI;

/**
 * 用户Controller
 * 
 * @author chikara
 */
@Controller
@RequestMapping("/location")
public class LocationController extends BaseController {

	@Autowired
	private LocationServiceI locationService;

	// 获取省份
	@ResponseBody
	@RequestMapping("/province")
	public List<String> province() {
		return locationService.province();
	}
	
	// 获取省份
	@ResponseBody
	@RequestMapping("/city")
	public List<String> city(String province) {
		return locationService.city(province);
	}
	
	// 获取学校
	@ResponseBody
	@RequestMapping("/school")
	public List<String> school(String city) {
		return locationService.school(city);
	}
	
	// 获取单元
	@ResponseBody
	@RequestMapping("/unit")
	public List<Unit> unit(String school) {
		return locationService.unit(school);
	}


}
