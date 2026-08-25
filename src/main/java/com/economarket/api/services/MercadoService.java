package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.entities.Mercado;
import com.economarket.api.repositories.MercadoRepository;

@Service
public class MercadoService {
	@Autowired
	private MercadoRepository repository;
// id
// nome
// link

	public Mercado salvar(Mercado mercado) {
		if (mercado.getNome().isEmpty()) {
			throw new RuntimeException("O nome deve ser informado");
		}
		if (mercado.getLink()==null) {
			throw new RuntimeException("O link deve ser informado");
		}
		repository.save(mercado);
		return mercado;
	}

	public List<Mercado> consultar() {
		return repository.findAll();
	}
	public Mercado consultarUm(Long id) {
		Optional<Mercado> opt = repository.findById(id);
		Mercado mer = opt.orElseThrow(() -> new RuntimeException("produto não encontrado"));
		return mer;
	}
	public Mercado alterar(Long id, Mercado mercado) {
		Mercado mer = consultarUm(id);
		mer.setNome(mercado.getNome());
		mer.setLink(mercado.getLink());
		return repository.save(mer);
	}
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}

