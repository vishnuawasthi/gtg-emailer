package com.gtg.email.service;

import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

public interface EmailSenderService {

	public void send(Set<String> to, Set<String> cc, Set<String> bcc, String subject, String body);

	@Service
	public class Impl implements EmailSenderService {

		@Autowired
		private Environment env;

		@Override
		public void send(Set<String> to, Set<String> cc, Set<String> bcc, String subject, String body) {
			System.out.println("send() - start");

			final String username = env.getProperty("spring.mail.username");
			final String password = env.getProperty("spring.mail.password");
			String from = env.getProperty("spring.mail.from");

			// Get the Session object.
			Session session = Session.getInstance(getEmailProperties(), new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			try {
				InternetAddress[] toAddress = {};
				InternetAddress[] ccAddress = {};
				InternetAddress[] bccAddress = {};

				// To Addresses
				if (!CollectionUtils.isEmpty(to)) {
					toAddress = new InternetAddress[to.size()];
					int i = 0;
					for (String toEmail : to) {
						toAddress[i] = new InternetAddress(toEmail);
						i++;
					}
				}
				// CC Addresses
				if (!CollectionUtils.isEmpty(cc)) {
					toAddress = new InternetAddress[to.size()];
					int i = 0;
					for (String ccEmail : to) {
						ccAddress[i] = new InternetAddress(ccEmail);
						i++;
					}
				}
				// BCC
				if (!CollectionUtils.isEmpty(bcc)) {
					toAddress = new InternetAddress[to.size()];
					int i = 0;
					for (String bccEmail : cc) {
						bccAddress[i] = new InternetAddress(bccEmail);
						i++;
					}
				}
				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);
				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));
				// Set To: header field of the header.
				if (toAddress != null) {
					message.setRecipients(Message.RecipientType.TO, toAddress);
				}

				if (ccAddress != null) {
					message.addRecipients(Message.RecipientType.CC, ccAddress);
				}

				if (bccAddress != null) {
					message.addRecipients(Message.RecipientType.BCC, bccAddress);
				}
				// Set Subject: header field
				message.setSubject(subject);
				// Now set the actual message
				message.setText(body);
				// message.setFileName("classpath:templates/userRegistrationEmail.jsp");
				// Send message
				Transport.send(message);
				System.out.println("send() - end");
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		}

		public Properties getEmailProperties() {
			Properties props = new Properties();
			props.put("mail.smtp.auth", env.getProperty("spring.mail.smtp.auth"));
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", env.getProperty("spring.mail.host").trim());
			props.put("mail.smtp.port", env.getProperty("spring.mail.port"));
			// props.put("mail.smtp.user",
			// env.getProperty("spring.mail.username"));
			// props.put("mail.smtp.password",
			// env.getProperty("spring.mail.password"));
			return props;
		}

	}
}
