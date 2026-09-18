package com.economarket.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.economarket.api.dto.ContatoRequest;
import com.economarket.api.services.ContatoService;

@RestController
@RequestMapping("/contato")
public class ContatoController {
	
	@Autowired
	private ContatoService contatoService;
	
	@PostMapping
	public ResponseEntity<String> enviarMensagem(@RequestBody ContatoRequest contato){
		contatoService.enviarEmail(contato);
		
		return ResponseEntity.ok("Email enviado!");
	}
	
	
}
