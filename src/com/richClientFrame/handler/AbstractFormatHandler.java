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
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� AbstractFormatHandler
 * ������ �� ���������ʽ���ӿڳ�����
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2011.11.23
 * @author king
 */
public abstract class AbstractFormatHandler implements IFormatFace {


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
     * @param value ��Ҫת����ֵ
     * @param method ������
     * @param params ����
     * @return ת����ֵ
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
