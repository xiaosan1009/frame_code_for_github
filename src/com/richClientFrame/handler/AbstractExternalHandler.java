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
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� AbstractExternalHandler
 * ������ �� �����ⲿϵͳ�ӿ��¼��ӿڳ�����
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2011.11.23
 * @author king
 */
public abstract class AbstractExternalHandler implements IExternalFace {
    
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
     * �����б���Ϣ.
     */
    protected List<TableRowMap> tabs;
    
    /**
     * ������Ϣ.
     */
    protected TableRowMap tab;
    
    /**
     * ����������Ϣ.
     */
    protected String method;

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

    /**
     * ��ִͨ�з���.
     * @param tabs �����б���Ϣ
     * @param method ������
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
     * ��ִͨ�з���.
     * @param tab ������Ϣ
     * @param method ������
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
