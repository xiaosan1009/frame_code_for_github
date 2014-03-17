/**
 * 
 */
package com.richClientFrame.check;

import com.richClientFrame.data.SetValType;
import com.richClientFrame.data.check.ErrTabBean;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.response.ResponseHeader;
import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.data.response.data.ResponseHeaderData;
import com.richClientFrame.exception.InputException;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.info.CheckInfo;
import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.info.ControlDispField;
import com.richClientFrame.info.ControlErrorMap;
import com.richClientFrame.info.ControlMasters;
import com.richClientFrame.info.ControlRequestMap;
import com.richClientFrame.info.ControlResourceMap;
import com.richClientFrame.service.IDbService;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.HtmlUtil;
import com.richClientFrame.util.LogUtil;

import java.util.List;
import java.util.Vector;

/**
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： LogUtil
 * 类描述 ： 共通验证类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.04.26
 * @author king
 */
public class CommonChecker {

    private NumChecker numCheck;

    private TimeChecker timeCheck;

    private DateChecker dateCheck;

    private StringChecker strCheck;
    
    private FileChecker fileChecker;
    
    protected IDbService db;
    
    protected LogUtil log = new LogUtil(this.getClass());
    
    /**
     * 
     */
    public CommonChecker() {
        super();
        numCheck = new NumChecker();
        timeCheck = new TimeChecker();
        dateCheck = new DateChecker();
        strCheck = new StringChecker();
        fileChecker = new FileChecker();
    }
    
    /**
     * 项目是否为必须输入项.
     * @param param request
     * @param fieldNo 项目ID
     * @return 是否为必须输入项【true：必须；false：非必须】
     * @throws RichClientWebException RichClientWebException
     */
    private boolean isRequired(Param param, String fieldNo) throws RichClientWebException {
        final ControlErrorMap errMap = ControlErrorMap.getInstance();
        final String requiredType = errMap.getRequiredType(param, fieldNo);
        int required = -1;
        try {
            required = Integer.parseInt(requiredType);
        } catch (NumberFormatException e) {
            throw new RichClientWebException(
                    "[FILE ERROR] There is no such pattern for a required check type. ( dispcode:" 
                    + param.dispCode + " field:" + fieldNo + " input:" + requiredType + ")");
        }

        boolean bRequired = false;

        switch (required) {
            case ControlErrorMap.IS_REQUIRED:
                bRequired = true;
                break;
            case ControlErrorMap.NOT_REQUIRED:
                bRequired = false;
                break;
            default:
                throw new RichClientWebException("[FILE ERROR] There is no such pattern " 
                        + "for a required check type. ( dispcode:" 
                        + param.dispCode + " field:" + fieldNo + " input:" + requiredType + ")");
        }
        return bRequired;
    }
    
    /**
     * 单一项目验证.
     * @param errTabList 错误信息对象
     * @param param request
     * @param fieldNo 项目ID
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public void commonCheckSingle(
            ErrTabBean errTabList,
            Param param, 
            String fieldNo) 
        throws RichClientWebException, InputException {
        
        log.debug("commonCheckSingle", "start");
        
        final String dispCode = param.dispCode;
        
        final String targetId = getTargetIds(fieldNo)[0];
        
        final String filedIndex = getTargetIds(fieldNo)[1];
        
        final String[] valueList = param.getList(targetId);
        
        final boolean bRequired = isRequired(param, targetId);
        
        log.debug("commonCheckSingle", null, " fieldKey : " + targetId, " valueList = " + valueList);

        if (bRequired) {
            if (valueList == null) {
                errTabList.add(makeErrTab(dispCode, targetId, filedIndex, ConstantsUtil.Check.NOTHING_INPUT));
                return;
            }
        } else {
            if (valueList == null) {
                return;
            }
        }
        
        if (valueList == null) {
            return;
        }

        if (CommonUtil.isNotEmpty(filedIndex)) {
            final String strValue = valueList[Integer.parseInt(filedIndex)];
            final ResponseTab resultTab = checkSingleItem(param, targetId, filedIndex, strValue);
            errTabList.add(resultTab);
        } else {
            for (int j = 0; j < valueList.length; j++) {
                String index = ConstantsUtil.Str.EMPTY;
                if (valueList.length > 1) {
                    index = String.valueOf(j);
                } else {
                    index = ConstantsUtil.Str.EMPTY;
                }
                
                final String strValue = valueList[j];
                
                final ResponseTab resultTab = checkSingleItem(param, targetId, index, strValue);
                errTabList.add(resultTab);
            }
        }
        
        log.debug("commonCheckSingle", "end");
        
    }
    
    /**
     * 获取画面ID信息.
     * @param targetId 画面ID
     * @return 画面ID信息
     */
    public String[] getTargetIds(String targetId) {
        if (CommonUtil.isEmpty(targetId)) {
            return null;
        }
        final String[] targetIds = new String[2];
        if (targetId.indexOf(ConstantsUtil.Str.TARGETID_SUFFIX) != -1) {
            targetIds[0] = targetId.substring(0, targetId.indexOf(ConstantsUtil.Str.TARGETID_SUFFIX));
            targetIds[1] = targetId.substring(
                    targetId.indexOf(ConstantsUtil.Str.TARGETID_SUFFIX) 
                    + ConstantsUtil.Str.TARGETID_SUFFIX.length());
        } else {
            targetIds[0] = targetId;
            targetIds[1] = ConstantsUtil.Str.EMPTY;
        }
        return targetIds;
    }
    
