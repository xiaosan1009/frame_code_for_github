package com.richClientFrame.data.response.data;

import com.richClientFrame.data.param.Param;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ResponseHeaderData
 * 类描述 ： 
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Aug 15, 2013 2:43:59 PM
 * @author king
 */
public class ResponseHeaderData {
    
    private Param param;
    
    private String resCode;
    
    private String dataKind;
    
    private String targetId;

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getDataKind() {
        return dataKind;
    }

    public void setDataKind(String dataKind) {
        this.dataKind = dataKind;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

}
