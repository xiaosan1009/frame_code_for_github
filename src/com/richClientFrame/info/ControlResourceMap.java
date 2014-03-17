package com.richClientFrame.info;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ControlResourceMap
 * 类描述 ： 固定ID和固定文字保存类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2010.05.30
 * @author king
 */
public final class ControlResourceMap {
    
    /**
     * logger
     */
    private LogUtil log = new LogUtil(ControlResourceMap.class, true);;
    
    private static ControlResourceMap sMe;
    
    private Map<String, Properties> itemNameMap;
    
    private static String sPath;
    
    private static String sFramePath;
    
    /**
     * 构造函数
     */
    private ControlResourceMap() {
        super();
        itemNameMap = new HashMap<String, Properties>();
        // 读取共通资源文件信息
        if (CommonUtil.isNotEmpty(sPath)) {
            readItemField(sPath, "properties", itemNameMap);
        }
//        String framePath = Config.getProperty("");
//        readItemField(sFramePath, "properties", itemNameMap);
    }
    
    /**
     * 本类对象生成.
     * @return ControlResourceMap 本类对象
     */
    public static ControlResourceMap getInstance() {
        
        synchronized (ControlResourceMap.class) {
            if (sMe == null) {
                sMe = new ControlResourceMap();
            }
        }
        return sMe;
    }

    /**
     * 文件路径取得.
     * @return sPath 文件路径
     */
    public static String getPath() {
        return sPath;
    }

    /**
     * 文件路径设定.
     * @param path 文件路径
     */
    public static void setPath(String path) {
        ControlResourceMap.sPath = path;
    }
    
    /**
     * 项目名称取得.
     * @param itemId 项目ID
     * @return 项目名称
     */
    public String getItemName(String itemId) {
        return getItemName(ConstantsUtil.Str.EMPTY, itemId);
    }
    
    /**
     * 项目名称取得.
     * @param dispId 画面ID
     * @param itemId 项目ID
     * @return 项目名称
     */
    public String getItemName(String dispId, String itemId) {
        log.debug("getItemName", "start", "dispId = " + dispId, "itemId = " + itemId, "itemNameMap = " + itemNameMap);
        if (CommonUtil.isEmpty(itemId)) {
            return ConstantsUtil.Str.EMPTY;
        }
        String itemName = "";
        if (itemNameMap != null) {
            if (itemNameMap.get(dispId) != null && itemNameMap.get(dispId).get(itemId) != null) {
                itemName = (String)itemNameMap.get(dispId).get(itemId);
            } else if (itemNameMap.get("commonResource") != null 
                    && itemNameMap.get("commonResource").get(itemId) != null) {
                itemName = CommonUtil.toString(itemNameMap.get("commonResource").get(itemId));
            }
        }
        log.debug("getItemName", "end", "itemName = " + itemName);
        return itemName;
    }
    
    /**
     * 资源文件对象取得.
     * @param path 文件路径
     * @param extension 文件后缀
     * @param retMap 资源文件对象
     */
    private void readItemField(String path, String extension, Map<String, Properties> retMap) {
        
        log.debug("ControlResourceMap : readItemField", "start", "path = " + path);
        
        final File dir = new File(path);
        
        if (!dir.isDirectory()) {
            return;
        }
        final File[] dirs = dir.listFiles();
        
        for (int i = 0; i < dirs.length; i++) {
            
            if (dirs[i].isDirectory()) {
                
                final File[] files = dirs[i].listFiles();
                for (File file : files) {
                    addProperties(retMap, file, extension);
                }
            } else if (dirs[i].isFile()) {
                addProperties(retMap, dirs[i], extension);
            }
        }
        
        log.debug("ControlResourceMap : readItemField", "end");
    }
    
    /**
     * 添加资源文件对象.
     * @param retMap 资源文件对象
     * @param file 文件对象
     * @param extension 文件后缀
     */
    private void addProperties(Map<String, Properties> retMap, File file, String extension) {
        
        try {
            if (file.getName().endsWith(extension)) {
                final Properties map = new Properties();
                final Properties properties = new Properties();
                
                properties.load(new FileInputStream(file));
                map.putAll(properties);
                
                String fileName = file.getName();
                final int extIdx = getExtensionIndex(fileName, extension);
                
                if (extIdx > 0) {
                    fileName = fileName.substring(0, extIdx);
                    retMap.put(fileName, map);
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * 资源文件后缀位置取得.
     * @param fileName 文件名
     * @param extension 文件后缀
     * @return 资源文件后缀
     */
    private int getExtensionIndex(String fileName, String extension) {
        final String lang = ControlConfig.getInstance().getConfiguration().getLang();
        final int extIdx = fileName.indexOf(ConstantsUtil.Str.UNDERLINE + lang 
                + ConstantsUtil.Str.DOT + extension);
        return extIdx;
    }

    /**
     * 框架文件路径设定.
     * @param framePath 文件路径
     */
    public static void setFramePath(String framePath) {
        sFramePath = framePath;
    }
}
