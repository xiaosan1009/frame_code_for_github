package com.richClientFrame.data.response;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.ConstantsUtil;

import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： AbstractResponseData
 * 类描述 ： 响应数据管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public class AbstractResponseData {
    
    /**
     * 响应数据存储KEY.
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
     * 头文件对象
     */
    private ResponseHeader resHeader;
    
    /**
     * 请求返回数据容器
     */
    private Map<String, AbstractResponseData> resMap;
    
    /**
     * 构造函数.
     * @throws RichClientWebException RichClientWebException
     */
    public AbstractResponseData() throws RichClientWebException {
        super();
        this.resHeader = new ResponseHeader();
    }
    
    /**
     * 构造函数.
     * 
     * @param header 头文件对象
     */
    public AbstractResponseData(ResponseHeader header) {
        super();
        this.resHeader = header;
    }
    
    /**
     * 构造函数.
     * 
     * @param header 头文件对象
     * @param dataType 控件类型
     */
    public AbstractResponseData(ResponseHeader header, int dataType) {
        super();
        this.resHeader = header;
        this.mDataType = dataType;
    }
    
    /**
     * 构造函数.
     * 
     * @param header 头文件对象
     * @param targetId 控件ID
     */
    public AbstractResponseData(ResponseHeader header, String targetId) {
        super();
        this.resHeader = header;
        this.mDataType = ConstantsUtil.Widget.DATAGRID;
        this.mTargetId = targetId;
    }
    
    /**
     * 构造函数.
     * 
     * @param header 头文件对象
     * @param dataType 控件类型
     * @param targetId 控件ID
     */
    public AbstractResponseData(ResponseHeader header, int dataType, String targetId) {
        super();
        this.resHeader = header;
        this.mDataType = dataType;
        this.mTargetId = targetId;
    }

    /**
     * 取得头文件对象.
     * 
     * @return resHeader 头文件对象
     */
    public ResponseHeader getResHeader() {
        return resHeader;
    }

    /**
     * 设置头文件对象.
     * 
     * @param resHeader 头文件对象
     */
    public void setResHeader(ResponseHeader resHeader) {
        this.resHeader = resHeader;
    }

    /**
     * 请求返回数据容器的取得.
     * 
     * @return 请求返回数据容器
     */
    public Map<String, AbstractResponseData> getResMap() {
        return resMap;
    }

    /**
     * 请求返回数据容器的设定.
     * 
     * @param resMap 请求返回数据容器
     */
    public void setResMap(Map<String, AbstractResponseData> resMap) {
        this.resMap = resMap;
    }
    
    /**
     * 请求返回数据的取得.
     * 
     * @return 请求返回数据
     */
    public ResponseList getDataList() {
        return mDataList;
    }

    /**
     * 请求返回数据的设定.
     * 
     * @param list 请求返回数据
     */
    public void setDataList(ResponseList list) {
        this.mDataList = list;
    }
    
    /**
     * 请求返回数据的取得.
     * 
     * @param resData 请求返回数据
     * @param key KEY
     * @return 请求返回数据
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
     * 客户端类型的取得.
     * 
     * @return 客户端类型
     */
    public String getClientType() {
        return mClientType;
    }

    /**
     * 客户端类型的设定.
     * 
     * @param clientType 客户端类型
     */
    public void setClientType(String clientType) {
        this.mClientType = clientType;
    }

    /**
     * 请求数据类型的取得.
     * 
     * @return 请求数据类型
     */
    public int getDataType() {
        return mDataType;
    }

    /**
     * 请求数据类型的设定.
     * 
     * @param dataType 请求数据类型
     */
    public void setDataType(int dataType) {
        this.mDataType = dataType;
    }

    /**
     * 请求控件ID的取得.
     * 
     * @return 请求控件ID
     */
    public String getTargetId() {
        return mTargetId;
    }

    /**
     * 请求控件ID的设定.
     * 
     * @param targetId 请求控件ID
     */
    public void setTargetId(String targetId) {
        this.mTargetId = targetId;
    }

    /**
     * 二维请求数据的取得.
     * 
     * @return 二维请求数据
     */
    public ResponseDimensions getDimensionList() {
        return mDimensionList;
    }

    /**
     * 二维请求数据的设定.
     * 
     * @param dimensionList 二维请求数据
     */
    public void setDimensionList(ResponseDimensions dimensionList) {
        mDimensionList = dimensionList;
    }

    /**
     * @Description: 判断是否为上传操作
     * @author king
     * @since Apr 20, 2013 11:09:28 AM 
     * @version V1.0
     * @return 是否为上传操作
     */
    public boolean isUpload() {
        return mIsUpload;
    }

    /**
     * @Description: 设置是否为上传操作
     * @author king
     * @since Apr 20, 2013 11:09:54 AM 
     * @version V1.0
     * @param upload 是否为上传操作
     */
    public void setUpload(boolean upload) {
        this.mIsUpload = upload;
    }

    /**
     * @Description: 取得excel操作信息
     * @author king
     * @since Apr 20, 2013 11:10:05 AM 
     * @version V1.0
     * @return excel操作信息
     */
    public ResponseExcel getResExcel() {
        return resExcel;
    }

    /**
     * @Description: 设置excel操作信息
     * @author king
     * @since Apr 20, 2013 11:10:22 AM 
     * @version V1.0
     * @param resExcel excel操作信息
     */
    public void setResExcel(ResponseExcel resExcel) {
        this.resExcel = resExcel;
    }

    /**
     * @Description: 取得返回数据流信息（一般用于接口调用）
     * @author king
     * @since Apr 20, 2013 11:10:31 AM 
     * @version V1.0
     * @return 返回数据流信息
     */
    public String getOutPut() {
        return outPut;
    }

    /**
     * @Description: 设置返回数据流信息
     * @author king
     * @since Apr 20, 2013 11:11:12 AM 
     * @version V1.0
     * @param outPut 返回数据流信息
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
