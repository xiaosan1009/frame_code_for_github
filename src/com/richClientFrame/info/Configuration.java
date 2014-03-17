package com.richClientFrame.info;

import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;

import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� CheckInfo
 * ������ �� �����趨�ļ���Ϣ������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2010.03.19
 * @author king
 */
public class Configuration {
    
    /**
     * �����ļ�MAP
     */
    private Map<String, Map<String, String>> configMap;

    /**
     * ���캯��.
     */
    public Configuration() {
        super();
    }
    
    /**
     * �Ƿ�Ϊdebugģʽ.
     * @return �Ƿ�Ϊdebugģʽ
     */
    public boolean isDebug() {
        if (configMap.get("frame") == null) {
            return false;
        }
        return Boolean.parseBoolean((configMap.get("frame").get("debug")));
    }
    
    /**
     * �Ƿ��ӡ��־.
     * @return �Ƿ��ӡ��־
     */
    public boolean doLog() {
        if (configMap.get("frame") == null) {
            return false;
        }
        return Boolean.parseBoolean((configMap.get("frame").get("log")));
    }
    
    /**
     * �Ƿ��ӡ�����־.
     * @return �Ƿ��ӡ�����־
     */
    public boolean doFrameLog() {
        if (configMap.get("frame") == null) {
            return false;
        }
        return Boolean.parseBoolean((configMap.get("frame").get("frameLog")));
    }
    
    /**
     * �Ƿ��ӡ�����־ʱ��.
     * @return �Ƿ��ӡ�����־ʱ��
     */
    public boolean doLogTime() {
        if (configMap.get("frame") == null) {
            return false;
        }
        return Boolean.parseBoolean((configMap.get("frame").get("logTime")));
    }
    
    /**
     * �Ƿ��ӡ�����־ʱ��.
     * @return �Ƿ��ӡ�����־ʱ��
     */
    public int getLogLevel() {
        int level = 0;
        if (configMap.get("frame") == null) {
            return level;
        }
        if (CommonUtil.isEmpty(configMap.get("frame").get("logLevel"))) {
            return level;
        }
        try {
            level = Integer.parseInt(configMap.get("frame").get("logLevel"));
        } catch (Exception e) {
            level = 0;
            System.out.println("config.xml��[frame]��ǩ��[logLevel]����ֵΪ��ֵ��");
        }
        return level;
    }
    
