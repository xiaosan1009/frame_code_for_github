package com.richClientFrame.handler;

import com.richClientFrame.check.CommonChecker;
import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.check.ErrTabBean;
import com.richClientFrame.data.db.PageBean;
import com.richClientFrame.data.http.HttpService;
import com.richClientFrame.data.mail.MailBean;
import com.richClientFrame.data.mail.MailSender;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.param.RequestParam;
import com.richClientFrame.data.param.UploadParam;
import com.richClientFrame.data.param.RequestParam.BackgroundColor;
import com.richClientFrame.data.param.RequestParam.Cell;
import com.richClientFrame.data.param.RequestParam.Checker;
import com.richClientFrame.data.param.RequestParam.Cmb;
import com.richClientFrame.data.param.RequestParam.Compare;
import com.richClientFrame.data.param.RequestParam.Complex;
import com.richClientFrame.data.param.RequestParam.Condition;
import com.richClientFrame.data.param.RequestParam.Dimension;
import com.richClientFrame.data.param.RequestParam.Display;
import com.richClientFrame.data.param.RequestParam.Excel;
import com.richClientFrame.data.param.RequestParam.External;
import com.richClientFrame.data.param.RequestParam.Format;
import com.richClientFrame.data.param.RequestParam.Item;
import com.richClientFrame.data.param.RequestParam.Items;
import com.richClientFrame.data.param.RequestParam.Lists;
import com.richClientFrame.data.param.RequestParam.Mail;
import com.richClientFrame.data.param.RequestParam.Mails;
import com.richClientFrame.data.param.RequestParam.Result;
import com.richClientFrame.data.param.RequestParam.Row;
import com.richClientFrame.data.param.RequestParam.Sheet;
import com.richClientFrame.data.param.RequestParam.Style;
import com.richClientFrame.data.param.RequestParam.Taber;
import com.richClientFrame.data.param.RequestParam.TextColor;
import com.richClientFrame.data.param.RequestParam.Update;
import com.richClientFrame.data.param.RequestParam.Updates;
import com.richClientFrame.data.param.RequestParam.Upload;
import com.richClientFrame.data.param.RequestParamFace.IActionStep;
import com.richClientFrame.data.param.RequestParamFace.IChecker;
import com.richClientFrame.data.param.RequestParamFace.ICondition;
import com.richClientFrame.data.param.RequestParamFace.IExcel;
import com.richClientFrame.data.param.RequestParamFace.IExternal;
import com.richClientFrame.data.param.RequestParamFace.IFormat;
import com.richClientFrame.data.param.RequestParamFace.IList;
import com.richClientFrame.data.param.RequestParamFace.ISingle;
import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseBean;
import com.richClientFrame.data.response.ResponseData;
import com.richClientFrame.data.response.ResponseDimensions;
import com.richClientFrame.data.response.ResponseExcel;
import com.richClientFrame.data.response.ResponseHeader;
import com.richClientFrame.data.response.ResponseItemBean;
import com.richClientFrame.data.response.ResponseLine;
import com.richClientFrame.data.response.ResponseList;
import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.data.response.TabRowGroup;
import com.richClientFrame.data.response.TabRowMapDimensions;
import com.richClientFrame.data.response.TabRowMapList;
import com.richClientFrame.data.response.common.CommonPageListInfo;
import com.richClientFrame.data.response.data.ResponseHeaderData;
import com.richClientFrame.exception.InputException;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.handler.iface.IActionFace;
import com.richClientFrame.handler.iface.IDataSourceFace;
import com.richClientFrame.handler.iface.IExpandFace;
import com.richClientFrame.handler.iface.IExternalFace;
import com.richClientFrame.handler.iface.IFormatFace;
import com.richClientFrame.info.ContorlMemcached;
import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.info.ControlDispField;
import com.richClientFrame.info.ControlRequestMap;
import com.richClientFrame.info.UserInfo;
import com.richClientFrame.service.IDbService;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.DateUtils;
import com.richClientFrame.util.ExcelUtil;
import com.richClientFrame.util.FileUtil;
import com.richClientFrame.util.HtmlUtil;
import com.richClientFrame.util.LogUtil;
import com.richClientFrame.util.PageUtil;
import com.richClientFrame.util.xml.RequestConfigUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： AbstractHandler
 * 类描述 ： 引擎共通处理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2010.03.24
 * @author king
 */
public class AbstractHandler {
    
    private static final LogUtil LOG = new LogUtil(AbstractHandler.class, true);
    
    private IActionFace mAction;
    
    private IFormatFace mFormat;
    
    private IDataSourceFace mDataSource;
    
    private IExternalFace mExecute;
    
    private ContorlMemcached mMemcached;
    
    private IDbService db;
    
    private HtmlTabHandler mHtmlTab;
    
    private IExpandFace mExpand;
    
    private ServletContext context;
    
    private HttpServletRequest request;
    
    private HttpServletResponse response;
    
    private SessionData session;
    
    private MailSender mMailSender;
    
    private org.apache.poi.ss.usermodel.Sheet sheetItem;
    
    private org.apache.poi.ss.usermodel.Row rowItem;
    
    private org.apache.poi.ss.usermodel.Cell cellItem;

    /**
     * 构造函数.
     */
    public AbstractHandler() {
        super();
    }
    
    /**
     * 画面初始化方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     */
    public AbstractResponseData showPage(Param param, SessionData seData)
        throws RichClientWebException {
        return showPagePri(param, seData);
    }
    
    /**
     * 画面单项目初始化方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public AbstractResponseData showItem(Param param, SessionData seData)
        throws RichClientWebException, InputException {
        return showItemPri(param, seData);
    }
    
    /**
     * 多项目方法.
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return response信息
     * @throws RichClientWebException RichClientWebException
     */
    public AbstractResponseData showList(Param param, SessionData seData) 
        throws RichClientWebException {
        return showListPriv(param, seData);
    }

    /**
     * 数据库增删改方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public AbstractResponseData update(Param param, SessionData seData)
        throws RichClientWebException, InputException {
        return updatePri(param, seData);
    }
    
    /**
     * 复合方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public AbstractResponseData showComplex(Param param, SessionData seData) 
        throws RichClientWebException, InputException {
        return showComplexPri(param, seData);
    }
    
    /**
     * @Description: 
     * @author king
     * @since Oct 9, 2013 4:56:14 PM 
     * @version V1.0
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public AbstractResponseData sendMail(Param param, SessionData seData) 
        throws RichClientWebException, InputException {
        return sendMailPri(param, seData);
    }

    /**
     * @Description: 
     * @author king
     * @since Oct 10, 2013 2:56:18 PM 
     * @version V1.0
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     */
    private ResponseData sendMailPri(Param param, SessionData seData) 
        throws RichClientWebException {
        // 画面对象属性容器数组
        final TableRowMap tabContent = new TableRowMap();
        final List<ResponseTab> tabList = new ArrayList<ResponseTab>();
        final List<Mail> mails = ControlRequestMap.getInstance().getMails(param).getMails();
        // 把request中的数据保存在执行sql所需的map中
        final Map<String, Object> whereMap = createCondition(param);
        for (int i = 0; i < mails.size(); i++) {
            final Mail mail = mails.get(i);
            final MailBean mailBean = new MailBean();
            mailBean.parseMail(param, seData, mail);
            // 由request生成查询条件
            final List<com.richClientFrame.data.param.RequestParam.Param> params = mail.getParams();
            // 加载在<params>标签中的数据，使之作用到数据查询条件中
            reloadParams(whereMap, params, param, seData, tabContent);
            final String url = mail.getUrl();
            if (CommonUtil.isNotEmpty(url)) {
                final com.richClientFrame.data.http.Result result = HttpService.doPost(url, whereMap);
                mailBean.setContent(result.getData());
            }
            mMailSender.sendHtmlMail(mailBean);
        }
        final ResponseTab[] responseTabs = tabList.toArray(new ResponseTab[0]);
        
        final ResponseData resData = makeHeader(param, EngineExceptionEnum.UR_COM_MAIL_SUCCESS);
        resData.setResTab(responseTabs);
        return resData;
    }

    /**
     * 画面初始化方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     */
    private AbstractResponseData showPagePri(Param param, SessionData seData) throws RichClientWebException {
        LOG.debug("showPage", "start");
        final AbstractResponseData resData = createShowPageContent(param, seData);
        LOG.debug("showPage", "end");
        return resData;
    }

    /**
     * @Description: 创建showPage的内容
     * @author king
     * @since Dec 3, 2012 9:31:36 AM 
     * @version V1.0
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return showPage的内容
     * @throws RichClientWebException RichClientWebException
     */
    private AbstractResponseData createShowPageContent(Param param, SessionData seData) 
        throws RichClientWebException {
        final AbstractResponseData resData = new AbstractResponseData();
        final Map<String, AbstractResponseData> resMap = new HashMap<String, AbstractResponseData>();
        createCmbShowPage(param, resMap);
        createCmdShowPage(param, seData, resMap);
        param.cmdCode = "0";
        resData.setResMap(resMap);
        return resData;
    }

    /**
     * @Description: 
     * @author king
     * @since Jul 17, 2013 2:35:09 PM 
     * @version V1.0
     * @param param param
     * @param seData seData
     * @param resMap resMap
     * @throws RichClientWebException RichClientWebException
     */
    private void createCmdShowPage(Param param, SessionData seData,
            final Map<String, AbstractResponseData> resMap) throws RichClientWebException {
        final List<String> cmdList = ControlRequestMap.getInstance().getInitializeList(param);
        if (cmdList != null && cmdList.size() > 0) {
            for (int i = 0; i < cmdList.size(); i++) {
                final String initializeCmd = cmdList.get(i);
                param.cmdCode = initializeCmd;
                final String targetId = ControlRequestMap.getInstance().getList(param).getTargetId();
                if (CommonUtil.isNotEmpty(targetId)) {
                    final AbstractResponseData initializeList = showListPriv(param, seData);
                    final ResponseHeader initializeHeader = initializeList.getResHeader();
                    final String initializePagesize = initializeHeader.getPageSize();
                    final String initializeTotalRows = initializeHeader.getTotalRows();
                    param.httpServletRequest.setAttribute("initializeTargetId", targetId);
                    param.httpServletRequest.setAttribute("initializeCmd", initializeCmd);
                    param.httpServletRequest.setAttribute("pagingPageSizeInit", initializePagesize);
                    param.httpServletRequest.setAttribute("pagingTotalRowsInit", initializeTotalRows);
                    resMap.put(targetId, initializeList);
                }
            }
        }
    }

    /**
     * @Description: 
     * @author king
     * @since Jul 17, 2013 2:34:26 PM 
     * @version V1.0
     * @param param param
     * @param resMap resMap
     * @throws RichClientWebException RichClientWebException
     */
    private void createCmbShowPage(Param param, final Map<String, AbstractResponseData> resMap)
        throws RichClientWebException {
        final java.util.List<Cmb> cmbs = ControlRequestMap.getInstance().getCmbs(param);
        if (cmbs != null & cmbs.size() > 0) {
            for (int j = 0; j < cmbs.size(); j++) {
                final String targetId = cmbs.get(j).getTargetId();
                resMap.put(targetId, createCmbInfoForShow(cmbs.get(j), param));
            }
        }
    }
    
    /**
     * 复合方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    private ResponseData showComplexPri(Param param, SessionData seData)
        throws RichClientWebException, InputException {
        LOG.debug("showComplex", "start");
        initializeHandler(param, seData);
        final TableRowMap tab = new TableRowMap();
        final List<ResponseTab> tabList = new ArrayList<ResponseTab>();
        
        // sql中的动态参数保存对象
        final Map<String, Object> whereMap = createCondition(param);
        
        final Complex complex = ControlRequestMap.getInstance().getComplex(param);
        final Items items = complex.getItems();
        if (items != null) {
            createItemContent(param, seData, items, tab, tabList);
        }
        Map<String, AbstractResponseData> resMap = null;
        PageBean pageBean = null;
        final Lists lists = complex.getLists();
        if (lists != null) {
            resMap = new HashMap<String, AbstractResponseData>();
            final List<RequestParam.List> listList = lists.getLists();
            for (int i = 0; i < listList.size(); i++) {
                final RequestParam.List listItem = listList.get(i);
                final String targetId = listItem.getTargetId();
                pageBean = createList(param, seData, whereMap, listItem);
                final List<TableRowMap> rowList = getListDatas(pageBean);
                // 调用外部对象取值
                executeExternal(listItem, rowList, param, seData);
                final String index = listItem.getIndex();
                
                if (mAction != null) {
                    mAction.onListPostExecute(rowList, index);
                }
                final ResponseList responseList = showList(rowList, param, seData, listItem);
                final AbstractResponseData responseData = new AbstractResponseData();
                responseData.setDataList(responseList);
                resMap.put(targetId, responseData);
                tab.put(targetId, rowList);
            }
        }
        readExcel(param, seData, complex, tab);
        final ResponseExcel resExcel = writeExcel(param, seData, complex, tab);
        final ResponseTab[] tabs = tabList.toArray(new ResponseTab[0]);
        
//        ResponseData resData = null;
//        if (!ConstantsUtil.Client.AJAX_TYPE.equals(param.clientType) && lists != null) {
//            final ResponseHeader header = new ResponseHeader(param, 
//                    ConstantsUtil.Result.BLANK, ConstantsUtil.DataType.LIST);
//            resData = new ResponseData(header);
//        } else {
//        }
        final ResponseHeaderData responseHeaderData = new ResponseHeaderData();
        responseHeaderData.setParam(param);
        responseHeaderData.setDataKind(ConstantsUtil.DataType.COMPLEX);
        ResponseHeader header = new ResponseHeader(responseHeaderData);
        if (CommonUtil.isNotEmpty(ControlRequestMap.getInstance().getForward(param))) {
            responseHeaderData.setDataKind(ConstantsUtil.DataType.SINGLE);
            responseHeaderData.setResCode(EngineExceptionEnum.UR_COM_PAGE_CHANGED.getCode());
            header = new ResponseHeader(responseHeaderData);
        }
        if (pageBean != null) {
            final String pageSizeStr = CommonUtil.toString(pageBean.getPageSize());
            header.setPageSize(pageSizeStr);
            final String totalRowsStr = CommonUtil.toString(pageBean.getTotalRows());
            header.setTotalRows(totalRowsStr);
        }
        final ResponseData resData = new ResponseData(header);
        final ResponseBean responseBean = new ResponseBean();
        responseBean.setItemBean(new ResponseItemBean(tab));
        if (resMap != null) {
            resData.setResMap(resMap);
        }
        resData.setResTab(tabs);
        if (resExcel != null) {
            resData.setResExcel(resExcel);
        }
        resData.setResponseBean(responseBean);
        LOG.debug("showComplex", "end");
        return resData;
    }
    
    /**
     * 画面单项目初始化方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    private ResponseData showItemPri(Param param, SessionData seData)
        throws RichClientWebException, InputException {
        LOG.debug("showItem", "start");
        initializeHandler(param, seData);
        final List<ResponseTab> tabList = new ArrayList<ResponseTab>();
        final Items items = ControlRequestMap.getInstance().getItems(param);
        final TableRowMap tab = new TableRowMap();
        // 创建单项目内容.
        final Item item = createItemContent(param, seData, items, tab, tabList);
        
        readExcel(param, seData, item, tab);
        final ResponseExcel resExcel = writeExcel(param, seData, item, tab);
        final ResponseTab[] tabs = tabList.toArray(new ResponseTab[0]);
        // 创建数据头
        final ResponseBean responseBean = new ResponseBean();
        responseBean.setItemBean(new ResponseItemBean(tab));
        final ResponseData resData = makeHeader(param, EngineExceptionEnum.UR_COM_BLANK);
        resData.setResTab(tabs);
        // 设置通过流的形式返回页面的信息
        setOutPut(tab, resData);
        if (resExcel != null) {
            resData.setResExcel(resExcel);
        }
        resData.setResponseBean(responseBean);
        LOG.debug("showItem", "end");
        return resData;
    }
    
    /**
     * 创建单项目内容.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param items 单项目数组容器
     * @param tabContent 单项目内容保存容器
     * @param tabList 单项目信息列表容器
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    private Item createItemContent(
            Param param, SessionData seData, Items items, TableRowMap tabContent, List<ResponseTab> tabList) 
        throws RichClientWebException, InputException {
        LOG.debug("createItemContent", "start");
        Item item = null;
        if (items == null) {
            return null;
        }
        final String id = items.getId();
        final List<Item> itemList = items.getItems();
        // 把request中的数据保存在执行sql所需的map中
        final Map<String, Object> whereMap = createCondition(param);
        if (itemList.size() > 0) {
            for (int i = 0; i < itemList.size(); i++) {
                item = itemList.get(i);
                // 对此次请求进行验证操作
                doCheck(param, seData, item, true, whereMap);
                // 判断此<item>节点是否需要执行，根据<condtion>标签来判断
                if (!judgeOperator(item, tabContent, param, seData)) {
                    continue;
                }
                final String sql = item.getSql();
                final String source = item.getSource();
                // 在执行sql之前进行处理
                final boolean isExecute = doActionPre(item, param, seData, whereMap, tabContent);
                // 多条数据的单项目
                if (item.isList()) {
                    final List<TableRowMap> list = new ArrayList<TableRowMap>();
                    String targetId = param.targetId;
                    if (CommonUtil.isNotEmpty(item.getTargetId())) {
                        targetId = item.getTargetId();
                    }
                    // 判断是否是固定值【true：固定值】
                    if (item.isResultConstant()) {
                        final TableRowMap tabResult = new TableRowMap();
                        tabResult.putAll(reloadResult(tabResult, item, param, seData));
                        list.add(tabResult);
                    }
                    if (CommonUtil.isNotEmpty(sql) && getDb() != null && isExecute) {
                        TableRowMap[] tabs = null;
                        if (CommonUtil.isEmpty(source)) {
                            tabs = getDb().querys(sql, whereMap);
                        } else {
                            tabs = getDb().get(source).querys(sql, whereMap);
                        }
                        if (tabs != null) {
                            list.addAll(Arrays.asList(tabs));
                        }
                    }
                    if (mAction != null) {
                        final String itemTargetId = mAction.onItemPostExecute(list, item.getIndex());
                        LOG.info("createItemContent", "单项目多条记录情况下，有自定义action", "itemTargetId = " + itemTargetId);
                        if (CommonUtil.isNotEmpty(itemTargetId)) {
                            targetId = itemTargetId;
                        }
                    }
                    executeExternal(item, list, param, seData);
                    showItemByItems(list, param, targetId, seData, tabList, item, tabContent);
                // 单条数据的单项目
                } else {
                    if (CommonUtil.isNotEmpty(sql) && getDb() != null && isExecute) {
                        TableRowMap tab = null;
                        if (CommonUtil.isEmpty(source)) {
                            tab = getDb().query(sql, whereMap);
                        } else {
                            tab = getDb().get(source).query(sql, whereMap);
                        }
                        if (tab != null) {
                            tabContent.putAll(tab.getDataMap());
                        }
                    }
                    // 把参数信息保存在返回画面的容器中
//                    keepParamsValues(param, seData, item, tabContent);
                    if (mAction != null) {
                        mAction.onItemPostExecute(tabContent, item.getIndex());
                    }
                    
                    executeExternal(item, tabContent, param, seData);
                    
//                    if (tab == null) {
//                        tab = reloadResult(tab, item, param, seData);
//                    } else {
//                    }
                    reloadResult(tabContent, item, param, seData);
                    showItemByItems(tabContent, param, seData, tabList);
                }
//                if (tab != null) {
//                    tabContent.putAll(tab.getDataMap());
//                    tabContent.setOutPut(tab.getOutPut());
//                }
            }
        } else {
            if (mAction != null) {
                mAction.onItemPreExecute(whereMap, id);
                mAction.onItemPostExecute(tabContent, id);
                showItemByItems(tabContent, param, seData, tabList);
//                if (tab != null) {
//                    tabContent.putAll(tab.getDataMap());
//                    tabContent.setOutPut(tab.getOutPut());
//                }
            }
        }
        setSingleDefault(param, tabList);
        LOG.debug("createItemContent", "end");
        return item;
    }
    
    /**
     * @Description: 当请求为single请求时，如果返回结果中不包含控件信息，则返回源数据，以清除之前的错误样式
     * @author king
     * @since Jun 8, 2013 10:58:35 AM 
     * @version V1.0
     * @param param param
     * @param tabList tabList
     * @throws RichClientWebException RichClientWebException
     */
    private void setSingleDefault(Param param, List<ResponseTab> tabList) 
        throws RichClientWebException {
        if (CommonUtil.isNotEmpty(param.targetId)) {
            boolean hasSingleTarget = false;
            for (int i = 0; i < tabList.size(); i++) {
                final ResponseTab tab = tabList.get(i);
                if (tab != null && param.targetId.equals(tab.getTargetId())) {
                    hasSingleTarget = true;
                    break;
                }
            }
            if (!hasSingleTarget) {
                final Map<String, String> types = ControlDispField.getInstance().getField(param.dispCode);
                final int type = getWidgetType(param.targetId, types);
                String value = param.get(param.targetId);
                if (param.targetId.indexOf(ConstantsUtil.Str.TARGETID_SUFFIX) != -1) {
                    final String targetId = param.targetId.split(ConstantsUtil.Str.TARGETID_SUFFIX)[0];
                    final String targetIdIndex = param.targetId.split(ConstantsUtil.Str.TARGETID_SUFFIX)[1];
                    if (CommonUtil.isNotEmpty(targetId) && CommonUtil.isNotEmpty(targetIdIndex)) {
                        final String[] valueStrings = param.getList(targetId);
                        if (valueStrings != null && valueStrings.length > Integer.parseInt(targetIdIndex)) {
                            value = valueStrings[Integer.parseInt(targetIdIndex)];
                        }
                    }
                }
                final ResponseTab responseTab = RequestConfigUtil.createItem(
                        param, type, param.targetId, value);
                tabList.add(responseTab);
            }
        }
    }

