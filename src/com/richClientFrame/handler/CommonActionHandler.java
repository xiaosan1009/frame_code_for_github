package com.richClientFrame.handler;

import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseBean;
import com.richClientFrame.exception.BreakProgressException;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.iface.CustomContentLoaderListener;
import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.info.ControlRequestMap;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.DateUtils;
import com.richClientFrame.util.FileUtil;
import com.richClientFrame.util.LogUtil;

import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： CommonActionHandler
 * 类描述 ： 
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Apr 19, 20138:12:04 PM
 * @author king
 */
public class CommonActionHandler {
    
    private static LogUtil sLog = new LogUtil(CommonActionHandler.class, true);
    
    /**
     * @Description: 调用自定制action
     * @author king
     * @since Apr 19, 2013 9:05:58 PM 
     * @version V1.0
     * @param dispCode 画面ID
     * @param cmdCode 方法ID
     * @return 调用结果
     * @throws BreakProgressException BreakProgressException
     * @throws RichClientWebException RichClientWebException
     * @throws NoSuchMethodException NoSuchMethodException
     * @throws InvocationTargetException InvocationTargetException
     * @throws IOException IOException
     * @throws IllegalAccessException IllegalAccessException
     */
    public static AbstractResponseData doAction(String dispCode, String cmdCode) 
        throws BreakProgressException, 
        RichClientWebException, 
        NoSuchMethodException, 
        InvocationTargetException, 
        IOException, 
        IllegalAccessException {
        final Param param = new Param();
        param.dispCode = dispCode;
        param.cmdCode = cmdCode;
        final SessionData seData = new SessionData();
        return doAction(null, null, null, param, seData);
    }
    
    /**
     * @Description: 调用自定制action
     * @author king
     * @since Apr 19, 2013 9:05:58 PM 
     * @version V1.0
     * @param param 请求对象
     * @return 调用结果
     * @throws BreakProgressException BreakProgressException
     * @throws RichClientWebException RichClientWebException
     * @throws NoSuchMethodException NoSuchMethodException
     * @throws InvocationTargetException InvocationTargetException
     * @throws IOException IOException
     * @throws IllegalAccessException IllegalAccessException
     */
    public static AbstractResponseData doAction(Param param) 
        throws BreakProgressException, 
        RichClientWebException, 
        NoSuchMethodException, 
        InvocationTargetException, 
        IOException, 
        IllegalAccessException {
        final SessionData seData = new SessionData();
        return doAction(null, null, null, param, seData);
    }
    
    /**
     * @Description: 调用自定制action
     * @author king
     * @since Apr 19, 2013 9:05:58 PM 
     * @version V1.0
     * @param param 请求对象
     * @param seData session对象
     * @return 调用结果
     * @throws BreakProgressException BreakProgressException
     * @throws RichClientWebException RichClientWebException
     * @throws NoSuchMethodException NoSuchMethodException
     * @throws InvocationTargetException InvocationTargetException
     * @throws IOException IOException
     * @throws IllegalAccessException IllegalAccessException
     */
    public static AbstractResponseData doAction(Param param, SessionData seData) 
        throws BreakProgressException, 
        RichClientWebException, 
        NoSuchMethodException, 
        InvocationTargetException, 
        IOException, 
        IllegalAccessException {
        return doAction(null, null, null, param, seData);
    }
    
