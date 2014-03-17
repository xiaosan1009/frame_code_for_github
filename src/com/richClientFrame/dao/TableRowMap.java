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
 * 项目名称 ： Web2.0开发引擎
 * 类名称 ： TableRowMap
 * 类描述 ： 数据库交互通用类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19 3:08:20 PM 
 * @author king
 */
public class TableRowMap implements Map<String, Object>, Serializable {

    private static final long serialVersionUID = 1L;

    private final LogUtil log = new LogUtil(this.getClass());
    
    private Map<String, Object> dataMap;
    
    /*
     * 对应<style>标签中的<property>属性，如果<property>属性为'*'，将遍历所有的画面项目，
     * 并根据后台对此属性的设置，判断相应的显示结果
     * 
     * 外围MAP，key为控件ID，值为判断用MAP
     * 判断用MAP，key为<condition>标签的<compareSource><value>属性的#后的值，值为要跟<compareTarget><value>判断用的值
     *
     */
    private Map<String, Map<String, Object>> conditionMap;
    
    // 对应<result>标签中的<list>属性，如果<list>属性为true，相应的数组数据的key将保存在此属性中
    private Map<String, String> listMap = new HashMap<String, String>();
    
    // 多维数据存储数组
    private List<TableRowMap> datasList;
    
    // 此属性为存储要通过流的形式回写给调用接口方的值
    private String outPut;
    
    private Object tabValue;

    /**
     * 构造函数.
     */
    public TableRowMap() {
        dataMap = new HashMap<String, Object>();
        conditionMap = new HashMap<String, Map<String,Object>>();
    }
    
    /**
     * 构造函数.
     * 
     * @param map 信息保持MAP
     */
    public TableRowMap(Map<String, Object> map) {
        dataMap = new HashMap<String, Object>(map);
        conditionMap = new HashMap<String, Map<String,Object>>();
    }
    
    /**
     * 清空数据.
     */
    public void clear() {
        dataMap.clear();
    }

    /**
     * 判断是否包含指定KEY.
     * 
     * @param arg0 KEY
     * @return 判断结果
     */
    public boolean containsKey(Object arg0) {
        return dataMap.containsKey(arg0);
    }
    
    /**
     * 判断是否包含指定KEY.
     * 
     * @param key 对应画面控件ID
     * @param arg0 KEY
     * @return 判断结果
     */
    public boolean containsKey(String key, Object arg0) {
        if (conditionMap.get(key) == null) {
            return false;
        }
        return conditionMap.get(key).containsKey(arg0);
    }

    /**
     * 判断是否包含指定VALUE.
     * 
     * @param arg0 VALUE
     * @return 判断结果
     */
    public boolean containsValue(Object arg0) {
        return dataMap.containsValue(arg0);
    }
    
    /**
     * 判断是否包含指定VALUE.
     * 
     * @param key 对应画面控件ID
     * @param arg0 VALUE
     * @return 判断结果
     */
    public boolean containsValue(String key, Object arg0) {
        if (conditionMap.get(key) == null) {
            return false;
        }
        return conditionMap.get(key).containsValue(arg0);
    }

    /**
     * 取得数据列表.
     * 
     * @return 数据列表
     */
    public Set<Map.Entry<String, Object>> entrySet() {
        return dataMap.entrySet();
    }

    /**
     * 取得对应KEY的数据.
     * 
     * @param key KEY
     * @return 数据
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
     * 取得对应KEY的数据.
     * 
     * @param key 对应画面控件ID
     * @param arg0 KEY
     * @return 数据
     */
    public Object get(String key, String arg0) {
        return conditionMap.get(key).get(arg0);
    }
    
    /**
     * 取得对应KEY的数据.
     * 
     * @param key KEY
     * @return 数据
     */
    public String getString(Object key) {
        return CommonUtil.toString(get(key));
    }
    
    /**
     * 取得对应KEY的数据.
     * 
     * @param key 对应画面控件ID
     * @param arg0 KEY
     * @return 数据
     */
    public String getString(String key, String arg0) {
        return CommonUtil.toString(conditionMap.get(key).get(arg0));
    }

    /**
     * 判断是否MAP为空.
     * 
     * @return 判断结果
     */
    public boolean isEmpty() {
        return dataMap.isEmpty();
    }
    
    /**
     * 判断是否MAP为空.
     * 
     * @param key 对应画面控件ID
     * @return 判断结果
     */
    public boolean isEmpty(String key) {
        return conditionMap.get(key).isEmpty();
    }

    /**
     * 取得数据列表.
     * 
     * @return 数据列表
     */
    public Set<String> keySet() {
        return dataMap.keySet();
    }
    
    /**
     * 取得数据列表.
     * 
     * @return 数据列表
     */
    public Set<String> conditionKeySet() {
        return conditionMap.keySet();
    }

    /**
     * 保存数据.
     * 
     * @param key KEY
     * @param value VALUE
     * @return 数据容器
     */
    public Object put(String key, Object value) {
        return dataMap.put(key, value);
    }
    
    /**
     * 保存数据.
     * 
     * @param key 对应画面控件ID
     * @param arg0 KEY
     * @param value VALUE
     * @return 数据容器
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
     * 保存数据.
     * 
     * @param t 要保存的数据容器
     */
    public void putAll(Map<? extends String, ? extends Object> t) {
        dataMap.putAll(t);
    }
    
    /**
     * 保存数据.
     * 
     * @param t 要保存的数据容器
     */
    public void putAll(TableRowMap t) {
        dataMap.putAll(t.getDataMap());
    }

    /**
     * 移除对应KEY的数据.
     * 
     * @param key KEY
     * @return 数据容器
     */
    public Object remove(Object key) {
        return dataMap.remove(key);
    }

    /**
     * 数据数量的取得.
     * 
     * @return 数据数量
     */
    public int size() {
        return dataMap.size();
    }

    /**
     * 所有数据的值的取得.
     * 
     * @return 所以数据的值
     */
    public Collection<Object> values() {
        return dataMap.values();
    }

    /**
     * 数据报文的取得.
     * 
     * @return 数据报文
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
     * 数据报文的设置.
     * 
     * @param data 数据报文
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
