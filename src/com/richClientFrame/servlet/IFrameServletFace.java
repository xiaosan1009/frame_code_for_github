package com.richClientFrame.servlet;

import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * servlet��ͨ�ӿ�.
 * @author king
 * @since 2010.07.28
 */
public interface IFrameServletFace {
    
    /**
     * servlet ��ʼ������.
     * @param config ����������
     * @throws ServletException ServletException
     */
    void init(ServletConfig config) throws ServletException;
    
    /**
     * get����.
     * @param request �ͻ����������
     * @param response ��������Ӧ����
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
    /**
     * post����.
     * @param request �ͻ����������
     * @param response ��������Ӧ����
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
    /**
     * post����.
     * @param request �ͻ����������
     * @param response ��������Ӧ����
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
    /**
     * servlet ��ʼ������.
     * @param context ����������
     * @throws RichClientWebException RichClientWebException
     */
    void initialize(ServletContext context) throws RichClientWebException;
    
    /**
     * ��Դ�ļ�·��ȡ��.
     * @return ��Դ�ļ�·��
     */
    String getResourcePath();
    
    /**
     * �Ƿ�Ϊ�û��ǳ�����.
     * @param param �ͻ����������
     * @return �жϽ��
     * @throws RichClientWebException RichClientWebException
     */
    boolean isLogout(Param param) throws RichClientWebException;
}
