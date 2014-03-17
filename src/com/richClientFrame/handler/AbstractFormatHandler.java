package com.richClientFrame.handler;

import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.handler.iface.IFormatFace;
import com.richClientFrame.info.ContorlMemcached;
import com.richClientFrame.service.IDbService;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;
import com.richClientFrame.util.xml.RequestConfigUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： AbstractFormatHandler
 * 类描述 ： 引擎参数格式化接口抽象类
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2011.11.23
 * @author king
 */
public abstract class AbstractFormatHandler implements IFormatFace {


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
     * 共通执行方法.
     * @param value 需要转换的值
     * @param method 方法名
     * @param params 参数
     * @return 转换的值
     * @throws RichClientWebException RichClientWebException
     */
    public String execute(String value, String method, String params) throws RichClientWebException {
        final Class<? extends Object> handlerClazz = this.getClass();
        String returnValue = ConstantsUtil.Str.EMPTY;
        String[] paramStrings = null;
        if (CommonUtil.isNotEmpty(params)) {
            final String[] methodStrings = params.split(ConstantsUtil.Str.BLANK);
            paramStrings = new String[methodStrings.length];
            for (int i = 0; i < methodStrings.length; i++) {
                paramStrings[i] = RequestConfigUtil.getRequestValue(null, request, session, methodStrings[i]);
            }
        }
        try {
            final Method executeMethod = handlerClazz.getMethod(method, new Class[]{String.class, String[].class});
            returnValue = CommonUtil.toString(executeMethod.invoke(this, new Object[]{value, paramStrings}));
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
        return returnValue;
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
    
}
