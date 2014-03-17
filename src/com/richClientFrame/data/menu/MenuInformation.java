package com.richClientFrame.data.menu;

import java.util.List;
import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� MenuData
 * ������ �� �˵���Ϣ������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.03.19
 * @author king
 */
public class MenuInformation {
    
    /**
     * �����ļ�MAP
     */
    private List<MenuData> menuList;
    
    private Map<String, MenuData> menuMap;
    
    private Map<String, String> parentMap;

    /**
     * ���캯��.
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
