package com.economarket.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.economarket.api.entities.Produto;
import com.economarket.api.dto.ProdutoRequest;
import com.economarket.api.services.ProdutoService;

@RestController
@RequestMapping("produto")
@CrossOrigin(origins = "*")
public class ProdutoSetController {
	@Autowired
	private ProdutoService service;

	@PostMapping("/{id}")
	public ResponseEntity<?> salvar(@RequestBody ProdutoRequest request) {
		try {
			Produto produto = service.alterar(request.getId(), request);
			return ResponseEntity.status(HttpStatus.CREATED).body(produto);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}

