package com.richClientFrame.dao;

import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ��Ŀ���� �� Web2.0��������
 * ������ �� TableRowMap
 * ������ �� ���ݿ⽻��ͨ����.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19 3:08:20 PM 
 * @author king
 */
public class TableRowMap implements Map<String, Object>, Serializable {

    private static final long serialVersionUID = 1L;

    private final LogUtil log = new LogUtil(this.getClass());
    
    private Map<String, Object> dataMap;
    
    /*
     * ��Ӧ<style>��ǩ�е�<property>���ԣ����<property>����Ϊ'*'�����������еĻ�����Ŀ��
     * �����ݺ�̨�Դ����Ե����ã��ж���Ӧ����ʾ���
     * 
     * ��ΧMAP��keyΪ�ؼ�ID��ֵΪ�ж���MAP
     * �ж���MAP��keyΪ<condition>��ǩ��<compareSource><value>���Ե�#���ֵ��ֵΪҪ��<compareTarget><value>�ж��õ�ֵ
     *
     */
    private Map<String, Map<String, Object>> conditionMap;
    
    // ��Ӧ<result>��ǩ�е�<list>���ԣ����<list>����Ϊtrue����Ӧ���������ݵ�key�������ڴ�������
    private Map<String, String> listMap = new HashMap<String, String>();
    
    // ��ά���ݴ洢����
    private List<TableRowMap> datasList;
    
    // ������Ϊ�洢Ҫͨ��������ʽ��д�����ýӿڷ���ֵ
    private String outPut;
    
    private Object tabValue;

    /**
     * ���캯��.
     */
    public TableRowMap() {
        dataMap = new HashMap<String, Object>();
        conditionMap = new HashMap<String, Map<String,Object>>();
    }
    
    /**
     * ���캯��.
     * 
     * @param map ��Ϣ����MAP
     */
    public TableRowMap(Map<String, Object> map) {
        dataMap = new HashMap<String, Object>(map);
        conditionMap = new HashMap<String, Map<String,Object>>();
    }
    
    /**
     * �������.
     */
    public void clear() {
        dataMap.clear();
    }

    /**
     * �ж��Ƿ����ָ��KEY.
     * 
     * @param arg0 KEY
     * @return �жϽ��
     */
    public boolean containsKey(Object arg0) {
        return dataMap.containsKey(arg0);
    }
    
    /**
     * �ж��Ƿ����ָ��KEY.
     * 
     * @param key ��Ӧ����ؼ�ID
     * @param arg0 KEY
     * @return �жϽ��
     */
    public boolean containsKey(String key, Object arg0) {
        if (conditionMap.get(key) == null) {
            return false;
        }
        return conditionMap.get(key).containsKey(arg0);
    }

    /**
     * �ж��Ƿ����ָ��VALUE.
     * 
     * @param arg0 VALUE
     * @return �жϽ��
     */
    public boolean containsValue(Object arg0) {
        return dataMap.containsValue(arg0);
    }
    
    /**
     * �ж��Ƿ����ָ��VALUE.
     * 
     * @param key ��Ӧ����ؼ�ID
     * @param arg0 VALUE
     * @return �жϽ��
     */
    public boolean containsValue(String key, Object arg0) {
        if (conditionMap.get(key) == null) {
            return false;
        }
        return conditionMap.get(key).containsValue(arg0);
    }

    /**
     * ȡ�������б�.
     * 
     * @return �����б�
     */
    public Set<Map.Entry<String, Object>> entrySet() {
        return dataMap.entrySet();
    }

    /**
     * ȡ�ö�ӦKEY������.
     * 
     * @param key KEY
     * @return ����
     */
    public Object get(Object key) {
        if (dataMap.get(key) instanceof Clob) {
            String clobStr = ConstantsUtil.Str.EMPTY;
            final Clob clob = (Clob)dataMap.get(key);
            try {
                clobStr = clob.getSubString(1, (int)clob.length());
            } catch (SQLException e) {
                e.printStackTrace();
                return ConstantsUtil.Str.EMPTY;
//                throw new RichClientWebException(ConstantsUtil.Result.SYSTEM_ERROR, e);
            }
            return clobStr;
        }
        return dataMap.get(key);
    }
    
    /**
     * ȡ�ö�ӦKEY������.
     * 
     * @param key ��Ӧ����ؼ�ID
     * @param arg0 KEY
     * @return ����
     */
    public Object get(String key, String arg0) {
        return conditionMap.get(key).get(arg0);
    }
    
    /**
     * ȡ�ö�ӦKEY������.
     * 
     * @param key KEY
     * @return ����
     */
    public String getString(Object key) {
        return CommonUtil.toString(get(key));
    }
    
