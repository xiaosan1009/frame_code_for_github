package com.richClientFrame.info;

import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;

import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： CheckInfo
 * 类描述 ： 环境设定文件信息管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2010.03.19
 * @author king
 */
public class Configuration {
    
    /**
     * 配置文件MAP
     */
    private Map<String, Map<String, String>> configMap;

    /**
     * 构造函数.
     */
    public Configuration() {
        super();
    }
    
    /**
     * 是否为debug模式.
     * @return 是否为debug模式
     */
    public boolean isDebug() {
        if (configMap.get("frame") == null) {
            return false;
        }
        return Boolean.parseBoolean((configMap.get("frame").get("debug")));
    }
    
    /**
     * 是否打印日志.
     * @return 是否打印日志
     */
    public boolean doLog() {
        if (configMap.get("frame") == null) {
            return false;
        }
        return Boolean.parseBoolean((configMap.get("frame").get("log")));
    }
    
    /**
     * 是否打印框架日志.
     * @return 是否打印框架日志
     */
    public boolean doFrameLog() {
        if (configMap.get("frame") == null) {
            return false;
        }
        return Boolean.parseBoolean((configMap.get("frame").get("frameLog")));
    }
    
    /**
     * 是否打印框架日志时间.
     * @return 是否打印框架日志时间
     */
    public boolean doLogTime() {
        if (configMap.get("frame") == null) {
            return false;
        }
        return Boolean.parseBoolean((configMap.get("frame").get("logTime")));
    }
    
