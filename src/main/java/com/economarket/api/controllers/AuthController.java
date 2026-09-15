package com.economarket.api.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.economarket.api.dto.CadastroRequest;
import com.economarket.api.dto.LoginRequest;
import com.economarket.api.entities.Cliente;
import com.economarket.api.services.ClienteService;
import com.economarket.api.services.JwtService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	@Autowired
	ClienteService clienteService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtService jwtService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {

		Cliente usuario = clienteService.consultarEmail(request.getEmail());
		if (usuario == null) {
			return ResponseEntity.status(401).body("Usuário inválido");
		}

		if (!passwordEncoder.matches(request.getPassword(), usuario.getSenha())) {
			return ResponseEntity.status(401).body("Usuário ou senha inválidos");
		}

		String token = jwtService.gerarToken(usuario.getEmail(), usuario.getRole(), usuario.getNome(), usuario.getId());

		return ResponseEntity.ok(Map.of("token", token));
	}

	@PutMapping("/editar")
	public ResponseEntity<?> editar(@AuthenticationPrincipal Long id, @RequestBody CadastroRequest request) {

		if (id == null) {
			return ResponseEntity.status(401).body("Usuário não autenticado");
		}

		Cliente cliente = clienteService.consultarUm(id);

		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}

		cliente.setNome(request.getNome());
		cliente.setEmail(request.getEmail());

		// Só altera a senha se o usuário preencher
		if (request.getPassword() != null && !request.getPassword().isBlank()) {
			cliente.setSenha(passwordEncoder.encode(request.getPassword()));
		}

		clienteService.alterar(id, cliente);

		String token = jwtService.gerarToken(cliente.getEmail(), cliente.getRole(), cliente.getNome(), cliente.getId());

		return ResponseEntity.ok(Map.of("token", token));
	}

	@PostMapping("/cadastro")
	public ResponseEntity<?> cadastrar(@RequestBody CadastroRequest request) {

		if (clienteService.consultarEmail(request.getEmail()) != null) {
			return ResponseEntity.badRequest().body("Usuário já existe");
		}

		Cliente usuario = new Cliente();

		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());
		usuario.setTelefone(request.getTelefone());

		usuario.setSenha(passwordEncoder.encode(request.getPassword()));

		usuario.setRole("USER");

		clienteService.salvar(usuario);

		String token = jwtService.gerarToken(usuario.getEmail(), usuario.getRole(), usuario.getNome(), usuario.getId());
		return ResponseEntity.ok(Map.of("token", token));
	}

	@GetMapping("/status")
	public ResponseEntity<?> status(@AuthenticationPrincipal Long id) {
		if (id == null) {
			return ResponseEntity.ok(-1L);
		}
		return ResponseEntity.ok(id);
	}

	@GetMapping("/cliente")
	public Cliente cliente(@AuthenticationPrincipal Long id) {
		Cliente cliente = null;
		if (id != null)
			cliente = clienteService.consultarUm(id);
		return cliente;
	}
}
