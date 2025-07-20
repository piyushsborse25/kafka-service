package com.kafka.notify.notification_service.utils;

import org.apache.wink.json4j.JSONArray;
import org.apache.wink.json4j.JSONException;
import org.apache.wink.json4j.OrderedJSONObject;

public class OrderEmailUtils {

	public static String buildOrderEmailHtml(OrderedJSONObject orderJson) throws JSONException {
		StringBuilder html = new StringBuilder();

		html.append("<!DOCTYPE html>");
		html.append("<html><head><style>").append("body { font-family: Arial, sans-serif; color: #333; }").append(
				".container { max-width: 600px; margin: auto; border: 1px solid #ddd; border-radius: 10px; padding: 20px; background-color: #f9f9f9; }")
				.append("h2 { color: #2c3e50; }").append(".order-summary { margin-top: 20px; }")
				.append(".order-summary th, .order-summary td { padding: 8px 12px; text-align: left; }")
				.append(".order-summary th { background-color: #f2f2f2; }")
				.append(".footer { margin-top: 30px; font-size: 0.9em; color: #555; }").append("</style></head><body>");

		html.append("<div class=\"container\">").append("<h2>Order Confirmation - ")
				.append(orderJson.getString("orderId")).append("</h2>").append("<p>Hello,</p>")
				.append("<p>Thank you for your order placed on <strong>").append(orderJson.getString("orderDate"))
				.append("</strong>.</p>");

		html.append("<table class=\"order-summary\" width=\"100%\" border=\"1\" cellspacing=\"0\">")
				.append("<thead><tr><th>Item</th></tr></thead><tbody>");

		JSONArray items = orderJson.getJSONArray("items");
		for (int i = 0; i < items.length(); i++) {
			html.append("<tr><td>").append(items.getString(i)).append("</td></tr>");
		}

		html.append("</tbody></table>");

		html.append("<p><strong>Total Amount:</strong> ₹").append(orderJson.getDouble("totalAmount")).append("</p>")
				.append("<p><strong>Shipping Address:</strong> ").append(orderJson.getString("shippingAddress"))
				.append("</p>").append("<p><strong>Payment Status:</strong> ")
				.append(orderJson.getString("paymentStatus")).append("</p>");

		html.append("<div class=\"footer\">")
				.append("<p>If you have any questions, contact us at support@example.com</p>")
				.append("<p>Thank you for shopping with us!</p>").append("</div></div></body></html>");

		return html.toString();
	}
}
