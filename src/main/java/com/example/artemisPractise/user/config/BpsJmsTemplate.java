package com.example.artemisPractise.user.config;

import com.example.artemisPractise.user.entity.BusinessUser;
import com.example.artemisPractise.user.entity.BusinessUserDTO;
import com.example.artemisPractise.user.entity.NotificationDTO;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Objects;

public class BpsJmsTemplate extends JmsTemplate {
    public BpsJmsTemplate() {
    }
    public BpsJmsTemplate(ConnectionFactory connectionFactory) {
        super(connectionFactory);
    }


    public void convertAndSendNotificationDTO(String destinationName, NotificationDTO message) {
        convertAndSend(destinationName, message);
    }

    public void convertAndSendBusinessUser(String destinationName, BusinessUserDTO businessUser){
        convertAndSend(destinationName, businessUser);
    }

}
