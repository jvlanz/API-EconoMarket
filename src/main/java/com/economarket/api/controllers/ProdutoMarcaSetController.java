package com.economarket.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.economarket.api.entities.ProdutoMarca;
import com.economarket.api.dto.ProdutoMarcaRequest;
import com.economarket.api.services.ProdutoMarcaService;

@RestController
@RequestMapping("produtoMarca")
@CrossOrigin(origins = "*")
public class ProdutoMarcaSetController {
	@Autowired
	private ProdutoMarcaService service;

	@PostMapping("/{id}")
	public ResponseEntity<?> salvar(@RequestBody ProdutoMarcaRequest request) {
		try {
			ProdutoMarca produtoMarca = service.alterar(request.getId(), request);
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoMarca);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}

