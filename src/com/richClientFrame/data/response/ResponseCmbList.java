/**
 * 
 */
package com.richClientFrame.data.response;

import java.util.ArrayList;
import java.util.List;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ResponseCmbList
 * ������ �� �����б��������ݹ�����.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2009.07.28
 * @author king
 */
public class ResponseCmbList {
    
    /**
     * ��Ŀ�ؼ�LIST.
     */
    private List<ResponseTab[]> list = new ArrayList<ResponseTab[]>();
    
    /**
     * ���캯��.
     * 
     * @param list ��Ŀ�ؼ�LIST
     */
    public ResponseCmbList(List<ResponseTab[]> list) {
        this.list = list;
    }
    
    /**
     * ��Ŀ�ؼ�LIST��ȡ��.
     * 
     * @return ��Ŀ�ؼ�LIST
     */
    public List<ResponseTab[]> getList() {
        return list;
    }
    
    /**
     * ��Ŀ�ؼ�LIST���趨.
     * 
     * @param list ��Ŀ�ؼ�LIST
     */
    public void setList(List<ResponseTab[]> list) {
        this.list = list;
    }
    
    /**
     * �пؼ���ȡ��.
     * 
     * @param index ���
     * @return �пؼ�
     */
    public ResponseTab[] getLine(int index) {
        return (ResponseTab[])list.get(index);
    }
    
    /**
     * �пؼ������.
     * 
     * @param resCmbs �ؼ�����
     */
    public void addLine(ResponseTab[] resCmbs) {
        list.add(resCmbs);
    }
    
    /**
     * �пؼ���������ȡ��.
     * 
     * @return �пؼ�������
     */
    public int size() {
        return list.size();
    }
}
