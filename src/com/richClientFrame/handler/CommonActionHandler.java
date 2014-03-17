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
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� CommonActionHandler
 * ������ �� 
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Apr 19, 20138:12:04 PM
 * @author king
 */
public class CommonActionHandler {
    
    private static LogUtil sLog = new LogUtil(CommonActionHandler.class, true);
    
    /**
     * @Description: �����Զ���action
     * @author king
     * @since Apr 19, 2013 9:05:58 PM 
     * @version V1.0
     * @param dispCode ����ID
     * @param cmdCode ����ID
     * @return ���ý��
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
     * @Description: �����Զ���action
     * @author king
     * @since Apr 19, 2013 9:05:58 PM 
     * @version V1.0
     * @param param �������
     * @return ���ý��
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
     * @Description: �����Զ���action
     * @author king
     * @since Apr 19, 2013 9:05:58 PM 
     * @version V1.0
     * @param param �������
     * @param seData session����
     * @return ���ý��
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
     * @Description: �����Զ���action
     * @author king
     * @since Apr 19, 2013 9:05:58 PM 
     * @version V1.0
     * @param context ��������
     * @param request �������
     * @param response ��Ӧ����
     * @param param �������
     * @param seData session����
     * @return ���ý��
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
        // ȡ������
        final String handlerName = ControlRequestMap.getInstance().getHanlder(param);
        // ȡ�÷�����
        String methodName = ControlRequestMap.getInstance().getMethod(param);
        
        sLog.info("doAction", "", "handlerName = " + handlerName, "methodName = " + methodName);
        
        final ApplicationContext wac = CustomContentLoaderListener.getApplicationContext();
        final Object handlerObj = wac.getBean(handlerName);
        final Class<? extends Object> handlerClazz = handlerObj.getClass();
        
        if (context != null && request != null && response != null) {
            // ���ɷ�������
            final Method initMethod = handlerClazz.getMethod("handlerInit", 
                    new Class[]{ServletContext.class, HttpServletRequest.class, 
                        HttpServletResponse.class, SessionData.class,});
            // ִ�з���
            initMethod.invoke(handlerObj, new Object[]{context, request, response, seData});
            
            // �Զ���servlet
            if (ControlRequestMap.getInstance().hasServlet(param)) {
                methodName = ControlRequestMap.getInstance().getServletMethod(param);
                if (CommonUtil.isEmpty(methodName)) {
                    methodName = "execute";
                }
                Object executeObj = null;
                try {
                    // ���ɷ�������
                    final Method method = handlerClazz.getMethod(methodName, new Class[]{});
                    // ִ�з���
                    executeObj = method.invoke(handlerObj, new Object[]{});
                } catch (NoSuchMethodException e) {
                    sLog.info("doAction", "�Զ������ķ���û�б����졣");
                    // ���ɷ�������
                    final Method method = handlerClazz.getMethod(methodName, 
                            new Class[]{HttpServletRequest.class, 
                                HttpServletResponse.class, SessionData.class,});
                    // ִ�з���
                    executeObj = method.invoke(
                            handlerObj, new Object[]{request, response, seData});
                }
                // ����ֵΪ[�����Ƿ���л���Ǩ�Ʋ���]�����Ϊtrue���򲻽���xml�����еĻ���Ǩ�Ʋ���
                if (executeObj != null) {
                    final boolean executeFlg = (Boolean)executeObj;
                    if (executeFlg) {
                        throw new BreakProgressException();
                    }
                } else {
                    throw new BreakProgressException();
                }
            } else {
                // ���ɷ�������
                final Method method = handlerClazz.getMethod(
                        methodName, new Class[]{Param.class, SessionData.class});
                // ִ�з���
                resData = (AbstractResponseData)method.invoke(handlerObj, new Object[]{param, seData});
            }
        } else {
            // ���ɷ�������
            final Method method = handlerClazz.getMethod(
                    methodName, new Class[]{Param.class, SessionData.class});
            // ִ�з���
            resData = (AbstractResponseData)method.invoke(handlerObj, new Object[]{param, seData});
        }
        
        ResponseBean responseBean = null;
        if (resData != null) {
            responseBean = resData.getResponseBean();
        }
        // ����ÿ��������������淽������
        final Method endHandlerMethod = handlerClazz.getMethod(
                "handlerFinished", new Class[]{Param.class, SessionData.class, ResponseBean.class});
        endHandlerMethod.invoke(handlerObj, new Object[]{param, seData, responseBean});
        
        if (resData != null) {
            // excel��Ӧ�Ĳ���
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
            // ֱ�ӷ������ݣ��ӿڵ��õĳ���
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
