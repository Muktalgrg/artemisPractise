package com.example.artemisPractise.user.listener;


import com.example.artemisPractise.user.config.BpsJmsTemplate;
import com.example.artemisPractise.user.config.JmsConfig;
import com.example.artemisPractise.user.entity.BusinessUserDTO;
import com.example.artemisPractise.user.entity.NotificationDTO;
import com.example.artemisPractise.user.service.impl.SimpleEmailNotificationAdapterImpl;
import org.apache.activemq.artemis.jms.client.ActiveMQTextMessage;
import org.aspectj.weaver.ast.Not;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import java.util.Objects;

@Component
public class MessageConsumer {

    private final static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    private final SimpleEmailNotificationAdapterImpl simpleEmailNotificationAdapterImpl;

    public MessageConsumer(SimpleEmailNotificationAdapterImpl simpleEmailNotificationAdapterImpl) {
        this.simpleEmailNotificationAdapterImpl = simpleEmailNotificationAdapterImpl;
    }




    @JmsListener(destination = JmsConfig.BPS_NOTIFICATION)
    public  void receiveMessageForEmailService(NotificationDTO notificationDTO){
        System.out.println("inside consumer 1: "+notificationDTO);

        String subject = "Payment";
        String content = this.simpleEmailNotificationAdapterImpl.getContent(notificationDTO.getName());

        try {
            this.simpleEmailNotificationAdapterImpl.sendEmail(notificationDTO.getEmail(), subject, content);
            logger.info("Email has been sent to the user.");
        } catch (Exception e) {
            logger.error("Some exception related to email process.");
            throw new RuntimeException(e);
        }

    }

//    @JmsListener(destination = JmsConfig.BPS_NOTIFICATION)
    public void receiveMessageForMobileService(NotificationDTO notificationDTO){
        System.out.println("inside consumer 2: "+notificationDTO);
    }

//    @JmsListener(destination = JmsConfig.BPS_NOTIFICATION)
    public void receiveMessageForMobileService1(NotificationDTO notificationDTO){
        System.out.println("inside consumer 3: "+notificationDTO);
    }

//    @JmsListener(destination = JmsConfig.BPS_NOTIFICATION)
    public void receiveMessageForMobileService2(NotificationDTO notificationDTO){
        System.out.println("inside consumer 4: "+notificationDTO);
    }

//    @JmsListener(destination = "DLQ")
//    public void processDLQMessage(String message) {
//        // Process the message from the DLQ
//        System.out.println("Received message from DLQ: " + message);
//    }



}
