package com.richClientFrame.dao;

import com.richClientFrame.data.db.PageBean;
import com.richClientFrame.data.param.Param;

import org.springframework.dao.DataAccessException;

/**
 * ��Ŀ���� �� Web2.0��������
 * ������ �� IDbExcute
 * ������ �� ���ݿ����ӽӿ�.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.11.08
 * @author king
 */
public interface IDbExcute {
    
    /**
     * ��ҳ��ѯ.
     * @param sqlID sql ID
     * @param pageSize ÿҳ��ʾ������
     * @param param request
     * @return ��ѯ���
     */
    PageBean queryPages(String sqlID, int pageSize, Param param);
    
    /**
     * ��ҳ��ѯ.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @param pageSize ÿҳ��ʾ������
     * @param param request
     * @return ��ѯ���
     */
    PageBean queryPages(String sqlID, Object bindParams, int pageSize, Param param);
    
    /**
     * ��Ӹ������ݿ�.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     * @throws DataAccessException DataAccessException
     */
    TableRowMap insert(String sqlID, Object bindParams) throws DataAccessException;
    
    /**
     * ��Ӹ������ݿ�.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     * @throws DataAccessException DataAccessException
     */
    int update(String sqlID, Object bindParams) throws DataAccessException;
    
    /**
     * ��Ӹ������ݿ�.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @param tab ��Ϣ��������
     * @return �������
     * @throws DataAccessException DataAccessException
     */
    int update(String sqlID, Object bindParams, TableRowMap tab) throws DataAccessException;
    
    /**
     * ��ѯ��������.
     * @param sqlID sql ID
     * @return �������
     */
    TableRowMap query(String sqlID);
    
    /**
     * ��ѯ��������.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     */
    TableRowMap query(String sqlID, Object bindParams);
    
    /**
     * ��ѯ��������.
     * @param sqlID sql ID
     * @return �������
     */
    TableRowMap[] querys(String sqlID);
    
    /**
     * ��ѯ��������.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     */
    TableRowMap[] querys(String sqlID, Object bindParams);

}
