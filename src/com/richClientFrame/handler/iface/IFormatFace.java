package com.richClientFrame.handler.iface;

import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.ContorlMemcached;
import com.richClientFrame.service.IDbService;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： IFormatFace
 * 类描述 ： 引擎参数格式化接口
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.05.30
 * @author king
 */
public interface IFormatFace {
    
    /**
     * @Description: 
     * @author king
     * @since May 10, 2013 11:22:08 AM 
     * @version V1.0
     * @param value value
     * @param method method
     * @param params params
     * @return value
     * @throws RichClientWebException RichClientWebException
     */
    String execute(String value, String method, String params) throws RichClientWebException;
    
    /**
     * 设置request对象.
     * 
     * @param param request
     */
    void setParam(Param param);
    
    /**
     * 设置session对象.
     * 
     * @param seData session
     */
    void setSeData(SessionData seData);
    
    /**
     * 设置缓存服务器对象.
     * 
     * @param memcached 缓存服务器对象
     */
    void setMemcached(ContorlMemcached memcached);
    
    /**
     * 设置数据库操作对象.
     * 
     * @param db 数据库操作对象
     */
    void setDb(IDbService db);


}
