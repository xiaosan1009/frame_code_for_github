/**
 * 
 */
package com.richClientFrame.data.menu;

import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： MenuData
 * 类描述 ： 菜单信息管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.11.04
 * @author king
 */
public class MenuData {

    /**
     * menu的名称
     */
    private String name;
    
    /**
     * 机能ID
     */
    private String parentId;

    /**
     * 一级菜单内容List
     */
    private List<SubMenuData> sublist;

    /**
     * 构造函数.
     * 
     */
    public MenuData() {
    }

    /**
     * 菜单名字取得.
     * 
     * @return 菜单名字
     */
    public String getName() {
        return name;
    }
    
    /**
     * 菜单名字设定.
     * 
     * @param name 菜单名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 一级菜单内容List取得.
     * 
     * @return 一级菜单内容List
     */
    public List<SubMenuData> getSublist() {
        return sublist;
    }

    /**
     * 一级菜单内容List设定.
     * 
     * @param sublist 一级菜单内容List
     */
    public void setSublist(List<SubMenuData> sublist) {
        this.sublist = sublist;
    }

    /**
     * 机能ID取得.
     * 
     * @return 机能ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 机能ID设定.
     * 
     * @param parentId 机能ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
