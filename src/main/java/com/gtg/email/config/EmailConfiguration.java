package com.gtg.email.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfiguration {
	/*
	@Value("#{spring.mail.host}")
	public String host;
	
	@Value("#{spring.mail.protocol}")
	public String protocol;
	
	@Value("#{spring.mail.port}")
	public String port;
	
	@Value("#{spring.mail.default-encoding}")
	public String defaultEncoding;
	
	@Value("#{spring.mail.username}")
	public  String password;
	
	@Value("#{spring.mail.username}")
	public String  username;
*/
	
	@Autowired
	private Environment env;
	
	@Bean
	public JavaMailSender javaMailSender(){
		JavaMailSenderImpl impl = new JavaMailSenderImpl();
		/*impl.setDefaultEncoding(defaultEncoding);
		impl.setUsername(username);
		impl.setPassword(password);
		impl.setProtocol(protocol);
		impl.setHost(host);
		impl.setPort(Integer.parseInt(port));*/
		
		
		impl.setDefaultEncoding(env.getProperty("spring.mail.default-encoding"));
		impl.setUsername(env.getProperty("spring.mail.username"));
		impl.setPassword(env.getProperty("spring.mail.password"));
		impl.setProtocol(env.getProperty("spring.mail.protocol"));
		impl.setHost(env.getProperty("spring.mail.host"));
		impl.setPort(Integer.parseInt(env.getProperty("spring.mail.port")));
		return impl;
	}
	
	@Bean
	public SimpleMailMessage simpleMailMessage(){
		SimpleMailMessage message =  new SimpleMailMessage();
		return message;
	}

	
	
}
