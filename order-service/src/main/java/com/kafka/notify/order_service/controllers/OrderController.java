package com.kafka.notify.order_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.notify.order_service.entities.Order;
import com.kafka.notify.order_service.producers.OrderProducer;

@RestController
@RequestMapping(path = "/v1")
public class OrderController {

	@Autowired
	private OrderProducer orderProducer;

	@PostMapping(path = "/placeOrder")
	public ResponseEntity<String> placeOrder(@RequestBody Order order) {
		System.out.println(order);
		boolean success = orderProducer.sendOrder("orders", order);

		if (success) {
			return ResponseEntity.ok("Order placed and sent to Kafka.");
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
