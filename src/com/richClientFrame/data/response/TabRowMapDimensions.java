package com.richClientFrame.data.response;

import java.util.ArrayList;
import java.util.List;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� TabRowMapDimensions
 * ������ �� ��ά������Ϣ������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
 * @author king
 */
public class TabRowMapDimensions {
    
    /**
     * ��ά������ϢLIST.
     */
    private List<TabRowMapList> dimensions = new ArrayList<TabRowMapList>();
    
    /**
     * ��ά������Ϣ��ȡ��.
     * 
     * @param index ���
     * @return ��ά������Ϣ
     */
    public TabRowMapList getDimension(int index) {
        return dimensions.get(index);
    }
    
    /**
     * ��ά������Ϣ�����.
     * 
     * @param tabRowMapList ��ά������Ϣ
     */
    public void addDimension(TabRowMapList tabRowMapList) {
        dimensions.add(tabRowMapList);
    }
    
    public void addDimension(TabRowMapList tabRowMapList, int index) {
        dimensions.add(index, tabRowMapList);
    }
    
    public int size() {
        return dimensions.size();
    }
}
