package com.richClientFrame.handler.impl;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractFormatHandler;
import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.util.KeyedDigestMD5;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� FormatHandlerImpl
 * ������ �� ���������ʽ���ӿ�ʵ����
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Oct 5, 2012 10:53:23 AM 
 * @author king
 */
public class FormatHandlerImpl extends AbstractFormatHandler {
    
    /** 
    * @Description: �������md5ת��
    * @author king
    * @since Oct 5, 2012 10:53:50 AM 
    * 
    * @param value �������ֵ
    * @param params ����
    * @return md5ת������������
    * @throws RichClientWebException RichClientWebException
    */
    public String md5(String value, String[] params) throws RichClientWebException {
        final String key = ControlConfig.getInstance().getConfiguration().getMd5Key();
        final String md5 = KeyedDigestMD5.getKeyedDigest(value, key);
        return md5;
    }
    
    /**
     * ����ת��������.
     * @param value Ҫת��������
     * @param params ����
     * @return ת��������
     */
    public String dateToNum(String value, String[] params) {
        value = value.replaceAll("-", "");
        value = value.replaceAll("/", "");
        value = value.replaceAll(":", "");
        value = value.replaceAll(" ", "");
        return value;
    }

}
