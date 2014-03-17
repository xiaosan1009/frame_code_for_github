package com.richClientFrame.info;

import java.io.File;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.ConstantsUtil;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ControlConfig
 * ������ �� ����������Ϣ������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2010.03.19
 * @author king
 */
public final class ControlConfig {
    
    /**
     * XML�ļ�·��.
     */
    public static final String DEF_XMLDIR = "WEB-INF" 
        + File.separator + ConstantsUtil.Str.EXTENSION_XML;
    
    /**
     * ����session���ļ���.
     */
    public static final String DEF_SESDIR = "WEB-INF" 
        + File.separator + "seswork" + File.separator;
    
    /**
     * HTMLģ����ļ���.
     */
    public static final String DEF_TPLDIR = "WEB-INF" 
        + File.separator + "templates" + File.separator;

    /**
     * PDF�ļ�������ļ���.
     */
    public static final String DEF_PDFDIR = "pdf" + File.separator;
    
    /**
     * Javascript���ļ���.
     */
    public static final String DEF_JSDIR = "js" + File.separator;
    
    /**
     * ͼƬ���ļ���.
     */
    public static final String DEF_IMGDIR = "img" + File.separator;
    
    /**
     * �ϴ����ļ���.
     */
    public static final String DEF_UPLOADDIR = "upload" + File.separator;

    /**
     * ���캯��.
     */
    private ControlConfig() {
        super();
        // ����������Ϣ��ȡ��
        this.config = ConfigurationReader.read(sConfPath);
    }

    /**
     * ��������ȡ��.
     * @return ControlBaConfig �������
     * @throws RichClientWebException RichClientWebException
     */
    public static ControlConfig getInstance() {
        
        synchronized (ControlConfig.class) {
            if (sMe == null && sConfPath != null) {
                sMe = new ControlConfig();
            }
        }
        
        return sMe;
    }    

    /**
     * �����趨�ļ���Ϣ�������ȡ��.
     * @return Configuration �����趨�ļ���Ϣ������
     */
    public Configuration getConfiguration() {
        return config;
    }

    /**
     * �������
     */
    private static ControlConfig sMe;
    
    /**
     * �����趨�ļ���Ϣ���������
     */
    private Configuration config;
    
    /**
     * �ļ����ָ�ʽ.
     */
    public static final String DEF_CHARCODE = "UTF-8";
    
    /**
     * ����·��
     */
    private static String sRealPath;
    
    /**
     * config.xml·��
     */
    private static String sConfPath;
    
    /**
     * ����URL·��
     */
    private static String sHostUrl;
    
    /**
     * session·��
     */
    private static String sSesfPath;
    
    /**
     * ������·��
     */
    private static String sErrorMenuPath;

    /**
     * config.xml·���趨.
     * @param confPath config.xml·��
     */
    public static void setConfPath(String confPath) {
        ControlConfig.sConfPath = confPath;
    }
    
    /**
     * ����URL�趨.
     * @param url ����URL
     */
    public static void setHostUrl(String url) {
        ControlConfig.sHostUrl = url;
    }
    
    /**
     * ����URLȡ��.
     * @return ����URL
     */
    public static String getHostUrl() {
        return ControlConfig.sHostUrl;
    }
    
    /**
     * ������·��ȡ��.
     * @return errorMenuPath ������·��
     */
    public static String getErrorMenuPath() {
        return sErrorMenuPath;
    }

    /**
     * ������·���趨.
     * @param errorMenuPath ������·��
     */
    public static void setErrorMenuPath(String errorMenuPath) {
        ControlConfig.sErrorMenuPath = errorMenuPath;
    }

    /**
     * session·��ȡ��.
     * @return session·��
     */
    public static String getSesfPath() {
        return sSesfPath;
    }

    /**
     * session·���趨.
     * @param sesfPath session·��
     */
    public static void setSesfPath(String sesfPath) {
        ControlConfig.sSesfPath = sesfPath;
    }

    /**
     * ����·��ȡ��.
     * @return ����·��
     */
    public static String getRealPath() {
        return sRealPath;
    }

    /**
     * ����·���趨.
     * @param realPath ����·��
     */
    public static void setRealPath(String realPath) {
        ControlConfig.sRealPath = realPath;
    }
}
