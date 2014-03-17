package com.richClientFrame.handler.iface;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseBean;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.ContorlMemcached;
import com.richClientFrame.service.IDbService;

import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� IExpandFace
 * ������ �� ������չ�¼��ӿ�
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.05.30
 * @author king
 */
public interface IExpandFace {
    
    /**
     * sql������չ����.
     * 
     * @param whereMap ��������
     * @throws RichClientWebException RichClientWebException
     */
    void createCondition(Map<String, Object> whereMap) throws RichClientWebException;
    
    /**
     * update����֮������淽����չ����.
     * 
     * @param tab ��������
     * @throws RichClientWebException RichClientWebException
     */
    void postUpdate(TableRowMap tab) throws RichClientWebException;
    
    /**
     * @Description: service����֮������淽��
     * @author king
     * @since Sep 29, 2013 2:59:34 PM 
     * @version V1.0
     * 
     * @param param param
     * @param session session
     * @param responseBean responseBean
     * @throws RichClientWebException RichClientWebException
     */
    void postDoService(Param param, SessionData session, ResponseBean responseBean) throws RichClientWebException;
    
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
     * �����ⲿ�������.
     * 
     * @param external �ⲿ�������
     */
    void setExternal(IExternalFace external);
    
    /**
     * ���ø�ʽ������.
     * 
     * @param format ʽ������
     */
    void setFormat(IFormatFace format);
    
    /**
     * ��������Դ�л�����.
     * 
     * @param dataSource ����Դ�л�����
     */
    void setDataSource(IDataSourceFace dataSource);
    
    /**
     * �������ݿ��������.
     * 
     * @param db ���ݿ��������
     */
    void setDb(IDbService db);

}
