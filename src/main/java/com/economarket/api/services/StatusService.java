package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.entities.Status;
import com.economarket.api.repositories.StatusRepository;

@Service
public class StatusService {
	@Autowired
	private StatusRepository repository;
// id
// marca

	public Status salvar(Status status) {
		if (status.getStatus().isEmpty()) {
			throw new RuntimeException("O status ser informado");
		}
		repository.save(status);
		return status;
	}

	public List<Status> consultar() {
		return repository.findAll();
	}
	public Status consultarUm(Long id) {
		Optional<Status> opt = repository.findById(id);
		Status status = opt.orElseThrow(() -> new RuntimeException("status não encontrado"));
		return status;
	}
	public Status alterar(Long id, Status status) {
		Status stat = consultarUm(id);
		stat.setStatus(status.getStatus());
		return repository.save(stat);
	}
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}

