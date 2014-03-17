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
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ControlMasters
 * 类描述 ： 名称管理MASTER文件保存类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2010.08.07
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
     * 自对象取得.
     * @return ControlMasters 自身对象
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
        ControlMasters.sPath = path;
    }
    
    /**
     * 错误类型取得.
     * @param dispId 画面ID
     * @param type 错误序号
     * @return SetValType 错误类型
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
     * 错误类型取得.
     * @param type 错误序号
     * @return SetValType 错误类型
     */
    public SetValType getErrType(String type) {
        if (errMsgMap.get(ERRMSG_SETVAL) == null) {
            return null;
        }
        return (errMsgMap.get(ERRMSG_SETVAL)).get(type);
    }
    
    /**
     * 头错误类型取得.
     * @param type 错误序号
     * @return SetValType 错误类型
     */
    public SetValType getHeaderType(String type) {
        if (headerMsgMap.get(MSG_HEADER) == null) {
            return null;
        }
        return (headerMsgMap.get(MSG_HEADER)).get(type);
    }
    

    /**
     * 头错误类型取得.
     * @param dispId 画面ID
     * @param type 错误序号
     * @return SetValType 错误类型
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
     * 画面列表取得.
     * @return 画面列表
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
     * 构造函数.
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
     * @Description: 读取文件信息
     * @author king
     * @since Apr 24, 2013 10:58:47 AM 
     * @version V1.0
     * @param file 文件对象
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
