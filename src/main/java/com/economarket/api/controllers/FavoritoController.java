package com.economarket.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.economarket.api.dto.FavoritoRequest;
import com.economarket.api.entities.Favorito;
import com.economarket.api.services.FavoritoService;

@RestController
@RequestMapping("favoritos")
@CrossOrigin(origins = "*") // permitir acesso a esse endpoint a partir de qualquer lugar
public class FavoritoController {

	@Autowired
	private FavoritoService service;

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody FavoritoRequest request) {
		try {
			Favorito fav = service.salvar(request);
			return ResponseEntity.status(HttpStatus.CREATED).body(fav);
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