    /**
     * 多项目验证.
     * @param errTabBean 错误信息对象
     * @param param request
     * @param fieldNo 项目ID
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public void commonCheckSift(
            ErrTabBean errTabBean,
            Param param, 
            String fieldNo) 
        throws RichClientWebException, InputException {
        
        log.debug("commonCheckSift", "start");
        
        final String dispCode = param.dispCode;
        
        final String filedIndex = ConstantsUtil.Str.EMPTY;
        
        final String[] valueList = param.getList(fieldNo);
        
        final boolean bRequired = isRequired(param, fieldNo);
        
        log.debug("commonCheckSift", null, " fieldKey : " + fieldNo, " valueList = " + valueList);
        
        if (bRequired) {
            if (valueList == null) {
                errTabBean.add(makeErrTab(dispCode, fieldNo, filedIndex, ConstantsUtil.Check.NOTHING_INPUT));
                return;
            }
        }
        
        if (valueList == null) {
            return;
        }
        
        if (valueList.length == 1) {
            final ResponseTab resultTab = checkSingleItem(param, fieldNo, filedIndex, valueList[0]);
            errTabBean.add(resultTab);
        } else {
            final String checkIndexId = ControlRequestMap.getInstance().getCheckIndex(param);
            
            if (fieldNo.equals(checkIndexId)) {
                final ResponseTab resultTab = makeTabNormal(param.dispCode, fieldNo, filedIndex);
                errTabBean.add(resultTab);
                return;
            }
            
            final String[] checkIndexs = param.getList(checkIndexId);
            
            if (checkIndexs == null) {
                return;
            }
            
            try {
                for (int j = 0; j < checkIndexs.length; j++) {
                    
                    final String index = checkIndexs[j];
                    
                    final String strValue = valueList[Integer.parseInt(index)];
                    
                    final ResponseTab resultTab = checkSingleItem(param, fieldNo, index, strValue);
                    errTabBean.add(resultTab);
                }
            } catch (NumberFormatException e) {
                throw new RichClientWebException(EngineExceptionEnum.FM_CHECKER_CHECKINDEX_VALUE_MUST_INDEXNUMBER);
            }
            
        }
        
        log.debug("commonCheckSift", "end");
        
    }

    /**
     * 具体单一控件验证.
     * @param param request
     * @param fieldNo 项目ID
     * @param index 项目序号
     * @param strValue 项目值
     * @return 具体单一控件验证结果
     * @throws RichClientWebException RichClientWebException
     */
    private ResponseTab checkSingleItem(
            Param param, 
            String fieldNo, 
            String index, 
            final String strValue)
        throws RichClientWebException {
        
        ResponseTab resultTab = null;
        final boolean bRequired = isRequired(param, fieldNo);
        if (bRequired && CommonUtil.isEmpty(strValue)) {
            resultTab = makeErrTab(param.dispCode, fieldNo, index, ConstantsUtil.Check.NOTHING_INPUT);
            return resultTab;
        }
        
        CheckInfo checkInfo = new CheckInfo();
        
        final ControlErrorMap errMap = ControlErrorMap.getInstance();
        
        final String errType = errMap.getErrType(param, fieldNo);
        log.debug("checkSingleItem", null, " errType : " + errType);
        
        int errTypeInt = -1;
        if (errType != null) {
            errTypeInt = Integer.parseInt(errType);
        } else {
            throw new RichClientWebException(
                    "[FILE ERROR] There is no such pattern for a error " 
                    + "check type. ( dispcode:" + param.dispCode + " field:" + fieldNo
                            + " input:" + errType + ")");
        }
        
        log.debug("checkSingleItem", null, "errTypeInt : " + errTypeInt);

        switch (errTypeInt) {
            case ControlErrorMap.CHECK_LONG:
                checkInfo = numCheck.checkLong(param, fieldNo, strValue, bRequired);
                break;

            case ControlErrorMap.CHECK_FLOAT:
                checkInfo = numCheck.checkFloat(param, fieldNo, strValue, bRequired);
                break;

            case ControlErrorMap.CHECK_TIME:
                checkInfo = timeCheck.checkTime(param, fieldNo, strValue, bRequired);
                break;

            case ControlErrorMap.CHECK_DATE:
                checkInfo = dateCheck.checkDate(param, fieldNo, strValue, bRequired);
                break;

            case ControlErrorMap.CHECK_STRING:
                checkInfo = strCheck.checkString(param, fieldNo, strValue, bRequired);
                break;
                
            case ControlErrorMap.CHECK_FILE:
                checkInfo = fileChecker.checkFile(param, fieldNo, strValue, bRequired);
                break;

            case ControlErrorMap.CHECK_DEF:
                break;
            default:
                throw new RichClientWebException("[FILE ERROR] There is no such pattern for a " 
                        + "error check type. ( dispcode:" 
                        + param.dispCode + " field:" + fieldNo + " input:" + errType + ")");
        }

        if (checkInfo.isError()) {
            resultTab = makeErrTab(param.dispCode, fieldNo, index, checkInfo);
        } else {
            resultTab = makeTabNormal(param.dispCode, fieldNo, index);
        }
        return resultTab;
    }
    
