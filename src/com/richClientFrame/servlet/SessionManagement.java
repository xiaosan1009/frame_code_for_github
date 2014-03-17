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
 * session管理类.
 * @author King
 * @since 2010.03.24
 */
public class SessionManagement implements HttpSessionBindingListener {
    
    /**
     * session储存KEY.
     */
    public static final String KEY_SESSIONMNG = "seMng";
    
    /**
     * Log
     */
    private LogUtil log = new LogUtil(SessionManagement.class);
    
    /**
     * 用户session 
     */
    private static final String USER_SESSION_DIRNAME = "userSessionLog";
    
    
    /**
     * 构造函数.
     */
    public SessionManagement() {
        super();
    }
    


    /**
     * session处理开始.
     * @param arg0 HttpSessionBindingEvent session对象
     * @see javax.servlet.http.HttpSessionBindingListener#valueBound
     * (javax.servlet.http.HttpSessionBindingEvent)
     */
    public void valueBound(HttpSessionBindingEvent arg0) {
        
        // session ID 取得
        final HttpSession session = arg0.getSession();        
        final String sessionId = session.getId();
        
        // session数据更新
        final SessionCountManagement sessionCounter = SessionCountManagement.getInstance();
        sessionCounter.countSession(true);
        
        // 工程路径取得
        final String strRootPath =  ControlConfig.getSesfPath();
        
        // session数据存放路径
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
     * session终了处理.
     * @param arg0 HttpSessionBindingEvent session对象
     * @see javax.servlet.http.HttpSessionBindingListener#valueUnbound
     * (javax.servlet.http.HttpSessionBindingEvent)
     */
    public void valueUnbound(HttpSessionBindingEvent arg0) {
        
        // session ID 取得
        final HttpSession session = arg0.getSession();
        final String sessionId = session.getId();
        
        // session数据更新
        final SessionCountManagement sessionCounter = SessionCountManagement.getInstance();
        sessionCounter.countSession(false);
        
        // 工程路径取得
        final String strRootPath =  ControlConfig.getSesfPath();
        
        // session数据存放路径
        final String filePath = strRootPath + sessionId;
        File dir = null;
        dir = new File(filePath);
        if (dir != null) {
            try {
                if (dir.exists()) {
                    // 用户session文件夹下的数据清除
                    FileUtils.cleanDirectory(dir);
                    // 用户session文件夹清除
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
