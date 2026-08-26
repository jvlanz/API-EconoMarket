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

import com.economarket.api.entities.Mercado;
import com.economarket.api.services.MercadoService;

@RestController
@RequestMapping("mercados")
public class MercadoController {

	@Autowired
	private MercadoService service;

	@GetMapping
	public ResponseEntity<List<Mercado>> getMercados() {
		return ResponseEntity.status(HttpStatus.OK).body(service.consultar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUmMercado(@PathVariable long id) {
		try {
			Mercado mercado = service.consultarUm(id);
			return ResponseEntity.status(HttpStatus.OK).body(mercado);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mercado não encontrado");
		}
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Mercado mercado) {
		try {
			mercado = service.salvar(mercado);
			return ResponseEntity.status(HttpStatus.CREATED).body(mercado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable long id, @RequestBody Mercado mercado) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, mercado));
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

