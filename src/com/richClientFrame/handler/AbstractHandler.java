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
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� AbstractHandler
 * ������ �� ���湲ͨ������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2010.03.24
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
     * ���캯��.
     */
    public AbstractHandler() {
        super();
    }
    
    /**
     * �����ʼ������.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    public AbstractResponseData showPage(Param param, SessionData seData)
        throws RichClientWebException {
        return showPagePri(param, seData);
    }
    
    /**
     * ���浥��Ŀ��ʼ������.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ������Ϣ
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public AbstractResponseData showItem(Param param, SessionData seData)
        throws RichClientWebException, InputException {
        return showItemPri(param, seData);
    }
    
    /**
     * ����Ŀ����.
     * @param param �û��������
     * @param seData �û��Ự����
     * @return response��Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    public AbstractResponseData showList(Param param, SessionData seData) 
        throws RichClientWebException {
        return showListPriv(param, seData);
    }

    /**
     * ���ݿ���ɾ�ķ���.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ������Ϣ
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    public AbstractResponseData update(Param param, SessionData seData)
        throws RichClientWebException, InputException {
        return updatePri(param, seData);
    }
    
    /**
     * ���Ϸ���.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ������Ϣ
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
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ������Ϣ
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
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    private ResponseData sendMailPri(Param param, SessionData seData) 
        throws RichClientWebException {
        // �������������������
        final TableRowMap tabContent = new TableRowMap();
        final List<ResponseTab> tabList = new ArrayList<ResponseTab>();
        final List<Mail> mails = ControlRequestMap.getInstance().getMails(param).getMails();
        // ��request�е����ݱ�����ִ��sql�����map��
        final Map<String, Object> whereMap = createCondition(param);
        for (int i = 0; i < mails.size(); i++) {
            final Mail mail = mails.get(i);
            final MailBean mailBean = new MailBean();
            mailBean.parseMail(param, seData, mail);
            // ��request���ɲ�ѯ����
            final List<com.richClientFrame.data.param.RequestParam.Param> params = mail.getParams();
            // ������<params>��ǩ�е����ݣ�ʹ֮���õ����ݲ�ѯ������
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
     * �����ʼ������.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    private AbstractResponseData showPagePri(Param param, SessionData seData) throws RichClientWebException {
        LOG.debug("showPage", "start");
        final AbstractResponseData resData = createShowPageContent(param, seData);
        LOG.debug("showPage", "end");
        return resData;
    }

    /**
     * @Description: ����showPage������
     * @author king
     * @since Dec 3, 2012 9:31:36 AM 
     * @version V1.0
     * @param param �û��������
     * @param seData �û��Ự����
     * @return showPage������
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
     * ���Ϸ���.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ������Ϣ
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    private ResponseData showComplexPri(Param param, SessionData seData)
        throws RichClientWebException, InputException {
        LOG.debug("showComplex", "start");
        initializeHandler(param, seData);
        final TableRowMap tab = new TableRowMap();
        final List<ResponseTab> tabList = new ArrayList<ResponseTab>();
        
        // sql�еĶ�̬�����������
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
                // �����ⲿ����ȡֵ
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
     * ���浥��Ŀ��ʼ������.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ������Ϣ
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
        // ��������Ŀ����.
        final Item item = createItemContent(param, seData, items, tab, tabList);
        
        readExcel(param, seData, item, tab);
        final ResponseExcel resExcel = writeExcel(param, seData, item, tab);
        final ResponseTab[] tabs = tabList.toArray(new ResponseTab[0]);
        // ��������ͷ
        final ResponseBean responseBean = new ResponseBean();
        responseBean.setItemBean(new ResponseItemBean(tab));
        final ResponseData resData = makeHeader(param, EngineExceptionEnum.UR_COM_BLANK);
        resData.setResTab(tabs);
        // ����ͨ��������ʽ����ҳ�����Ϣ
        setOutPut(tab, resData);
        if (resExcel != null) {
            resData.setResExcel(resExcel);
        }
        resData.setResponseBean(responseBean);
        LOG.debug("showItem", "end");
        return resData;
    }
    
    /**
     * ��������Ŀ����.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @param items ����Ŀ��������
     * @param tabContent ����Ŀ���ݱ�������
     * @param tabList ����Ŀ��Ϣ�б�����
     * @return ������Ϣ
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
        // ��request�е����ݱ�����ִ��sql�����map��
        final Map<String, Object> whereMap = createCondition(param);
        if (itemList.size() > 0) {
            for (int i = 0; i < itemList.size(); i++) {
                item = itemList.get(i);
                // �Դ˴����������֤����
                doCheck(param, seData, item, true, whereMap);
                // �жϴ�<item>�ڵ��Ƿ���Ҫִ�У�����<condtion>��ǩ���ж�
                if (!judgeOperator(item, tabContent, param, seData)) {
                    continue;
                }
                final String sql = item.getSql();
                final String source = item.getSource();
                // ��ִ��sql֮ǰ���д���
                final boolean isExecute = doActionPre(item, param, seData, whereMap, tabContent);
                // �������ݵĵ���Ŀ
                if (item.isList()) {
                    final List<TableRowMap> list = new ArrayList<TableRowMap>();
                    String targetId = param.targetId;
                    if (CommonUtil.isNotEmpty(item.getTargetId())) {
                        targetId = item.getTargetId();
                    }
                    // �ж��Ƿ��ǹ̶�ֵ��true���̶�ֵ��
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
                        LOG.info("createItemContent", "����Ŀ������¼����£����Զ���action", "itemTargetId = " + itemTargetId);
                        if (CommonUtil.isNotEmpty(itemTargetId)) {
                            targetId = itemTargetId;
                        }
                    }
                    executeExternal(item, list, param, seData);
                    showItemByItems(list, param, targetId, seData, tabList, item, tabContent);
                // �������ݵĵ���Ŀ
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
                    // �Ѳ�����Ϣ�����ڷ��ػ����������
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
     * @Description: ������Ϊsingle����ʱ��������ؽ���в������ؼ���Ϣ���򷵻�Դ���ݣ������֮ǰ�Ĵ�����ʽ
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
    * @Description: �Ѳ�����Ϣ�����ڷ��ػ����������
    * @author king
    * @since Oct 7, 2012 9:03:45 AM 
    * 
    * @param param �ͻ����������
    * @param seData session����
    * @param action �¼�����
    * @param tab ��������
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
     * ��ͨactionִ��sqlǰ����.
     * 
     * @param action action����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param tab ����
     * @return �Ƿ�ִ��sql
     * @throws RichClientWebException RichClientWebException
     */
    private boolean doActionPre(
            IActionStep action,
            Param param, 
            SessionData seData, 
            TableRowMap tab) 
        throws RichClientWebException {
        
        // ��request���ɲ�ѯ����
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
     * ��ͨactionִ��sqlǰ����.
     * 
     * @param action action����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param whereMap sql��̬����
     * @return �Ƿ�ִ��sql
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
     * ��ͨactionִ��sqlǰ����.
     * 
     * @param action action����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param whereMap sql��̬����
     * @param tab ֮ǰ���������ݲ�ѯ�Ľ����Ϣ
     * @return �Ƿ�ִ��sql
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
     * ��ͨactionִ��sqlǰ����.
     * 
     * @param action action����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param upload �ϴ�����
     * @param whereMap sql��̬����
     * @param tab ֮ǰ���������ݲ�ѯ�Ľ����Ϣ
     * @return �Ƿ�ִ��sql
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
        // ��request���ɲ�ѯ����
        final List<com.richClientFrame.data.param.RequestParam.Param> params = action.getParams();
        // ������<params>��ǩ�е����ݣ�ʹ֮���õ����ݲ�ѯ������
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
     * �����ⲿ����ȡֵ
     * @param externalFace �ⲿ��������
     * @param tab ����
     * @param param �û��������
     * @param seData �û��Ự����
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
     * ���ø�ʽ������ȡֵ
     * @param formatFace �ⲿ��������
     * @param value ����
     * @return ת�����ֵ
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
     * �����ⲿ����ȡֵ
     * @param externalFace �ⲿ��������
     * @param tabs ����
     * @param param �û��������
     * @param seData �û��Ự����
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
     * �Դ˴����������֤����.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @param check ��֤����
     * @param isThrow �Ƿ��׳��쳣��true���׳���false�����׳���
     * @param whereMap sql������������
     * @return ��֤�����true����֤����������false����֤����ʧ�ܡ�
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    private boolean doCheck(
            Param param, SessionData seData, IChecker check, boolean isThrow, Map<String, Object> whereMap) 
        throws RichClientWebException, InputException {
        LOG.debug("doCheck", "start");
        
        // ��֤�����ʶ��true����֤ʧ�ܣ�����false����֤�ɹ����������к���������
        boolean result = false;
        String resCode = EngineExceptionEnum.UR_COM_INPUT_ERROR.getCode();
        
        // ȡ��<checks>��ǩ�µ�<check>��ǩ��������
        final List<Checker> checks = check.getChecks();
        
        // ��ͨ��֤��
        final CommonChecker commonCheck = new CommonChecker();
        ErrTabBean errTabBean = new ErrTabBean();
        // �Ƿ���Ҫ��ͨ��֤
        if (ControlRequestMap.getInstance().isChecked(param)) {
            // ִ�й�ͨ��֤����
            errTabBean = commonChecker(param, seData);
        }
        
        if (checks.size() > 0) {
            // ����<checks>��ǩ�µ�<check>��ǩ��������
            for (int i = 0; i < checks.size(); i++) {
                boolean isError = false;
                
                // ȡ��<check>��ǩ����
                final Checker checker = checks.get(i);
                
                // ������֤�����ݶ���
                final TableRowMap tabContainer = new TableRowMap();
                
                // ����֤�����жϣ����whether�е�conditionΪ��false������֤���ٽ��У���Ϊû�д�����
                final Map<String, List<String>> checkResult = judgeOperator(
                        checker.getWhethers(), tabContainer, param, seData, null);
                
                if (checkResult != null) {
                    continue;
                }
                
                // ȡ��������������
                final List<Condition> conditions = checker.getConditions();
                final boolean isExecute = doActionPre(checker, param, seData, whereMap);
                
                TableRowMap tab = new TableRowMap();
                // ȡ��<check>��ǩ�ġ�sql������ֵ
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
                
                // �����ⲿ����ȡֵ
                executeExternal(checker, tabContainer, param, seData);
                // ��֤����ؼ���������
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
                            // ��check��ǩ��message��Ϊ��̬
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
     * @Description: ���������ʾ��code
     * @author king
     * @since Dec 25, 2012 1:44:02 PM 
     * @version V1.0
     * @param param �ͻ����������
     * @param seData session����
     * @param tab ���ݽ����
     * @param taber tab��ǩ����
     * @return �����ʾ��code
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
     * ����Ŀ����.
     * @param param �û��������
     * @param seData �û��Ự����
     * @return response��Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    private AbstractResponseData showListPriv(Param param, SessionData seData)
        throws RichClientWebException {
        LOG.debug("showListPriv", "start");
        // ��ʼ��handler����
        initializeHandler(param, seData);
        // sql�еĶ�̬�����������
        final Map<String, Object> whereMap = createCondition(param);
        
        // ����ҳ����ʾԪ�ض��󼯺�
        List<TableRowMap> list = new ArrayList<TableRowMap>();
        
        // <list>��ǩ�еġ�targetId������ֵ
        final String targetId = CommonUtil.toString(ControlRequestMap.getInstance().getTargetId(param));
        
        PageBean pageBean = null;
        
        if (ControlRequestMap.getInstance().hasDimension(param)) {
            
            // ���ɶ�ά��������
            final TabRowMapDimensions dimensionList = new TabRowMapDimensions();
            
            final Dimension dimension = ControlRequestMap.getInstance().getDimension(param);
            
            final String index = dimension.getIndex();
            
            // ���ɶ�ά�ĵ�һά����Ŀ��������
            list = getListDatas(createList(param, seData, whereMap, dimension));
            
            // ȡ�ö�ά��ǩ�е�һά����Ŀ��ǩ����
            final RequestParam.List paramList = dimension.getList();
            
            // ��ά��ǩ�е�һά����Ŀ��ǩ�������
            if (paramList != null) {
                
                // ������ά�ĵ�һά����Ŀ��������
                for (int i = 0; i < list.size(); i++) {
                    
                    final TableRowMap tab = list.get(i);
                    
                    // ���ɶ�ά���������еڶ�ά������
                    final TabRowMapList tabList = new TabRowMapList();
                    
                    // ������<results>��ǩ�е����ݣ�ʹ֮���õ����ݲ�ѯ�����
                    tab.putAll(reloadResult(tab, dimension, param, seData));
                    
                    tabList.addLine(tab);
                    createCondition(whereMap, tab);
                    final List<TableRowMap> tabLists = getListDatas(createList(param, seData, whereMap, paramList));
                    executeExternal(paramList, tabLists, param, seData);
                    // ִ���Զ��Ƶ�action����
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
        
        // �Ƿ���һά����Ŀ
        if (ControlRequestMap.getInstance().hasList(param)) {
            
            // ȡ����<results>��ǩ�е�����
            
            pageBean = createList(param, seData, whereMap, listItem);
            
            // ����һά����Ŀ��������
            list = getListDatas(pageBean);
            
        }
        
        //�����ⲿ����ȡֵ
        executeExternal(listItem, list, param, seData);
        
        final String pageSize = ControlRequestMap.getInstance().getPageSize(listItem);
        
        if (CommonUtil.isNotEmpty(pageSize) && pageBean == null) {
            pageBean = new PageBean();
        }
        
        // ��ȡexcel�ķ���
        readExcel(param, seData, listItem, list);
        
        final String index = listItem.getIndex();
        
        if (mAction != null) {
            mAction.onListPostExecute(list, index);
        }
        
        list = getListForPaging(param, list, pageBean, pageSize);
        
        // ����excel�ķ���
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
        // �����б���Ŀ���÷���[һά�б�] 
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
    * @Description: ���ɷ�ҳ������Ϣ
    * @author king
    * @since Sep 14, 2012 11:27:15 PM 
    * 
    * @param param �������
    * @param list ������Ϣ
    * @param pageBean ��ҳ��Ϣ����
    * @param pageSize pageSize
    * @return ��ҳ������Ϣ
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
    * @Description: ȡ��������Ϣ�е�������Ϣ
    * @author king
    * @since Sep 14, 2012 10:53:57 PM 
    * 
    * @param pageBean ��Ϣ�洢����
    * @return ������Ϣ�е�������Ϣ
    */
    private List<TableRowMap> getListDatas(PageBean pageBean) {
        if (pageBean == null) {
            return new ArrayList<TableRowMap>();
        }
        return pageBean.getDatas();
    }

    /**
     * ����һά����Ŀ��������
     * @param param �û��������
     * @param seData �û��Ự����
     * @param whereMap sql����
     * @param action ��ͨ����
     * @return ����
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
                // �����ɵ����ݼ��ص�����ҳ����ʾԪ�ض��󼯺���
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
     * ���ݿ���ɾ�ķ���.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ������Ϣ
     * @throws RichClientWebException RichClientWebException
     * @throws InputException InputException
     */
    private ResponseData updatePri(Param param, SessionData seData) throws RichClientWebException,
            InputException {
        LOG.debug("update", "start");
        
        // ��ʼ��handler����
        initializeHandler(param, seData);
        
        // �������������������
        final List<ResponseTab> tabList = new ArrayList<ResponseTab>();
        
        // �������������������
        TableRowMap tabContent = new TableRowMap();
        // ��request���ɲ�ѯ����
        final Map<String, Object> whereMap = createCondition(param);
        final Updates updates = ControlRequestMap.getInstance().getUpdates(param);
        final List<com.richClientFrame.data.param.RequestParam.Param> params = updates.getParams();
        reloadParams(whereMap, params, param, seData, tabContent);
        final List<Update> updateList = updates.getUpdates();
        boolean updateExecuteResult = true;
        for (int i = 0; i < updateList.size(); i++) {
            final Update update = updateList.get(i);
            // �Դ˴����������֤����
            doCheck(param, seData, update, true, whereMap);
            // �жϴ�<update>�ڵ��Ƿ���Ҫִ�У�����<condtion>��ǩ���ж�
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
            // �Ѳ�����Ϣ�����ڷ��ػ����������
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
     * @Description: ���ø��²����Ľ��code
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
     * �ļ��ϴ�����.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ������Ϣ
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
     * �ļ��ϴ�����.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ������Ϣ
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
        LOG.info("upload", "�ϴ��ļ�������Ϊ��" + fileItemList.size());
        final Map<String, Object> whereMap = createCondition(param);
        final Upload uploadItem = ControlRequestMap.getInstance().getUpload(param);
        final String dataType = uploadItem.getDataType();
        // �Դ˴����������֤����
        doCheck(param, seData, uploadItem, true, whereMap);
        for (int i = 0; i < fileItemList.size(); i++) {
            final FileItem item = fileItemList.get(i);
            final String fileName = item.getName();
            final String fileRealName = fileName.substring(fileName.lastIndexOf("\\") + 1);
            final UploadParam upload = new UploadParam();
            LOG.info("upload", "�ϴ��ļ�������Ϊ��" + fileName);
            LOG.info("upload", "�ϴ��ļ�����ʵ����Ϊ��" + fileRealName);
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
                // ������������ַ���ļ�·��
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
            // �ļ��г��ڻ�
            if (dir.isDirectory() && dir.exists()) {
                FileUtils.cleanDirectory(dir);
            }
            // �����ļ���
            FileUtils.forceMkdir(dir);
        } else {
            if (!dir.exists()) {
                // �����ļ���
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
    * @Description: ����ͨ��������ʽ����ҳ�����Ϣ
    * @author king
    * @since Oct 7, 2012 9:08:42 AM 
    * 
    * @param tab ��Ϣ����
    * @param resData ���ػ�������
    */
    private void setOutPut(TableRowMap tab, final ResponseData resData) {
        if (tab != null && CommonUtil.isNotEmpty(tab.getOutPut())) {
            resData.setOutPut(tab.getOutPut());
        }
    }
    
    /**
     * �����������Ŀ���÷���.
     * 
     * @param tabList ����
     * @param param �û��������
     * @param targetId ����ؼ�ID
     * @param seData �û��Ự����
     * @param resList ������ʾ����
     * @param item <item>��ǩ����
     * @param tabContent ֮ǰ���ݿ��ѯ�����Ϣ
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
     * @Description: ���������ļ�ȡ�ÿؼ�����
     * @author king
     * @since Jun 8, 2013 10:57:24 AM 
     * @version V1.0
     * @param targetId �ؼ�ID
     * @param types �����ļ���������
     * @return �ؼ�����
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
     * ���浥��Ŀ���÷���.
     * 
     * @param tab ����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param resList ������ʾ����
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
     * ��������ͷ
     * @param param �û��������
     * @param resCode ִ�н��code
     * @return �������ݶ���
     * @throws RichClientWebException RichClientWebException
     */
    private ResponseData makeHeader(Param param, EngineExceptionEnum resCode) throws RichClientWebException {
        return makeHeader(param, resCode.getCode());
    }
    
    /**
     * ��������ͷ
     * @param param �û��������
     * @param resCode ִ�н��code
     * @return �������ݶ���
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
     * �����б���Ŀ���÷���.
     * 
     * @param tabs ����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param listItem <list>��ǩ��Ϣ
     * @return ������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    public ResponseList showList(
            List<TableRowMap> tabs, Param param, SessionData seData, 
            com.richClientFrame.data.param.RequestParam.List listItem)
        throws RichClientWebException {
        final ResponseList list = new ResponseList();
        // �Ƿ�����������Ϣ��ʶ
        boolean hasCmbFlag = false;
        
        if (tabs != null) {
            // �б�����������������
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
            // �����б�����Ϣ
            createListLines(tabs, param, seData, listItem, list, cmbMaps, sedCmbs);
        }
        list.setHasCmbs(hasCmbFlag);
        
        return list;
    }

    /** 
    * @Description: �����б�����Ϣ
    * @author king
    * @since Oct 8, 2012 9:50:08 AM 
    * 
    * @param tabs �б���Ϣ
    * @param param �ͻ����������
    * @param seData session
    * @param listItem �б������Ϣ
    * @param list ���������
    * @param cmbMaps ����MAP
    * @param sedCmbs ����������
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
                // ��ά���ݽ���
                final List<TableRowMap> tabList = tab.getDatasList();
                if (tabList != null && tabList.size() > 0) {
                    final ResponseList responseList = new ResponseList();
                    createListLines(tabList, param, seData, listItem, responseList, cmbMaps, sedCmbs);
                    line.setList(responseList);
                }
            }
            tab.putAll(reloadResult(tab, listItem, param, seData));
            Map<String, TableRowMap[]> sedCmbMapItems = new HashMap<String, TableRowMap[]>();
            // �б��е���������Ϣ����
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
            // ����Ŀ����������Ϣ���趨
            setTabLine(tab, param, types, line, seData, sedCmbMapItems);
            list.addLine(line);
        }
    }

    /**
     * ����Ŀ����������Ϣ���趨
     * @param tabRow ����
     * @param param �û��������
     * @param types �ؼ�����
     * @param line �����ж���
     * @param cmbMaps ����������
     * @param seData �û��Ự����
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
                // ������Ŀ��������
                tab = RequestConfigUtil.createItem(
                        param, type, key, CommonUtil.toString(tabRow.get(key)), null, cmbMaps, true, null);
            }
            setStyle(tab, param, tabRow, seData);
            line.put(key, tab);
        }
        setStyle(tabRow, line, param, seData);
    }
    
    /**
     * �����ά�б���Ŀ���÷���.
     * 
     * @param dimensionList ��ά�б�����
     * @param targetId �б�ؼ�ID
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ������Ϣ
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
     * ��ҳ����.
     * @param param �û��������
     * @param seData �û��Ự����
     * @return response��Ϣ
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
     * ��ͨ��֤����.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ��֤��Ϣ
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
     * ���ý������ݶ���.
     * 
     * @param action �������ݶ���
     */
    public void setAction(IActionFace action) {
        mAction = action;
    }
    
    /**
     * ��request���ɲ�ѯ����.
     * ��request�е����ݱ�����ִ��sql�����map��
     * 
     * @param param �û��������
     * @return ��ѯ����
     */
    private Map<String, Object> createCondition(Param param) {
        // sql�еĶ�̬�����������
        final Map<String, Object> whereMap = new HashMap<String, Object>();
        final Map<String, String[]> paramMaps = param.getReqTable();
        for (String key : paramMaps.keySet()) {
            whereMap.put(key, CommonUtil.trim(paramMaps.get(key)[0]));
        }
        return whereMap;
    }
    
    /**
     * ��request���ɲ�ѯ����.
     * 
     * @param tab ���ݿ��ѯ�������
     * @param param �û��������
     */
    private void createCondition(TableRowMap tab, Param param) {
        // sql�еĶ�̬�����������
        final Map<String, String[]> paramMaps = param.getReqTable();
        for (String key : paramMaps.keySet()) {
            tab.getDataMap().put(key, CommonUtil.trim(paramMaps.get(key)[0]));
        }
    }
    
    /**
     * ��sql������ɲ�ѯ����.
     * 
     * @param whereMap ��������
     * @param tab ����
     */
    private void createCondition(Map<String, Object> whereMap, TableRowMap tab) {
        for (String key : tab.keySet()) {
            whereMap.put(key, CommonUtil.toString(tab.get(key)));
        }
    }
    
    /**
     * ��<params>�е��������ɲ�ѯ����.
     * 
     * @param whereMap ��������
     * @param parameter <params>�е�����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param tab ���ݿ��ѯ���ݶ���
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
     * ͨ��<cmbs>�е�����������������Ϣ.
     * 
     * @param cmb <cmbs>�е�����
     * @param tablist ������Ϣ������
     * @param param �û��������
     * @throws RichClientWebException RichClientWebException
     */
    private void createCmb(Cmb cmb, List<TableRowMap> tablist, Param param) 
        throws RichClientWebException {
        createCmb(cmb, tablist, param, null);
    }
    
    /**
     * ͨ��<cmbs>�е�����������������Ϣ.
     * 
     * @param cmb <cmbs>�е�����
     * @param tablist ������Ϣ������
     * @param param �û��������
     * @param tabData ���ݲ�ѯ��Ϣ
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
                // TODO ��Ӧ������������������˵���ѯ������KEY,����ж������KEY�ÿո�ָ���֮��Ӧ��<param>��ǩ���������
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
     * @Description: ͨ��<cmb>��ǩ�ľ�̬���������������Ϣ
     * @author king
     * @since Dec 13, 2012 10:36:02 AM 
     * @version V1.0
     * @param cmb �������ǩ����
     * @param tablist �������������ݶ���
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
     * ������<results>��ǩ�е����ݣ�ʹ֮���õ����ݲ�ѯ�����.
     * 
     * @param tabs �����б�
     * @param action <results>��ǩ�е�����
     * @param param �û��������
     * @param seData �û��Ự����
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
     * ������<results>��ǩ�е����ݣ�ʹ֮���õ����ݲ�ѯ�����.
     * 
     * @param tab ����
     * @param action <results>��ǩ�е�����
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ������ʾ�����ݼ���
     * @throws RichClientWebException RichClientWebException
     */
    private TableRowMap reloadResult(TableRowMap tab, IActionStep action, Param param, SessionData seData) 
        throws RichClientWebException {
        
        if (action == null) {
            return tab;
        }
        
        final List<Result> results = action.getResults();
        
        // �����жϵ���������
        TableRowMap judgeTab = new TableRowMap();
        
        // ����Ҫ���ص�������ݼ��е����������ȸ����жϵ���������
        judgeTab = tab;
        
        // �����Ҫ���ص�������ݼ��е���������Ϊ�գ��ȴ���
        if (tab == null) {
            tab = new TableRowMap();
        }
        
        if (results == null || results.size() <= 0) {
            return tab;
        }
        
        boolean clearFlg = true;
        
        // �����������ݵ���ʱ��������<results>��ǩ��������appoint=trueʱ
        final TableRowMap appointTab = new TableRowMap();
        
        // ����<results>��ǩ�е�<result>����
        for (int i = 0; i < results.size(); i++) {
            
            // ȡ��<result>���ݶ���
            final Result result = results.get(i);
            
            // ���������ж��Ƿ�����渳ֵ
            if (judgeOperator(result, judgeTab, param, seData)) {
                // �Ƿ�ǿ�����<result>��ǩ�ж��������<appoint>�����Ϊtrue����<result>��ǩ�е���Ŀ���������
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
     * ������<results>��ǩ�е����ݣ�ʹ֮���õ����ݲ�ѯ�����.
     * 
     * @param tab ���ݱ������
     * @param result <result>��ǩ�е�����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param valueTab ������Դ����
     * @throws RichClientWebException RichClientWebException
     */
    private void setResultValues(
            TableRowMap tab, 
            Param param, 
            SessionData seData,
            final Result result, 
            TableRowMap valueTab) throws RichClientWebException {
        
        // ȡ��<result>���ݶ���ġ�targetId������ֵ���ڵ���Ŀ��������Ϊ����ؼ���ID���ڶ���Ŀ�У����Զ����ǩ�еĶ���ID
        final String targetId = result.getTargetId();
        Object value = ConstantsUtil.Str.EMPTY;
        // ���<result>��ǩ�е�������Ϊ�б����ݷ���
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
            // ����<result>��ǩȡ��Ҫ�Խ�����в�����ֵ
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
        // ���������ֵ
        tab.put(targetId, value);
    }
    
    /**
     * ������<params>��ǩ�е����ݣ�ʹ֮���õ����ݲ�ѯ������.
     * 
     * @param whereMap ��������
     * @param params <params>�е�����
     * @param param �û��������
     * @param seData �û��Ự����
     * @throws RichClientWebException RichClientWebException
     */
    private void reloadParams(Map<String, Object> whereMap, 
            List<com.richClientFrame.data.param.RequestParam.Param> params, 
            Param param, SessionData seData) 
        throws RichClientWebException {
        reloadParams(whereMap, params, param, seData, null);
    }
    
    /**
     * ������<params>��ǩ�е����ݣ�ʹ֮���õ����ݲ�ѯ������.
     * 
     * @param whereMap ��������
     * @param params <params>�е�����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param tab ���ݿ��ѯ����
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
     * ����<result>��ǩȡ��Ҫ�Խ�����в�����ֵ.
     * 
     * @param result ���ݽ������
     * @param tab ����
     * @param param �û��������
     * @param seData �û��Ự����
     * @return ���ز�����Ľ��
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
     * �Ѳ�ѯ���������ֵ���õ���������������.
     * 
     * @param whereMap ��ѯ��������
     * @param tab ������������
     */
//    private void setWhereMapToTab(Map<String, Object> whereMap, TableRowMap tab) {
//        if (whereMap != null && tab != null) {
//            for (String key : whereMap.keySet()) {
//                tab.put(key, whereMap.get(key));
//            }
//        }
//    }

    /**
     * ��ʼ��������Ϣ
     * @param param �û��������
     * @param seData �û��Ự����
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
     * ��ʼ��������Ϣ
     * @param param �û��������
     * @param seData �û��Ự����
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
     * ��ʼ��handler����.
     * @param param �û��������
     * @param seData �û��Ự����
     */
    private void initializeHandler(Param param, SessionData seData) {
        initializeFormat(param, seData);
        initializeDataSource(param, seData);
        initializeExternal(param, seData);
        initializeAction(param, seData);
        initializeExpand(param, seData);
    }
    
    /**
     * ��ʼ����ʽ������
     * @param param �û��������
     * @param seData �û��Ự����
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
     * ��ʼ����ʽ������
     * @param param �û��������
     * @param seData �û��Ự����
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
     * ��ʼ��������Ϣ
     * @param param �û��������
     * @param seData �û��Ự����
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
     * ��������Ϣ��������.
     * 
     * @param cmb <cmb>��ǩ����
     * @param param �û��������
     * @return ��������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    private TableRowMap[] createCmbInfo(Cmb cmb, Param param) throws RichClientWebException {
        return createCmbInfo(cmb, param, null);
    }
    
    /**
     * ��������Ϣ��������.
     * 
     * @param cmb <cmb>��ǩ����
     * @param param �û��������
     * @param tabData ���ݿ�����
     * @return ��������Ϣ
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
     * ��������Ϣ����������showPage�����ʼ����ʱ��.
     * 
     * @param cmb <cmb>��ǩ����
     * @param param �û��������
     * @return ��������Ϣ
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
     * ��ȡexcel�ķ���.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @param excel ��Ӧ<excel>��ǩ�е���Ϣ����
     * @param tabs ������Ϣ��
     * @throws RichClientWebException RichClientWebException
     */
    private void readExcel(Param param, SessionData seData, IExcel excel, List<TableRowMap> tabs) 
        throws RichClientWebException {
        readExcel(param, seData, excel, tabs, null);
    }
    
    /**
     * ��ȡexcel�ķ���.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @param excel ��Ӧ<excel>��ǩ�е���Ϣ����
     * @param tab ������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    private void readExcel(Param param, SessionData seData, IExcel excel, TableRowMap tab) 
        throws RichClientWebException {
        readExcel(param, seData, excel, tab, null);
    }
    
    /**
     * ��ȡexcel�ķ���.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @param excel ��Ӧ<excel>��ǩ�е���Ϣ����
     * @param tab ������Ϣ
     * @param inputStream ��ȡ�ļ���
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
     * ��ȡexcel�ķ���.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @param excel ��Ӧ<excel>��ǩ�е���Ϣ����
     * @param tabs ������Ϣ
     * @param inputStream ��ȡ�ļ���
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
     * ����excel�ķ���.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @param excel ��Ӧ<excel>��ǩ�е���Ϣ����
     * @param tabs ������Ϣ��
     * @return excel������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    private ResponseExcel writeExcel(Param param, SessionData seData, IExcel excel, List<TableRowMap> tabs) 
        throws RichClientWebException {
        LOG.debug("writeExcel", "start", "tabs size = " + tabs.size());
        final Excel excelItem = excel.getExcel();
        if (excelItem == null) {
            return null;
        }
        // excel��������[TYPE_WRITE������excel�ļ����浽��������TYPE_STREAM����excelͨ�����ķ�ʽ�ṩ���ͻ���]
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
            // ����excel zip
            writeExcel(param, seData, excelItem, tabs, filesList);
        } else {
            // ����excel��������
            wb = ExcelUtil.createExcelFile(excelItem);
            // ����excel
            writeExcel(param, seData, excelItem, tabs, wb);
        }
        if (autoZipEnabled && autoRange > 0) {
            return ExcelUtil.createExcelFile(excelItem, type, filesList);
            
        }
        LOG.debug("writeExcel", "end", "type = " + type);
        return ExcelUtil.createExcelFile(excelItem, type, wb);
    }
    
    /**
     * ����excel�ķ���.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @param excelItem ��Ӧ<excel>��ǩ�е���Ϣ����
     * @param tabs ������Ϣ��
     * @param wb excel����
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
     * ����excel�ķ���.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @param excelItem ��Ӧ<excel>��ǩ�е���Ϣ����
     * @param tabs ������Ϣ��
     * @param filesList excel�ļ�����
     * @throws RichClientWebException RichClientWebException
     */
    private void writeExcel(
            Param param, SessionData seData, Excel excelItem, List<TableRowMap> tabs, List<File> filesList) 
        throws RichClientWebException {
        // ����excel��������
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
     * @Description: ������Ϣ��������Ҫ��zip����excel�ļ�
     * @author king
     * @since Nov 27, 2012 11:37:37 AM 
     * @version V1.0
     * @param excelItem excel����
     * @param filesList �����ļ�����������
     * @param wb ����excel�Ķ���
     * @param folderPath �ļ���·��
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
     * ����excel�ķ���.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @param excel ��Ӧ<excel>��ǩ�е���Ϣ����
     * @param tab ������Ϣ
     * @return excel������Ϣ
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
     * ����excel������
     * @param param �û��������
     * @param seData �û��Ự����
     * @param tab ������Ϣ
     * @param excelItem ��Ӧ<excel>��ǩ�е���Ϣ����
     * @param outWb excel����
     * @param accumulation ���ݷ��ض�ά��Ϣ�����к�
     * @throws RichClientWebException RichClientWebException
     */
    @SuppressWarnings("unchecked")
    private void createExcelContent(Param param, SessionData seData, TableRowMap tab,
            final Excel excelItem, Workbook outWb, int accumulation) throws RichClientWebException {
        LOG.debug("createExcelContent", "start", "accumulation = " + accumulation);
        // ȡ��<excel>��ǩ����<sheet>��Ϣ����
        final List<Sheet> sheets = excelItem.getSheets();
        
        for (int i = 0; i < sheets.size(); i++) {
            final Sheet sheet = sheets.get(i);
            final String sheetName = sheet.getName();
            
            // �Ƿ�������excel·�����߶�ȡ��excel�ļ���
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
                
                // ��Ӧ<row>��ǩ�е�<targetId>���ԣ���ֵΪaction������Ϣ��key
                final String targetId = row.getTargetId();
                if (CommonUtil.isNotEmpty(targetId)) {
                    final Object obj = tab.get(targetId);
                    if (obj instanceof List) {
                        final List<TableRowMap> tabList = (List<TableRowMap>)obj;
                        for (int k = 0; k < tabList.size(); k++) {
                            final TableRowMap tabRow = tabList.get(k);
                            if (setValuesInExistExcel) {
                                // �����е�excelģ����е�Ԫ�����Ϣ����
                                setCellContent(param, seData, tabRow, outWb, sheetItem, row, k);
                            } else {
                                createCellContent(param, seData, tabRow, outWb, sheetItem, row, k);
                            }
                        }
                    }
                } else {
                    if (setValuesInExistExcel) {
                        // �����е�excelģ����е�Ԫ�����Ϣ����
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
     * �����е�excelģ����е�Ԫ�����Ϣ����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param tab ������Ϣ
     * @param sheetItem ��Ӧexcelģ������sheet����
     * @param outWb excel����
     * @param row ��Ӧ<row>��ǩ�е���Ϣ����
     * @param index ��ʼ����
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
        // ǿ��excel�ڼ������ʱ���ù�ʽ
        sheetItem.setForceFormulaRecalculation(true);
        final int rowSite = ExcelUtil.countRowIndex(row, index);
        
        boolean alternate = row.isAlternate();
        if (!alternate) {
            alternate = ControlRequestMap.getInstance().getRowAlternateInResource();
        }
        
        // ��sheet��ȡ��row����
        rowItem = sheetItem.getRow(rowSite);
        
        // ���row����Ϊ���򴴽�
        if (rowItem == null) {
            rowItem = sheetItem.createRow(rowSite);
        }
        // �����и�
        final float height = row.getHeight();
        if (height != -1) {
            rowItem.setHeightInPoints(height);
        }
        final List<Cell> cells = row.getCells();
        for (int k = 0; k < cells.size(); k++) {
            final Cell cell = cells.get(k);
            // ����<condition>��ǩ���������жϲ���
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
            
            // ���õ�Ԫ�����������
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
     * ������Ԫ�񲢽��е�Ԫ�����Ϣ����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param tab ������Ϣ
     * @param sheetItem ��Ӧexcelģ������sheet����
     * @param outWb excel����
     * @param row ��Ӧ<row>��ǩ�е���Ϣ����
     * @param index ��ʼ����
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
     * �����жϲ���.
     * 
     * @param conditionFace �����ж϶���
     * @param tab ����
     * @param param �û��������
     * @param seData �û��Ự����
     * @throws RichClientWebException RichClientWebException
     * @return �жϺ�Ľ��
     */
    public Map<String, List<String>> judgeSiftOperator(
            ICondition conditionFace, TableRowMap tab, Param param, SessionData seData) 
        throws RichClientWebException {
        final List<Condition> conditions = conditionFace.getConditions();
        final String id = conditionFace.getConditionId();
        return judgeOperator(conditions, tab, param, seData, id);
    }
    
    /**
     * ����<condition>��ǩ���������жϲ���.
     * 
     * @param conditionFace �����ж϶���
     * @param tab ����
     * @param param �û��������
     * @param seData �û��Ự����
     * @throws RichClientWebException RichClientWebException
     * @return �жϺ�Ľ��
     */
    public boolean judgeOperator(ICondition conditionFace, TableRowMap tab, Param param, SessionData seData) 
        throws RichClientWebException {
        return judgeOperator(conditionFace, tab, param, seData, null);
    }
    
    /**
     * �����жϲ���.
     * 
     * @param conditionFace �����ж϶���
     * @param tab ����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param targetId ����ؼ�ID
     * @throws RichClientWebException RichClientWebException
     * @return �жϺ�Ľ��
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
     * �����жϲ���.
     * 
     * @param conditions �����ж�����
     * @param tab ����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param id �����ж���ID
     * @throws RichClientWebException RichClientWebException
     * @return �жϺ�Ľ����true������������false��������������
     */
    public Map<String, List<String>> judgeOperator(
            List<Condition> conditions, TableRowMap tab, Param param, SessionData seData, String id) 
        throws RichClientWebException {
        return judgeOperator(conditions, tab, param, seData, id, null);
    }
    
    /**
     * �����жϲ���.
     * 
     * @param conditions �����ж�����
     * @param tab ����
     * @param param �û��������
     * @param seData �û��Ự����
     * @param id �����ж���ID
     * @param targetId ����ؼ�ID
     * @throws RichClientWebException RichClientWebException
     * @return �жϺ�Ľ����true������������false��������������
     */
    private Map<String, List<String>> judgeOperator(
            List<Condition> conditions, TableRowMap tab, Param param, SessionData seData, String id, String targetId) 
        throws RichClientWebException {
        
        Map<String, List<String>> judgeMap = null;
        
        // ������֤������Ŀ��ŵĿؼ�ID
        final String checkIndexId = ControlRequestMap.getInstance().getCheckIndex(param);
        
        TableRowMap judegTab = tab;
        if (conditions != null && conditions.size() > 0) {
            for (int i = 0; i < conditions.size(); i++) {
                
                final Condition condition = conditions.get(i);
                
                final Map<String, Object> whereMap = createCondition(param);
                
                final boolean isExecute = doActionPre(condition, param, seData, whereMap);
                
                judegTab = executeSql(judegTab, condition, whereMap, isExecute);
                
                // ��condition��ǩ��sql��ѯ�Ľ�������ڽ������������
                if (judegTab != null && tab != null) {
                    tab.putAll(judegTab);
                }
                
                // value��ȡֵ��Χ��ȷ�����Ƚϵ�ֵ
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
     * @param judegTab �ж���������������
     * @param condition condition��ǩ����
     * @param whereMap ��������
     * @param isExecute �Ƿ�ִ��sql��ʶ
     * @return �ж���������������
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
     * �����жϲ���.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @param condition �����ж϶���
     * @param judegTab ����
     * @param id �����ж���ID
     * @param index �ؼ����к�
     * @throws RichClientWebException RichClientWebException
     * @return �жϺ�Ľ����true������������false��������������
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
     * �����жϲ���.
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @param condition �����ж϶���
     * @param judegTab ����
     * @param id �����ж���ID
     * @param index �ؼ����к�
     * @param targetId ����ؼ�ID
     * @throws RichClientWebException RichClientWebException
     * @return �жϺ�Ľ����true������������false��������������
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
        
        // �жϷ���
        final String operator = condition.getOperator();
        
        // value��ȡֵ��Χ��ȷ�����Ƚϵ�ֵ
        final Compare compareSource = condition.getCompareSource();
        final String compareSourceStr = compareSource.getValue();
        
        // �Ƚ�ֵ
        final Compare compareTarget = condition.getCompareTarget();
        String compareTargetStr = ConstantsUtil.Str.EMPTY;
        if (compareTarget != null) {
            compareTargetStr = compareTarget.getValue();
        }
        // ���ݱ�ǩ�еķ��ŷ�����Ӧ��ֵ
        String compareSourceValue = RequestConfigUtil.getRequestValue(
                judegTab, param, seData, compareSourceStr, index, targetId);
        // ���ݱ�ǩ�еķ��ŷ�����Ӧ��ֵ
        String compareTargetValue = RequestConfigUtil.getRequestValue(
                judegTab, param, seData, compareTargetStr, index, targetId);
        // ���ø�ʽ������ȡֵ
        compareSourceValue = CommonUtil.toString(executeFormat(compareSource, compareSourceValue));
        // ���ø�ʽ������ȡֵ
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
            } else if ("��".equals(operator)) {
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
            } else if ("��=".equals(operator)) {
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
            } else if ("��".equals(operator)) {
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
            } else if ("��=".equals(operator)) {
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
     * �ؼ�������ʽ�ķ���.
     * 
     * @param tab �ؼ�����
     * @param param �û��������
     * @param tabRow ����
     * @param seData �û��Ự����
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
     * �ؼ�������ʽ�ķ���.
     * 
     * @param tab �ؼ�����
     * @param param �û��������
     * @param tabRow ����
     * @param seData �û��Ự����
     * @param targetId ����ؼ�ID
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
     * ����Ŀ���пؼ�������ʽ�ķ���(�Բ��Ǵ����ݿ���ȡ�ã���XML�����õĿؼ�����������ʽ).
     * 
     * @param tabRow ����
     * @param line �ؼ�����
     * @param param �û��������
     * @param seData �û��Ự����
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
     * �ؼ�������ʽ�ķ���(�Բ��Ǵ����ݿ���ȡ�ã���XML�����õĿؼ�����������ʽ).
     * 
     * @param tabRow ����
     * @param tabList �ؼ�����
     * @param param �û��������
     * @param seData �û��Ự����
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
     * �����������������.
     * 
     * @param memcached �������������
     */
    public void setMemcached(ContorlMemcached memcached) {
        mMemcached = memcached;
    }

    /**
     * �������������ȡ��.
     * 
     * @return �������������
     */
    public ContorlMemcached getMemcached() {
        return mMemcached;
    }

    /**
     * ��ʽ����������.
     * 
     * @param format ��ʽ������
     */
    public void setFormat(IFormatFace format) {
        mFormat = format;
    }
    
    /**
     * ���ƴ����������.
     * 
     * @param execute ���ƴ������
     */
    public void setExecute(IExternalFace execute) {
        mExecute = execute;
    }

    /**
     * ���ݿ������������.
     * 
     * @param db ���ݿ��������
     */
    public void setDb(IDbService db) {
        this.db = db;
    }

    /**
     * ���ݿ��������ȡ��.
     * 
     * @return ���ݿ��������
     */
    public IDbService getDb() {
        return db;
    }

    /**
     * ����ؼ����ö�������.
     * 
     * @param htmlTab ����ؼ����ö���
     */
    public void setHtmlTab(HtmlTabHandler htmlTab) {
        mHtmlTab = htmlTab;
    }

    /** 
    * @Description: ȡ������Դ�л�����
    * @author king
    * @since Oct 8, 2012 11:02:36 AM 
    * 
    * @return ����Դ�л�����
    */
    public IDataSourceFace getDataSource() {
        return mDataSource;
    }

    /** 
    * @Description: ��������Դ�л�����
    * @author king
    * @since Oct 8, 2012 11:02:49 AM 
    * 
    * @param dataSource ����Դ�л�����
    */
    public void setDataSource(IDataSourceFace dataSource) {
        this.mDataSource = dataSource;
    }

    /** 
    * @Description: ȡ���������
    * @author king
    * @since Oct 8, 2012 11:02:58 AM 
    * 
    * @return �������
    */
    public IExpandFace getExpand() {
        return mExpand;
    }

    /** 
    * @Description: �����������
    * @author king
    * @since Oct 8, 2012 11:03:11 AM 
    * 
    * @param expand �������
    */
    public void setExpand(IExpandFace expand) {
        this.mExpand = expand;
    }
    
    /** 
    * @Description: �Զ�������Ĭ�Ϸ���
    * @author king
    * @since Nov 3, 2012 7:05:10 PM 
    * 
    * @return �Ƿ�ִ�п��Ĭ����ת����[true����ִ�У�false��ִ��]
    */
    public boolean execute() {
        return true;
    }
    
    /** 
    * @Description: �Զ�������ִ�����ʼ����������
    * @author king
    * @since Nov 3, 2012 7:14:39 PM 
    * 
    * @param context ��������
    * @param request �ͻ����������
    * @param response ��������Ӧ����
    * @param session session����
    */
    public void handlerInit(ServletContext context, HttpServletRequest request, 
            HttpServletResponse response, SessionData session) {
        this.context = context;
        this.request = request;
        this.response = response;
        this.session = session;
    }

    /** 
    * @Description: ȡ����������
    * @author king
    * @since Nov 3, 2012 7:15:31 PM 
    * 
    * @return ��������
    */
    public ServletContext getApplication() {
        return context;
    }

    /** 
    * @Description: ȡ�ÿͻ����������
    * @author king
    * @since Nov 3, 2012 7:15:43 PM 
    * 
    * @return �ͻ����������
    */
    public HttpServletRequest getRequest() {
        return request;
    }

    /** 
    * @Description: ȡ�÷�������Ӧ����
    * @author king
    * @since Nov 3, 2012 7:15:52 PM 
    * 
    * @return ��������Ӧ����
    */
    public HttpServletResponse getResponse() {
        return response;
    }

    /** 
    * @Description: ȡ��session����
    * @author king
    * @since Nov 3, 2012 7:16:02 PM 
    * 
    * @return session����
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
