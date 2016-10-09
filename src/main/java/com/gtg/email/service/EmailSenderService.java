package com.gtg.email.service;

import java.util.Set;

public interface EmailSenderService {

	public void send(Set<String> to, Set<String> cc, Set<String> bcc, String subject, String body);

	public void sendAsync(Set<String> to, Set<String> cc, Set<String> bcc, String subject, String body);

}
