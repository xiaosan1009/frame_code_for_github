package com.richClientFrame.util;

import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseData;
import com.richClientFrame.data.response.ResponseHeader;
import com.richClientFrame.data.response.ResponseLine;
import com.richClientFrame.data.response.ResponseList;
import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.exception.EngineCommonException;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.info.Configuration;
import com.richClientFrame.info.ControlConfig;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 页面共通方法类.
 * @author King
 * @since 2010.03.19
 */
public final class HtmlUtil {
    
    private static final String HEADER_BEGIN_TAG = "<HEADER";
    private static final String RESPONSE_TAB_BEGIN_TAG = "<RESPONSETAB";
    private static final String WIDGET_END_TAG = " />\r\n";
    
    /**
     * logger
     */
    private static LogUtil sLog = new LogUtil(HtmlUtil.class, true);
    
    /**
     * 构造函数.
     */
    private HtmlUtil() {
    }

    /**
     * 创建AJAX请求头文件.
     * 
     * @param resData 请求信息
     * @return 头文件信息
     */
    public static String createResponseHeader(AbstractResponseData resData) {
        String header = ConstantsUtil.Str.EMPTY;
        if (ConstantsUtil.Client.AJAX_TYPE.equals(resData.getClientType())) {
            if (resData != null && !resData.isUpload()) {
                final ResponseHeader headerDom = resData.getResHeader();
                header = createAjaxResponseHeader(resData);
                if (headerDom != null && (headerDom.getDataKind() == ConstantsUtil.DataType.COMPLEX)) {
                    header += createAjaxResponseTab(resData);
                }
            }
        }
        return header;
    }
    
    /**
     * 创建AJAX请求头文件.
     * 
     * @param resData 请求信息
     * @return 头文件信息
     */
    public static String createResponseHeaderForFilter(AbstractResponseData resData) {
        sLog.debug("createResponseHeaderForFilter", "start", 
                "resData = " + resData, "type = " + resData.getClientType());
        String header = ConstantsUtil.Str.EMPTY;
        if (ConstantsUtil.Client.AJAX_TYPE.equals(resData.getClientType())) {
            if (!resData.isUpload()) {
                header = createAjaxResponseHeader(resData);
            }
        } else {
            header = createJsonResponseHeader(resData).toString();
        }
        sLog.debug("createResponseHeaderForFilter", "end");
        return header;
    }
    
    /**
     * 创建AJAX请求头文件.
     * 
     * @param resData 请求信息
     * @return 头文件信息
     */
    private static String createAjaxResponseHeader(AbstractResponseData resData) {
        sLog.debug("createAjaxResponseHeader", "start");
        final StringBuilder headerInnerHtml = new StringBuilder();
        ResponseHeader header = null;
        if (resData != null) {
            header = resData.getResHeader();
            if (header != null && header.getDataKind() != ConstantsUtil.DataType.PAGE) {
                headerInnerHtml.append(HEADER_BEGIN_TAG);
                headerInnerHtml.append(" dispCode=\"" + header.getDisp() + "\""); 
                headerInnerHtml.append(" cmdCode=\"" + header.getCmd() + "\""); 
                headerInnerHtml.append(" dataKind=\"" + header.getDataKind() + "\""); 
                headerInnerHtml.append(" resCode=\"" + header.getResCode() + "\"");
                headerInnerHtml.append(" targetId=\"" + header.getTargetId() + "\"");
                headerInnerHtml.append(" targetIndex=\"" + header.getTargetIndex() + "\"");
                headerInnerHtml.append(" headerMsg=\"" + header.getHeaderMsg() + "\"");
                headerInnerHtml.append(" error=\"" + header.getError() + "\"");
                headerInnerHtml.append(" totalRows=\"" + header.getTotalRows() + "\"");
                headerInnerHtml.append(" pageSize=\"" + header.getPageSize() + "\"");
                headerInnerHtml.append(WIDGET_END_TAG);
            }
        }
        sLog.debug("createAjaxResponseHeader", "end", "header = " + headerInnerHtml.toString());
        return headerInnerHtml.toString();
    }
    
