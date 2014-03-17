package com.richClientFrame.handler.iface;

import com.richClientFrame.util.LogUtil;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： CustomContentLoaderListener
 * 类描述 ： 
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Apr 19, 20138:33:21 PM
 * @author king
 */
public class CustomContentLoaderListener implements ServletContextListener {
    
    private static LogUtil log = new LogUtil(CustomContentLoaderListener.class, true);
    
    private static ApplicationContext sSpringContext;

    public void contextDestroyed(ServletContextEvent event) {
    }

    public void contextInitialized(ServletContextEvent event) {
        log.info("contextInitialized", "start");
        sSpringContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
    }
    
    /**
     * @Description: 获取spring容器对象
     * @author king
     * @since Apr 19, 2013 8:35:35 PM 
     * @version V1.0
     * @return spring容器对象
     */
    public static ApplicationContext getApplicationContext() {
        return sSpringContext;
    }
    
    /**
     * @Description: 设置spring容器对象
     * @author king
     * @since Apr 20, 2013 11:53:56 AM 
     * @version V1.0
     * @param applicationContext spring容器对象
     */
    public static void setApplicationContext(ApplicationContext applicationContext) {
        sSpringContext = applicationContext;
    }


}
