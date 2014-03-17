package com.richClientFrame.info;

import com.richClientFrame.data.access.LoadProperties;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ControlErrorMap
 * ������ �� ������Ϣ�����ࣨ�ļ�Ϊerror.mst��.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2010.05.30
 * @author king
 */
public final class ControlErrorMap {
    
    private static LogUtil sLog = new LogUtil(ControlErrorMap.class, true);
    
    /**
     * ����������Ŀ��λ����.
     */
    private static final int DATA_LENGTH_REQUIRED_ITEM = 1;
    
    /**
     * �������ͣ�λ����.
     */
    private static final int DATA_LENGTH_ERRTYPE = 2;
    
    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~������Ŀ����~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    
    /**
     * ������Ŀ.
     */
    public static final int IS_REQUIRED = 1;
    
    /**
     * �Ǳ�����Ŀ.
     */
    public static final int NOT_REQUIRED = 0;
    
    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~�������Ͳ���~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    
    /**
     * ����Ҫ��֤.
     */
    public static final int CHECK_DEF = 0;
    
    /**
     * LONG����֤.
     */
    public static final int CHECK_LONG = 1;
    
    /**
     * FLOAT����֤.
     */
    public static final int CHECK_FLOAT = 2;
    
    /**
     * TIME����֤.
     */
    public static final int CHECK_TIME = 3;
    
    /**
     * DATE����֤.
     */
    public static final int CHECK_DATE = 4;
    
    /**
     * DATETIME����֤.
     */
    public static final int CHECK_DATETIME = 5;
    
    /**
     * LAST����֤�����һ�죩.
     */
    public static final int CHECK_LAST = 6;
    
    /**
     * Y_RNG����֤���귶Χ��.
     */
    public static final int CHECK_Y_RNG = 7;
    
    /**
     * YM_RNG����֤�����·�Χ��.
     */
    public static final int CHECK_YM_RNG = 8;
    
    /**
     * DATE_RNG����֤�������շ�Χ��.
     */
    public static final int CHECK_DATE_RNG = 9;
    
    /**
     * PAST����֤����ȥ�����գ�.
     */
    public static final int CHECK_PAST = 10;
    
    /**
     * FUTURE����֤��δ�������գ�.
     */
    public static final int CHECK_FUTURE = 11;
    
    /**
     * STRING����֤.
     */
    public static final int CHECK_STRING = 12;
    
    /**
     * �ļ�������֤.
     */
    public static final int CHECK_FILE = 13;
    
    /**
     * �ļ���
     */
    private static final String DEF_ERRFILE = "WEB-INF" + File.separator + "err";
    /**
     * �������
     */
    private static ControlErrorMap sMe;
    
    /**
     * ��Ŀ������Ϣ�������
     */
    private Map<String, Map<String,String>> sErrDisp;
    
    /**
     * ��ͨ������Ϣ�������
     */
    private Map<String, String> mErrMust;
    
    /**
     * ·��
     */
    private static String sPath;
    