    /**
     * 创建JSON请求头文件.
     * 
     * @param resData 请求信息
     * @return 头文件信息
     */
    public static JSONObject createJsonResponseHeader(AbstractResponseData resData) {
        sLog.debug("createJsonResponseHeader", "start");
        JSONObject headerJson = null;
        ResponseHeader header = null;
        if (resData != null) {
            header = resData.getResHeader();
            if (header != null) {
                headerJson = new JSONObject();
                headerJson.put(ConstantsUtil.Json.DISP_HEADER, header.getDisp());
                headerJson.put(ConstantsUtil.Json.CMD_HEADER, header.getCmd());
                headerJson.put(ConstantsUtil.Json.DATA_KIND_HEADER, header.getDataKind());
                headerJson.put(ConstantsUtil.Json.RES_CODE_HEADER, header.getResCode());
                headerJson.put(ConstantsUtil.Json.TARGET_ID_HEADER, header.getTargetId());
                headerJson.put(ConstantsUtil.Json.TARGET_INDEX_HEADER, header.getTargetIndex());
                headerJson.put(ConstantsUtil.Json.MSG_HEADER, CommonUtil.toString(header.getHeaderMsg()));
                headerJson.put(ConstantsUtil.Json.ERROR_HEADER, CommonUtil.toString(header.getError()));
                headerJson.put(ConstantsUtil.Json.TOTAL_ROWS_HEADER, header.getTotalRows());
                headerJson.put(ConstantsUtil.Json.PAGE_SIZE_HEADER, header.getPageSize());
            }
        }
        sLog.debug("createJsonResponseHeader", "end", "header = " + headerJson);
        return headerJson;
    }
    
    /**
     * 创建请求单项目信息.
     * 
     * @param resData 请求信息
     * @return 单项目信息
     */
    public static String createResponseTab(AbstractResponseData resData) {
        String responseTab = "";
        if (ConstantsUtil.Client.AJAX_TYPE.equals(resData.getClientType())) {
            if (resData.isUpload()) {
                responseTab = createJsonResponseTab(resData);
            } else {
                responseTab = createAjaxResponseTab(resData);
            }
        } else if (ConstantsUtil.Client.FLEX_TYPE.equals(resData.getClientType())) {
            responseTab = createJsonResponseTab(resData);
        } else if (ConstantsUtil.Client.ANDROID_TYPE.equals(resData.getClientType())) {
            responseTab = createAndroidResponseTab(resData);
        }
        return responseTab;
    }
    
    /**
     * 创建请求JSON单项目信息.
     * 
     * @param resData 请求信息
     * @return 单项目信息
     */
    private static String createJsonResponseTab(AbstractResponseData resData) {
        sLog.debug("createJsonResponseTab", "start");
        final JSONObject requestJson = new JSONObject();
        ResponseTab[] tab = null;
        if (resData != null) {
            final JSONObject headerJson = createJsonResponseHeader(resData);
            if (headerJson != null) {
                requestJson.put(ConstantsUtil.Json.HEADER, headerJson);
            }
            if (((ResponseData)resData).getResTab() != null) {
                tab = (ResponseTab[])((ResponseData)resData).getResTab();
            }
            if (tab != null) {
                final JSONArray dataArray = new JSONArray();
                for (int nCnt = 0; nCnt < tab.length; nCnt++) {
                    final JSONObject data = new JSONObject();
                    data.put(ConstantsUtil.Json.TARGET_ID, tab[nCnt].getTargetId());
                    data.put(ConstantsUtil.Json.TARGET_INDEX, tab[nCnt].getTargetIndex());
                    data.put(ConstantsUtil.Json.EFFECT, tab[nCnt].getEffect());
                    data.put(ConstantsUtil.Json.VALUE, tab[nCnt].getValue());
                    data.put(ConstantsUtil.Json.TEXT, tab[nCnt].getText());
                    data.put(ConstantsUtil.Json.DATA_TYPE, tab[nCnt].getDataType());
                    data.put(ConstantsUtil.Json.BG_COLOR, tab[nCnt].getBgColor());
                    data.put(ConstantsUtil.Json.CHAR_COLOR, tab[nCnt].getCharColor());
                    data.put(ConstantsUtil.Json.ERR_MSG_COLOR, tab[nCnt].getErrMsgColor());
                    data.put(ConstantsUtil.Json.DISP_STAT, tab[nCnt].getDispStat());
                    data.put(ConstantsUtil.Json.MAX_LEN, tab[nCnt].getMaxLen());
                    data.put(ConstantsUtil.Json.DETAIL_CODE, tab[nCnt].getDetailCode());
                    data.put(ConstantsUtil.Json.DETAIL_MSG, tab[nCnt].getDetailMsg());
                    data.put(ConstantsUtil.Json.ITEM_NAME, tab[nCnt].getItemName());
                    dataArray.add(data);
                }
                requestJson.put(ConstantsUtil.Json.DATAS, dataArray);
            }
        }
        sLog.debug("createJsonResponseTab", "end", "responseTab = " + requestJson.toString());
        return requestJson.toString();
    }
    
