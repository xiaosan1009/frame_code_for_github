/**
 * 
 */
package com.richClientFrame.data.menu;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� SubMenuData
 * ������ �� �����˵���Ϣ������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.11.04
 * @author king
 */
public class SubMenuData {
    
    /**
     * �����˵�����
     */
    private String subname;
    
    /**
     * �����˵�����ID
     */
    private String dispcode;
    
    /**
     * ����ID
     */
    private String functionId;

    /**
     * ���캯��.
     */
    public SubMenuData() {
        super();
    }

    /**
     * �Ӳ˵�����ȡ��.
     * 
     * @return �Ӳ˵�����ȡ��
     */
    public String getSubname() {
        return subname;
    }

    /**
     * �Ӳ˵������趨.
     * 
     * @param subname �Ӳ˵�����
     */
    public void setSubname(String subname) {
        this.subname = subname;
    }

    /**
     * �����˵�������������ȡ��.
     * 
     * @return �����˵�����������
     */
    public int getNameLength() {

        return getSubname().length();
    }

    /**
     * �����˵�����IDȡ��.
     * 
     * @return dispcode �����˵�����ID
     * 
     */
    public String getDispcode() {
        return dispcode;
    }

    /**
     * �����˵�����ID�趨.
     * 
     * @param dispcode �����˵�����ID
     */
    public void setDispcode(String dispcode) {
        this.dispcode = dispcode;
    }

    /**
     * ����IDȡ��.
     * 
     * @return dispcode ����ID
     */
    public String getFunctionId() {
        return functionId;
    }

    /**
     * ����ID�趨.
     * 
     * @param functionId ����ID
     */
    public void setFunctionId(String functionId) {
        this.functionId = functionId;
    }

}
