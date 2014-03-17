package com.richClientFrame.handler.iface;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.ContorlMemcached;
import com.richClientFrame.service.IDbService;

import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： IExternalFace
 * 类描述 ： 引擎外部系统接口事件接口
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2011.05.30
 * @author king
 */
public interface IExternalFace {
    
    void execute(List<TableRowMap> tabs, String method) throws RichClientWebException;
    
    void execute(TableRowMap tab, String method) throws RichClientWebException;
    
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
