package com.economarket.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.economarket.api.entities.ProdutoMarca;
import com.economarket.api.services.ProdutoMarcaService;

@RestController
@RequestMapping("produtosMarca")
public class ProdutoMarcaController {

	@Autowired
	private ProdutoMarcaService service;

	@GetMapping
	public ResponseEntity<List<ProdutoMarca>> getProdutosMarca() {
		return ResponseEntity.status(HttpStatus.OK).body(service.consultar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUmProdutoMarca(@PathVariable long id) {
		try {
			ProdutoMarca produtoMarca = service.consultarUm(id);
			return ResponseEntity.status(HttpStatus.OK).body(produtoMarca);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto/Marca não encontrado");
		}
	}

// não será necessário alterar/remover produtos via API
//	@PostMapping
//	public ResponseEntity<?> salvar(@RequestBody Produto produto) { // requestbody para pegar produto da requisicao
//		try {
//			produto = service.salvar(produto);
//			return ResponseEntity.status(HttpStatus.CREATED).body(produto);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//		}
//	}
//
//	@PutMapping("/{id}")
//	public ResponseEntity<?> alterar(@PathVariable long id, @RequestBody Produto produto) {
//		try {
//			return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, produto));
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//			
//		}
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> deletar(@PathVariable long id) {
//		service.excluir(id);
//		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//	}

}

