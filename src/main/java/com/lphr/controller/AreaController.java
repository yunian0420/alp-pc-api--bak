package com.lphr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lphr.annotations.ApiVersion;
import com.lphr.dto.area.ListAreaDTO;
import com.lphr.dto.area.ListSimpleAreaDTO;
import com.lphr.entity.Area;
import com.lphr.service.AreaService;
import com.lphr.vo.CommonVO;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.SimpleAreaVO;

/**
 * 地区管理controller
 * @author: YN
 * @Date: 2019年4月27日下午2:31:10
 *
 */
@RestController
@RequestMapping("{version}/area")
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	
	/**
	 * 分页查询地区
	 *  
	 */
	@ApiVersion(1)
	@GetMapping("list")
	public CommonVO<DataTableVO<Area>> list(ListAreaDTO dto) {
		return new CommonVO<DataTableVO<Area>>(areaService.findByPage(dto));
	}
	
	
	/**
	  *  根据父ID查看子地区
	 * @author: YN
	 * @Date: 2019年4月27日下午2:36:52
	 */ 
	@RequestMapping(value="listByParentId",method=RequestMethod.GET)
	public CommonVO<DataTableVO<SimpleAreaVO>> listByParentId(ListSimpleAreaDTO dto) {
		return new CommonVO<DataTableVO<SimpleAreaVO>>(areaService.listByParentId(dto));
	}
	
	/**
	  *  根据父ID查看子地区
	 * @author: YN
	 * @Date: 2019年4月27日下午2:36:52
	 */ 
	@RequestMapping(value="getAreaByCid",method=RequestMethod.GET)
	public CommonVO<DataTableVO<SimpleAreaVO>> getAreaByCid(ListSimpleAreaDTO dto) {
		return new CommonVO<DataTableVO<SimpleAreaVO>>(areaService.getAreaByCid(dto));
	}
	
	public CommonVO<Object> addData() {
		return areaService.addData();
	}
	
	
	/**
	 * 根据parentId查询地区
	 */
//	@RequestMapping(value="detail",method=RequestMethod.GET)
//	public CommonVO<List<SimpleAreaVO>> detail(ListSimpleAreaDTO dto) {
//		GlobalLog.MY_LOGGER.info("根据parentId查询所有地区");
//		return new CommonVO<List<SimpleAreaVO>>(areaService.listByParentId(dto.getParentId()));
//	}
}