    /**
     * ȡ��memcached��key.
     * @return memcached��key
     */
    public String getMemcachedKey() {
        if (configMap.get("frame") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("frame").get("memcachedKey");
    }
    
    /**
     * ȡ��memcached��key.
     * @return memcached��key
     */
    public String getMd5Key() {
        if (configMap.get("frame") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("frame").get("md5Key");
    }
    
    /**
     * �����ļ��ľ��Ե�ַȡ��.
     * @return �����ļ��ľ��Ե�ַ
     */
    public String getAbsolutePath() {
        if (configMap.get("frame") == null) {
            return null;
        }
        return configMap.get("frame").get("absolutePath");
    }

    /**
     * ������󱳾�ɫ��Ĭ�ϣ��ۺ�ɫ��ȡ��.
     * 
     * @return inputErrBackColor ������󱳾�ɫ��Ĭ�ϣ��ۺ�ɫ��
     */
    public String getInputErrBackColor() {
        if (configMap.get("inputerr") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("inputerr").get("bgcolor");
    }

    /**
     * �����������ɫ��Ĭ�ϣ��ۺ�ɫ��.
     * @return inputErrCharColor
     */
    public String getInputErrCharColor() {
        if (configMap.get("inputerr") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("inputerr").get("charcolor");
    }

    /**
     * �б������б���ɫ��Ĭ�ϣ���ɫ��ȡ��.
     * 
     * @return columnBackColor �б������б���ɫ��Ĭ�ϣ���ɫ��
     */
    public String getColumnBackColor() {
        if (configMap.get("columnback") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("columnback").get("color");
    }

    /**
     * �б�ż���б���ɫ��Ĭ�ϣ���ɫ��ȡ��.
     * 
     * @return columnBackColorAlt �б�ż���б���ɫ��Ĭ�ϣ���ɫ��
     */
    public String getColumnBackColorAlt() {
        if (configMap.get("columnback") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("columnback").get("altcolor");
    }

    /**
     * ��������ȡ��.
     * @return ��������
     */
    public String getLang() {
        if (configMap.get("lang") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("lang").get("type");
    }

    /**
     * BODY������ɫ��Ĭ�ϣ���ɫ��ȡ��.
     * @return BODY������ɫ��Ĭ�ϣ���ɫ��
     */
    public String getBodyBackColor() {
        if (configMap.get("backcolor") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("backcolor").get("body");
    }

    /**
     * ��Ŀ����ɫ��Ĭ�ϣ���ɫ��ȡ��.
     * @return ��Ŀ����ɫ��Ĭ�ϣ���ɫ��
     */
    public String getItemBackColor() {
        if (configMap.get("backcolor") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("backcolor").get("item");
    }

    /**
     * �ɱ༭��Ŀ����ɫ��Ĭ�ϣ�ˮɫ��ȡ��.
     * @return �ɱ༭��Ŀ����ɫ��Ĭ�ϣ�ˮɫ��
     */
    public String getEditBackColor() {
        if (configMap.get("backcolor") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("backcolor").get("edit");
    }
    
    /**
     * host��ַȡ��.
     * @return host��ַ
     */
    public String getUploadHostUrl() {
        if (configMap.get("upload") == null) {
            return null;
        }
        return configMap.get("upload").get("url");
    }
    
    /**
     * host�˿�ȡ��.
     * @return host�˿�
     */
    public boolean isUploadHostUrlAuto() {
        if (configMap.get("upload") == null) {
            return false;
        }
        return Boolean.parseBoolean(configMap.get("upload").get("auto"));
    }
    
    /**
     * �ϴ���ַȡ��.
     * @return �ϴ���ַ
     */
    public String getUploadPath() {
        if (configMap.get("upload") == null) {
            return null;
        }
        return configMap.get("upload").get("path");
    }
    
    /**
     * ftp��ַȡ��.
     * @return ftp��ַ
     */
    public String getFtpUrl() {
        if (configMap.get("ftp") == null) {
            return null;
        }
        return configMap.get("ftp").get("url");
    }
    
    /**
     * ftp����URLȡ��.
     * @return ftp��ַ
     */
    public String getFtpAddress() {
        if (configMap.get("ftp") == null) {
            return null;
        }
        return configMap.get("ftp").get("address");
    }
    
    /**
     * ftp�û���ȡ��.
     * @return ftp�û���
     */
    public String getFtpUser() {
        if (configMap.get("ftp") == null) {
            return null;
        }
        return configMap.get("ftp").get("user");
    }
    
    /**
     * ftp����ȡ��.
     * @return ftp����
     */
    public String getFtpPassword() {
        if (configMap.get("ftp") == null) {
            return null;
        }
        return configMap.get("ftp").get("password");
    }
    
    /**
     * ftp�ļ�·��ȡ��.
     * @return ftp�ļ�·��
     */
    public String getFtpPath() {
        if (configMap.get("ftp") == null) {
            return null;
        }
        return configMap.get("ftp").get("path");
    }
    
    /**
     * ftp�����Сȡ��.
     * @return ftp�����С
     */
    public int getFtpSize() {
        if (configMap.get("ftp") == null) {
            return 0;
        }
        return Integer.parseInt(configMap.get("ftp").get("size"));
    }
    
    /**
     * ftp�����ʽȡ��.
     * @return ftp�����ʽ
     */
    public String getFtpEncoding() {
        if (configMap.get("ftp") == null) {
            return null;
        }
        return configMap.get("ftp").get("encoding");
    }
    
    /**
     * ajax������֤KEYȡ��.
     * @return ������֤KEY
     */
    public String getAjaxRequestChecker() {
        if (configMap.get("requestChecker") == null) {
            return null;
        }
        return configMap.get("requestChecker").get("ajax");
    }
    
    /**
     * flex������֤KEYȡ��.
     * @return ������֤KEY
     */
    public String getFlexRequestChecker() {
        if (configMap.get("requestChecker") == null) {
            return null;
        }
        return configMap.get("requestChecker").get("flex");
    }
    
    /**
     * android������֤KEYȡ��.
     * @return ������֤KEY
     */
    public String getAndroidRequestChecker() {
        if (configMap.get("requestChecker") == null) {
            return null;
        }
        return configMap.get("requestChecker").get("android");
    }
    
    /**
     * �Ƿ����������.
     * @return ������֤KEY
     */
    public boolean isEnableRequestChecker() {
        if (configMap.get("requestChecker") == null) {
            return false;
        }
        return Boolean.parseBoolean(configMap.get("requestChecker").get("enable"));
    }
    
    /**
     * userAgent������֤KEYȡ��.
     * @return ������֤KEY
     */
    public String getUserAgentRequestHeaderChecker() {
        if (configMap.get("reqHeaderChecker") == null) {
            return null;
        }
        return configMap.get("reqHeaderChecker").get("userAgent");
    }
    
    /**
     * �Ƿ����������.
     * @return ������֤KEY
     */
    public boolean isEnableRequestHeaderChecker() {
        if (configMap.get("reqHeaderChecker") == null) {
            return false;
        }
        return Boolean.parseBoolean(configMap.get("reqHeaderChecker").get("enable"));
    }
    
    /**
     * socket�˿�ȡ��.
     * @return socket�˿�
     */
    public int getSocketPort() {
        if (configMap.get("socket") == null) {
            return 0;
        }
        return Integer.parseInt(configMap.get("socket").get("port"));
    }
    
    /**
     * socket��ȫ�˿�ȡ��.
     * @return socket��ȫ�˿�
     */
    public int getSocketSecurity() {
        if (configMap.get("socket") == null) {
            return 0;
        }
        return Integer.parseInt(configMap.get("socket").get("security"));
    }
    
    /**
     * socket�Ƿ����.
     * @return �Ƿ����
     */
    public boolean isSocketEnable() {
        if (configMap.get("socket") == null) {
            return false;
        }
        return Boolean.parseBoolean((configMap.get("socket").get("enable")));
    }

    /**
     * excel�������ݴ�Сȡ��.
     * @return excel�������ݴ�С
     */
    public int getExcelMaxSize() {
        if (configMap.get("excel") == null) {
            return 0;
        }
        return Integer.parseInt(configMap.get("excel").get("maxSize"));
    }
    
    /**
     * �Ƿ����������.
     * @return ������֤KEY
     */
    public boolean isAutoZipEnabled() {
        if (configMap.get("excel") == null) {
            return false;
        }
        return Boolean.parseBoolean(configMap.get("excel").get("autoZipEnabled"));
    }

    /**
     * �����ļ�MAPȡ��.
     * @return �����ļ�MAP
     */
    public Map<String, Map<String, String>> getConfigMap() {
        return configMap;
    }

    /**
     * �����ļ�MAP�趨.
     * @param configMap �����ļ�MAP
     */
    public void setConfigMap(Map<String, Map<String, String>> configMap) {
        this.configMap = configMap;
    }
}
