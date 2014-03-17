package com.richClientFrame.info;

import com.richClientFrame.util.CommonUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * �û���Ϣ������.
 * @author King
 * @since 2010.06.04
 */
public class UserInfo implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * �û��ȼ��������ȼ�_��ͼ�.
     */
    public static final int LOW_OPE_LEVEL = 8;
    
    /**
     * �û��ȼ�����ʾ�ȼ�_��ͼ�.
     */
    public static final int LOW_DSP_LEVEL = 8;
    
    /**
     * �û�Ȩ�ޣ�����Ա.
     */
    public static final int AUTHORITY_SYSADMIN = 0;
    
    /**
     * �û�Ȩ�ޣ���ͨ�û�.
     */
    public static final int AUTHORITY_GENERAL = 2;
    
    
    /**
     * �û��Ƿ���Ա�ɾ������ֹɾ��.
     */
    public static final int PROHIBITION_NO = 1;
    
    /**
     * �û��Ƿ���Ա�ɾ��������ɾ��.
     */
    public static final int PROHIBITION_YES = 0;
    
    /**
     * �û�ID
     */
    private String userId = "";
    
    /**
     * �û���¼�˺�
     */
    private String userLogin = "";
    
    /**
     * �û���ʵ����
     */
    private String userName = "";
        
    /**
     * �û�����
     */
    private String password = "";
    
    /**
     * �û�ʡID
     */
    private String provinceId = "";
    
    /**
     * �û���Ӧ������Դ
     */
    private String dataSource;
    
    /**
     * �����ȼ�<br>
     * Ĭ�ϣ���͵ȼ�
     */
    private String level;
    
    /**
     * ��ʾ�ȼ�<br>
     * Ĭ�ϣ���͵ȼ�
     */
    private int dsplevel = LOW_DSP_LEVEL;
        
    /**
     * �û�Ȩ�ޣ�������<br>
     * Ĭ�ϣ���ͨ�û�
     */
    private int authority = AUTHORITY_GENERAL;

    /**
     * �û�Ȩ�ޣ���ʾ��<br>
     * Ĭ�ϣ���ͨ�û�
     */
    private int dspAuthority = AUTHORITY_GENERAL;
    
    /**
     * �û��Ƿ�ɱ�ɾ��<br>
     * Ĭ�ϣ���ɾ��
     */
    private int prohibition = PROHIBITION_YES;
    
    private Map<String, Object> userInfo = new HashMap<String, Object>();

    /**
     * ���캯��.
     */
    public UserInfo() {
        super();
    }

    /**
     * ȡ���û�ID.
     * @return loginId �û�ID
     */    
    public String getUserId() {
        return userId;
    }

    /**
     * �趨�û�ID.
     * @param userId �û�ID
     */    
    public void setUserId(String userId) {
        this.userId = userId;
        userInfo.put("userId", userId);
    }

    /**
     * ȡ���û�����.
     * @return loginPass �û�����
     */        
    public String getLoginPass() {
        return password;
    }

    /**
     * �趨�û�����.
     * @param password �û�����
     */        
    public void setPassword(String password) {
        this.password = password;
        userInfo.put("password", password);
    }

    /**
     * ȡ���û��ȼ�.
     * @return opelevel �û��ȼ�
     */        
    public String getLevel() {
        return level;
    }

    /**
     * �趨�û��ȼ�.
     * @param level �û��ȼ�
     */    
    public void setLevel(String level) {
        this.level = level;
        userInfo.put("opelevel", String.valueOf(level));
    }

    /**
     * ȡ����ʾ�ȼ�.
     * @return dsplevel ��ʾ�ȼ�
     */    
    public int getDsplevel() {
        return dsplevel;
    }

    /**
     * �趨��ʾ�ȼ�.
     * @param dsplevel ��ʾ�ȼ�
     */        
    public void setDsplevel(int dsplevel) {
        this.dsplevel = dsplevel;
        userInfo.put("dsplevel", String.valueOf(dsplevel));
    }

    /**
     * ȡ���û�Ȩ�ޣ�������.
     * @return authority �û�Ȩ�ޣ�������
     */    
    public int getAuthority() {
        return authority;
    }

    /**
     * �趨�û�Ȩ�ޣ�������.
     * @param authority �û�Ȩ�ޣ�������
     */    
    public void setAuthority(int authority) {
        this.authority = authority;
        userInfo.put("authority", String.valueOf(authority));
    }

    /**
     * ȡ���û��Ƿ�ɱ�ɾ��.
     * @return prohibition �û��Ƿ�ɱ�ɾ��
     */    
    public int getProhibition() {
        return prohibition;
    }

    /**
     * �趨�û��Ƿ�ɱ�ɾ��.
     * @param prohibition �û��Ƿ�ɱ�ɾ��
     */    
    public void setProhibition(int prohibition) {
        this.prohibition = prohibition;
        userInfo.put("prohibition", String.valueOf(prohibition));
    }

    /**
     * ȡ���û�Ȩ�ޣ���ʾ��.
     * @return �û�Ȩ�ޣ���ʾ��
     */
    public int getDspAuthority() {
        return dspAuthority;
    }

    /**
     * �趨�û�Ȩ�ޣ���ʾ��.
     * @param dspAuthority �û�Ȩ�ޣ���ʾ��
     */
    public void setDspAuthority(int dspAuthority) {
        this.dspAuthority = dspAuthority;
        userInfo.put("dspAuthority", String.valueOf(dspAuthority));
    }
    
    /**
     * ȡ���û���Ϣ.
     * @param key map key
     * @return �û���Ϣ
     */
    public String getUserInfo(String key) {
        return CommonUtil.toString(userInfo.get(key));
    }
    
    /**
     * ȡ���û���Ϣ.
     * @param key map key
     * @return �û���Ϣ
     */
    public Object getUserObj(String key) {
        return userInfo.get(key);
    }

    /**
     * ȡ����ʵ����.
     * @return ��ʵ����
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ��ʵ�����趨.
     * @param userName ��ʵ����
     */
    public void setUserName(String userName) {
        this.userName = userName;
        userInfo.put("userName", userName);
    }
    
    /**
     * ͨ��key�����û���Ϣ����.
     * @param key ��Ϣkey
     * @param value ��Ϣֵ
     */
    public void setUserInfo(String key, String value) {
        userInfo.put(key, value);
    }
    
    /**
     * ͨ��key�����û���Ϣ����.
     * @param key ��Ϣkey
     * @param value ��Ϣֵ
     */
    public void setUserInfo(String key, Object value) {
        userInfo.put(key, value);
    }

    /**
     * ȡ���û���Ӧ������Դ.
     * @return �û���Ӧ������Դ
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * �û���Ӧ������Դ�趨.
     * @param dataSource �û���Ӧ������Դ
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
        userInfo.put("dataSource", dataSource);
    }

    /** 
    * @Description: ȡ���û�ʡID
    * @author king
    * @since Oct 5, 2012 11:30:28 AM 
    * 
    * @return �û�ʡID
    */
    public String getProvinceId() {
        return provinceId;
    }

    /** 
    * @Description: �����û�ʡID
    * @author king
    * @since Oct 5, 2012 11:30:39 AM 
    * 
    * @param provinceId �û�ʡID
    */
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
        userInfo.put("provinceId", provinceId);
    }

    /** 
    * @Description: ȡ���û���¼�˺�
    * @author king
    * @since Oct 5, 2012 11:30:48 AM 
    * 
    * @return �û���¼�˺�
    */
    public String getUserLogin() {
        return userLogin;
    }

    /** 
    * @Description: �����û���¼�˺�
    * @author king
    * @since Oct 5, 2012 11:31:02 AM 
    * 
    * @param userLogin �û���¼�˺�
    */
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
        userInfo.put("userLogin", userLogin);
    }

}