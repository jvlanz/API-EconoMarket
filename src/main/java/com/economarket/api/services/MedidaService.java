package com.economarket.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.entities.Medida;
import com.economarket.api.repositories.MedidaRepository;

@Service
public class MedidaService {
	@Autowired
	private MedidaRepository repository;
// id
// medida
	private List<Medida> medidas = new ArrayList<Medida>();

	public Medida salvar(Medida medida) {
		if (medida.getMedida().isEmpty()) {
			throw new RuntimeException("A medida deve ser informada");
		}
		repository.save(medida);
		return medida;
	}

	public List<Medida> consultar() {
		return repository.findAll();
	}
	public Medida consultarUm(Long id) {
		Optional<Medida> opt = repository.findById(id);
		Medida med = opt.orElseThrow(() -> new RuntimeException("medida não encontrada"));
		return med;
	}
	public Medida alterar(Long id, Medida medida) {
		Medida med = consultarUm(id);
		med.setMedida(medida.getMedida());
		return repository.save(med);
	}
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}

