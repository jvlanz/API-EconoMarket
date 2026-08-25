package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.entities.ProdutoMarca;
import com.economarket.api.repositories.ProdutoMarcaRepository;

@Service
public class ProdutoMarcaService {
	@Autowired
	private ProdutoMarcaRepository repository;
// id
// idProduto
// idMarca

	public ProdutoMarca salvar(ProdutoMarca produtoMarca) {
		if (produtoMarca.getProduto()==null) {
			throw new RuntimeException("O produto deve ser informado");
		}
		if (produtoMarca.getMarca()==null) {
			throw new RuntimeException("A marca deve ser informada");
		}
		repository.save(produtoMarca);
		return produtoMarca;
	}

	public List<ProdutoMarca> consultar() {
		return repository.findAll();
	}
	public ProdutoMarca consultarUm(Long id) {
		Optional<ProdutoMarca> opt = repository.findById(id);
		ProdutoMarca prodM = opt.orElseThrow(() -> new RuntimeException("produto/marca não encontrado"));
		return prodM;
	}
	public ProdutoMarca alterar(Long id, ProdutoMarca produtoMarca) {
		ProdutoMarca prodM = consultarUm(id);
		prodM.setProduto(produtoMarca.getProduto());
		prodM.setMarca(produtoMarca.getMarca());
		return repository.save(prodM);
	}
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}

