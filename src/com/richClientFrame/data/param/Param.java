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
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� Param
 * ������ �� ����������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.07.28
 * @author king
 */
public class Param extends ThreadLocal<Param> {
    
    private static final LogUtil LOG = new LogUtil(Param.class, true);
    
    /**
     * ����Table
     */
    private Map<String, String[]> reqTable;
    
    private Map<String, Object> valueMap;
    
    /**
     * ����ID.
     */
    public String contextPath;
    
    /**
     * �ͻ����������.
     */
    public HttpServletRequest httpServletRequest;
    
    /**
     * ����ID.
     */
    public String dispCode = "";
    
    /**
     * ����code.
     */
    public String cmdCode = "";
    
    /**
     * Ŀ��ID.
     */
    public String targetId = "";
    
    /**
     * Ŀ��ID���.
     */
    public String targetIndex = "";
    
    /**
     * �����б��ѡ���к����.
     */
    public String listIndex = "";
    
    /**
     * ����Դ��ַ.
     */
    public String dataSource;
    
    /**
     * ����Դ��ַ.
     */
    public String inputSteam;
    
    /**
     * �ͻ�������.
     */
    public String clientType = ConstantsUtil.Client.AJAX_TYPE;
    
    /**
     * �ͻ�������.
     */
    public String checker;
    
    /**
     * �ϴ��ļ�����.
     */
    private FileItem uploadItem;
    
    private List<FileItem> uploadItemList;

    /**
     * ���캯��.
     */
    public Param() {
        super();
        reqTable = new HashMap<String, String[]>();
        valueMap = new HashMap<String, Object>();
    }

    /**
     * �����鱨ȡ��.
     * @param request HttpServletRequest �������
     * @throws RichClientWebException RichClientWebException
     */
    public void pickParameters(HttpServletRequest request, boolean getInputStream) 
        throws RichClientWebException {
        // ����Table����
        copyParameters(request);
        
        httpServletRequest = request;
        
        contextPath = request.getContextPath();
        // ����ID
        dispCode = CommonUtil.toString(get(ConstantsUtil.Param.DISP));
        // ����code
        cmdCode = CommonUtil.toString(get(ConstantsUtil.Param.CMD));
        // Ŀ��ID
        targetId = CommonUtil.toString(get(ConstantsUtil.Param.TARGET));
        // Ŀ��ID���
        targetIndex = CommonUtil.toString(get(ConstantsUtil.Param.TARGET_IDX));
        // Ŀ��ID���
        clientType = CommonUtil.nvl(get(ConstantsUtil.Param.CLIENT_TYPE), ConstantsUtil.Client.AJAX_TYPE);
        // ������֤KEY
        checker = CommonUtil.toString(get(ConstantsUtil.Param.CHECKER));
        // �����б��ѡ���к�
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
     * ����������е����ݱ���������Table��
     * @param request HttpServletRequest �������
     */
    @SuppressWarnings("unchecked")
    private void copyParameters(HttpServletRequest request) {
        LOG.info("copyParameters", "start");
        this.reqTable.putAll(request.getParameterMap());
        LOG.info("copyParameters", "end");
    }
    
    /**
     * �ֶ�������Table����������.
     * 
     * @param name ����KEY
     * @param values ����
     */
    public void putParameters(String name, String[] values) {
        this.reqTable.put(name, values);
    }
    
    /**
     * ����Table�����ݵ�ȡ��.
     * @param key ������ĿKEY
     * @return ����ֵ
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
     * ����Table�е�����.
     * @param key ������ĿKEY
     * @param value ֵ
     */
    public void set(String key, String value) {
        reqTable.put(key, new String[] {value});
    }
    
    /**
     * ����Table�е�����.
     * @param key ������ĿKEY
     * @param value ֵ
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
     * ����Table�����ݵ�ȡ��.
     * @param key ����KEY
     * @param index ������ĿKEY
     * @return ����ֵ
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
     * ����Table�и������ݵ�ȡ��.
     * @param key ������ĿKEY
     * @return ��������
     */
    public String[] getList(String key) {
        
        final String[] value = (String[])this.reqTable.get(key);
        
        return value;
    }
    
    /**
     * �ж�����Table���Ƿ����������ĿKEY.
     * @param key ������ĿKEY
     * @return �жϽ��
     */
    public boolean containsKey(String key) {
        return this.reqTable.containsKey(key);
    }
    
    /**
     * ����Table�и������ݵ�����ȡ��.
     * @param key  ������ĿKEY
     * @return ����Table�и������ݵ�����
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
     * ����MAPȡ��.
     * @return ����MAP
     */
    public Map<String, String[]> getReqTable() {
        return reqTable;
    }

    /**
     * �ϴ�����ȡ��.
     * @return �ϴ�����
     */
    public FileItem getUploadItem() {
        return uploadItem;
    }

    /**
     * �ϴ���������.
     * @param uploadItem �ϴ�����
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
