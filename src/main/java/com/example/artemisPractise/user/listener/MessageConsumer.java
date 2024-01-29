package com.example.artemisPractise.user.listener;


import com.example.artemisPractise.user.config.JmsConfig;
import com.example.artemisPractise.user.entity.BusinessUser;
import com.example.artemisPractise.user.service.impl.SimpleEmailNotificationAdapterImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private final static Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    private final SimpleEmailNotificationAdapterImpl simpleEmailNotificationAdapterImpl;

    public MessageConsumer(SimpleEmailNotificationAdapterImpl simpleEmailNotificationAdapterImpl) {
        this.simpleEmailNotificationAdapterImpl = simpleEmailNotificationAdapterImpl;
    }

//    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void receiveJsonMessage(BusinessUser businessUser){
        String content = this.simpleEmailNotificationAdapterImpl.getContent(businessUser.getName());

        try {
//            this.simpleEmailNotificationAdapterImpl.sendEmail(businessUser.getEmail(), subject, content);
            logger.info("Email has been sent to the user.");
        } catch (Exception e) {
            logger.error("Some exception related to email process.");
            throw new RuntimeException(e);
        }

    }



}
