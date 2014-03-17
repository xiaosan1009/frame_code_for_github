package com.richClientFrame.info;

import java.util.Hashtable;

import com.richClientFrame.exception.RichClientWebException;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ControlOperator
 * 类描述 ： 用户信息管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ：2010.10.07
 * @author king
 */
public class ControlOperator {
    
    /**
     * 自身对象
     */
    private static ControlOperator sMe;
        
    /**
     * 同步锁
     */
    private static Object sLockObj = new Object();
    
    /**
     * 登录用户保存对象
     */
    private Hashtable<String, LoginOperator> logOpeTable;
    
    /**
     * 自对象取得.
     * @return ControlOperator 自对象
     * @throws RichClientWebException RichClientWebException
     */
    public static ControlOperator getInstance() throws RichClientWebException {
        
        synchronized (ControlOperator.class) {
            if (sMe == null) {
                sMe = new ControlOperator();
            }
        }
        
        return sMe;
    }
    
    /**
     * 构造函数.
     * @throws RichClientWebException RichClientWebException
     */
    public ControlOperator() throws RichClientWebException {
        super();
        // 登录用户信息保存类的生成
        this.logOpeTable = new Hashtable<String, LoginOperator>();
    }
    
    //--------------------------------------------------------------
    /* 
     * 登录用户信息的操作
     */
    //--------------------------------------------------------------
    
    /**
     * 登录用户信息追加.
     * @param clientIP 客户IP
     * @param loginOperator 登录用户信息
     */
    public void putLoginOperator(String clientIP, LoginOperator loginOperator) {
        synchronized (sLockObj) {
            logOpeTable.put(clientIP, loginOperator);
        }
    }
    
    /**
     * 指定IP的登录用户信息的取得.
     * @param clientIP 用户IP
     * @return 登录用户信息
     */
    public LoginOperator getLoginOperator(String clientIP) {
        synchronized (sLockObj) {
            return logOpeTable.get(clientIP);
        }
    }
    
    /**
     * 登录用户信息的删除.
     * @param clientIP 用户IP
     */
    public void removeLoginOperator(String clientIP) {
        synchronized (sLockObj) {
            logOpeTable.remove(clientIP);
        }
    }
}