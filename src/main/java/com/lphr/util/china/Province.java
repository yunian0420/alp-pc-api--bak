package com.lphr.util.china;

import java.util.List;

/**
 * 省份
 * @author jx on 2018/4/12.
 */

public class Province {
    private String code;
    
    private Integer parentId = 0;
    private String sname;
    private Integer itype = 1;
    private List<City> cityList;
    
    /**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the sname
	 */
	public String getSname() {
		return sname;
	}

	/**
	 * @param sname the sname to set
	 */
	public void setSname(String sname) {
		this.sname = sname;
	}

	/**
	 * @return the itype
	 */
	public Integer getItype() {
		return itype;
	}

	/**
	 * @param itype the itype to set
	 */
	public void setItype(Integer itype) {
		this.itype = itype;
	}

	/**
	 * @return the cityList
	 */
	public List<City> getCityList() {
		return cityList;
	}

	/**
	 * @param cityList the cityList to set
	 */
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	

    
}
