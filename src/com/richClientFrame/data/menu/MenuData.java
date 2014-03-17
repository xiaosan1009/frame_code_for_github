/**
 * 
 */
package com.richClientFrame.data.menu;

import java.util.List;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� MenuData
 * ������ �� �˵���Ϣ������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.11.04
 * @author king
 */
public class MenuData {

    /**
     * menu������
     */
    private String name;
    
    /**
     * ����ID
     */
    private String parentId;

    /**
     * һ���˵�����List
     */
    private List<SubMenuData> sublist;

    /**
     * ���캯��.
     * 
     */
    public MenuData() {
    }

    /**
     * �˵�����ȡ��.
     * 
     * @return �˵�����
     */
    public String getName() {
        return name;
    }
    
    /**
     * �˵������趨.
     * 
     * @param name �˵�����
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * һ���˵�����Listȡ��.
     * 
     * @return һ���˵�����List
     */
    public List<SubMenuData> getSublist() {
        return sublist;
    }

    /**
     * һ���˵�����List�趨.
     * 
     * @param sublist һ���˵�����List
     */
    public void setSublist(List<SubMenuData> sublist) {
        this.sublist = sublist;
    }

    /**
     * ����IDȡ��.
     * 
     * @return ����ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * ����ID�趨.
     * 
     * @param parentId ����ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
