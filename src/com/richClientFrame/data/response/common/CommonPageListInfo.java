/**
 * 
 */
package com.richClientFrame.data.response.common;

import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseHeader;
import com.richClientFrame.data.response.ResponseList;
import com.richClientFrame.exception.RichClientWebException;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� CommonPageListInfo
 * ������ �� ��ҳ��������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.11.03
 * @author king
 */
public class CommonPageListInfo extends AbstractResponseData {
    
    public static final int LIST_SIZE = 1;
    
    private ResponseList list1;
    
    /**
     * ���캯��.
     * @throws RichClientWebException RichClientWebException
     */
    public CommonPageListInfo() throws RichClientWebException {
    }

    /**
     * ���캯��.
     * 
     * @param header ͷ�ļ�
     * @throws RichClientWebException RichClientWebException
     */
    public CommonPageListInfo(ResponseHeader header) throws RichClientWebException {
        setResHeader(header);
    }

    /**
     * ����ȡ��.
     * 
     * @return ����
     */
    public ResponseList getDataList() {
        return list1;
    }

    /**
     * ��������.
     * 
     * @param list1 ����
     */
    public void setDataList(ResponseList list1) {
        this.list1 = list1;
    }    
}
