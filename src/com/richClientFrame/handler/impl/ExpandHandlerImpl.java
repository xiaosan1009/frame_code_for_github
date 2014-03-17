package com.richClientFrame.handler.impl;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseBean;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractExpandHandler;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ExpandHandlerImpl
 * 类描述 ： 引擎扩展方法接口实现类
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.05.30
 * @author king
 */
public class ExpandHandlerImpl extends AbstractExpandHandler {
    
    /**
     * update操作之后的切面方法扩展方法.
     * 
     * @param tab 条件容器
     * @throws RichClientWebException RichClientWebException
     */
    public void postUpdate(TableRowMap tab) 
        throws RichClientWebException {
    }
    
    /**
     * @Description: service请求之后的切面方法
     * @author king
     * @since Sep 29, 2013 2:59:34 PM 
     * @version V1.0
     * 
     * @param param param
     * @param session session
     * @param responseBean responseBean
     * @throws RichClientWebException RichClientWebException
     */
    public void postDoService(Param param, SessionData session, ResponseBean responseBean) 
        throws RichClientWebException {
        
    }

}
