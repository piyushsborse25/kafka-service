package com.kafka.notify.order_service.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import com.kafka.notify.order_service.entities.Order;

public class OrderUtil {

	public static Order enrich(Order order) {
		order.setOrderId(generateOrderId());
		order.setOrderDate(currentTimestamp());
		order.setTotalAmount(generateTotalAmount(order.getItems()));
		order.setPaymentStatus("PAID");
		return order;
	}

	private static String generateOrderId() {
		return "ORD" + (100000 + new Random().nextInt(900000));
	}

	private static String currentTimestamp() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	private static double generateTotalAmount(List<String> items) {
		double basePrice = 20000.0;
		double pricePerItem = 10000.0;
		return basePrice + (items != null ? items.size() * pricePerItem : 0);
	}
}