    /**
     * 创建请求JSON单项目信息.
     * 
     * @param resData 请求信息
     * @return 单项目信息
     * @throws EngineCommonException EngineCommonException
     */
    public static Map<String, String> getDataTab(AbstractResponseData resData) throws EngineCommonException {
        final Map<String, String> data = new HashMap<String, String>();
        if (resData != null) {
            ResponseTab[] tab = null;
            final JSONObject headerJson = createJsonResponseHeader(resData);
            if (headerJson != null) {
                if (!"0".equals(headerJson.get(ConstantsUtil.Json.ERROR_HEADER))) {
                    throw new EngineCommonException(EngineExceptionEnum.SYSTEM_ERROR);
                }
            }
            if (((ResponseData)resData).getResTab() != null) {
                tab = (ResponseTab[])((ResponseData)resData).getResTab();
            }
            if (tab != null) {
                for (int nCnt = 0; nCnt < tab.length; nCnt++) {
                    data.put(tab[nCnt].getTargetId(), tab[nCnt].getValue());
                }
            }
        }
        return data;
    }
    
    /**
     * 创建请求JSON单项目信息.
     * 
     * @param resData 请求信息
     * @return 单项目信息
     */
    private static String createAndroidResponseTab(AbstractResponseData resData) {
        sLog.debug("createAndroidResponseTab", "start");
        final JSONObject requestJson = new JSONObject();
        if (resData != null) {
            final JSONObject headerJson = createJsonResponseHeader(resData);
            if (headerJson != null) {
                requestJson.put(ConstantsUtil.Json.HEADER, headerJson);
            }
            final JSONArray dataArray = createAndroidResponseDatas(resData, headerJson);
            requestJson.put(ConstantsUtil.Json.DATAS, dataArray);
        }
        sLog.debug("createAndroidResponseTab", "end", "responseTab = " + requestJson.toString());
        return requestJson.toString();
    }

    /**
     * @Description: 
     * @author king
     * @since Jun 3, 2013 10:20:48 AM 
     * @version V1.0
     * @param resData resData
     * @param headerJson headerJson
     * @return JSONArray
     */
    private static JSONArray createAndroidResponseDatas(
            AbstractResponseData resData, final JSONObject headerJson) {
        ResponseTab[] tabs = null;
        if (((ResponseData)resData).getResTab() != null) {
            tabs = (ResponseTab[])((ResponseData)resData).getResTab();
        }
        if (tabs == null) {
            return null;
        }
        final JSONArray dataArray = new JSONArray();
        for (int nCnt = 0; nCnt < tabs.length; nCnt++) {
            final ResponseTab tab = tabs[nCnt];
            final JSONObject data = new JSONObject();
            data.put(ConstantsUtil.Json.TARGET_ID, tab.getTargetId());
            if (CommonUtil.toString(ResponseHeader.ERROR_HEADER_CODE).equals(
                    headerJson.get(ConstantsUtil.Json.ERROR_HEADER))) {
                data.put(ConstantsUtil.Json.DETAIL_CODE, tab.getDetailCode());
                data.put(ConstantsUtil.Json.DETAIL_MSG, tab.getDetailMsg());
            } else {
                data.put(ConstantsUtil.Json.VALUE, tab.getValue());
                data.put(ConstantsUtil.Json.LABEL, tab.getText());
                final Map<String, Object> dataMap = tab.getDataMap();
                if (dataMap != null) {
                    for (String key : dataMap.keySet()) {
                        data.put(key, dataMap.get(key));
                    }
                }
            }
            dataArray.add(data);
        }
        return dataArray;
    }
    
    /**
     * 创建请求AJAX单项目信息.
     * 
     * @param resData 请求信息
     * @return 单项目信息
     */
    private static String createAjaxResponseTab(AbstractResponseData resData) {
        sLog.debug("createAjaxResponseTab", "start");
        final StringBuilder responseTabInnerHtml = new StringBuilder();
        ResponseTab[] tab = null;
        if (resData != null) {
            if (resData instanceof ResponseData && ((ResponseData)resData).getResTab() != null) {
                tab = (ResponseTab[])((ResponseData)resData).getResTab();
            }
            if (tab != null) {
                for (int nCnt = 0; nCnt < tab.length; nCnt++) {
                    final ResponseTab tabItem = tab[nCnt];
                    responseTabInnerHtml.append(RESPONSE_TAB_BEGIN_TAG);
                    responseTabInnerHtml.append(" targetId=\"" + tabItem.getTargetId() + "\""); 
                    responseTabInnerHtml.append(" targetIndex=\"" + tabItem.getTargetIndex() + "\""); 
                    responseTabInnerHtml.append(" effect=\"" + tabItem.getEffect() + "\""); 
                    responseTabInnerHtml.append(" value=\"" + tabItem.getValue() + "\""); 
                    responseTabInnerHtml.append(" text=\"" + tabItem.getText() + "\""); 
                    responseTabInnerHtml.append(" dataType=\"" + tabItem.getDataType() + "\""); 
                    responseTabInnerHtml.append(" bgColor=\"" + tabItem.getBgColor() + "\""); 
                    responseTabInnerHtml.append(" charColor=\"" + tabItem.getCharColor() + "\""); 
                    responseTabInnerHtml.append(" errMsgColor=\"" + tabItem.getErrMsgColor() + "\""); 
                    responseTabInnerHtml.append(" dispStat=\"" + tabItem.getDispStat() + "\""); 
                    responseTabInnerHtml.append(" maxLen=\"" + tabItem.getMaxLen() + "\""); 
                    responseTabInnerHtml.append(" detailCode=\"" + tabItem.getDetailCode() + "\""); 
                    responseTabInnerHtml.append(" detailMsg=\"" + tabItem.getDetailMsg() + "\""); 
                    responseTabInnerHtml.append(" itemName=\"" + tabItem.getItemName() + "\""); 
                    if (tabItem.isSelected()) {
                        responseTabInnerHtml.append(" selected=\"true\"");
                    } else {
                        responseTabInnerHtml.append(" selected=\"\"");
                    }
                    responseTabInnerHtml.append(WIDGET_END_TAG);
                }
            }
        }
        sLog.debug("createAjaxResponseTab", "end", "responseTab = " + responseTabInnerHtml.toString());
        return responseTabInnerHtml.toString();
    }
    