    /** 
    * @Description: 把参数信息保存在返回画面的容器中
    * @author king
    * @since Oct 7, 2012 9:03:45 AM 
    * 
    * @param param 客户端请求对象
    * @param seData session对象
    * @param action 事件对象
    * @param tab 容器对象
    * @throws RichClientWebException RichClientWebException
    */
//    private void keepParamsValues(Param param, SessionData seData, IActionStep action, TableRowMap tab)
//        throws RichClientWebException {
//        final List<com.richClientFrame.data.param.RequestParam.Param> params = action.getParams();
//        for (int j = 0; j < params.size(); j++) {
//            final com.richClientFrame.data.param.RequestParam.Param parameter = params.get(j);
//            if (parameter.isKeep()) {
//                if (judgeOperator(parameter, null, param, seData)) {
//                    createParams(tab, parameter, param, seData);
//                }
//            }
//        }
//    }

    /**
     * 共通action执行sql前方法.
     * 
     * @param action action对象
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param tab 数据
     * @return 是否执行sql
     * @throws RichClientWebException RichClientWebException
     */
    private boolean doActionPre(
            IActionStep action,
            Param param, 
            SessionData seData, 
            TableRowMap tab) 
        throws RichClientWebException {
        
        // 由request生成查询条件
        createCondition(tab, param);
        final List<com.richClientFrame.data.param.RequestParam.Param> params = action.getParams();
        reloadParams(tab.getDataMap(), params, param, seData);
        boolean isExecute = true;
        if (mAction != null) {
            if (action instanceof External) {
                isExecute = mAction.onExternalPreExecute(tab, action.getIndex());
            }
        }
        return isExecute;
    }
    
    /**
     * 共通action执行sql前方法.
     * 
     * @param action action对象
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param whereMap sql动态参数
     * @return 是否执行sql
     * @throws RichClientWebException RichClientWebException
     */
    private boolean doActionPre(
            IActionStep action,
            Param param, 
            SessionData seData, 
            Map<String, Object> whereMap) 
        throws RichClientWebException {
        
        return doActionPre(action, param, seData, whereMap, null);
    }
    
    /**
     * 共通action执行sql前方法.
     * 
     * @param action action对象
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param whereMap sql动态参数
     * @param tab 之前流程里数据查询的结果信息
     * @return 是否执行sql
     * @throws RichClientWebException RichClientWebException
     */
    private boolean doActionPre(
            IActionStep action,
            Param param, 
            SessionData seData, 
            Map<String, Object> whereMap, 
            TableRowMap tab) 
        throws RichClientWebException {
        
        return doActionPre(action, param, seData, whereMap, null, tab);
    }
    
    /**
     * 共通action执行sql前方法.
     * 
     * @param action action对象
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param upload 上传对象
     * @param whereMap sql动态参数
     * @param tab 之前流程里数据查询的结果信息
     * @return 是否执行sql
     * @throws RichClientWebException RichClientWebException
     */
    private boolean doActionPre(
            IActionStep action,
            Param param, 
            SessionData seData, 
            Map<String, Object> whereMap,
            UploadParam upload, 
            TableRowMap tab) 
        throws RichClientWebException {
        // 由request生成查询条件
        final List<com.richClientFrame.data.param.RequestParam.Param> params = action.getParams();
        // 加载在<params>标签中的数据，使之作用到数据查询条件中
        reloadParams(whereMap, params, param, seData, tab);
        boolean isExecute = true;
        if (mAction != null) {
            if (action instanceof Item) {
                isExecute = mAction.onItemPreExecute(whereMap, action.getIndex());
            } else if (action instanceof Checker) {
                isExecute = mAction.onCheckPreExecute(whereMap, action.getIndex());
            } else if (action instanceof RequestParam.List) {
                isExecute = mAction.onListPreExecute(whereMap, action.getIndex());
            } else if (action instanceof Condition) {
                isExecute = mAction.onConditionPreExecute(whereMap, action.getIndex());
            } else if (action instanceof Update) {
                isExecute = mAction.onUpdatePreExecute(whereMap, action.getIndex());
            } else if (action instanceof Upload) {
                isExecute = mAction.onUploadPreExecute(upload, whereMap, action.getIndex());
            }
        }
        return isExecute;
    }
    
    
    
    

    /**
     * 调用外部对象取值
     * @param externalFace 外部对象容器
     * @param tab 数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @throws RichClientWebException RichClientWebException
     */
    private void executeExternal(IExternal externalFace, TableRowMap tab, Param param, SessionData seData)
        throws RichClientWebException {
        final List<External> externals = externalFace.getExternals();
        if (externals != null && externals.size() > 0) {
            for (int j = 0; j < externals.size(); j++) {
                final External external = externals.get(j);
                final String method = external.getMethod();
                final boolean isExecute = doActionPre(external, param, seData, tab);
                if (mExecute != null && isExecute) {
                    mExecute.execute(tab, method);
                }
                if (mAction != null) {
                    mAction.onExternalPostExecute(tab, external.getIndex());
                }
                tab.putAll(reloadResult(tab, external, param, seData));
            }
        }
    }
        
    /**
     * 调用格式化对象取值
     * @param formatFace 外部对象容器
     * @param value 数据
     * @return 转换后的值
     * @throws RichClientWebException RichClientWebException
     */
    private Object executeFormat(IFormat formatFace, Object value)
        throws RichClientWebException {
        if (formatFace == null) {
            return value;
        }
        final List<Format> formats = formatFace.getFormats();
        Object returnValue = value;
        if (formats != null && formats.size() > 0) {
            String param = ConstantsUtil.Str.EMPTY;
            if (value instanceof String[]) {
                param = ((String[])value)[0];
            } else {
                param = CommonUtil.toString(value);
            }
            for (int j = 0; j < formats.size(); j++) {
                final Format format = formats.get(j);
                final String method = format.getMethod();
                final String params = format.getParams();
                if (mFormat != null) {
                    returnValue = mFormat.execute(param, method, params);
                }
            }
        }
        return returnValue;
    }
    
    /**
     * 调用外部对象取值
     * @param externalFace 外部对象容器
     * @param tabs 数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @throws RichClientWebException RichClientWebException
     */
    private void executeExternal(IExternal externalFace, List<TableRowMap> tabs, Param param, SessionData seData)
        throws RichClientWebException {
        final List<External> externals = externalFace.getExternals();
        if (externals != null && externals.size() > 0) {
            for (int j = 0; j < externals.size(); j++) {
                final External external = externals.get(j);
                final String method = external.getMethod();
                final String index = external.getIndex();
                boolean isExecute = true;
                if (mAction != null) {
                    isExecute = mAction.onExternalPreExecute(tabs, index);
                }
                if (mExecute != null && isExecute) {
                    mExecute.execute(tabs, method);
                }
                if (mAction != null) {
                    mAction.onExternalPostExecute(tabs, index);
                }
                reloadResult(tabs, external, param, seData);
            }
        }
    }
    
    /**
     * 对此次请求进行验证操作.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param check 验证对象
     * @param isThrow 是否抛出异常【true：抛出；false：不抛出】
     * @param whereMap sql条件容器对象
     * @return 验证结果【true：验证条件成立；false：验证条件失败】
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    private boolean doCheck(
            Param param, SessionData seData, IChecker check, boolean isThrow, Map<String, Object> whereMap) 
        throws RichClientWebException, InputException {
        LOG.debug("doCheck", "start");
        
        // 验证结果标识【true：验证失败，报错；false：验证成功，正常进行后续操作】
        boolean result = false;
        String resCode = EngineExceptionEnum.UR_COM_INPUT_ERROR.getCode();
        
        // 取得<checks>标签下的<check>标签对象数组
        final List<Checker> checks = check.getChecks();
        
        // 共通验证类
        final CommonChecker commonCheck = new CommonChecker();
        ErrTabBean errTabBean = new ErrTabBean();
        // 是否需要共通验证
        if (ControlRequestMap.getInstance().isChecked(param)) {
            // 执行共通验证方法
            errTabBean = commonChecker(param, seData);
        }
        
        if (checks.size() > 0) {
            // 遍历<checks>标签下的<check>标签对象数组
            for (int i = 0; i < checks.size(); i++) {
                boolean isError = false;
                
                // 取得<check>标签对象
                final Checker checker = checks.get(i);
                
                // 创建验证用数据对象
                final TableRowMap tabContainer = new TableRowMap();
                
                // 在验证中做判断，如果whether中的condition为【false】，验证不再进行，作为没有错误处理
                final Map<String, List<String>> checkResult = judgeOperator(
                        checker.getWhethers(), tabContainer, param, seData, null);
                
                if (checkResult != null) {
                    continue;
                }
                
                // 取得条件对象数组
                final List<Condition> conditions = checker.getConditions();
                final boolean isExecute = doActionPre(checker, param, seData, whereMap);
                
                TableRowMap tab = new TableRowMap();
                // 取得<check>标签的【sql】属性值
                final String sql = checker.getSql();
                final String source = checker.getSource();
                if (CommonUtil.isNotEmpty(sql) && getDb() != null && isExecute) {
                    if (CommonUtil.isEmpty(source)) {
                        tab = getDb().query(sql, whereMap);
                    } else {
                        tab = getDb().get(source).query(sql, whereMap);
                    }
                }
                if (tab != null) {
                    tabContainer.putAll(tab.getDataMap());
                }
                
                // 调用外部对象取值
                executeExternal(checker, tabContainer, param, seData);
                // 验证错误控件序列数组
                Map<String, List<String>> actionCheckList = null;
                if (conditions != null && conditions.size() > 0) {
                    if (ControlRequestMap.getInstance().isSiftChecked(param)) {
                        actionCheckList = judgeSiftOperator(checker, tab, param, seData);
                        if (actionCheckList != null) {
                            isError = true;
                        }
                    } else {
                        isError = !judgeOperator(checker, tab, param, seData);
                    }
                }
                if (!isError && mAction != null) {
                    actionCheckList = mAction.onCheckPostExecute(tabContainer, checker.getIndex());
                    if (actionCheckList != null) {
                        isError = true;
                    }
                }
                if (isError && isThrow) {
                    result = true;
                    final List<Taber> tabs = checker.getTabs();
                    if (CommonUtil.isNotEmpty(checker.getResCode())) {
                        resCode = checker.getResCode();
                    }
                    if (errTabBean == null) {
                        errTabBean = new ErrTabBean(true);
                    } else {
                        errTabBean.setError(true);
                    }
                    if (actionCheckList != null) {
                        for (int j = 0; j < tabs.size(); j++) {
                            final Taber taber = tabs.get(j);
                            final String targetId = taber.getTargetId();
                            final List<String> checkIndexs = actionCheckList.get(targetId);
                            // 把check标签的message变为动态
                            final String mescode = createMessageCode(param, seData, tabContainer, taber);
                            if (checkIndexs == null || checkIndexs.size() == 0) {
                                errTabBean.add(commonCheck.makeErrTab(
                                        param.dispCode, targetId, ConstantsUtil.Str.EMPTY, mescode));
                            } else {
                                for (int k = 0; k < checkIndexs.size(); k++) {
                                    errTabBean.add(commonCheck.makeErrTab(
                                            param.dispCode, targetId, checkIndexs.get(k), mescode));
                                }
                            }
                        }
                    } else {
                        for (int j = 0; j < tabs.size(); j++) {
                            final String targetId = tabs.get(j).getTargetId();
                            final String mescode = createMessageCode(param, seData, tabContainer, tabs.get(j));
                            final String[] targetIds = commonCheck.getTargetIds(param.targetId);
                            String targetIndex = ConstantsUtil.Str.EMPTY;
                            if (targetIds != null) {
                                targetIndex = targetIds[1];
                            }
                            errTabBean.add(commonCheck.makeErrTab(param.dispCode, targetId, targetIndex, mescode));
                        }
                    }
                }
            }
        }
        if (errTabBean != null && (errTabBean.isError() 
                || HtmlUtil.hasErrorCheck(errTabBean.getErrList().toArray(new ResponseTab[0])))) {
            commonCheck.showErrorResponse(errTabBean, param, resCode);
        }
        
        LOG.debug("doCheck", "end");
        return result;
    }

    /**
     * @Description: 创建结果显示的code
     * @author king
     * @since Dec 25, 2012 1:44:02 PM 
     * @version V1.0
     * @param param 客户端请求对象
     * @param seData session对象
     * @param tab 数据结果集
     * @param taber tab标签对象
     * @return 结果显示的code
     * @throws RichClientWebException RichClientWebException
     */
    private String createMessageCode(Param param, SessionData seData, TableRowMap tab,
            Taber taber) throws RichClientWebException {
        LOG.debug("createMessageCode", null, "taber.getMessage = " + taber.getMessage());
        final String mescodeString = RequestConfigUtil.getRequestValue(
                tab, param, seData, taber.getMessage());
        String mescode = ConstantsUtil.Check.DEFAULT;
        if (CommonUtil.isNotEmpty(mescodeString)) {
            mescode = mescodeString;
        }
        return mescode;
    }

