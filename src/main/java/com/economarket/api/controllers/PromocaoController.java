package com.economarket.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.economarket.api.entities.Promocao;
import com.economarket.api.services.PromocaoService;

@RestController
@RequestMapping("promocoes")
@CrossOrigin(origins = "*")
public class PromocaoController {

	@Autowired
	private PromocaoService service;

	@GetMapping
	public ResponseEntity<List<Promocao>> getPromocao() {
		return ResponseEntity.status(HttpStatus.OK).body(service.consultar());
	}

}

