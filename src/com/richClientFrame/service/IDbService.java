package com.richClientFrame.service;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.db.PageBean;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.iface.IExpandFace;

import org.springframework.dao.DataAccessException;

/**
 * ��ͨDB��������ӿ�.
 * @author king
 * @since 2012.03.28
 */
public interface IDbService {

    /**
     * ��ҳ��ѯ.
     * @param sqlID sql ID
     * @param pageSize ÿҳ��ʾ������
     * @param param request
     * @return ��ѯ���
     * @throws RichClientWebException RichClientWebException
     */
    PageBean queryPages(String sqlID, int pageSize, Param param) 
        throws RichClientWebException;
    
    /**
     * ��ҳ��ѯ.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @param pageSize ÿҳ��ʾ������
     * @param param request
     * @return ��ѯ���
     * @throws RichClientWebException RichClientWebException
     */
    PageBean queryPages(String sqlID, Object bindParams, int pageSize, Param param) 
        throws RichClientWebException;
    
    /**
     * ��Ӹ������ݿ�.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     * @throws DataAccessException DataAccessException
     * @throws RichClientWebException RichClientWebException
     */
    TableRowMap insert(String sqlID, Object bindParams) 
        throws DataAccessException, RichClientWebException;
    
    /**
     * ��Ӹ������ݿ�.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     * @throws DataAccessException DataAccessException
     * @throws RichClientWebException RichClientWebException
     */
    int update(String sqlID, Object bindParams) 
        throws DataAccessException, RichClientWebException;
    
    /**
     * ��Ӹ������ݿ�.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @param tab ��Ϣ��������
     * @return �������
     * @throws DataAccessException DataAccessException
     * @throws RichClientWebException RichClientWebException
     */
    int update(String sqlID, Object bindParams, TableRowMap tab) 
        throws DataAccessException, RichClientWebException;
    
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
    TableRowMap query(String sqlID, Object bindParams) throws RichClientWebException;
    
    /**
     * ��ѯ��������.
     * @param sqlID sql ID
     * @return �������
     */
    TableRowMap[] querys(String sqlID) throws RichClientWebException;
    
    /**
     * ��ѯ��������.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     */
    TableRowMap[] querys(String sqlID, Object bindParams) throws RichClientWebException;
    
    /**
     * ������չ����.
     * @param expand ��չ����
     */
    void setExpand(IExpandFace expand);
    
    IDbService get(String key);
}