    /**
     * 共通性验证.
     * @param param request
     * @return 验证结果
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public ErrTabBean commonCheck(Param param) 
        throws RichClientWebException, InputException {
        
        log.debug("commonCheck", "start");

        final ErrTabBean errTabList = new ErrTabBean();

        final ControlErrorMap errMap = ControlErrorMap.getInstance();
        
        log.debug("commonCheck", null, "errMap : " + errMap);

        // 取得画面需要验证的控件组
        final String[] fieldKeys = errMap.getAllCheckFields(param);
        if (fieldKeys == null) {
            return errTabList;
        }
        
        log.info("commonCheck", null, "fieldKeys.length : " + fieldKeys.length);

        for (int i = 0; i < fieldKeys.length; i++) {
            commonCheckSingle(errTabList, param, fieldKeys[i]);
        }
        
        log.debug("commonCheck", "end");
         
        return errTabList;

    }
    
    /**
     * 共通性验证.
     * @param param request
     * @return 验证结果
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public ErrTabBean commonCheckSift(Param param) 
        throws RichClientWebException, InputException {
        
        log.debug("commonCheckSift", "start");
        
        final ErrTabBean errTabBean = new ErrTabBean();
        
        final ControlErrorMap errMap = ControlErrorMap.getInstance();
        
        log.debug("commonCheckSift", null, "errMap : " + errMap);
        
        // 取得画面需要验证的控件组
        final String[] fieldKeys = errMap.getAllCheckFields(param);
        if (fieldKeys == null) {
            return errTabBean;
        }
        
        log.debug("commonCheckSift", null, "fieldKeys.length : " + fieldKeys.length);
        
        for (int i = 0; i < fieldKeys.length; i++) {
            commonCheckSift(errTabBean, param, fieldKeys[i]);
        }
        
        log.debug("commonCheckSift", "end");
        
        return errTabBean;
        
    }
    
    /**
     * 单一项目共通性验证.
     * @param param request
     * @return 验证结果
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public ErrTabBean commonCheckSingleOnly(Param param) 
        throws InputException, RichClientWebException {
        
        log.debug("commonCheckSingleOnly", "start");
        
        final ErrTabBean errTabBean = new ErrTabBean();
        
        this.commonCheckSingle(errTabBean, param, param.targetId);
        
        final int size = errTabBean.getErrList().size();
        if (size != 0) {
            final ResponseTab[] tablist = (ResponseTab[]) errTabBean.getErrList().toArray(new ResponseTab[size]);
            if (HtmlUtil.hasErrorCheck(tablist)) {
                final ResponseHeader errHead = makeErrHead(param);
                
                throw new InputException(errHead, tablist);
            }
        }
        
        log.debug("commonCheckSingleOnly", "end");
        return errTabBean;
    }
    
    /**
     * 共通性验证.
     * @param param request
     * @return 验证结果
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public ErrTabBean commonCheckOnly(Param param)
        throws InputException, RichClientWebException {
        
        log.debug("commonCheckOnly", "start");
         
        final ErrTabBean errTabBean = this.commonCheck(param);
        
        final int size = errTabBean.getErrList().size();
        if (size != 0) {
            final ResponseTab[] tablist = (ResponseTab[])errTabBean.getErrList().toArray(new ResponseTab[size]);
            if (HtmlUtil.hasErrorCheck(tablist)) {
                final ResponseHeader errHead = makeErrHead(param);
                throw new InputException(errHead, tablist);
            }
        }
        
        log.debug("commonCheckOnly", "end");
        return errTabBean;
    }
    
    /**
     * 共通性验证.
     * @param param request
     * @return 验证结果
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public ErrTabBean commonCheckSiftOnly(Param param)
        throws InputException, RichClientWebException {
        
        log.debug("commonCheckSiftOnly", "start");
        
        final ErrTabBean errTabBean = this.commonCheckSift(param);
        
        final int size = errTabBean.getErrList().size();
        if (size != 0) {
            final ResponseTab[] tablist = (ResponseTab[])errTabBean.getErrList().toArray(new ResponseTab[size]);
            if (HtmlUtil.hasErrorCheck(tablist)) {
                final ResponseHeader errHead = makeErrHead(param);
                throw new InputException(errHead, tablist);
            }
        }
        
        log.debug("commonCheckSiftOnly", "end");
        return errTabBean;
    }
    
    /**
     * 创建正常控件，去除错误样式.
     * @param dispId 画面ID
     * @param fieldId 项目ID
     * @param idx 项目序号
     * @return 映射对象
     * @throws RichClientWebException RichClientWebException
     */
    private ResponseTab makeTabNormal(
            String dispId, 
            String fieldId, 
            String idx) throws RichClientWebException {
        final ResponseTab tab = new ResponseTab();
        
        tab.setTargetId(fieldId);
        tab.setTargetIndex(idx);
        tab.setEffect(ConstantsUtil.Status.EFFECT_NO);
        final int type = getDataType(dispId, fieldId);
        tab.setDataType(type);
        tab.setBgColor(ControlConfig.getInstance().getConfiguration().getItemBackColor());
        tab.setCharColor(ConstantsUtil.Status.CHAR_COLOR_DEFAULT);
        tab.setDispStat(ConstantsUtil.Status.NO_CHANGE);
        
        return tab;
    }

