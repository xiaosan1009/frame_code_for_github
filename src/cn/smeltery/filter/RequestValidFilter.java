package cn.smeltery.filter;

import com.richClientFrame.dao.DynamicDataSource;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.response.ResponseData;
import com.richClientFrame.data.response.ResponseHeader;
import com.richClientFrame.data.response.data.ResponseHeaderData;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.info.ControlRequestMap;
import com.richClientFrame.info.UserInfo;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.HtmlUtil;
import com.richClientFrame.util.LogUtil;
import com.richClientFrame.util.MemcachedUtil;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 请求过滤.
 * @author King
 * @since 2011.10.01
 */
public class RequestValidFilter implements Filter {
    private FilterConfig config;
    private String reLoginPage;
    private static LogUtil sLog;

    /**
     * 过滤器过滤的方法.
     * 
     * @param request 客户端请求
     * @param response 服务器端响应
     * @param chain 过滤器对象
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        
        String dispCode = null;
        String cmdCode = null;
        ControlRequestMap requestMap = null;
        
        try {
            requestMap = ControlRequestMap.getInstance();
            final Param param = new Param();
        
            if (request instanceof HttpServletRequest) {
                final HttpServletRequest req = (HttpServletRequest) request;
    
                dispCode = request.getParameter(ConstantsUtil.Param.DISP);
                cmdCode = request.getParameter(ConstantsUtil.Param.CMD);
                
                if (CommonUtil.isEmpty(dispCode)) {
                    dispCode = CommonUtil.toString(request.getAttribute(ConstantsUtil.Param.DISP));
                }
                
                if (CommonUtil.isEmpty(cmdCode)) {
                    cmdCode = CommonUtil.toString(request.getAttribute(ConstantsUtil.Param.CMD));
                }
                
                SessionData seData = null;
                UserInfo ope = null;
                final HttpSession session = req.getSession(false);
                if (session != null) {
                    final Object obj = session.getAttribute(SessionData.KEY_SESSIONDATA);
                    if (obj != null) {
                        if (obj instanceof SessionData) {
                            seData = (SessionData)obj;
                            ope = seData.getUserInfo();
                        }
                    }
                }
                
                param.dispCode = dispCode;
                param.cmdCode = cmdCode;
                
                if (ope != null) {
                    sLog.info("doFilter", null, "sessionId:" + session.getId(), "userId=" + ope.getUserId());
                    Object memSessionId = null;
                    try {
                        memSessionId = MemcachedUtil.getInstance().get(ope.getUserId());
                    } catch (Exception e) {
                        sLog.error("doFilter", "访问Memcache异常", e.toString());
                    }
                    if (memSessionId != null && !(session.getId()).equals(memSessionId)) {
                        sLog.info("doFilter", 
                                "用户重复登录Invalidate Session", 
                                "userID = " + ope.getUserId(), 
                                ",有效sessionID = " + CommonUtil.toString(memSessionId));
                        if (requestMap.isServlet(param)) {
                            final RequestDispatcher rd = request.getRequestDispatcher(reLoginPage);
                            rd.forward(request, response);
                        } else {
                            final ResponseHeaderData responseHeaderData = new ResponseHeaderData();
                            responseHeaderData.setDataKind(ConstantsUtil.DataType.SINGLE);
                            responseHeaderData.setResCode(EngineExceptionEnum.UR_COM_LOGIN_REPEAT.getCode());
                            final ResponseHeader head = new ResponseHeader(responseHeaderData);
                            final ResponseData resData = new ResponseData(head);
                            resData.setClientType(param.clientType);
                            final BufferedWriter fout = new BufferedWriter(response.getWriter());
                            fout.write(HtmlUtil.createResponseHeader(resData));
                            fout.close();
                        }
                        session.invalidate();
                        return;
                    }
                    final String dataSource = ope.getDataSource();
                    if (CommonUtil.isNotEmpty(dataSource)) {
                        DynamicDataSource.setDb(dataSource);
                        sLog.info("doFilter", null, "用户session对应的数据源为 : " + dataSource);
                    }
                }
                
                chain.doFilter(request, response);
            }
            
        } catch (RichClientWebException e) {
            e.printStackTrace();
        }
    }

    /**
     * 过滤器初始化方法.
     * @param filterConfig filter对象
     * @throws ServletException ServletException
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        sLog = new LogUtil(RequestValidFilter.class);
        reLoginPage = config.getInitParameter("reLoginPage");
    }

    /**
     * 过滤器销毁的方法.
     * 
     */
    public void destroy() {
    }
}