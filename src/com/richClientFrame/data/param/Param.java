package com.richClientFrame.data.param;

import com.richClientFrame.data.SessionData;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.info.ControlRequestMap;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import org.apache.commons.fileupload.FileItem;

import sun.util.logging.resources.logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： Param
 * 类描述 ： 参数管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.07.28
 * @author king
 */
public class Param extends ThreadLocal<Param> {
    
    private static final LogUtil LOG = new LogUtil(Param.class, true);
    
    /**
     * 请求Table
     */
    private Map<String, String[]> reqTable;
    
    private Map<String, Object> valueMap;
    
    /**
     * 画面ID.
     */
    public String contextPath;
    
    /**
     * 客户端请求对象.
     */
    public HttpServletRequest httpServletRequest;
    
    /**
     * 画面ID.
     */
    public String dispCode = "";
    
    /**
     * 方法code.
     */
    public String cmdCode = "";
    
    /**
     * 目标ID.
     */
    public String targetId = "";
    
    /**
     * 目标ID序号.
     */
    public String targetIndex = "";
    
    /**
     * 数据列表的选择行号序号.
     */
    public String listIndex = "";
    
    /**
     * 数据源地址.
     */
    public String dataSource;
    
    /**
     * 数据源地址.
     */
    public String inputSteam;
    
    /**
     * 客户端类型.
     */
    public String clientType = ConstantsUtil.Client.AJAX_TYPE;
    
    /**
     * 客户端类型.
     */
    public String checker;
    
    /**
     * 上传文件对象.
     */
    private FileItem uploadItem;
    
    private List<FileItem> uploadItemList;

    /**
     * 构造函数.
     */
    public Param() {
        super();
        reqTable = new HashMap<String, String[]>();
        valueMap = new HashMap<String, Object>();
    }

    /**
     * 请求情报取得.
     * @param request HttpServletRequest 请求对象
     * @throws RichClientWebException RichClientWebException
     */
    public void pickParameters(HttpServletRequest request, boolean getInputStream) 
        throws RichClientWebException {
        // 请求Table生成
        copyParameters(request);
        
        httpServletRequest = request;
        
        contextPath = request.getContextPath();
        // 画面ID
        dispCode = CommonUtil.toString(get(ConstantsUtil.Param.DISP));
        // 方法code
        cmdCode = CommonUtil.toString(get(ConstantsUtil.Param.CMD));
        // 目标ID
        targetId = CommonUtil.toString(get(ConstantsUtil.Param.TARGET));
        // 目标ID序号
        targetIndex = CommonUtil.toString(get(ConstantsUtil.Param.TARGET_IDX));
        // 目标ID序号
        clientType = CommonUtil.nvl(get(ConstantsUtil.Param.CLIENT_TYPE), ConstantsUtil.Client.AJAX_TYPE);
        // 请求验证KEY
        checker = CommonUtil.toString(get(ConstantsUtil.Param.CHECKER));
        // 数据列表的选择行号
        listIndex = CommonUtil.toString(get(ConstantsUtil.Param.LIST_INDEX));
        
        if (ControlRequestMap.getInstance().isInputStream(dispCode, cmdCode) && getInputStream) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(
                        (ServletInputStream)request.getInputStream(), "UTF-8"));
                final StringBuffer input = new StringBuffer();
                String data = ConstantsUtil.Str.EMPTY;
                while ((data = br.readLine()) != null) {
                    input.append(data);
                }
                inputSteam = input.toString();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new RichClientWebException(EngineExceptionEnum.FM_COM_UNSUPPORTED_ENCODING_ERROR, e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
                    }
                }
            }
        }
    }

    /**
     * 把请求参数中的数据保存在请求Table中
     * @param request HttpServletRequest 请求对象
     */
    @SuppressWarnings("unchecked")
    private void copyParameters(HttpServletRequest request) {
        LOG.info("copyParameters", "start");
        this.reqTable.putAll(request.getParameterMap());
        LOG.info("copyParameters", "end");
    }
    
    /**
     * 手动往请求Table中设置数据.
     * 
     * @param name 容器KEY
     * @param values 数据
     */
    public void putParameters(String name, String[] values) {
        this.reqTable.put(name, values);
    }
    
    /**
     * 请求Table中数据的取得.
     * @param key 画面项目KEY
     * @return 数据值
     */
    public String get(String key) {
        
        final String[] value = (String[])this.reqTable.get(key);
        
        if (value == null) {
            return null;
        } else {
            return value[0];
        }
    }
    
    /**
     * 设置Table中的数据.
     * @param key 画面项目KEY
     * @param value 值
     */
    public void set(String key, String value) {
        reqTable.put(key, new String[] {value});
    }
    
    /**
     * 设置Table中的数据.
     * @param key 画面项目KEY
     * @param value 值
     */
    public void set(String key, String[] value) {
        reqTable.put(key, value);
    }
    
    /**
     * @Description: 
     * @author king
     * @since Sep 6, 2013 11:25:16 AM 
     * @version V1.0
     * @param key key
     * @param value value
     */
    public void setValue(String key, Object value) {
        valueMap.put(key, value);
    }
    
    /**
     * @Description: 
     * @author king
     * @since Sep 6, 2013 11:25:55 AM 
     * @version V1.0
     * @param key key
     * @return valueMap
     */
    public Object getValue(String key) {
        return valueMap.get(key);
    }
    
    /**
     * 请求Table中数据的取得.
     * @param key 画面KEY
     * @param index 画面项目KEY
     * @return 数据值
     */
    public String get(String key, int index) {
        
        final String[] value = (String[])this.reqTable.get(key);
        
        if (value.length == 0) {
            return ConstantsUtil.Str.EMPTY;
        }
        
        if (index >= value.length) {
            index = 0;
        }
        
        return value[index];
    }
    
    /**
     * 请求Table中复数数据的取得.
     * @param key 画面项目KEY
     * @return 复数数据
     */
    public String[] getList(String key) {
        
        final String[] value = (String[])this.reqTable.get(key);
        
        return value;
    }
    
    /**
     * 判断请求Table中是否包含画面项目KEY.
     * @param key 画面项目KEY
     * @return 判断结果
     */
    public boolean containsKey(String key) {
        return this.reqTable.containsKey(key);
    }
    
    /**
     * 请求Table中复数数据的数量取得.
     * @param key  画面项目KEY
     * @return 请求Table中复数数据的数量
     */
    public int getSize(String key) {
        
        int size = 0;
        
        final String[] value = (String[])this.reqTable.get(key);
        if (value != null) {
            size = value.length;
        }
        
        return size;
    }

    /**
     * 参数MAP取得.
     * @return 参数MAP
     */
    public Map<String, String[]> getReqTable() {
        return reqTable;
    }

    /**
     * 上传对象取得.
     * @return 上传对象
     */
    public FileItem getUploadItem() {
        return uploadItem;
    }

    /**
     * 上传对象设置.
     * @param uploadItem 上传对象
     */
    public void setUploadItem(FileItem uploadItem) {
        this.uploadItem = uploadItem;
        if (uploadItemList == null) {
            uploadItemList = new ArrayList<FileItem>();
        }
        uploadItemList.add(uploadItem);
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getContextPath() {
        return contextPath;
    }

    public String getInputSteam() {
        return inputSteam;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public List<FileItem> getUploadItemList() {
        return uploadItemList;
    }

}
