package com.richClientFrame.handler.impl;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.response.AbstractResponseData;
import com.richClientFrame.data.response.ResponseBean;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractExpandHandler;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ExpandHandlerImpl
 * ������ �� ������չ�����ӿ�ʵ����
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.05.30
 * @author king
 */
public class ExpandHandlerImpl extends AbstractExpandHandler {
    
    /**
     * update����֮������淽����չ����.
     * 
     * @param tab ��������
     * @throws RichClientWebException RichClientWebException
     */
    public void postUpdate(TableRowMap tab) 
        throws RichClientWebException {
    }
    
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
    public void postDoService(Param param, SessionData session, ResponseBean responseBean) 
        throws RichClientWebException {
        
    }

}
