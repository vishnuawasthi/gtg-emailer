package com.gtg.email.service;
/*package com.shopping.email.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

public interface EmailService {

	public void send(Set<String> to,Set<String> cc,String subject, String body);
	
	@Service
	public class Impl  implements EmailService{
		
		@Autowired
		private MailSender mailSender;
		
		@Autowired
		private SimpleMailMessage simpleMailMessage;
		
		@Override
		public void send(Set<String> to, Set<String> cc, String subject, String body) {
			System.out.println("send() - start");
			  SimpleMailMessage mailToSend = new SimpleMailMessage(simpleMailMessage);
			  mailToSend.setTo("vishnuawasthi121@gmail.com");
			  mailToSend.setSubject("Test Email By Vishnu");
			  mailToSend.setText("Hi , This is a test email.");
			  mailSender.send(mailToSend);
			System.out.println("send() - end");
		}
		
	}
	
}
*/