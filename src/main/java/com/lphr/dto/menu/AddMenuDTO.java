package com.lphr.dto.menu;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月23日下午1:03:59
 * @TODO: 
 * @param: 
 * @return: 
 */
@Data
public class AddMenuDTO {

	@NotNull(message="parentId必填")
	public Integer parentId;
	//@NotBlank
	private String menuName;
	//@NotBlank
	private String menuPath;
	//@NotBlank
	private String scode;
	//@NotNull
	private Integer menuSort;
	private String iconCls;
	private String description;
}
