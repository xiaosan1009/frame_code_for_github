package com.richClientFrame.data.response;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.ConstantsUtil;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ResponseData
 * ������ �� ������Ŀ���ݹ�����.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.24
 * @author king
 */
public class ResponseData extends AbstractResponseData {
    
    /**
     * ������Ŀ��������.
     */
    private ResponseTab[] resTab;
    
    /**
     * ���캯��.
     * 
     * @throws RichClientWebException RichClientWebException
     */
    public ResponseData() throws RichClientWebException {
        super(new ResponseHeader());
    }
    
    /**
     * ���캯��.
     * 
     * @param header ͷ�ļ�����
     */
    public ResponseData(ResponseHeader header) {
        super(header);
    }
    
    /**
     * ���캯��.
     * 
     * @param header ͷ�ļ�����
     * @param dateType �ؼ�����
     */
    public ResponseData(ResponseHeader header, int dateType) {
        super(header, dateType);
    }

    /**
     * ��û�����Ŀ����.
     * @return resTab ������Ŀ����
     */
    public ResponseTab[] getResTab() {
        return resTab;
    }

    /**
     * ���û�����Ŀ����.
     * @param resTab ������Ŀ����
     */
    public void setResTab(ResponseTab[] resTab) {
        this.resTab = resTab;
    }
    
}
