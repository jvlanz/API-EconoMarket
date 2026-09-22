package com.economarket.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.economarket.api.dto.ContatoRequest;

@Service
public class ContatoService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public void enviarEmail(ContatoRequest contato) {
		SimpleMailMessage email = new SimpleMailMessage();

        email.setTo("economarketofc@gmail.com");

        email.setSubject(contato.getTitulo());

        email.setText(
            "Nome: " + contato.getNome() +
            "\nE-mail: " + contato.getEmail() +
            "\n\nMensagem:\n" +
            contato.getMensagem()
        );

        mailSender.send(email);
	}
}