    /**
     * @Description: 调用自定制action
     * @author king
     * @since Apr 19, 2013 9:05:58 PM 
     * @version V1.0
     * @param context 容器对象
     * @param request 请求对象
     * @param response 响应对象
     * @param param 请求对象
     * @param seData session对象
     * @return 调用结果
     * @throws BreakProgressException BreakProgressException
     * @throws RichClientWebException RichClientWebException
     * @throws NoSuchMethodException NoSuchMethodException
     * @throws InvocationTargetException InvocationTargetException
     * @throws IOException IOException
     * @throws IllegalAccessException IllegalAccessException
     */
    public static AbstractResponseData doAction(
            ServletContext context, 
            HttpServletRequest request, 
            HttpServletResponse response, 
            Param param, SessionData seData) 
        throws BreakProgressException, 
            RichClientWebException, 
            NoSuchMethodException, 
            InvocationTargetException, 
            IOException, 
            IllegalAccessException {
        
        AbstractResponseData resData = null;
        // 取得类名
        final String handlerName = ControlRequestMap.getInstance().getHanlder(param);
        // 取得方法名
        String methodName = ControlRequestMap.getInstance().getMethod(param);
        
        sLog.info("doAction", "", "handlerName = " + handlerName, "methodName = " + methodName);
        
        final ApplicationContext wac = CustomContentLoaderListener.getApplicationContext();
        final Object handlerObj = wac.getBean(handlerName);
        final Class<? extends Object> handlerClazz = handlerObj.getClass();
        
        if (context != null && request != null && response != null) {
            // 生成方法对象
            final Method initMethod = handlerClazz.getMethod("handlerInit", 
                    new Class[]{ServletContext.class, HttpServletRequest.class, 
                        HttpServletResponse.class, SessionData.class,});
            // 执行方法
            initMethod.invoke(handlerObj, new Object[]{context, request, response, seData});
            
            // 自定义servlet
            if (ControlRequestMap.getInstance().hasServlet(param)) {
                methodName = ControlRequestMap.getInstance().getServletMethod(param);
                if (CommonUtil.isEmpty(methodName)) {
                    methodName = "execute";
                }
                Object executeObj = null;
                try {
                    // 生成方法对象
                    final Method method = handlerClazz.getMethod(methodName, new Class[]{});
                    // 执行方法
                    executeObj = method.invoke(handlerObj, new Object[]{});
                } catch (NoSuchMethodException e) {
                    sLog.info("doAction", "自定义服务的方法没有被改造。");
                    // 生成方法对象
                    final Method method = handlerClazz.getMethod(methodName, 
                            new Class[]{HttpServletRequest.class, 
                                HttpServletResponse.class, SessionData.class,});
                    // 执行方法
                    executeObj = method.invoke(
                            handlerObj, new Object[]{request, response, seData});
                }
                // 返回值为[后续是否进行画面迁移操作]，如果为true：则不进行xml配置中的画面迁移操作
                if (executeObj != null) {
                    final boolean executeFlg = (Boolean)executeObj;
                    if (executeFlg) {
                        throw new BreakProgressException();
                    }
                } else {
                    throw new BreakProgressException();
                }
            } else {
                // 生成方法对象
                final Method method = handlerClazz.getMethod(
                        methodName, new Class[]{Param.class, SessionData.class});
                // 执行方法
                resData = (AbstractResponseData)method.invoke(handlerObj, new Object[]{param, seData});
            }
        } else {
            // 生成方法对象
            final Method method = handlerClazz.getMethod(
                    methodName, new Class[]{Param.class, SessionData.class});
            // 执行方法
            resData = (AbstractResponseData)method.invoke(handlerObj, new Object[]{param, seData});
        }
        
        ResponseBean responseBean = null;
        if (resData != null) {
            responseBean = resData.getResponseBean();
        }
        // 生成每次请求结束的切面方法对象
        final Method endHandlerMethod = handlerClazz.getMethod(
                "handlerFinished", new Class[]{Param.class, SessionData.class, ResponseBean.class});
        endHandlerMethod.invoke(handlerObj, new Object[]{param, seData, responseBean});
        
        if (resData != null) {
            // excel相应的操作
            if (resData.getResExcel() != null) {
                response.reset();
                response.setContentType("octets/stream");
                final String fileNameString = CommonUtil.toUtf8String(resData.getResExcel().getTitle());
                if (resData.getResExcel().getFileList() != null 
                        && resData.getResExcel().getFileList().size() > 0) {
                    String zipPath = ControlConfig.getRealPath() + "temp" 
                        + File.separator + DateUtils.getNowTime(DateUtils.FORMAT_YMD2);
                    zipPath += File.separator + fileNameString;
                    FileUtil.makeFilesToZipForDownload(
                            response, resData.getResExcel().getFileList().toArray(new File[0]), zipPath);
                } else {
                    response.setHeader("Content-Disposition", "filename=" 
                            + fileNameString);
                    resData.getResExcel().getWorkbook().write(response.getOutputStream());
                    response.getOutputStream().close();
                }
                throw new BreakProgressException();
            // 直接返回数据，接口调用的场合
            } else if (CommonUtil.isNotEmpty(resData.getOutPut())) {
                sLog.info("dispatch", "OutPut", "outPut = " + resData.getOutPut());
                response.setContentType("text/html;charset=UTF-8");
                final PrintWriter out = response.getWriter();
                out.print(resData.getOutPut());
                out.flush();
                out.close();
                throw new BreakProgressException();
            }
        }
        return resData;
    }

}
