package com.richClientFrame.data.response;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.ConstantsUtil;

import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� AbstractResponseData
 * ������ �� ��Ӧ���ݹ�����.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
 * @author king
 */
public class AbstractResponseData {
    
    /**
     * ��Ӧ���ݴ洢KEY.
     */
    public static final String KEY_RESPONSE_DATA = "resData";
    
    public static final String KEY_RESPONSE_LIST = "resList";
    
    private ResponseList mDataList;
    
    private ResponseDimensions mDimensionList;
    
    private String mClientType;
    
    private int mDataType;
    
    private String mTargetId;
    
    private boolean mIsUpload;
    
    private ResponseExcel resExcel;
    
    private String outPut;
    
    private ResponseBean responseBean;
    
    /**
     * ͷ�ļ�����
     */
    private ResponseHeader resHeader;
    
    /**
     * ���󷵻���������
     */
    private Map<String, AbstractResponseData> resMap;
    
    /**
     * ���캯��.
     * @throws RichClientWebException RichClientWebException
     */
    public AbstractResponseData() throws RichClientWebException {
        super();
        this.resHeader = new ResponseHeader();
    }
    
    /**
     * ���캯��.
     * 
     * @param header ͷ�ļ�����
     */
    public AbstractResponseData(ResponseHeader header) {
        super();
        this.resHeader = header;
    }
    
    /**
     * ���캯��.
     * 
     * @param header ͷ�ļ�����
     * @param dataType �ؼ�����
     */
    public AbstractResponseData(ResponseHeader header, int dataType) {
        super();
        this.resHeader = header;
        this.mDataType = dataType;
    }
    
    /**
     * ���캯��.
     * 
     * @param header ͷ�ļ�����
     * @param targetId �ؼ�ID
     */
    public AbstractResponseData(ResponseHeader header, String targetId) {
        super();
        this.resHeader = header;
        this.mDataType = ConstantsUtil.Widget.DATAGRID;
        this.mTargetId = targetId;
    }
    
    /**
     * ���캯��.
     * 
     * @param header ͷ�ļ�����
     * @param dataType �ؼ�����
     * @param targetId �ؼ�ID
     */
    public AbstractResponseData(ResponseHeader header, int dataType, String targetId) {
        super();
        this.resHeader = header;
        this.mDataType = dataType;
        this.mTargetId = targetId;
    }

    /**
     * ȡ��ͷ�ļ�����.
     * 
     * @return resHeader ͷ�ļ�����
     */
    public ResponseHeader getResHeader() {
        return resHeader;
    }

    /**
     * ����ͷ�ļ�����.
     * 
     * @param resHeader ͷ�ļ�����
     */
    public void setResHeader(ResponseHeader resHeader) {
        this.resHeader = resHeader;
    }

    /**
     * ���󷵻�����������ȡ��.
     * 
     * @return ���󷵻���������
     */
    public Map<String, AbstractResponseData> getResMap() {
        return resMap;
    }

    /**
     * ���󷵻������������趨.
     * 
     * @param resMap ���󷵻���������
     */
    public void setResMap(Map<String, AbstractResponseData> resMap) {
        this.resMap = resMap;
    }
    
    /**
     * ���󷵻����ݵ�ȡ��.
     * 
     * @return ���󷵻�����
     */
    public ResponseList getDataList() {
        return mDataList;
    }

    /**
     * ���󷵻����ݵ��趨.
     * 
     * @param list ���󷵻�����
     */
    public void setDataList(ResponseList list) {
        this.mDataList = list;
    }
    
    /**
     * ���󷵻����ݵ�ȡ��.
     * 
     * @param resData ���󷵻�����
     * @param key KEY
     * @return ���󷵻�����
     */
    public static AbstractResponseData getAbstractResponseData(
            AbstractResponseData resData, String key) {
        AbstractResponseData resList1 = null;
        if (resData.getResMap() != null && key != null) {
            final Map<String, AbstractResponseData> resMap = resData.getResMap();
            if (resMap != null) {
                if (resMap.get(key) != null) {
                    resList1 = resMap.get(key);
                }
            }
        } else {
            resList1 = resData;
        }
        return resList1;
    }

    /**
     * �ͻ������͵�ȡ��.
     * 
     * @return �ͻ�������
     */
    public String getClientType() {
        return mClientType;
    }

    /**
     * �ͻ������͵��趨.
     * 
     * @param clientType �ͻ�������
     */
    public void setClientType(String clientType) {
        this.mClientType = clientType;
    }

    /**
     * �����������͵�ȡ��.
     * 
     * @return ������������
     */
    public int getDataType() {
        return mDataType;
    }

    /**
     * �����������͵��趨.
     * 
     * @param dataType ������������
     */
    public void setDataType(int dataType) {
        this.mDataType = dataType;
    }

    /**
     * ����ؼ�ID��ȡ��.
     * 
     * @return ����ؼ�ID
     */
    public String getTargetId() {
        return mTargetId;
    }

    /**
     * ����ؼ�ID���趨.
     * 
     * @param targetId ����ؼ�ID
     */
    public void setTargetId(String targetId) {
        this.mTargetId = targetId;
    }

    /**
     * ��ά�������ݵ�ȡ��.
     * 
     * @return ��ά��������
     */
    public ResponseDimensions getDimensionList() {
        return mDimensionList;
    }

    /**
     * ��ά�������ݵ��趨.
     * 
     * @param dimensionList ��ά��������
     */
    public void setDimensionList(ResponseDimensions dimensionList) {
        mDimensionList = dimensionList;
    }

    /**
     * @Description: �ж��Ƿ�Ϊ�ϴ�����
     * @author king
     * @since Apr 20, 2013 11:09:28 AM 
     * @version V1.0
     * @return �Ƿ�Ϊ�ϴ�����
     */
    public boolean isUpload() {
        return mIsUpload;
    }

    /**
     * @Description: �����Ƿ�Ϊ�ϴ�����
     * @author king
     * @since Apr 20, 2013 11:09:54 AM 
     * @version V1.0
     * @param upload �Ƿ�Ϊ�ϴ�����
     */
    public void setUpload(boolean upload) {
        this.mIsUpload = upload;
    }

    /**
     * @Description: ȡ��excel������Ϣ
     * @author king
     * @since Apr 20, 2013 11:10:05 AM 
     * @version V1.0
     * @return excel������Ϣ
     */
    public ResponseExcel getResExcel() {
        return resExcel;
    }

    /**
     * @Description: ����excel������Ϣ
     * @author king
     * @since Apr 20, 2013 11:10:22 AM 
     * @version V1.0
     * @param resExcel excel������Ϣ
     */
    public void setResExcel(ResponseExcel resExcel) {
        this.resExcel = resExcel;
    }

    /**
     * @Description: ȡ�÷�����������Ϣ��һ�����ڽӿڵ��ã�
     * @author king
     * @since Apr 20, 2013 11:10:31 AM 
     * @version V1.0
     * @return ������������Ϣ
     */
    public String getOutPut() {
        return outPut;
    }

    /**
     * @Description: ���÷�����������Ϣ
     * @author king
     * @since Apr 20, 2013 11:11:12 AM 
     * @version V1.0
     * @param outPut ������������Ϣ
     */
    public void setOutPut(String outPut) {
        this.outPut = outPut;
    }

    public ResponseBean getResponseBean() {
        return responseBean;
    }

    public void setResponseBean(ResponseBean responseBean) {
        this.responseBean = responseBean;
    }

}
