package com.lphr.dto.shop;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月29日下午4:32:01
 *
 */
@Data
public class AddShopDTO {
	
	private String shopName;
	private Integer provinceId;
	private String provinceName;
	private Integer cityId;
	private String cityName;
	private Integer areaId;
	private String areaName;
	private String address;
	private String contactPerson;
	private String contactPhone;
	
}