    /**
     * ���캯��
     * @throws RichClientWebException RichClientWebException
     */
    @SuppressWarnings("unchecked")
    private ControlErrorMap() throws RichClientWebException {
        super();
        
        // �ļ���ȡ
        sErrDisp = readErrorField(sPath, "err");
        try {
            mErrMust = LoadProperties.load(sPath, "mst");
        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, fne);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, ioe);            
        }
    }
    
    /**
     * �Զ���ȡ��.
     * @return RequestMap �������
     * @throws RichClientWebException RichClientWebException
     */
    public static ControlErrorMap getInstance() throws RichClientWebException {
        
        synchronized (ControlErrorMap.class) {
            if (sMe == null) {
                sMe = new ControlErrorMap();
            }
        }
        return sMe;
    }
    
    /**
     * ����ID��Ӧ�Ļ�����Ŀȡ��.
     * @param param request
     * @return ȡ����Ҫ��֤����Ŀ
     */
    public String[] getAllCheckFields(Param param) {
        
        String[] fieldIds = null;
        
        final String dispCode = getDispCode(param);
        
        Map<String,String> dispMap = null;
        if (sErrDisp != null) {
            dispMap = sErrDisp.get(dispCode);
        }
        
        if (dispMap != null) {
            fieldIds = (String[])dispMap.keySet().toArray(new String[dispMap.size()]);
        }
        
        
        return fieldIds;
    }
    
    /**
     * ��Ŀ�ǲ��Ǳ����.
     * @param param request
     * @param fieldNo ��ĿID
     * @return �Ƿ�Ϊ������
     */
    public String getRequiredType(Param param, String fieldNo) {
        
        final String dispCode = getDispCode(param);
        final Map<String,String> dispMap = sErrDisp.get(dispCode);
        String reqValue = null;
        if (dispMap != null) {
            final String errNo = (String)dispMap.get(fieldNo);
            if (errNo != null) {
                reqValue = errNo.substring(0, DATA_LENGTH_REQUIRED_ITEM);
            }
        }
        return reqValue;
    }
    
    /**
     * ���������ȡ��.
     * @param param request
     * @param fieldNo ��ĿID
     * @return ��������
     */
    public String getErrType(Param param, String fieldNo) {
        
        String errType = null;        
        
        final String dispCode = getDispCode(param);
        final Map<String,String> dispMap = sErrDisp.get(dispCode);
        if (dispMap != null) {
            final String errNo = (String)dispMap.get(fieldNo);
            if (errNo != null) {
                errType = errNo.substring(DATA_LENGTH_REQUIRED_ITEM, 
                        DATA_LENGTH_REQUIRED_ITEM + DATA_LENGTH_ERRTYPE);
            }
        }
        
        return errType;
    }
    
    /**
     * ��Ŀ��Ҫ��֤����������.
     * @param param request
     * @param fieldNo ��ĿID
     * @return �����������
     */
    public String[] getErrPattern(Param param, String fieldNo) {
        
        String[] errPat = null;
        
        final Map<String,String> dispMap = sErrDisp.get(param.dispCode);
        if (dispMap != null) {
            final String errNo = (String)dispMap.get(fieldNo);
            if (errNo != null) {
                final String pattern = errNo.substring(DATA_LENGTH_REQUIRED_ITEM, errNo.length());
                final String patternList = mErrMust.get(pattern);
                if (patternList != null) {
                    if (patternList.length() > 0) {
                        errPat = patternList.split(ConstantsUtil.Str.COMMA);
                    } else {
                        errPat = new String[0];
                    }
                }
            }
        }
        
        return errPat;
    }
    
    /**
     * ��Ŀ��Ҫ��֤����������.
     * @param dispNo ����ID
     * @param fieldNo ��ĿID
     * @return �������
     */
    public String getErrCheckNum(String dispNo, String fieldNo) {
        String errCheckNum = null;
        
        final Map<String,String> dispMap = sErrDisp.get(dispNo);
        if (dispMap != null) {
            errCheckNum = (String)(dispMap).get(fieldNo);
        }
        
        return errCheckNum;
    }

    /**
     * �ļ�·��ȡ��.
     * 
     * @return path �ļ�·��
     */
    public static String getPath() {
        return sPath;
    }

    /**
     * �ļ�·���趨.
     * @param path �ļ�·��
     */
    public static void setPath(String path) {
        ControlErrorMap.sPath = path + DEF_ERRFILE;
    }

    /**
     * .err�ļ���Ϣ�ı���
     * @param path .err�ļ�·��
     * @param extension �ļ���׺
     * @return ������� key:��ĿID value:��Ŀ��֤�����
     * @throws RichClientWebException RichClientWebException
     */
    private Map<String, Map<String,String>> readErrorField(String path, String extension) 
        throws RichClientWebException {
        
        sLog.info("readErrorField", "start", "path = " + path, "extension = " + extension);
        
        // ������Ŀ����
        final Map<String, Map<String,String>> retMap = new HashMap<String, Map<String,String>>();
        
        final File dir = new File(path);
        
        if (!dir.isDirectory()) {
            return null;
        }
        parse(retMap, dir, extension);
        
        sLog.info("readErrorField", "end");
        
        return retMap;
    }
    
    /** 
    * @Description: ����.err�ļ���Ϣ
    * @author king
    * @since Oct 10, 2012 2:11:25 PM 
    * 
    * @param retMap ��Ϣ��������
    * @param file �ļ�����
    * @param extension ��չ��
    * @throws RichClientWebException RichClientWebException
    */
    private void parse(Map<String, Map<String,String>> retMap, File file, String extension) 
        throws RichClientWebException {
        if (file.isDirectory()) {
            final File[] childFiles = file.listFiles();
            for (int j = 0; j < childFiles.length; j++) {
                parse(retMap, childFiles[j], extension);
            }
        } else {
            // ��Ŀ����
            Map<String, String> map = new HashMap<String, String>();
            
            if (!file.getName().endsWith(extension)) {
                return;
            }

            map = getAllLine(file, ControlConfig.DEF_CHARCODE);
            
            String fileName = file.getName();
            final int extIdx = fileName.indexOf(ConstantsUtil.Char.DOT + extension);
            fileName = fileName.substring(0, extIdx);
            retMap.put(fileName, map);
        }
    }
    
    /**
     * .err��Ŀ�Ķ�ȡ.
     * 
     * @param file �����ļ�����
     * @param enc ��׺
     * @return ������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    private static Map<String, String> getAllLine(File file, String enc) 
        throws RichClientWebException {
        
        final Map<String, String> map = new LinkedHashMap<String,String>();
        BufferedReader in = null;
        
        try {
            in = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file.getPath()), enc));
            
            while (in.ready()) {
                final String str = in.readLine();
                final String[] splitData = str.split(ConstantsUtil.Str.REGEX_EQUAL);

                String[] temp = null;
                if (splitData != null && splitData.length >= 2) {
                    temp = new String[2];
                    temp[0] = CommonUtil.nvl(splitData[0]);
                    temp[1] = CommonUtil.nvl(splitData[1]);
                }

                if (temp != null) {
                    // key:��ĿID , value: ��Ŀ��ֵ֤
                    map.put(temp[0], temp[1]);
                }
            }
            
        } catch (UnsupportedEncodingException ue) {
            throw new RichClientWebException(ue.toString());
        } catch (FileNotFoundException fnfe) {
            throw new RichClientWebException(fnfe.toString());
        } catch (IOException e) {
            throw new RichClientWebException(e.toString());
        } finally {
            if (in != null) {
                try {
                    in.close();
                    in = null;
                } catch (IOException ex) {
                    throw new RichClientWebException(ex.toString());
                }
            }
        }
        
        return map;
    }
    
    /**
     * ����ID���TAG ID.
     * 
     * @param param request
     * @return ������Ϣ
     */
    private String getDispCode(Param param) {
        return param.dispCode;
    }

    /**
     * ��ͨ������Ϣ�������ȡ��.
     * 
     * @return ��ͨ������Ϣ�������
     */
    public Map<String, String> getErrMust() {
        return mErrMust;
    }

    /**
     * ��Ŀ������Ϣ�������ȡ��.
     * 
     * @return ��Ŀ������Ϣ�������
     */
    public Map<String, Map<String, String>> getErrDisp() {
        return sErrDisp;
    }
}
