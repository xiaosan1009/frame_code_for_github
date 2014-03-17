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
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ControlErrorMap
 * 类描述 ： 错误信息保存类（文件为error.mst）.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2010.05.30
 * @author king
 */
public final class ControlErrorMap {
    
    private static LogUtil sLog = new LogUtil(ControlErrorMap.class, true);
    
    /**
     * 必须输入项目（位数）.
     */
    private static final int DATA_LENGTH_REQUIRED_ITEM = 1;
    
    /**
     * 错误类型（位数）.
     */
    private static final int DATA_LENGTH_ERRTYPE = 2;
    
    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~必须项目参数~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    
    /**
     * 必须项目.
     */
    public static final int IS_REQUIRED = 1;
    
    /**
     * 非必须项目.
     */
    public static final int NOT_REQUIRED = 0;
    
    /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~错误类型参数~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    
    /**
     * 不需要验证.
     */
    public static final int CHECK_DEF = 0;
    
    /**
     * LONG型验证.
     */
    public static final int CHECK_LONG = 1;
    
    /**
     * FLOAT型验证.
     */
    public static final int CHECK_FLOAT = 2;
    
    /**
     * TIME型验证.
     */
    public static final int CHECK_TIME = 3;
    
    /**
     * DATE型验证.
     */
    public static final int CHECK_DATE = 4;
    
    /**
     * DATETIME型验证.
     */
    public static final int CHECK_DATETIME = 5;
    
    /**
     * LAST型验证（最后一天）.
     */
    public static final int CHECK_LAST = 6;
    
    /**
     * Y_RNG型验证（年范围）.
     */
    public static final int CHECK_Y_RNG = 7;
    
    /**
     * YM_RNG型验证（年月范围）.
     */
    public static final int CHECK_YM_RNG = 8;
    
    /**
     * DATE_RNG型验证（年月日范围）.
     */
    public static final int CHECK_DATE_RNG = 9;
    
    /**
     * PAST型验证（过去年月日）.
     */
    public static final int CHECK_PAST = 10;
    
    /**
     * FUTURE型验证（未来年月日）.
     */
    public static final int CHECK_FUTURE = 11;
    
    /**
     * STRING型验证.
     */
    public static final int CHECK_STRING = 12;
    
    /**
     * 文件类型验证.
     */
    public static final int CHECK_FILE = 13;
    
    /**
     * 文件名
     */
    private static final String DEF_ERRFILE = "WEB-INF" + File.separator + "err";
    /**
     * 自身对象
     */
    private static ControlErrorMap sMe;
    
    /**
     * 项目错误信息保存对象
     */
    private Map<String, Map<String,String>> sErrDisp;
    
    /**
     * 共通错误信息保存对象
     */
    private Map<String, String> mErrMust;
    
    /**
     * 路径
     */
    private static String sPath;
    
    /**
     * 构造函数
     * @throws RichClientWebException RichClientWebException
     */
    @SuppressWarnings("unchecked")
    private ControlErrorMap() throws RichClientWebException {
        super();
        
        // 文件读取
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
     * 自对象取得.
     * @return RequestMap 自身对象
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
     * 画面ID对应的画面项目取得.
     * @param param request
     * @return 取得需要验证的项目
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
     * 项目是不是必须的.
     * @param param request
     * @param fieldNo 项目ID
     * @return 是否为必须项
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
     * 错误种类的取得.
     * @param param request
     * @param fieldNo 项目ID
     * @return 错误类型
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
     * 项目需要验证的条件集合.
     * @param param request
     * @param fieldNo 项目ID
     * @return 错误类型序号
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
     * 项目需要验证的条件序列.
     * @param dispNo 画面ID
     * @param fieldNo 项目ID
     * @return 错误序号
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
     * 文件路径取得.
     * 
     * @return path 文件路径
     */
    public static String getPath() {
        return sPath;
    }

    /**
     * 文件路径设定.
     * @param path 文件路径
     */
    public static void setPath(String path) {
        ControlErrorMap.sPath = path + DEF_ERRFILE;
    }

    /**
     * .err文件信息的保存
     * @param path .err文件路径
     * @param extension 文件后缀
     * @return 画面对象 key:项目ID value:项目验证错误号
     * @throws RichClientWebException RichClientWebException
     */
    private Map<String, Map<String,String>> readErrorField(String path, String extension) 
        throws RichClientWebException {
        
        sLog.info("readErrorField", "start", "path = " + path, "extension = " + extension);
        
        // 画面项目集合
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
    * @Description: 解析.err文件信息
    * @author king
    * @since Oct 10, 2012 2:11:25 PM 
    * 
    * @param retMap 信息保持容器
    * @param file 文件对象
    * @param extension 扩展名
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
            // 项目集合
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
     * .err项目的读取.
     * 
     * @param file 错误文件对象
     * @param enc 后缀
     * @return 错误信息
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
                    // key:项目ID , value: 项目验证值
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
     * 画面ID结合TAG ID.
     * 
     * @param param request
     * @return 错误信息
     */
    private String getDispCode(Param param) {
        return param.dispCode;
    }

    /**
     * 共通错误信息保存对象取得.
     * 
     * @return 共通错误信息保存对象
     */
    public Map<String, String> getErrMust() {
        return mErrMust;
    }

    /**
     * 项目错误信息保存对象取得.
     * 
     * @return 项目错误信息保存对象
     */
    public Map<String, Map<String, String>> getErrDisp() {
        return sErrDisp;
    }
}
