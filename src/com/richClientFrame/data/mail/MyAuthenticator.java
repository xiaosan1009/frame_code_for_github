package com.richClientFrame.data.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： MyAuthenticator
 * 类描述 ： 
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Oct 9, 2013 2:44:52 PM
 * @author king
 */
public class MyAuthenticator extends Authenticator {

    String userName;

    String password;

    public MyAuthenticator() {
    }

    public MyAuthenticator(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }

}
