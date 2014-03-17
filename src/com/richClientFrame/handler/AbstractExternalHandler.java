package com.richClientFrame.handler;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.handler.iface.IExternalFace;
import com.richClientFrame.info.ContorlMemcached;
import com.richClientFrame.service.IDbService;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： AbstractExternalHandler
 * 类描述 ： 引擎外部系统接口事件接口抽象类
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2011.11.23
 * @author king
 */
public abstract class AbstractExternalHandler implements IExternalFace {
    
    /**
     * 缓存服务器对象.
     */
    protected ContorlMemcached memcached;
    
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
    
    /**
     * 数据列表信息.
     */
    protected List<TableRowMap> tabs;
    
    /**
     * 数据信息.
     */
    protected TableRowMap tab;
    
    /**
     * 操作方法信息.
     */
    protected String method;

    /**
     * 数据库操作对象设置.
     * 
     * @param db 数据库操作对象
     */
    public void setDb(IDbService db) {
        this.db = db;
    }

    /**
     * 缓存服务器对象设置.
     * @param memcached 缓存服务器对象
     */
    public void setMemcached(ContorlMemcached memcached) {
        this.memcached = memcached;
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
     * 共通执行方法.
     * @param tabs 数据列表信息
     * @param method 方法名
     * @throws RichClientWebException RichClientWebException
     */
    public void execute(List<TableRowMap> tabs, String method) throws RichClientWebException {
        this.tabs = tabs;
        final Class<? extends Object> handlerClazz = this.getClass();
        
        try {
            final Method executeMethod = handlerClazz.getMethod(method, new Class[]{});
            executeMethod.invoke(this, new Object[]{});
        } catch (SecurityException e) {
            log.fatal(e.toString(), e);
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        } catch (NoSuchMethodException e) {
            log.fatal(e.toString(), e);
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        } catch (IllegalArgumentException e) {
            log.fatal(e.toString(), e);
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        } catch (IllegalAccessException e) {
            log.fatal(e.toString(), e);
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        } catch (InvocationTargetException e) {
            log.fatal(e.toString(), e);
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        }
    }
    
    /**
     * 共通执行方法.
     * @param tab 数据信息
     * @param method 方法名
     * @throws RichClientWebException RichClientWebException
     */
    public void execute(TableRowMap tab, String method) throws RichClientWebException {
        this.tab = tab;
        final Class<? extends Object> handlerClazz = this.getClass();
        
        try {
            final Method executeMethod = handlerClazz.getMethod(method, new Class[]{});
            executeMethod.invoke(this, new Object[]{});
        } catch (SecurityException e) {
            log.fatal(e.toString(), e);
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        } catch (NoSuchMethodException e) {
            log.fatal(e.toString(), e);
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        } catch (IllegalArgumentException e) {
            log.fatal(e.toString(), e);
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        } catch (IllegalAccessException e) {
            log.fatal(e.toString(), e);
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        } catch (InvocationTargetException e) {
            log.fatal(e.toString(), e);
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        }
    }

}
