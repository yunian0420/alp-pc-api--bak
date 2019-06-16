package com.lphr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lphr.dto.area.ListAreaDTO;
import com.lphr.dto.area.ListSimpleAreaDTO;
import com.lphr.entity.Area;
import com.lphr.enums.StateEnum;
import com.lphr.mapper.AreaMapper;
import com.lphr.util.BeanMapper;
import com.lphr.util.china.City;
import com.lphr.util.china.NewArea;
import com.lphr.util.china.NewMainGetData;
import com.lphr.util.china.Province;
import com.lphr.vo.CommonVO;
import com.lphr.vo.DataTableVO;
import com.lphr.vo.SimpleAreaVO;

/**
 * @author: YN
 * @Date: 2019年4月27日下午2:33:11
 */
@Service
public class AreaService {

	@Autowired
	private AreaMapper areaMapper;
	/**
	  *  查询地区列表
	 * @author: YN
	 * @Date: 2019年4月27日下午2:35:00
	 *
	 */ 
	public DataTableVO<Area> findByPage(ListAreaDTO dto) {
		Area area = new Area();
		area.setParentId(dto.getParentId());
		PageHelper.startPage(dto.getPage(), dto.getSize());
		List<Area> entitys = areaMapper.select(area);
		PageInfo<Area> page = new PageInfo<Area>(entitys);
		int pageSize  = page.getPageSize();
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		DataTableVO<Area> dataTable = new DataTableVO<>(pageSize, allCount, allPage, currentPage, entitys);
		return dataTable;
	}

	/**
	 *  根据父ID查看子地区
	 * @author: YN
	 * @Date: 2019年4月27日下午2:36:52
	 */ 
	public DataTableVO<SimpleAreaVO> listByParentId(ListSimpleAreaDTO dto) {
		Area query = new Area();
		query.setParentId(dto.getParentId());
		query.setState(StateEnum.VALID.getCode());
		PageHelper.startPage(dto.getPage(), dto.getSize());
		List<Area> entitys = areaMapper.select(query);
		PageInfo<Area> page = new PageInfo<Area>(entitys);
		int pageSize  = page.getPageSize();
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		List<SimpleAreaVO> VO = BeanMapper.mapList(entitys, SimpleAreaVO.class);
		DataTableVO<SimpleAreaVO> dataTable = new DataTableVO<>(pageSize, allCount, allPage, currentPage, VO);
		return dataTable;
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月27日下午4:16:06
	 *
	 */ 
	public DataTableVO<SimpleAreaVO> getAreaByCid(ListSimpleAreaDTO dto) {
		Area query = new Area();
		query.setParentId(dto.getParentId());
		query.setState(StateEnum.VALID.getCode());
		PageHelper.startPage(dto.getPage(), dto.getSize());
		List<Area> entitys = areaMapper.select(query);
		PageInfo<Area> page = new PageInfo<Area>(entitys);
		int pageSize  = page.getPageSize();
		long allCount = page.getTotal();
		int allPage = page.getPages();
		int currentPage = page.getPageNum();
		List<SimpleAreaVO> VO = BeanMapper.mapList(entitys, SimpleAreaVO.class);
		DataTableVO<SimpleAreaVO> dataTable = new DataTableVO<>(pageSize, allCount, allPage, currentPage, VO);
		return dataTable;
	}

	/**
	 * @author: YN
	 * @Date: 2019年4月30日下午3:41:23
	 *
	 */ 
	public CommonVO<Object> addData() {
		CommonVO<Object> commonVO = new CommonVO<Object>();
		List<Province> provinces = NewMainGetData.processData();
		for(Province province : provinces) {
			Area pArea = new Area();
			pArea.setParentId(0);
			pArea.setSname(province.getSname());
			pArea.setItype((byte) 1);
			areaMapper.insertSelective(pArea);
			List<City> citys = province.getCityList();
			for(City city : citys) {
				Area cArea = new Area();
				cArea.setParentId(pArea.getId());
				cArea.setSname(city.getSname());
				cArea.setItype((byte) 2);
				areaMapper.insertSelective(cArea);
				List<NewArea> areas = city.getAreaList();
				for(NewArea area : areas) {
					Area aArea = new Area();
					aArea.setParentId(cArea.getId());
					aArea.setSname(area.getSname());
					aArea.setItype((byte) 3);
					areaMapper.insertSelective(aArea);
				}
			}
		}
		commonVO.setMessage("操作成功");
		return commonVO;
	}

}