    /**
     * 多项目方法.
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return response信息
     * @throws RichClientWebException RichClientWebException
     */
    private AbstractResponseData showListPriv(Param param, SessionData seData)
        throws RichClientWebException {
        LOG.debug("showListPriv", "start");
        // 初始化handler对象
        initializeHandler(param, seData);
        // sql中的动态参数保存对象
        final Map<String, Object> whereMap = createCondition(param);
        
        // 加载页面显示元素对象集合
        List<TableRowMap> list = new ArrayList<TableRowMap>();
        
        // <list>标签中的【targetId】属性值
        final String targetId = CommonUtil.toString(ControlRequestMap.getInstance().getTargetId(param));
        
        PageBean pageBean = null;
        
        if (ControlRequestMap.getInstance().hasDimension(param)) {
            
            // 生成二维数据容器
            final TabRowMapDimensions dimensionList = new TabRowMapDimensions();
            
            final Dimension dimension = ControlRequestMap.getInstance().getDimension(param);
            
            final String index = dimension.getIndex();
            
            // 生成二维的第一维多项目画面数据
            list = getListDatas(createList(param, seData, whereMap, dimension));
            
            // 取得二维标签中的一维多项目标签对象
            final RequestParam.List paramList = dimension.getList();
            
            // 二维标签中的一维多项目标签对象存在
            if (paramList != null) {
                
                // 遍历二维的第一维多项目画面数据
                for (int i = 0; i < list.size(); i++) {
                    
                    final TableRowMap tab = list.get(i);
                    
                    // 生成二维数据容器中第二维的容器
                    final TabRowMapList tabList = new TabRowMapList();
                    
                    // 加载在<results>标签中的数据，使之作用到数据查询结果中
                    tab.putAll(reloadResult(tab, dimension, param, seData));
                    
                    tabList.addLine(tab);
                    createCondition(whereMap, tab);
                    final List<TableRowMap> tabLists = getListDatas(createList(param, seData, whereMap, paramList));
                    executeExternal(paramList, tabLists, param, seData);
                    // 执行自定制的action方法
                    if (mAction != null) {
                        mAction.onListPostExecute(tabLists, index);
                    }
                    reloadResult(tabLists, paramList, param, seData);
                    tabList.setList(tabLists);
                    dimensionList.addDimension(tabList);
                }
            }
            if (mAction != null) {
                mAction.onDemensionListPostExecute(list, dimensionList, dimension.getIndex());
            }
            
            return showList(dimensionList, targetId, param, seData);
        }
        
        final com.richClientFrame.data.param.RequestParam.List listItem = 
            ControlRequestMap.getInstance().getList(param);
        
        // 是否是一维多项目
        if (ControlRequestMap.getInstance().hasList(param)) {
            
            // 取得在<results>标签中的数据
            
            pageBean = createList(param, seData, whereMap, listItem);
            
            // 生成一维多项目画面数据
            list = getListDatas(pageBean);
            
        }
        
        //调用外部对象取值
        executeExternal(listItem, list, param, seData);
        
        final String pageSize = ControlRequestMap.getInstance().getPageSize(listItem);
        
        if (CommonUtil.isNotEmpty(pageSize) && pageBean == null) {
            pageBean = new PageBean();
        }
        
        // 读取excel的方法
        readExcel(param, seData, listItem, list);
        
        final String index = listItem.getIndex();
        
        if (mAction != null) {
            mAction.onListPostExecute(list, index);
        }
        
        list = getListForPaging(param, list, pageBean, pageSize);
        
        // 生成excel的方法
        final ResponseExcel resExcel = writeExcel(param, seData, listItem, list);
        
        LOG.debug("showListPriv", "end");
        return createListResponse(param, seData, list, targetId, pageBean, listItem, resExcel);
    }

    /**
     * @Description: 
     * @author king
     * @since Jul 26, 2013 2:58:53 PM 
     * @version V1.0
     * @param param param
     * @param seData seData
     * @param list list
     * @param targetId targetId
     * @param pageBean pageBean
     * @param listItem listItem
     * @param resExcel resExcel
     * @return ListResponse
     * @throws RichClientWebException RichClientWebException
     */
    private AbstractResponseData createListResponse(
            Param param, 
            SessionData seData,
            List<TableRowMap> list, 
            final String targetId) throws RichClientWebException {
        return createListResponse(param, seData, list, targetId, null, null, null);
    }
    
    /**
     * @Description: 
     * @author king
     * @since Jul 26, 2013 2:58:53 PM 
     * @version V1.0
     * @param param param
     * @param seData seData
     * @param list list
     * @param targetId targetId
     * @param pageBean pageBean
     * @param listItem listItem
     * @param resExcel resExcel
     * @return ListResponse
     * @throws RichClientWebException RichClientWebException
     */
    private AbstractResponseData createListResponse(
            Param param, 
            SessionData seData,
            List<TableRowMap> list, 
            final String targetId, 
            PageBean pageBean,
            final com.richClientFrame.data.param.RequestParam.List listItem,
            final ResponseExcel resExcel) throws RichClientWebException {
        // 画面列表项目设置方法[一维列表] 
        final ResponseList responseList = showList(list, param, seData, listItem);
        final ResponseHeaderData responseHeaderData = new ResponseHeaderData();
        responseHeaderData.setParam(param);
        responseHeaderData.setTargetId(targetId);
        responseHeaderData.setDataKind(ConstantsUtil.DataType.LIST);
        final ResponseHeader header = new ResponseHeader(responseHeaderData);
        if (pageBean != null) {
            final String pageSizeStr = CommonUtil.toString(pageBean.getPageSize());
            header.setPageSize(pageSizeStr);
            final String totalRowsStr = CommonUtil.toString(pageBean.getTotalRows());
            header.setTotalRows(totalRowsStr);
        }
        final AbstractResponseData resList1 = new AbstractResponseData(header, targetId);
        resList1.setDataList(responseList);
        
        if (resExcel != null) {
            resList1.setResExcel(resExcel);
        }
        return resList1;
    }

    /** 
    * @Description: 生成分页数组信息
    * @author king
    * @since Sep 14, 2012 11:27:15 PM 
    * 
    * @param param 请求对象
    * @param list 数组信息
    * @param pageBean 分页信息容器
    * @param pageSize pageSize
    * @return 分页数组信息
    * @throws RichClientWebException RichClientWebException
    */
    private List<TableRowMap> getListForPaging(
            Param param, List<TableRowMap> list, PageBean pageBean, String pageSize)
        throws RichClientWebException {
        if (list == null) {
            return new ArrayList<TableRowMap>();
        }
        if (CommonUtil.isNotEmpty(pageSize) && (pageBean.getPageSize() <= 0)) {
            final int count = list.size();
            final int pageSizeInt = Integer.parseInt(pageSize);
            pageBean.setPageSize(pageSizeInt);
            pageBean.setTotalRows(count);
            if (pageSizeInt < count) {
                int page = 1;
                final String currentpage = param.get("currentpage");
                if (CommonUtil.isNotEmpty(currentpage)) {
                    page = Integer.parseInt(currentpage);
                }
                int beginIndex = (page - 1) * pageSizeInt;
                if (beginIndex >= count) {
                    page = count / pageSizeInt;
                    if (count % pageSizeInt == 0) {
                        page--;
                    }
                    beginIndex = page * pageSizeInt;
                }
                if ((beginIndex + pageSizeInt) <= count) {
                    return list.subList(beginIndex, beginIndex + pageSizeInt);
                } else {
                    return list.subList(beginIndex, count);
                }
            }
        }
        return list;
    }
    
    /** 
    * @Description: 取得数组信息中的数据信息
    * @author king
    * @since Sep 14, 2012 10:53:57 PM 
    * 
    * @param pageBean 信息存储容器
    * @return 数组信息中的数据信息
    */
    private List<TableRowMap> getListDatas(PageBean pageBean) {
        if (pageBean == null) {
            return new ArrayList<TableRowMap>();
        }
        return pageBean.getDatas();
    }

    /**
     * 生成一维多项目画面数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param whereMap sql条件
     * @param action 共通对象
     * @return 数据
     * @throws RichClientWebException RichClientWebException
     */
    private PageBean createList(
            Param param, 
            SessionData seData, 
            final Map<String, Object> whereMap, 
            IActionStep action)
        throws RichClientWebException {
        LOG.debug("createList", "start");
        PageBean pageBean = new PageBean();
        TableRowMap[] tabs = null;
        final List<TableRowMap> tabList = new ArrayList<TableRowMap>();
        
        final boolean isExecute = doActionPre(action, param, seData, whereMap);
        
        final String sql = action.getSql();
        final String source = action.getSource();
        if (CommonUtil.isNotEmpty(sql) && getDb() != null && isExecute) {
            if (CommonUtil.isNotEmpty(ControlRequestMap.getInstance().getPageSize((IList)action))) {
                final int pageSize = Integer.parseInt(ControlRequestMap.getInstance().getPageSize((IList)action));
                LOG.info("createList", "", "pageSize = " + pageSize);
                if (CommonUtil.isEmpty(source)) {
                    pageBean = getDb().queryPages(sql, whereMap, pageSize, param);
                } else {
                    pageBean = getDb().get(source).queryPages(sql, whereMap, pageSize, param);
                }
                if (pageBean != null && pageBean.getDatas() != null) {
                    tabList.addAll(pageBean.getDatas());
                }
            } else {
                if (CommonUtil.isEmpty(source)) {
                    tabs = getDb().querys(sql, whereMap);
                } else {
                    tabs = getDb().get(source).querys(sql, whereMap);
                }
                // 把生成的数据加载到加载页面显示元素对象集合中
                if (tabs != null) {
                    tabList.addAll(Arrays.asList(tabs));
                }
            }
        }
        
        
        if (pageBean != null) {
            pageBean.setDatas(tabList);
        }
        LOG.debug("createList", "end");
        return pageBean;
    }
    
    /**
     * 数据库增删改方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    private ResponseData updatePri(Param param, SessionData seData) throws RichClientWebException,
            InputException {
        LOG.debug("update", "start");
        
        // 初始化handler对象
        initializeHandler(param, seData);
        
        // 画面对象属性容器数组
        final List<ResponseTab> tabList = new ArrayList<ResponseTab>();
        
        // 画面对象属性容器数组
        TableRowMap tabContent = new TableRowMap();
        // 由request生成查询条件
        final Map<String, Object> whereMap = createCondition(param);
        final Updates updates = ControlRequestMap.getInstance().getUpdates(param);
        final List<com.richClientFrame.data.param.RequestParam.Param> params = updates.getParams();
        reloadParams(whereMap, params, param, seData, tabContent);
        final List<Update> updateList = updates.getUpdates();
        boolean updateExecuteResult = true;
        for (int i = 0; i < updateList.size(); i++) {
            final Update update = updateList.get(i);
            // 对此次请求进行验证操作
            doCheck(param, seData, update, true, whereMap);
            // 判断此<update>节点是否需要执行，根据<condtion>标签来判断
            if (!judgeOperator(update, tabContent, param, seData)) {
                continue;
            }
            final boolean isExecute = doActionPre(update, param, seData, whereMap, tabContent);
            
            final String sql = update.getSql();
            final String source = update.getSource();
            if (CommonUtil.isNotEmpty(sql) && getDb() != null && isExecute) {
                TableRowMap tab = null;
                if (update.hasResult()) {
                    if (CommonUtil.isEmpty(source)) {
                        tab = getDb().insert(sql, whereMap);
                    } else {
                        tab = getDb().get(source).insert(sql, whereMap);
                    }
                    if (tab == null) {
                        updateExecuteResult = false;
                    } else if (tabContent != null) {
                        tabContent.putAll(tab.getDataMap());
                    }
                } else {
                    int result = 0;
                    if (CommonUtil.isEmpty(source)) {
                        result = getDb().update(sql, whereMap, tabContent);
                    } else {
                        result = getDb().get(source).update(sql, whereMap, tabContent);
                    }
                    if (result < 0) {
                        updateExecuteResult = false;
                    }
                }
            }
            if (tabContent == null) {
                tabContent = new TableRowMap();
            }
            // 把参数信息保存在返回画面的容器中
//            keepParamsValues(param, seData, update, tab);
            if (isExecute) {
                tabContent.put(TableRowMap.Constant.Update.RESULT_EXECUTE, "true");
            } else {
                tabContent.put(TableRowMap.Constant.Update.RESULT_EXECUTE, "false");
            }
//            if (CommonUtil.isEmpty(param.targetId)) {
//                setWhereMapToTab(whereMap, tab);
//            }
            if (mAction != null) {
                mAction.onUpdatePostExecute(tabContent, updateExecuteResult, update.getIndex());
            }
            executeExternal(update, tabContent, param, seData);
            tabContent.putAll(reloadResult(tabContent, update, param, seData));
        }
        final String resCode = setUpdateResCode(param, updates, updateExecuteResult);
        
        showItemByItems(tabContent, param, seData, tabList);
        
        setSingleDefault(param, tabList);
        
        final ResponseTab[] tabs = tabList.toArray(new ResponseTab[0]);
        
        final ResponseData resData = makeHeader(param, resCode);
        resData.setResTab(tabs);
        setOutPut(tabContent, resData);
        
        LOG.debug("update", "end");
        return resData;
    }

    /**
     * @Description: 设置更新操作的结果code
     * @author king
     * @since Jul 11, 2013 9:22:59 AM 
     * @version V1.0
     * @param param param
     * @param updates updates
     * @param flag flag
     * @return UpdateResCode
     * @throws RichClientWebException RichClientWebException
     */
    private String setUpdateResCode(Param param, final Updates updates, boolean flag)
        throws RichClientWebException {
        final String success = updates.getSuccess();
        final String error = updates.getError();
        String resCode = EngineExceptionEnum.UR_COM_REGISTER_SUCCESS.getCode();
        if (flag) {
            if (CommonUtil.isEmpty(ControlRequestMap.getInstance().getForward(param))) {
                resCode = CommonUtil.isNotEmpty(success) ? success 
                        : EngineExceptionEnum.UR_COM_REGISTER_SUCCESS.getCode();
            }
        } else {
            resCode = CommonUtil.isNotEmpty(error) ? error 
                    : EngineExceptionEnum.UR_COM_REGISTER_ERROR.getCode();
        }
        return resCode;
    }
    
