package cn.smeltery.service;

import com.richClientFrame.dao.TableRowMap;

/**
 * ��ͨ����Դ�л�����ӿ�.
 * @author king
 * @since 2012.03.12
 */
public interface IDBSourceSwitchService {
    
    /**
     * ȡ������ʡ������Դ��ӳ����Ϣ.
     * @return ����ʡ������Դ��ӳ����Ϣ
     */
    TableRowMap[] queryAllProvinceDBMapping();
    
    /**
     * ȡ�õ�¼�û���Ϣ.
     * @param userLogin ��¼�û�ID
     * @return ��¼�û���Ϣ
     */
    TableRowMap queryUserInfoByUserLogin(String userLogin);
}
