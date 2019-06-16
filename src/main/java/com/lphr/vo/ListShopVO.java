package com.lphr.vo;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月29日上午10:56:01
 *
 */
@Data
public class ListShopVO {
	
	private Integer id;
	
	private String shopName;
	
	private String contactPerson;
	
	private String contactPhone;
	
	private Integer provinceId;
	private String provinceName;
	
	private Integer cityId;
	private String cityName;
	
	private Integer areaId;
	private String areaName;
	
	private String address;
	private Integer state;
	
}
