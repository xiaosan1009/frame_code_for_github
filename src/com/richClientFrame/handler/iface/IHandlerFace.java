package com.richClientFrame.handler.iface;

import com.richClientFrame.data.SessionData;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� IHandlerFace
 * ������ �� handler��ͨ�ӿ�
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Apr 15, 201310:37:16 PM
 * @author king
 */
public interface IHandlerFace {
    
    public void handlerInit(ServletContext context, HttpServletRequest request, 
            HttpServletResponse response, SessionData seData);

}
