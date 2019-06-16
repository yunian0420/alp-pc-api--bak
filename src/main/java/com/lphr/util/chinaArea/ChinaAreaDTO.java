package com.lphr.util.chinaArea;

import java.util.List;

import lombok.Data;

/**
 * @author: YN
 * @Date: 2019年4月30日下午6:10:39
 *
 */
@Data
public class ChinaAreaDTO {

    private Integer parentId;
    private String sname;
    private Integer itype;
    private List<CityDTO> cityList;
}