    /**
     * 是否打印框架日志时间.
     * @return 是否打印框架日志时间
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
            System.out.println("config.xml的[frame]标签的[logLevel]属性值为数值！");
        }
        return level;
    }
    
    /**
     * 取得memcached的key.
     * @return memcached的key
     */
    public String getMemcachedKey() {
        if (configMap.get("frame") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("frame").get("memcachedKey");
    }
    
    /**
     * 取得memcached的key.
     * @return memcached的key
     */
    public String getMd5Key() {
        if (configMap.get("frame") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("frame").get("md5Key");
    }
    
    /**
     * 工程文件的绝对地址取得.
     * @return 工程文件的绝对地址
     */
    public String getAbsolutePath() {
        if (configMap.get("frame") == null) {
            return null;
        }
        return configMap.get("frame").get("absolutePath");
    }

    /**
     * 输入错误背景色（默认：粉红色）取得.
     * 
     * @return inputErrBackColor 输入错误背景色（默认：粉红色）
     */
    public String getInputErrBackColor() {
        if (configMap.get("inputerr") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("inputerr").get("bgcolor");
    }

    /**
     * 输入错误文字色（默认：粉红色）.
     * @return inputErrCharColor
     */
    public String getInputErrCharColor() {
        if (configMap.get("inputerr") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("inputerr").get("charcolor");
    }

    /**
     * 列表奇数行背景色（默认：灰色）取得.
     * 
     * @return columnBackColor 列表奇数行背景色（默认：灰色）
     */
    public String getColumnBackColor() {
        if (configMap.get("columnback") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("columnback").get("color");
    }

    /**
     * 列表偶数行背景色（默认：白色）取得.
     * 
     * @return columnBackColorAlt 列表偶数行背景色（默认：白色）
     */
    public String getColumnBackColorAlt() {
        if (configMap.get("columnback") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("columnback").get("altcolor");
    }

    /**
     * 语言种类取得.
     * @return 语言种类
     */
    public String getLang() {
        if (configMap.get("lang") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("lang").get("type");
    }

    /**
     * BODY背景颜色（默认：灰色）取得.
     * @return BODY背景颜色（默认：灰色）
     */
    public String getBodyBackColor() {
        if (configMap.get("backcolor") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("backcolor").get("body");
    }

    /**
     * 项目背景色（默认：白色）取得.
     * @return 项目背景色（默认：白色）
     */
    public String getItemBackColor() {
        if (configMap.get("backcolor") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("backcolor").get("item");
    }

    /**
     * 可编辑项目背景色（默认：水色）取得.
     * @return 可编辑项目背景色（默认：水色）
     */
    public String getEditBackColor() {
        if (configMap.get("backcolor") == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return configMap.get("backcolor").get("edit");
    }
    
    /**
     * host地址取得.
     * @return host地址
     */
    public String getUploadHostUrl() {
        if (configMap.get("upload") == null) {
            return null;
        }
        return configMap.get("upload").get("url");
    }
    
    /**
     * host端口取得.
     * @return host端口
     */
    public boolean isUploadHostUrlAuto() {
        if (configMap.get("upload") == null) {
            return false;
        }
        return Boolean.parseBoolean(configMap.get("upload").get("auto"));
    }
    
    /**
     * 上传地址取得.
     * @return 上传地址
     */
    public String getUploadPath() {
        if (configMap.get("upload") == null) {
            return null;
        }
        return configMap.get("upload").get("path");
    }
    
    /**
     * ftp地址取得.
     * @return ftp地址
     */
    public String getFtpUrl() {
        if (configMap.get("ftp") == null) {
            return null;
        }
        return configMap.get("ftp").get("url");
    }
    
    /**
     * ftp工程URL取得.
     * @return ftp地址
     */
    public String getFtpAddress() {
        if (configMap.get("ftp") == null) {
            return null;
        }
        return configMap.get("ftp").get("address");
    }
    
    /**
     * ftp用户名取得.
     * @return ftp用户名
     */
    public String getFtpUser() {
        if (configMap.get("ftp") == null) {
            return null;
        }
        return configMap.get("ftp").get("user");
    }
    
    /**
     * ftp密码取得.
     * @return ftp密码
     */
    public String getFtpPassword() {
        if (configMap.get("ftp") == null) {
            return null;
        }
        return configMap.get("ftp").get("password");
    }
    
    /**
     * ftp文件路径取得.
     * @return ftp文件路径
     */
    public String getFtpPath() {
        if (configMap.get("ftp") == null) {
            return null;
        }
        return configMap.get("ftp").get("path");
    }
    
    /**
     * ftp缓冲大小取得.
     * @return ftp缓冲大小
     */
    public int getFtpSize() {
        if (configMap.get("ftp") == null) {
            return 0;
        }
        return Integer.parseInt(configMap.get("ftp").get("size"));
    }
    
    /**
     * ftp编码格式取得.
     * @return ftp编码格式
     */
    public String getFtpEncoding() {
        if (configMap.get("ftp") == null) {
            return null;
        }
        return configMap.get("ftp").get("encoding");
    }
    
    /**
     * ajax请求验证KEY取得.
     * @return 请求验证KEY
     */
    public String getAjaxRequestChecker() {
        if (configMap.get("requestChecker") == null) {
            return null;
        }
        return configMap.get("requestChecker").get("ajax");
    }
    
    /**
     * flex请求验证KEY取得.
     * @return 请求验证KEY
     */
    public String getFlexRequestChecker() {
        if (configMap.get("requestChecker") == null) {
            return null;
        }
        return configMap.get("requestChecker").get("flex");
    }
    
    /**
     * android请求验证KEY取得.
     * @return 请求验证KEY
     */
    public String getAndroidRequestChecker() {
        if (configMap.get("requestChecker") == null) {
            return null;
        }
        return configMap.get("requestChecker").get("android");
    }
    
    /**
     * 是否做请求控制.
     * @return 请求验证KEY
     */
    public boolean isEnableRequestChecker() {
        if (configMap.get("requestChecker") == null) {
            return false;
        }
        return Boolean.parseBoolean(configMap.get("requestChecker").get("enable"));
    }
    
    /**
     * userAgent请求验证KEY取得.
     * @return 请求验证KEY
     */
    public String getUserAgentRequestHeaderChecker() {
        if (configMap.get("reqHeaderChecker") == null) {
            return null;
        }
        return configMap.get("reqHeaderChecker").get("userAgent");
    }
    
    /**
     * 是否做请求控制.
     * @return 请求验证KEY
     */
    public boolean isEnableRequestHeaderChecker() {
        if (configMap.get("reqHeaderChecker") == null) {
            return false;
        }
        return Boolean.parseBoolean(configMap.get("reqHeaderChecker").get("enable"));
    }
    
    /**
     * socket端口取得.
     * @return socket端口
     */
    public int getSocketPort() {
        if (configMap.get("socket") == null) {
            return 0;
        }
        return Integer.parseInt(configMap.get("socket").get("port"));
    }
    
    /**
     * socket安全端口取得.
     * @return socket安全端口
     */
    public int getSocketSecurity() {
        if (configMap.get("socket") == null) {
            return 0;
        }
        return Integer.parseInt(configMap.get("socket").get("security"));
    }
    
    /**
     * socket是否可用.
     * @return 是否可用
     */
    public boolean isSocketEnable() {
        if (configMap.get("socket") == null) {
            return false;
        }
        return Boolean.parseBoolean((configMap.get("socket").get("enable")));
    }

    /**
     * excel最大的内容大小取得.
     * @return excel最大的内容大小
     */
    public int getExcelMaxSize() {
        if (configMap.get("excel") == null) {
            return 0;
        }
        return Integer.parseInt(configMap.get("excel").get("maxSize"));
    }
    
    /**
     * 是否做请求控制.
     * @return 请求验证KEY
     */
    public boolean isAutoZipEnabled() {
        if (configMap.get("excel") == null) {
            return false;
        }
        return Boolean.parseBoolean(configMap.get("excel").get("autoZipEnabled"));
    }

    /**
     * 配置文件MAP取得.
     * @return 配置文件MAP
     */
    public Map<String, Map<String, String>> getConfigMap() {
        return configMap;
    }

    /**
     * 配置文件MAP设定.
     * @param configMap 配置文件MAP
     */
    public void setConfigMap(Map<String, Map<String, String>> configMap) {
        this.configMap = configMap;
    }
}
