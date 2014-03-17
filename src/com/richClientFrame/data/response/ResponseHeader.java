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
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ResponseHeader
 * ������ �� ResponseHeader�Ĺ�����.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2009.07.28
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
     * �����ֱ�
     */
    private String dataKind = ConstantsUtil.DataType.INITIAL;
    
    /**
     * �������
     */
    private String resCode = EngineExceptionEnum.UR_COM_BLANK.getCode();

    /**
     * �ؼ�ID
     */
    private String targetId = "";
    
    /**
     * ����ID
     */
    private String disp = "";
    
    /**
     * �ؼ�ID
     */
    private String cmd = "";
    
    /**
     * ����Ŀ�ؼ����
     */
    private String targetIndex = "";
    
    /**
     * ����Ŀ�ؼ����
     */
    private String headerMsg = "";
    
    /**
     * ����Ŀ�б�����
     */
    private String totalRows = "";
    
    /**
     * ����Ŀ�б�ÿҳ��ʾ����
     */
    private String pageSize = "";
    
    /**
     * �Ƿ�Ϊ������Ϣͷ��0�����ǣ���0���ǡ�
     */
    private int error;
    
    /**
     * �����ʼ����ResponseHeader������.
     * @throws RichClientWebException RichClientWebException
     */
    public ResponseHeader() throws RichClientWebException {
        super();
        this.dataKind = ConstantsUtil.DataType.PAGE;
        this.headerMsg = getHeaderMsg(resCode);
    }
    
    /**
     * �����ʼ����ResponseHeader������.
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
//     * ����Ŀ��ResponseHeader������.
//     * @param resEnum �������
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
//     * ����Ŀ��ResponseHeader������.
//     * @param param request
//     * @param resEnum �������
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
//     * ����Ŀ��ResponseHeader������.
//     * @param param request
//     * @param dataKind �����ֱ�
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
//     * ����Ŀ��ResponseHeader������.
//     * @param param request
//     * @param resEnum �������
//     * @param dataKind �����ֱ�
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
//     * �б���Ŀ��ResponseHeader������.
//     * @param param request
//     * @param targetId �ؼ�ID
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
//     * ����Ǩ�Ƶ�ResponseHeader������.
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
     * ResponseHeader��Ϣ��ȡ��.
     * @return ResponseHeader��Ϣ���ַ�����ʽ
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
     * dataKind��ȡ��.
     * @return dataKind��ֵ
     */
    public String getDataKind() {
        return dataKind;
    }

    /**
     * dataKind���趨.
     * @param dataKind �������͵�ֵ
     */
    public void setDataKind(String dataKind) {
        this.dataKind = dataKind;
    }

    /**
     * resCode��ȡ��.
     * @return resCode��ֵ
     */
    public String getResCode() {
        return resCode;
    }

    /**
     * targetId��ȡ��.
     * @return targetId��ֵ
     */
    public String getTargetId() {
        return targetId;
    }

    /**
     * targetId���趨.
     * @param targetId �ؼ�ID��ֵ
     */
    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    /**
     * targetIndex��ȡ��.
     * @return targetIndex��ֵ
     */
    public String getTargetIndex() {
        return targetIndex;
    }

    /**
     * targetIndex���趨.
     * @param targetIndex �ؼ�ID INDEX��ֵ
     */
    public void setTargetIndex(String targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * ������Ϣ��ȡ��.
     * @return ������Ϣ
     */
    public String getHeaderMsg() {
        return headerMsg;
    }

    /**
     * ������Ϣ���趨.
     * @param headerMsg ������Ϣ
     */
    public void setHeaderMsg(String headerMsg) {
        this.headerMsg = headerMsg;
    }
    
    /**
     * ͷ��Ϣ��ȡ��.
     * @param resCode �����
     * @return ͷ��Ϣ
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
     * ͷ��Ϣ��ȡ��.
     * @param param request
     * @param resCode �����
     * @return ͷ��Ϣ
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
