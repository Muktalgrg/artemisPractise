package com.example.artemisPractise.user.config;


//import com.example.artemisPractise.user.listener.MessageConsumer;
import com.example.artemisPractise.user.entity.BusinessUser;
import com.example.artemisPractise.user.entity.BusinessUserDTO;
import com.example.artemisPractise.user.entity.NotificationDTO;
import com.example.artemisPractise.user.entity.PaymentDetails;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableJms
public class JmsConfig {
    public static final String BPS_NOTIFICATION = "bps-notification";

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        return new ActiveMQConnectionFactory();
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory =
                new CachingConnectionFactory(activeMQConnectionFactory());
        cachingConnectionFactory.setSessionCacheSize(10);

        return cachingConnectionFactory;
    }

    @Bean
    public BpsJmsTemplate bpsJmsTemplate(){
        BpsJmsTemplate bpsJmsTemplate = new BpsJmsTemplate(activeMQConnectionFactory());
        bpsJmsTemplate.setPubSubDomain(true); // topic will be used instead of queue
        bpsJmsTemplate.setMessageConverter(messageConverter());
        return bpsJmsTemplate;
    }
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory());
        factory.setMessageConverter(messageConverter());
        factory.setPubSubDomain(true); // topic will be used
        return factory;
    }
//    @Bean
//    public DefaultMessageListenerContainer orderMessageListenerContainer() {
//        SimpleJmsListenerEndpoint endpoint =
//                new SimpleJmsListenerEndpoint();
//        endpoint.setMessageListener(new StatusMessageListener("DMLC"));
//        endpoint.setDestination(status1Destination);
//
//        return orderDefaultJmsListenerContainerFactory()
//                .createListenerContainer(endpoint);
//    }



    @Bean
    public MessageConverter messageConverter(){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("notificationDTO", NotificationDTO.class);
        typeIdMappings.put("businessUserDTO", BusinessUserDTO.class);
        converter.setTypeIdMappings(typeIdMappings);
        return converter;
    }


//    @Bean
//    public SimpleMessageConverter messageConverter(){
//        return new SimpleMessageConverter();
//    }

    /*
//    @Bean
    public void createQueue() throws JMSException {
        System.out.println("------------ >> inside createQueue method --------");
        ActiveMQConnectionFactory factory = activeMQConnectionFactory();
        Connection connection = factory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination bpsNotificationDestination = session.createQueue(BPS_NOTIFICATION);  // message queue produces message and pushes here
        Destination testQueueDestination = session.createQueue(MY_TEST_QUEUE);


        MessageProducer notificationProducer = session.createProducer(bpsNotificationDestination);
        MessageProducer testQueueProducer = session.createProducer(testQueueDestination);

        MessageConsumer notificationConsumer =  session.createConsumer(bpsNotificationDestination);
        MessageConsumer testQueueConsumer =  session.createConsumer(testQueueDestination);

        System.out.println("notification destination: "+bpsNotificationDestination);
        System.out.println("notification producer: "+notificationProducer);
        System.out.println("notification consumer: "+notificationConsumer);

    }

    */




}