    /**
     * 创建请求多项目信息.
     * 
     * @param resData 请求信息
     * @return 多项目信息
     * @throws RichClientWebException 
     */
    public static String createResponseList(AbstractResponseData resData) {
        sLog.debug("createResponseList", "start", 
                "resData = " + resData, "type = " + resData.getClientType());
        String responseData = "";
        if (ConstantsUtil.Client.FLEX_TYPE.equals(resData.getClientType())) {
            responseData = createJsonResponseList(resData);
        } else if (ConstantsUtil.Client.ANDROID_TYPE.equals(resData.getClientType())) {
            responseData = createAndroidResponseList(resData);
        } else if (ConstantsUtil.Client.AUTO_COMPLETE.equals(resData.getClientType())) {
            responseData = createJsonList(resData).toString();
        }
        sLog.debug("createResponseList", "end", "responseData = " + responseData);
        return responseData;
    }
    
    /**
     * 创建请求JSON多项目信息.
     * 
     * @param resData 请求信息
     * @return 多项目信息
     * @throws EngineCommonException EngineCommonException
     */
    public static List<Map<String, String>> getDataList(AbstractResponseData resData) 
        throws EngineCommonException {
        final List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
        if (resData != null) {
            final JSONObject headerJson = createJsonResponseHeader(resData);
            if (headerJson != null) {
                if (!"0".equals(headerJson.get(ConstantsUtil.Json.ERROR_HEADER))) {
                    throw new EngineCommonException(EngineExceptionEnum.SYSTEM_ERROR);
                }
            }
            final ResponseList list = resData.getDataList();
            for (int i = 0; i < list.size(); i++) {
                final Map<String, String> data = new HashMap<String, String>();
                final ResponseLine resLine = list.getLine(i);
                if (resLine != null) {
                    final Map<String, ResponseTab> line = resLine.getLine();
                    for (String lineKey : line.keySet()) {
                        if (ConstantsUtil.Widget.CMB == resData.getDataType()) {
                            data.put(ConstantsUtil.Json.LABEL, getText(resLine.get(lineKey)));
                            data.put(ConstantsUtil.Json.DATA, getXmlValue(resLine.get(lineKey)));
                        } else {
                            data.put(lineKey, getXmlValue(resLine.get(lineKey)));
                        }
                    }
                    dataList.add(data);
                }
            }
        }
        return dataList;
    }
    
    /**
     * 创建请求JSON多项目信息.
     * 
     * @param resData 请求信息
     * @return 多项目信息
     */
    private static String createJsonResponseList(AbstractResponseData resData) {
        sLog.debug("createJsonResponseList", "start");
        final JSONObject responseListJson = new JSONObject();
        if (resData != null) {
            final JSONObject headerJson = createJsonResponseHeader(resData);
            if (headerJson != null) {
                responseListJson.put(ConstantsUtil.Json.HEADER, headerJson);
            }
            final JSONArray datasJson = new JSONArray();
            if (resData.getResMap() != null) {
                final Map<String, AbstractResponseData> resMap = resData.getResMap();
                for (String key : resMap.keySet()) {
                    final AbstractResponseData resDataTab = resMap.get(key);
                    final JSONObject dataJson = createJsonListForItem(resDataTab, key);
                    if (dataJson != null) {
                        datasJson.add(dataJson);
                    }
                }
            } else {
                final JSONObject dataJson = createJsonListForItem(resData, resData.getTargetId());
                if (dataJson != null) {
                    datasJson.add(dataJson);
                }
            }
            responseListJson.put(ConstantsUtil.Json.DATAS, datasJson);
        }
        sLog.debug("createJsonResponseList", "end", "responseData = " + responseListJson.toString());
        return responseListJson.toString();
    }
    
