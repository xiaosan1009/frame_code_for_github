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
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ControlResourceMap
 * ������ �� �̶�ID�͹̶����ֱ�����.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2010.05.30
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
     * ���캯��
     */
    private ControlResourceMap() {
        super();
        itemNameMap = new HashMap<String, Properties>();
        // ��ȡ��ͨ��Դ�ļ���Ϣ
        if (CommonUtil.isNotEmpty(sPath)) {
            readItemField(sPath, "properties", itemNameMap);
        }
//        String framePath = Config.getProperty("");
//        readItemField(sFramePath, "properties", itemNameMap);
    }
    
    /**
     * �����������.
     * @return ControlResourceMap �������
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
     * �ļ�·��ȡ��.
     * @return sPath �ļ�·��
     */
    public static String getPath() {
        return sPath;
    }

    /**
     * �ļ�·���趨.
     * @param path �ļ�·��
     */
    public static void setPath(String path) {
        ControlResourceMap.sPath = path;
    }
    
    /**
     * ��Ŀ����ȡ��.
     * @param itemId ��ĿID
     * @return ��Ŀ����
     */
    public String getItemName(String itemId) {
        return getItemName(ConstantsUtil.Str.EMPTY, itemId);
    }
    
    /**
     * ��Ŀ����ȡ��.
     * @param dispId ����ID
     * @param itemId ��ĿID
     * @return ��Ŀ����
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
     * ��Դ�ļ�����ȡ��.
     * @param path �ļ�·��
     * @param extension �ļ���׺
     * @param retMap ��Դ�ļ�����
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
     * �����Դ�ļ�����.
     * @param retMap ��Դ�ļ�����
     * @param file �ļ�����
     * @param extension �ļ���׺
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
     * ��Դ�ļ���׺λ��ȡ��.
     * @param fileName �ļ���
     * @param extension �ļ���׺
     * @return ��Դ�ļ���׺
     */
    private int getExtensionIndex(String fileName, String extension) {
        final String lang = ControlConfig.getInstance().getConfiguration().getLang();
        final int extIdx = fileName.indexOf(ConstantsUtil.Str.UNDERLINE + lang 
                + ConstantsUtil.Str.DOT + extension);
        return extIdx;
    }

    /**
     * ����ļ�·���趨.
     * @param framePath �ļ�·��
     */
    public static void setFramePath(String framePath) {
        sFramePath = framePath;
    }
}
