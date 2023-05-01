package tacos.messaging;

import java.util.HashMap;
import java.util.Map;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

import jakarta.jms.Destination;
import tacos.domain.TacoOrder;

@Configuration
public class MessagingConfig {

    @Bean
    Destination orderQueue() {
		return new ActiveMQQueue("tacocloud.order.queue");
	}
    
    @Bean
    MessageConverter messageConverter() {
    	MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
    	messageConverter.setTypeIdPropertyName("_typeId");
    	
    	Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
    	typeIdMappings.put("order", TacoOrder.class);
    	messageConverter.setTypeIdMappings(typeIdMappings);
    	
    	return messageConverter;
    }
}