    /**
     * 创建请求JSON多项目信息.
     * 
     * @param resData 请求信息
     * @return 多项目信息
     */
    private static String createAndroidResponseList(AbstractResponseData resData) {
        sLog.debug("createAndroidResponseList", "start");
        final JSONObject responseListJson = new JSONObject();
        if (resData != null) {
            final JSONObject headerJson = createJsonResponseHeader(resData);
            String dataKind = ConstantsUtil.Str.EMPTY;
            if (headerJson != null) {
                responseListJson.put(ConstantsUtil.Json.HEADER, headerJson);
                dataKind = headerJson.getString(ConstantsUtil.Json.DATA_KIND_HEADER);
            }
            final JSONArray datasJson = new JSONArray();
            if (CommonUtil.isNotEmpty(dataKind) 
                    && ConstantsUtil.DataType.COMPLEX.equals(dataKind)) {
                final JSONArray dataArray = createAndroidResponseDatas(resData, headerJson);
                responseListJson.put(ConstantsUtil.Json.ITEM, dataArray);
            }
            if (resData.getResMap() != null) {
                final Map<String, AbstractResponseData> resMap = resData.getResMap();
                for (String key : resMap.keySet()) {
                    final AbstractResponseData resDataTab = resMap.get(key);
                    final JSONObject dataJson = createAndroidListForItem(resDataTab, key);
                    if (dataJson != null) {
                        datasJson.add(dataJson);
                    }
                }
            } else {
                final JSONObject dataJson = createAndroidListForItem(resData, resData.getTargetId());
                if (dataJson != null) {
                    datasJson.add(dataJson);
                }
            }
            responseListJson.put(ConstantsUtil.Json.DATAS, datasJson);
        }
        sLog.debug("createAndroidResponseList", "end", "responseData = " + responseListJson.toString());
        return responseListJson.toString();
    }
    
    /**
     * 创建请求JSON多项目信息.
     * 
     * @param resDataTab 请求信息
     * @param key 控件ID
     * @return 多项目信息
     */
    private static JSONObject createJsonListForItem(AbstractResponseData resDataTab, String key) {
        sLog.debug("createJsonListForItem", "start", "resDataTab = " + resDataTab, "key = " + key);
        JSONObject dataJson = null;
        if (resDataTab != null) {
            dataJson = new JSONObject();
            final ResponseList list = resDataTab.getDataList();
            if (list != null) {
                dataJson.put(ConstantsUtil.Json.TARGET_ID, key);
                dataJson.put(ConstantsUtil.Json.DATA_TYPE, resDataTab.getDataType());
                final JSONArray datasJson = createJsonList(resDataTab);
                dataJson.put(ConstantsUtil.Json.DATAS, datasJson);
            }
        }
        sLog.debug("createJsonListForItem", "end", "dataJson = " + dataJson);
        return dataJson;
    }
    
    /**
     * 创建请求JSON多项目信息.
     * 
     * @param resDataTab 请求信息
     * @param key 控件ID
     * @return 多项目信息
     */
    private static JSONObject createAndroidListForItem(AbstractResponseData resDataTab, String key) {
        sLog.debug("createAndroidListForItem", "start", "resDataTab = " + resDataTab, "key = " + key);
        JSONObject dataJson = null;
        if (resDataTab != null) {
            dataJson = new JSONObject();
            final ResponseList list = resDataTab.getDataList();
            if (list != null) {
                dataJson.put(ConstantsUtil.Json.TARGET_ID, key);
                dataJson.put(ConstantsUtil.Json.DATA_TYPE, resDataTab.getDataType());
                final JSONArray datasJson = createAndroidList(resDataTab);
                dataJson.put(ConstantsUtil.Json.DATAS, datasJson);
            }
        }
        sLog.debug("createAndroidListForItem", "end", "dataJson = " + dataJson);
        return dataJson;
    }

