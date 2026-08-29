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

import com.economarket.api.entities.Cliente;
import com.economarket.api.services.ClienteService;

@RestController
@RequestMapping("clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> consultarUmCliente(@PathVariable Long id) {
		try {
		Cliente cliente = service.consultarUm(id);
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado");
		}
	}

	@GetMapping("/{id}/size")
	public ResponseEntity<?> obterTamanho(@PathVariable long id) {
		try {
			Cliente cliente = service.consultarUm(id);
			return ResponseEntity.status(HttpStatus.OK).body(cliente.getListaCompra().size());
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não encontrado");
		}
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Cliente cliente) {
		try {
		cliente = service.salvar(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Cliente cliente) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, cliente));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable long id) {
		service.deletar(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
