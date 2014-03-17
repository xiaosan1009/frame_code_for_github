package com.richClientFrame.info;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.access.ReadXmlFolder;
import com.richClientFrame.data.param.RequestParam;
import com.richClientFrame.data.param.RequestParam.BackgroundColor;
import com.richClientFrame.data.param.RequestParam.Cell;
import com.richClientFrame.data.param.RequestParam.CellBorder;
import com.richClientFrame.data.param.RequestParam.CellDataFormat;
import com.richClientFrame.data.param.RequestParam.CellFont;
import com.richClientFrame.data.param.RequestParam.CellGround;
import com.richClientFrame.data.param.RequestParam.CellLayout;
import com.richClientFrame.data.param.RequestParam.CellStyle;
import com.richClientFrame.data.param.RequestParam.Checker;
import com.richClientFrame.data.param.RequestParam.Cmb;
import com.richClientFrame.data.param.RequestParam.Compare;
import com.richClientFrame.data.param.RequestParam.Complex;
import com.richClientFrame.data.param.RequestParam.Condition;
import com.richClientFrame.data.param.RequestParam.DataSource;
import com.richClientFrame.data.param.RequestParam.Display;
import com.richClientFrame.data.param.RequestParam.Excel;
import com.richClientFrame.data.param.RequestParam.External;
import com.richClientFrame.data.param.RequestParam.Format;
import com.richClientFrame.data.param.RequestParam.Item;
import com.richClientFrame.data.param.RequestParam.Items;
import com.richClientFrame.data.param.RequestParam.Lists;
import com.richClientFrame.data.param.RequestParam.Mail;
import com.richClientFrame.data.param.RequestParam.Mails;
import com.richClientFrame.data.param.RequestParam.Param;
import com.richClientFrame.data.param.RequestParam.Result;
import com.richClientFrame.data.param.RequestParam.Row;
import com.richClientFrame.data.param.RequestParam.Servlet;
import com.richClientFrame.data.param.RequestParam.Sheet;
import com.richClientFrame.data.param.RequestParam.Style;
import com.richClientFrame.data.param.RequestParam.Taber;
import com.richClientFrame.data.param.RequestParam.TextColor;
import com.richClientFrame.data.param.RequestParam.Update;
import com.richClientFrame.data.param.RequestParam.Upload;
import com.richClientFrame.data.param.RequestParamFace.IActionStep;
import com.richClientFrame.data.param.RequestParamFace.IChecker;
import com.richClientFrame.data.param.RequestParamFace.ICmb;
import com.richClientFrame.data.param.RequestParamFace.ICondition;
import com.richClientFrame.data.param.RequestParamFace.IDataSource;
import com.richClientFrame.data.param.RequestParamFace.IExcel;
import com.richClientFrame.data.param.RequestParamFace.IExternal;
import com.richClientFrame.data.param.RequestParamFace.IFormat;
import com.richClientFrame.data.param.RequestParamFace.IItem;
import com.richClientFrame.data.param.RequestParamFace.IList;
import com.richClientFrame.data.param.RequestParamFace.IParam;
import com.richClientFrame.data.param.RequestParamFace.IWhether;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * request.xml的读取与保存.
 * 
 * @author king
 * @since 2010.03.19
 */
public class RequestMapReader extends ReadXmlFolder {
    
    /**
     * logger
     */
    private static LogUtil sLog = new LogUtil(RequestMapReader.class);
    
    private static final String RESOURCE = "resource";
    
    private static final String DISPS = "disps";
    
    private static final String DISP = "disp";
    
    private static final String CODE = "code";
    
    private static final String CLASS = "class";
    
    private static final String JSP = "jsp";
    
    private static final String COMPLEX = "complex";
    
    private static final String DATA_SOURCE = "dataSource";
    
    private static final String EXCEL_STYLES = "excelStyles";
    
    private static final String LISTS = "lists";
    
    private static final String LIST = "list";
    
    private static final String SESSION_ALLOW_NULL = ConstantsUtil.Xsd.PARAM_SESSION_ALLOW_NULL;
    
    private static final String KEEP = "keep";
    
    private static final String DIMENSION = "dimension";
    
    private static final String PAGE_SIZE = "pageSize";
    
    private static final String SQL = "sql";
    
    private static final String TARGET_ID = "targetId";
    
    private static final String URL = "url";
    
    private static final String MAIL_SERVER_HOST = "mailServerHost";
    
    private static final String MAIL_SERVER_PORT = "mailServerPort";
    
    private static final String USER_NAME = "userName";
    
    private static final String PASSWORD = "password";
    
    private static final String VALIDATE = "validate";
    
    private static final String FROM_ADDRESS = "fromAddress";
    
    private static final String TO_ADDRESS = "toAddress";
    
    private static final String SUBJECT = "subject";
    
    private static final String CONTENT = "content";
    
    private static final String SELECTED_KEY = "selectedKey";
    
    private static final String CONSTANT = "constant";
    
    private static final String APPOINT = "appoint";
    
    private static final String CMDS = "cmds";
    
    private static final String CMD = "cmd";
    
    private static final String LOGOUT = "logout";
    
    private static final String DESCRIBE = "describe";
    
    private static final String REQUEST = "request";
    
    private static final String FORWARD = "forward";
    
    private static final String CHECK = "check";
    
    private static final String CHECK_INDEX = "checkIndex";
    
    private static final String CLIENT = "client";
    
    private static final String INPUT_STREAM = "inputStream";
    
    private static final String SOURCE = "source";
    
    private static final String USERID = "userId";
    
    private static final String CMBS = "cmbs";
    
    private static final String CMB = "cmb";
    
    private static final String OPTION = "option";
    
    private static final String VALUE = "value";
    
    private static final String LABEL = "label";
    
    private static final String ID = "id";
    
    private static final String COUNT = "count";
    
    private static final String START = "start";
    
    private static final String END = "end";
    
    private static final String METHOD = "method";
    
    private static final String TARGET = "target";
    
    private static final String NAME = "name";
    
    private static final String TYPE = "type";
    
    private static final String FILE_TYPE = "fileType";
    
    private static final String UPLOAD = "upload";
    
    private static final String SERVLET = "servlet";
    
    private static final String FILE_NAME = "fileName";
    
    private static final String USE_FILE_NAME = "useFileName";
    
    private static final String PATH = "path";
    
    private static final String DATA_TYPE = "dataType";
    
    private static final String FTP = "ftp";
    
    private static final String INITIALIZE = "initialize";
    
    private static final String UPDATES = "updates";
    
    private static final String SUCCESS = "success";
    
    private static final String ERROR = "error";
    
    private static final String UPDATE = "update";
    
    private static final String INDEX = "index";
    
    private static final String HAS_RESULT = "hasResult";
    
    private static final String RESCODE = "resCode";
    
    private static final String ITEMS = "items";
    
    private static final String MAILS = "mails";
    
    private static final String ITEM = "item";
    
    private static final String MAIL = "mail";
    
    private static final String EXTERNALS = "externals";
    
    private static final String EXTERNAL = "external";
    
    private static final String PARAMETERS = "parameters";
    
    private static final String PARAMETER = "parameter";
    
    private static final String PARAMS = "params";
    
    private static final String PARAM = "param";
    
    private static final String KEY = "key";
    
    private static final String SCOPE = "scope";
    
    private static final String RESULTS = "results";
    
    private static final String RESULT = "result";
    
    private static final String PROPERTY = "property";
    
    private static final String DISPLAY = "display";
    
    private static final String DISPLAYS = "displays";
    
    private static final String TEXT_COLOR = "textColor";
    
    private static final String TEXT_COLORS = "textColors";
    
    private static final String BACKGROUND_COLOR = "backgroundColor";
    
    private static final String BACKGROUND_COLORS = "backgroundColors";
    
    private static final String OPERATOR = "operator";
    
    private static final String CONDITIONS = "conditions";
    
    private static final String FORMATS = "formats";
    
    private static final String WHETHER = "whether";
    
    private static final String CONDITION = "condition";
    
    private static final String COMPARE_SOURCE = "compareSource";
    
    private static final String COMPARE_TARGET = "compareTarget";
    
    private static final String FORMAT = "format";
    
    private static final String ALIGN = "align";
    
    private static final String FORE_COLOR = "foreColor";
    
    private static final String BACK_COLOR = "backColor";
    
