package com.richClientFrame.info;

import com.richClientFrame.util.MemcachedUtil;

import java.util.Date;
import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ConfigurationReader
 * ������ �� memcached���������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� ��2010.03.19
 * @author king
 */
public class ContorlMemcached {
    
    /**
     * ȡ��memcached�д洢�Ķ���.
     * @param key memcached�д洢�����key
     * @return memcached�д洢�Ķ���
     */
    public Object get(String key) {
        return MemcachedUtil.getInstance().get(key);
    }
    
    /**
     * ����memcached�д洢�Ķ���.
     * @param key memcached�д洢�����key
     * @param value memcached�д洢�����ֵ
     * @return memcached�д洢�Ķ���
     */
    public boolean set(String key, Object value) {
        return MemcachedUtil.getInstance().set(key, value);
    }
    
    /**
     * ����memcached�д洢�Ķ���[������Чʱ��].
     * @param key memcached�д洢�����key
     * @param value memcached�д洢�����ֵ
     * @param expiry memcached�д洢����Ĵ���ʱ��
     * @return memcached�д洢�Ķ���
     */
    public boolean set(String key, Object value, long expiry) {
        return MemcachedUtil.getInstance().set(key, value, expiry);
    }
    
    /**
     * ȡ��memcached�д洢�Ķ���[MAP].
     * @param id memcached�д洢�����key
     * @return memcached�д洢�Ķ���
     */
    public Map getMap(String id) {
        return MemcachedUtil.getInstance().getMap(id);
    }
    
    /**
     * ����memcached�д洢�Ķ���[MAP].
     * @param id memcached�д洢�����key
     * @param valueMap memcached�д洢�����ֵ[MAP]
     * @return ���ý��
     */
    public boolean setMap(String id, Map valueMap) {
        return MemcachedUtil.getInstance().setMap(id, valueMap);
    }
    
    /**
     * ����memcached�д洢�Ķ���[MAP].
     * @param id memcached�д洢�����key
     * @param valueMap memcached�д洢�����ֵ
     * @param expiry memcached�д洢����Ĵ���ʱ��
     * @return ���ý��
     */
    public boolean setMap(String id, Map valueMap, Date expiry) {
        return MemcachedUtil.getInstance().setMap(id, valueMap, expiry);
    }
    
    /**
     * ��memcached���Ƴ���Ӧkey��ֵ.
     * @param id memcached���Ƴ���Ӧkey
     * @return �Ƴ����
     */
    public boolean remove(String id) {
        return MemcachedUtil.getInstance().remove(id);
    }
    
    /**
     * ȡ��memcached�ͻ��˵�״̬.
     * @return memcached�ͻ��˵�״̬
     */
    public Map stats() {
        return MemcachedUtil.getInstance().stats();
    }
    
    /**
     * �ж�memcached�������Ƿ�����..
     * @return memcached�������Ƿ�����
     */
    public boolean isNotConnection() {
        return MemcachedUtil.getInstance().isNotConnection();
    }

}
