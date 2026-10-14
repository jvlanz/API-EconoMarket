package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.entities.Cliente;
import com.economarket.api.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;

	public Cliente salvar(Cliente cliente) {		
		/*if (repository.existsByEmail(cliente.getEmail())) {
			throw new RuntimeException("Este e-mail já está cadastrado");
		}*/
		return repository.save(cliente);
	}
	public void deletar(Long id) {
		repository.deleteById(id);
	}
	public List<Cliente> consultar() {
		return repository.findAll();
	}
	public Cliente consultarUm(Long id) {
		Optional<Cliente> opt = repository.findById(id);
		Cliente cliente = opt.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		return cliente;
	}
	public Cliente consultarEmail(String email) {
		return repository.findByEmail(email).orElse(null);
	}
	public Cliente alterar(Long id, Cliente cliente) {
		Cliente clienteConsultado = consultarUm(id);
		clienteConsultado.setNome(cliente.getNome());
		clienteConsultado.setEmail(cliente.getEmail());
		clienteConsultado.setTelefone(cliente.getTelefone());
		clienteConsultado.setListaCompra(cliente.getListaCompra());
		clienteConsultado.setFavoritos(cliente.getFavoritos());
		clienteConsultado.setRole(cliente.getRole());
		clienteConsultado.setAssinatura(cliente.getAssinatura());
		return repository.save(clienteConsultado);
	}

}