    private static final String FILL = "fill";
    
    private static final String VERTICAL = "vertical";
    
    private static final String SOURCE_FORMAT = "sourceFormat";
    
    private static final String STYLES = "styles";
    
    private static final String STYLE = "style";
    
    private static final String TABS = "tabs";
    
    private static final String TAB = "tab";
    
    private static final String CHECKS = "checks";
    
    private static final String CHECKER = "check";
    
    private static final String MESSAGE = "message";
    
    private static final String EXCEL = "excel";
    
    private static final String IN_ABSOLUTE = "inAbsolute";
    
    private static final String OUT_ABSOLUTE = "outAbsolute";
    
    private static final String SHEETS = "sheets";
    
    private static final String SHEET = "sheet";
    
    private static final String ROWS = "rows";
    
    private static final String CELLS = "cells";
    
    private static final String CELL = "cell";
    
    private static final String ROW = "row";
    
    private static final String ALTERNATE = "alternate";
    
    private static final String ODD_COLOR = "oddColor";
    
    private static final String EVEN_COLOR = "evenColor";
    
    private static final String SITE = "site";
    
    private static final String WIDTH = "width";
    
    private static final String HEIGHT = "height";
    
    private static final String CELL_STYLE = "cellStyle";
    
    private static final String CELL_BORDERS = "cellBorders";
    
    private static final String CELL_BORDER = "cellBorder";
    
    private static final String WHOLE_BORDER = "wholeBorder";
    
    private static final String LEFT_BORDER = "leftBorder";
    
    private static final String RIGHT_BORDER = "rightBorder";
    
    private static final String TOP_BORDER = "topBorder";
    
    private static final String BOTTOM_BORDER = "bottomBorder";
    
    private static final String CELL_FONT = "cellFont";
    
    private static final String CELL_DATA_FORMAT = "cellDataFormat";
    
    private static final String CELL_LAYOUT = "cellLayout";
    
    private static final String CELL_GROUND = "cellGround";
    
    private static final String COLOR = "color";
    
    private static final String BOLD = "bold";
    
    private static final String ITALIC = "italic";
    
    private static final String STRIKEOUT = "strikeout";
    
    private static final String FONT_HEIGHT = "fontHeight";
    
    private static final String TYPE_OFFSET = "typeOffset";
    
    private static final String UNDER_LINE = "underline";
    
    private static final String IN_NAME = "inName";
    
    private static final String IN_PATH = "inPath";
    
    private static final String OUT_NAME = "outName";
    
    private static final String OUT_PATH = "outPath";

    /**
     * 构造函数.
     */
    public RequestMapReader() {
        super();
    }

    /**
     * request.xml的读取.
     * @param path request.xml的路径
     * @param resourceMap 资源存储器
     * @return Map request.xml的内容的保存对象
     * @throws RichClientWebException RichClientWebException
     */
    public static synchronized Map<String, Map<String, RequestParam>> read(
            String path, Map<String, RequestParam> resourceMap) 
        throws RichClientWebException {

        sLog.info("read", "start", "path = " + path);
        final Map<String, Map<String, RequestParam>> config 
            = new HashMap<String, Map<String, RequestParam>>();
        final List<Document> list = parse(path);

        if (list == null) {
            return null;
        }
        
        for (int i = 0; i < list.size(); i++) {
            analyze(list.get(i), config, resourceMap);
        }
        
        sLog.info("read", "end", "config = " + config);
        
        return config;
    }
    
    /**
     * doc的解析
     * @param doc DOC对象
     * @param reqMap 解析对象容器
     * @param resourceMap 资源存储器
     * @return doc的解析后对象
     * @throws RichClientWebException RichClientWebException
     */
    private static Map<String, Map<String, RequestParam>> analyze(
            Document doc, 
            Map<String, Map<String, RequestParam>> reqMap, 
            Map<String, RequestParam> resourceMap) throws RichClientWebException {
        
        // 资源
        setResource(doc, resourceMap);

        // 画面
        setDisps(doc, reqMap);
        
        return reqMap;
    }
    
    /**
     * 取得节点对象
     * @param element 节点
     * @param key 节点KEY
     * @return 节点对象
     */
    private static Element getElement(Element element, String key) {
        Element elementResult = null;
        final NodeList childs = element.getChildNodes();
        for (int i = 0; i < childs.getLength(); i++) {
            final Node node = childs.item(i);
            if (key.equals(node.getNodeName())) {
                elementResult = (Element)node;
            }
        }
        return elementResult;
    }
    
    /**
     * 资源设定
     * @param doc DOC对象
     * @param resourceMap 资源存储器
     * @throws RichClientWebException RichClientWebException
     */
    private static void setResource(Document doc, Map<String, RequestParam> resourceMap) 
        throws RichClientWebException {
        final NodeList resourceList = doc.getElementsByTagName(RESOURCE);
        if (resourceList != null && resourceList.getLength() > 0) {
            final Element resourceElement = (Element)resourceList.item(0);
            if (resourceElement != null) {
                RequestParam resourceParam = new RequestParam();
                if (resourceMap.get(RESOURCE) != null) {
                    resourceParam = resourceMap.get(RESOURCE);
                }
                setCmbs(resourceElement, resourceParam, "commonResource");
                setDataSource(resourceElement, resourceParam);
                setExcelStyles(resourceElement, resourceParam);
                setExcel(resourceParam, resourceParam, resourceElement);
                resourceMap.put(RESOURCE, resourceParam);
            }
        }
    }
    