    /**
     * 文件上传方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public AbstractResponseData upload(Param param, SessionData seData)
        throws RichClientWebException, InputException {
        try {
            return uploadPri(param, seData);
        } catch (IOException e) {
            e.printStackTrace();
            LOG.fatal(EngineExceptionEnum.FM_COM_IO_ERROR.getErrinfo() + e.toString(), e);
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
        }
    }

    /**
     * 文件上传方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     * @throws IOException IOException
     */
    private AbstractResponseData uploadPri(Param param, SessionData seData) 
        throws RichClientWebException, InputException, IOException {
        LOG.debug("upload", "start");
        initializeHandler(param, seData);
        final List<ResponseTab> tabList = new ArrayList<ResponseTab>();
        TableRowMap tab = null;
        List<TableRowMap> tabs = null;
        final List<FileItem> fileItemList = param.getUploadItemList();
        LOG.info("upload", "上传文件的数量为：" + fileItemList.size());
        final Map<String, Object> whereMap = createCondition(param);
        final Upload uploadItem = ControlRequestMap.getInstance().getUpload(param);
        final String dataType = uploadItem.getDataType();
        // 对此次请求进行验证操作
        doCheck(param, seData, uploadItem, true, whereMap);
        for (int i = 0; i < fileItemList.size(); i++) {
            final FileItem item = fileItemList.get(i);
            final String fileName = item.getName();
            final String fileRealName = fileName.substring(fileName.lastIndexOf("\\") + 1);
            final UploadParam upload = new UploadParam();
            LOG.info("upload", "上传文件的名称为：" + fileName);
            LOG.info("upload", "上传文件的真实名称为：" + fileRealName);
            upload.setFileName(fileName);
            upload.setFileRealName(fileRealName);
            if (uploadItem.getExcel() != null) {
                final String suffix = fileName.substring(fileName.lastIndexOf(ConstantsUtil.Str.DOT) + 1);
                uploadItem.getExcel().setFileType(suffix);
                if (uploadItem.isList() || "list".equals(dataType)) {
                    tabs = new ArrayList<TableRowMap>();
                    readExcel(param, seData, uploadItem, tabs, item.getInputStream());
                } else {
                    tab = new TableRowMap();
                    readExcel(param, seData, uploadItem, tab, item.getInputStream());
                }
            } else {
                String fileRealPath = CommonUtil.getUploadRealPath() + File.separator;
                String urlFilePath = ControlConfig.getInstance().getConfiguration().getUploadPath() + File.separator;
                final String paramPath = uploadItem.getPath();
                if (CommonUtil.isNotEmpty(paramPath)) {
                    urlFilePath += paramPath + File.separator;
                }
                fileRealPath += urlFilePath;
                
                String url = ConstantsUtil.Str.EMPTY;
                if (uploadItem.isUseFileName()) {
                    final String fileNameForStore = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    upload.setStoreFileName(fileNameForStore);
                } else {
                    upload.setStoreFileName(FileUtil.createFileId(DateUtils.getNowTime(DateUtils.FORMAT_YYYYMMDDHHSS3)) 
                            + fileName.substring(fileName.lastIndexOf(ConstantsUtil.Str.DOT)));
                }
                upload.setRealPath(fileRealPath);
                whereMap.put("fileName", upload.getFileName());
                whereMap.put("realPath", upload.getRealPath() + File.separator + upload.getStoreFileName());
                final boolean isExecute = doActionPre(uploadItem, param, seData, whereMap, upload, null);
                String ftp = "0";
                if (uploadItem.isFtp()) {
                    FileUtil.ftpUpload(item.getInputStream(), upload.getStoreFileName());
                    url = ControlConfig.getInstance().getConfiguration().getFtpAddress() 
                        + ControlConfig.getInstance().getConfiguration().getFtpPath() 
                        + File.separator + upload.getStoreFileName();
                    ftp = "1";
                } else {
                    storeUploadFile(item, uploadItem, upload);
                    url = ControlConfig.getHostUrl() + param.getContextPath() 
                        + File.separator + urlFilePath + upload.getStoreFileName();
                }
                if (CommonUtil.isNotEmpty(url)) {
                    url = url.replaceAll(ConstantsUtil.Str.TRANSFERREDS, ConstantsUtil.Str.SLASH);
                }
                upload.setUrl(url);
                upload.setSize(item.getSize());
                LOG.info("upload", null, "StoreFileName = " + upload.getStoreFileName(), 
                        "url = " + url, "size = " + item.getSize());
                whereMap.put("fileUrl", url);
                whereMap.put("ftp", ftp);
                whereMap.put("fileSize", item.getSize());
                final String sql = uploadItem.getSql();
                final String source = uploadItem.getSource();
                if (CommonUtil.isNotEmpty(sql) && getDb() != null && isExecute) {
                    if (CommonUtil.isEmpty(source)) {
                        tab = getDb().insert(sql, whereMap);
                    } else {
                        tab = getDb().get(source).insert(sql, whereMap);
                    }
                    if (tab != null) {
                        upload.setDbResult(true);
                    } else {
                        tab = new TableRowMap();
                    }
                } else {
                    tab = new TableRowMap();
                }
                tab.put("fileSize", item.getSize());
                tab.put("fileUrl", url);
                tab.put("fileName", upload.getFileName());
                // 不含服务器地址的文件路径
                tab.put("relative", urlFilePath + upload.getStoreFileName());
            }
            
            if (mAction != null) {
                if (tabs != null) {
                    mAction.onUploadPostExecute(upload, tabs, uploadItem.getIndex());
                } else {
                    mAction.onUploadPostExecute(upload, tab, uploadItem.getIndex());
                }
            }
            if (tabs != null) {
                final String targetId = uploadItem.getTargetId();
                if ("list".equals(dataType)) {
                    return createListResponse(param, seData, tabs, targetId);
                } else {
                    showItemByItems(tabs, param, targetId, seData, tabList, uploadItem, tab);
                }
            } else {
                tab.putAll(reloadResult(tab, uploadItem, param, seData));
                
                showItemByItems(tab, param, seData, tabList);
            }
            
        }
        
        setSingleDefault(param, tabList);
        
        final ResponseTab[] responseTabs = tabList.toArray(new ResponseTab[0]);
        
        final ResponseData resData = makeHeader(param, EngineExceptionEnum.UR_COM_UPLOAD_SUCCESS);
        resData.setResTab(responseTabs);
        setOutPut(tab, resData);
        LOG.debug("upload", "end");
        return resData;
    }

    /**
     * @Description: 
     * @author king
     * @since Jul 17, 2013 4:03:06 PM 
     * @version V1.0
     * @param item item
     * @param uploadItem uploadItem
     * @param upload upload
     * @throws IOException IOException
     * @throws RichClientWebException RichClientWebException
     */
    private void storeUploadFile(final FileItem item, final Upload uploadItem,
            final UploadParam upload) throws IOException, RichClientWebException {
        final File dir = new File(upload.getRealPath());
        if (uploadItem.isInitialize()) {
            // 文件夹初期化
            if (dir.isDirectory() && dir.exists()) {
                FileUtils.cleanDirectory(dir);
            }
            // 生成文件夹
            FileUtils.forceMkdir(dir);
        } else {
            if (!dir.exists()) {
                // 生成文件夹
                FileUtils.forceMkdir(dir);
            }
        }
        try {
            item.write(new File(upload.getRealPath(), upload.getStoreFileName()));
        } catch (Exception e) {
            LOG.fatal(EngineExceptionEnum.FM_COM_SYSTEM_ERROR.getErrinfo() + e.toString(), e);
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        }
    }

    /** 
    * @Description: 设置通过流的形式返回页面的信息
    * @author king
    * @since Oct 7, 2012 9:08:42 AM 
    * 
    * @param tab 信息容器
    * @param resData 返回画面结果集
    */
    private void setOutPut(TableRowMap tab, final ResponseData resData) {
        if (tab != null && CommonUtil.isNotEmpty(tab.getOutPut())) {
            resData.setOutPut(tab.getOutPut());
        }
    }
    
    /**
     * 画面多条单项目设置方法.
     * 
     * @param tabList 数据
     * @param param 用户请求对象
     * @param targetId 画面控件ID
     * @param seData 用户会话对象
     * @param resList 画面显示数据
     * @param item <item>标签对象
     * @param tabContent 之前数据库查询结果信息
     * @throws RichClientWebException RichClientWebException
     */
    private void showItemByItems(
            List<TableRowMap> tabList, Param param, String targetId, 
            SessionData seData, List<ResponseTab> resList, ISingle item, TableRowMap tabContent) 
        throws RichClientWebException {
        if (tabList != null) {
            final Map<String, String> types = ControlDispField.getInstance().getField(param.dispCode);
            for (int i = 0; i < tabList.size(); i++) {
                final TableRowMap tab = tabList.get(i);
                if (!item.isResultConstant()) {
                    tab.putAll(reloadResult(tab, (IActionStep)item, param, seData));
                }
                if (tab != null) {
                    final int type = getWidgetType(targetId, types);
                    
                    String value = ConstantsUtil.Str.EMPTY;
                    String text = ConstantsUtil.Str.EMPTY;
                    if (type == ConstantsUtil.Widget.OPTION) {
                        if (tab.containsKey(ConstantsUtil.Cmb.VALUE)) {
                            value = CommonUtil.toString(tab.get(ConstantsUtil.Cmb.VALUE));
                            text = CommonUtil.toString(tab.get(ConstantsUtil.Cmb.LABEL));
                        } else if (tab.containsKey(ConstantsUtil.Cmb.VALUE.toUpperCase())) {
                            value = CommonUtil.toString(tab.get(ConstantsUtil.Cmb.VALUE.toUpperCase()));
                            text = CommonUtil.toString(tab.get(ConstantsUtil.Cmb.LABEL.toUpperCase()));
                        }
                    } else {
                        if (tab.containsKey("_value")) {
                            value = CommonUtil.toString(tab.get("_value"));
                        } else if (tab.containsKey("value")) {
                            value = CommonUtil.toString(tab.get("value"));
                        } else {
                            for (String key : tab.keySet()) {
                                value = CommonUtil.toString(tab.get(key));
                                break;
                            }
                        }
                    }
                    ResponseTab tabItem = null;
                    if (type == ConstantsUtil.Widget.OPTION) {
                        String selectVal = null;
                        final String selectedKey = item.getSelectedKey();
                        if (CommonUtil.isNotEmpty(selectedKey) 
                                && tabContent.containsKey(selectedKey)) {
                            selectVal = tabContent.getString(selectedKey);
                        }
                        tabItem = RequestConfigUtil.createItem(param, type, targetId, value, text, false, selectVal);
                    } else {
                        tabItem = RequestConfigUtil.createItem(param, type, targetId, value, false);
                        tabItem.setDataMap(tab.getDataMap());
                    }
                    setStyle(tabItem, param, tab, seData);
                    resList.add(tabItem);
                }
            }
        }
    }

    /**
     * @Description: 根据配置文件取得控件类型
     * @author king
     * @since Jun 8, 2013 10:57:24 AM 
     * @version V1.0
     * @param targetId 控件ID
     * @param types 配置文件容器对象
     * @return 控件类型
     */
    private int getWidgetType(String targetId, final Map<String, String> types) {
        int type = ConstantsUtil.Widget.CHK;
        String typeTargetId = targetId;
        if (targetId.indexOf(ConstantsUtil.Str.TARGETID_SUFFIX) != -1) {
            typeTargetId = targetId.substring(0, targetId.indexOf(ConstantsUtil.Str.TARGETID_SUFFIX));
        }
        if (types != null && types.containsKey(typeTargetId)) {
            type = Integer.parseInt(types.get(typeTargetId));
            if (type == ConstantsUtil.Widget.CMB) {
                type = ConstantsUtil.Widget.OPTION;
            }
        }
        return type;
    }
    
    
    /**
     * 画面单项目设置方法.
     * 
     * @param tab 数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param resList 画面显示数据
     * @throws RichClientWebException RichClientWebException
     */
    private void showItemByItems(TableRowMap tab, Param param, SessionData seData, List<ResponseTab> resList)
        throws RichClientWebException {
        if (tab != null) {
            final Map<String, String> types = ControlDispField.getInstance().getField(param.dispCode);
            for (String key : tab.keySet()) {
                if (ConstantsUtil.Db.PARAMS.equals(key) 
                        || ConstantsUtil.Db.SQL.equals(key) 
                        || ConstantsUtil.Db.SQL_ID.equals(key)) {
                    continue;
                }
                int type = ConstantsUtil.Widget.TEXTBOX;
                if (types != null && types.containsKey(key)) {
                    type = Integer.parseInt(types.get(key));
                }
                if (tab.getListMap().containsKey(key)) {
                    if (tab.get(key) instanceof String[]) {
                        final String[] tabs = (String[]) tab.get(key);
                        for (int i = 0; i < tabs.length; i++) {
                            final ResponseTab tabItem = RequestConfigUtil.createItem(param, type, key, tabs[i]);
                            if (i == 0) {
                                setStyle(tabItem, param, tab, seData, key);
                            }
                            resList.add(tabItem);
                        }
                    }
                } else {
                    final ResponseTab tabItem = RequestConfigUtil.createItem(
                            param, type, key, tab.getString(key), false);
                    setStyle(tabItem, param, tab, seData, key);
                    resList.add(tabItem);
                }
            }
        }
        setStyle(tab, resList, param, seData);
    }
    
    /**
     * 创建数据头
     * @param param 用户请求对象
     * @param resCode 执行结果code
     * @return 画面数据对象
     * @throws RichClientWebException RichClientWebException
     */
    private ResponseData makeHeader(Param param, EngineExceptionEnum resCode) throws RichClientWebException {
        return makeHeader(param, resCode.getCode());
    }
    
    /**
     * 创建数据头
     * @param param 用户请求对象
     * @param resCode 执行结果code
     * @return 画面数据对象
     * @throws RichClientWebException RichClientWebException
     */
    private ResponseData makeHeader(Param param, String resCode) throws RichClientWebException {
        ResponseHeader header = null;
        final ResponseHeaderData responseHeaderData = new ResponseHeaderData();
        responseHeaderData.setParam(param);
        if (CommonUtil.isNotEmpty(ControlRequestMap.getInstance().getForward(param))) {
            responseHeaderData.setResCode(EngineExceptionEnum.UR_COM_PAGE_CHANGED.getCode());
            header = new ResponseHeader(responseHeaderData);
        } else {
            responseHeaderData.setResCode(resCode);
            header = new ResponseHeader(responseHeaderData);
        }
        final ResponseData resData = new ResponseData(header);
        return resData;
    }
    
    /**
     * 画面列表项目设置方法.
     * 
     * @param tabs 数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param listItem <list>标签信息
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     */
    public ResponseList showList(
            List<TableRowMap> tabs, Param param, SessionData seData, 
            com.richClientFrame.data.param.RequestParam.List listItem)
        throws RichClientWebException {
        final ResponseList list = new ResponseList();
        // 是否有下拉框信息标识
        boolean hasCmbFlag = false;
        
        if (tabs != null) {
            // 列表中下拉框内容设置
            final Map<String, TableRowMap[]> cmbMaps = new HashMap<String, TableRowMap[]>();
            List<Cmb> sedCmbs = null;
            if (listItem != null) {
                final List<Cmb> cmbs = listItem.getCmbs();
                if (cmbs != null && cmbs.size() > 0) {
                    if (cmbs != null & cmbs.size() > 0) {
                        hasCmbFlag = true;
                        for (int j = 0; j < cmbs.size(); j++) {
                            final Cmb cmb = cmbs.get(j);
                            if (CommonUtil.isEmpty(cmb.getKey())) {
                                final String cmbTargetId = cmb.getTargetId();
                                cmbMaps.put(cmbTargetId, createCmbInfo(cmb, param));
                            } else {
                                if (sedCmbs == null) {
                                    sedCmbs = new ArrayList<Cmb>();
                                }
                                sedCmbs.add(cmb);
                            }
                        }
                    }
                }
            }
            // 创建列表行信息
            createListLines(tabs, param, seData, listItem, list, cmbMaps, sedCmbs);
        }
        list.setHasCmbs(hasCmbFlag);
        
        return list;
    }

    /** 
    * @Description: 创建列表行信息
    * @author king
    * @since Oct 8, 2012 9:50:08 AM 
    * 
    * @param tabs 列表信息
    * @param param 客户端请求对象
    * @param seData session
    * @param listItem 列表对象信息
    * @param list 结果集对象
    * @param cmbMaps 方法MAP
    * @param sedCmbs 下拉框数组
    * @throws RichClientWebException RichClientWebException
    */
    private void createListLines(
            List<TableRowMap> tabs, 
            Param param, 
            SessionData seData,
            com.richClientFrame.data.param.RequestParam.List listItem, 
            final ResponseList list,
            final Map<String, TableRowMap[]> cmbMaps,
            List<Cmb> sedCmbs) throws RichClientWebException {
        final Map<String, String> types = ControlDispField.getInstance().getField(param.dispCode);
        final Map<String, Map<String, TableRowMap[]>> sedCmbMaps = 
            new HashMap<String, Map<String, TableRowMap[]>>();
        for (int i = 0; i < tabs.size(); i++) {
            final ResponseLine line = new ResponseLine();
            final TableRowMap tab = tabs.get(i);
            if (tab != null) {
                // 多维数据解析
                final List<TableRowMap> tabList = tab.getDatasList();
                if (tabList != null && tabList.size() > 0) {
                    final ResponseList responseList = new ResponseList();
                    createListLines(tabList, param, seData, listItem, responseList, cmbMaps, sedCmbs);
                    line.setList(responseList);
                }
            }
            tab.putAll(reloadResult(tab, listItem, param, seData));
            Map<String, TableRowMap[]> sedCmbMapItems = new HashMap<String, TableRowMap[]>();
            // 列表中的下拉框信息解析
            if (sedCmbs != null) {
                for (int j = 0; j < sedCmbs.size(); j++) {
                    final Cmb cmb = sedCmbs.get(j);
                    String cmbTargetId = cmb.getTargetId();
                    final String cmbKey = cmb.getKey();
                    final String[] cmbKeys = cmbKey.split(ConstantsUtil.Str.BLANK);
                    for (int k = 0; k < cmbKeys.length; k++) {
                        final String tabValue = tab.getString(cmbKeys[k]);
                        cmbTargetId += tabValue;
                    }
                    if (sedCmbMaps.containsKey(cmbTargetId)) {
                        sedCmbMapItems = sedCmbMaps.get(cmbTargetId);
                    } else {
                        sedCmbMapItems.put(cmb.getTargetId(), createCmbInfo(cmb, param, tab));
                    }
                }
            }
            sedCmbMapItems.putAll(cmbMaps);
            // 多项目中行数据信息的设定
            setTabLine(tab, param, types, line, seData, sedCmbMapItems);
            list.addLine(line);
        }
    }

