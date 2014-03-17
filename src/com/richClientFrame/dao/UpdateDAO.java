package com.richClientFrame.dao;

import org.springframework.dao.DataAccessException;

/**
 * ��Ŀ���� �� Web2.0��������
 * ������ �� UpdateDAO
 * ������ �� ���ݿ����ɾ����ӿ�.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
 * @author king
 */
public interface UpdateDAO {

    /**
     * �����ݿ��޸Ľ������.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @return ���ݿ��޸Ľ��
     */
    int update(String sqlID, Object bindParams) throws DataAccessException;
    
    /**
     * �����ݿ��޸Ľ������.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @param tab ��Ϣ��������
     * @return ���ݿ��޸Ľ��
     */
    int update(String sqlID, Object bindParams, TableRowMap tab) throws DataAccessException;
    
    /**
     * �����ݿ���ӽ������.
     * 
     * @param sqlID SQL��ID
     * @param bindParams SQL��ID
     * @return ���ݿ���ӽ��
     */
    TableRowMap insert(String sqlID, Object bindParams) throws DataAccessException;

}
