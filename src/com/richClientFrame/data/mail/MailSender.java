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
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� SimpleMailSender
 * ������ �� 
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� Oct 9, 2013 2:46:03 PM
 * @author king
 */
public class MailSender {
    
    private static final LogUtil LOG = new LogUtil(MailSender.class, true);
    
    private MailBean mailBean;

    /**
     * @Description: ���ı���ʽ�����ʼ�
     * @author king
     * @since Oct 9, 2013 2:47:22 PM 
     * @version V1.0
     * @param mailInfo �����͵��ʼ�����Ϣ
     * @return boolean
     */
    public boolean sendTextMail(MailBean mailInfo) {
        resetMailBean(mailInfo);
        // �ж��Ƿ���Ҫ�����֤   
        MyAuthenticator authenticator = null;
        final Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // �����Ҫ�����֤���򴴽�һ��������֤��   
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session   
        final Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // ����session����һ���ʼ���Ϣ   
            final Message mailMessage = new MimeMessage(sendMailSession);
            // �����ʼ������ߵ�ַ   
            final Address from = new InternetAddress(mailInfo.getFromAddress());
            // �����ʼ���Ϣ�ķ�����   
            mailMessage.setFrom(from);
            // �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��   
            final Address to = new InternetAddress(mailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // �����ʼ���Ϣ������   
            mailMessage.setSubject(mailInfo.getSubject());
            // �����ʼ���Ϣ���͵�ʱ��   
            mailMessage.setSentDate(new Date());
            // �����ʼ���Ϣ����Ҫ����   
            final String mailContent = mailInfo.getContent();
            mailMessage.setText(mailContent);
            // �����ʼ�   
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * @Description: ��HTML��ʽ�����ʼ�
     * @author king
     * @since Oct 9, 2013 2:48:09 PM 
     * @version V1.0
     * @param mailInfo �����͵��ʼ���Ϣ
     * @return boolean
     */
    public boolean sendHtmlMail(MailBean mailInfo) {
        resetMailBean(mailInfo);
        // �ж��Ƿ���Ҫ�����֤   
        MyAuthenticator authenticator = null;
        final Properties pro = mailInfo.getProperties();
        //�����Ҫ�����֤���򴴽�һ��������֤��    
        if (mailInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session   
        final Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // ����session����һ���ʼ���Ϣ   
            final Message mailMessage = new MimeMessage(sendMailSession);
            // �����ʼ������ߵ�ַ   
            final Address from = new InternetAddress(mailInfo.getFromAddress());
            // �����ʼ���Ϣ�ķ�����   
            mailMessage.setFrom(from);
            // �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��   
            final Address to = new InternetAddress(mailInfo.getToAddress());
            // Message.RecipientType.TO���Ա�ʾ�����ߵ�����ΪTO   
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // �����ʼ���Ϣ������   
            mailMessage.setSubject(mailInfo.getSubject());
            // �����ʼ���Ϣ���͵�ʱ��   
            mailMessage.setSentDate(new Date());
            // MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ���   
            final Multipart mainPart = new MimeMultipart();
            // ����һ������HTML���ݵ�MimeBodyPart   
            final BodyPart html = new MimeBodyPart();
            // ����HTML����   
            html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // ��MiniMultipart��������Ϊ�ʼ�����   
            mailMessage.setContent(mainPart);
            // �����ʼ�   
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
        mailInfo.setPassword("tiantian!520");//������������   
        mailInfo.setFromAddress("xiaosan9528@163.com");   
        mailInfo.setToAddress("jinlei@kuai-info.com");   
        mailInfo.setSubject("�����������");   
        mailInfo.setContent("������������");   
           //�������Ҫ�������ʼ�  
        MailSender sms = new MailSender();  
            sms.sendTextMail(mailInfo);//���������ʽ   
//            sms.sendHtmlMail(mailInfo);//����html��ʽ  
    }

    public void setMailBean(MailBean mailBean) {
        this.mailBean = mailBean;
    }

}
