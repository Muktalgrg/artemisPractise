package com.example.artemisPractise.listener;


import com.example.artemisPractise.config.JmsConfig;
import com.example.artemisPractise.model.EmailMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

//@Component
public class MessageConsumer {

    private final static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void receiveJsonMessage(EmailMessage message){
        // send email to respective logic!

        logger.warn(">>>> inside message consumer.\nMessage from the queue: "+message.getContent());

    }

}
