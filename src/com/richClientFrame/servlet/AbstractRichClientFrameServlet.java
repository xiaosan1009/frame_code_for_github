/**
 * 框架共通servlet
 */
package com.richClientFrame.servlet;

import com.hisunsray.commons.res.Config;
import com.richClientFrame.dao.DynamicDataSource;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.param.RequestParam.DataSource;
import com.richClientFrame.data.param.RequestParam.Upload;
import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseData;
import com.richClientFrame.data.response.ResponseHeader;
import com.richClientFrame.data.response.ResponseTab;
import com.richClientFrame.data.response.data.ResponseHeaderData;
import com.richClientFrame.exception.BreakProgressException;
import com.richClientFrame.exception.InputException;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.handler.CommonActionHandler;
import com.richClientFrame.handler.iface.IDataSourceFace;
import com.richClientFrame.info.ContorlMemcached;
import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.info.ControlDispField;
import com.richClientFrame.info.ControlErrorMap;
import com.richClientFrame.info.ControlMasters;
import com.richClientFrame.info.ControlRequestMap;
import com.richClientFrame.info.ControlResourceMap;
import com.richClientFrame.service.IDbService;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.FileUtil;
import com.richClientFrame.util.LogUtil;
import com.richClientFrame.util.MemcachedUtil;
import com.richClientFrame.util.socket.SocketProtocol;
import com.richClientFrame.util.socket.SocketThread;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.FileUtils;
import org.springframework.dao.DataAccessException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * servlet main control.
 * @author king
 * @since 2010.07.28
 */
public abstract class AbstractRichClientFrameServlet implements IFrameServletFace {

    /**
     * UID
     */
    private static final long serialVersionUID = 3529815599573833555L;

    /**
     * ServletContext
     */
    private ServletContext context;
    
    /**
     * realPath
     */
    private static String sRealPath;
    
    private boolean isUpload;
    
    private IDataSourceFace mDataSource;
    
    private ContorlMemcached mMemcached;
    
    private IDbService db;
    
    /**
     * logger
     */
    protected LogUtil log;
    
    /**
     * 构造函数.
     */
    public AbstractRichClientFrameServlet() {
        super();
    }

    /**
     * initialize the servlet.
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     * @param config ServletConfig
     * @throws ServletException ServletException
     */
    public void init(ServletConfig config) throws ServletException {
        try {
            // logger
            log = new LogUtil(AbstractRichClientFrameServlet.class, true);
            
            final StringBuilder frameLog = new StringBuilder();
            frameLog.append("\nengine_version:" + ConstantsUtil.Frame.ENGINE_VERSION);
            frameLog.append("\nengine_version_descript:");
            for (int i = 0; i < ConstantsUtil.Frame.ENGINE_VERSION_DESCRIPT.length; i++) {
                frameLog.append("\n");
                frameLog.append((i + 1) + ".");
                frameLog.append(ConstantsUtil.Frame.ENGINE_VERSION_DESCRIPT[i]);
            }
            
            log.info("init", "start", frameLog.toString());
            
            synchronized (this) {
                if (context == null) {
                    context = config.getServletContext();
                }
            }
            
            // realPath
            sRealPath = context.getRealPath(File.separator);
            
            final String configPath = sRealPath + context.getInitParameter("configPath");
            final String errorMenuPath = context.getInitParameter("errorMenuPath");
            final String masterPath = sRealPath + context.getInitParameter("masterPath");
            final String memcachedPath = context.getInitParameter("memcachedPath");
            final String configFilePath = context.getInitParameter("configFilePath");
            
            final String sesPath = sRealPath + ControlConfig.DEF_SESDIR;
            
            ControlConfig.setSesfPath(sesPath);
            ControlConfig.setErrorMenuPath(errorMenuPath);
            if (CommonUtil.isNotEmpty(configFilePath)) {
                Config.setConfigResource(sRealPath + configFilePath);
            }
            
            initialize(context);
            
            // Config.xml
            ControlConfig.setConfPath(configPath);
            ControlConfig.setRealPath(sRealPath);
            ControlConfig.setHostUrl(CommonUtil.getAppUrlPath());
            ControlConfig.getInstance();
            
            // 资源文件读取
            if (CommonUtil.isNotEmpty(getResourcePath())) {
                ControlResourceMap.setPath(sRealPath + getResourcePath());
            }
            ControlResourceMap.setFramePath(sRealPath + ConstantsUtil.Frame.DIR_RESOURCE);
            ControlResourceMap.getInstance();
            
            // 控制层整体配置信息读取(WEB-INF/xml/request)
            ControlRequestMap.setPath(sRealPath);
            ControlRequestMap.getInstance();
            
            // 验证文件读取(WEB-INF/err)
            ControlErrorMap.setPath(sRealPath);
            ControlErrorMap.getInstance();
            
            // 画面项目文件读取(WEB-INF/disp/field & resource)
            ControlDispField.setPath(sRealPath);
            ControlDispField.getInstance();
            
            // 结果显示信息读取(WEB-INF/xml/errmsg)
            ControlMasters.setPath(masterPath);
            ControlMasters.getInstance();
            
            // memcached信息读取
            if (CommonUtil.isNotEmpty(memcachedPath)) {
                MemcachedUtil.setPath(memcachedPath);
                MemcachedUtil.getInstance();
            }
            
            if (ControlConfig.getInstance().getConfiguration().isSocketEnable()) {
                new SocketProtocol();
                new SocketThread();
                log.info("init", "thread start");
            }
            
        } catch (Exception e) {
            log.fatal(e);
            e.printStackTrace();
            throw new ServletException(e);
        }
        log.info("init", "end");
    }
    