    /**
     * 画面设定
     * @param doc DOC对象
     * @param reqMap 解析对象容器
     * @throws RichClientWebException RichClientWebException
     */
    private static void setDisps(Document doc, Map<String, Map<String, RequestParam>> reqMap) 
        throws RichClientWebException {
        final NodeList dispsList = doc.getElementsByTagName(DISPS);
        if (dispsList != null && dispsList.getLength() > 0) {
            final Element dispsElement = (Element)dispsList.item(0);
            if (dispsElement != null) {
                final NodeList dispList = dispsElement.getElementsByTagName(DISP);
                for (int nCnt = 0; nCnt < dispList.getLength(); nCnt++) {
                    final Map<String, RequestParam> cmdMap = new HashMap<String, RequestParam>();
                    final Element dispElement = (Element)dispList.item(nCnt);
                    final String disp = initDisp(cmdMap, dispElement);
                    
                    // 方法属性
                    setCmds(dispElement, cmdMap);
                    
                    if (!reqMap.containsKey(disp)) {
                        reqMap.put(disp, cmdMap);
                    } else {
                        sLog.fatal("画面ID 【" + disp + "】重复使用！");
                        throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR);
                    }
                }
            }
        }
    }

    /**
     * 画面初始化方法
     * @param cmdMap 方法对象容器
     * @param dispElement 画面节点对象
     * @return 画面ID
     * @throws RichClientWebException RichClientWebException
     */
    private static String initDisp(final Map<String, RequestParam> cmdMap, final Element dispElement) 
        throws RichClientWebException {
        final String disp = dispElement.getAttribute(CODE);
        final String handler = dispElement.getAttribute(CLASS);
        final String initJsp = dispElement.getAttribute(JSP);
        final String logout = dispElement.getAttribute(LOGOUT);
        
        final RequestParam initParam = new RequestParam();
        initParam.setHandlerClass(handler);
        initParam.setDispCode(disp);
        initParam.setMethodCode(ConstantsUtil.Method.SHOW_PAGE_CODE);
        initParam.getMethod().setName(ConstantsUtil.Method.SHOW_PAGE);
        initParam.setJspName(initJsp);
        if (ControlConfig.getInstance().getConfiguration().isDebug()) {
            initParam.setLogout("0");
        } else {
            initParam.setLogout(logout);
        }
        
        // 下拉框
        setCmbs(dispElement, initParam, disp);
        
        cmdMap.put(ConstantsUtil.Method.SHOW_PAGE_CODE, initParam);
        return disp;
    }
    
    /**
     * 列表设定
     * @param element 节点对象
     * @return 列表
     * @throws RichClientWebException RichClientWebException
     */
    private static RequestParam.List setList(Element element) 
        throws RichClientWebException {
        return setList(element, null, null, null, null);
    }
    
    private static void setLists(Element element, Complex complex, String disp) 
        throws RichClientWebException {
        final Element listsElement = getElement(element, LISTS);
        if (listsElement != null) {
            final Lists lists = new RequestParam().new Lists(); 
            final NodeList listList = listsElement.getElementsByTagName(LIST);
            if (listList != null && listList.getLength() > 0) {
                for (int listCnt = 0; listCnt < listList.getLength(); listCnt++) {
                    final Element listElement = (Element)listList.item(listCnt);
                    final RequestParam.List list = new RequestParam().new List();
                    createListContent(disp, list, listElement);
                    lists.addList(list);
                }
            }
            complex.setLists(lists);
        }
    }
    
    /**
     * 列表设定
     * @param element 节点对象
     * @param paramObj 节点对象容器
     * @param handler 机能class
     * @param disp 画面ID
     * @param cmdMap 方法容器
     * @return 列表
     * @throws RichClientWebException RichClientWebException
     */
    private static RequestParam.List setList(
            Element element, RequestParam paramObj, String handler, String disp, Map<String, RequestParam> cmdMap) 
        throws RichClientWebException {
        RequestParam.List list = null;
        final Element listElement = getElement(element, LIST);
        if (listElement != null) {
            list = new RequestParam().new List();
            final String pageSize = createListContent(disp, list, listElement);
            setExcel(paramObj, list, listElement);
            if (CommonUtil.isNotEmpty(pageSize) && cmdMap != null) {
                final RequestParam pagingParam = new RequestParam();
                pagingParam.setHandlerClass(handler);
                pagingParam.setDispCode(disp);
                pagingParam.setMethodCode(ConstantsUtil.Method.PAGING_CODE);
                pagingParam.getMethod().setName(ConstantsUtil.Method.PAGING);
                pagingParam.setJspName(ConstantsUtil.Page.PAGING_PATH);
                pagingParam.setLogout("0");
                cmdMap.put(ConstantsUtil.Method.PAGING_CODE, pagingParam);
            }
            if (paramObj != null) {
                setStyles(listElement, paramObj);
                paramObj.setList(list);
            }
        }
        return list;
    }

    /**
     * @Description: 
     * @author king
     * @since Jun 7, 2013 4:33:15 PM 
     * @version V1.0
     * @param element element
     * @param list list
     * @return String
     */
    private static String setPageSize(Element element, IList list) {
        final String pageSize = element.getAttribute(PAGE_SIZE);
        list.setPageSize(pageSize);
        return pageSize;
    }

    /**
     * 列表信息创建
     * @param disp 画面ID
     * @param list 列表对象
     * @param listElement 列表xml节点对象
     * @return pageSize
     * @throws RichClientWebException RichClientWebException
     */
    private static String createListContent(
            String disp,
            RequestParam.List list, 
            final Element listElement) throws RichClientWebException {
        final String pageSize = setPageSize(listElement, list);
        list.setSql(listElement.getAttribute(SQL));
        list.setSource(listElement.getAttribute(SOURCE));
        list.setTargetId(listElement.getAttribute(TARGET_ID));
        list.setIndex(listElement.getAttribute(ID));
        list.setInitialize(toBoolean(listElement.getAttribute(INITIALIZE)));
        setChecks(list, listElement);
        setParams(list, listElement);
        setResults(list, listElement);
        setExternals(list, listElement);
        setCmbs(listElement, list, disp);
        return pageSize;
    }
    
    /**
     * 二维列表设定
     * @param element 节点对象
     * @param paramObj 节点对象容器
     * @param handler 机能class
     * @param disp 画面ID
     * @param cmdMap 方法容器
     * @throws RichClientWebException RichClientWebException
     */
    private static void setDemension(
            Element element, RequestParam paramObj, String handler, String disp, Map<String, RequestParam> cmdMap) 
        throws RichClientWebException {
        final NodeList dimensionList = element.getElementsByTagName(DIMENSION);
        if (dimensionList != null && dimensionList.getLength() > 0) {
            final RequestParam.Dimension dimension = new RequestParam().new Dimension();
            final Element dimensionElement = (Element)dimensionList.item(0);
            setStyles(dimensionElement, paramObj);
            final String pageSize = setPageSize(dimensionElement, dimension);
            dimension.setSql(extractAttribute(element, DIMENSION, SQL));
            dimension.setSource(extractAttribute(element, DIMENSION, SOURCE));
            dimension.setTargetId(extractAttribute(element, DIMENSION, TARGET_ID));
            dimension.setIndex(extractAttribute(element, DIMENSION, ID));
            dimension.setList(setList(dimensionElement));
            setParams(dimension, dimensionElement);
            setResults(dimension, dimensionElement);
            paramObj.setDimension(dimension);
            if (CommonUtil.isNotEmpty(pageSize)) {
                final RequestParam pagingParam = new RequestParam();
                pagingParam.setHandlerClass(handler);
                pagingParam.setDispCode(disp);
                pagingParam.setMethodCode(ConstantsUtil.Method.PAGING_CODE);
                pagingParam.getMethod().setName(ConstantsUtil.Method.PAGING);
                pagingParam.setJspName(ConstantsUtil.Page.PAGING_PATH);
                pagingParam.setLogout("0");
                cmdMap.put(ConstantsUtil.Method.PAGING_CODE, pagingParam);
            }
        }
    }
    
    /**
     * 方法设定
     * @param element 节点对象
     * @param cmdMap 方法容器
     * @throws RichClientWebException RichClientWebException
     */
    private static void setCmds(Element element, Map<String, RequestParam> cmdMap) 
        throws RichClientWebException {
        final Element cmdsElement = getElement(element, CMDS);
        final String disp = element.getAttribute(CODE);
        final String handler = element.getAttribute(CLASS);
        if (cmdsElement != null) {
            final NodeList cmdList = cmdsElement.getElementsByTagName(CMD);
            for (int cmdCnt = 0; cmdCnt < cmdList.getLength(); cmdCnt++) {
                final RequestParam paramObj = new RequestParam();
                final Element cmdElement = (Element)cmdList.item(cmdCnt);
                
                final String cmd = cmdElement.getAttribute(CODE);
                paramObj.setHandlerClass(handler);
                paramObj.setDispCode(disp);
                paramObj.setMethodCode(cmd);
                paramObj.setDescribe(cmdElement.getAttribute(DESCRIBE));
                paramObj.setLogout(cmdElement.getAttribute(LOGOUT));
                paramObj.setRequest(cmdElement.getAttribute(REQUEST));
                paramObj.setForward(cmdElement.getAttribute(FORWARD));
                paramObj.setCheck(cmdElement.getAttribute(CHECK));
                paramObj.setCheckIndex(cmdElement.getAttribute(CHECK_INDEX));
                paramObj.setJspName(cmdElement.getAttribute(JSP));
                paramObj.setClient(cmdElement.getAttribute(CLIENT));
                paramObj.setInputStream(toBoolean(cmdElement.getAttribute(INPUT_STREAM)));
                
                // 自定义方法及初期化
                setMethod(cmdElement, paramObj);
                
                setComplex(cmdElement, paramObj, disp);
                
                // 多项目
                setList(cmdElement, paramObj, handler, disp, cmdMap);
                
                // 多项目
                setDemension(cmdElement, paramObj, handler, disp, cmdMap);
                
                // 单项目
                setItems(cmdElement, paramObj);
                
                // 数据库增删改
                setUpdates(cmdElement, paramObj);
                
                // 文件上传
                setUpload(cmdElement, paramObj);
                
                // 邮件配置设定
                setMails(cmdElement, paramObj);
                
                // 自定义服务对象设定
                setServlet(cmdElement, paramObj);
                
                // 切换数据源对象设置
                setDataSource(cmdElement, paramObj);
                
                cmdMap.put(cmd, paramObj);
            }
        }
    }
    
    /**
     * 下拉框对象设定
     * @param element 节点对象
     * @param paramObj 节点对象容器
     * @param disp 画面ID
     * @throws RichClientWebException RichClientWebException
     */
    private static void setCmbs(Element element, ICmb paramObj, String disp) 
        throws RichClientWebException {
        final Element cmbsElement = getElement(element, CMBS);
        if (cmbsElement != null) {
            final NodeList cmbList = cmbsElement.getElementsByTagName(CMB);
            if (cmbList != null && cmbList.getLength() > 0) {
                for (int cmbCnt = 0; cmbCnt < cmbList.getLength(); cmbCnt++) {
                    final Cmb cmb = new RequestParam().new Cmb();
                    final Element cmbElement = (Element)cmbList.item(cmbCnt);
                    final NodeList optionList = cmbElement.getElementsByTagName(OPTION);
                    for (int optionCnt = 0; optionCnt < optionList.getLength(); optionCnt++) {
                        final Element optionElement = (Element)optionList.item(optionCnt);
                        final TableRowMap tab = new TableRowMap();
                        String label = optionElement.getAttribute(LABEL);
                        if (label.startsWith(ConstantsUtil.Xsd.FMT)) {
                            final String fmt = label.substring(4);
                            label = ControlResourceMap.getInstance().getItemName(disp, fmt);
                        }
                        tab.put(ConstantsUtil.Cmb.VALUE, optionElement.getAttribute(VALUE));
                        tab.put(ConstantsUtil.Cmb.LABEL, label);
                        cmb.addOptions(tab);
                    }
                    cmb.setSql(cmbElement.getAttribute(SQL));
                    cmb.setSource(cmbElement.getAttribute(SOURCE));
                    cmb.setKey(cmbElement.getAttribute(KEY));
                    cmb.setTargetId(cmbElement.getAttribute(TARGET_ID));
                    cmb.setId(cmbElement.getAttribute(ID));
                    cmb.setValue(cmbElement.getAttribute(VALUE));
                    cmb.setLabel(cmbElement.getAttribute(LABEL));
                    cmb.setCount(toInt(cmbElement.getAttribute(COUNT)));
                    cmb.setStart(toInt(cmbElement.getAttribute(START)));
                    cmb.setEnd(toInt(cmbElement.getAttribute(END)));
                    paramObj.addCmb(cmb);
                }
            }
        }
    }
    
    /**
     * 方法对象设定
     * @param element 节点对象
     * @param paramObj 节点对象容器
     */
    private static void setMethod(Element element, RequestParam paramObj) {
        final Element methodElement = getElement(element, METHOD);
        if (methodElement != null) {
            paramObj.getMethod().setName(
                    extractAttribute(element, METHOD, NAME));
            paramObj.getMethod().setType(
                    toInt(extractAttribute(element, METHOD, TYPE)));
        }
    }
    
    /**
     * 上传对象设定
     * @param element 节点对象
     * @param paramObj 节点对象容器
     */
    private static void setUpload(Element element, RequestParam paramObj) {
        final Element uploadElement = getElement(element, UPLOAD);
        if (uploadElement != null) {
            final Upload upload = new RequestParam().new Upload();
            setParams(upload, uploadElement);
            setResults(upload, uploadElement);
            setChecks(upload, uploadElement);
            setExcel(paramObj, upload, uploadElement);
            upload.setIndex(extractAttribute(element, UPLOAD, ID));
            upload.setFileName(extractAttribute(element, UPLOAD, FILE_NAME));
            upload.setUseFileName(toBoolean(extractAttribute(element, UPLOAD, USE_FILE_NAME)));
            upload.setSql(extractAttribute(element, UPLOAD, SQL));
            upload.setSource(extractAttribute(element, UPLOAD, SOURCE));
            upload.setPath(extractAttribute(element, UPLOAD, PATH));
            upload.setDataType(extractAttribute(element, UPLOAD, DATA_TYPE));
            upload.setFtp(toBoolean(extractAttribute(element, UPLOAD, FTP)));
            upload.setList(toBoolean(extractAttribute(element, UPLOAD, LIST)));
            upload.setInitialize(toBoolean(extractAttribute(element, UPLOAD, INITIALIZE)));
            upload.setTargetId(extractAttribute(element, UPLOAD, TARGET_ID));
            paramObj.setUpload(upload);
        }
    }
    
    /**
     * 自定义服务对象设定
     * @param element 节点对象
     * @param paramObj 节点对象容器
     */
    private static void setServlet(Element element, RequestParam paramObj) {
        final Element servletElement = getElement(element, SERVLET);
        if (servletElement != null) {
            final Servlet servlet = new RequestParam().new Servlet();
            final String method = extractAttribute(element, SERVLET, METHOD);
            servlet.setMethod(method);
            paramObj.setServlet(servlet);
        }
    }
    
    /**
     * 修改对象设定
     * @param element 节点对象
     * @param paramObj 节点对象容器
     */
    private static void setUpdates(Element element, RequestParam paramObj) {
        final Element updatesElement = getElement(element, UPDATES);
        if (updatesElement != null) {
            paramObj.getUpdates().setId(extractAttribute(element, UPDATES, ID));
            paramObj.getUpdates().setSuccess(getHeaderType(extractAttribute(element, UPDATES, SUCCESS)));
            paramObj.getUpdates().setError(getHeaderType(extractAttribute(element, UPDATES, ERROR)));
            setParams(paramObj.getUpdates(), updatesElement);
            final NodeList updateList = updatesElement.getElementsByTagName(UPDATE);
            if (updateList != null && updateList.getLength() > 0) {
                for (int updateCnt = 0; updateCnt < updateList.getLength(); updateCnt++) {
                    final Element updateElement = (Element)updateList.item(updateCnt);
                    final Update update = new RequestParam().new Update();
                    update.setSql(updateElement.getAttribute(SQL));
                    update.setSource(updateElement.getAttribute(SOURCE));
                    update.setIndex(updateElement.getAttribute(INDEX));
                    update.setResult(toBoolean(updateElement.getAttribute(HAS_RESULT)));
                    setParams(update, updateElement);
                    setResults(update, updateElement);
                    setChecks(update, updateElement);
                    setConditions(update, updateElement);
                    setExternals(update, updateElement);
                    paramObj.getUpdates().addUpdate(update);
                }
            }
        }
    }
    
    /**
     * 单项目对象设定
     * @param element 节点对象
     * @param paramObj 节点对象容器
     */
    private static void setItems(Element element, IItem paramObj) {
        final Element itemsElement = getElement(element, ITEMS);
        if (itemsElement != null) {
            final Items items = new RequestParam().new Items();
            items.setId(itemsElement.getAttribute(ID));
            if (paramObj instanceof RequestParam) {
                setStyles(itemsElement, (RequestParam)paramObj);
            }
            final NodeList itemList = itemsElement.getElementsByTagName(ITEM);
            if (itemList != null && itemList.getLength() > 0) {
                for (int itemCnt = 0; itemCnt < itemList.getLength(); itemCnt++) {
                    final Element itemElement = (Element)itemList.item(itemCnt);
                    final Item item = new RequestParam().new Item();
                    item.setSql(itemElement.getAttribute(SQL));
                    item.setSource(itemElement.getAttribute(SOURCE));
                    item.setIndex(itemElement.getAttribute(INDEX));
                    item.setList(toBoolean(itemElement.getAttribute(LIST)));
                    item.setTargetId(itemElement.getAttribute(TARGET_ID));
                    item.setSelectedKey(itemElement.getAttribute(SELECTED_KEY));
                    if (paramObj instanceof RequestParam) {
                        setExcel((RequestParam)paramObj, item, itemElement);
                    }
                    setConditions(item, itemElement);
                    setParams(item, itemElement);
                    setResults(item, itemElement);
                    setChecks(item, itemElement);
                    setExternals(item, itemElement);
                    items.addItem(item);
                }
            }
            paramObj.setItems(items);
        }
    }
    
    /**
     * 邮件配置设定
     * @param element 节点对象
     * @param paramObj 节点对象容器
     */
    private static void setMails(Element element, RequestParam paramObj) {
        final Element mailsElement = getElement(element, MAILS);
        if (mailsElement != null) {
            final Mails mails = new RequestParam().new Mails();
            mails.setId(mailsElement.getAttribute(ID));
            final NodeList mailList = mailsElement.getElementsByTagName(MAIL);
            if (mailList != null && mailList.getLength() > 0) {
                for (int mailCnt = 0; mailCnt < mailList.getLength(); mailCnt++) {
                    final Element mailElement = (Element)mailList.item(mailCnt);
                    final Mail mail = new RequestParam().new Mail();
                    mail.setIndex(mailElement.getAttribute(INDEX));
                    mail.setUrl(mailElement.getAttribute(URL));
                    mail.setMailServerHost(mailElement.getAttribute(MAIL_SERVER_HOST));
                    mail.setMailServerPort(mailElement.getAttribute(MAIL_SERVER_PORT));
                    mail.setUserName(mailElement.getAttribute(USER_NAME));
                    mail.setPassword(mailElement.getAttribute(PASSWORD));
                    mail.setValidate(toBoolean(mailElement.getAttribute(VALIDATE)));
                    mail.setFromAddress(mailElement.getAttribute(FROM_ADDRESS));
                    mail.setToAddress(mailElement.getAttribute(TO_ADDRESS));
                    mail.setSubject(mailElement.getAttribute(SUBJECT));
                    mail.setContent(mailElement.getAttribute(CONTENT));
                    mails.addMail(mail);
                }
            }
            paramObj.setMails(mails);
        }
    }
    
    /**
     * 样式属性设定
     * @param element 样式对象
     * @param style 样式属性容器
     */
    private static void setStylePropertys(Element element, Style style) {
        final Element displaysElement = getElement(element, DISPLAYS);
        if (displaysElement != null) {
            final NodeList displayList = displaysElement.getElementsByTagName(DISPLAY);
            if (displayList != null && displayList.getLength() > 0) {
                for (int displayCnt = 0; displayCnt < displayList.getLength(); displayCnt++) {
                    final Element displayElement = (Element)displayList.item(displayCnt);
                    final Display display = new RequestParam().new Display();
                    setConditions(display, displayElement);
                    display.setValue(displayElement.getAttribute(VALUE));
                    style.addDisplay(display);
                }
            }
        }
        
        final Element textColorsElement = getElement(element, TEXT_COLORS);
        if (textColorsElement != null) {
            final NodeList textColorList = textColorsElement.getElementsByTagName(TEXT_COLOR);
            if (textColorList != null && textColorList.getLength() > 0) {
                for (int textColorCnt = 0; textColorCnt < textColorList.getLength(); textColorCnt++) {
                    final Element textColorElement = (Element)textColorList.item(textColorCnt);
                    final TextColor textColor = new RequestParam().new TextColor();
                    setConditions(textColor, textColorElement);
                    textColor.setValue(textColorElement.getAttribute(VALUE));
                    style.addTextColor(textColor);
                }
            }
        }
        
        final Element backgroundColorsElement = getElement(element, BACKGROUND_COLORS);
        if (backgroundColorsElement != null) {
            final NodeList backgroundColorList = backgroundColorsElement.getElementsByTagName(BACKGROUND_COLOR);
            if (backgroundColorList != null && backgroundColorList.getLength() > 0) {
                for (int backgroundColorCnt = 0; backgroundColorCnt < backgroundColorList
                        .getLength(); backgroundColorCnt++) {
                    final Element backgroundColorElement = (Element)backgroundColorList.item(backgroundColorCnt);
                    final BackgroundColor backgroundColor = new RequestParam().new BackgroundColor();
                    setConditions(backgroundColor, backgroundColorElement);
                    backgroundColor.setValue(backgroundColorElement.getAttribute(VALUE));
                    style.addBackgroundColor(backgroundColor);
                }
            }
        }
    }
    
    /**
     * 单项目对象设定
     * @param element 节点对象
     * @param formatFace 节点对象容器
     */
    private static void setFormats(IFormat formatFace, Element element) {
        final Element formatsElement = getElement(element, FORMATS);
        if (formatsElement != null) {
            final NodeList formatList = formatsElement.getElementsByTagName(FORMAT);
            if (formatList != null && formatList.getLength() > 0) {
                for (int formatCnt = 0; formatCnt < formatList.getLength(); formatCnt++) {
                    final Element formatElement = (Element)formatList.item(formatCnt);
                    final Format format = new RequestParam().new Format();
                    format.setId(formatElement.getAttribute(ID));
                    format.setMethod(formatElement.getAttribute(METHOD));
                    format.setParams(formatElement.getAttribute(PARAMS));
                    formatFace.addFormat(format);
                }
            }
        }
    }
    
    /**
     * 条件对象设定
     * @param element 节点对象
     * @param conditionFace 节点对象容器
     */
    private static void setConditions(ICondition conditionFace, Element element) {
        final Element conditionsElement = getElement(element, CONDITIONS);
        if (conditionsElement != null) {
            conditionFace.setConditionId(conditionsElement.getAttribute(ID));
            final NodeList conditionList = conditionsElement.getElementsByTagName(CONDITION);
            if (conditionList != null && conditionList.getLength() > 0) {
                for (int conditionCnt = 0; conditionCnt < conditionList.getLength(); conditionCnt++) {
                    final Element conditionElement = (Element)conditionList.item(conditionCnt);
                    final Condition condition = new RequestParam().new Condition();
                    setCondition(conditionElement, condition);
                    conditionFace.addCondition(condition);
                }
            }
        }
    }

    /**
     * 条件对象设定
     * @param conditionElement 条件节点对象
     * @param condition 节点对象容器
     */
    private static void setCondition(final Element conditionElement, final Condition condition) {
        condition.setOperator(conditionElement.getAttribute(OPERATOR));
        condition.setSql(conditionElement.getAttribute(SQL));
        condition.setSource(conditionElement.getAttribute(SOURCE));
        condition.setIndex(conditionElement.getAttribute(INDEX));
        setParams(condition, conditionElement);
        if (CommonUtil.isNotEmpty(conditionElement.getAttribute(PROPERTY))) {
            final Compare compare = new RequestParam().new Compare();
            compare.setValue(conditionElement.getAttribute(PROPERTY));
            condition.setCompareSource(compare);
        } else {
            setCompareSource(condition, conditionElement);
        }
        if (CommonUtil.isNotEmpty(conditionElement.getAttribute(VALUE))) {
            final Compare compare = new RequestParam().new Compare();
            compare.setValue(conditionElement.getAttribute(VALUE));
            condition.setCompareTarget(compare);
        } else {
            setCompareTarget(condition, conditionElement);
        }
    }
    
    /**
     * 参数对象设定
     * @param condition 条件对象容器
     * @param element 节点对象
     */
    private static void setCompareSource(Condition condition, Element element) {
        final Element compareSourceElement = getElement(element, COMPARE_SOURCE);
        if (compareSourceElement != null) {
            final Compare compare = new RequestParam().new Compare();
            compare.setValue(compareSourceElement.getAttribute(VALUE));
            setFormats(compare, compareSourceElement);
            condition.setCompareSource(compare);
        }
    }
    
    /**
     * 参数对象设定
     * @param condition 条件对象容器
     * @param element 节点对象
     */
    private static void setCompareTarget(Condition condition, Element element) {
        final Element compareTargetElement = getElement(element, COMPARE_TARGET);
        if (compareTargetElement != null) {
            final Compare compare = new RequestParam().new Compare();
            compare.setValue(compareTargetElement.getAttribute(VALUE));
            setFormats(compare, compareTargetElement);
            condition.setCompareTarget(compare);
        }
    }
    
    /**
     * 单项目对象设定
     * @param element 节点对象
     * @param whetherFace 节点对象容器
     */
    private static void setWhethers(IWhether whetherFace, Element element) {
        final Element conditionsElement = getElement(element, WHETHER);
        if (conditionsElement != null) {
            final NodeList conditionList = conditionsElement.getElementsByTagName(CONDITION);
            if (conditionList != null && conditionList.getLength() > 0) {
                for (int conditionCnt = 0; conditionCnt < conditionList.getLength(); conditionCnt++) {
                    final Element conditionElement = (Element)conditionList.item(conditionCnt);
                    final Condition condition = new RequestParam().new Condition();
                    setCondition(conditionElement, condition);
                    whetherFace.addWhethers(condition);
                }
            }
        }
    }
    
    /**
     * 单项目对象设定
     * @param element 节点对象
     * @param paramObj 节点对象容器
     */
    private static void setStyles(Element element, RequestParam paramObj) {
        final Element stylesElement = getElement(element, STYLES);
        if (stylesElement != null) {
            final NodeList styleList = stylesElement.getElementsByTagName(STYLE);
            if (styleList != null && styleList.getLength() > 0) {
                for (int styleCnt = 0; styleCnt < styleList.getLength(); styleCnt++) {
                    final Element styleElement = (Element)styleList.item(styleCnt);
                    final Style style = new RequestParam().new Style();
                    style.setProperty(styleElement.getAttribute(PROPERTY));
                    setStylePropertys(styleElement, style);
                    setConditions(style, styleElement);
                    paramObj.addStyle(style);
                }
            }
        }
    }
    
    /**
     * 参数对象设定
     * @param addParameter 参数对象容器
     * @param element 节点对象
     */
    private static void setParams(IParam addParameter, Element element) {
        final Element paramsElement = getElement(element, PARAMS);
        if (paramsElement != null) {
            final NodeList paramList = paramsElement.getElementsByTagName(PARAM);
            if (paramList != null && paramList.getLength() > 0) {
                for (int paramCnt = 0; paramCnt < paramList.getLength(); paramCnt++) {
                    final Element paramElement = (Element)paramList.item(paramCnt);
                    final Param param = new RequestParam().new Param();
                    param.setKey(paramElement.getAttribute(KEY));
                    param.setValue(paramElement.getAttribute(VALUE));
                    param.setList(toBoolean(paramElement.getAttribute(LIST)));
                    param.setSessionAllowNull(toBoolean(paramElement.getAttribute(SESSION_ALLOW_NULL)));
                    param.setKeep(toBoolean(paramElement.getAttribute(KEEP)));
                    setFormats(param, paramElement);
                    setConditions(param, paramElement);
                    addParameter.addParam(param);
                }
            }
        }
    }
    
    /**
     * 参数对象设定
     * @param paramObj 画面请求对象容器
     * @param element 节点对象
     * @param disp 画面ID
     * @throws RichClientWebException RichClientWebException
     */
    private static void setComplex(Element element, RequestParam paramObj, String disp) 
        throws RichClientWebException {
        final Element complexElement = getElement(element, COMPLEX);
        if (complexElement != null) {
            final Complex complex = new RequestParam().new Complex();
            setItems(complexElement, complex);
            setLists(complexElement, complex, disp);
            setExcel(paramObj, complex, complexElement);
            paramObj.setComplex(complex);
        }
    }
    
    /**
     * 切换数据源对象设置
     * @param iDataSource 画面请求对象容器
     * @param element 节点对象
     * @throws RichClientWebException RichClientWebException
     */
    private static void setDataSource(Element element, IDataSource iDataSource) 
        throws RichClientWebException {
        final Element dataSourceElement = getElement(element, DATA_SOURCE);
        if (dataSourceElement != null) {
            final DataSource dataSource = new RequestParam().new DataSource();
            dataSource.setMethod(dataSourceElement.getAttribute(METHOD));
            dataSource.setTarget(dataSourceElement.getAttribute(TARGET));
            iDataSource.setDataSource(dataSource);
        }
    }
    
    /**
     * 切换数据源对象设置
     * @param paramObj 画面请求对象容器
     * @param element 节点对象
     * @throws RichClientWebException RichClientWebException
     */
    private static void setExcelStyles(Element element, RequestParam paramObj) 
        throws RichClientWebException {
        final Element excelStylesElement = getElement(element, EXCEL_STYLES);
        if (excelStylesElement != null) {
            final NodeList cellStyleList = excelStylesElement.getElementsByTagName(CELL_STYLE);
            if (cellStyleList != null && cellStyleList.getLength() > 0) {
                for (int cellStyleCnt = 0; cellStyleCnt < cellStyleList.getLength(); cellStyleCnt++) {
                    final Element cellStyleElement = (Element)cellStyleList.item(cellStyleCnt);
                    paramObj.addCellStyle(getCellStyle(cellStyleElement));
                }
            }
        }
    }
    
    /**
     * 参数对象设定
     * @param paramObj 画面请求对象容器
     * @param setExcel excel对象容器
     * @param element 节点对象
     */
    private static void setExcel(RequestParam paramObj, IExcel setExcel, Element element) {
        final Element excelElement = getElement(element, EXCEL);
        if (excelElement != null) {
            final Excel excel = new RequestParam().new Excel();
            excel.setInName(excelElement.getAttribute(IN_NAME));
            excel.setInPath(excelElement.getAttribute(IN_PATH));
            excel.setOutName(excelElement.getAttribute(OUT_NAME));
            excel.setOutPath(excelElement.getAttribute(OUT_PATH));
            excel.setInAbsolute(toBoolean(excelElement.getAttribute(IN_ABSOLUTE)));
            excel.setOutAbsolute(toBoolean(excelElement.getAttribute(OUT_ABSOLUTE)));
            excel.setType(excelElement.getAttribute(TYPE));
            excel.setFileType(excelElement.getAttribute(FILE_TYPE));
            if (ConstantsUtil.Excel.TYPE_STREAM.equals(excelElement.getAttribute(TYPE))) {
                paramObj.setForward(ControlRequestMap.FORWARD_NO);
            }
            setSheets(excel, excelElement);
            setExcel.setExcel(excel);
        }
    }
    
    /**
     * 单项目对象设定
     * @param element 节点对象
     * @param excel excel对象容器
     */
    private static void setSheets(Excel excel, Element element) {
        final Element sheetsElement = getElement(element, SHEETS);
        if (sheetsElement != null) {
            final NodeList sheetList = sheetsElement.getElementsByTagName(SHEET);
            if (sheetList != null && sheetList.getLength() > 0) {
                for (int sheetCnt = 0; sheetCnt < sheetList.getLength(); sheetCnt++) {
                    final Element sheetElement = (Element)sheetList.item(sheetCnt);
                    final Sheet sheet = new RequestParam().new Sheet();
                    sheet.setName(sheetElement.getAttribute(NAME));
                    setRows(sheet, sheetElement);
                    excel.addSheets(sheet);
                }
            }
        }
    }
    
    /**
     * excel行信息设定
     * @param element 节点对象
     * @param sheet sheet对象容器
     */
    private static void setRows(Sheet sheet, Element element) {
        final Element rowsElement = getElement(element, ROWS);
        if (rowsElement != null) {
            final boolean alternate = toBoolean(rowsElement.getAttribute(ALTERNATE));
            final String oddColor = rowsElement.getAttribute(ODD_COLOR);
            final String evenColor = rowsElement.getAttribute(EVEN_COLOR);
            final NodeList rowList = rowsElement.getElementsByTagName(ROW);
            if (rowList != null && rowList.getLength() > 0) {
                for (int cellCnt = 0; cellCnt < rowList.getLength(); cellCnt++) {
                    final Element rowElement = (Element)rowList.item(cellCnt);
                    final Row row = new RequestParam().new Row();
                    row.setAlternate(alternate);
                    row.setOddColor(oddColor);
                    row.setEvenColor(evenColor);
                    row.setSite(toInt(rowElement.getAttribute(SITE)));
                    row.setHeight(toFloat(rowElement.getAttribute(HEIGHT)));
                    row.setTargetId(rowElement.getAttribute(TARGET_ID));
                    row.setClassName(rowElement.getAttribute(CLASS));
                    row.setStart(toInt(rowElement.getAttribute(START)));
                    row.setEnd(toInt(rowElement.getAttribute(END)));
                    setCells(row, rowElement);
                    sheet.addRows(row);
                }
            }
        }
    }
    
    /**
     * 单项目对象设定
     * @param element 节点对象
     * @param row row对象容器
     */
    private static void setCells(Row row, Element element) {
        final Element cellsElement = getElement(element, CELLS);
        if (cellsElement != null) {
            final NodeList cellList = cellsElement.getElementsByTagName(CELL);
            if (cellList != null && cellList.getLength() > 0) {
                for (int cellCnt = 0; cellCnt < cellList.getLength(); cellCnt++) {
                    final Element cellElement = (Element)cellList.item(cellCnt);
                    final Cell cell = new RequestParam().new Cell();
                    cell.setSite(toInt(cellElement.getAttribute(SITE)));
                    cell.setWidth(toInt(cellElement.getAttribute(WIDTH)));
                    cell.setValue(cellElement.getAttribute(VALUE));
                    cell.setType(cellElement.getAttribute(TYPE));
                    cell.setTargetId(cellElement.getAttribute(TARGET_ID));
                    cell.setClassName(cellElement.getAttribute(CLASS));
                    setConditions(cell, cellElement);
                    setCellStyle(cell, cellElement);
                    row.addCell(cell);
                }
            }
        }
    }
    
    /**
     * 参数对象设定
     * @param cell cell对象容器
     * @param element 节点对象
     */
    private static void setCellStyle(Cell cell, Element element) {
        final Element cellStyleElement = getElement(element, CELL_STYLE);
        cell.setStyle(getCellStyle(cellStyleElement));
    }

    private static CellStyle getCellStyle(final Element cellStyleElement) {
        if (cellStyleElement != null) {
            final CellStyle cellStyle = new RequestParam().new CellStyle();
            setCellBorders(cellStyle, cellStyleElement);
            setCellFont(cellStyle, cellStyleElement);
            setCellDataFormat(cellStyle, cellStyleElement);
            setCellLayout(cellStyle, cellStyleElement);
            setCellGround(cellStyle, cellStyleElement);
            cellStyle.setId(cellStyleElement.getAttribute(ID));
            return cellStyle; 
        }
        return null;
    }
    
    /**
     * 参数对象设定
     * @param cellStyle cellStyle对象容器
     * @param element 节点对象
     */
    private static void setCellBorders(CellStyle cellStyle, Element element) {
        final Element cellBordersElement = getElement(element, CELL_BORDERS);
        if (cellBordersElement != null) {
            final CellBorder cellBorder = new RequestParam().new CellBorder();
            setWholeBorder(cellBorder, cellBordersElement);
            setLeftBorder(cellBorder, cellBordersElement);
            setRightBorder(cellBorder, cellBordersElement);
            setTopBorder(cellBorder, cellBordersElement);
            setBottomBorder(cellBorder, cellBordersElement);
            cellStyle.setBorder(cellBorder);
        }
    }
    
    /**
     * 参数对象设定
     * @param cellStyle cellStyle对象容器
     * @param element 节点对象
     */
    private static void setCellDataFormat(CellStyle cellStyle, Element element) {
        final Element cellDataFormatElement = getElement(element, CELL_DATA_FORMAT);
        if (cellDataFormatElement != null) {
            final CellDataFormat cellDataFormat = new RequestParam().new CellDataFormat();
            cellDataFormat.setFormat(cellDataFormatElement.getAttribute(FORMAT));
            cellDataFormat.setSourceFormat(cellDataFormatElement.getAttribute(SOURCE_FORMAT));
            cellStyle.setFormat(cellDataFormat);
        }
    }
    
    /**
     * 参数对象设定
     * @param cellStyle cellStyle对象容器
     * @param element 节点对象
     */
    private static void setCellLayout(CellStyle cellStyle, Element element) {
        final Element cellLayoutElement = getElement(element, CELL_LAYOUT);
        if (cellLayoutElement != null) {
            final CellLayout cellLayout = new RequestParam().new CellLayout();
            cellLayout.setAlign(cellLayoutElement.getAttribute(ALIGN));
            cellLayout.setVertical(cellLayoutElement.getAttribute(VERTICAL));
            cellStyle.setLayout(cellLayout);
        }
    }
    
    /**
     * 参数对象设定
     * @param cellStyle cellStyle对象容器
     * @param element 节点对象
     */
    private static void setCellGround(CellStyle cellStyle, Element element) {
        final Element cellGroundElement = getElement(element, CELL_GROUND);
        if (cellGroundElement != null) {
            final CellGround cellGround = new RequestParam().new CellGround();
            cellGround.setForeColor(cellGroundElement.getAttribute(FORE_COLOR));
            cellGround.setBackColor(cellGroundElement.getAttribute(BACK_COLOR));
            cellGround.setFill(cellGroundElement.getAttribute(FILL));
            cellStyle.setGround(cellGround);
        }
    }
    
    /**
     * 参数对象设定
     * @param cellStyle cellStyle对象容器
     * @param element 节点对象
     */
    private static void setCellFont(CellStyle cellStyle, Element element) {
        final Element cellFontElement = getElement(element, CELL_FONT);
        if (cellFontElement != null) {
            final CellFont cellFont = new RequestParam().new CellFont();
            cellFont.setName(cellFontElement.getAttribute(NAME));
            cellFont.setBold(cellFontElement.getAttribute(BOLD));
            cellFont.setColor(cellFontElement.getAttribute(COLOR));
            cellFont.setItalic(toBoolean(cellFontElement.getAttribute(ITALIC)));
            cellFont.setStrikeout(toBoolean(cellFontElement.getAttribute(STRIKEOUT)));
            cellFont.setFontHeight(toInt(cellFontElement.getAttribute(FONT_HEIGHT)));
            cellFont.setTypeOffset(cellFontElement.getAttribute(TYPE_OFFSET));
            cellFont.setUnderline(cellFontElement.getAttribute(UNDER_LINE));
            cellStyle.setFont(cellFont);
        }
    }
    
    /**
     * 参数对象设定
     * @param element 节点对象
     * @return cell border
     */
    private static Map<String, String> setCellBorder(Element element) {
        final Element cellBorderElement = getElement(element, CELL_BORDER);
        Map<String, String> map = null;
        if (cellBorderElement != null) {
            map = new HashMap<String, String>();
            map.put("style", cellBorderElement.getAttribute(STYLE));
            map.put("color", cellBorderElement.getAttribute(COLOR));
        }
        return map;
    }
    
    /**
     * 参数对象设定
     * @param cellBorder cellBorder对象容器
     * @param element 节点对象
     */
    private static void setWholeBorder(CellBorder cellBorder, Element element) {
        final Element wholeBorderElement = getElement(element, WHOLE_BORDER);
        if (wholeBorderElement != null) {
            final Map<String, String> map = setCellBorder(wholeBorderElement);
            if (map != null) {
                cellBorder.setWholeColor(CommonUtil.toString(map.get("color")));
                cellBorder.setWholeStyle(CommonUtil.toString(map.get("style")));
            }
        }
    }
    
    /**
     * 参数对象设定
     * @param cellBorder cellBorder对象容器
     * @param element 节点对象
     */
    private static void setLeftBorder(CellBorder cellBorder, Element element) {
        final Element leftBorderElement = getElement(element, LEFT_BORDER);
        if (leftBorderElement != null) {
            final Map<String, String> map = setCellBorder(leftBorderElement);
            if (map != null) {
                cellBorder.setLeftColor(CommonUtil.toString(map.get("color")));
                cellBorder.setLeftStyle(CommonUtil.toString(map.get("style")));
            }
        }
    }
    
    /**
     * 参数对象设定
     * @param cellBorder cellBorder对象容器
     * @param element 节点对象
     */
    private static void setRightBorder(CellBorder cellBorder, Element element) {
        final Element rightBorderElement = getElement(element, RIGHT_BORDER);
        if (rightBorderElement != null) {
            final Map<String, String> map = setCellBorder(rightBorderElement);
            if (map != null) {
                cellBorder.setRightColor(CommonUtil.toString(map.get("color")));
                cellBorder.setRightStyle(CommonUtil.toString(map.get("style")));
            }
        }
    }
    
    /**
     * 参数对象设定
     * @param cellBorder cellBorder对象容器
     * @param element 节点对象
     */
    private static void setTopBorder(CellBorder cellBorder, Element element) {
        final Element topBorderElement = getElement(element, TOP_BORDER);
        if (topBorderElement != null) {
            final Map<String, String> map = setCellBorder(topBorderElement);
            if (map != null) {
                cellBorder.setTopColor(CommonUtil.toString(map.get("color")));
                cellBorder.setTopStyle(CommonUtil.toString(map.get("style")));
            }
        }
    }
    
    /**
     * 参数对象设定
     * @param cellBorder cellBorder对象容器
     * @param element 节点对象
     */
    private static void setBottomBorder(CellBorder cellBorder, Element element) {
        final Element bottomBorderElement = getElement(element, BOTTOM_BORDER);
        if (bottomBorderElement != null) {
            final Map<String, String> map = setCellBorder(bottomBorderElement);
            if (map != null) {
                cellBorder.setBottomColor(CommonUtil.toString(map.get("color")));
                cellBorder.setBottomStyle(CommonUtil.toString(map.get("style")));
            }
        }
    }
    
    /**
     * 外部对象设定
     * @param addExternal 外部对象容器
     * @param element 节点对象
     */
    private static void setExternals(IExternal addExternal, Element element) {
        final Element externalsElement = getElement(element, EXTERNALS);
        if (externalsElement != null) {
            final NodeList externalList = externalsElement.getElementsByTagName(EXTERNAL);
            if (externalList != null && externalList.getLength() > 0) {
                for (int externalCnt = 0; externalCnt < externalList.getLength(); externalCnt++) {
                    final Element externalElement = (Element)externalList.item(externalCnt);
                    final External external = new RequestParam().new External();
                    external.setMethod(externalElement.getAttribute(METHOD));
                    external.setIndex(externalElement.getAttribute(ID));
                    setParams(external, externalElement);
                    setResults(external, externalElement);
                    addExternal.addExternal(external);
                }
            }
        }
    }
    
    /**
     * 参数对象设定
     * @param addResult 参数对象容器
     * @param element 节点对象
     */
    private static void setResults(IActionStep addResult, Element element) {
        final Element resultsElement = getElement(element, RESULTS);
        if (resultsElement != null) {
            addResult.setResultConstant(toBoolean(resultsElement.getAttribute(CONSTANT)));
            addResult.setResultAppoint(toBoolean(resultsElement.getAttribute(APPOINT)));
            final NodeList retultList = resultsElement.getElementsByTagName(RESULT);
            if (retultList != null && retultList.getLength() > 0) {
                for (int resultCnt = 0; resultCnt < retultList.getLength(); resultCnt++) {
                    final Element resultElement = (Element)retultList.item(resultCnt);
                    final Result result = new RequestParam().new Result();
                    result.setTargetId(resultElement.getAttribute(TARGET_ID));
                    result.setOperator(resultElement.getAttribute(OPERATOR));
                    result.setValue(resultElement.getAttribute(VALUE));
                    result.setList(toBoolean(resultElement.getAttribute(LIST)));
                    setConditions(result, resultElement);
                    setFormats(result, resultElement);
                    addResult.addResult(result);
                }
            }
        }
    }
    
    /** 
    * @Description: 验证对象设定
    * @author king
    * @since Sep 15, 2012 7:44:58 PM 
    * 
    * @param checker 验证对象使用对象
    * @param element 节点对象
    */
    private static void setChecks(IChecker checker, Element element) {
        final Element checksElement = getElement(element, CHECKS);
        if (checksElement != null) {
            final String checksSuccess = getHeaderType(checksElement.getAttribute(SUCCESS));
            final String checksError = getHeaderType(checksElement.getAttribute(ERROR));
            final NodeList checkList = checksElement.getElementsByTagName(CHECKER);
            if (checkList != null && checkList.getLength() > 0) {
                for (int checkCnt = 0; checkCnt < checkList.getLength(); checkCnt++) {
                    final Element checkElement = (Element)checkList.item(checkCnt);
                    final Checker check = new RequestParam().new Checker();
                    
                    String checkSuccess = checkElement.getAttribute(SUCCESS);
                    String checkError = checkElement.getAttribute(ERROR);
                    if (CommonUtil.isEmpty(checkSuccess)) {
                        checkSuccess = checksSuccess;
                    } else {
                        checkSuccess = getHeaderType(checkSuccess);
                    }
                    if (CommonUtil.isEmpty(checkError)) {
                        checkError = checksError;
                    } else {
                        checkError = getHeaderType(checkError);
                    }
                    check.setSql(checkElement.getAttribute(SQL));
                    check.setSource(checkElement.getAttribute(SOURCE));
                    check.setIndex(checkElement.getAttribute(INDEX));
                    if (CommonUtil.isNotEmpty(checkElement.getAttribute(RESCODE))) {
                        check.setResCode(String.valueOf(getHeaderType(checkElement.getAttribute(RESCODE))));
                    }
                    setParams(check, checkElement);
                    setTabs(check, checkElement);
                    setConditions(check, checkElement);
                    setWhethers(check, checkElement);
                    setExternals(check, checkElement);
                    checker.addCheck(check);
                }
            }
        }
    }
    
    /**
     * 参数对象设定
     * @param addTab 参数对象容器
     * @param element 节点对象
     */
    private static void setTabs(Checker addTab, Element element) {
        final Element tabsElement = getElement(element, TABS);
        if (tabsElement != null) {
            final NodeList tabList = tabsElement.getElementsByTagName(TAB);
            if (tabList != null && tabList.getLength() > 0) {
                for (int tabCnt = 0; tabCnt < tabList.getLength(); tabCnt++) {
                    final Element tabElement = (Element)tabList.item(tabCnt);
                    final Taber tab = new RequestParam().new Taber();
                    tab.setTargetId(tabElement.getAttribute(TARGET_ID));
                    tab.setMessage(tabElement.getAttribute(MESSAGE));
                    addTab.addTab(tab);
                }
            }
        }
    }
    
    /**
     * 属性数值型转换
     * @param attr 属性值
     * @return 属性数值型
     */
    private static int toInt(String attr) {
        int result = -1;
        if (CommonUtil.isNotEmpty(attr)) {
            result = Integer.parseInt(attr);
        }
        return result;
    }
    
    /**
     * 属性浮点型转换
     * @param attr 属性值
     * @return 属性数值型
     */
    private static float toFloat(String attr) {
        float result = -1;
        if (CommonUtil.isNotEmpty(attr)) {
            result = Float.parseFloat(attr);
        }
        return result;
    }
    
    /**
     * 属性BOOLEAN型转换
     * @param attr 属性值
     * @return 属性BOOLEAN型
     */
    private static boolean toBoolean(String attr) {
        boolean result = false;
        if (CommonUtil.isNotEmpty(attr)) {
            result = Boolean.parseBoolean(attr);
        }
        return result;
    }

    /**
     * 获取头信息
     * @param type 头信息code
     * @return 头信息
     */
    private static String getHeaderType(String type) {
        String result = EngineExceptionEnum.UR_COM_BLANK.getCode();
        if (CommonUtil.isNotEmpty(type)) {
            if ("PAGE_CHANGED".equals(type)) {
                result = EngineExceptionEnum.UR_COM_PAGE_CHANGED.getCode();
            } else if ("SYSTEM_ERROR".equals(type)) {
                result = EngineExceptionEnum.UR_COM_SYSTEM_ERROR.getCode();
            } else if ("BLANK".equals(type)) {
                result = EngineExceptionEnum.UR_COM_BLANK.getCode();
            } else if ("REGISTER_SUCCESS".equals(type)) {
                result = EngineExceptionEnum.UR_COM_REGISTER_SUCCESS.getCode();
            } else if ("INPUT_ERROR".equals(type)) {
                result = EngineExceptionEnum.UR_COM_INPUT_ERROR.getCode();
            } else if ("REGISTER_ERROR".equals(type)) {
                result = EngineExceptionEnum.UR_COM_REGISTER_ERROR.getCode();
            } else if ("READ_ERROR".equals(type)) {
                result = EngineExceptionEnum.UR_COM_READ_ERROR.getCode();
            } else if ("NO_DATA".equals(type)) {
                result = EngineExceptionEnum.UR_COM_NO_DATA.getCode();
            } else if ("DELETE_SUCCESS".equals(type)) {
                result = EngineExceptionEnum.UR_COM_DELETE_SUCCESS.getCode();
            } else if ("INIT_INFO".equals(type)) {
                result = EngineExceptionEnum.UR_COM_INIT_INFO.getCode();
            } else if ("UPLOAD_SUCCESS".equals(type)) {
                result = EngineExceptionEnum.UR_COM_UPLOAD_SUCCESS.getCode();
            } else if ("LOGIN_ERROR".equals(type)) {
                result = EngineExceptionEnum.UR_COM_LOGIN_ERROR.getCode();
            } else if ("SESSION_TIMEOUT".equals(type)) {
                result = EngineExceptionEnum.UR_COM_SESSION_TIMEOUT.getCode();
            } else if ("DB_ERROR".equals(type)) {
                result = EngineExceptionEnum.UR_COM_DB_ERROR.getCode();
            } else if ("DELETE_ERROR".equals(type)) {
                result = EngineExceptionEnum.UR_COM_DELETE_ERROR.getCode();
            } else if ("OPENAME_OVERLAP".equals(type)) {
                result = EngineExceptionEnum.UR_COM_OPENAME_OVERLAP.getCode();
            } else if ("LOGIN_ERR_SESSION_MAX".equals(type)) {
                result = EngineExceptionEnum.UR_COM_LOGIN_ERR_SESSION_MAX.getCode();
            } else {
                result = type;
            }
        }
        return result;
    }
}
