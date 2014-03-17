/**
 * 
 */
package com.richClientFrame.servlet;

import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


/**
 * session������.
 * @author King
 * @since 2010.03.24
 */
public class SessionManagement implements HttpSessionBindingListener {
    
    /**
     * session����KEY.
     */
    public static final String KEY_SESSIONMNG = "seMng";
    
    /**
     * Log
     */
    private LogUtil log = new LogUtil(SessionManagement.class);
    
    /**
     * �û�session 
     */
    private static final String USER_SESSION_DIRNAME = "userSessionLog";
    
    
    /**
     * ���캯��.
     */
    public SessionManagement() {
        super();
    }
    


    /**
     * session����ʼ.
     * @param arg0 HttpSessionBindingEvent session����
     * @see javax.servlet.http.HttpSessionBindingListener#valueBound
     * (javax.servlet.http.HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent arg0) {
        
        // session ID ȡ��
        final HttpSession session = arg0.getSession();        
        final String sessionId = session.getId();
        
        // session���ݸ���
        final SessionCountManagement sessionCounter = SessionCountManagement.getInstance();
        sessionCounter.countSession(true);
        
        // ����·��ȡ��
        final String strRootPath =  ControlConfig.getSesfPath();
        
        // session���ݴ��·��
        String filePath = strRootPath + sessionId;

        File dir = new File(filePath);
        try {
            FileUtils.forceMkdir(dir);
            
            filePath += ConstantsUtil.Str.SLASH + USER_SESSION_DIRNAME;
            
            dir = new File(filePath);
            
            FileUtils.forceMkdir(dir);
            
        } catch (IOException e) {
            log.fatal(e.toString());
        } catch (Exception e) {
            log.fatal(e.toString());
        }
    }

    /**
     * session���˴���.
     * @param arg0 HttpSessionBindingEvent session����
     * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound
     * (javax.servlet.http.HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent arg0) {
        
        // session ID ȡ��
        final HttpSession session = arg0.getSession();
        final String sessionId = session.getId();
        
        // session���ݸ���
        final SessionCountManagement sessionCounter = SessionCountManagement.getInstance();
        sessionCounter.countSession(false);
        
        // ����·��ȡ��
        final String strRootPath =  ControlConfig.getSesfPath();
        
        // session���ݴ��·��
        final String filePath = strRootPath + sessionId;
        File dir = null;
        dir = new File(filePath);
        if (dir != null) {
            try {
                if (dir.exists()) {
                    // �û�session�ļ����µ��������
                    FileUtils.cleanDirectory(dir);
                    // �û�session�ļ������
                    FileUtils.deleteDirectory(dir);
                }
            } catch (IOException e) {
                log.fatal(e.toString());
            } catch (Exception e) {
                log.fatal(e.toString());
            }
        }
    }
}