    /**
     * 多项目中行数据信息的设定
     * @param tabRow 数据
     * @param param 用户请求对象
     * @param types 控件类型
     * @param line 数据行对象
     * @param cmbMaps 下拉框数据
     * @param seData 用户会话对象
     * @throws RichClientWebException RichClientWebException
     */
    private void setTabLine(
            TableRowMap tabRow, 
            Param param, 
            final Map<String, String> types, 
            final ResponseLine line, 
            SessionData seData, 
            Map<String, TableRowMap[]> cmbMaps) 
        throws RichClientWebException {
        for (String key : tabRow.keySet()) {
            int type = ConstantsUtil.Widget.TEXT;
            if (types != null && types.containsKey(key)) {
                type = Integer.parseInt(types.get(key));
            }
            ResponseTab tab = null;
            if (tabRow.get(key) instanceof TabRowGroup) {
                final TabRowGroup rowGroup = (TabRowGroup)tabRow.get(key);
                if (rowGroup.getWidgetType1() != null && rowGroup.getWidgetType2() != null) {
                    tab = mHtmlTab.makeGroupTab(key, rowGroup.getType1(), rowGroup.getType2());
                } else if (rowGroup.getWidgetType1() != null 
                        && rowGroup.getValue1() != null 
                        && rowGroup.getWidgetType2() == null) {
                    tab = RequestConfigUtil.createItem(param, rowGroup.getType1(), key, rowGroup.getValue1(), true);
                }
            } else {
                // 画面项目创建方法
                tab = RequestConfigUtil.createItem(
                        param, type, key, CommonUtil.toString(tabRow.get(key)), null, cmbMaps, true, null);
            }
            setStyle(tab, param, tabRow, seData);
            line.put(key, tab);
        }
        setStyle(tabRow, line, param, seData);
    }
    
    /**
     * 画面二维列表项目设置方法.
     * 
     * @param dimensionList 二维列表数据
     * @param targetId 列表控件ID
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 画面信息
     * @throws RichClientWebException RichClientWebException
     */
    public AbstractResponseData showList(
            TabRowMapDimensions dimensionList, 
            String targetId, Param param, SessionData seData)
        throws RichClientWebException {
        final ResponseDimensions dimensions = new ResponseDimensions();
        
        if (dimensionList != null) {
            final Map<String, String> types = ControlDispField.getInstance().getField(param.dispCode);
            for (int i = 0; i < dimensionList.size(); i++) {
                final ResponseList list = new ResponseList();
                final TabRowMapList tabRow = dimensionList.getDimension(i);
                final List<TableRowMap> tabRowList = tabRow.getList();
                for (int j = 0; j < tabRowList.size(); j++) {
                    final ResponseLine line = new ResponseLine();
                    final TableRowMap tabMap = tabRowList.get(j);
                    setTabLine(tabMap, param, types, line, seData, null);
                    list.addLine(line);
                }
                final ResponseLine headLine = new ResponseLine();
                final TableRowMap tabMap = tabRow.getDimensionLine();
                setTabLine(tabMap, param, types, headLine, seData, null);
                list.setDimensionLine(headLine);
                dimensions.addDimension(list);
            }
        }
        final ResponseHeaderData responseHeaderData = new ResponseHeaderData();
        responseHeaderData.setParam(param);
        responseHeaderData.setTargetId(targetId);
        responseHeaderData.setDataKind(ConstantsUtil.DataType.LIST);
        final ResponseHeader header = new ResponseHeader(responseHeaderData);
        final AbstractResponseData resList1 = new AbstractResponseData(header, targetId);
        resList1.setDimensionList(dimensions);
        
        return resList1;
    }
    
    /**
     * 分页方法.
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return response信息
     * @throws RichClientWebException RichClientWebException
     */
    public AbstractResponseData showPageList(Param param, SessionData seData) throws RichClientWebException {
        final ResponseList list1 = new ResponseList();
        final PageUtil page = new PageUtil();
        page.pageShow(list1, param);
        final ResponseHeaderData responseHeaderData = new ResponseHeaderData();
        responseHeaderData.setParam(param);
        responseHeaderData.setTargetId(ConstantsUtil.Handler.PAGING_ID);
        responseHeaderData.setDataKind(ConstantsUtil.DataType.LIST);
        final ResponseHeader header = new ResponseHeader(responseHeaderData);
        final CommonPageListInfo resData = new CommonPageListInfo(header);
        resData.setDataList(list1);
        return resData;
    }
    
    /**
     * 共通验证方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 验证信息
     * @throws RichClientWebException RichClientWebException
     */
    public ErrTabBean commonChecker(Param param, SessionData seData) 
        throws RichClientWebException {
//        ResponseData resData = null;
//
//        ResponseHeader header = null;

        ResponseTab[] resTabs = null;

        ErrTabBean errTabBean = new ErrTabBean();

        final CommonChecker check = new CommonChecker();

        try {

            if (CommonUtil.isNotEmpty(param.targetId)) {
                errTabBean = check.commonCheckSingleOnly(param);
            } else {
                if (ControlRequestMap.getInstance().isAllChecked(param)) {
                    errTabBean = check.commonCheckOnly(param);
                } else if (ControlRequestMap.getInstance().isSiftChecked(param)) {
                    errTabBean = check.commonCheckSiftOnly(param);
                }
            }
//            resTabs = (ResponseTab[]) tablist.toArray(new ResponseTab[tablist.size()]);

        } catch (InputException e) {

//            header = e.getErrHead();
            resTabs = e.getErrTab();
            errTabBean.addAll(Arrays.asList(resTabs));
            seData.setDetailErr(param.dispCode, e);
        } catch (RichClientWebException richex) {
            richex.printStackTrace();
            LOG.fatal(richex.getMessage());
            richex.setEngineExceptionEnum(EngineExceptionEnum.UR_COM_REGISTER_ERROR);

            final ResponseTab[] resTab = errTabBean.getErrList().toArray(new ResponseTab[0]);
            richex.setErrTab(resTab);

            throw richex;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.fatal(EngineExceptionEnum.FM_COM_SYSTEM_ERROR.getErrinfo() + e.toString(), e);
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        } 
//        finally {
//            if (resTabs != null) {
//                resData = new ResponseData(header);
//                resData.setResTab(resTabs);
//            }
//        }

        return errTabBean;
    }

    /**
     * 设置解析数据对象.
     * 
     * @param action 解析数据对象
     */
    public void setAction(IActionFace action) {
        mAction = action;
    }
    
    /**
     * 由request生成查询条件.
     * 把request中的数据保存在执行sql所需的map中
     * 
     * @param param 用户请求对象
     * @return 查询条件
     */
    private Map<String, Object> createCondition(Param param) {
        // sql中的动态参数保存对象
        final Map<String, Object> whereMap = new HashMap<String, Object>();
        final Map<String, String[]> paramMaps = param.getReqTable();
        for (String key : paramMaps.keySet()) {
            whereMap.put(key, CommonUtil.trim(paramMaps.get(key)[0]));
        }
        return whereMap;
    }
    
    /**
     * 由request生成查询条件.
     * 
     * @param tab 数据库查询结果对象
     * @param param 用户请求对象
     */
    private void createCondition(TableRowMap tab, Param param) {
        // sql中的动态参数保存对象
        final Map<String, String[]> paramMaps = param.getReqTable();
        for (String key : paramMaps.keySet()) {
            tab.getDataMap().put(key, CommonUtil.trim(paramMaps.get(key)[0]));
        }
    }
    
    /**
     * 由sql结果生成查询条件.
     * 
     * @param whereMap 条件容器
     * @param tab 数据
     */
    private void createCondition(Map<String, Object> whereMap, TableRowMap tab) {
        for (String key : tab.keySet()) {
            whereMap.put(key, CommonUtil.toString(tab.get(key)));
        }
    }
    
    /**
     * 由<params>中的数据生成查询条件.
     * 
     * @param whereMap 条件容器
     * @param parameter <params>中的数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param tab 数据库查询数据对象
     * @throws RichClientWebException RichClientWebException
     */
    private void createParams(
            Map<String, Object> whereMap, 
            com.richClientFrame.data.param.RequestParam.Param parameter, 
            Param param, 
            SessionData seData, 
            TableRowMap tab) throws RichClientWebException {
        final String key = parameter.getKey();
        String paramValue = parameter.getValue();
        final boolean keepValue = parameter.isKeep();
        Object value = null;
        
        final boolean isList = parameter.isList();
        
        if (isList) {
            if (paramValue.startsWith(ConstantsUtil.Xsd.REQUEST_SIGN)) {
                paramValue = paramValue.substring(1);
            }
            value = param.getList(paramValue);
        } else {
            final boolean sessionAllowNull = parameter.isSessionAllowNull();
            param.setValue(ConstantsUtil.Xsd.PARAM_SESSION_ALLOW_NULL, sessionAllowNull);
            value = RequestConfigUtil.getRequestValue(tab, param, seData, paramValue);
        }
        
        if (CommonUtil.isNotEmpty(value)) {
            value = executeFormat(parameter, value);
        }
        whereMap.put(key, value);
        if (keepValue && tab != null) {
            tab.put(key, value);
        }
    }
    
    /**
     * 通过<cmbs>中的数据生成下拉框信息.
     * 
     * @param cmb <cmbs>中的数据
     * @param tablist 画面信息绑定容器
     * @param param 用户请求对象
     * @throws RichClientWebException RichClientWebException
     */
    private void createCmb(Cmb cmb, List<TableRowMap> tablist, Param param) 
        throws RichClientWebException {
        createCmb(cmb, tablist, param, null);
    }
    
    /**
     * 通过<cmbs>中的数据生成下拉框信息.
     * 
     * @param cmb <cmbs>中的数据
     * @param tablist 画面信息绑定容器
     * @param param 用户请求对象
     * @param tabData 数据查询信息
     * @throws RichClientWebException RichClientWebException
     */
    private void createCmb(Cmb cmb, List<TableRowMap> tablist, Param param, TableRowMap tabData) 
        throws RichClientWebException {
        final List<TableRowMap> options = cmb.getOptions();
        if (options != null & options.size() > 0) {
            tablist.addAll(options);
        }
        final String sql = cmb.getSql();
        final String source = cmb.getSource();
        if (CommonUtil.isNotEmpty(sql) && getDb() != null) {
            final Map<String, Object> whereMap = createCondition(param);
            if (tabData != null) {
                // TODO 对应下拉框二级联动二级菜单查询条件的KEY,如果有多个条件KEY用空格分隔，之后应用<param>标签替代此属性
                if (CommonUtil.isNotEmpty(cmb.getKey())) {
                    final String[] cmbKeys = cmb.getKey().split(ConstantsUtil.Str.BLANK);
                    for (int k = 0; k < cmbKeys.length; k++) {
                        whereMap.put(cmbKeys[k], tabData.getString(cmbKeys[k]));
                    }
                }
            }
            TableRowMap[] tabs = null;
            if (CommonUtil.isEmpty(source)) {
                tabs = getDb().querys(sql, whereMap);
            } else {
                tabs = getDb().get(source).querys(sql, whereMap);
            }
            if (tabs != null) {
                tablist.addAll(Arrays.asList(tabs));
            }
        }
        createCmbInfoByMark(cmb, tablist);
    }

    /**
     * @Description: 通过<cmb>标签的静态属性添加下拉框信息
     * @author king
     * @since Dec 13, 2012 10:36:02 AM 
     * @version V1.0
     * @param cmb 下拉框标签对象
     * @param tablist 生成下拉框内容对象
     */
    private void createCmbInfoByMark(Cmb cmb, List<TableRowMap> tablist) {
        final String value = cmb.getValue();
        final String label = cmb.getLabel();
        final int count = cmb.getCount();
        int start = cmb.getStart();
        int end = cmb.getEnd();
        if (count <= 0 && start <= 0 && end <= 0) {
            return;
        }
        if (start < 0) {
            start = 0;
        }
        if (end <= 0) {
            if (count <= 0) {
                return;
            } else {
                end = count;
                if (start > end) {
                    return;
                }
            }
        }
        for (int i = start; i < end; i++) {
            final TableRowMap tab = new TableRowMap();
            String tabValue = ConstantsUtil.Str.EMPTY;
            if (CommonUtil.isNotEmpty(value)) {
                tabValue = value + i;
            } else {
                tabValue = CommonUtil.toString(i);
            }
            String tabLable = ConstantsUtil.Str.EMPTY;
            if (CommonUtil.isNotEmpty(label)) {
                tabLable = label + i;
            } else {
                tabLable = CommonUtil.toString(i);
            }
            tab.put(ConstantsUtil.Cmb.VALUE, tabValue);
            tab.put(ConstantsUtil.Cmb.LABEL, tabLable);
            tablist.add(tab);
        }
    }
    
    /**
     * 加载在<results>标签中的数据，使之作用到数据查询结果中.
     * 
     * @param tabs 数据列表
     * @param action <results>标签中的数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @throws RichClientWebException RichClientWebException
     */
    private void reloadResult(List<TableRowMap> tabs, IActionStep action, Param param, SessionData seData) 
        throws RichClientWebException {
        if (tabs != null) {
            for (int i = 0; i < tabs.size(); i++) {
                final TableRowMap tab = tabs.get(i);
                tab.putAll(reloadResult(tab, action, param, seData));
            }
        }
    }
    
    /**
     * 加载在<results>标签中的数据，使之作用到数据查询结果中.
     * 
     * @param tab 数据
     * @param action <results>标签中的数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 画面显示的数据集合
     * @throws RichClientWebException RichClientWebException
     */
    private TableRowMap reloadResult(TableRowMap tab, IActionStep action, Param param, SessionData seData) 
        throws RichClientWebException {
        
        if (action == null) {
            return tab;
        }
        
        final List<Result> results = action.getResults();
        
        // 用作判断的数据容器
        TableRowMap judgeTab = new TableRowMap();
        
        // 把需要加载到结果数据集中的数据容器先赋给判断的数据容器
        judgeTab = tab;
        
        // 如果需要加载到结果数据集中的数据容器为空，先创建
        if (tab == null) {
            tab = new TableRowMap();
        }
        
        if (results == null || results.size() <= 0) {
            return tab;
        }
        
        boolean clearFlg = true;
        
        // 用作保存数据的临时容器，在<results>标签中设置了appoint=true时
        final TableRowMap appointTab = new TableRowMap();
        
        // 遍历<results>标签中的<result>数据
        for (int i = 0; i < results.size(); i++) {
            
            // 取得<result>数据对象
            final Result result = results.get(i);
            
            // 根据条件判断是否给画面赋值
            if (judgeOperator(result, judgeTab, param, seData)) {
                // 是否强制输出<result>标签中定义的属性<appoint>，如果为true，非<result>标签中的项目将不被输出
                if (action.isResultAppoint()) {
                    if (clearFlg) {
                        appointTab.putAll(tab);
                        tab.clear();
                        clearFlg = false;
                    }
                    setResultValues(tab, param, seData, result, appointTab);
                } else {
                    setResultValues(tab, param, seData, result, tab);
                }
            }
        }
        return tab;
    }

    /**
     * 加载在<results>标签中的数据，使之作用到数据查询结果中.
     * 
     * @param tab 数据保存对象
     * @param result <result>标签中的数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param valueTab 数据来源对象
     * @throws RichClientWebException RichClientWebException
     */
    private void setResultValues(
            TableRowMap tab, 
            Param param, 
            SessionData seData,
            final Result result, 
            TableRowMap valueTab) throws RichClientWebException {
        
        // 取得<result>数据对象的【targetId】属性值，在单项目，此属性为画面控件的ID；在多项目中，是自定义标签中的对象ID
        final String targetId = result.getTargetId();
        Object value = ConstantsUtil.Str.EMPTY;
        // 如果<result>标签中的内容作为列表数据返回
        if (result.isList()) {
            tab.getListMap().put(targetId, targetId);
            final String resultValue = result.getValue();
            if (resultValue.startsWith(ConstantsUtil.Xsd.DB_SIGN) && !ConstantsUtil.Xsd.RESULT.equals(resultValue)) {
                if (valueTab.containsKey(resultValue.substring(1))) {
                    value = valueTab.get(resultValue.substring(1));
                } else if (valueTab.containsKey(resultValue.substring(1).toUpperCase())) {
                    value = valueTab.get(resultValue.substring(1).toUpperCase());
                }
            }
        } else {
            // 根据<result>标签取得要对结果进行操作的值
            value = getResultValue(result, valueTab, param, seData);
            if (targetId.startsWith(ConstantsUtil.Xsd.USER)) {
                UserInfo userInfo = seData.getUserInfo();
                if (userInfo == null) {
                    userInfo = new UserInfo();
                }
                final String userKey = targetId.substring(ConstantsUtil.Xsd.USER.length());
                userInfo.setUserInfo(userKey, CommonUtil.toString(value));
                seData.setUserInfo(userInfo);
                return;
            }
        }
        value = executeFormat(result, value);
        // 给画面对象赋值
        tab.put(targetId, value);
    }
    
