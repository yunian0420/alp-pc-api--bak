package com.lphr.util.china;

import java.util.List;

/**
 * 地级市
 * @author jx on 2018/4/12.
 */

public class City {
    private String code;
    private Integer parentId;
    private String sname;
    private Integer itype = 2;
    private List<NewArea> areaList;
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
	 * @return the areaList
	 */
	public List<NewArea> getAreaList() {
		return areaList;
	}
	/**
	 * @param areaList the areaList to set
	 */
	public void setAreaList(List<NewArea> areaList) {
		this.areaList = areaList;
	}

}
