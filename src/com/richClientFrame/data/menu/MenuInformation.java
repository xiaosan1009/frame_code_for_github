package com.richClientFrame.data.menu;

import java.util.List;
import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： MenuData
 * 类描述 ： 菜单信息保持类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.03.19
 * @author king
 */
public class MenuInformation {
    
    /**
     * 配置文件MAP
     */
    private List<MenuData> menuList;
    
    private Map<String, MenuData> menuMap;
    
    private Map<String, String> parentMap;

    /**
     * 构造函数.
     */
    public MenuInformation() {
        super();
    }

    public List<MenuData> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuData> menuList) {
        this.menuList = menuList;
    }

    public Map<String, MenuData> getMenuMap() {
        return menuMap;
    }

    public void setMenuMap(Map<String, MenuData> menuMap) {
        this.menuMap = menuMap;
    }

    public Map<String, String> getParentMap() {
        return parentMap;
    }

    public void setParentMap(Map<String, String> parentMap) {
        this.parentMap = parentMap;
    }
}