    /**
     * 创建请求JSON多项目信息.
     * @param resDataTab 请求信息
     * @param list
     * @return 多项目信息
     */
    private static JSONArray createJsonList(AbstractResponseData resDataTab) {
        sLog.debug("createJsonList", "start");
        final JSONArray datasJson = new JSONArray();
        final ResponseList list = resDataTab.getDataList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final JSONObject data = new JSONObject();
                final ResponseLine resLine = list.getLine(i);
                if (resLine != null) {
                    final Map<String, ResponseTab> line = resLine.getLine();
                    for (String lineKey : line.keySet()) {
                        if (ConstantsUtil.Widget.CMB == resDataTab.getDataType()) {
                            data.put(ConstantsUtil.Json.LABEL, getText(resLine.get(lineKey)));
                            data.put(ConstantsUtil.Json.DATA, getXmlValue(resLine.get(lineKey)));
                        } else {
                            data.put(lineKey, getXmlValue(resLine.get(lineKey)));
                        }
                    }
                    datasJson.add(data);
                }
            }
        }
        sLog.debug("createJsonList", "end", "datasJson = " + datasJson);
        return datasJson;
    }
    
    /**
     * 创建请求JSON多项目信息.
     * @param resDataTab 请求信息
     * @param list
     * @return 多项目信息
     */
    private static JSONArray createAndroidList(AbstractResponseData resDataTab) {
        sLog.debug("createAndroidList", "start");
        final JSONArray datasJson = new JSONArray();
        final ResponseList list = resDataTab.getDataList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final JSONObject data = new JSONObject();
                final ResponseLine resLine = list.getLine(i);
                if (resLine != null) {
                    final Map<String, ResponseTab> line = resLine.getLine();
                    for (String lineKey : line.keySet()) {
                        if (ConstantsUtil.Widget.CMB == resDataTab.getDataType()) {
                            data.put(ConstantsUtil.Json.LABEL, getText(resLine.get(lineKey)));
                            data.put(ConstantsUtil.Json.DATA, getXmlValue(resLine.get(lineKey)));
                        } else {
                            data.put(lineKey, getXmlValue(resLine.get(lineKey)));
                        }
                    }
                    datasJson.add(data);
                }
            }
        }
        sLog.debug("createAndroidList", "end", "datasJson = " + datasJson);
        return datasJson;
    }
    
    /**
     * 创建组控件信息.
     * 
     * @param resTab 请求信息
     * @param index 控件ID序号
     * @return 组控件信息
     */
    public static String createHtml(ResponseTab resTab, String index) {
        sLog.debug("createHtml", "start", "resTab = " + resTab, "index = " + index);
        String htmlContent = ConstantsUtil.Str.EMPTY;
        if (resTab.getDataType() < 100) {
            htmlContent = createHtmlByResponse(resTab, index);
        } else {
            final int type1 = resTab.getDataType() / 100;
            final int type2 = resTab.getDataType() % 100;
            final String targetId = resTab.getTargetId();
            final ResponseTab resTab1 = resTab;
            resTab1.setTargetId(targetId);
            final ResponseTab resTab2 = resTab;
            resTab2.setTargetId(targetId);
            htmlContent += createHtmlByResponse(resTab, type1, index);
            htmlContent += createHtmlByResponse(resTab, type2, index);
        }
        sLog.debug("createHtml", "end", "htmlContent = " + htmlContent);
        return htmlContent;
    }
    
    /**
     * 创建控件信息.
     * 
     * @param resTab 请求信息
     * @param index 控件ID序号
     * @return 组控件信息
     */
    public static String createHtmlByResponse(ResponseTab resTab, String index) {
        sLog.debug("createHtmlByResponse", "start", "resTab = " + resTab, "index = " + index);
        String htmlContent = ConstantsUtil.Str.EMPTY;
        htmlContent = createHtmlByResponse(resTab, resTab.getDataType(), index);
        sLog.debug("createHtmlByResponse", "end", "htmlContent = " + htmlContent);
        return htmlContent;
    }
    
    /**
     * 创建控件信息.
     * 
     * @param resTab 请求信息
     * @param type 控件类型
     * @param index 控件ID序号
     * @return 组控件信息
     */
    public static String createHtmlByResponse(ResponseTab resTab, int type, String index) {
        sLog.debug("createHtmlByResponse", "start", 
                "resTab = " + resTab, "type = " + type, "index = " + index);
        String htmlContent = ConstantsUtil.Str.EMPTY;
        switch (type) {
            case ConstantsUtil.Widget.TEXT:
                htmlContent = "<span " + getID(resTab, index) + ">" + getValue(resTab) + "</span>";
                break;
            case ConstantsUtil.Widget.TEXTBOX:
                htmlContent = "<input type=\"text\" " + getID(resTab, index) + "value=\"" + getValue(resTab) + "\" />";
                break;
            case ConstantsUtil.Widget.RADIO:
                htmlContent = "<input type=\"radio\" " + getID(resTab, index) + "value=\"" + getValue(resTab)
                    + "\" /><span>" + getText(resTab) + "</span>";
                break;
            case ConstantsUtil.Widget.CHK:
                htmlContent = "<input type=\"checkbox\" " + getID(resTab, index) + "value=\"" + getValue(resTab) 
                    + "\" /><span>" + getText(resTab) + "</span>";
                break;
            default:
                break;
        }
        sLog.debug("createHtmlByResponse", "end", "htmlContent = " + htmlContent);
        return htmlContent;
    }
    
    /**
     * 取得控件的值.
     * 
     * @param resTab 请求信息
     * @return 控件的值
     */
    public static String getXmlValue(ResponseTab resTab) {
        final StringBuilder sb = new StringBuilder();
        
        if (resTab.getEffect() == ConstantsUtil.Status.EFFECT_YES) {
            sb.append(resTab.getValue());
        }
        return sb.toString();
    }
    
    /**
     * 取得控件的ID.
     * 
     * @param resTab 请求信息
     * @return 控件的ID
     */
    public static String getID(ResponseTab resTab) {
        return getID(resTab, resTab.getTargetIndex());
    }
    
    /**
     * 取得控件的ID.
     * 
     * @param resTab 请求信息
     * @param index 控件序号
     * @return 控件的ID
     */
    public static String getID(ResponseTab resTab, String index) {
        final String targetId = resTab.getTargetId();
        final String targetIdIndex = index;
        if ((resTab.getDataType() == ConstantsUtil.Widget.IMG)) {
            if (CommonUtil.isNotEmpty(targetIdIndex)) {
                return " id=\"" + targetId + ConstantsUtil.Str.TARGETID_SUFFIX + targetIdIndex + "\"";
            } else {
                return " id=\"" + targetId + "\"";
            }
        }
        
        String str = ConstantsUtil.Str.EMPTY;
        if (CommonUtil.isNotEmpty(targetIdIndex)) {
            str = " name=\"" + targetId + "\" id=\"" 
                + targetId + ConstantsUtil.Str.TARGETID_SUFFIX + targetIdIndex + "\"";
        } else {
            str = " name=\"" + targetId + "\" id=\"" + targetId + "\"";
        }
        return str;
    }
    
    /**
     * 取得控件的ID.
     * 
     * @param targetId 控件ID
     * @param index 控件序号
     * @return 控件的ID
     */
    public static String getID(String targetId, String index) {
        
        String str = ConstantsUtil.Str.EMPTY;
        if (CommonUtil.isNotEmpty(index)) {
            str = " name=\"" + targetId + "\" id=\"" 
                + targetId + ConstantsUtil.Str.TARGETID_SUFFIX + index + "\"";
        } else {
            str = " name=\"" + targetId + "\" id=\"" + targetId + "\"";
        }
        return str;
    }

    /**
     * 取得控件的值.
     * 
     * @param resTab 请求信息
     * @return 控件的值
     */
    public static String getValue(ResponseTab resTab) {
        final StringBuilder sb = new StringBuilder();
        
        if (resTab.getEffect() == ConstantsUtil.Status.EFFECT_YES) {
            sb.append(resTab.getValue());
//            if (resTab.getDataType() == ConstantsUtil.Widget.TEXT) {
//            } else {
//                sb.append(" value=\"").append(resTab.getValue()).append("\"");
//            }
        }
        return sb.toString();
    }
    
    /**
     * 取得控件的显示的值.
     * 
     * @param resTab 请求信息
     * @return 控件的显示的值
     */
    public static String getText(ResponseTab resTab) {
        final StringBuilder sb = new StringBuilder();
        
        if (resTab.getEffect() == ConstantsUtil.Status.EFFECT_YES) {
            sb.append(resTab.getText());
        }
        return sb.toString();
    }
    
    /**
     * 取得控件的显示的值.
     * 
     * @param resTab 请求信息
     * @return 控件的显示的值
     */
    public static String getSelected(ResponseTab resTab) {
        final StringBuilder sb = new StringBuilder();
        
        if (resTab.getEffect() == ConstantsUtil.Status.EFFECT_YES) {
            if (resTab.isSelected()) {
                sb.append(" selected");
            }
        }
        return sb.toString();
    }

    /**
     * 创建下拉框.
     * 
     * @param resTab 请求信息
     * @param optionTabs 下拉框的内容
     * @return 下拉框
     */
    public static String makeSelectOptions(ResponseTab resTab, ResponseTab[] optionTabs) {

        final StringBuilder sb = new StringBuilder();
        
        if (optionTabs != null) {
            
            for (int i = 0; i < optionTabs.length; i++) {
                final ResponseTab optionTab = optionTabs[i];
                if (optionTab.getDataType() == ConstantsUtil.Widget.OPTION) {
                    
                    if (optionTab.getEffect() == ConstantsUtil.Status.EFFECT_YES) {
                        sb.append("<option value=" + optionTab.getValue());
                        if (resTab.getEffect() == ConstantsUtil.Status.EFFECT_YES) {
                            if (resTab.getValue().equals(optionTab.getValue())) {
                                sb.append(" selected");
                            }
                        }
                        sb.append(">" + optionTab.getText() + "</option>");
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * 取得控件样式内容.
     * 
     * @param resTab 请求信息
     * @param style 控件样式
     * @return 样式内容
     */
    public static String getStyle(ResponseTab resTab, String style) {
        final StringBuilder sb = new StringBuilder();
        final String bgColor = resTab.getBgColor();
        final String charColor = resTab.getCharColor();
        final String charWeight = resTab.getCharWeight();

        sb.append(" style=\"");
        sb.append(style);
        if (resTab.getDispStat() == ConstantsUtil.Status.HIDE) {
            sb.append("display:none; ");
        } else {
            if (!ConstantsUtil.Status.BG_COLOR_DEFAULT.equals(bgColor)) {
                sb.append("background-color:").append(bgColor).append("; ");
            }
            if (!ConstantsUtil.Status.CHAR_COLOR_DEFAULT.equals(charColor)) {
                sb.append("color:").append(charColor).append("; ");
            }
            if (!ConstantsUtil.Status.CHAR_WEIGHT_DEFAULT.equals(charColor)) {
                sb.append("font-weight:").append(charWeight).append("; ");
            }
            sb.append("display:inline; ");
        }
        sb.append("\"");
        return sb.toString();
    }

    /**
     * 取得控件样式内容.
     * 
     * @param resTab 请求信息
     * @param style 控件样式
     * @return 样式内容
     */
    public static String getHtmlTagProperties(ResponseTab resTab, String style) {
        final StringBuilder sb = new StringBuilder();
        sb.append(getDisabled(resTab));
        sb.append(getStyle(resTab, style));
        return sb.toString();
    }
    
    /**
     * 取得控件样式内容.
     * 
     * @param resTab 请求信息
     * @param index 行序号
     * @return 样式内容
     * @throws RichClientWebException RichClientWebException
     */
    public static String getHtmlTagProperties(ResponseTab resTab, int index) 
        throws RichClientWebException {
        return getHtmlTagProperties(resTab, getStyleAlternateColor(index));
    }

    /**
     * 取得控件样式内容.
     * 
     * @param resTab 请求信息
     * @return 样式内容
     */
    public static String getStyle(ResponseTab resTab) {
        return getStyle(resTab, "");
    }

    /**
     * 取得控件样式内容.
     * 
     * @param resTab 请求信息
     * @return 样式内容
     */
    public static String getDisabled(ResponseTab resTab) {
        final StringBuilder sb = new StringBuilder();
        if (resTab.getDispStat() == ConstantsUtil.Status.READ) {
            if (resTab.getDataType() == ConstantsUtil.Widget.TEXTBOX) {
                sb.append(" readonly");
            } else {
                sb.append(" disabled");
            }
        }
        return sb.toString();
    }

    /**
     * 取得列表间隔色.
     * 
     * @param index 列表序号
     * @return 列表间隔色
     * @throws RichClientWebException RichClientWebException
     */
    public static String getAlternateColor(int index) throws RichClientWebException {
        final Configuration config = ControlConfig.getInstance().getConfiguration();
        return getAlternateColor(index, config.getColumnBackColor(), config.getColumnBackColorAlt());
    }
    
    /**
     * 取得列表间隔色.
     * 
     * @param index 列表序号
     * @return 列表间隔色
     * @throws RichClientWebException RichClientWebException
     */
    public static String getStyleAlternateColor(int index) throws RichClientWebException {
        final Configuration config = ControlConfig.getInstance().getConfiguration();
        return getStyleAlternateColor(index, config.getColumnBackColor(), config.getColumnBackColorAlt());
    }

    /**
     * 取得列表间隔色.
     * 
     * @param index 列表序号
     * @param color1 颜色1
     * @param color2 颜色2
     * @return 列表间隔色
     */
    public static String getAlternateColor(int index, String color1, String color2) {
        final StringBuilder sb = new StringBuilder();
        sb.append(" bgcolor=\"");
        if (index % 2 == 0) {
            sb.append(color1);
        } else {
            sb.append(color2);
        }
        sb.append("\"");
        return sb.toString();
    }
    
    /**
     * 取得列表间隔色.
     * 
     * @param index 列表序号
     * @param color1 颜色1
     * @param color2 颜色2
     * @return 列表间隔色
     */
    public static String getStyleAlternateColor(int index, String color1, String color2) {
        final StringBuilder sb = new StringBuilder();
        if (index % 2 == 0) {
            sb.append(" background-color:").append(color1).append("; ");
        } else {
            sb.append(" background-color:").append(color2).append("; ");
        }
        return sb.toString();
    }
    
    /**
     * 取得控件的值.
     * 
     * @param resTab 请求信息
     * @return 控件的值
     */
    public static String getKeyValue(ResponseTab resTab) {
        return resTab.getValue();
    }
    
    /**
     * 是否有错误信息.
     * @param resTabs 处理返回值
     * @return 检查结果
     */
    public static boolean hasErrorCheck(ResponseTab[] resTabs) {
        boolean hasError = false;
        if (resTabs != null) {
            for (int i = 0; i < resTabs.length; i++) {
                if (!ConstantsUtil.Check.DEFAULT.equals(resTabs[i].getDetailCode())) {
                    hasError = true;
                    break;
                }
            }
        }
        return hasError;
        
    }
}
