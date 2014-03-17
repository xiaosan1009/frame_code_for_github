package com.richClientFrame.handler.iface;

import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.ContorlMemcached;
import com.richClientFrame.service.IDbService;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� IDataSourceFace
 * ������ �� ��������Դ�л��ӿ�
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.05.30
 * @author king
 */
public interface IDataSourceFace {
    
    String execute(String method) throws RichClientWebException;
    
    void preExecute(String method) throws RichClientWebException;
    
    /**
     * ����request����.
     * 
     * @param param request
     */
    void setParam(Param param);
    
    /**
     * ����session����.
     * 
     * @param seData session
     */
    void setSeData(SessionData seData);
    
    /**
     * ���û������������.
     * 
     * @param memcached �������������
     */
    void setMemcached(ContorlMemcached memcached);
    
    /**
     * �������ݿ��������.
     * 
     * @param db ���ݿ��������
     */
    void setDb(IDbService db);


}
