package com.richClientFrame.info;

import com.richClientFrame.data.SetValType;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ControlMasters
 * ������ �� ���ƹ���MASTER�ļ�������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2010.08.07
 * @author king
 */
public final class ControlMasters {
    
    private static LogUtil sLog = new LogUtil(ControlMasters.class, true);
    
    private static final String ERRMSG_SETVAL = "errmsg";
    
    private static final String MSG_HEADER = "headermsg";
    
    public static final int CTRL_HIST_SETVAL_CODE = 999;
    
    private Map<String, Map<String, SetValType>> errMsgMap = 
        new HashMap<String, Map<String, SetValType>>();
    
    private Map<String, Map<String, SetValType>> headerMsgMap = 
        new HashMap<String, Map<String, SetValType>>();
    
    private static ControlMasters sMe;
    
    private static String sPath;
    
    /**
     * �Զ���ȡ��.
     * @return ControlMasters �������
     * @throws RichClientWebException RichClientWebException
     */
    public static ControlMasters getInstance() throws RichClientWebException {
        
        synchronized (ControlMasters.class) {
            if (sMe == null) {
                sMe = new ControlMasters();
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
        ControlMasters.sPath = path;
    }
    
    /**
     * ��������ȡ��.
     * @param dispId ����ID
     * @param type �������
     * @return SetValType ��������
     */
    public SetValType getErrType(String dispId, String type) {
        if (errMsgMap == null) {
            return null;
        }
        final Map<String, SetValType> dispErrType = errMsgMap.get(dispId);
        if (dispErrType != null && dispErrType.get(type) != null) {
            return dispErrType.get(type);
        }
        return getErrType(type);
    }
    
    /**
     * ��������ȡ��.
     * @param type �������
     * @return SetValType ��������
     */
    public SetValType getErrType(String type) {
        if (errMsgMap.get(ERRMSG_SETVAL) == null) {
            return null;
        }
        return (errMsgMap.get(ERRMSG_SETVAL)).get(type);
    }
    
    /**
     * ͷ��������ȡ��.
     * @param type �������
     * @return SetValType ��������
     */
    public SetValType getHeaderType(String type) {
        if (headerMsgMap.get(MSG_HEADER) == null) {
            return null;
        }
        return (headerMsgMap.get(MSG_HEADER)).get(type);
    }
    

    /**
     * ͷ��������ȡ��.
     * @param dispId ����ID
     * @param type �������
     * @return SetValType ��������
     */
    public SetValType getHeaderType(String dispId, String type) {
        if (headerMsgMap == null) {
            return null;
        }
        final Map<String, SetValType> dispErrType = headerMsgMap.get(dispId);
        if (dispErrType != null && dispErrType.get(type) != null) {
            return dispErrType.get(type);
        }
        return getHeaderType(type);
    }
    
    /**
     * �����б�ȡ��.
     * @return �����б�
     */
    public String[] getScreedIdList() {
        String[] screendIds = null;
        
        if (errMsgMap != null) {
            final Set<String> workSet = errMsgMap.keySet();
            screendIds = workSet.toArray(new String[workSet.size()]);
        }
        
        return screendIds;
    }
    
    /**
     * ���캯��.
     * @throws RichClientWebException RichClientWebException
     */
    private ControlMasters() throws RichClientWebException {
        super();
        sLog.info("ControlMasters", "start", "sPath = " + sPath);
        final File dir = new File(sPath);
        if (!dir.isDirectory()) {
            return;
        }
        readFile(dir);
        sLog.info("ControlMasters", "end");
    }
    
    /**
     * @Description: ��ȡ�ļ���Ϣ
     * @author king
     * @since Apr 24, 2013 10:58:47 AM 
     * @version V1.0
     * @param file �ļ�����
     * @throws RichClientWebException RichClientWebException
     */
    private void readFile(File file) throws RichClientWebException {
        if (file.isDirectory()) {
            final File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                readFile(files[i]);
            }            
        } else {
            if (!(file.getName().endsWith(ConstantsUtil.Str.EXTENSION_XML))) {
                return;
            }
            final Map<String, SetValType> errorMap = new HashMap<String, SetValType>();
            final Map<String, SetValType> headerMap = new HashMap<String, SetValType>();
            SetValMstReader.read(file.getAbsolutePath(), errorMap, headerMap);
            final String[] fileName = file.getName().split(ConstantsUtil.Str.REGEX_DOT);
            if (fileName != null) {
                errMsgMap.put(CommonUtil.nvl(fileName[0]), errorMap);
                headerMsgMap.put(CommonUtil.nvl(fileName[0]), headerMap);
            }
            
        }
    }
}
