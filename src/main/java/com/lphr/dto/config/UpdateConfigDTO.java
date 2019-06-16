package com.lphr.dto.config;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月24日下午3:28:30
 * @TODO: 
 * @param: 
 * @return: 
 */
@Data
public class UpdateConfigDTO {

	@NotNull(message="id不能为空")
	private Integer id;
	@NotNull(message="parentId必填")
	private Integer parentId;
	@NotNull(message="参数名称必填")
    private String sname;
	@NotNull(message="参数Key必填")
    private String sysKey;
	@NotNull(message="参数值必填")
    private String sysValue;
	
	private String sysDes;
}
