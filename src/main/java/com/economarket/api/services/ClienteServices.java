package com.economarket.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.entities.Cliente;
import com.economarket.api.repositories.ClienteRepositories;

@Service
public class ClienteServices {
	@Autowired
	private ClienteRepositories repository;
	private List<Cliente> clientes = new ArrayList();
	
	
	public Cliente salvar(Cliente cliente) {
		if(cliente.getNome().isBlank()) {
			throw new RuntimeException("O nome precisa ser informado");
		}
		
		if (cliente.getNome().length()<3) {
			throw new RuntimeException("O nome precisa ter mais de 2 caracteres");
		}
		//mais validações
		
		return repository.save(cliente);
	}

}
