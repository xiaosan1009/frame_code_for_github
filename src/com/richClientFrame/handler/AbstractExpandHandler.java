package com.richClientFrame.handler;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.iface.IDataSourceFace;
import com.richClientFrame.handler.iface.IExpandFace;
import com.richClientFrame.handler.iface.IExternalFace;
import com.richClientFrame.handler.iface.IFormatFace;
import com.richClientFrame.info.ContorlMemcached;
import com.richClientFrame.service.IDbService;
import com.richClientFrame.util.LogUtil;

import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： AbstractExpandHandler
 * 类描述 ： 引擎扩展事件接口抽象类
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2011.10.01
 * @author king
 */
public abstract class AbstractExpandHandler implements IExpandFace {
    
    /**
     * 缓存服务器对象.
     */
    protected ContorlMemcached memcached;
    
    /**
     * 外部处理对象.
     */
    protected IExternalFace external;
    
    /**
     * 格式化对象.
     */
    protected IFormatFace format;
    
    /**
     * 数据源切换对象.
     */
    protected IDataSourceFace dataSource;
    
    /**
     * 客户端请求对象.
     */
    protected Param request;
    
    /**
     * 客户端session对象.
     */
    protected SessionData session;
    
    /**
     * 数据库操作对象.
     */
    protected IDbService db;
    
    /**
     * log对象.
     */
    protected LogUtil log = new LogUtil(this.getClass());

    public void createCondition(Map<String, Object> whereMap) 
        throws RichClientWebException {
        if (session != null && session.getUserInfo() != null) {
            final String userId = session.getUserInfo().getUserId();
            whereMap.put("OPERATOR_ID", userId);
        }
    }

    /**
     * 缓存服务器对象设置.
     * @param memcach 缓存服务器对象
     */
    public void setMemcached(ContorlMemcached memcach) {
        this.memcached = memcach;
    }

    /**
     * 客户端请求对象设置.
     * @param param 客户端请求对象
     */
    public void setParam(Param param) {
        this.request = param;
    }

    /**
     * 客户端session对象设置.
     * @param seData 客户端session对象
     */
    public void setSeData(SessionData seData) {
        this.session = seData;
    }

    /**
     * 外部处理对象设置.
     * @param external 外部处理对象
     */
    public void setExternal(IExternalFace external) {
        this.external = external;
    }

    /**
     * 数据库操作对象设置.
     * 
     * @param db 数据库操作对象
     */
    public void setDb(IDbService db) {
        this.db = db;
    }

    /**
     * 改变sql条件数据.
     * 
     * @param whereMap 条件
     * @param index sql执行标识
     * @param id sql执行标识
     */
    public void onCheckPreExecute(Map<String, Object> whereMap, String id, String index) {
    }

    /**
     * 进行验证.
     * 
     * @param tab 数据
     * @param id sql执行标识
     * @param index sql执行标识
     * @return 验证结果【true：验证通过；false：验证失败】
     */
    public boolean onCheckPostExecute(TableRowMap tab, String id, String index) {
        return true;
    }

    /**
     * 格式化对象设置.
     * 
     * @param format 格式化对象
     */
    public void setFormat(IFormatFace format) {
        this.format = format;
    }

    /**
     * 数据源切换对象设置.
     * 
     * @param dataSourceFace 数据源切换对象
     */
    public void setDataSource(IDataSourceFace dataSourceFace) {
        this.dataSource = dataSourceFace;
    }

}
