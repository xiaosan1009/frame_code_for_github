package com.richClientFrame.info;

import java.io.File;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.ConstantsUtil;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ControlConfig
 * 类描述 ： 环境变量信息保存类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2010.03.19
 * @author king
 */
public final class ControlConfig {
    
    /**
     * XML文件路径.
     */
    public static final String DEF_XMLDIR = "WEB-INF" 
        + File.separator + ConstantsUtil.Str.EXTENSION_XML;
    
    /**
     * 保存session的文件夹.
     */
    public static final String DEF_SESDIR = "WEB-INF" 
        + File.separator + "seswork" + File.separator;
    
    /**
     * HTML模板的文件夹.
     */
    public static final String DEF_TPLDIR = "WEB-INF" 
        + File.separator + "templates" + File.separator;

    /**
     * PDF文件保存的文件夹.
     */
    public static final String DEF_PDFDIR = "pdf" + File.separator;
    
    /**
     * Javascript的文件夹.
     */
    public static final String DEF_JSDIR = "js" + File.separator;
    
    /**
     * 图片的文件夹.
     */
    public static final String DEF_IMGDIR = "img" + File.separator;
    
    /**
     * 上传的文件夹.
     */
    public static final String DEF_UPLOADDIR = "upload" + File.separator;

    /**
     * 构造函数.
     */
    private ControlConfig() {
        super();
        // 环境变量信息的取得
        this.config = ConfigurationReader.read(sConfPath);
    }

    /**
     * 自身对象的取得.
     * @return ControlBaConfig 自身对象
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
     * 环境设定文件信息管理类的取得.
     * @return Configuration 环境设定文件信息管理类
     */
    public Configuration getConfiguration() {
        return config;
    }

    /**
     * 自身对象
     */
    private static ControlConfig sMe;
    
    /**
     * 环境设定文件信息管理类对象
     */
    private Configuration config;
    
    /**
     * 文件文字格式.
     */
    public static final String DEF_CHARCODE = "UTF-8";
    
    /**
     * 绝对路径
     */
    private static String sRealPath;
    
    /**
     * config.xml路径
     */
    private static String sConfPath;
    
    /**
     * 工程URL路径
     */
    private static String sHostUrl;
    
    /**
     * session路径
     */
    private static String sSesfPath;
    
    /**
     * 错误画面路径
     */
    private static String sErrorMenuPath;

    /**
     * config.xml路径设定.
     * @param confPath config.xml路径
     */
    public static void setConfPath(String confPath) {
        ControlConfig.sConfPath = confPath;
    }
    
    /**
     * 工程URL设定.
     * @param url 工程URL
     */
    public static void setHostUrl(String url) {
        ControlConfig.sHostUrl = url;
    }
    
    /**
     * 工程URL取得.
     * @return 工程URL
     */
    public static String getHostUrl() {
        return ControlConfig.sHostUrl;
    }
    
    /**
     * 错误画面路径取得.
     * @return errorMenuPath 错误画面路径
     */
    public static String getErrorMenuPath() {
        return sErrorMenuPath;
    }

    /**
     * 错误画面路径设定.
     * @param errorMenuPath 错误画面路径
     */
    public static void setErrorMenuPath(String errorMenuPath) {
        ControlConfig.sErrorMenuPath = errorMenuPath;
    }

    /**
     * session路径取得.
     * @return session路径
     */
    public static String getSesfPath() {
        return sSesfPath;
    }

    /**
     * session路径设定.
     * @param sesfPath session路径
     */
    public static void setSesfPath(String sesfPath) {
        ControlConfig.sSesfPath = sesfPath;
    }

    /**
     * 绝对路径取得.
     * @return 绝对路径
     */
    public static String getRealPath() {
        return sRealPath;
    }

    /**
     * 绝对路径设定.
     * @param realPath 绝对路径
     */
    public static void setRealPath(String realPath) {
        ControlConfig.sRealPath = realPath;
    }
}
