package com.example.artemisPractise.config;

//import com.au.bps.core.constants.MessageCodes;
//import com.au.bps.core.exceptions.BusinessException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
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



}
