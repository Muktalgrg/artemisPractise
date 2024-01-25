package com.example.artemisPractise.user.producer;


import com.example.artemisPractise.user.config.JmsConfig;
import com.example.artemisPractise.user.entity.BusinessUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);


    private final JmsTemplate jmsTemplate;

    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * converts and send message from java obj to json
     */
    public void sendJsonMessage(BusinessUser businessUser){
        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, businessUser);
        logger.warn(">>>>>>> message has just been pushed to the queue: "+businessUser.getEmail());
    }
}
