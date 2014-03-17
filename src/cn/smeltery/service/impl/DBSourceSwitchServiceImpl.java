package cn.smeltery.service.impl;

import cn.smeltery.service.IDBSourceSwitchService;

import com.richClientFrame.dao.DbConnection;
import com.richClientFrame.dao.TableRowMap;

/**
 * ��ͨ����Դ�л�����ʵ����.
 * @author king
 * @since 2012.03.12
 */
public class DBSourceSwitchServiceImpl implements IDBSourceSwitchService {
    
    private DbConnection db;
    
    /**
     * ȡ�õ�¼�û���Ϣ.
     * @param userLogin ��¼�û�ID
     * @return ��¼�û���Ϣ
     */
    public TableRowMap queryUserInfoByUserLogin(String userLogin) {
        return db.query("Frame.queryUserInfoByUserLogin", userLogin);
    }

    /**
     * ȡ������ʡ������Դ��ӳ����Ϣ.
     * @return ����ʡ������Դ��ӳ����Ϣ
     */
    public TableRowMap[] queryAllProvinceDBMapping() {
        return db.querys("Frame.queryAllProvinceDBMapping");
    }
    
    /**
     * ��������Դ��Ϣ.
     * @param db ����Դ��Ϣ
     */
    public void setDb(DbConnection db) {
        this.db = db;
    }
}