    /**
     * ȡ�ö�ӦKEY������.
     * 
     * @param key ��Ӧ����ؼ�ID
     * @param arg0 KEY
     * @return ����
     */
    public String getString(String key, String arg0) {
        return CommonUtil.toString(conditionMap.get(key).get(arg0));
    }

    /**
     * �ж��Ƿ�MAPΪ��.
     * 
     * @return �жϽ��
     */
    public boolean isEmpty() {
        return dataMap.isEmpty();
    }
    
    /**
     * �ж��Ƿ�MAPΪ��.
     * 
     * @param key ��Ӧ����ؼ�ID
     * @return �жϽ��
     */
    public boolean isEmpty(String key) {
        return conditionMap.get(key).isEmpty();
    }

    /**
     * ȡ�������б�.
     * 
     * @return �����б�
     */
    public Set<String> keySet() {
        return dataMap.keySet();
    }
    
    /**
     * ȡ�������б�.
     * 
     * @return �����б�
     */
    public Set<String> conditionKeySet() {
        return conditionMap.keySet();
    }

    /**
     * ��������.
     * 
     * @param key KEY
     * @param value VALUE
     * @return ��������
     */
    public Object put(String key, Object value) {
        return dataMap.put(key, value);
    }
    
    /**
     * ��������.
     * 
     * @param key ��Ӧ����ؼ�ID
     * @param arg0 KEY
     * @param value VALUE
     * @return ��������
     */
    public Object put(String key, String arg0, Object value) {
        Map<String, Object> conMap = new HashMap<String, Object>();
        if (containsKey(key, arg0)) {
            conMap = conditionMap.get(key);
        }
        conMap.put(arg0, value);
        return conditionMap.put(key, conMap);
    }

    /**
     * ��������.
     * 
     * @param t Ҫ�������������
     */
    public void putAll(Map<? extends String, ? extends Object> t) {
        dataMap.putAll(t);
    }
    
    /**
     * ��������.
     * 
     * @param t Ҫ�������������
     */
    public void putAll(TableRowMap t) {
        dataMap.putAll(t.getDataMap());
    }

    /**
     * �Ƴ���ӦKEY������.
     * 
     * @param key KEY
     * @return ��������
     */
    public Object remove(Object key) {
        return dataMap.remove(key);
    }

    /**
     * ����������ȡ��.
     * 
     * @return ��������
     */
    public int size() {
        return dataMap.size();
    }

    /**
     * �������ݵ�ֵ��ȡ��.
     * 
     * @return �������ݵ�ֵ
     */
    public Collection<Object> values() {
        return dataMap.values();
    }

    /**
     * ���ݱ��ĵ�ȡ��.
     * 
     * @return ���ݱ���
     */
    public String getData() {
        ByteArrayOutputStream byteStream = null;
        ObjectOutputStream objectStream = null;
        try {
            byteStream = new ByteArrayOutputStream();
            objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(dataMap);
            final byte[] byteData = byteStream.toByteArray();
            return CommonUtil.convertBinaryToHex(byteData);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        } finally {
            if (byteStream != null) {
                try {
                    byteStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error(e);
                }
            }
            if (objectStream != null) {
                try {
                    objectStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error(e);
                }
            }
        }
        return null;
    }

    /**
     * ���ݱ��ĵ�����.
     * 
     * @param data ���ݱ���
     */
    @SuppressWarnings("unchecked")
    public void setData(String data) {
        if (CommonUtil.isEmpty(data)) {
            return;
        }

        ByteArrayInputStream byteStream = null;
        ObjectInputStream objectStream = null;
        try {
            final byte[] byteData = CommonUtil.convertHexToBinary(data);

            byteStream = new ByteArrayInputStream(byteData);
            objectStream = new ObjectInputStream(byteStream);
            this.dataMap = (Map<String, Object>) objectStream.readObject();

        } catch (IOException e) {
            e.printStackTrace();
            log.error(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error(e);
        } finally {
            if (byteStream != null) {
                try {
                    byteStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error(e);
                }
            }
            if (objectStream != null) {
                try {
                    objectStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    log.error(e);
                }
            }
        }
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public Map<String, String> getListMap() {
        return listMap;
    }

    public void setListMap(Map<String, String> listMap) {
        this.listMap = listMap;
    }

    public String getOutPut() {
        return outPut;
    }

    public void setOutPut(String outPut) {
        this.outPut = outPut;
    }

    public List<TableRowMap> getDatasList() {
        return datasList;
    }

    public void setDatasList(List<TableRowMap> datasList) {
        this.datasList = datasList;
    }

    public interface Constant {
        public interface Update {
            String RESULT_EXECUTE = "UPDATE_EXECUTE";
        }
    }

    public Object getTabValue() {
        return tabValue;
    }

    public void setTabValue(Object tabValue) {
        this.tabValue = tabValue;
    }

}
