package com.example.artemisPractise.user.service.impl;

//import com.au.bps.core.constants.MessageCodes;
//import com.au.bps.core.exceptions.BusinessException;

import com.example.artemisPractise.user.entity.BusinessUser;
import com.example.artemisPractise.user.service.IEmailNotificationAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.TemplateEngine;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class SimpleEmailNotificationAdapterImpl implements IEmailNotificationAdapter {

    private static final String THIS_COMPONENT_NAME = SimpleEmailNotificationAdapterImpl.class.getName();
    private static final Logger logger = Logger.getLogger(THIS_COMPONENT_NAME);
    private static final String EXCEPTION_OCCURRED = "EXCEPTION OCCURRED WHILE SENDING EMAIL:";


    private static String HOST;

    @Value("${mail.host}")
    public void setHOST(String host) {
        SimpleEmailNotificationAdapterImpl.HOST = host;
    }

    private static String FROM;

    @Value("${mail.from}")
    public void setFROM(String from) {
        SimpleEmailNotificationAdapterImpl.FROM = from;
    }


    private static String FROMNAME;

    @Value("${mail.fromname}")
    public void setFROMNAME(String fromname) {
        SimpleEmailNotificationAdapterImpl.FROMNAME = fromname;
    }

    private static String SMTP_USERNAME;

    @Value("${mail.username}")
    public void setSmtpUsername(String smtpUsername) {
        SMTP_USERNAME = smtpUsername;
    }

    private static String SMTP_PASSWORD;

    @Value("${mail.password}")
    public void setSmtpPassword(String smtpPassword) {
        SMTP_PASSWORD = smtpPassword;
    }

    private static String PROTOCOL;

    @Value("${mail.properties.mail.transport.protocol}")
    public void setPROTOCOL(String protocol) {
        SimpleEmailNotificationAdapterImpl.PROTOCOL = protocol;
    }

    private static int PORT;

    @Value("${mail.properties.mail.smtp.port}")
    public void setPORT(int port) {
        SimpleEmailNotificationAdapterImpl.PORT = port;
    }

    private static String AUTH;

    @Value("${mail.properties.mail.smtp.auth}")
    public void setAUTH(String auth) {
        SimpleEmailNotificationAdapterImpl.AUTH = auth;
    }

    private static String STARTTLS_ENABLE;

    @Value("${mail.properties.mail.smtp.starttls.enable}")
    public void setStarttlsEnable(String starttlsEnable) {
        STARTTLS_ENABLE = starttlsEnable;
    }

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendEmail(List<String> to, List<String> cc, String subject, String content) throws Exception {

        Transport transport = null;

        try {
            // Create a Properties object to contain connection configuration information.
            Properties props = System.getProperties();
            props.put("mail.transport.protocol", PROTOCOL);
            props.put("mail.smtp.port", PORT);
            props.put("mail.smtp.starttls.enable", STARTTLS_ENABLE);
            props.put("mail.smtp.auth", AUTH);
            Session session = Session.getDefaultInstance(props);

            // Create a message with the specified information.
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, FROMNAME));


            if (!CollectionUtils.isEmpty(to)) {
                InternetAddress[] toAddress = new InternetAddress[to.size()];

                // To get the array of toaddresses
                for (int i = 0; i < to.size(); i++) {
                    toAddress[i] = new InternetAddress(to.get(i));
                }

                // Set To: header field of the header.
                for (int i = 0; i < toAddress.length; i++) {
                    msg.addRecipient(Message.RecipientType.TO, toAddress[i]);
                }

            }

            if (!CollectionUtils.isEmpty(cc)) {

                InternetAddress[] ccAddress = new InternetAddress[cc.size()];

                // To get the array of ccaddresses
                for (int i = 0; i < cc.size(); i++) {
                    ccAddress[i] = new InternetAddress(cc.get(i));
                }

                // Set cc: header field of the header.
                for (int i = 0; i < ccAddress.length; i++) {
                    msg.addRecipient(Message.RecipientType.CC, ccAddress[i]);
                }
            }

            msg.setSubject(subject);
            msg.setContent(content, "text/plain");

            transport = session.getTransport();

            // Connect to Amazon SES using the SMTP username and password
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());

        } catch (Exception e) {

            logger.log(
                    Level.SEVERE
                    , EXCEPTION_OCCURRED
                    , e
            );

//            throw new BusinessException(MessageCodes.PPL_GENERIC_ERROR, e.getMessage());
            throw new Exception("Some exception occured while sending an email!");

        } finally {
            try {
                if (transport != null) {
                    transport.close();
                }
            } catch (MessagingException e) {
            }
        }
    }

    @Override
    public void sendEmail(String to, String subject, String content) throws Exception {

        Transport transport = null;

        try {
            // Create a Properties object to contain connection configuration information.
            Properties props = System.getProperties();
            props.put("mail.transport.protocol", PROTOCOL);
            props.put("mail.smtp.port", PORT);
            props.put("mail.smtp.starttls.enable", STARTTLS_ENABLE);
            props.put("mail.smtp.auth", AUTH);
            Session session = Session.getDefaultInstance(props);

            // Create a message with the specified information.
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(FROM, FROMNAME));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));


            msg.setSubject(subject);
            msg.setContent(content, "text/html");

            transport = session.getTransport();

            // Connect to Amazon SES using the SMTP username and password
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());

        } catch (Exception e) {

            logger.log(
                    Level.SEVERE
                    , EXCEPTION_OCCURRED
                    , e
            );

//            throw new BusinessException(MessageCodes.PPL_GENERIC_ERROR, e.getMessage());
            throw new Exception("Some exception occured while sending an email!");

        } finally {
            try {
                if (transport != null) {
                    transport.close();
                }
            } catch (MessagingException e) {
            }
        }
    }


    public String getContent(String username) {
       return "<table class=\"body-wrap\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif;" +
               " box-sizing: border-box; font-size: 14px; width: 100%; background-color: #f6f6f6; margin: 0;\" bgcolor=\"#f6f6f6\"><tbody><tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; " +
               "box-sizing: border-box; font-size: 14px; margin: 0;\"><td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\" valign=\"top\"></td><td class=\"container\" width=\"600\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px;" +
               " vertical-align: top; display: block !important; max-width: 600px !important; clear: both !important;" +
               " margin: 0 auto;\" valign=\"top\"><div class=\"content\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box;" +
               " font-size: 14px; max-width: 600px; display: block; margin: 0 auto; padding: 20px;\"><table class=\"main\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; " +
               "box-sizing: border-box; font-size: 14px; border-radius: 3px; background-color: #fff; margin: 0; border: 1px solid #e9e9e9;\" bgcolor=\"#fff\"><tbody><tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\"><td class=\"\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 16px; " +
               "vertical-align: top; color: #fff; font-weight: 500; text-align: center; border-radius: 3px 3px 0 0; background-color: #38414a;" +
               " margin: 0; padding: 20px;\" align=\"center\" bgcolor=\"#71b6f9\" valign=\"top\"><a href=\"https://inventecsolutions.com/\" style=\"font-size:32px;color:#fff; text-decoration: none\"> Inventec</a> <br></td></tr><tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; " +
               "box-sizing: border-box; font-size: 14px; margin: 0;\"><td class=\"content-wrap\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; " +
               "box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0; padding: 20px;\" valign=\"top\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif;" +
               " box-sizing: border-box; font-size: 14px; margin: 0;\"><tbody><tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif;" +
               " box-sizing: border-box; font-size: 14px; margin: 0;\"><td class=\"content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px;" +
               " vertical-align: top; margin: 0; padding: 0 0 20px;\" valign=\"top\"><center>Payment has been successfully performed by "+username+".</center></strong></td></tr></tbody></table></td></tr></tbody></table><div class=\"footer\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; width: 100%; clear: both; color: #999; margin: 0; padding: 20px;" +
               "\"><table width=\"100%\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; margin: 0;\"><tbody><tr style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif;" +
               " box-sizing: border-box; font-size: 14px; margin: 0;\"><td class=\"aligncenter content-block\" style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 12px;" +
               " vertical-align: top; color: #999; text-align: center; margin: 0;" +
               " padding: 0 0 20px;\" align=\"center\" valign=\"top\">&copy; 2024 Inventec. All rights reserved.</td></tr></tbody></table></div></div></td><td style=\"font-family: 'Helvetica Neue',Helvetica,Arial,sans-serif; box-sizing: border-box; font-size: 14px; vertical-align: top; margin: 0;\" valign=\"top\"></td></tr></tbody></table>\n";

    }



}
