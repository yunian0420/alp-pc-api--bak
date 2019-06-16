package com.lphr.util.chinaArea;

import java.util.List;

import com.lphr.entity.Area;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月30日下午6:13:08
 *
 */
@Data
public class CityDTO {

	private Integer parentId;
    private String sname;
    private Integer itype;
    private List<Area> areaList;
}