    /**
     * 加载在<params>标签中的数据，使之作用到数据查询条件中.
     * 
     * @param whereMap 条件容器
     * @param params <params>中的数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @throws RichClientWebException RichClientWebException
     */
    private void reloadParams(Map<String, Object> whereMap, 
            List<com.richClientFrame.data.param.RequestParam.Param> params, 
            Param param, SessionData seData) 
        throws RichClientWebException {
        reloadParams(whereMap, params, param, seData, null);
    }
    
    /**
     * 加载在<params>标签中的数据，使之作用到数据查询条件中.
     * 
     * @param whereMap 条件容器
     * @param params <params>中的数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param tab 数据库查询对象
     * @throws RichClientWebException RichClientWebException
     */
    private void reloadParams(Map<String, Object> whereMap, 
            List<com.richClientFrame.data.param.RequestParam.Param> params, 
            Param param, SessionData seData, TableRowMap tab) 
        throws RichClientWebException {
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                final com.richClientFrame.data.param.RequestParam.Param parameter = params.get(i);
                if (judgeOperator(parameter, null, param, seData)) {
                    createParams(whereMap, parameter, param, seData, tab);
                }
            }
        }
    }
    
    
    
    /**
     * 根据<result>标签取得要对结果进行操作的值.
     * 
     * @param result 数据结果对象
     * @param tab 数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 返回操作后的结果
     * @throws RichClientWebException RichClientWebException
     */
    private String getResultValue(Result result, TableRowMap tab, Param param, SessionData seData) 
        throws RichClientWebException {
        final String operator = result.getOperator();
        String change = CommonUtil.toString(result.getValue());
        if (change.indexOf(ConstantsUtil.Str.BLANK) != -1 && CommonUtil.isNotEmpty(operator)) {
            final String[] changes = change.split(ConstantsUtil.Str.BLANK);
            if ("set".equals(operator)) {
                change = RequestConfigUtil.getRequestValue(tab, param, seData, changes[0]);
            } else if ("add".equals(operator)) {
                final StringBuffer changeBuff = new StringBuffer();
                for (int i = 0; i < changes.length; i++) {
                    changeBuff.append(RequestConfigUtil.getRequestValue(tab, param, seData, changes[i]));
                }
                change = changeBuff.toString();
            }
        } else {
            change = RequestConfigUtil.getRequestValue(tab, param, seData, change);
        }
        return change;
    }
    
    /**
     * 把查询条件对象的值设置到画面数据容器中.
     * 
     * @param whereMap 查询条件对象
     * @param tab 画面数据容器
     */
