package com.lphr.vo;

import java.util.List;

import lombok.Data;

@Data
public class MenuVO implements Comparable<MenuVO>{

	private Integer id;
	private Integer parentId;
    private String menuName;
    private String iconCls;
    private Integer menuSort;
    private String menuPath;
	private List<MenuVO> children;
	private String checked; //是否选中，编辑角色时使用
	private String memo;
	private String scode;
    private Integer itype;//菜单类型：1:menu、2:btn

	@Override
	public int compareTo(MenuVO o) {
		return menuSort.compareTo(o.getMenuSort());//通过menuSort升序
	}
}
