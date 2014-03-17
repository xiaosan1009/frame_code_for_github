package com.richClientFrame.handler.iface;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseBean;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.ContorlMemcached;
import com.richClientFrame.service.IDbService;

import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： IExpandFace
 * 类描述 ： 引擎扩展事件接口
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.05.30
 * @author king
 */
public interface IExpandFace {
    
    /**
     * sql条件扩展方法.
     * 
     * @param whereMap 条件容器
     * @throws RichClientWebException RichClientWebException
     */
    void createCondition(Map<String, Object> whereMap) throws RichClientWebException;
    
    /**
     * update操作之后的切面方法扩展方法.
     * 
     * @param tab 条件容器
     * @throws RichClientWebException RichClientWebException
     */
    void postUpdate(TableRowMap tab) throws RichClientWebException;
    
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
    void postDoService(Param param, SessionData session, ResponseBean responseBean) throws RichClientWebException;
    
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
     * 设置外部处理对象.
     * 
     * @param external 外部处理对象
     */
    void setExternal(IExternalFace external);
    
    /**
     * 设置格式化对象.
     * 
     * @param format 式化对象
     */
    void setFormat(IFormatFace format);
    
    /**
     * 设置数据源切换对象.
     * 
     * @param dataSource 数据源切换对象
     */
    void setDataSource(IDataSourceFace dataSource);
    
    /**
     * 设置数据库操作对象.
     * 
     * @param db 数据库操作对象
     */
    void setDb(IDbService db);

}