    /**
     * 创建错误信息与页面的映射头信息.
     * @param param request
     * @return 映射头信息
     * @throws RichClientWebException RichClientWebException
     */
    protected ResponseHeader makeErrHead(Param param) throws RichClientWebException {
        final ResponseHeader errHead = makeErrHead(param, EngineExceptionEnum.UR_COM_INPUT_ERROR);
        return errHead;

    }
    
    /**
     * 创建错误信息与页面的映射头信息.
     * @param param request
     * @param resCode 结果号
     * @return 映射头信息
     * @throws RichClientWebException RichClientWebException
     */
    protected ResponseHeader makeErrHead(Param param, EngineExceptionEnum resCode) 
        throws RichClientWebException {
        return makeErrHead(param, resCode.getCode());
    }
    
    /**
     * 创建错误信息与页面的映射头信息.
     * @param param request
     * @param resCode 结果号
     * @return 映射头信息
     * @throws RichClientWebException RichClientWebException
     */
    protected ResponseHeader makeErrHead(Param param, String resCode) 
        throws RichClientWebException {
        final ResponseHeaderData responseHeaderData = new ResponseHeaderData();
        responseHeaderData.setParam(param);
        responseHeaderData.setResCode(resCode);
        final ResponseHeader errHead = new ResponseHeader(responseHeaderData);
        errHead.setError(ResponseHeader.ERROR_HEADER_CODE);
        return errHead;
        
    }
    
