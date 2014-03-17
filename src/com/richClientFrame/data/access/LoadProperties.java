package com.richClientFrame.data.access;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： LoadProperties
 * 类描述 ： property文件读取类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
 * @author king
 */
public final class LoadProperties {

    /**
     * 构造函数.
     */
    private LoadProperties() {
        super();
    }
    
    /**
     * 把文件中的数据读入到MAP中.
     * 
     * @param path 读取文件的路径
     * @param extension 读取文件的后缀名
     * @return MAP
     * @throws IOException IOException
     */
    @SuppressWarnings("unchecked")
    public static Map load(String path, String extension) throws IOException {
        
        final Map map = new HashMap();
        final File dir = new File(path);
        
        if (!dir.isDirectory()) {
            return null;
        }
        
        final File[] files = dir.listFiles();
        final Properties properties = new Properties();
        for (int i = 0; i < files.length; i++) {
            if (!files[i].getName().endsWith(extension)) {
                continue;
            }
        
            properties.clear();
            properties.load(new FileInputStream(files[i]));
            
            map.putAll(properties);
        }
        
        return map;
    }
}
