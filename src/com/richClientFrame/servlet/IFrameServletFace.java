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
 * servlet共通接口.
 * @author king
 * @since 2010.07.28
 */
public interface IFrameServletFace {
    
    /**
     * servlet 初始化方法.
     * @param config 服务器对象
     * @throws ServletException ServletException
     */
    void init(ServletConfig config) throws ServletException;
    
    /**
     * get请求.
     * @param request 客户端请求对象
     * @param response 服务器相应对象
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
    /**
     * post请求.
     * @param request 客户端请求对象
     * @param response 服务器相应对象
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
    /**
     * post请求.
     * @param request 客户端请求对象
     * @param response 服务器相应对象
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
    /**
     * servlet 初始化方法.
     * @param context 服务器对象
     * @throws RichClientWebException RichClientWebException
     */
    void initialize(ServletContext context) throws RichClientWebException;
    
    /**
     * 资源文件路径取得.
     * @return 资源文件路径
     */
    String getResourcePath();
    
    /**
     * 是否为用户登出操作.
     * @param param 客户端请求对象
     * @return 判断结果
     * @throws RichClientWebException RichClientWebException
     */
    boolean isLogout(Param param) throws RichClientWebException;
}
