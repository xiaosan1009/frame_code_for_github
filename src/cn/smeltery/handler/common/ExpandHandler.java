package cn.smeltery.handler.common;

import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.response.ResponseBean;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.impl.ExpandHandlerImpl;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ExpandHandler
 * ������ �� 
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Sep 29, 2013 3:16:17 PM
 * @author king
 */
public class ExpandHandler extends ExpandHandlerImpl {
    
    @Override
    public void postDoService(Param param, SessionData session, ResponseBean responseBean) 
        throws RichClientWebException {
        if (!"0".equals(param.cmdCode)) {
            log.info("postDoService", "start", "disp = " + param.dispCode, "cmd = " + param.cmdCode);
        }
    }

}
