package com.gtg.email;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gtg.email.service.EmailSenderService;

@EnableAsync
@EnableWebMvc
@EnableAutoConfiguration
@ComponentScan(basePackages = { 
		"com.gtg.email",
		"com.gtg.email.service",
		"com.gtg.email.config",
		"com.gtg.core.repository"})
public class Application implements CommandLineRunner {

	@Autowired
	//EmailService emailService;
	
	private EmailSenderService  emailService;
	
	public void run(String... arg0) throws Exception {
		System.out.println("run() - start");
		Set<String> to = new HashSet<String>();
		
		to.add("vishnuawasthi121@gmail.com");
		//to.add("dharmendrachar@gmail.com");
		
		String  body= "Hi This is test email.";
		String  subject = "Email From Service";
		
		emailService.send(to, null,null, subject, body);
		
		//emailService.send(null, null, null, null);
		
		System.out.println("run() - end");
	}
	
	public static void main(String... args){
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}
}
