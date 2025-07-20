package com.kafka.notify.order_service.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.notify.order_service.entities.Order;

@Service
public class OrderProducer {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	public boolean sendOrder(String topic, Order order) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			kafkaTemplate.send(topic, mapper.writeValueAsString(order));
			return true;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return false;
		}
	}

}
