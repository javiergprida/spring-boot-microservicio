package com.jagp.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EnvioMailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail (String to, String subject, String text) {
		
		SimpleMailMessage mensaje = new SimpleMailMessage();
		
		mensaje.setFrom("javierprida0123@gmail.com");
		mensaje.setTo(to);
		mensaje.setSubject(subject);
		mensaje.setText(text);
		
		mailSender.send(mensaje);
		
	}

}
