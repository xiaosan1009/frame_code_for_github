package com.richClientFrame.data.response;

import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.response.data.ResponseHeaderData;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.info.ControlMasters;
import com.richClientFrame.info.ControlRequestMap;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;

import java.io.Serializable;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ResponseHeader
 * 类描述 ： ResponseHeader的管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2009.07.28
 * @author king
 */
public class ResponseHeader implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static final int NORMAL_HEADER_CODE = 0; 
    
    public static final int ERROR_HEADER_CODE = 1;
    
    /**
     * 数据种别
     */
    private String dataKind = ConstantsUtil.DataType.INITIAL;
    
    /**
     * 结果类型
     */
    private String resCode = EngineExceptionEnum.UR_COM_BLANK.getCode();

    /**
     * 控件ID
     */
    private String targetId = "";
    
    /**
     * 画面ID
     */
    private String disp = "";
    
    /**
     * 控件ID
     */
    private String cmd = "";
    
    /**
     * 多项目控件序号
     */
    private String targetIndex = "";
    
    /**
     * 多项目控件序号
     */
    private String headerMsg = "";
    
    /**
     * 多项目列表数量
     */
    private String totalRows = "";
    
    /**
     * 多项目列表每页显示数量
     */
    private String pageSize = "";
    
    /**
     * 是否为错误信息头【0：不是；非0：是】
     */
    private int error;
    
    /**
     * 画面初始化的ResponseHeader的生成.
     * @throws RichClientWebException RichClientWebException
     */
    public ResponseHeader() throws RichClientWebException {
        super();
        this.dataKind = ConstantsUtil.DataType.PAGE;
        this.headerMsg = getHeaderMsg(resCode);
    }
    
    /**
     * 画面初始化的ResponseHeader的生成.
     * @param resHeaderData resHeaderData
     * @throws RichClientWebException RichClientWebException
     */
    public ResponseHeader(ResponseHeaderData resHeaderData) throws RichClientWebException {
        super();
        final Param param = resHeaderData.getParam();
        if (CommonUtil.isNotEmpty(resHeaderData.getResCode())) {
            this.resCode = resHeaderData.getResCode();
        }
        if (param != null) {
            this.dataKind = ConstantsUtil.DataType.SINGLE;
            if (EngineExceptionEnum.UR_COM_PAGE_CHANGED.getCode().equals(resHeaderData.getResCode())) {
                this.targetId = ControlRequestMap.getInstance().getForward(param);
            } else {
                this.targetId = param.targetId;
            }
            this.targetIndex = param.targetIndex;
            this.headerMsg = getHeaderMsg(param, resCode);
            this.disp = param.dispCode;
            this.cmd = param.cmdCode;
        }
        if (CommonUtil.isNotEmpty(resHeaderData.getTargetId())) {
            this.targetId = resHeaderData.getTargetId();
        }
        if (CommonUtil.isNotEmpty(resHeaderData.getDataKind())) {
            this.dataKind = resHeaderData.getDataKind();
        }
        if (CommonUtil.isEmpty(headerMsg)) {
            this.headerMsg = getHeaderMsg(resCode);
        }
    }
