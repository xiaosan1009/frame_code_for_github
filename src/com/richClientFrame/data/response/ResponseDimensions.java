package com.richClientFrame.data.response;

import java.util.ArrayList;
import java.util.List;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ResponseDimensions
 * ������ �� ��ά������Ϣ������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
 * @author king
 */
public class ResponseDimensions {
    
    /**
     * ��ά������ϢLIST.
     */
    private List<ResponseList> dimensions = new ArrayList<ResponseList>();
    
    /**
     * ��ά������Ϣ��ȡ��.
     * 
     * @param index ���
     * @return ��ά������Ϣ
     */
    public ResponseList getDimension(int index) {
        return dimensions.get(index);
    }
    
    /**
     * ��ά������Ϣ�����.
     * 
     * @param responseList ��ά������Ϣ
     */
    public void addDimension(ResponseList responseList) {
        dimensions.add(responseList);
    }
    
    public void addDimension(ResponseList responseList, int index) {
        dimensions.add(index, responseList);
    }
    
    public int size() {
        return dimensions.size();
    }
}
