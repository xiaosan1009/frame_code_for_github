package com.richClientFrame.filter;

import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.info.ControlRequestMap;
import com.richClientFrame.info.UserInfo;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import java.io.File;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： SessionCheckFilter
 * 类描述 ： 用户session信息过滤器
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.05.30
 * @author king
 */
public class SessionCheckFilter extends AbstractCommonFilter {
    
    private ServletContext context;
    
    private static String sRealPath;
    
    private static LogUtil sLog;

    /**
     * 构造函数.
     * 
     */
    public SessionCheckFilter() {
        super();
    }

    /**
     * 初始化方法.
     * 
     * @param filterConfig 过滤器对象
     * @throws ServletException ServletException
     * 
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        
        context = filterConfig.getServletContext();
        
        sRealPath = context.getRealPath(File.separator);
        ControlRequestMap.setPath(sRealPath);
        sLog = new LogUtil(SessionCheckFilter.class, true);
    }
    
    /**
     * 过滤器过滤方法.
     * 
     * @param request 客户端请求
     * @param response 服务器端响应
     * @param chain 过滤器对象
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        sLog.info("doFilter", "start");
        
        String dispCode = null;
        String cmdCode = null;
        ControlRequestMap requestMap = null;
        boolean isUpload = false;
        
        try {
            requestMap = ControlRequestMap.getInstance();
            final Param param = new Param();
        
            if (request instanceof HttpServletRequest) {
                final HttpServletRequest req = (HttpServletRequest) request;
                param.pickParameters(req, false);

                final String explorerInfo = "user-agent : " + req.getHeader("user-agent");
                final Cookie[] cookies = req.getCookies();
                final StringBuffer cookieBuff = new StringBuffer();
                if (cookies != null && cookies.length > 0) {
                    for (int i = 0; i < cookies.length; i++) {
                        final Cookie cookie = cookies[i];
                        cookieBuff.append(cookie.getName() + "=" + cookie.getValue() + ";");
                    }
                }
                final String cookieInfo = "cookie : " + cookieBuff.toString();
                final String requestPath = "requestPath : " + req.getServletPath();
                final String requestUrl = "requestUrl : " + req.getRequestURL();
                final String remoteAddr = "remoteAddr : " + req.getRemoteAddr();
                final String remoteHost = "remoteHost : " + req.getRemoteHost();
                final String remotePort = "remotePort : " + req.getRemotePort();
                final String queryString = "queryString : " + req.getQueryString();

                sLog.info("doFilter", null, explorerInfo, 
                        requestPath, requestUrl, queryString, remoteAddr, remoteHost, remotePort, cookieInfo);
                
                if (ControlConfig.getInstance().getConfiguration().isEnableRequestHeaderChecker()) {
                    final String userAgentRequestHeaderChecker = 
                        ControlConfig.getInstance().getConfiguration().getUserAgentRequestHeaderChecker();
                    if (CommonUtil.isNotEmpty(userAgentRequestHeaderChecker)) {
                        final String[] userAgents = userAgentRequestHeaderChecker.split("\\|");
                        boolean checkSuccess = false;
                        for (int i = 0; i < userAgents.length; i++) {
                            if (explorerInfo.indexOf(userAgents[i]) != -1) {
                                checkSuccess = true;
                                break;
                            }
                        }
                        if (!checkSuccess) {
                            forwardErrorUrl(request, response, isUpload, 
                                    EngineExceptionEnum.UR_COM_INVALID_URL_REQUEST);
                            return;
                        }
                    }
                }
    
                final ServletRequestContext requestcontext = new ServletRequestContext(
                        (HttpServletRequest)request);
                if (ServletFileUpload.isMultipartContent(requestcontext)) {
                    chain.doFilter(request, response);
                    return;
                } else {
                    dispCode = request.getParameter(ConstantsUtil.Param.DISP);
                    cmdCode = request.getParameter(ConstantsUtil.Param.CMD);
                    isUpload = false;
                }

                SessionData seData = null;
                UserInfo ope = null;
                HttpSession session = req.getSession(false);
                sLog.fatal("doFilter", "session == " + session);
                if (session != null) {
                    final Object obj = session.getAttribute(SessionData.KEY_SESSIONDATA);
                    sLog.fatal("doFilter", "userinfo == " + obj);
                    if (obj != null) {
                        if (obj instanceof SessionData) {
                            seData = (SessionData)obj;
                            ope = seData.getUserInfo();
                        }
                    }
                }
                param.dispCode = dispCode;
                param.cmdCode = cmdCode;
                sLog.info("doFilter", null, "dispCode = " + dispCode, "cmdCode = " + cmdCode, 
                        "clientType = " + param.clientType, "checker = " + param.checker);
                if (ControlConfig.getInstance().getConfiguration().isEnableRequestChecker()) {
                    if (ConstantsUtil.Client.AJAX_TYPE.equals(param.clientType)) {
                        final String requestAjax = 
                            ControlConfig.getInstance().getConfiguration().getAjaxRequestChecker();
                        final boolean flg = checkReqeustKey(
                                request, response, isUpload, param, requestAjax);
                        if (!flg) {
                            return;
                        }
                    } else if (ConstantsUtil.Client.FLEX_TYPE.equals(param.clientType)) {
                        final String requestFlex = 
                            ControlConfig.getInstance().getConfiguration().getFlexRequestChecker();
                        final boolean flg = checkReqeustKey(
                                request, response, isUpload, param, requestFlex);
                        if (!flg) {
                            return;
                        }
                    } else if (ConstantsUtil.Client.ANDROID_TYPE.equals(param.clientType)) {
                        final String requestAndroid = 
                            ControlConfig.getInstance().getConfiguration().getAndroidRequestChecker();
                        final boolean flg = checkReqeustKey(
                                request, response, isUpload, param, requestAndroid);
                        if (!flg) {
                            return;
                        }
                    } else {
                        forwardErrorUrl(request, response, isUpload, 
                                EngineExceptionEnum.UR_COM_INVALID_URL_REQUEST);
                        return;
                    }
                }
                
                // 当画面ID与方法ID其一不存在的
                if (CommonUtil.isEmpty(dispCode) || CommonUtil.isEmpty(cmdCode)) {
                    sLog.fatal("doFilter", "画面ID或者方法ID为空");
                    forwardErrorUrl(request, response, isUpload, EngineExceptionEnum.UR_COM_INVALID_URL_REQUEST);
                    return;
                }
                
                // 当画面ID与方法ID不匹配的
                if (!requestMap.isKey(param)) {
                    sLog.fatal("doFilter", "画面ID : " + dispCode + ",方法ID : " + cmdCode + "的组合不存在.");
                    forwardErrorUrl(request, response, isUpload, EngineExceptionEnum.UR_COM_INVALID_URL_REQUEST);
                    return;
                }
                
                final boolean hasSession = checkSessionExist(requestMap, param, ope);
                if (!hasSession && !ConstantsUtil.Client.FLEX_TYPE.equals(param.clientType)) {
                    sLog.info("doFilter", "用户session过期");
                    forwardErrorUrl(request, response, param, EngineExceptionEnum.UR_COM_SESSION_TIMEOUT, isUpload);
                    seData = null;
                    if (session != null) {
                        session.invalidate();
                    }
                    session = null;
                    return;
                }
                sLog.info("doFilter", "end");
                chain.doFilter(request, response);
            }
        } catch (RichClientWebException e) {
            e.printStackTrace();
        }
    }

    private boolean checkReqeustKey(ServletRequest request, ServletResponse response,
            boolean isUpload, final Param param,
            final String requestAjax) throws RichClientWebException, ServletException, IOException {
        if (CommonUtil.isNotEmpty(requestAjax)) {
            if (ConstantsUtil.Request.REQUEST_REFUSE.equals(requestAjax)) {
                forwardErrorUrl(request, response, isUpload, EngineExceptionEnum.UR_COM_INVALID_URL_REQUEST);
                return false;
            }
            if (CommonUtil.isEmpty(param.checker) || !requestAjax.equals(param.checker)) {
                forwardErrorUrl(request, response, isUpload, EngineExceptionEnum.UR_COM_INVALID_URL_REQUEST);
                return false;
            }
        }
        return true;
    }

    /**
     * 验证session是否存在的方法.
     * 
     * @param requestMap 系统请求的分发xml解析类对象
     * @param param request
     * @param userInfo 用户信息对象
     * @return 验证结果
     */
    private boolean checkSessionExist(ControlRequestMap requestMap, final Param param,
            UserInfo userInfo) {
        boolean hasSession = true;
        if (CommonUtil.isEmpty(param.dispCode) || CommonUtil.isEmpty(param.cmdCode)) {
            return hasSession;
        }
        if (!requestMap.isAutoLogout(param)) {
            sLog.info("doFilter", "isAutoLogout == true");
            hasSession = true;
        } else {
            if (userInfo == null) {
                sLog.info("doFilter", "userInfo == null");
                hasSession = false;
            } else {
                hasSession = true;
            }
        }
        return hasSession;
    }

    /**
     * 过滤器销毁的方法.
     * 
     */
    public void destroy() {
    }
    
}
