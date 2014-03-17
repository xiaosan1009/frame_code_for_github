package com.richClientFrame.service.impl;

import com.richClientFrame.dao.IDbExcute;
import com.richClientFrame.dao.SwitchDataSource;
import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.db.PageBean;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.iface.IExpandFace;
import com.richClientFrame.service.IDbService;
import com.richClientFrame.util.LogUtil;

import org.springframework.dao.DataAccessException;

import java.util.Map;

/**
 * ��ͨDB��������ʵ����.
 * @author king
 * @since 2012.03.28
 */
public class DbServiceImpl implements IDbService {
    
    private LogUtil log = new LogUtil(this.getClass(), true);
    
    private IDbExcute db;
    
    private IExpandFace expand;
    
    public IDbService get(String key) {
        SwitchDataSource.setType(key);
        return this;
    }

    /**
     * ��Ӹ������ݿ�.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     * @throws DataAccessException DataAccessException
     * @throws RichClientWebException RichClientWebException
     */
    public TableRowMap insert(String sqlID, Object bindParams) 
        throws DataAccessException, RichClientWebException {
        TableRowMap tab = null;
        try {
            // ������չ�����ʼ������
            setCondition(bindParams);
            // ��Ӹ������ݿ�
            tab = db.insert(sqlID, bindParams);
            // update����֮������淽����չ����
            setUpdatePost(tab);
        } finally {
            // ���ݿ����ִ�����֮��Ĵ���
            afterExecute();
        }
        return tab;
    }

    /**
     * ��ѯ��������.
     * @param sqlID sql ID
     * @return �������
     */
    public TableRowMap query(String sqlID) {
        TableRowMap tab = null;
        try {
            // ��ѯ��������
            tab = db.query(sqlID);
        } finally {
            // ���ݿ����ִ�����֮��Ĵ���
            afterExecute();
        }
        return tab;
    }

    /**
     * ��ѯ��������.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     * @throws RichClientWebException RichClientWebException
     */
    public TableRowMap query(String sqlID, Object bindParams) 
        throws RichClientWebException {
        TableRowMap tab = null;
        try {
            // ������չ�����ʼ������
            setCondition(bindParams);
            // ��ѯ��������
            tab = db.query(sqlID, bindParams);
        } finally {
            // ���ݿ����ִ�����֮��Ĵ���
            afterExecute();
        }
        return tab;
    }

    /**
     * ��ҳ��ѯ.
     * @param sqlID sql ID
     * @param pageSize ÿҳ��ʾ������
     * @param param request
     * @return ��ѯ���
     */
    public PageBean queryPages(String sqlID, int pageSize, Param param) {
        PageBean pageBean = null;
        try {
            // ��ҳ��ѯ
            pageBean = db.queryPages(sqlID, pageSize, param);
        } finally {
            // ���ݿ����ִ�����֮��Ĵ���
            afterExecute();
        }
        return pageBean;
    }

    /**
     * ��ҳ��ѯ.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @param pageSize ÿҳ��ʾ������
     * @param param request
     * @return ��ѯ���
     * @throws RichClientWebException RichClientWebException
     */
    public PageBean queryPages(String sqlID, Object bindParams, int pageSize,
            Param param) throws RichClientWebException {
        PageBean pageBean = null;
        try {
            // ������չ�����ʼ������
            setCondition(bindParams);
            // ��ҳ��ѯ
            pageBean = db.queryPages(sqlID, bindParams, pageSize, param);
        } finally {
            // ���ݿ����ִ�����֮��Ĵ���
            afterExecute();
        }
        return pageBean;
    }

    /**
     * ��ѯ��������.
     * @param sqlID sql ID
     * @return �������
     */
    public TableRowMap[] querys(String sqlID) {
        TableRowMap[] tabs = null;
        try {
            // ��ѯ��������
            tabs = db.querys(sqlID);
        } finally {
            // ���ݿ����ִ�����֮��Ĵ���
            afterExecute();
        }
        return tabs;
    }

    /**
     * ��ѯ��������.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     * @throws RichClientWebException RichClientWebException
     */
    public TableRowMap[] querys(String sqlID, Object bindParams) throws RichClientWebException {
        TableRowMap[] tabs = null;
        try {
            // ������չ�����ʼ������
            setCondition(bindParams);
            // ��ѯ��������
            tabs = db.querys(sqlID, bindParams);
        } finally {
            // ���ݿ����ִ�����֮��Ĵ���
            afterExecute();
        }
        return tabs;
    }

    /**
     * ��Ӹ������ݿ�.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @return �������
     * @throws DataAccessException DataAccessException
     * @throws RichClientWebException RichClientWebException
     */
    public int update(String sqlID, Object bindParams) 
        throws DataAccessException, RichClientWebException {
        int result = 0;
        try {
            // ������չ�����ʼ������
            setCondition(bindParams);
            final TableRowMap tab = new TableRowMap();
            // ��Ӹ������ݿ�
            result = db.update(sqlID, bindParams);
            // update����֮������淽����չ����
            setUpdatePost(tab);
        } finally {
            // ���ݿ����ִ�����֮��Ĵ���
            afterExecute();
        }
        return result;
    }
    
    
    /**
     * ��Ӹ������ݿ�.
     * @param sqlID sql ID
     * @param bindParams ��ѯ����
     * @param tab ��Ϣ��������
     * @return �������
     * @throws DataAccessException DataAccessException
     * @throws RichClientWebException RichClientWebException
     */
    public int update(String sqlID, Object bindParams, TableRowMap tab) 
        throws DataAccessException, RichClientWebException {
        int result = 0;
        try {
            // ������չ�����ʼ������
            setCondition(bindParams);
            // ��Ӹ������ݿ�
            result = db.update(sqlID, bindParams, tab);
            // ���ݿ����ִ�����֮��Ĵ���
            setUpdatePost(tab);
        } finally {
            // ���ݿ����ִ�����֮��Ĵ���
            afterExecute();
        }
        return result;
    }
    
    /**
     * ��������Դ��Ϣ.
     * @param db ����Դ��Ϣ
     */
    public void setDb(IDbExcute db) {
        this.db = db;
    }

    /**
     * ������չ����.
     * @param expand ��չ����
     */
    public void setExpand(IExpandFace expand) {
        this.expand = expand;
    }
    
    /**
     * ������չ�����ʼ������.
     * @param bindParams ��������
     * @throws RichClientWebException RichClientWebException
     */
    private void setCondition(Object bindParams) throws RichClientWebException {
        if (expand != null && bindParams instanceof Map) {
            expand.createCondition((Map<String, Object>) bindParams);
        }
    }
    
    /**
     * update����֮������淽����չ����.
     * @param tab ��������
     * @throws RichClientWebException RichClientWebException
     */
    private void setUpdatePost(TableRowMap tab) throws RichClientWebException {
        log.info("setUpdatePost", "start", "expand = " + expand);
        if (expand != null && tab != null) {
            expand.postUpdate(tab);
        }
        log.info("setUpdatePost", "end");
    }
    
    /**
     * @Description: ���ݿ����ִ�����֮��Ĵ���
     * @author king
     * @since Jan 17, 2013 1:07:26 PM 
     * @version V1.0
     */
    private void afterExecute() {
        SwitchDataSource.setType(null);
    }

}
