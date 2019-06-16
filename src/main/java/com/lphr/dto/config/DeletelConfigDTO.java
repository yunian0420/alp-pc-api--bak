package com.lphr.dto.config;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月24日下午3:42:53
 * @TODO: 
 * @param: 
 * @return: 
 */
@Data
public class DeletelConfigDTO {

	@NotNull(message="id必填")
	private Integer id;
}
