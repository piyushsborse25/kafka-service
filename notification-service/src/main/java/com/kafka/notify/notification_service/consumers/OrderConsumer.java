package com.kafka.notify.notification_service.consumers;

import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.OrderedJSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.notify.notification_service.services.EmailService;
import com.kafka.notify.notification_service.utils.OrderEmailUtils;

@Service
public class OrderConsumer {

	@Autowired
	EmailService emailService;

	@KafkaListener(topics = { "orders" }, groupId = "notify-group")
	public void ordersListner(String order) {
		OrderedJSONObject orderObject = null;
		try {
			orderObject = new OrderedJSONObject(order);
			emailService.sendEmailHTML(orderObject.getString("customerEmail"),
					OrderEmailUtils.buildOrderEmailHtml(orderObject));
			System.out.println("Order Processed with ID: " + orderObject.getString("orderId"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
