package com.richClientFrame.info;

import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.param.RequestParam;
import com.richClientFrame.data.param.RequestParam.CellStyle;
import com.richClientFrame.data.param.RequestParam.Cmb;
import com.richClientFrame.data.param.RequestParam.Complex;
import com.richClientFrame.data.param.RequestParam.DataSource;
import com.richClientFrame.data.param.RequestParam.Dimension;
import com.richClientFrame.data.param.RequestParam.Items;
import com.richClientFrame.data.param.RequestParam.List;
import com.richClientFrame.data.param.RequestParam.Lists;
import com.richClientFrame.data.param.RequestParam.Mails;
import com.richClientFrame.data.param.RequestParam.Style;
import com.richClientFrame.data.param.RequestParam.Updates;
import com.richClientFrame.data.param.RequestParam.Upload;
import com.richClientFrame.data.param.RequestParamFace.IList;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ControlRequestMap
 * 类描述 ： 系统请求的分发xml解析类.保存的内容与request.xml一致
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2010.07.28
 * @author king
 */
public final class ControlRequestMap {
    
    /**
     * 不自动登出.
     */
    public static final String LOGOUT_NO = "0";
    
    /**
     * 请求要求的种别.
     */
    public static final String REQUEST_KIND_SERVLET = "servlet";
    
    /**
     * 不需页面迁移.
     */
    public static final String FORWARD_NO = "0";
    
    /**
     * 不需要共通验证.
     */
    public static final String CHECKED_OFF = "0";
    
    /**
     * 需要全项目共通验证.
     */
    public static final String CHECKED_ON = "1";
    
    /**
     * 需要筛选项目共通验证.
     */
    public static final String CHECKED_SIFT = "2";
    
    /** request.xml文件路径 */
    private static final String PATH_REQMAP = "WEB-INF" + File.separator 
                        + ConstantsUtil.Str.EXTENSION_XML + File.separator + "request";

    /**
     * 本类对象
     */
    private static ControlRequestMap sMe;
    
    /**
     * 画面ID + 方法ID =
     *【0】：Handler 类名
     *【1】：方法名
     *【2】：JSP路径
     *【3】：logout（默认：自动logout; logout='0'：不自动logout）
     *【4】：request（默认：ajax）
     *【5】：forward（默认：迁移，forward=“0”：不迁移
     *【6】：JSP文件名）
     */
    private Map<String, Map<String, RequestParam>> reqestMap;
    private Map<String, RequestParam> resourceMap;
    
    /**
     * 路径
     */
    private static String sPath;
    
    /**
     * 构造函数
     * @throws RichClientWebException RichClientWebException
     */
    private ControlRequestMap() throws RichClientWebException {
        super();
        reqestMap = new HashMap<String, Map<String, RequestParam>>();
        resourceMap = new HashMap<String, RequestParam>();
        
        // 读取文件
        reqestMap = RequestMapReader.read(sPath, resourceMap);
        
    }
    
    /**
     * 本类对象生成.
     * @return RequestMap 本类对象
     * @throws RichClientWebException RichClientWebException
     */
    public static ControlRequestMap getInstance() throws RichClientWebException {
        
        synchronized (ControlRequestMap.class) {
            if (sMe == null) {
                sMe = new ControlRequestMap();
            }
        }
        return sMe;
    }
    
    /**
     * 是否是自动logout.
     * @param param request
     * @return 是否自动登出
     */
    public boolean isAutoLogout(Param param) {
        
        boolean bRet = true;
        
        final RequestParam value = reqestMap.get(param.dispCode).get(param.cmdCode);
        if (value != null && value.getLogout() != null && LOGOUT_NO.equals(value.getLogout())) {
            bRet = false;
        }
        
        return bRet;
    }
    
