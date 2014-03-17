package com.richClientFrame.data.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� MyAuthenticator
 * ������ �� 
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Oct 9, 2013 2:44:52 PM
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
