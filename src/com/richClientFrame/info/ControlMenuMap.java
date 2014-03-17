package com.richClientFrame.info;

import com.richClientFrame.data.menu.MenuInformation;
import com.richClientFrame.exception.RichClientWebException;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ControlMenuMap
 * ������ �� ϵͳ����ķַ�xml������.�����������request.xmlһ��
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2010.07.28
 * @author king
 */
public final class ControlMenuMap {

    /**
     * �������
     */
    private static ControlMenuMap sMe;
    
    /**
     * �˵���Ϣ
     */
    private MenuInformation menuInfo;
    
    /**
     * ·��
     */
    private static String sPath;
    
    /**
     * ���캯��
     * @throws RichClientWebException RichClientWebException
     */
    private ControlMenuMap() throws RichClientWebException {
        // ��ȡ�ļ�
        menuInfo = MenuConfigReader.read(sPath);
        
    }
    
    /**
     * �����������.
     * @return RequestMap �������
     * @throws RichClientWebException RichClientWebException
     */
    public static ControlMenuMap getInstance() throws RichClientWebException {
        
        synchronized (ControlMenuMap.class) {
            if (sMe == null) {
                sMe = new ControlMenuMap();
            }
        }
        return sMe;
    }

    /**
     * request.xml·������.
     * 
     * @param path request.xml·��
     */
    public static void setPath(String path) {
        sPath = path;
    }

    /**
     * �˵���Ϣȡ��.
     * 
     * @return �˵���Ϣ
     */
    public MenuInformation getMenuInfo() {
        return menuInfo;
    }
    
}
