package com.economarket.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.entities.Categoria;
import com.economarket.api.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repository;
// id
// categoria

	public Categoria salvar(Categoria categoria) {
		if (categoria.getCategoria().isEmpty()) {
			throw new RuntimeException("A categoria deve ser informada");
		}
		repository.save(categoria);
		return categoria;
	}

	public List<Categoria> consultar() {
		return repository.findAll();
	}
	public Categoria consultarUm(Long id) {
		Optional<Categoria> opt = repository.findById(id);
		Categoria cat = opt.orElseThrow(() -> new RuntimeException("categoria não encontrada"));
		return cat;
	}
	public Categoria alterar(Long id, Categoria categoria) {
		Categoria cat = consultarUm(id);
		cat.setCategoria(categoria.getCategoria());
		return repository.save(cat);
	}
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}

