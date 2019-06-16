package com.lphr.dto.area;


import com.lphr.dto.PageInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: YN
 * @Date: 2019年4月27日下午2:34:00
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class ListAreaDTO extends PageInfo {

	private Integer parentId = 0;
}
