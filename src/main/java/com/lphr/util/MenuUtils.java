package com.lphr.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.lphr.vo.MenuVO;

/**
 * 处理menu返回树形结构数据
 * 
 * @author YN
 *
 */
public class MenuUtils {

	public static List<MenuVO> formatMenu(List<MenuVO> sysMenuList) {
        List<MenuVO> treeList = new ArrayList<MenuVO>();
        List<Integer> idList = new ArrayList<Integer>();
        if (!CollectionUtils.isEmpty(sysMenuList)) {
            for (int i = 0; i < sysMenuList.size(); i++) {
            	MenuVO sysMenuVO = sysMenuList.get(i);
                idList.add(sysMenuVO.getId());
            }
            for (int j = 0; j < sysMenuList.size(); j++) {
            	MenuVO sysMenuVO = sysMenuList.get(j);
                if (!idList.contains(sysMenuVO.getParentId())) {
                    sysMenuVO.setChildren(getRecursionChildList(sysMenuList, sysMenuVO.getId()));
                    treeList.add(sysMenuVO);
                }
            }
        }
        Collections.sort(treeList);
        return treeList;
    }

    private static List<MenuVO> getRecursionChildList(List<MenuVO> dataList, long parentId) {
        List<MenuVO> childList = new ArrayList<MenuVO>();
        if (!CollectionUtils.isEmpty(dataList)) {
            for (int i = 0; i < dataList.size(); i++) {
            	MenuVO sysMenuVO = dataList.get(i);
                if (String.valueOf(parentId).equals(String.valueOf(sysMenuVO.getParentId()))) {
                    sysMenuVO.setChildren(getRecursionChildList(dataList, sysMenuVO.getId()));
                    childList.add(sysMenuVO);
                }
            }
        }
        Collections.sort(childList);
        return childList;
    }
}
