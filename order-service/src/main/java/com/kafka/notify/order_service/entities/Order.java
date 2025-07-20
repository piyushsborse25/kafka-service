package com.kafka.notify.order_service.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {

	private String orderId;
	private String customerEmail;
	private String orderDate;
	private List<String> items;
	private double totalAmount;
	private String shippingAddress;
	private String paymentStatus;
}
