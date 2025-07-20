package com.kafka.notify.notification_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.from}")
	private String from;

	public void sendEmail(String to, String content) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(to);
		mail.setSubject("Order Confirmation");
		mail.setText(content);
		mail.setFrom(from);
		mailSender.send(mail);
		System.out.println("Mail Sent Successfully");
	}

	public void sendEmailHTML(String to, String content) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			helper.setTo(to);
			helper.setSubject("Order Confirmation");
			helper.setText(content, true);
			helper.setFrom(from);

			mailSender.send(mimeMessage);
			System.out.println("Mail Sent Successfully");

		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("Failed to send mail");
		}
	}
}