//    private void setWhereMapToTab(Map<String, Object> whereMap, TableRowMap tab) {
//        if (whereMap != null && tab != null) {
//            for (String key : whereMap.keySet()) {
//                tab.put(key, whereMap.get(key));
//            }
//        }
//    }

    /**
     * 初始化机能信息
     * @param param 用户请求对象
     * @param seData 用户会话对象
     */
    private void initializeAction(Param param, SessionData seData) {
        if (mAction != null) {
            mAction.setMemcached(mMemcached);
            mAction.setExternal(mExecute);
            mAction.setParam(param);
            mAction.setSeData(seData);
            mAction.setFormat(mFormat);
            mAction.setDataSource(mDataSource);
            mAction.setDb(db);
            mAction.setApplication(context);
        }
    }
    
    /**
     * 初始化机能信息
     * @param param 用户请求对象
     * @param seData 用户会话对象
     */
    private void initializeExpand(Param param, SessionData seData) {
        if (mExpand != null) {
            if (db != null) {
                db.setExpand(mExpand);
            }
            mExpand.setMemcached(mMemcached);
            mExpand.setExternal(mExecute);
            mExpand.setParam(param);
            mExpand.setSeData(seData);
            mExpand.setFormat(mFormat);
            mExpand.setDataSource(mDataSource);
            mExpand.setDb(db);
        }
    }
    
    /**
     * 初始化handler对象.
     * @param param 用户请求对象
     * @param seData 用户会话对象
     */
    private void initializeHandler(Param param, SessionData seData) {
        initializeFormat(param, seData);
        initializeDataSource(param, seData);
        initializeExternal(param, seData);
        initializeAction(param, seData);
        initializeExpand(param, seData);
    }
    
    /**
     * 初始化格式化对象
     * @param param 用户请求对象
     * @param seData 用户会话对象
     */
    private void initializeFormat(Param param, SessionData seData) {
        if (mFormat != null) {
            mFormat.setMemcached(mMemcached);
            mFormat.setParam(param);
            mFormat.setSeData(seData);
            mFormat.setDb(db);
        }
    }
    
    /**
     * 初始化格式化对象
     * @param param 用户请求对象
     * @param seData 用户会话对象
     */
    private void initializeDataSource(Param param, SessionData seData) {
        if (mDataSource != null) {
            mDataSource.setParam(param);
            mDataSource.setSeData(seData);
            mDataSource.setMemcached(mMemcached);
            mDataSource.setDb(db);
        }
    }
    
    /**
     * 初始化机能信息
     * @param param 用户请求对象
     * @param seData 用户会话对象
     */
    private void initializeExternal(Param param, SessionData seData) {
        if (mExecute != null) {
            mExecute.setMemcached(mMemcached);
            mExecute.setParam(param);
            mExecute.setSeData(seData);
            mExecute.setDb(db);
        }
    }
    
    /**
     * 下拉框信息创建方法.
     * 
     * @param cmb <cmb>标签对象
     * @param param 用户请求对象
     * @return 下拉框信息
     * @throws RichClientWebException RichClientWebException
     */
    private TableRowMap[] createCmbInfo(Cmb cmb, Param param) throws RichClientWebException {
        return createCmbInfo(cmb, param, null);
    }
    
    /**
     * 下拉框信息创建方法.
     * 
     * @param cmb <cmb>标签对象
     * @param param 用户请求对象
     * @param tabData 数据库数据
     * @return 下拉框信息
     * @throws RichClientWebException RichClientWebException
     */
    private TableRowMap[] createCmbInfo(Cmb cmb, Param param, TableRowMap tabData) throws RichClientWebException {
        final List<TableRowMap> tablist = new ArrayList<TableRowMap>();
        createCmb(cmb, tablist, param, tabData);
        if (CommonUtil.isNotEmpty(cmb.getId())) {
            final Cmb cmbRes = ControlRequestMap.getInstance().getCmbResource(cmb.getId());
            createCmb(cmbRes, tablist, param);
        }
        final TableRowMap[] tabs = tablist.toArray(new TableRowMap[0]);
        return tabs;
    }
    
    /**
     * 下拉框信息创建方法【showPage画面初始化的时候】.
     * 
     * @param cmb <cmb>标签对象
     * @param param 用户请求对象
     * @return 下拉框信息
     * @throws RichClientWebException RichClientWebException
     */
    private AbstractResponseData createCmbInfoForShow(Cmb cmb, Param param) throws RichClientWebException {
        final List<TableRowMap> tablist = new ArrayList<TableRowMap>();
        final String targetId = cmb.getTargetId();
        createCmb(cmb, tablist, param);
        if (CommonUtil.isNotEmpty(cmb.getId())) {
            final Cmb cmbRes = ControlRequestMap.getInstance().getCmbResource(cmb.getId());
            createCmb(cmbRes, tablist, param);
        }
        final TableRowMap[] tabs = tablist.toArray(new TableRowMap[0]);
        return mHtmlTab.showCmbs(targetId, tabs, null);
    }
    
    /**
     * 读取excel的方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param excel 对应<excel>标签中的信息对象
     * @param tabs 数据信息组
     * @throws RichClientWebException RichClientWebException
     */
    private void readExcel(Param param, SessionData seData, IExcel excel, List<TableRowMap> tabs) 
        throws RichClientWebException {
        readExcel(param, seData, excel, tabs, null);
    }
    
    /**
     * 读取excel的方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param excel 对应<excel>标签中的信息对象
     * @param tab 数据信息
     * @throws RichClientWebException RichClientWebException
     */
    private void readExcel(Param param, SessionData seData, IExcel excel, TableRowMap tab) 
        throws RichClientWebException {
        readExcel(param, seData, excel, tab, null);
    }
    
    /**
     * 读取excel的方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param excel 对应<excel>标签中的信息对象
     * @param tab 数据信息
     * @param inputStream 读取文件流
     * @throws RichClientWebException RichClientWebException
     */
    private void readExcel(Param param, SessionData seData, IExcel excel, TableRowMap tab, InputStream inputStream) 
        throws RichClientWebException {
        
        if (excel == null) {
            return;
        }
        final Excel excelItem = excel.getExcel();
        if (excelItem == null) {
            return;
        }
        final String type = excelItem.getType();
        if (ConstantsUtil.Excel.TYPE_READ.equals(type)) {
            if (ExcelUtil.hasExcelFile(excelItem, inputStream)) {
                Workbook wb = null;
                if (inputStream != null) {
                    wb = ExcelUtil.readExcelFile(inputStream, excelItem.getFileType());
                } else {
                    wb = ExcelUtil.createExcelFile(excelItem);
                }
                if (wb != null) {
                    final List<Sheet> sheets = excelItem.getSheets();
                    for (int i = 0; i < sheets.size(); i++) {
                        final Sheet sheet = sheets.get(i);
                        final String sheetName = sheet.getName();
                        final org.apache.poi.ss.usermodel.Sheet sheetPoi = wb.getSheet(sheetName);
                        final List<Row> rows = sheet.getRows();
                        for (int j = 0; j < rows.size(); j++) {
                            final Row row = rows.get(j);
                            final int rowSite = row.getSite();
                            final org.apache.poi.ss.usermodel.Row rowItem = sheetPoi.getRow(rowSite);
                            final List<Cell> cells = row.getCells();
                            for (int k = 0; k < cells.size(); k++) {
                                final Cell cell = cells.get(k);
                                ExcelUtil.readCellValue(tab, rowItem, cell, sheetPoi, getPalette(wb));
                            }
                        }
                    }
                }
            } else {
                throw new RichClientWebException(EngineExceptionEnum.FM_EXCEL_XML_SET_READ_MUST_SET_IN);
            }
        }
    }
    
    /**
     * 读取excel的方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param excel 对应<excel>标签中的信息对象
     * @param tabs 数据信息
     * @param inputStream 读取文件流
     * @throws RichClientWebException RichClientWebException
     */
    private void readExcel(
            Param param, SessionData seData, IExcel excel, List<TableRowMap> tabs, InputStream inputStream) 
        throws RichClientWebException {
        
        if (excel == null) {
            return;
        }
        final Excel excelItem = excel.getExcel();
        if (excelItem == null) {
            return;
        }
        final String type = excelItem.getType();
        if (ConstantsUtil.Excel.TYPE_READ.equals(type)) {
            if (ExcelUtil.hasExcelFile(excelItem, inputStream)) {
                Workbook wb = null;
                if (inputStream != null) {
                    wb = ExcelUtil.readExcelFile(inputStream, excelItem.getFileType());
                } else {
                    wb = ExcelUtil.createExcelFile(excelItem);
                }
                if (wb != null) {
                    final List<Sheet> sheets = excelItem.getSheets();
                    for (int i = 0; i < sheets.size(); i++) {
                        final Sheet sheet = sheets.get(i);
                        final String sheetName = sheet.getName();
                        if (ConstantsUtil.Str.STAR.equals(sheetName)) {
                            for (int j = 0; j < wb.getNumberOfSheets(); j++) {
                                final org.apache.poi.ss.usermodel.Sheet sheetPoi = wb.getSheetAt(j);
                                final String sheetNameValue = sheetPoi.getSheetName();
                                final TableRowMap sheetTab = new TableRowMap();
                                final List<TableRowMap> sheetTabs = new ArrayList<TableRowMap>();
                                getExcelSheetDatas(sheetTabs, wb, sheet, sheetPoi);
                                sheetTab.put(ConstantsUtil.Excel.SHEET_NAME_KEY, sheetNameValue);
                                sheetTab.setTabValue(sheetTabs);
                                tabs.add(sheetTab);
                            }
                        } else {
                            org.apache.poi.ss.usermodel.Sheet sheetPoi = wb.getSheet(sheetName);
                            if (sheetPoi == null) {
                                sheetPoi = wb.getSheetAt(0);
                            }
                            getExcelSheetDatas(tabs, wb, sheet, sheetPoi);
                        }
                    }
                }
            } else {
                throw new RichClientWebException(EngineExceptionEnum.FM_EXCEL_XML_SET_READ_MUST_SET_IN);
            }
        }
    }

    /**
     * @Description: 
     * @author king
     * @since Jul 30, 2013 11:13:26 AM 
     * @version V1.0
     * @param tabs tabs
     * @param wb wb
     * @param sheet sheet
     * @param sheetPoi sheetPoi
     */
    private void getExcelSheetDatas(
            List<TableRowMap> tabs, Workbook wb, final Sheet sheet,
            final org.apache.poi.ss.usermodel.Sheet sheetPoi) {
        final List<Row> rows = sheet.getRows();
        for (int j = 0; j < rows.size(); j++) {
            final Row row = rows.get(j);
            final int rowSite = row.getSite();
            for (int k = rowSite; k < sheetPoi.getLastRowNum() + 1; k++) {
                final TableRowMap rowTab = new TableRowMap();
                final org.apache.poi.ss.usermodel.Row rowPoi = sheetPoi.getRow(k);
                if (rowPoi == null) {
                    continue;
                }
                final List<Cell> cells = row.getCells();
                for (int m = 0; m < cells.size(); m++) {
                    final Cell cell = cells.get(m);
                    ExcelUtil.readCellValue(rowTab, rowPoi, cell, sheetPoi, getPalette(wb));
                }
                rowTab.put(ConstantsUtil.Excel.ROW_SIT_KEY, k);
                tabs.add(rowTab);
            }
        }
    }

    /**
     * @Description: 
     * @author king
     * @since Jul 31, 2013 4:18:50 PM 
     * @version V1.0
     * @param wb wb
     * @return Palette
     */
    private HSSFPalette getPalette(Workbook wb) {
        HSSFPalette palette = null;
        if (wb instanceof HSSFWorkbook) {
            palette = ((HSSFWorkbook)wb).getCustomPalette();
        }
        return palette;
    }
    
    /**
     * 生成excel的方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param excel 对应<excel>标签中的信息对象
     * @param tabs 数据信息组
     * @return excel生成信息
     * @throws RichClientWebException RichClientWebException
     */
    private ResponseExcel writeExcel(Param param, SessionData seData, IExcel excel, List<TableRowMap> tabs) 
        throws RichClientWebException {
        LOG.debug("writeExcel", "start", "tabs size = " + tabs.size());
        final Excel excelItem = excel.getExcel();
        if (excelItem == null) {
            return null;
        }
        // excel操作类型[TYPE_WRITE：生成excel文件保存到服务器；TYPE_STREAM：把excel通过流的方式提供给客户端]
        final String type = CommonUtil.evl(excelItem.getType(), ConstantsUtil.Excel.TYPE_STREAM);
        
        if (!ConstantsUtil.Excel.TYPE_WRITE.equals(type) && !ConstantsUtil.Excel.TYPE_STREAM.equals(type)) {
            return null;
        }
        
        final List<File> filesList = new ArrayList<File>();
        final boolean autoZipEnabled = ControlConfig.getInstance().getConfiguration().isAutoZipEnabled();
        Workbook wb = null;
        final int cellSize = excelItem.getSheets().get(0).getRows().get(0).getCells().size();
        final int excelMaxSize = ControlConfig.getInstance().getConfiguration().getExcelMaxSize();
        final int autoRange = cellSize * tabs.size() / excelMaxSize;
        if (autoZipEnabled && autoRange > 0) {
            // 生成excel zip
            writeExcel(param, seData, excelItem, tabs, filesList);
        } else {
            // 创建excel容器对象
            wb = ExcelUtil.createExcelFile(excelItem);
            // 生成excel
            writeExcel(param, seData, excelItem, tabs, wb);
        }
        if (autoZipEnabled && autoRange > 0) {
            return ExcelUtil.createExcelFile(excelItem, type, filesList);
            
        }
        LOG.debug("writeExcel", "end", "type = " + type);
        return ExcelUtil.createExcelFile(excelItem, type, wb);
    }
    
    /**
     * 生成excel的方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param excelItem 对应<excel>标签中的信息对象
     * @param tabs 数据信息组
     * @param wb excel对象
     * @throws RichClientWebException RichClientWebException
     */
    private void writeExcel(
            Param param, SessionData seData, Excel excelItem, List<TableRowMap> tabs, Workbook wb) 
        throws RichClientWebException {
        for (int i = 0; i < tabs.size(); i++) {
            createExcelContent(param, seData, tabs.get(i), excelItem, wb, i);
        }
    }
    
    /**
     * 生成excel的方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param excelItem 对应<excel>标签中的信息对象
     * @param tabs 数据信息组
     * @param filesList excel文件数组
     * @throws RichClientWebException RichClientWebException
     */
    private void writeExcel(
            Param param, SessionData seData, Excel excelItem, List<TableRowMap> tabs, List<File> filesList) 
        throws RichClientWebException {
        // 创建excel容器对象
        int index = 0;
        int accumulation = 0;
        final int cellSize = excelItem.getSheets().get(0).getRows().get(0).getCells().size();
        Workbook wb = ExcelUtil.createExcelFile(excelItem);
        String fileName = excelItem.getOutName();
        if (CommonUtil.isEmpty(fileName)) {
            fileName = "excel";
        }
        final String folderPath = ControlConfig.getRealPath() + "temp" 
            + File.separator + DateUtils.getNowTime(DateUtils.FORMAT_YMD2);
        FileUtil.createFolder(folderPath);
        final int excelMaxSize = ControlConfig.getInstance().getConfiguration().getExcelMaxSize();
        for (int i = 0; i < tabs.size(); i++) {
            if ((cellSize * i / excelMaxSize) > index) {
                createExcelFileForZip(excelItem, filesList, wb, folderPath);
                index++;
                accumulation = 0;
                wb = ExcelUtil.createExcelFile(excelItem);
            }
            createExcelContent(param, seData, tabs.get(i), excelItem, wb, accumulation);
            accumulation++;
        }
        createExcelFileForZip(excelItem, filesList, wb, folderPath);
    }

    /**
     * @Description: 根据信息内容生成要打zip包的excel文件
     * @author king
     * @since Nov 27, 2012 11:37:37 AM 
     * @version V1.0
     * @param excelItem excel对象
     * @param filesList 保存文件的容器对象
     * @param wb 生成excel的对象
     * @param folderPath 文件夹路径
     */
    private void createExcelFileForZip(Excel excelItem, List<File> filesList, Workbook wb,
            final String folderPath) {
        final String path = folderPath + File.separator 
            + FileUtil.createFileId(DateUtils.getNowTime(DateUtils.FORMAT_YYYYMMDDHHSS3)) 
            + ConstantsUtil.Str.DOT + excelItem.getFileType();
        final File file = new File(path);
        try {
            wb.write(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        filesList.add(file);
    }
    
    /**
     * 生成excel的方法.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param excel 对应<excel>标签中的信息对象
     * @param tab 数据信息
     * @return excel生成信息
     * @throws RichClientWebException RichClientWebException
     */
    private ResponseExcel writeExcel(Param param, SessionData seData, IExcel excel, TableRowMap tab) 
        throws RichClientWebException {
        if (excel == null) {
            return null;
        }
        final Excel excelItem = excel.getExcel();
        if (excelItem == null) {
            return null;
        }
        String type = excelItem.getType();
        if (CommonUtil.isEmpty(type)) {
            type = ConstantsUtil.Excel.TYPE_STREAM;
        }
        if (!ConstantsUtil.Excel.TYPE_WRITE.equals(type) && !ConstantsUtil.Excel.TYPE_STREAM.equals(type)) {
            return null;
        }
        
        final Workbook outWb = ExcelUtil.createExcelFile(excelItem);
        
        createExcelContent(param, seData, tab, excelItem, outWb, 0);
        
        return ExcelUtil.createExcelFile(excelItem, type, outWb);
    }
    
    /**
     * 创建excel的内容
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param tab 数据信息
     * @param excelItem 对应<excel>标签中的信息对象
     * @param outWb excel对象
     * @param accumulation 根据返回多维信息的序列号
     * @throws RichClientWebException RichClientWebException
     */
    @SuppressWarnings("unchecked")
    private void createExcelContent(Param param, SessionData seData, TableRowMap tab,
            final Excel excelItem, Workbook outWb, int accumulation) throws RichClientWebException {
        LOG.debug("createExcelContent", "start", "accumulation = " + accumulation);
        // 取得<excel>标签设置<sheet>信息数组
        final List<Sheet> sheets = excelItem.getSheets();
        
        for (int i = 0; i < sheets.size(); i++) {
            final Sheet sheet = sheets.get(i);
            final String sheetName = sheet.getName();
            
            // 是否设置了excel路径或者读取了excel文件流
            boolean setValuesInExistExcel = ExcelUtil.hasExcelFile(excelItem, null);
            if (outWb.getSheet(sheetName) != null) {
                sheetItem = outWb.getSheet(sheetName);
            } else {
                sheetItem = outWb.createSheet(sheetName);
                setValuesInExistExcel = false;
            }
            final List<Row> rows = sheet.getRows();
            for (int j = 0; j < rows.size(); j++) {
                final Row row = rows.get(j);
                
                // 对应<row>标签中的<targetId>属性，此值为action返回信息的key
                final String targetId = row.getTargetId();
                if (CommonUtil.isNotEmpty(targetId)) {
                    final Object obj = tab.get(targetId);
                    if (obj instanceof List) {
                        final List<TableRowMap> tabList = (List<TableRowMap>)obj;
                        for (int k = 0; k < tabList.size(); k++) {
                            final TableRowMap tabRow = tabList.get(k);
                            if (setValuesInExistExcel) {
                                // 对已有的excel模板进行单元格的信息设置
                                setCellContent(param, seData, tabRow, outWb, sheetItem, row, k);
                            } else {
                                createCellContent(param, seData, tabRow, outWb, sheetItem, row, k);
                            }
                        }
                    }
                } else {
                    if (setValuesInExistExcel) {
                        // 对已有的excel模板进行单元格的信息设置
                        setCellContent(param, seData, tab, outWb, sheetItem, row, accumulation);
                    } else {
                        createCellContent(param, seData, tab, outWb, sheetItem, row, accumulation);
                    }
                }
            }
        }
        LOG.debug("createExcelContent", "end");
    }
    
    /**
     * 对已有的excel模板进行单元格的信息设置
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param tab 数据信息
     * @param sheetItem 对应excel模板对象的sheet对象
     * @param outWb excel对象
     * @param row 对应<row>标签中的信息对象
     * @param index 起始行数
     * @throws RichClientWebException RichClientWebException
     */
    private void setCellContent(
            Param param, 
            SessionData seData, 
            TableRowMap tab,
            Workbook outWb, 
            org.apache.poi.ss.usermodel.Sheet sheetItem, 
            final Row row, 
            final int index)
        throws RichClientWebException {
        LOG.info("setCellContent", "start", "index = " + index);
        // 强制excel在加载完成时调用公式
        sheetItem.setForceFormulaRecalculation(true);
        final int rowSite = ExcelUtil.countRowIndex(row, index);
        
        boolean alternate = row.isAlternate();
        if (!alternate) {
            alternate = ControlRequestMap.getInstance().getRowAlternateInResource();
        }
        
        // 从sheet中取得row对象
        rowItem = sheetItem.getRow(rowSite);
        
        // 如果row对象为空则创建
        if (rowItem == null) {
            rowItem = sheetItem.createRow(rowSite);
        }
        // 设置行高
        final float height = row.getHeight();
        if (height != -1) {
            rowItem.setHeightInPoints(height);
        }
        final List<Cell> cells = row.getCells();
        for (int k = 0; k < cells.size(); k++) {
            final Cell cell = cells.get(k);
            // 根据<condition>标签进行条件判断操作
            if (!judgeOperator(cell, tab, param, seData)) {
                continue;
            }
            final String value = cell.getValue();
            final int col = ExcelUtil.countColIndex(cell.getSite(), row, index, k);
            
            cellItem = rowItem.getCell(col);
            if (cellItem == null) {
                cellItem = rowItem.createCell(col);
            }
            if (alternate) {
                if (index % 2 == 0) {
                    String evenColorString = row.getEvenColor();
                    if (CommonUtil.isEmpty(evenColorString)) {
                        evenColorString = ControlRequestMap.getInstance().getRowEvenColorInResource();
                    }
                    if (CommonUtil.isNotEmpty(evenColorString)) {
                        ExcelUtil.setCellBackGroundStyle(outWb, cellItem, evenColorString);
                    }
                } else {
                    String oddColorString = row.getOddColor();
                    if (CommonUtil.isEmpty(oddColorString)) {
                        oddColorString = ControlRequestMap.getInstance().getRowOddColorInResource();
                    }
                    if (CommonUtil.isNotEmpty(oddColorString)) {
                        ExcelUtil.setCellBackGroundStyle(outWb, cellItem, oddColorString);
                    }
                }
            }
            final String type = cell.getType();
            if (CommonUtil.isNotEmpty(value)) {
                String cellValue = ConstantsUtil.Str.EMPTY;
                if ("%index%".equals(value)) {
                    cellValue = CommonUtil.toString(rowSite - row.getSite() + 1);
                } else {
                    cellValue = RequestConfigUtil.getRequestValue(tab, param, seData, value);
                }
                if ("CELL_TYPE_NUMERIC".equals(type)) {
                    if (CommonUtil.isEmpty(cellValue)) {
                        cellItem.setCellValue(ConstantsUtil.Str.EMPTY);
                    } else {
                        cellItem.setCellValue(Double.parseDouble(cellValue));
                    }
                } else {
                    cellItem.setCellValue(cellValue);
                }
            }
            ExcelUtil.setCellStyle(outWb, cellItem, cell, row);
            
            // 设置单元格的数据类型
            if (CommonUtil.isNotEmpty(type)) {
                cellItem.setCellType(ExcelUtil.getCellType(type));
            }
            final int width = cell.getWidth();
            if (width != -1) {
                final short widthPoint = (short) (width * 35.7);
                sheetItem.setColumnWidth(col, widthPoint);
            }
        }
        LOG.info("setCellContent", "end", "index = " + index);
    }
    
    /**
     * 创建单元格并进行单元格的信息设置
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param tab 数据信息
     * @param sheetItem 对应excel模板对象的sheet对象
     * @param outWb excel对象
     * @param row 对应<row>标签中的信息对象
     * @param index 起始行数
     * @throws RichClientWebException RichClientWebException
     */
    private void createCellContent(
            Param param, 
            SessionData seData, 
            TableRowMap tab,
            Workbook outWb, 
            org.apache.poi.ss.usermodel.Sheet sheetItem, 
            final Row row, 
            final int index)
        throws RichClientWebException {
        
        final int rowSite = ExcelUtil.countRowIndex(row, index);
        
        rowItem = sheetItem.createRow(rowSite);
        final float height = row.getHeight();
        if (height != -1) {
            rowItem.setHeightInPoints(height);
        }
        final List<Cell> cells = row.getCells();
        for (int k = 0; k < cells.size(); k++) {
            final Cell cell = cells.get(k);
            if (!judgeOperator(cell, tab, param, seData)) {
                continue;
            }
            final String value = cell.getValue();
            final int col = ExcelUtil.countColIndex(cell.getSite(), row, index, k);
            cellItem = rowItem.createCell(col);
            if (CommonUtil.isNotEmpty(value)) {
                String cellValue = ConstantsUtil.Str.EMPTY;
                if ("%index%".equals(value)) {
                    cellValue = CommonUtil.toString(rowSite - row.getSite() + 1);
                } else {
                    cellValue = RequestConfigUtil.getRequestValue(tab, param, seData, value);
                }
                cellItem.setCellValue(cellValue);
            }
            ExcelUtil.setCellStyle(outWb, cellItem, cell, row);
            final String type = cell.getType();
            if (CommonUtil.isNotEmpty(type)) {
                cellItem.setCellType(ExcelUtil.getCellType(type));
            }
            final int width = cell.getWidth();
            if (width != -1) {
                final short widthPoint = (short) (width * 35.7);
                sheetItem.setColumnWidth(col, widthPoint);
            }
        }
    }
    
    /**
     * 条件判断操作.
     * 
     * @param conditionFace 条件判断对象
     * @param tab 数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @throws RichClientWebException RichClientWebException
     * @return 判断后的结果
     */
    public Map<String, List<String>> judgeSiftOperator(
            ICondition conditionFace, TableRowMap tab, Param param, SessionData seData) 
        throws RichClientWebException {
        final List<Condition> conditions = conditionFace.getConditions();
        final String id = conditionFace.getConditionId();
        return judgeOperator(conditions, tab, param, seData, id);
    }
    
    /**
     * 根据<condition>标签进行条件判断操作.
     * 
     * @param conditionFace 条件判断对象
     * @param tab 数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @throws RichClientWebException RichClientWebException
     * @return 判断后的结果
     */
    public boolean judgeOperator(ICondition conditionFace, TableRowMap tab, Param param, SessionData seData) 
        throws RichClientWebException {
        return judgeOperator(conditionFace, tab, param, seData, null);
    }
    
    /**
     * 条件判断操作.
     * 
     * @param conditionFace 条件判断对象
     * @param tab 数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param targetId 画面控件ID
     * @throws RichClientWebException RichClientWebException
     * @return 判断后的结果
     */
    private boolean judgeOperator(
            ICondition conditionFace, TableRowMap tab, Param param, SessionData seData, String targetId) 
        throws RichClientWebException {
        final List<Condition> conditions = conditionFace.getConditions();
        final String id = conditionFace.getConditionId();
        final Map<String, List<String>> checkResult = judgeOperator(conditions, tab, param, seData, id, targetId);
        if (checkResult == null) {
            return true;
        }
        return false;
    }
    
    /**
     * 条件判断操作.
     * 
     * @param conditions 条件判断数组
     * @param tab 数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param id 条件判断总ID
     * @throws RichClientWebException RichClientWebException
     * @return 判断后的结果【true：符合条件；false：不符合条件】
     */
    public Map<String, List<String>> judgeOperator(
            List<Condition> conditions, TableRowMap tab, Param param, SessionData seData, String id) 
        throws RichClientWebException {
        return judgeOperator(conditions, tab, param, seData, id, null);
    }
    
    /**
     * 条件判断操作.
     * 
     * @param conditions 条件判断数组
     * @param tab 数据
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param id 条件判断总ID
     * @param targetId 画面控件ID
     * @throws RichClientWebException RichClientWebException
     * @return 判断后的结果【true：符合条件；false：不符合条件】
     */
    private Map<String, List<String>> judgeOperator(
            List<Condition> conditions, TableRowMap tab, Param param, SessionData seData, String id, String targetId) 
        throws RichClientWebException {
        
        Map<String, List<String>> judgeMap = null;
        
        // 控制验证数据条目序号的控件ID
        final String checkIndexId = ControlRequestMap.getInstance().getCheckIndex(param);
        
        TableRowMap judegTab = tab;
        if (conditions != null && conditions.size() > 0) {
            for (int i = 0; i < conditions.size(); i++) {
                
                final Condition condition = conditions.get(i);
                
                final Map<String, Object> whereMap = createCondition(param);
                
                final boolean isExecute = doActionPre(condition, param, seData, whereMap);
                
                judegTab = executeSql(judegTab, condition, whereMap, isExecute);
                
                // 把condition标签中sql查询的结果保存在结果数据容器中
                if (judegTab != null && tab != null) {
                    tab.putAll(judegTab);
                }
                
                // value的取值范围，确定被比较的值
                final Compare compareSource = condition.getCompareSource();
                final String compareSourceStr = compareSource.getValue();
                
                if (CommonUtil.isNotEmpty(checkIndexId)) {
                    final String[] checkIndexs = param.getList(checkIndexId);
                    
                    if (checkIndexs == null) {
                        return null;
                    }
                    if (!compareSourceStr.startsWith(ConstantsUtil.Xsd.REQUEST_SIGN)) {
                        continue;
                    }
                    final List<String> checkList = new ArrayList<String>();
                    for (int j = 0; j < checkIndexs.length; j++) {
                        final String index = checkIndexs[j];
                        if (!judgeOperator(param, seData, judegTab, condition, id, index)) {
                            if (judgeMap == null) {
                                judgeMap = new HashMap<String, List<String>>();
                            }
                            checkList.add(index);
                        }
                    }
                    if (judgeMap != null) {
                        judgeMap.put(compareSourceStr.substring(1), checkList);
                    }
                } else {
                    if (!judgeOperator(param, seData, judegTab, condition, id, null, targetId)) {
                        judgeMap = new HashMap<String, List<String>>();
                    }
                }
                
            }
        }
        return judgeMap;
    }

    /**
     * @Description: 
     * @author king
     * @since Jul 8, 2013 3:19:33 PM 
     * @version V1.0
     * @param judegTab 判断用数据容器对象
     * @param condition condition标签对象
     * @param whereMap 条件对象
     * @param isExecute 是否执行sql标识
     * @return 判断用数据容器对象
     * @throws RichClientWebException RichClientWebException
     */
    private TableRowMap executeSql(TableRowMap judegTab, final Condition condition,
            final Map<String, Object> whereMap, final boolean isExecute)
        throws RichClientWebException {
        final String sql = condition.getSql();
        final String source = condition.getSource();
        if (CommonUtil.isNotEmpty(sql) && getDb() != null && isExecute) {
            if (CommonUtil.isEmpty(source)) {
                judegTab = getDb().query(sql, whereMap);
            } else {
                judegTab = getDb().get(source).query(sql, whereMap);
            }
        }
        return judegTab;
    }
    
    /**
     * 条件判断操作.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param condition 条件判断对象
     * @param judegTab 数据
     * @param id 条件判断总ID
     * @param index 控件序列号
     * @throws RichClientWebException RichClientWebException
     * @return 判断后的结果【true：符合条件；false：不符合条件】
     */
    private boolean judgeOperator(
            Param param, 
            SessionData seData, 
            TableRowMap judegTab, 
            Condition condition, 
            String id, 
            String index) 
        throws RichClientWebException {
        return judgeOperator(param, seData, judegTab, condition, id, index, null);
    }
    
    /**
     * 条件判断操作.
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param condition 条件判断对象
     * @param judegTab 数据
     * @param id 条件判断总ID
     * @param index 控件序列号
     * @param targetId 画面控件ID
     * @throws RichClientWebException RichClientWebException
     * @return 判断后的结果【true：符合条件；false：不符合条件】
     */
    private boolean judgeOperator(
            Param param, 
            SessionData seData, 
            TableRowMap judegTab, 
            Condition condition, 
            String id, 
            String index, String targetId) 
        throws RichClientWebException {
        
        boolean judge = true;
        
        // 判断符号
        final String operator = condition.getOperator();
        
        // value的取值范围，确定被比较的值
        final Compare compareSource = condition.getCompareSource();
        final String compareSourceStr = compareSource.getValue();
        
        // 比较值
        final Compare compareTarget = condition.getCompareTarget();
        String compareTargetStr = ConstantsUtil.Str.EMPTY;
        if (compareTarget != null) {
            compareTargetStr = compareTarget.getValue();
        }
        // 根据标签中的符号返回相应的值
        String compareSourceValue = RequestConfigUtil.getRequestValue(
                judegTab, param, seData, compareSourceStr, index, targetId);
        // 根据标签中的符号返回相应的值
        String compareTargetValue = RequestConfigUtil.getRequestValue(
                judegTab, param, seData, compareTargetStr, index, targetId);
        // 调用格式化对象取值
        compareSourceValue = CommonUtil.toString(executeFormat(compareSource, compareSourceValue));
        // 调用格式化对象取值
        compareTargetValue = CommonUtil.toString(executeFormat(compareTarget, compareTargetValue));
        
        if (ConstantsUtil.Xsd.RESULT.equals(compareSourceValue)) {
            if ("empty".equals(operator)) {
                if (judegTab != null) {
                    return false;
                }
            } else if ("notEmpty".equals(operator)) {
                if (judegTab == null) {
                    return false;
                }
            }
        } else {
            if ("empty".equals(operator)) {
                if (CommonUtil.isNotEmpty(compareSourceValue)) {
                    return false;
                }
            } else if ("notEmpty".equals(operator)) {
                if (CommonUtil.isEmpty(compareSourceValue)) {
                    return false;
                }
            } else if ("==".equals(operator) && !compareSourceValue.equals(compareTargetValue)) {
                return false;
            } else if ("!=".equals(operator) && compareSourceValue.equals(compareTargetValue)) {
                return false;
            } else if ("＞".equals(operator)) {
                if (CommonUtil.isNotEmpty(compareSourceValue) && CommonUtil.isNotEmpty(compareTargetValue)) {
                    try {
                        final BigDecimal compare1 = RequestConfigUtil.toNumber(compareSourceValue);
                        final BigDecimal compare2 = RequestConfigUtil.toNumber(compareTargetValue);
                        if (compare1.compareTo(compare2) <= 0) {
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        LOG.fatal(e.toString() 
                                + EngineExceptionEnum.FM_CONDITION_PRO_AND_VALUE_NUMBER_IN_LARGER.getErrinfo());
                        throw new RichClientWebException(
                                EngineExceptionEnum.FM_CONDITION_PRO_AND_VALUE_NUMBER_IN_LARGER);
                    }
                } else {
                    judge = false;
                }
            } else if ("＞=".equals(operator)) {
                if (CommonUtil.isNotEmpty(compareSourceValue) && CommonUtil.isNotEmpty(compareTargetValue)) {
                    try {
                        final BigDecimal compare1 = RequestConfigUtil.toNumber(compareSourceValue);
                        final BigDecimal compare2 = RequestConfigUtil.toNumber(compareTargetValue);
                        if (compare1.compareTo(compare2) < 0) {
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        LOG.fatal(e.toString() 
                                + EngineExceptionEnum.FM_CONDITION_PRO_AND_VALUE_NUMBER_IN_LARGER_OR_EQUAL
                                .getErrinfo());
                        throw new RichClientWebException(
                                EngineExceptionEnum.FM_CONDITION_PRO_AND_VALUE_NUMBER_IN_LARGER_OR_EQUAL);
                    }
                } else if (CommonUtil.isEmpty(compareSourceValue) && CommonUtil.isEmpty(compareTargetValue)) {
                    judge = true;
                } else {
                    judge = false;
                }
            } else if ("＜".equals(operator)) {
                if (CommonUtil.isNotEmpty(compareSourceValue) && CommonUtil.isNotEmpty(compareTargetValue)) {
                    try {
                        final BigDecimal compare1 = RequestConfigUtil.toNumber(compareSourceValue);
                        final BigDecimal compare2 = RequestConfigUtil.toNumber(compareTargetValue);
                        if (compare1.compareTo(compare2) >= 0) {
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        LOG.fatal(e.toString() 
                                + EngineExceptionEnum.FM_CONDITION_PRO_AND_VALUE_NUMBER_IN_LESS.getErrinfo());
                        throw new RichClientWebException(
                                EngineExceptionEnum.FM_CONDITION_PRO_AND_VALUE_NUMBER_IN_LESS);
                    }
                } else {
                    judge = false;
                }
            } else if ("＜=".equals(operator)) {
                if (CommonUtil.isNotEmpty(compareSourceValue) && CommonUtil.isNotEmpty(compareTargetValue)) {
                    try {
                        final BigDecimal compare1 = RequestConfigUtil.toNumber(compareSourceValue);
                        final BigDecimal compare2 = RequestConfigUtil.toNumber(compareTargetValue);
                        if (compare1.compareTo(compare2) > 0) {
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        LOG.fatal(e.toString() 
                                + EngineExceptionEnum.FM_CONDITION_PRO_AND_VALUE_NUMBER_IN_LESS_OR_EQUAL.getErrinfo());
                        throw new RichClientWebException(
                                EngineExceptionEnum.FM_CONDITION_PRO_AND_VALUE_NUMBER_IN_LESS_OR_EQUAL);
                    }
                } else if (CommonUtil.isEmpty(compareSourceValue) && CommonUtil.isEmpty(compareTargetValue)) {
                    judge = true;
                } else {
                    judge = false;
                }
            }
        }
        return judge;
    }
    
    /**
     * 控件设置样式的方法.
     * 
     * @param tab 控件属性
     * @param param 用户请求对象
     * @param tabRow 数据
     * @param seData 用户会话对象
     * @throws RichClientWebException RichClientWebException
     */
    private void setStyle(
            ResponseTab tab, 
            Param param, 
            TableRowMap tabRow, 
            SessionData seData) 
        throws RichClientWebException {
        setStyle(tab, param, tabRow, seData, null);
    }
    
    /**
     * 控件设置样式的方法.
     * 
     * @param tab 控件属性
     * @param param 用户请求对象
     * @param tabRow 数据
     * @param seData 用户会话对象
     * @param targetId 画面控件ID
     * @throws RichClientWebException RichClientWebException
     */
    private void setStyle(
            ResponseTab tab, 
            Param param, 
            TableRowMap tabRow, 
            SessionData seData, String targetId) 
        throws RichClientWebException {
        if (tab == null) {
            return;
        }
        if (ControlRequestMap.getInstance().hasStyle(param, tab.getTargetId())) {
            final Style style = ControlRequestMap.getInstance().getStyle(param, tab.getTargetId());
            if (judgeOperator(style, tabRow, param, seData, targetId)) {
                final List<Display> displays = style.getDisplays();
                for (int i = 0; i < displays.size(); i++) {
                    final Display display = displays.get(i);
                    if (CommonUtil.isNotEmpty(display.getValue()) 
                            && (judgeOperator(display, tabRow, param, seData, targetId))) {
                        if ("none".equals(display.getValue())) {
                            tab.setDispStat(ConstantsUtil.Status.HIDE);
                        } else if ("read".equals(display.getValue())) {
                            tab.setDispStat(ConstantsUtil.Status.READ);
                        } else if ("normal".equals(display.getValue())) {
                            tab.setDispStat(ConstantsUtil.Status.WRITE);
                        } else if ("notvisibled".equals(display.getValue())) {
                            tab.setDispStat(ConstantsUtil.Status.NOT_VISIBLED);
                        }
                    }
                }
                
                final List<BackgroundColor> backgroundColors = style.getBackgroundColors();
                for (int i = 0; i < backgroundColors.size(); i++) {
                    final BackgroundColor backgroundColor = backgroundColors.get(i);
                    if (CommonUtil.isNotEmpty(backgroundColor.getValue()) 
                            && (judgeOperator(backgroundColor, tabRow, param, seData, targetId))) {
                        tab.setBgColor(backgroundColor.getValue());
                    }
                }
                
                final List<TextColor> textColors = style.getTextColors();
                for (int i = 0; i < textColors.size(); i++) {
                    final TextColor textColor = textColors.get(i);
                    if (CommonUtil.isNotEmpty(textColor.getValue()) 
                            && (judgeOperator(textColor, tabRow, param, seData, targetId))) {
                        tab.setCharColor(textColor.getValue());
                    }
                }
            }
        }
    }
    
    /**
     * 多项目中行控件设置样式的方法(对不是从数据库中取得，在XML中设置的控件对象设置样式).
     * 
     * @param tabRow 数据
     * @param line 控件容器
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @throws RichClientWebException RichClientWebException
     */
    private void setStyle(
            TableRowMap tabRow, 
            ResponseLine line, 
            Param param, 
            SessionData seData) 
        throws RichClientWebException {
        final List<Style> styles = ControlRequestMap.getInstance().getStyles(param);
        for (int i = 0; i < styles.size(); i++) {
            final String key = styles.get(i).getProperty();
            if (!tabRow.containsKey(key) && !tabRow.containsKey(key.toUpperCase()) 
                    && !"*".equals(key)) {
                final Map<String, String> types = ControlDispField.getInstance().getField(param.dispCode);
                int type = ConstantsUtil.Widget.TEXT;
                if (types != null && types.containsKey(key)) {
                    type = Integer.parseInt(types.get(key));
                }
                final ResponseTab tabItem = RequestConfigUtil.createItem(
                        param, type, key, ConstantsUtil.Str.EMPTY, true);
                tabItem.setEffect(ConstantsUtil.Status.EFFECT_NO);
                setStyle(tabItem, param, tabRow, seData);
                line.put(key, tabItem);
            }
        }
    }
    
    /**
     * 控件设置样式的方法(对不是从数据库中取得，在XML中设置的控件对象设置样式).
     * 
     * @param tabRow 数据
     * @param tabList 控件容器
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @throws RichClientWebException RichClientWebException
     */
    private void setStyle(
            TableRowMap tabRow, 
            List<ResponseTab> tabList, 
            Param param, 
            SessionData seData) 
        throws RichClientWebException {
        final List<Style> styles = ControlRequestMap.getInstance().getStyles(param);
        final Map<String, String> types = ControlDispField.getInstance().getField(param.dispCode);
        for (int i = 0; i < styles.size(); i++) {
            if (!"*".equals(styles.get(i).getProperty())) {
                if (!tabRow.containsKey(styles.get(i).getProperty())) {
                    int type = ConstantsUtil.Widget.TEXT;
                    if (types != null && types.containsKey(styles.get(i).getProperty())) {
                        type = Integer.parseInt(types.get(styles.get(i).getProperty()));
                    }
                    final ResponseTab tabItem = RequestConfigUtil.createItem(param, type, 
                            styles.get(i).getProperty(), ConstantsUtil.Str.EMPTY, false);
                    tabItem.setEffect(ConstantsUtil.Status.EFFECT_NO);
                    setStyle(tabItem, param, tabRow, seData);
                    tabList.add(tabItem);
                }
            } else {
                for (String key : tabRow.conditionKeySet()) {
                    int type = ConstantsUtil.Widget.TEXT;
                    if (types != null && types.containsKey(key)) {
                        type = Integer.parseInt(types.get(key));
                    }
                    final ResponseTab tabItem = RequestConfigUtil.createItem(param, type, 
                            key, ConstantsUtil.Str.EMPTY, false);
                    tabItem.setEffect(ConstantsUtil.Status.EFFECT_NO);
                    setStyle(tabItem, param, tabRow, seData, key);
                    tabList.add(tabItem);
                }
            }
        }
    }
    
    /**
     * @Description: 
     * @author king
     * @since Sep 29, 2013 3:03:54 PM 
     * @version V1.0
     * 
     * @param param param
     * @param session session
     * @throws RichClientWebException RichClientWebException
     */
    public void handlerFinished(Param param, SessionData session, ResponseBean responseBean) 
        throws RichClientWebException {
        if (mExpand != null) {
            mExpand.postDoService(param, session, responseBean);
        }
    }

    /**
     * 缓存服务器对象设置.
     * 
     * @param memcached 缓存服务器对象
     */
    public void setMemcached(ContorlMemcached memcached) {
        mMemcached = memcached;
    }

    /**
     * 缓存服务器对象取得.
     * 
     * @return 缓存服务器对象
     */
    public ContorlMemcached getMemcached() {
        return mMemcached;
    }

    /**
     * 格式化对象设置.
     * 
     * @param format 格式化对象
     */
    public void setFormat(IFormatFace format) {
        mFormat = format;
    }
    
    /**
     * 特制处理对象设置.
     * 
     * @param execute 特制处理对象
     */
    public void setExecute(IExternalFace execute) {
        mExecute = execute;
    }

    /**
     * 数据库操作对象设置.
     * 
     * @param db 数据库操作对象
     */
    public void setDb(IDbService db) {
        this.db = db;
    }

    /**
     * 数据库操作对象取得.
     * 
     * @return 数据库操作对象
     */
    public IDbService getDb() {
        return db;
    }

    /**
     * 画面控件设置对象设置.
     * 
     * @param htmlTab 画面控件设置对象
     */
    public void setHtmlTab(HtmlTabHandler htmlTab) {
        mHtmlTab = htmlTab;
    }

    /** 
    * @Description: 取得数据源切换对象
    * @author king
    * @since Oct 8, 2012 11:02:36 AM 
    * 
    * @return 数据源切换对象
    */
    public IDataSourceFace getDataSource() {
        return mDataSource;
    }

    /** 
    * @Description: 设置数据源切换对象
    * @author king
    * @since Oct 8, 2012 11:02:49 AM 
    * 
    * @param dataSource 数据源切换对象
    */
    public void setDataSource(IDataSourceFace dataSource) {
        this.mDataSource = dataSource;
    }

    /** 
    * @Description: 取得切面对象
    * @author king
    * @since Oct 8, 2012 11:02:58 AM 
    * 
    * @return 切面对象
    */
    public IExpandFace getExpand() {
        return mExpand;
    }

    /** 
    * @Description: 设置切面对象
    * @author king
    * @since Oct 8, 2012 11:03:11 AM 
    * 
    * @param expand 切面对象
    */
    public void setExpand(IExpandFace expand) {
        this.mExpand = expand;
    }
    
    /** 
    * @Description: 自定义服务的默认方法
    * @author king
    * @since Nov 3, 2012 7:05:10 PM 
    * 
    * @return 是否执行框架默认跳转方法[true：不执行；false：执行]
    */
    public boolean execute() {
        return true;
    }
    
    /** 
    * @Description: 自定义服务的执行类初始化参数方法
    * @author king
    * @since Nov 3, 2012 7:14:39 PM 
    * 
    * @param context 容器对象
    * @param request 客户端请求对象
    * @param response 服务器响应对象
    * @param session session对象
    */
    public void handlerInit(ServletContext context, HttpServletRequest request, 
            HttpServletResponse response, SessionData session) {
        this.context = context;
        this.request = request;
        this.response = response;
        this.session = session;
    }

    /** 
    * @Description: 取得容器对象
    * @author king
    * @since Nov 3, 2012 7:15:31 PM 
    * 
    * @return 容器对象
    */
    public ServletContext getApplication() {
        return context;
    }

    /** 
    * @Description: 取得客户端请求对象
    * @author king
    * @since Nov 3, 2012 7:15:43 PM 
    * 
    * @return 客户端请求对象
    */
    public HttpServletRequest getRequest() {
        return request;
    }

    /** 
    * @Description: 取得服务器响应对象
    * @author king
    * @since Nov 3, 2012 7:15:52 PM 
    * 
    * @return 服务器响应对象
    */
    public HttpServletResponse getResponse() {
        return response;
    }

    /** 
    * @Description: 取得session对象
    * @author king
    * @since Nov 3, 2012 7:16:02 PM 
    * 
    * @return session对象
    */
    public SessionData getSession() {
        return session;
    }

    /**
     * @Description: 
     * @author king
     * @since Oct 9, 2013 5:06:47 PM 
     * @version V1.0
     * @param mailSender mailSender
     */
    public void setMailSender(MailSender mailSender) {
        mMailSender = mailSender;
    }
    
}
