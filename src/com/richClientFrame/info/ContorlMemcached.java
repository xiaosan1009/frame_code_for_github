package com.richClientFrame.info;

import com.richClientFrame.util.MemcachedUtil;

import java.util.Date;
import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ConfigurationReader
 * 类描述 ： memcached对象控制类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2010.03.19
 * @author king
 */
public class ContorlMemcached {
    
    /**
     * 取得memcached中存储的对象.
     * @param key memcached中存储对象的key
     * @return memcached中存储的对象
     */
    public Object get(String key) {
        return MemcachedUtil.getInstance().get(key);
    }
    
    /**
     * 设置memcached中存储的对象.
     * @param key memcached中存储对象的key
     * @param value memcached中存储对象的值
     * @return memcached中存储的对象
     */
    public boolean set(String key, Object value) {
        return MemcachedUtil.getInstance().set(key, value);
    }
    
    /**
     * 设置memcached中存储的对象[设置生效时间].
     * @param key memcached中存储对象的key
     * @param value memcached中存储对象的值
     * @param expiry memcached中存储对象的存续时间
     * @return memcached中存储的对象
     */
    public boolean set(String key, Object value, long expiry) {
        return MemcachedUtil.getInstance().set(key, value, expiry);
    }
    
    /**
     * 取得memcached中存储的对象[MAP].
     * @param id memcached中存储对象的key
     * @return memcached中存储的对象
     */
    public Map getMap(String id) {
        return MemcachedUtil.getInstance().getMap(id);
    }
    
    /**
     * 设置memcached中存储的对象[MAP].
     * @param id memcached中存储对象的key
     * @param valueMap memcached中存储对象的值[MAP]
     * @return 设置结果
     */
    public boolean setMap(String id, Map valueMap) {
        return MemcachedUtil.getInstance().setMap(id, valueMap);
    }
    
    /**
     * 设置memcached中存储的对象[MAP].
     * @param id memcached中存储对象的key
     * @param valueMap memcached中存储对象的值
     * @param expiry memcached中存储对象的存续时间
     * @return 设置结果
     */
    public boolean setMap(String id, Map valueMap, Date expiry) {
        return MemcachedUtil.getInstance().setMap(id, valueMap, expiry);
    }
    
    /**
     * 在memcached中移除对应key的值.
     * @param id memcached中移除对应key
     * @return 移除结果
     */
    public boolean remove(String id) {
        return MemcachedUtil.getInstance().remove(id);
    }
    
    /**
     * 取得memcached客户端的状态.
     * @return memcached客户端的状态
     */
    public Map stats() {
        return MemcachedUtil.getInstance().stats();
    }
    
    /**
     * 判断memcached服务器是否连接..
     * @return memcached服务器是否连接
     */
    public boolean isNotConnection() {
        return MemcachedUtil.getInstance().isNotConnection();
    }

}