    /**
     * 创建错误信息与页面的映射对象.
     * @param dispId 画面ID
     * @param fieldId 项目ID
     * @param detailCode 错误号
     * @return 映射对象
     * @throws RichClientWebException RichClientWebException
     */
    public ResponseTab makeErrTab(
            String dispId, 
            String fieldId, 
            String detailCode) throws RichClientWebException {
        return makeErrTab(dispId, fieldId, ConstantsUtil.Str.EMPTY, detailCode);
    }
    
    /**
     * 创建错误信息与页面的映射对象.
     * @param dispId 画面ID
     * @param fieldId 项目ID
     * @param idx 项目序号
     * @param detailCode 错误号
     * @return 映射对象
     * @throws RichClientWebException RichClientWebException
     */
    public ResponseTab makeErrTab(
            String dispId, 
            String fieldId, 
            String idx, 
            String detailCode) throws RichClientWebException {
        
        final String itemNameId = ControlDispField.getInstance().getMessage(dispId, fieldId, idx);
        
        return makeErrTabExec(dispId, fieldId, idx, detailCode, itemNameId, ConstantsUtil.Str.EMPTY);
    }
    
    /**
     * 创建错误信息与页面的映射对象.
     * @param dispId 画面ID
     * @param fieldId 项目ID
     * @param idx 项目序号
     * @param checkInfo 验证信息
     * @return 映射对象
     * @throws RichClientWebException RichClientWebException
     */
    public ResponseTab makeErrTab(
            String dispId, 
            String fieldId, 
            String idx, 
            CheckInfo checkInfo) throws RichClientWebException {
        
        final String itemNameId = ControlDispField.getInstance().getMessage(dispId, fieldId, idx);
        
        final String detailCode = checkInfo.getDetailCode();
        
        final String message = getMessage(dispId, checkInfo, detailCode);
        
        return makeErrTabExec(dispId, fieldId, idx, detailCode, itemNameId, message);
    }

    /**
     * @Description: 
     * @author king
     * @since Jun 3, 2013 5:26:39 PM 
     * @version V1.0
     * @param dispId dispId
     * @param checkInfo checkInfo
     * @param detailCode detailCode
     * @return Message
     * @throws RichClientWebException RichClientWebException
     */
    private String getMessage(String dispId, CheckInfo checkInfo, final String detailCode)
        throws RichClientWebException {
        final String message = getErrTypeMessage(dispId, detailCode);
        
        return getPatternMessage(checkInfo, message);
    }

    /**
     * @Description: 
     * @author king
     * @since Jun 3, 2013 5:25:17 PM 
     * @version V1.0
     * @param checkInfo checkInfo
     * @param message message
     * @return PatternMessage
     */
    private String getPatternMessage(CheckInfo checkInfo, String message) {
        final List<String> pattern = checkInfo.getPattern();
        
        if (pattern != null) {
            for (int i = 0; i < pattern.size(); i++) {
                message = message.replace("%" + i, pattern.get(i));
            }
        }
        return message;
    }

    /**
     * @Description: 
     * @author king
     * @since Jun 3, 2013 5:23:56 PM 
     * @version V1.0
     * @param dispId dispId
     * @param detailCode detailCode
     * @return ErrTypeMessage
     * @throws RichClientWebException RichClientWebException
     */
    
    private String getErrTypeMessage(String dispId, final String detailCode)
        throws RichClientWebException {
        final SetValType errType = ControlMasters.getInstance().getErrType(dispId, detailCode);
        if (errType == null) {
            throw new RichClientWebException(EngineExceptionEnum.FM_CHECKER_CANT_GET_ERRMSG_INFO);
        }
        
        return errType.getName();
    }

    /**
     * 创建错误信息与页面的映射对象.
     * @param dispId 画面ID
     * @param fieldId 项目ID
     * @param idx 项目序号
     * @param detailCode 错误号
     * @param itemNameId 项目名称
     * @param detailMsg 错误详细信息
     * @return 映射对象
     * @throws RichClientWebException RichClientWebException
     */
    private ResponseTab makeErrTabExec(
            String dispId, 
            String fieldId, 
            String idx, 
            String detailCode, 
            String itemNameId, 
            String detailMsg) throws RichClientWebException {
        
        log.debug("makeErrTabExec", "start", 
                "dispId = " + dispId, 
                "fieldId = " + fieldId, 
                "idx = " + idx, 
                "detailCode = " + detailCode, 
                "itemNameId = " + itemNameId, 
                "detailMsg = " + detailMsg);
        
        final ResponseTab tab = new ResponseTab();
        final int type = getDataType(dispId, fieldId);
        detailMsg = getDetailMsg(dispId, detailCode, detailMsg);
        final String itemName = ControlResourceMap.getInstance().getItemName(dispId, itemNameId);

        tab.setTargetId(fieldId);
        tab.setTargetIndex(idx);
        tab.setEffect(ConstantsUtil.Status.EFFECT_NO);
        tab.setDataType(type);
        tab.setBgColor(ControlConfig.getInstance().getConfiguration().getInputErrBackColor());
        tab.setCharColor(ConstantsUtil.Status.CHAR_COLOR_DEFAULT);
        tab.setErrMsgColor(ControlConfig.getInstance().getConfiguration().getInputErrCharColor());
        tab.setDispStat(ConstantsUtil.Status.NO_CHANGE);
        tab.setDetailCode(detailCode);
        tab.setItemName(itemName);
        tab.setDetailMsg(detailMsg);

        log.debug("makeErrTabExec", "end", "detailMsg = " + tab.getDetailMsg());
        return tab;
    }

