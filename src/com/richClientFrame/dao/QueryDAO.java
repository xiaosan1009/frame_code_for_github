package com.richClientFrame.dao;

import java.util.Map;

/**
 * ��Ŀ���� �� Web2.0��������
 * ������ �� QueryDAO
 * ������ �� ���ݿ��ѯ��ӿ�.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
 * @author king
 */
public interface QueryDAO {

    /**
     * �����ݿ��ѯ�����װ�ɶ��󷵻�.
     * 
     * @param <E> ���ؽ������
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @param clazz ���ؽ������
     * @return ���ݿ��ѯ���
     */
    <E> E executeForObject(String sqlID, Object bindParams, Class<?> clazz);
    
    /**
     * �����ݿ��ѯ�����װ�ɶ��󷵻�.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @return ���ݿ��ѯ�������
     */
    int executeForCount(String sqlID, Object bindParams);
    
    /**
     * �����ݿ��ѯ�����װ��TableRowMap����.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @return ���ݿ��ѯ���
     */
    TableRowMap executeForObject(String sqlID, Object bindParams);

    /**
     * �����ݿ��ѯ�����װ��Map<String, Object>����.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @return ���ݿ��ѯ���
     */
    Map<String, Object> executeForMap(String sqlID, Object bindParams);

    /**
     * �����ݿ��ѯ�����װ�ɶ��󷵻�.
     * 
     * @param <E> ���ؽ������
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @param clazz ���ؽ������
     * @return ���ݿ��ѯ���
     */
    <E> E[] executeForObjectArray(String sqlID, Object bindParams, Class<?> clazz);
    
    /**
     * �����ݿ��ѯ�����װ��TableRowMap[]����.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @return ���ݿ��ѯ���
     */
    TableRowMap[] executeForArray(String sqlID, Object bindParams);

    /**
     * �����ݿ��ѯ�����װ��Map<String, Object>[]����.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @return ���ݿ��ѯ���
     */
    Map<String, Object>[] executeForMapArray(String sqlID, Object bindParams);

    /**
     * �����ݿ��ѯ�����װ�ɶ��󷵻�.
     * 
     * @param <E> ���ؽ������
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @param clazz ���ؽ������
     * @param beginIndex ��ѯ��ʼ���
     * @param maxCount ����ѯ����
     * @return ���ݿ��ѯ���
     */
    <E> E[] executeForObjectArray(String sqlID, Object bindParams, Class<?> clazz, int beginIndex, int maxCount);
    
    /**
     * �����ݿ��ѯ�����װ��TableRowMap[]����.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @param beginIndex ��ѯ��ʼ���
     * @param maxCount ����ѯ����
     * @return ���ݿ��ѯ���
     */
    TableRowMap[] executeForArray(String sqlID, Object bindParams, int beginIndex, int maxCount);

    /**
     * �����ݿ��ѯ�����װ��Map<String, Object>[]����.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @param beginIndex ��ѯ��ʼ���
     * @param maxCount ����ѯ����
     * @return ���ݿ��ѯ���
     */
    Map<String, Object>[] executeForMapArray(String sqlID, Object bindParams, int beginIndex, int maxCount);
}