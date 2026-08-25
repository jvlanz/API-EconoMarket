package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.entities.Marca;
import com.economarket.api.repositories.MarcaRepository;

@Service
public class MarcaService {
	@Autowired
	private MarcaRepository repository;
// id
// marca

	public Marca salvar(Marca marca) {
		if (marca.getMarca().isEmpty()) {
			throw new RuntimeException("A marca deve ser informada");
		}
		repository.save(marca);
		return marca;
	}

	public List<Marca> consultar() {
		return repository.findAll();
	}
	public Marca consultarUm(Long id) {
		Optional<Marca> opt = repository.findById(id);
		Marca mar = opt.orElseThrow(() -> new RuntimeException("marca não encontrada"));
		return mar;
	}
	public Marca alterar(Long id, Marca marca) {
		Marca mar = consultarUm(id);
		mar.setMarca(marca.getMarca());
		return repository.save(mar);
	}
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}

