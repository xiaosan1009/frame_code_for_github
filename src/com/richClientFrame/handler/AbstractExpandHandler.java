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
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� AbstractExpandHandler
 * ������ �� ������չ�¼��ӿڳ�����
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2011.10.01
 * @author king
 */
public abstract class AbstractExpandHandler implements IExpandFace {
    
    /**
     * �������������.
     */
    protected ContorlMemcached memcached;
    
    /**
     * �ⲿ�������.
     */
    protected IExternalFace external;
    
    /**
     * ��ʽ������.
     */
    protected IFormatFace format;
    
    /**
     * ����Դ�л�����.
     */
    protected IDataSourceFace dataSource;
    
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

    public void createCondition(Map<String, Object> whereMap) 
        throws RichClientWebException {
        if (session != null && session.getUserInfo() != null) {
            final String userId = session.getUserInfo().getUserId();
            whereMap.put("OPERATOR_ID", userId);
        }
    }

    /**
     * �����������������.
     * @param memcach �������������
     */
    public void setMemcached(ContorlMemcached memcach) {
        this.memcached = memcach;
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
     * �ⲿ�����������.
     * @param external �ⲿ�������
     */
    public void setExternal(IExternalFace external) {
        this.external = external;
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
     * �ı�sql��������.
     * 
     * @param whereMap ����
     * @param index sqlִ�б�ʶ
     * @param id sqlִ�б�ʶ
     */
    public void onCheckPreExecute(Map<String, Object> whereMap, String id, String index) {
    }

    /**
     * ������֤.
     * 
     * @param tab ����
     * @param id sqlִ�б�ʶ
     * @param index sqlִ�б�ʶ
     * @return ��֤�����true����֤ͨ����false����֤ʧ�ܡ�
     */
    public boolean onCheckPostExecute(TableRowMap tab, String id, String index) {
        return true;
    }

    /**
     * ��ʽ����������.
     * 
     * @param format ��ʽ������
     */
    public void setFormat(IFormatFace format) {
        this.format = format;
    }

    /**
     * ����Դ�л���������.
     * 
     * @param dataSourceFace ����Դ�л�����
     */
    public void setDataSource(IDataSourceFace dataSourceFace) {
        this.dataSource = dataSourceFace;
    }

}
