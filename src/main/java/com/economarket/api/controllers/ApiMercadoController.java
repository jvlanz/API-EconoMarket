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

import com.economarket.api.entities.ApiMercado;
import com.economarket.api.services.ApiMercadoService;

@RestController
@RequestMapping("APImercados")
public class ApiMercadoController {

	@Autowired
	private ApiMercadoService service;

	@GetMapping
	public ResponseEntity<List<ApiMercado>> getMercados() {
		return ResponseEntity.status(HttpStatus.OK).body(service.consultar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUmProduto(@PathVariable long id) {
		try {
			ApiMercado apiMercado = service.consultarUm(id);
			return ResponseEntity.status(HttpStatus.OK).body(apiMercado);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mercado não encontrado");
		}
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody ApiMercado apiMercado) {
		try {
			apiMercado = service.salvar(apiMercado);
			return ResponseEntity.status(HttpStatus.CREATED).body(apiMercado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable long id, @RequestBody ApiMercado apiMercado) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, apiMercado));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable long id) {
		service.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}

