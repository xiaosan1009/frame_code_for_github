package com.richClientFrame.handler.iface;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.ContorlMemcached;
import com.richClientFrame.service.IDbService;

import java.util.List;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� IExternalFace
 * ������ �� �����ⲿϵͳ�ӿ��¼��ӿ�
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.05.30
 * @author king
 */
public interface IExternalFace {
    
    void execute(List<TableRowMap> tabs, String method) throws RichClientWebException;
    
    void execute(TableRowMap tab, String method) throws RichClientWebException;
    
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
