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
    private final BpsJmsTemplate bpsJmsTemplate;

    private final SimpleEmailNotificationAdapterImpl simpleEmailNotificationAdapterImpl;
    private int count = 0;

    public MessageConsumer(BpsJmsTemplate bpsJmsTemplate, SimpleEmailNotificationAdapterImpl simpleEmailNotificationAdapterImpl) {
        this.bpsJmsTemplate = bpsJmsTemplate;
        this.simpleEmailNotificationAdapterImpl = simpleEmailNotificationAdapterImpl;
    }



//    @JmsListener(destination = JmsConfig.BPS_NOTIFICATION, containerFactory = "jmsListenerContainerFactory")
    public  void receiveJsonMessage(Message receivedMessage){
        System.out.println();
        System.out.println(receivedMessage);
        System.out.println(receivedMessage instanceof BusinessUserDTO);
        System.out.println(receivedMessage instanceof NotificationDTO);

        System.out.println((Object)receivedMessage instanceof BusinessUserDTO);
        System.out.println((Object)receivedMessage instanceof NotificationDTO);




//        String typeProperty = (String) object.getProperties().get("_type");
//
//
////        bpsJmsTemplate.receiveAnd
//        if(object instanceof NotificationDTO){
//            System.out.println("notification dto: "+ ((NotificationDTO)object).getEmail());
//        }
////        if(object instanceof BusinessUserDTO){
//            System.out.println("businessuser dto: "+ ((BusinessUserDTO)object).getEmail());
//        }

        // send email to respective logic!

//        logger.warn(">>>> inside message consumer.\nMessage from the queue: "+notificationDTO);

        logger.info("---------------------------------------");
        logger.info("---------------------------------------");
        count++;
        logger.info("count: "+count);
        logger.info("---------------------------------------");
        logger.info("---------------------------------------");

        String subject = "Payment";
//        String content = this.simpleEmailNotificationAdapterImpl.getContent(notificationDTO.getName());

        try {
//            this.simpleEmailNotificationAdapterImpl.sendEmail(notificationDTO.getEmail(), subject, content);
            logger.info("Email has been sent to the user.");
        } catch (Exception e) {
            logger.error("Some exception related to email process.");
            throw new RuntimeException(e);
        }

    }

//    @JmsListener(destination = "DLQ")
//    public void processDLQMessage(String message) {
//        // Process the message from the DLQ
//        System.out.println("Received message from DLQ: " + message);
//    }



}
