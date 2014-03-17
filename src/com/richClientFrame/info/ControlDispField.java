package com.richClientFrame.info;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ControlDispField
 * 类描述 ： 画面项目类型管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2010.05.30
 * @author king
 */
public final class ControlDispField {
    
    private static LogUtil sLog = new LogUtil(ControlDispField.class, true);
    
    /**
     * 文件路径
     */
    private static final String DIR_DISP = "WEB-INF" + File.separator + "disp" + File.separator;
    
    /**
     * 画面项目定义文件夹
     */
    private static final String DIR_FIELD = "field";
    
    /**
     * 画面项目定义的扩展文件夹
     */
    private static final String EXTENSION_FIELD = "fld";
    
    /**
     * 资源定义文件夹
     */
    private static final String DIR_RESOURCE = "resource";
    
    /**
     * 资源定义的扩展文件夹
     */
    private static final String EXTENSION_RESOURCE = "rsc";
    
    /**
     * 自对象
     */
    private static ControlDispField sMe;
    
    /**
     * 画面项目保存对象（项目ID = 项目类型）
     */
    private Map<String, Map<String, String>> fieldMap;
    
    /**
     * 资源保存对象（项目ID = 表示信息）
     */
    private Map<String, Map<String, String>> resourceMap;
    
    /**
     * 路径
     */
    private static String sPath;
    
    /**
     * 构造函数
     * @throws RichClientWebException RichClientWebException
     */
    private ControlDispField() throws RichClientWebException {
        super();
        
        // 项目文件读取
        readFile();
    }
    
    /**
     * 自身对象的获得.
     * @return me 自身对象
     * @throws RichClientWebException RichClientWebException
     */
    public static ControlDispField getInstance() throws RichClientWebException {
        
        synchronized (ControlDispField.class) {
            if (sMe == null) {
                sMe = new ControlDispField();
            }
        }
        return sMe;
    }
    
    /**
     * 画面项目文件的读取
     * @throws IOException
     * @throws RichClientWebException RichClientWebException
     */
    private void readFile() throws RichClientWebException {
        
        final File fieldDir = new File(sPath + DIR_FIELD);
        fieldMap = read(fieldDir, EXTENSION_FIELD);
        
        final File guideDir = new File(sPath + DIR_RESOURCE);
        resourceMap = read(guideDir, EXTENSION_RESOURCE);

    }
    
    /**
     * 文件夹内所有文件读取
     * @param dir 文件读取路径
     * @param ext 读取文件的后缀名
     * @return 项目内容保存对象（KEY：文件名。value：文件内数据序列）
     * @throws RichClientWebException RichClientWebException
     */
    private Map<String, Map<String, String>> read(File dir, String ext)
        throws RichClientWebException {
        
        sLog.info("read", "start", "dir = " + dir, "ext = " + ext);
        
        final Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
        
        if (!dir.isDirectory()) {
            return null;
        }

        parse(map, dir, ext);
        
        sLog.info("read", "end");
        
        return map;
    }
    
    /** 
     * @Description: 解析.fld或者.rsc文件信息
     * @author king
     * @since Oct 10, 2012 2:11:25 PM 
     * 
     * @param map 信息保持容器
     * @param file 文件对象
     * @param ext 扩展名
     * @throws RichClientWebException RichClientWebException
     */
    private void parse(Map<String, Map<String, String>> map, File file, String ext)
        throws RichClientWebException {
        if (file.isDirectory()) {
            final File[] childFiles = file.listFiles();
            for (int j = 0; j < childFiles.length; j++) {
                parse(map, childFiles[j], ext);
            }
        } else {
            if (!(file.getName().endsWith(ext))) {
                return;
            }
            final Map<String, String> valmap = DispFieldReader.getAllLine(file.getPath(),
                    ControlConfig.DEF_CHARCODE);
            final String[] fileName = file.getName().split(ConstantsUtil.Str.REGEX_DOT);
            if (fileName != null) {
                map.put(CommonUtil.nvl(fileName[0]), valmap);
            }
        }
    }
    
    /**
     * KEY（画面ID）对应画面的控件类型信息组.
     * 
     * @param key 画面ID
     * @return 画面ID对应的MAP
     * @since 2008/05/30
     */
    public Map<String, String> getField(String key) {
        if (fieldMap == null) {
            return null;
        }
        return fieldMap.get(key);
    }
    
    /**
     * KEY（画面ID，项目ID）对应的项目类型返回.
     * @param dispNo 画面ID
     * @param fieldNo 项目ID
     * @return 项目类型
     */
    public String getType(String dispNo, String fieldNo) {
        String type = null;
        
        final Map<String, String> map = fieldMap.get(dispNo);
        if (map == null) {
            return null;
        } else {
            if (map.get(fieldNo) != null) {
                type = map.get(fieldNo);
            }
        }
        
        return type;
    }
    
    /**
     * 画面ID对应的资源文件信息取得.
     * @return 资源文件信息
     */
    public Map<String, Map<String, String>> getResourceMap() {
        return resourceMap;
    }
    
    /**
     * KEY（画面ID，项目ID）对应的资源信息取得.
     * @param dispNo 画面ID
     * @param fieldNo 项目ID
     * @return 资源信息
     */
    public String getMessage(String dispNo, String fieldNo) {
        String msg = "";
        
        final Map<String, String> map = (Map<String, String>)resourceMap.get(dispNo);
        if (map == null) {
            return "";
        } else {
            if (map.get(fieldNo) != null) {
                msg = map.get(fieldNo);
            }
        }
        
        return msg;
    }
    
    /**
     * KEY（画面ID，项目ID，序列ID）对应的资源信息取得.
     * @param dispNo 画面ID
     * @param fieldNo 项目ID
     * @param idx 项目序号
     * @return 资源信息
     */
    public String getMessage(String dispNo, String fieldNo, String idx) {
        String msg = "";
        
        final Map<String, String> map = (Map<String, String>)resourceMap.get(dispNo);
        if (map == null) {
            return "";
        } else {
            if (map.get(fieldNo + ConstantsUtil.Str.UNDERLINE + idx) != null) {
                msg = map.get(fieldNo + ConstantsUtil.Str.UNDERLINE + idx);
            } else {
                if (map.get(fieldNo) != null) {
                    msg = map.get(fieldNo);
                }
            }
        }
        
        return msg;
    }

    /**
     * 文件路径取得.
     * @return 文件路径
     */
    public static String getPath() {
        return sPath;
    }

    /**
     * 文件路径设定.
     * @param path 文件路径
     */
    public static void setPath(String path) {
        ControlDispField.sPath = path + DIR_DISP;
    }

    /**
     * 取得画面项目保存对象（项目ID = 项目类型）.
     * @return 画面项目保存对象
     */
    public Map<String, Map<String, String>> getFieldMap() {
        return fieldMap;
    }
}
