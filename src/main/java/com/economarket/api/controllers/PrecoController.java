package com.economarket.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.economarket.api.entities.Preco;
import com.economarket.api.services.PrecoService;

@RestController
@RequestMapping("precos")
public class PrecoController {

	@Autowired
	private PrecoService service;

	@GetMapping
	public ResponseEntity<List<Preco>> getPrecos() {
		return ResponseEntity.status(HttpStatus.OK).body(service.consultar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUmPreco(@PathVariable long id) {
		try {
			Preco preco = service.consultarUm(id);
			return ResponseEntity.status(HttpStatus.OK).body(preco);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Preco não encontrado");
		}
	}

// não será necessário alterar/remover preco via API

}