    /**
     * session没有时是否退出系统.
     * @param param request
     * @return 是否为服务
     */
    public boolean isServlet(Param param) {
        
//        boolean bRet = false;
        
        if (ConstantsUtil.Client.ANDROID_TYPE.equals(param.clientType) 
                || ConstantsUtil.Client.FLEX_TYPE.equals(param.clientType)) {
            return false;
        }
        
        final String requestType = param.getHttpServletRequest().getHeader("X-Requested-With");
        
        if ("XMLHttpRequest".equals(requestType)) {
            return false;
        }
        
        return true;
        
//        if (reqestMap.get(param.dispCode) == null) {
//            return true;
//        }
//        final RequestParam value = reqestMap.get(param.dispCode).get(param.cmdCode);
//        if (value != null && value.getRequest() != null && REQUEST_KIND_SERVLET.equals(value.getRequest())) {
//            bRet = true;
//        }
//        
//        if (ConstantsUtil.Method.SHOW_PAGE_CODE.equals(param.cmdCode) 
//                || ConstantsUtil.Method.SHOW_PRINT_CODE.equals(param.cmdCode)) {
//            bRet = true;
//        }
        
//        return bRet;
    }
    
    /**
     * 画面是否迁移.
     * 1.画面ID或者方法ID为空
     * 2.request.xml中配置了forward属性为0
     * 以上情况为画面不做迁移操作
     * @param param 客户端请求对象
     * @return 是否迁移
     */
    public boolean isForward(Param param) {
        
        if (CommonUtil.isEmpty(param.dispCode) || CommonUtil.isEmpty(param.cmdCode)) {
            return false;
        }
        
        final RequestParam value = reqestMap.get(param.dispCode).get(param.cmdCode);
        if (value != null && value.getForward() != null && FORWARD_NO.equals(value.getForward())) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 请求是否需要共通验证.
     * @param param request
     * @return 是否需要共通验证
     */
    public boolean isChecked(Param param) {
        
        boolean bRet = false;
        
        final RequestParam value = reqestMap.get(param.dispCode).get(param.cmdCode);
        if (value != null && CommonUtil.isNotEmpty(value.getCheck()) && !CHECKED_OFF.equals(value.getCheck())) {
            bRet = true;
        }
        
        return bRet;
    }
    
    /**
     * 请求是否为全项目共通验证.
     * @param param request
     * @return 是否为全项目共通验证
     */
    public boolean isAllChecked(Param param) {
        
        boolean bRet = false;
        
        final RequestParam value = reqestMap.get(param.dispCode).get(param.cmdCode);
        if (value != null && value.getCheck() != null && CHECKED_ON.equals(value.getCheck())) {
            bRet = true;
        }
        
        return bRet;
    }
    
    /**
     * 请求是否为筛选项目共通验证..
     * @param param request
     * @return 是否为筛选项目共通验证
     */
    public boolean isSiftChecked(Param param) {
        
        boolean bRet = false;
        
        final RequestParam value = reqestMap.get(param.dispCode).get(param.cmdCode);
        if (value != null && value.getCheck() != null && CHECKED_SIFT.equals(value.getCheck())) {
            bRet = true;
        }
        
        return bRet;
    }
    
    /**
     * 请求是否为筛选项目共通验证..
     * @param dispCode 画面ID
     * @param cmdCode 方法ID
     * @return 是否为筛选项目共通验证
     */
    public boolean isInputStream(String dispCode, String cmdCode) {
        
        final Map<String, RequestParam> map = reqestMap.get(dispCode);
        if (map == null) {
            return false;
        }
        final RequestParam value = map.get(cmdCode);
        if (value == null) {
            return false;
        }
        
        return value.isInputStream();
    }
    
    /**
     * 请求是否为筛选项目共通验证..
     * @param param request
     * @return 是否为筛选项目共通验证
     */
    public String getCheckIndex(Param param) {
        
        final RequestParam value = reqestMap.get(param.dispCode).get(param.cmdCode);
        
        String checkIndex = value.getCheckIndex();
        
        if (CommonUtil.isEmpty(checkIndex) && isSiftChecked(param)) {
            checkIndex = "FcheckF";
        }
        
        return checkIndex;
    }
    
    /**
     * KEY相应的handler取得.
     * @param param request
     * @return KEY相应的handler
     */
    public String getHanlder(Param param) {
        return getHanlder(param.dispCode, param.cmdCode);
    }
    
    /**
     * KEY相应的handler取得.
     * @param dispCode 画面ID
     * @param cmdCode 方法ID
     * @return KEY相应的handler
     */
    public String getHanlder(String dispCode, String cmdCode) {
        return reqestMap.get(dispCode).get(cmdCode).getHandlerClass();
    }
    
    /**
     * KEY相应的数据源取得.
     * @param param request
     * @return 数据源
     */
    public DataSource getDataSource(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).getDataSource();
    }
    
