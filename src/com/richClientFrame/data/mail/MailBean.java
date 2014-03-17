
package com.richClientFrame.data.mail;

import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.param.RequestParam.Mail;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.xml.RequestConfigUtil;

import java.util.Properties;

/**
 * ��Ŀ���� �� Web2.0��������. ������ �� MailSenderInfo ������ �� ������ �� ���� ��ϵ��ʽ ��
 * xiaosan9528@163.com QQ �� 26981791 ����ʱ�� �� Oct 9, 2013 2:43:32 PM
 * 
 * @author king
 */
public class MailBean {

    // �����ʼ��ķ�������IP�Ͷ˿�
    private String mailServerHost;

    private String mailServerPort = "25";

    // �ʼ������ߵĵ�ַ
    private String fromAddress;

    // �ʼ������ߵĵ�ַ
    private String toAddress;

    // ��½�ʼ����ͷ��������û���������
    private String userName;

    private String password;

    // �Ƿ���Ҫ�����֤
    private boolean validate;

    // �ʼ�����
    private String subject;

    // �ʼ����ı�����
    private String content;

    // �ʼ��������ļ���
    private String[] attachFileNames;

    /**
     * ����ʼ��Ự����
     */
    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", this.mailServerHost);
        p.put("mail.smtp.port", this.mailServerPort);
        p.put("mail.smtp.auth", validate ? "true" : "false");
        return p;
    }

    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] fileNames) {
        this.attachFileNames = fileNames;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String textContent) {
        this.content = textContent;
    }
    
    public void parseMail(Param param, SessionData seData, Mail mail) 
        throws RichClientWebException {
        if (CommonUtil.isNotEmpty(mail.getMailServerHost())) {
            this.mailServerHost = RequestConfigUtil.getRequestValue(
                    param, seData, mail.getMailServerHost());
        }
        if (CommonUtil.isNotEmpty(mail.getMailServerHost())) {
            this.mailServerPort = RequestConfigUtil.getRequestValue(
                    param, seData, mail.getMailServerHost());
        }
        if (CommonUtil.isNotEmpty(mail.getUserName())) {
            this.userName = RequestConfigUtil.getRequestValue(
                    param, seData, mail.getUserName());
        }
        if (CommonUtil.isNotEmpty(mail.getPassword())) {
            this.password = RequestConfigUtil.getRequestValue(
                    param, seData, mail.getPassword());
        }
        this.validate = mail.isValidate();
        if (CommonUtil.isNotEmpty(mail.getFromAddress())) {
            this.fromAddress = RequestConfigUtil.getRequestValue(
                    param, seData, mail.getFromAddress());
        }
        if (CommonUtil.isNotEmpty(mail.getToAddress())) {
            this.toAddress = RequestConfigUtil.getRequestValue(
                    param, seData, mail.getToAddress());
        }
        if (CommonUtil.isNotEmpty(mail.getSubject())) {
            this.subject = RequestConfigUtil.getRequestValue(
                    param, seData, mail.getSubject());
        }
        if (CommonUtil.isNotEmpty(mail.getContent())) {
            this.content = RequestConfigUtil.getRequestValue(
                    param, seData, mail.getContent());
        }
    }

}