    /**
     * @Description: 
     * @author king
     * @since Jun 3, 2013 3:41:13 PM 
     * @version V1.0
     * @param dispId dispId
     * @param detailCode detailCode
     * @param detailMsg detailMsg
     * @return DetailMsg
     * @throws RichClientWebException RichClientWebException
     */
    private String getDetailMsg(String dispId, String detailCode, String detailMsg)
        throws RichClientWebException {
        if (CommonUtil.isEmpty(detailMsg)) {
            final SetValType errType = ControlMasters.getInstance().getErrType(dispId, detailCode);
            if (errType == null) {
                throw new RichClientWebException(EngineExceptionEnum.FM_CHECKER_CANT_GET_ERRMSG_INFO);
            }
            detailMsg = errType.getName();
        }
        return detailMsg;
    }

    /**
     * @Description: 
     * @author king
     * @since Jun 3, 2013 3:24:38 PM 
     * @version V1.0
     * @param dispId dispId
     * @param fieldId fieldId
     * @return DataType
     * @throws RichClientWebException RichClientWebException
     */
    
    private int getDataType(String dispId, String fieldId)
        throws RichClientWebException {
        String strType = null;
        int type = ConstantsUtil.Widget.DEFAULT;
        try {
            if (dispId != null) {
                final ControlDispField dispFld = ControlDispField.getInstance();
                strType = dispFld.getType(dispId, fieldId);
                if (strType != null) {
                    type = Integer.parseInt(strType);
                } else {
                    type = ConstantsUtil.Widget.TEXTBOX;
                }
            }
        } catch (NumberFormatException e) {
            throw new RichClientWebException("[FILE ERROR] The field type( " + strType
                    + " ) is not a number. ( dispcode:" + dispId + " field:"
                    + fieldId + ")");
        }
        return type;
    }
    
    /**
     * 显示错误信息.
     * @param errTabList 错误数组
     * @param param request
     * @throws InputException InputException
     * @throws RichClientWebException RichClientWebException
     */
    public void showErrorResponse(List<ResponseTab> errTabList, Param param) 
        throws InputException, RichClientWebException {
        ResponseHeader errHead = null;
        ResponseTab[] tablist = null;
        if (errTabList.size() != 0) {
            tablist = (ResponseTab[]) errTabList.toArray(new ResponseTab[errTabList.size()]);
            if (HtmlUtil.hasErrorCheck(tablist)) {
                errHead = makeErrHead(param);
                throw new InputException(errHead, tablist);
            }
        }
    }
    
    /**
     * 显示错误信息.
     * @param errTabBean 错误数组
     * @param param request
     * @param resCode 结果号
     * @throws InputException InputException
     * @throws RichClientWebException RichClientWebException
     */
    public void showErrorResponse(ErrTabBean errTabBean, Param param, String resCode) 
        throws InputException, RichClientWebException {
        ResponseHeader errHead = null;
        final int size = errTabBean.getErrList().size();
        if (size != 0) {
            final ResponseTab[] tablist = (ResponseTab[]) errTabBean.getErrList().toArray(new ResponseTab[size]);
            errHead = makeErrHead(param, resCode);
            throw new InputException(errHead, tablist);
        } else {
            errHead = makeErrHead(param, resCode);
            throw new RichClientWebException(errHead);
        }
    }

    /**
     * 设置数据库对象.
     * @param db 数据库对象
     */
    public void setDb(IDbService db) {
        this.db = db;
    }
}

