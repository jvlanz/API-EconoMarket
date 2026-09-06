package com.economarket.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.economarket.api.entities.ListaCompra;
import com.economarket.api.services.ListaCompraService;
import com.economarket.api.dto.ListaCompraRequest;

@RestController
@RequestMapping("listaCompra")
@CrossOrigin(origins = "*") // permitir acesso a esse endpoint a partir de qualquer lugar
public class ListaCompraController {

	@Autowired
	private ListaCompraService service;

	@GetMapping
	public ResponseEntity<List<ListaCompra>> getListaCompras() {
		return ResponseEntity.status(HttpStatus.OK).body(service.consultar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUmaListaCompra(@PathVariable long id) {
		try {
			ListaCompra lItem = service.consultarUm(id);
			return ResponseEntity.status(HttpStatus.OK).body(lItem);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lista de compras não encontrada");
		}
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody ListaCompraRequest request) {
		try {
			ListaCompra lItem = service.salvar(request);
			return ResponseEntity.status(HttpStatus.CREATED).body(lItem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable long id, @RequestBody ListaCompraRequest request) {
		try {
			ListaCompra lItem = service.alterar(id, request);
			return ResponseEntity.status(HttpStatus.CREATED).body(lItem);
//			return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, lItem));
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

