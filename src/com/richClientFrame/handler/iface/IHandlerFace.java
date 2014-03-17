package com.richClientFrame.handler.iface;

import com.richClientFrame.data.SessionData;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： IHandlerFace
 * 类描述 ： handler共通接口
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Apr 15, 201310:37:16 PM
 * @author king
 */
public interface IHandlerFace {
    
    public void handlerInit(ServletContext context, HttpServletRequest request, 
            HttpServletResponse response, SessionData seData);

}