    /**
     *  Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    public void doGet(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, java.io.IOException {
        processRequest(request, response);
    }

    /**
     *  Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    public void doPost(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, java.io.IOException {
        processRequest(request, response);
    }    

    /**
     * request 主请求
     * @param request HttpServletRequest
     * @param response HttpSerletResponse
     * @throws ServletException ServletException
     */
    protected void processRequest(
            HttpServletRequest request, HttpServletResponse response)
        throws ServletException {
        log.info("processRequest", "start");
        
        final Param param = new Param();
        
        String dispCode = null;
        String cmdCode = null;
        RichClientWebException th = null;
        ControlRequestMap reqMap = null;
        
        try {
            param.pickParameters(request, true);
            doRequest(request, response);
            reqMap = ControlRequestMap.getInstance();
            // get servlet request object
            final ServletRequestContext requestcontext = new ServletRequestContext(request);
            if (ServletFileUpload.isMultipartContent(requestcontext)) {
                // get session object
                final HttpSession session = request.getSession(false);
                log.info("processRequest", 
                        "isMultipartContent", "session = " + session);
                if (session != null) {
                    
                    // 文件上传工厂类
                    final DiskFileItemFactory factory = new DiskFileItemFactory();
                    factory.setSizeThreshold(1426);

                    final String tmppath = ControlConfig.getSesfPath() + "TEMP/";
                    
                    // 下载缓冲目录
                    final File dir = new File(tmppath);
                    if (dir.isDirectory() && dir.exists()) {
                        FileUtils.cleanDirectory(dir);
                    }
                    // 生成目录
                    FileUtils.forceMkdir(dir);

                    // 设置下载路径
                    factory.setRepository(new File(tmppath));

                    final ServletFileUpload upload = new ServletFileUpload(factory);
                    // 不限定上传文件大小
                    upload.setSizeMax(-1);
                    
                    List<?> items;
                    try {
                        items = upload.parseRequest(request);
                    } catch (FileUploadException e) {
                        throw new ServletException(e);
                    }

                    // 遍历上传文件
                    for (Object val : items) {
                        final FileItem item = (FileItem) val;
                        if (item.isFormField()) {
                            // 当格式为表单格式 取得画面ID和方法ID或者其他参数
                            if (item.getFieldName().equals(ConstantsUtil.Param.DISP)) {
                                dispCode = item.getString();
                            } else if (item.getFieldName().equals(ConstantsUtil.Param.CMD)) {
                                cmdCode = item.getString();
                            } else {
                                // 取得非固定意义的值
                                final String paramName = item.getFieldName();
                                final String paramValue = item.getString();
                                param.putParameters(paramName, new String[]{paramValue});
                            }
                        } else {
                            // 类型为文件类型的 上传到上传目录
                            param.putParameters(item.getFieldName(), new String[]{item.getName()});
//                            processUploadedFile(item, session.getId());
                            param.setUploadItem(item);
                        }
                    }
                    if (CommonUtil.isEmpty(dispCode)) {
                        dispCode = request.getParameter("dispcode");
                    }
                    if (CommonUtil.isEmpty(cmdCode)) {
                        cmdCode = request.getParameter("cmdcode");
                    }
                    param.dispCode = dispCode;
                    param.cmdCode = cmdCode;
                }
                isUpload = true;
            } else {
                dispCode = request.getParameter(ConstantsUtil.Param.DISP);
                if (CommonUtil.isNotEmpty(request.getParameter("pluginDispCode"))) {
                    dispCode = request.getParameter("pluginDispCode");
                }
                cmdCode = request.getParameter(ConstantsUtil.Param.CMD);
                param.dispCode = dispCode;
                param.cmdCode = cmdCode;
                isUpload = false;
            }

            // 当画面ID与方法ID其一不存在的
            if (dispCode == null || cmdCode == null) {
                log.fatal("dispcode or cmdcode is null.");
                return;
//                throw new RichClientWebException(ConstantsUtil.Result.SYSTEM_ERROR);
            }
            
            // 当画面ID与方法ID不匹配的
            if (!reqMap.isKey(param)) {
                log.fatal("dispCode : " + dispCode + ",cmdCode : " + cmdCode + " 不匹配.");
                throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR);
            }
            // 设置响应对象[默认浏览器不能返回操作]
            setResponse(response, param);
            // servlet 主处理方法,返回值为[后续是否进行画面迁移操作]，如果为true：则不进行xml配置中的画面迁移操作
            if (dispatch(request, response, param)) {
                return;
            }
        } catch (RichClientWebException he) {
            if (!he.isInformation()) {
                log.fatal(ConstantsUtil.ErrInfo.SYSTEM_ERROR, he);
                he.printStackTrace();
                th = he;
            } else {
                try {
                    final ResponseHeaderData responseHeaderData = new ResponseHeaderData();
                    responseHeaderData.setParam(param);
                    if (he.getEngineExceptionEnum() != null) {
                        responseHeaderData.setResCode(he.getEngineExceptionEnum().getCode());
                    } else {
                        responseHeaderData.setResCode(he.getInfoCode());
                    }
                    final ResponseHeader header = new ResponseHeader(responseHeaderData);
                    header.setError(ResponseHeader.ERROR_HEADER_CODE);
                    final ResponseData reData = new ResponseData(header);
                    reData.setClientType(param.clientType);
                    reData.setUpload(isUpload);
                    request.setAttribute(AbstractResponseData.KEY_RESPONSE_DATA, reData);
                } catch (RichClientWebException e) {
                    log.fatal(ConstantsUtil.ErrInfo.SYSTEM_ERROR, e);
                    e.printStackTrace();
                    throw new ServletException();
                }
            }
        } catch (RuntimeException rex) {
            log.fatal(EngineExceptionEnum.FM_COM_SYSTEM_ERROR.getErrinfo(), rex);
            rex.printStackTrace();
            th = new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, rex);
        } catch (Exception ex) {
            log.fatal(EngineExceptionEnum.FM_COM_SYSTEM_ERROR.getErrinfo(), ex);
            ex.printStackTrace();
            th = new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, ex);
        }
        // 画面是否迁移.
        if (!reqMap.isForward(param)) {
            return;
        }
        if (th != null) {
            // 在迁移一般画面或者打印画面的时候报错
            if (ConstantsUtil.Method.SHOW_PAGE_CODE.equals(cmdCode) 
                    || ConstantsUtil.Method.SHOW_PRINT_CODE.equals(cmdCode)) {
                if (!ConstantsUtil.Client.AJAX_TYPE.equals(param.clientType)) {
                    forwardToError(request, response, th, ConstantsUtil.Page.SINGLE_PAGE_URL_PATH, param);
                } else {
                    forwardToError(request, response, th, ConstantsUtil.Page.ERROR_PATH, param);
                }
            } else {
                // 其他错误画面的时候
                forwardToError(request, response, th, ConstantsUtil.Page.SINGLE_PAGE_URL_PATH, param);
            }
        } else {
            // 正确迁移
            forwardToJsp(request, response, param);
        }
        log.info("processRequest", "end", "dispCode = " + param.dispCode, "cmdCode = " + param.cmdCode);
    }

    /** 
    * @Description: 设置响应对象[默认浏览器不能返回操作]
    * @author king
    * @since Oct 4, 2012 11:14:49 AM 
    * 
    * @param response 客户端响应对象
    * @param param 客户端请求对象
    */
    protected void setResponse(HttpServletResponse response, Param param) {
        response.setHeader("Expires", "1990/01/01 00:00:00");
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
    }

    /**
     * servlet 主处理方法
     * @param request HttpServletRequest
     * @param response HttpSerletResponse
     * @param param Param
     * @return 后续是否进行画面迁移操作
     * @throws RichClientWebException RichClientWebException
     * @throws IOException IOException
     */
    private boolean dispatch(HttpServletRequest request, HttpServletResponse response,
            Param param) throws RichClientWebException, IOException {
        log.debug("dispatch", "start", 
                "dispCode : " + param.dispCode + ",cmdCode : " + param.cmdCode);
        HttpSession session = null;
        SessionData seData = null;
        // 是否清除session
        boolean bDelSession = false;

        try {
            bDelSession = isLogout(param);
            // 用户登录
            if (!bDelSession) {
                session = request.getSession(false);
                log.info("dispatch", null, "session = " + session);
                if (session == null) {
                    seData = createSession(request);
                } else {
                    seData = (SessionData)session.getAttribute(SessionData.KEY_SESSIONDATA);
                    if (seData == null || seData.getSessionId() == null) {
                        seData = createSession(request);
                    }
                }
            } else {
                log.info("dispatch", "用户登出.");
//                throw new RichClientWebException(ConstantsUtil.Result.SESSION_TIMEOUT, true);
            }
            
            // request初始数据设置
//            if (seData != null) {
//                param.setDefaultValues(seData);
//            }
            AbstractResponseData resData = null;
            ResponseHeader header = null;
            ResponseTab[] responseTabs = null;
            try {
                final String dataSource = switchDataSource(param, seData);
                if (CommonUtil.isNotEmpty(dataSource)) {
                    param.setDataSource(dataSource);
                }
                resData = CommonActionHandler.doAction(context, request, response, param, seData);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                throw new RichClientWebException(EngineExceptionEnum.FM_COM_NO_SUCH_METHOD, e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RichClientWebException(EngineExceptionEnum.FM_COM_ILLEGAL_ACCESS, e);
            } catch (InvocationTargetException e) {
                if (e.getTargetException() instanceof RichClientWebException) {
                    header = ((RichClientWebException)e.getTargetException()).getErrHead();
                    if (header == null) {
                        // 把error保存在session中
//                        seData.setDetailErr(param.dispCode, e);
//                        log.fatal(ConstantsUtil.ErrInfo.SYSTEM_ERROR, e.getTargetException());
//                        e.getTargetException().printStackTrace();
                        throw (RichClientWebException)e.getTargetException();
                    }
                } else if (e.getTargetException() instanceof NullPointerException) {
                    throw new RichClientWebException(EngineExceptionEnum.FM_COM_NULL_POINTER, e.getTargetException());
                } else if (e.getTargetException() instanceof DataAccessException) {
                    throw new RichClientWebException(EngineExceptionEnum.UR_COM_DB_ERROR, e.getTargetException());
                } else if (e.getTargetException() instanceof InputException) {
                    header = ((InputException)e.getTargetException()).getErrHead();
                    responseTabs = ((InputException)e.getTargetException()).getErrTab();
                } else {
                    throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e.getTargetException());
                }
            } catch (BreakProgressException e) {
                return true;
            } catch (Exception e) {
                throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
            } finally {
                if (resData == null) {
                    resData = new ResponseData(header);
                }
                resData.setClientType(param.clientType);
                final Upload upload = ControlRequestMap.getInstance().getUpload(param);
                if (upload != null) {
                    final String dataType = upload.getDataType();
                    if ("list".equals(dataType)) {
                        isUpload = false;
                    }
                }
                resData.setUpload(isUpload);
                if (responseTabs != null && resData instanceof ResponseData) {
                    ((ResponseData)resData).setResTab(responseTabs);
                }
                request.setAttribute(AbstractResponseData.KEY_RESPONSE_DATA, resData);
            }
        } finally {
            if (bDelSession) {
                seData = null;
                session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                }
                session = null;
            }
        }
        log.debug("dispatch", "end");
        return false;
    }
    
    /**
     * 切换数据源
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @return 切换的数据源地址
     * @throws RichClientWebException RichClientWebException
     */
    private String switchDataSource(Param param, SessionData seData) throws RichClientWebException {
        log.debug("switchDataSource", "start");
        DataSource dataSource = ControlRequestMap.getInstance().getDataSource(param);
        if (dataSource == null) {
            dataSource = ControlRequestMap.getInstance().getDataSourceInResource();
        }
        if (dataSource != null) {
            return switchDataSource(param, seData, dataSource);
        }
        log.debug("switchDataSource", "end");
        return null;
    }
    
    /**
     * 切换数据源
     * 
     * @param param 用户请求对象
     * @param seData 用户会话对象
     * @param dataSource 切换数据源对象
     * @return 数据源序号
     * @throws RichClientWebException RichClientWebException
     */
    private String switchDataSource(Param param, SessionData seData, DataSource dataSource) 
        throws RichClientWebException {
        final String source = dataSource.getTarget();
        log.debug("switchDataSource", "start", "dbSource = " + source);
        if (CommonUtil.isNotEmpty(source)) {
            initializeDataSource(param, seData);
            DynamicDataSource.setDb(source);
            return source;
        } else if (mDataSource != null) {
            final String method = dataSource.getMethod();
            log.info("switchDataSource", null, "method = " + method);
            if (CommonUtil.isNotEmpty(method)) {
                mDataSource.preExecute(method);
                initializeDataSource(param, seData);
                return mDataSource.execute(method);
            }
        }
        log.debug("switchDataSource", "end");
        return null;
    }
    
    /**
     * 创建session
     * @param request request
     * @return session对象
     */
    private SessionData createSession(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final SessionData seData = new SessionData();
        seData.setSessionId(session.getId());
        seData.setRemoteAddr(request.getRemoteAddr());
        session.setAttribute(SessionData.KEY_SESSIONDATA, seData);
        // 生成session管理类
//        final SessionManagement sessionM = new SessionManagement();
//        session.setAttribute(SessionManagement.KEY_SESSIONMNG, sessionM);
        return seData;
    }
    
    /**
     * 正常画面表示
     * 
     * @param request HttpServletRequest 请求对象
     * @param response HttpServletResponse 响应对象
     * @param param 用户请求对象
     * @throws ServletException ServletException
     */
    private void forwardToJsp(
            HttpServletRequest request,
            HttpServletResponse response,
            Param param) throws ServletException {
        log.debug("forwardToJsp", "start");
        String targetJsp = null;
        
        try {
            // 迁移画面路径取得
            targetJsp = ControlRequestMap.getInstance().getJsp(param);
            // 请求类型判断
            if (!ConstantsUtil.Client.AJAX_TYPE.equals(param.clientType)) {
                if (CommonUtil.isEmpty(targetJsp) 
                        && ControlRequestMap.getInstance().getDataType(param) != ConstantsUtil.DataType.LIST 
                        && !ConstantsUtil.Method.SHOW_PAGE_CODE.equals(param.cmdCode)) {
                    targetJsp = ConstantsUtil.Page.SINGLE_PAGE_URL_PATH;
                } else {
                    targetJsp = ConstantsUtil.Page.JSON_URL_PATH;
                }
            } else {
                if (CommonUtil.isEmpty(targetJsp)) {
                    targetJsp = ConstantsUtil.Page.SINGLE_PAGE_URL_PATH;
                }
            }
            log.info("forwardToJsp", null, "targetJsp = " + targetJsp);
            if (CommonUtil.isNotEmpty(targetJsp)) {
                // 画面迁移
                final RequestDispatcher rd = context.getRequestDispatcher(targetJsp);
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.fatal(e.toString());
            throw new ServletException();
        } finally {
            log.debug("forwardToJsp", "end");
        }
    }
    
    /**
     * 错误画面表示
     * @param request HttpServletRequest 请求对象
     * @param response HttpServletResponse 响应对象
     * @param ex HimWebException 异常对象
     * @param dispatchJsp 错误画面路径
     * @param param 用户请求对象
     * @throws ServletException ServletException
     */
    private void forwardToError(
            HttpServletRequest request,
            HttpServletResponse response,
            RichClientWebException ex,
            String dispatchJsp, Param param) throws ServletException {
        log.info("forwardToError", "start");
        try {
            final ResponseHeaderData responseHeaderData = new ResponseHeaderData();
            responseHeaderData.setParam(param);
            if (ex.getEngineExceptionEnum() != null) {
                responseHeaderData.setResCode(ex.getEngineExceptionEnum().getCode());
            } else {
                responseHeaderData.setResCode(ex.getInfoCode());
            }
            final ResponseHeader header = new ResponseHeader(responseHeaderData);
            header.setError(ResponseHeader.ERROR_HEADER_CODE);
            final ResponseData reData = new ResponseData(header);
            reData.setResTab(ex.getErrTab());
            reData.setClientType(param.clientType);
            reData.setUpload(isUpload);
            request.setAttribute(AbstractResponseData.KEY_RESPONSE_DATA, reData);
            
            // 画面迁移
            final RequestDispatcher rd = context.getRequestDispatcher(dispatchJsp);
            rd.forward(request, response);
        } catch (Exception e) {
            log.fatal(ConstantsUtil.ErrInfo.SYSTEM_ERROR, e);
            e.printStackTrace();
            throw new ServletException();
        } finally {
            log.info("forwardToError", "end");
        }
        
    }

    /**
     * 上传文件夹的初期化处理
     * @param item 上传文件对象
     * @param sessionID session号
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    private void processUploadedFile(FileItem item, String sessionID) throws IOException, ServletException {
        try {
            final String sesworkpath = CommonUtil.getSessionUploadPath(sessionID);
            
            // 文件夹初期化
            final File dir = new File(sesworkpath);
            if (dir.isDirectory() && dir.exists()) {
                FileUtils.cleanDirectory(dir);
            }
            // 生成文件夹
            FileUtils.forceMkdir(dir);
                
            
            FileUtil.ftpUpload(item.getInputStream(), item.getName());
//            item.write(new File(sesworkpath, item.getName()));
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    
    public IDataSourceFace getDataSource() {
        return mDataSource;
    }

    public void setDataSource(IDataSourceFace dataSource) {
        this.mDataSource = dataSource;
    }
    
    /**
     * 初始化格式化对象
     * @param param 用户请求对象
     * @param seData 用户会话对象
     */
    private void initializeDataSource(Param param, SessionData seData) {
        log.info("initializeDataSource", "start", "mDataSource = " + mDataSource);
        if (mDataSource != null) {
            mDataSource.setParam(param);
            mDataSource.setSeData(seData);
            mDataSource.setMemcached(mMemcached);
            mDataSource.setDb(db);
        }
        log.info("initializeDataSource", "end");
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

    public void doRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    public String getResourcePath() {
        return null;
    }

    public void initialize(ServletContext context) throws RichClientWebException {
        
    }

    public boolean isLogout(Param param) throws RichClientWebException {
        return false;
    }

}
