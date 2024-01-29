package com.example.artemisPractise.user.producer;


import com.example.artemisPractise.user.config.BpsJmsTemplate;
import com.example.artemisPractise.user.config.JmsConfig;
import com.example.artemisPractise.user.entity.BusinessUser;
import com.example.artemisPractise.user.entity.BusinessUserDTO;
import com.example.artemisPractise.user.entity.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);


//    private final JmsTemplate jmsTemplate;
    private final BpsJmsTemplate bpsJmsTemplate;

    public MessageProducer(BpsJmsTemplate bpsJmsTemplate) {
        this.bpsJmsTemplate = bpsJmsTemplate;
    }

    /**
     * converts and send message from java obj to json
     */
    public void sendJsonMessage(NotificationDTO notificationDTO){
//        bpsJmsTemplate.convertAndSend(JmsConfig.BPS_NOTIFICATION, businessUser);
        bpsJmsTemplate.convertAndSendNotificationDTO(JmsConfig.BPS_NOTIFICATION, notificationDTO);

        logger.warn(">>>>>>> message has just been pushed to the queue: "+notificationDTO.getEmail());
    }

    public void sendJsonMessage(BusinessUserDTO businessUser){
        logger.info("------------------------------");
        bpsJmsTemplate.convertAndSendBusinessUser(JmsConfig.BPS_NOTIFICATION, businessUser);
        logger.info("-------------------------------");
    }
}
