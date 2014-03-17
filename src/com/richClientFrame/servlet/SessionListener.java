package com.richClientFrame.servlet;

import com.richClientFrame.data.SessionData;
import com.richClientFrame.info.ControlOperator;
import com.richClientFrame.util.ConstantsUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * session生成和销毁时的处理
 * @author King
 * @since 2010.06.20
 */
public class SessionListener implements HttpSessionListener {
	
	protected static final Log log = LogFactory.getLog(SessionListener.class);

	public SessionListener() {
		super();
	}

	public void sessionCreated(HttpSessionEvent arg0) {
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		
		HttpSession session = arg0.getSession();
		if (session == null) {
			return;
		}
		
		Object obj = session.getAttribute(SessionData.KEY_SESSIONDATA);
		if (obj == null) {
			return;
		}
		
		if (obj instanceof SessionData) {
			SessionData seData = (SessionData)obj;
			
			try {
				if (seData.getRemoteAddr() != null) {
					ControlOperator.getInstance().removeLoginOperator(seData.getRemoteAddr());
				}
			} catch (Exception e) {
				log.fatal(ConstantsUtil.ErrInfo.SYSTEM_ERROR + e.toString());
				e.printStackTrace();
			} finally {
			}
		}
	}
}
