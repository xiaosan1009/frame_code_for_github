/**
 * ��ܹ�ͨservlet
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
     * ���캯��.
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
            
            // ��Դ�ļ���ȡ
            if (CommonUtil.isNotEmpty(getResourcePath())) {
                ControlResourceMap.setPath(sRealPath + getResourcePath());
            }
            ControlResourceMap.setFramePath(sRealPath + ConstantsUtil.Frame.DIR_RESOURCE);
            ControlResourceMap.getInstance();
            
            // ���Ʋ�����������Ϣ��ȡ(WEB-INF/xml/request)
            ControlRequestMap.setPath(sRealPath);
            ControlRequestMap.getInstance();
            
            // ��֤�ļ���ȡ(WEB-INF/err)
            ControlErrorMap.setPath(sRealPath);
            ControlErrorMap.getInstance();
            
            // ������Ŀ�ļ���ȡ(WEB-INF/disp/field & resource)
            ControlDispField.setPath(sRealPath);
            ControlDispField.getInstance();
            
            // �����ʾ��Ϣ��ȡ(WEB-INF/xml/errmsg)
            ControlMasters.setPath(masterPath);
            ControlMasters.getInstance();
            
            // memcached��Ϣ��ȡ
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
     * request ������
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
                    
                    // �ļ��ϴ�������
                    final DiskFileItemFactory factory = new DiskFileItemFactory();
                    factory.setSizeThreshold(1426);

                    final String tmppath = ControlConfig.getSesfPath() + "TEMP/";
                    
                    // ���ػ���Ŀ¼
                    final File dir = new File(tmppath);
                    if (dir.isDirectory() && dir.exists()) {
                        FileUtils.cleanDirectory(dir);
                    }
                    // ����Ŀ¼
                    FileUtils.forceMkdir(dir);

                    // ��������·��
                    factory.setRepository(new File(tmppath));

                    final ServletFileUpload upload = new ServletFileUpload(factory);
                    // ���޶��ϴ��ļ���С
                    upload.setSizeMax(-1);
                    
                    List<?> items;
                    try {
                        items = upload.parseRequest(request);
                    } catch (FileUploadException e) {
                        throw new ServletException(e);
                    }

                    // �����ϴ��ļ�
                    for (Object val : items) {
                        final FileItem item = (FileItem) val;
                        if (item.isFormField()) {
                            // ����ʽΪ����ʽ ȡ�û���ID�ͷ���ID������������
                            if (item.getFieldName().equals(ConstantsUtil.Param.DISP)) {
                                dispCode = item.getString();
                            } else if (item.getFieldName().equals(ConstantsUtil.Param.CMD)) {
                                cmdCode = item.getString();
                            } else {
                                // ȡ�÷ǹ̶������ֵ
                                final String paramName = item.getFieldName();
                                final String paramValue = item.getString();
                                param.putParameters(paramName, new String[]{paramValue});
                            }
                        } else {
                            // ����Ϊ�ļ����͵� �ϴ����ϴ�Ŀ¼
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

            // ������ID�뷽��ID��һ�����ڵ�
            if (dispCode == null || cmdCode == null) {
                log.fatal("dispcode or cmdcode is null.");
                return;
//                throw new RichClientWebException(ConstantsUtil.Result.SYSTEM_ERROR);
            }
            
            // ������ID�뷽��ID��ƥ���
            if (!reqMap.isKey(param)) {
                log.fatal("dispCode : " + dispCode + ",cmdCode : " + cmdCode + " ��ƥ��.");
                throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR);
            }
            // ������Ӧ����[Ĭ����������ܷ��ز���]
            setResponse(response, param);
            // servlet ��������,����ֵΪ[�����Ƿ���л���Ǩ�Ʋ���]�����Ϊtrue���򲻽���xml�����еĻ���Ǩ�Ʋ���
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
        // �����Ƿ�Ǩ��.
        if (!reqMap.isForward(param)) {
            return;
        }
        if (th != null) {
            // ��Ǩ��һ�㻭����ߴ�ӡ�����ʱ�򱨴�
            if (ConstantsUtil.Method.SHOW_PAGE_CODE.equals(cmdCode) 
                    || ConstantsUtil.Method.SHOW_PRINT_CODE.equals(cmdCode)) {
                if (!ConstantsUtil.Client.AJAX_TYPE.equals(param.clientType)) {
                    forwardToError(request, response, th, ConstantsUtil.Page.SINGLE_PAGE_URL_PATH, param);
                } else {
                    forwardToError(request, response, th, ConstantsUtil.Page.ERROR_PATH, param);
                }
            } else {
                // �����������ʱ��
                forwardToError(request, response, th, ConstantsUtil.Page.SINGLE_PAGE_URL_PATH, param);
            }
        } else {
            // ��ȷǨ��
            forwardToJsp(request, response, param);
        }
        log.info("processRequest", "end", "dispCode = " + param.dispCode, "cmdCode = " + param.cmdCode);
    }

    /** 
    * @Description: ������Ӧ����[Ĭ����������ܷ��ز���]
    * @author king
    * @since Oct 4, 2012 11:14:49 AM 
    * 
    * @param response �ͻ�����Ӧ����
    * @param param �ͻ����������
    */
    protected void setResponse(HttpServletResponse response, Param param) {
        response.setHeader("Expires", "1990/01/01 00:00:00");
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
    }

    /**
     * servlet ��������
     * @param request HttpServletRequest
     * @param response HttpSerletResponse
     * @param param Param
     * @return �����Ƿ���л���Ǩ�Ʋ���
     * @throws RichClientWebException RichClientWebException
     * @throws IOException IOException
     */
    private boolean dispatch(HttpServletRequest request, HttpServletResponse response,
            Param param) throws RichClientWebException, IOException {
        log.debug("dispatch", "start", 
                "dispCode : " + param.dispCode + ",cmdCode : " + param.cmdCode);
        HttpSession session = null;
        SessionData seData = null;
        // �Ƿ����session
        boolean bDelSession = false;

        try {
            bDelSession = isLogout(param);
            // �û���¼
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
                log.info("dispatch", "�û��ǳ�.");
//                throw new RichClientWebException(ConstantsUtil.Result.SESSION_TIMEOUT, true);
            }
            
            // request��ʼ��������
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
                        // ��error������session��
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
     * �л�����Դ
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @return �л�������Դ��ַ
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
     * �л�����Դ
     * 
     * @param param �û��������
     * @param seData �û��Ự����
     * @param dataSource �л�����Դ����
     * @return ����Դ���
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
     * ����session
     * @param request request
     * @return session����
     */
    private SessionData createSession(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final SessionData seData = new SessionData();
        seData.setSessionId(session.getId());
        seData.setRemoteAddr(request.getRemoteAddr());
        session.setAttribute(SessionData.KEY_SESSIONDATA, seData);
        // ����session������
//        final SessionManagement sessionM = new SessionManagement();
//        session.setAttribute(SessionManagement.KEY_SESSIONMNG, sessionM);
        return seData;
    }
    
    /**
     * ���������ʾ
     * 
     * @param request HttpServletRequest �������
     * @param response HttpServletResponse ��Ӧ����
     * @param param �û��������
     * @throws ServletException ServletException
     */
    private void forwardToJsp(
            HttpServletRequest request,
            HttpServletResponse response,
            Param param) throws ServletException {
        log.debug("forwardToJsp", "start");
        String targetJsp = null;
        
        try {
            // Ǩ�ƻ���·��ȡ��
            targetJsp = ControlRequestMap.getInstance().getJsp(param);
            // ���������ж�
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
                // ����Ǩ��
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
     * �������ʾ
     * @param request HttpServletRequest �������
     * @param response HttpServletResponse ��Ӧ����
     * @param ex HimWebException �쳣����
     * @param dispatchJsp ������·��
     * @param param �û��������
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
            
            // ����Ǩ��
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
     * �ϴ��ļ��еĳ��ڻ�����
     * @param item �ϴ��ļ�����
     * @param sessionID session��
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    private void processUploadedFile(FileItem item, String sessionID) throws IOException, ServletException {
        try {
            final String sesworkpath = CommonUtil.getSessionUploadPath(sessionID);
            
            // �ļ��г��ڻ�
            final File dir = new File(sesworkpath);
            if (dir.isDirectory() && dir.exists()) {
                FileUtils.cleanDirectory(dir);
            }
            // �����ļ���
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
     * ��ʼ����ʽ������
     * @param param �û��������
     * @param seData �û��Ự����
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
