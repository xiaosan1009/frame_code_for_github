package com.richClientFrame.data.access;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * ��Ŀ���� �� Web2.0��������
 * ������ �� LoadProperties
 * ������ �� property�ļ���ȡ��.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
 * @author king
 */
public final class LoadProperties {

    /**
     * ���캯��.
     */
    private LoadProperties() {
        super();
    }
    
    /**
     * ���ļ��е����ݶ��뵽MAP��.
     * 
     * @param path ��ȡ�ļ���·��
     * @param extension ��ȡ�ļ��ĺ�׺��
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
