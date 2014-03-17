package com.richClientFrame.info;

import com.richClientFrame.util.CommonUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * 用户信息管理类.
 * @author King
 * @since 2010.06.04
 */
public class UserInfo implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户等级：操作等级_最低级.
     */
    public static final int LOW_OPE_LEVEL = 8;
    
    /**
     * 用户等级：表示等级_最低级.
     */
    public static final int LOW_DSP_LEVEL = 8;
    
    /**
     * 用户权限：管理员.
     */
    public static final int AUTHORITY_SYSADMIN = 0;
    
    /**
     * 用户权限：普通用户.
     */
    public static final int AUTHORITY_GENERAL = 2;
    
    
    /**
     * 用户是否可以被删除：禁止删除.
     */
    public static final int PROHIBITION_NO = 1;
    
    /**
     * 用户是否可以被删除：允许删除.
     */
    public static final int PROHIBITION_YES = 0;
    
    /**
     * 用户ID
     */
    private String userId = "";
    
    /**
     * 用户登录账号
     */
    private String userLogin = "";
    
    /**
     * 用户真实姓名
     */
    private String userName = "";
        
    /**
     * 用户密码
     */
    private String password = "";
    
    /**
     * 用户省ID
     */
    private String provinceId = "";
    
    /**
     * 用户对应的数据源
     */
    private String dataSource;
    
    /**
     * 操作等级<br>
     * 默认：最低等级
     */
    private String level;
    
    /**
     * 显示等级<br>
     * 默认：最低等级
     */
    private int dsplevel = LOW_DSP_LEVEL;
        
    /**
     * 用户权限（操作）<br>
     * 默认：普通用户
     */
    private int authority = AUTHORITY_GENERAL;

    /**
     * 用户权限（表示）<br>
     * 默认：普通用户
     */
    private int dspAuthority = AUTHORITY_GENERAL;
    
    /**
     * 用户是否可被删除<br>
     * 默认：可删除
     */
    private int prohibition = PROHIBITION_YES;
    
    private Map<String, Object> userInfo = new HashMap<String, Object>();

    /**
     * 构造函数.
     */
    public UserInfo() {
        super();
    }

    /**
     * 取得用户ID.
     * @return loginId 用户ID
     */    
    public String getUserId() {
        return userId;
    }

    /**
     * 设定用户ID.
     * @param userId 用户ID
     */    
    public void setUserId(String userId) {
        this.userId = userId;
        userInfo.put("userId", userId);
    }

    /**
     * 取得用户密码.
     * @return loginPass 用户密码
     */        
    public String getLoginPass() {
        return password;
    }

    /**
     * 设定用户密码.
     * @param password 用户密码
     */        
    public void setPassword(String password) {
        this.password = password;
        userInfo.put("password", password);
    }

    /**
     * 取得用户等级.
     * @return opelevel 用户等级
     */        
    public String getLevel() {
        return level;
    }

    /**
     * 设定用户等级.
     * @param level 用户等级
     */    
    public void setLevel(String level) {
        this.level = level;
        userInfo.put("opelevel", String.valueOf(level));
    }

    /**
     * 取得显示等级.
     * @return dsplevel 显示等级
     */    
    public int getDsplevel() {
        return dsplevel;
    }

    /**
     * 设定显示等级.
     * @param dsplevel 显示等级
     */        
    public void setDsplevel(int dsplevel) {
        this.dsplevel = dsplevel;
        userInfo.put("dsplevel", String.valueOf(dsplevel));
    }

    /**
     * 取得用户权限（操作）.
     * @return authority 用户权限（操作）
     */    
    public int getAuthority() {
        return authority;
    }

    /**
     * 设定用户权限（操作）.
     * @param authority 用户权限（操作）
     */    
    public void setAuthority(int authority) {
        this.authority = authority;
        userInfo.put("authority", String.valueOf(authority));
    }

    /**
     * 取得用户是否可被删除.
     * @return prohibition 用户是否可被删除
     */    
    public int getProhibition() {
        return prohibition;
    }

    /**
     * 设定用户是否可被删除.
     * @param prohibition 用户是否可被删除
     */    
    public void setProhibition(int prohibition) {
        this.prohibition = prohibition;
        userInfo.put("prohibition", String.valueOf(prohibition));
    }

    /**
     * 取得用户权限（表示）.
     * @return 用户权限（表示）
     */
    public int getDspAuthority() {
        return dspAuthority;
    }

    /**
     * 设定用户权限（表示）.
     * @param dspAuthority 用户权限（表示）
     */
    public void setDspAuthority(int dspAuthority) {
        this.dspAuthority = dspAuthority;
        userInfo.put("dspAuthority", String.valueOf(dspAuthority));
    }
    
    /**
     * 取得用户信息.
     * @param key map key
     * @return 用户信息
     */
    public String getUserInfo(String key) {
        return CommonUtil.toString(userInfo.get(key));
    }
    
    /**
     * 取得用户信息.
     * @param key map key
     * @return 用户信息
     */
    public Object getUserObj(String key) {
        return userInfo.get(key);
    }

    /**
     * 取得真实姓名.
     * @return 真实姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 真实姓名设定.
     * @param userName 真实姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
        userInfo.put("userName", userName);
    }
    
    /**
     * 通过key设置用户信息属性.
     * @param key 信息key
     * @param value 信息值
     */
    public void setUserInfo(String key, String value) {
        userInfo.put(key, value);
    }
    
    /**
     * 通过key设置用户信息属性.
     * @param key 信息key
     * @param value 信息值
     */
    public void setUserInfo(String key, Object value) {
        userInfo.put(key, value);
    }

    /**
     * 取得用户对应的数据源.
     * @return 用户对应的数据源
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * 用户对应的数据源设定.
     * @param dataSource 用户对应的数据源
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
        userInfo.put("dataSource", dataSource);
    }

    /** 
    * @Description: 取得用户省ID
    * @author king
    * @since Oct 5, 2012 11:30:28 AM 
    * 
    * @return 用户省ID
    */
    public String getProvinceId() {
        return provinceId;
    }

    /** 
    * @Description: 设置用户省ID
    * @author king
    * @since Oct 5, 2012 11:30:39 AM 
    * 
    * @param provinceId 用户省ID
    */
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
        userInfo.put("provinceId", provinceId);
    }

    /** 
    * @Description: 取得用户登录账号
    * @author king
    * @since Oct 5, 2012 11:30:48 AM 
    * 
    * @return 用户登录账号
    */
    public String getUserLogin() {
        return userLogin;
    }

    /** 
    * @Description: 设置用户登录账号
    * @author king
    * @since Oct 5, 2012 11:31:02 AM 
    * 
    * @param userLogin 用户登录账号
    */
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
        userInfo.put("userLogin", userLogin);
    }

}