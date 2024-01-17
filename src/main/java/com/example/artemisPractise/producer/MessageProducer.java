package com.example.artemisPractise.producer;


import com.example.artemisPractise.config.JmsConfig;
import com.example.artemisPractise.model.EmailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

//@Component
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);


    private final JmsTemplate jmsTemplate;

    public MessageProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * converts and send message from java obj to json
     */
    public void sendJsonMessage(EmailMessage message){
        jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);
        logger.warn(">>>>>>> message has just been pushed to the queue: "+message.getContent());
    }
}
