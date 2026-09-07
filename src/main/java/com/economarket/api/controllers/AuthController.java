package com.economarket.api.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.economarket.api.dto.CadastroRequest;
import com.economarket.api.dto.LoginRequest;
import com.economarket.api.entities.Cliente;
import com.economarket.api.repositories.ClienteRepository;
import com.economarket.api.services.ClienteService;
import com.economarket.api.services.JwtService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ClienteService clienteService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtService jwtService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {

		Cliente usuario = clienteRepository.findByEmail(request.getEmail()).orElse(null);
		if (usuario == null) {
			return ResponseEntity.status(401).body("Usuário inválido");
		}

		if (!passwordEncoder.matches(request.getPassword(), usuario.getSenha())) {
			return ResponseEntity.status(401).body("Usuário ou senha inválidos");
		}

		String token = jwtService.gerarToken(usuario.getEmail(), usuario.getRole(), usuario.getNome(), usuario.getId());

		return ResponseEntity.ok(Map.of("token", token));
	}

	@PostMapping("/cadastro")
	public ResponseEntity<?> cadastrar(@RequestBody CadastroRequest request) {

		if (clienteRepository.findByEmail(request.getEmail()).isPresent()) {

			return ResponseEntity.badRequest().body("Usuário já existe");
		}

		Cliente usuario = new Cliente();

		usuario.setNome(request.getNome());
		usuario.setEmail(request.getEmail());
		usuario.setTelefone(request.getTelefone());

		usuario.setSenha(passwordEncoder.encode(request.getPassword()));

		usuario.setRole("USER");

		clienteRepository.save(usuario);

		return ResponseEntity.ok("Usuário cadastrado com sucesso");
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
		Cliente cliente = clienteService.consultarUm(id); // não deveria chamar clienteService??
		if (cliente!=null)
			return cliente;
		else
			return clienteService.consultarUm(0L);
	}
}
