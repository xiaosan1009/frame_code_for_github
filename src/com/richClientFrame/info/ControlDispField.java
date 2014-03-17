package com.richClientFrame.info;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ControlDispField
 * ������ �� ������Ŀ���͹�����.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2010.05.30
 * @author king
 */
public final class ControlDispField {
    
    private static LogUtil sLog = new LogUtil(ControlDispField.class, true);
    
    /**
     * �ļ�·��
     */
    private static final String DIR_DISP = "WEB-INF" + File.separator + "disp" + File.separator;
    
    /**
     * ������Ŀ�����ļ���
     */
    private static final String DIR_FIELD = "field";
    
    /**
     * ������Ŀ�������չ�ļ���
     */
    private static final String EXTENSION_FIELD = "fld";
    
    /**
     * ��Դ�����ļ���
     */
    private static final String DIR_RESOURCE = "resource";
    
    /**
     * ��Դ�������չ�ļ���
     */
    private static final String EXTENSION_RESOURCE = "rsc";
    
    /**
     * �Զ���
     */
    private static ControlDispField sMe;
    
    /**
     * ������Ŀ���������ĿID = ��Ŀ���ͣ�
     */
    private Map<String, Map<String, String>> fieldMap;
    
    /**
     * ��Դ���������ĿID = ��ʾ��Ϣ��
     */
    private Map<String, Map<String, String>> resourceMap;
    
    /**
     * ·��
     */
    private static String sPath;
    
    /**
     * ���캯��
     * @throws RichClientWebException RichClientWebException
     */
    private ControlDispField() throws RichClientWebException {
        super();
        
        // ��Ŀ�ļ���ȡ
        readFile();
    }
    
    /**
     * �������Ļ��.
     * @return me �������
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
     * ������Ŀ�ļ��Ķ�ȡ
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
     * �ļ����������ļ���ȡ
     * @param dir �ļ���ȡ·��
     * @param ext ��ȡ�ļ��ĺ�׺��
     * @return ��Ŀ���ݱ������KEY���ļ�����value���ļ����������У�
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
     * @Description: ����.fld����.rsc�ļ���Ϣ
     * @author king
     * @since Oct 10, 2012 2:11:25 PM 
     * 
     * @param map ��Ϣ��������
     * @param file �ļ�����
     * @param ext ��չ��
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
     * KEY������ID����Ӧ����Ŀؼ�������Ϣ��.
     * 
     * @param key ����ID
     * @return ����ID��Ӧ��MAP
     * @since 2008/05/30
     */
    public Map<String, String> getField(String key) {
        if (fieldMap == null) {
            return null;
        }
        return fieldMap.get(key);
    }
    
    /**
     * KEY������ID����ĿID����Ӧ����Ŀ���ͷ���.
     * @param dispNo ����ID
     * @param fieldNo ��ĿID
     * @return ��Ŀ����
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
     * ����ID��Ӧ����Դ�ļ���Ϣȡ��.
     * @return ��Դ�ļ���Ϣ
     */
    public Map<String, Map<String, String>> getResourceMap() {
        return resourceMap;
    }
    
    /**
     * KEY������ID����ĿID����Ӧ����Դ��Ϣȡ��.
     * @param dispNo ����ID
     * @param fieldNo ��ĿID
     * @return ��Դ��Ϣ
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
     * KEY������ID����ĿID������ID����Ӧ����Դ��Ϣȡ��.
     * @param dispNo ����ID
     * @param fieldNo ��ĿID
     * @param idx ��Ŀ���
     * @return ��Դ��Ϣ
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
     * �ļ�·��ȡ��.
     * @return �ļ�·��
     */
    public static String getPath() {
        return sPath;
    }

    /**
     * �ļ�·���趨.
     * @param path �ļ�·��
     */
    public static void setPath(String path) {
        ControlDispField.sPath = path + DIR_DISP;
    }

    /**
     * ȡ�û�����Ŀ���������ĿID = ��Ŀ���ͣ�.
     * @return ������Ŀ�������
     */
    public Map<String, Map<String, String>> getFieldMap() {
        return fieldMap;
    }
}
