
package com.richClientFrame.data.mail;

import com.richClientFrame.data.SessionData;
import com.richClientFrame.data.param.Param;
import com.richClientFrame.data.param.RequestParam.Mail;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.xml.RequestConfigUtil;

import java.util.Properties;

/**
 * 项目名称 ： Web2.0开发引擎. 类名称 ： MailSenderInfo 类描述 ： 创建人 ： 金雷 联系方式 ：
 * xiaosan9528@163.com QQ ： 26981791 创建时间 ： Oct 9, 2013 2:43:32 PM
 * 
 * @author king
 */
public class MailBean {

    // 发送邮件的服务器的IP和端口
    private String mailServerHost;

    private String mailServerPort = "25";

    // 邮件发送者的地址
    private String fromAddress;

    // 邮件接收者的地址
    private String toAddress;

    // 登陆邮件发送服务器的用户名和密码
    private String userName;

    private String password;

    // 是否需要身份验证
    private boolean validate;

    // 邮件主题
    private String subject;

    // 邮件的文本内容
    private String content;

    // 邮件附件的文件名
    private String[] attachFileNames;

    /**
     * 获得邮件会话属性
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
