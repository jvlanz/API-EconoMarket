package com.economarket.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.entities.Produto;
import com.economarket.api.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repository;
// id
// descricao
// marca
// idMedida
// valorMedida
	private List<Produto> produtos = new ArrayList<Produto>();
	
	public Produto salvar(Produto produto) {
		if (produto.getDescricao().isEmpty()) {
			throw new RuntimeException("A descrição deve ser informada");
		}
		if (produto.getMedida()==null) {
			throw new RuntimeException("A medida deve ser informada");
		}
		if (produto.getValorMedida()<0) {
			throw new RuntimeException("O valor da medida não pode ser negativa");
		}
		repository.save(produto);
		return produto;
	}

	public List<Produto> consultar() {
		return repository.findAll();
	}
	public Produto consultarUm(Long id) {
		Optional<Produto> opt = repository.findById(id);
		Produto prod = opt.orElseThrow(() -> new RuntimeException("produto não encontrado"));
		return prod;
	}
	public Produto alterar(Long id, Produto produto) {
		Produto prod = consultarUm(id);
		prod.setDescricao(produto.getDescricao());
		prod.setMedida(produto.getMedida());
		prod.setValorMedida(produto.getValorMedida());
		return repository.save(prod);
	}
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}