//    
//    /**
//     * 单项目的ResponseHeader的生成.
//     * @param resEnum 结果类型
//     * @throws RichClientWebException RichClientWebException
//     */
//    public ResponseHeader(EngineExceptionEnum resEnum) throws RichClientWebException {
//        super();
//        this.mResCode = resEnum;
//        this.dataKind = ConstantsUtil.DataType.SINGLE;
//        this.headerMsg = getHeaderMsg(mResCode.getCode());
//    }
//    
//    /**
//     * 单项目的ResponseHeader的生成.
//     * @param param request
//     * @param resEnum 结果类型
//     * @throws RichClientWebException RichClientWebException
//     */
//    public ResponseHeader(Param param, EngineExceptionEnum resEnum) throws RichClientWebException {
//        super();
//        this.mResCode = resEnum;
//        this.dataKind = ConstantsUtil.DataType.SINGLE;
//        this.headerMsg = getHeaderMsg(param, mResCode.getCode());
//        this.targetId = param.targetId;
//        this.targetIndex = param.targetIndex;
//        this.disp = param.dispCode;
//        this.cmd = param.cmdCode;
//    }
//    
//    /**
//     * 单项目的ResponseHeader的生成.
//     * @param param request
//     * @param dataKind 数据种别
//     * @throws RichClientWebException RichClientWebException
//     */
//    public ResponseHeader(Param param, int dataKind) throws RichClientWebException {
//        super();
//        this.dataKind = dataKind;
//        this.headerMsg = getHeaderMsg(param, mResCode.getCode());
//        this.targetId = param.targetId;
//        this.targetIndex = param.targetIndex;
//        this.disp = param.dispCode;
//        this.cmd = param.cmdCode;
//    }
//    
//    /**
//     * 单项目的ResponseHeader的生成.
//     * @param param request
//     * @param resEnum 结果类型
//     * @param dataKind 数据种别
//     * @throws RichClientWebException RichClientWebException
//     */
//    public ResponseHeader(Param param, EngineExceptionEnum resEnum, int dataKind) throws RichClientWebException {
//        super();
//        this.mResCode = resEnum;
//        this.dataKind = dataKind;
//        this.headerMsg = getHeaderMsg(param, mResCode.getCode());
//        this.targetId = param.targetId;
//        this.targetIndex = param.targetIndex;
//        this.disp = param.dispCode;
//        this.cmd = param.cmdCode;
//    }
//    
//    /**
//     * 列表项目的ResponseHeader的生成.
//     * @param param request
//     * @param targetId 控件ID
//     * @throws RichClientWebException RichClientWebException
//     */
//    public ResponseHeader(Param param, String targetId) throws RichClientWebException {
//        super();
//        this.dataKind = ConstantsUtil.DataType.LIST;
//        this.targetId = targetId;
//        this.targetIndex = ConstantsUtil.Str.EMPTY;
//        this.headerMsg = getHeaderMsg(param, mResCode.getCode());
//        this.disp = param.dispCode;
//        this.cmd = param.cmdCode;
//    }
//    
//    /**
//     * 画面迁移的ResponseHeader的生成.
//     * @param param request
//     * @throws RichClientWebException RichClientWebException
//     */
//    public ResponseHeader(Param param) throws RichClientWebException {
//        super();
//        this.dataKind = ConstantsUtil.DataType.SINGLE;
//        this.targetId = ControlRequestMap.getInstance().getForward(param);
//        this.targetIndex = ConstantsUtil.Str.EMPTY;
//        this.headerMsg = getHeaderMsg(mResCode.getCode());
//        this.disp = param.dispCode;
//        this.cmd = param.cmdCode;
//    }
    
    /**
     * ResponseHeader信息的取得.
     * @return ResponseHeader信息的字符串形式
     */
    public String getTabData() {
        
        final StringBuffer buf = new StringBuffer();
        buf.append(dataKind);
        buf.append("\t");
        buf.append(resCode);
        buf.append("\n");
        buf.append(targetId);
        buf.append("\t");
        buf.append(targetIndex);
        buf.append("\t");
        buf.append(cmd);
        
        return buf.toString();
    }

    /**
     * dataKind的取得.
     * @return dataKind的值
     */
    public String getDataKind() {
        return dataKind;
    }

    /**
     * dataKind的设定.
     * @param dataKind 数据类型的值
     */
    public void setDataKind(String dataKind) {
        this.dataKind = dataKind;
    }

    /**
     * resCode的取得.
     * @return resCode的值
     */
    public String getResCode() {
        return resCode;
    }

    /**
     * targetId的取得.
     * @return targetId的值
     */
    public String getTargetId() {
        return targetId;
    }

    /**
     * targetId的设定.
     * @param targetId 控件ID的值
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    /**
     * targetIndex的取得.
     * @return targetIndex的值
     */
    public String getTargetIndex() {
        return targetIndex;
    }

    /**
     * targetIndex的设定.
     * @param targetIndex 控件ID INDEX的值
     */
    public void setTargetIndex(String targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * 错误信息的取得.
     * @return 错误信息
     */
    public String getHeaderMsg() {
        return headerMsg;
    }

    /**
     * 错误信息的设定.
     * @param headerMsg 错误信息
     */
    public void setHeaderMsg(String headerMsg) {
        this.headerMsg = headerMsg;
    }
    
    /**
     * 头信息的取得.
     * @param resCode 结果号
     * @return 头信息
     * @throws RichClientWebException RichClientWebException
     */
    private String getHeaderMsg(String resCode) throws RichClientWebException {
        String msg = ConstantsUtil.Str.EMPTY;
        if (ControlMasters.getInstance().getHeaderType(resCode) != null) {
            msg = ControlMasters.getInstance().getHeaderType(resCode).getName();
        }
        return msg;
    }
    
    /**
     * 头信息的取得.
     * @param param request
     * @param resCode 结果号
     * @return 头信息
     * @throws RichClientWebException RichClientWebException
     */
    private String getHeaderMsg(Param param, String resCode) throws RichClientWebException {
        String msg = ConstantsUtil.Str.EMPTY;
        if (ControlMasters.getInstance().getHeaderType(param.dispCode, resCode) != null) {
            msg = ControlMasters.getInstance().getHeaderType(param.dispCode, resCode).getName();
        } else if (ControlMasters.getInstance().getHeaderType(
                param.get("msgDispCode"), resCode) != null) {
            msg = ControlMasters.getInstance().getHeaderType(param.get("msgDispCode"), resCode).getName();
        }
        return msg;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(String totalRows) {
        this.totalRows = totalRows;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }
}