    /**
     * 是否为自定义服务方法.
     * @param param request
     * @return 是否为自定义服务方法
     */
    public boolean hasServlet(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).hasServlet();
    }
    
    /**
     * 自定义服务方法取得..
     * @param param request
     * @return 自定义服务方法
     */
    public String getServletMethod(Param param) {
        if (reqestMap.get(param.dispCode).get(param.cmdCode).hasServlet()) {
            return reqestMap.get(param.dispCode).get(param.cmdCode).getServlet().getMethod();
        }
        return null;
    }
    
    /**
     * KEY相应的方法取得..
     * @param param request
     * @return KEY相应的方法
     */
    public String getMethod(Param param) {
        final String method = getMethod(param.dispCode, param.cmdCode);
        return method;
    }
    
    /**
     * KEY相应的方法取得..
     * @param dispCode 画面ID
     * @param cmdCode 方法ID
     * @return KEY相应的方法
     */
    public String getMethod(String dispCode, String cmdCode) {
        String method = ConstantsUtil.Request.SINGLE_METHOD;
        if (reqestMap.get(dispCode).get(cmdCode).hasMethod()) {
            method = reqestMap.get(dispCode).get(cmdCode).getMethod().getName();
        } else if (reqestMap.get(dispCode).get(cmdCode).hasList()) {
            method = ConstantsUtil.Request.LIST_METHOD;
        } else if (reqestMap.get(dispCode).get(cmdCode).hasDimension()) {
            method = ConstantsUtil.Request.LIST_METHOD;
        } else if (reqestMap.get(dispCode).get(cmdCode).hasUpdates()) {
            method = ConstantsUtil.Request.UPDATE_METHOD;
        } else if (reqestMap.get(dispCode).get(cmdCode).hasUpload()) {
            method = ConstantsUtil.Request.UPLOAD_METHOD;
        } else if (reqestMap.get(dispCode).get(cmdCode).hasItem()) {
            method = ConstantsUtil.Request.SINGLE_METHOD;
        } else if (reqestMap.get(dispCode).get(cmdCode).hasComplex()) {
            method = ConstantsUtil.Request.COMPLEX_METHOD;
        } else if (reqestMap.get(dispCode).get(cmdCode).hasMail()) {
            method = ConstantsUtil.Request.SEND_MAIL_METHOD;
        }
        return method;
    }
    
    /**
     * 下拉框对应的资源取得.
     * @param id 下拉框对应的资源ID
     * @return 下拉框对应的资源
     */
    public Cmb getCmbResource(String id) {
        if (resourceMap.get("resource") == null) {
            return null;
        }
        return resourceMap.get("resource").getCmbMaps().get(id);
    }
    
    /**
     * 切换数据源对应的资源取得.
     * @return 切换数据源对应的资源
     */
    public DataSource getDataSourceInResource() {
        if (resourceMap.get("resource") == null) {
            return null;
        }
        return resourceMap.get("resource").getDataSource();
    }
    
    /** 
    * @Description: 从<resource>标签中取得共通的excel单元格样式信息.
    * @author king
    * @since Nov 8, 2012 8:47:52 PM 
    * 
    * @param key <resource>标签中配置的共通样式key
    * @return 共通的excel单元格样式信息
    */
    public CellStyle getCellStyleInResource(String key) {
        if (resourceMap.get("resource") == null) {
            return null;
        }
        return resourceMap.get("resource").getCellStylesMap().get(key);
    }
    
    /** 
     * @Description: 从<resource>标签中取得共通的excel列表是否隔行变色显示.
     * @author king
     * @since Nov 8, 2012 8:47:52 PM 
     * 
     * @return 是否隔行变色显示
     */
    public boolean getRowAlternateInResource() {
        if (resourceMap.get("resource") == null) {
            return false;
        }
        if (resourceMap.get("resource").getExcel() == null) {
            return false;
        }
        if (resourceMap.get("resource").getExcel().getSheets() == null) {
            return false;
        }
        if (resourceMap.get("resource").getExcel().getSheets().size() <= 0) {
            return false;
        }
        if (resourceMap.get("resource").getExcel().getSheets().get(0).getRows() == null) {
            return false;
        }
        if (resourceMap.get("resource").getExcel().getSheets().get(0).getRows().size() <= 0) {
            return false;
        }
        return resourceMap.get("resource").getExcel().getSheets().get(0).getRows().get(0).isAlternate();
    }
    
    /** 
     * @Description: 从<resource>标签中取得共通的excel列表奇数行颜色.
     * @author king
     * @since Nov 8, 2012 8:47:52 PM 
     * 
     * @return 奇数行颜色
     */
    public String getRowOddColorInResource() {
        if (resourceMap.get("resource") == null) {
            return null;
        }
        if (resourceMap.get("resource").getExcel() == null) {
            return null;
        }
        if (resourceMap.get("resource").getExcel().getSheets() == null) {
            return null;
        }
        if (resourceMap.get("resource").getExcel().getSheets().size() <= 0) {
            return null;
        }
        if (resourceMap.get("resource").getExcel().getSheets().get(0).getRows() == null) {
            return null;
        }
        if (resourceMap.get("resource").getExcel().getSheets().get(0).getRows().size() <= 0) {
            return null;
        }
        return resourceMap.get("resource").getExcel().getSheets().get(0).getRows().get(0).getOddColor();
    }
    
    /** 
     * @Description: 从<resource>标签中取得共通的excel列表偶数行颜色.
     * @author king
     * @since Nov 8, 2012 8:47:52 PM 
     * 
     * @return 偶数行颜色
     */
    public String getRowEvenColorInResource() {
        if (resourceMap.get("resource") == null) {
            return null;
        }
        if (resourceMap.get("resource").getExcel() == null) {
            return null;
        }
        if (resourceMap.get("resource").getExcel().getSheets() == null) {
            return null;
        }
        if (resourceMap.get("resource").getExcel().getSheets().size() <= 0) {
            return null;
        }
        if (resourceMap.get("resource").getExcel().getSheets().get(0).getRows() == null) {
            return null;
        }
        if (resourceMap.get("resource").getExcel().getSheets().get(0).getRows().size() <= 0) {
            return null;
        }
        return resourceMap.get("resource").getExcel().getSheets().get(0).getRows().get(0).getEvenColor();
    }
    
    /**
     * 下拉框对应的资源取得.
     * @param param request
     * @param key style对应的控件ID
     * @return 下拉框对应的资源
     */
    public Style getStyle(Param param, String key) {
        if (reqestMap.get(param.dispCode).get(param.cmdCode).getStyles() != null 
                && reqestMap.get(param.dispCode).get(param.cmdCode).getStyles().size() > 0 
                && "*".equals(reqestMap.get(param.dispCode).get(param.cmdCode).getStyles().get(0).getProperty())) {
            return reqestMap.get(param.dispCode).get(param.cmdCode).getStyles().get(0);
        }
        return reqestMap.get(param.dispCode).get(param.cmdCode).getStylesMap().get(key);
    }
    
    /**
     * 判断是否有key相应的style.
     * @param param request
     * @param key style对应的控件ID
     * @return 下拉框对应的资源
     */
    public boolean hasStyle(Param param, String key) {
        if (reqestMap.get(param.dispCode).get(param.cmdCode).getStyles() != null 
                && reqestMap.get(param.dispCode).get(param.cmdCode).getStyles().size() > 0 
                && "*".equals(reqestMap.get(param.dispCode).get(param.cmdCode).getStyles().get(0).getProperty())) {
            return true;
        }
        return reqestMap.get(param.dispCode).get(param.cmdCode).getStylesMap().containsKey(key);
    }
    
    /**
     * 判断是否有dimension.
     * @param param request
     * @return 下拉框对应的资源
     */
    public boolean hasList(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).hasList();
    }
    
    /**
     * 取得需要在画面初始化加载的方法ID数组.
     * @param param request
     * @return 需要在画面初始化加载的方法ID数组
     */
    public java.util.List<String> getInitializeList(Param param) {
        final java.util.List<String> cmdList = new ArrayList<String>();
        final Map<String, RequestParam> requestParamMap = reqestMap.get(param.dispCode);
        for (String key : requestParamMap.keySet()) {
            final Param paramSub = new Param();
            paramSub.dispCode = param.dispCode;
            paramSub.cmdCode = key;
            if (hasList(paramSub) && getList(paramSub).isInitialize()) {
                cmdList.add(key);
            }
        }
        return cmdList;
    }
    
    /**
     * 判断是否有dimension.
     * @param param request
     * @return 下拉框对应的资源
     */
    public boolean hasDimension(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).hasDimension();
    }
    
    /**
     * KEY相应的Forward取得.
     * @param param request
     * @return KEY相应的Forward
     */
    public String getForward(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).getForward();
    }
    
    /**
     * KEY相应的画面取得.
     * @param param request
     * @return KEY相应的画面
     */
    public String getJsp(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).getJspName();
    }
    
    /**
     * 对应画面控件ID取得.
     * @param param request
     * @return 对应画面控件ID
     */
    public String getTargetId(Param param) {
        String targetId = ConstantsUtil.Str.EMPTY;
        if (reqestMap.get(param.dispCode).get(param.cmdCode).getList() != null) {
            targetId = reqestMap.get(param.dispCode).get(param.cmdCode).getList().getTargetId();
        } else if (reqestMap.get(param.dispCode).get(param.cmdCode).getDimension() != null) {
            targetId = reqestMap.get(param.dispCode).get(param.cmdCode).getDimension().getTargetId();
        }
        return targetId;
    }
    
    /**
     * 对应每页显示的数据数取得.
     * @param list list
     * @return 对应每页显示的数据数
     */
    public String getPageSize(IList list) {
        String pageSize = ConstantsUtil.Str.EMPTY;
        if (list != null) {
            pageSize = list.getPageSize();
        }
        return pageSize;
    }
    
    /**
     * 对应每页显示的数据数取得.
     * @param param request
     * @return 对应每页显示的数据数
     */
    public List getList(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).getList();
    }
    
    /**
     * 对应每页显示的数据数取得.
     * @param param request
     * @return 对应每页显示的数据数
     */
    public Dimension getDimension(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).getDimension();
    }
    
    /**
     * 下拉框数据取得.
     * @param param request
     * @return 下拉框数据
     */
    public java.util.List<Cmb> getCmbs(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).getCmbs();
    }
    
    /**
     * 增删改方法取得.
     * @param param request
     * @return 增删改方法
     */
    public Updates getUpdates(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).getUpdates();
    }
    
    /**
     * 复合对象取得.
     * @param param request
     * @return 复合对象
     */
    public Complex getComplex(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).getComplex();
    }
    
    /**
     * @Description: 单项目方法取得
     * @author king
     * @since Oct 9, 2013 3:50:53 PM 
     * @version V1.0
     * @param param request
     * @return 单项目方法
     */
    public Items getItems(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).getItems();
    }
    
    /**
     * @Description: 单项目方法取得
     * @author king
     * @since Oct 9, 2013 3:50:53 PM 
     * @version V1.0
     * @param param request
     * @return 单项目方法
     */
    public Mails getMails(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).getMails();
    }
    
    /**
     * 上传方法对象取得.
     * @param param request
     * @return 上传方法对象
     */
    public Upload getUpload(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).getUpload();
    }
    
    /**
     * 控件样式取得.
     * @param param request
     * @return 控件样式
     */
    public java.util.List<Style> getStyles(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).getStyles();
    }
    
    /**
     * 数据类型取得.
     * @param param request
     * @return 数据类型
     */
    public String getDataType(Param param) {
        String dataType = ConstantsUtil.DataType.SINGLE;
        if (reqestMap.get(param.dispCode).get(param.cmdCode).hasComplex()) {
            final Complex complex = getComplex(param);
            final Lists lists = complex.getLists();
            if (lists != null && !ConstantsUtil.Client.AJAX_TYPE.equals(param.clientType)) {
                dataType = ConstantsUtil.DataType.LIST;
            }
        } else if (reqestMap.get(param.dispCode).get(param.cmdCode).hasList() 
                || reqestMap.get(param.dispCode).get(param.cmdCode).hasDimension()) {
            dataType = ConstantsUtil.DataType.LIST;
        }
        return dataType;
    }
    
    /**
     * KEY是否存在.
     * @param param request
     * @return true:存在； false:不存在
     */
    public boolean isKey(Param param) {
        if (!reqestMap.containsKey(param.dispCode)) {
            return false;
        }
        return reqestMap.get(param.dispCode).containsKey(param.cmdCode);
    }
    
    /**
     * @Description: 
     * @author king
     * @since Sep 29, 2013 4:26:44 PM 
     * @version V1.0
     * @param param param
     * @return Describe
     */
    public String getDescribe(Param param) {
        return reqestMap.get(param.dispCode).get(param.cmdCode).getDescribe();
    }

    /**
     * request.xml路径取得.
     * 
     * @return path request.xml路径
     */
    public static String getPath() {
        return sPath;
    }

    /**
     * request.xml路径设置.
     * 
     * @param path request.xml路径
     */
    public static void setPath(String path) {
        ControlRequestMap.sPath = path + PATH_REQMAP;
    }
    
    /**
     * 请求MAP的设置【页面初始化】.
     * 
     * @param dispCode 画面ID
     * @param jsp JSP路径
     * 生成map数组中【0：类；
     *             1：方法名；
     *             2：JSP路径；
     *             3：是否自动登出；
     *             4：是否是servlet请求；
     *             5：是否页面迁移；
     *             6：JSP路径；
     *             7：是否需要共通验证;
     *             8:JSP路径;】
     */
    public void setReqestMapForDisplay(String dispCode, String jsp) {
        final Map<String, RequestParam> cmdMap = new HashMap<String, RequestParam>();
        final RequestParam paramObj = new RequestParam();
        paramObj.setHandlerClass("abstractHandlerItem");
        paramObj.setDispCode(dispCode);
        paramObj.getMethod().setName("showPage");
        paramObj.setMethodCode("0");
        paramObj.setJspName(jsp);
        paramObj.setLogout("0");
        paramObj.setRequest("");
        paramObj.setForward("");
        paramObj.setCheck("");
        cmdMap.put("0", paramObj);
        reqestMap.put(dispCode, cmdMap);
    }
    
    /** 
    * @Description: 请求MAP的设置.
    * 生成map数组中【0：类；
    *             1：方法名；
    *             2：JSP路径；
    *             3：是否自动登出；
    *             4：是否是servlet请求；
    *             5：是否页面迁移；
    *             6：JSP路径；
    *             7：是否需要共通验证;check = "1"→需要共通验证
    *             8:JSP路径;】
    * @author king
    * @since Nov 3, 2012 7:23:36 PM 
    * 
    * @param dispCode 画面ID
    * @param cmdCode 方法ID
    * @param jsp JSP路径
    * @param check 是否验证
    * @param methodName 方法名称 
    */
    public void setReqestMapForDisplay(String dispCode, String cmdCode, String jsp, String check, String methodName) {
        final Map<String, RequestParam> cmdMap = new HashMap<String, RequestParam>();
        final RequestParam paramObj = new RequestParam();
        paramObj.setHandlerClass("abstractHandlerItem");
        paramObj.setDispCode(dispCode);
        paramObj.getMethod().setName(methodName);
        paramObj.setMethodCode(cmdCode);
        paramObj.setJspName(jsp);
        paramObj.setLogout("0");
        paramObj.setRequest("");
        paramObj.setForward("");
        paramObj.setCheck(check);
        cmdMap.put(cmdCode, paramObj);
        reqestMap.put(dispCode, cmdMap);
    }

    
}
