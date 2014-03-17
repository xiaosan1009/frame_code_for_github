package com.richClientFrame.handler;

import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.handler.iface.IDataSourceFace;
import com.richClientFrame.info.ContorlMemcached;
import com.richClientFrame.service.IDbService;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� AbstractDataSourceHandler
 * ������ �� ��������Դ�л��ӿڳ�����
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2011.10.01
 * @author king
 */
public abstract class AbstractDataSourceHandler implements IDataSourceFace {


    /**
     * �������������.
     */
    protected ContorlMemcached memcached;
    
    /**
     * �ͻ����������.
     */
    protected Param request;
    
    /**
     * �ͻ���session����.
     */
    protected SessionData session;
    
    /**
     * ���ݿ��������.
     */
    protected IDbService db;
    
    /**
     * log����.
     */
    protected LogUtil log = new LogUtil(this.getClass());
    
    /**
     * ��ִͨ�з���.
     * @param method ������
     * @return ת����ֵ
     * @throws RichClientWebException RichClientWebException
     */
    public String execute(String method) throws RichClientWebException {
        log.info("execute", "start", "method = " + method);
        final Class<? extends Object> handlerClazz = this.getClass();
        String returnValue = ConstantsUtil.Str.EMPTY;
        try {
            final Method executeMethod = handlerClazz.getMethod(method, new Class[]{});
            returnValue = CommonUtil.toString(executeMethod.invoke(this, new Object[]{}));
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
        log.info("execute", "end", "returnValue = " + returnValue);
        return returnValue;
    }
    
    /**
     * ��ִͨ�з���.
     * @param method ������
     * @throws RichClientWebException RichClientWebException
     */
    public void preExecute(String method) throws RichClientWebException {
        log.info("preExecute", "start", "method = " + method);
        final Class<? extends Object> handlerClazz = this.getClass();
        try {
            final Method executeMethod = handlerClazz.getMethod(method + "Pre", new Class[]{});
            executeMethod.invoke(this, new Object[]{});
        } catch (SecurityException e) {
            log.fatal(e.toString(), e);
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        } catch (NoSuchMethodException e) {
            log.info("preExecute", null, "����Դ�л���ķ�����" + method + "û��pre����");
//            log.fatal(e.toString(), e);
//            e.printStackTrace();
//            throw new RichClientWebException(ConstantsUtil.Result.SYSTEM_ERROR, e);
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
        log.info("preExecute", "end");
    }

    /**
     * ���ݿ������������.
     * 
     * @param db ���ݿ��������
     */
    public void setDb(IDbService db) {
        this.db = db;
    }

    /**
     * �����������������.
     * @param memcached �������������
     */
    public void setMemcached(ContorlMemcached memcached) {
        this.memcached = memcached;
    }

    /**
     * �ͻ��������������.
     * @param param �ͻ����������
     */
    public void setParam(Param param) {
        this.request = param;
    }

    /**
     * �ͻ���session��������.
     * @param seData �ͻ���session����
     */
    public void setSeData(SessionData seData) {
        this.session = seData;
    }
    
}
