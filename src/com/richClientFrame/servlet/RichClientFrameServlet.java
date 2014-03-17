package com.richClientFrame.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * ¿ò¼Ü¹²Í¨servlet.
 * @author King
 * @since 2011.07.24
 */
public class RichClientFrameServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    private IFrameServletFace service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        final WebApplicationContext wac = WebApplicationContextUtils
        .getRequiredWebApplicationContext(config.getServletContext());
        service = (IFrameServletFace)wac.getBean(getInitParameter("service"));
        service.init(config);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        service.doGet(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        service.doPost(req, resp);
    }
    
}
