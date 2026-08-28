package com.economarket.api.controllers;

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

import com.economarket.api.entities.Compra;
import com.economarket.api.services.CompraService;

@RestController
@RequestMapping("compras")
@CrossOrigin(origins = "*")
public class CompraController {

	@Autowired
	private CompraService service;

//	@GetMapping
//	public ResponseEntity<List<Compra>> getCompras() {
//		return ResponseEntity.status(HttpStatus.OK).body(service.consultar());
//	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUmaCompra(@PathVariable long id) {
		try {
			Compra compra = service.consultarUm(id);
			return ResponseEntity.status(HttpStatus.OK).body(compra);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Compra não encontrado");
		}
	}

	@GetMapping("/{id}/size")
	public ResponseEntity<?> obterTamanho(@PathVariable long id) {
		try {
			Compra compra = service.consultarUm(id);
			return ResponseEntity.status(HttpStatus.OK).body(compra.getListaCompra().size());
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Compra não encontrado");
		}
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Compra lista) {
		try {
			lista = service.salvar(lista);
			return ResponseEntity.status(HttpStatus.CREATED).body(lista);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable long id, @RequestBody Compra lista) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, lista));
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
