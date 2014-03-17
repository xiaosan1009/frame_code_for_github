package com.richClientFrame.data.mail;

import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.LogUtil;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： SimpleMailSender
 * 类描述 ： 
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： Oct 9, 2013 2:46:03 PM
 * @author king
 */
public class MailSender {
    
    private static final LogUtil LOG = new LogUtil(MailSender.class, true);
    
    private MailBean mailBean;

    /**
     * @Description: 以文本格式发送邮件
     * @author king
     * @since Oct 9, 2013 2:47:22 PM 
     * @version V1.0
     * @param mailInfo 待发送的邮件的信息
     * @return boolean
     */
    public boolean sendTextMail(MailBean mailInfo) {
        resetMailBean(mailInfo);
        // 判断是否需要身份认证   
        MyAuthenticator authenticator = null;
        final Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器   
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session   
        final Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息   
            final Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址   
            final Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者   
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中   
            final Address to = new InternetAddress(mailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题   
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间   
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容   
            final String mailContent = mailInfo.getContent();
            mailMessage.setText(mailContent);
            // 发送邮件   
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * @Description: 以HTML格式发送邮件
     * @author king
     * @since Oct 9, 2013 2:48:09 PM 
     * @version V1.0
     * @param mailInfo 待发送的邮件信息
     * @return boolean
     */
    public boolean sendHtmlMail(MailBean mailInfo) {
        resetMailBean(mailInfo);
        // 判断是否需要身份认证   
        MyAuthenticator authenticator = null;
        final Properties pro = mailInfo.getProperties();
        //如果需要身份认证，则创建一个密码验证器    
        if (mailInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session   
        final Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息   
            final Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址   
            final Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者   
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中   
            final Address to = new InternetAddress(mailInfo.getToAddress());
            // Message.RecipientType.TO属性表示接收者的类型为TO   
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题   
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间   
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象   
            final Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart   
            final BodyPart html = new MimeBodyPart();
            // 设置HTML内容   
            html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容   
            mailMessage.setContent(mainPart);
            // 发送邮件   
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    private void resetMailBean(MailBean mailInfo) {
        if (CommonUtil.isEmpty(mailInfo.getMailServerHost())) {
            mailInfo.setMailServerHost(mailBean.getMailServerHost());
        }
        if (CommonUtil.isEmpty(mailInfo.getMailServerPort())) {
            mailInfo.setMailServerPort(mailBean.getMailServerPort());
        }
        if (CommonUtil.isEmpty(mailInfo.getUserName())) {
            mailInfo.setUserName(mailBean.getUserName());
        }
        if (CommonUtil.isEmpty(mailInfo.getPassword())) {
            mailInfo.setPassword(mailBean.getPassword());
        }
        if (CommonUtil.isEmpty(mailInfo.getFromAddress())) {
            mailInfo.setFromAddress(mailBean.getFromAddress());
        }
        LOG.info("resetMailBean", "MailServerHost = " + mailInfo.getMailServerHost());
    }
    
    public static void main(String[] args) {
        MailBean mailInfo = new MailBean();   
        mailInfo.setMailServerHost("smtp.163.com");   
        mailInfo.setMailServerPort("25");   
        mailInfo.setValidate(true);   
        mailInfo.setUserName("xiaosan9528@163.com");   
        mailInfo.setPassword("tiantian!520");//您的邮箱密码   
        mailInfo.setFromAddress("xiaosan9528@163.com");   
        mailInfo.setToAddress("jinlei@kuai-info.com");   
        mailInfo.setSubject("设置邮箱标题");   
        mailInfo.setContent("设置邮箱内容");   
           //这个类主要来发送邮件  
        MailSender sms = new MailSender();  
            sms.sendTextMail(mailInfo);//发送文体格式   
//            sms.sendHtmlMail(mailInfo);//发送html格式  
    }

    public void setMailBean(MailBean mailBean) {
        this.mailBean = mailBean;
    }

}
