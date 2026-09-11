package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.dto.ProdutoRequest;
import com.economarket.api.entities.Categoria;
import com.economarket.api.entities.Produto;
import com.economarket.api.repositories.CategoriaRepository;
import com.economarket.api.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	private final ProdutoRepository produtoRepository;
	private final CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoService(
		ProdutoRepository produtoRepository,
		CategoriaRepository categoriaRepository
	) {
		this.produtoRepository = produtoRepository;
		this.categoriaRepository = categoriaRepository;
	}
	
// id
// descricao
// marca
// idMedida
// valorMedida
// figura
// precoMin
// precoMax
	
	public Produto salvar(ProdutoRequest request) {
		if (request.getDescricao().isEmpty()) {
			throw new RuntimeException("A descrição deve ser informada");
		}
		if (request.getMedida()==null) {
			throw new RuntimeException("A medida deve ser informada");
		}
		if (request.getValorMedida()<0) {
			throw new RuntimeException("O valor da medida não pode ser negativa");
		}
		if (request.getPrecoMin()<0) {
			throw new RuntimeException("O valor do preço mínimo não pode ser negativo");
		}
		if (request.getPrecoMax()<0) {
			throw new RuntimeException("O valor do preço máximo não pode ser negativo");
		}
		Categoria categoria = categoriaRepository.findById(request.getIdCategoria()).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
		Produto produto = new Produto();
		produto.setDescricao(request.getDescricao());
		produto.setCategoria(categoria);
		produto.setMedida(request.getMedida());
		produto.setValorMedida(request.getValorMedida());
		produto.setPrecoMin(request.getPrecoMin());
		produto.setPrecoMax(request.getPrecoMax());
		produto.setFigura(request.getFigura());
		produtoRepository.save(produto);
		return produto;
	}

	public List<Produto> consultar() {
		return produtoRepository.findAll();
	}
	public Produto consultarUm(Long id) {
		Optional<Produto> opt = produtoRepository.findById(id);
		Produto prod = opt.orElseThrow(() -> new RuntimeException("produto não encontrado"));
		return prod;
	}
	public Produto alterar(Long id, ProdutoRequest request) {
		if (request.getDescricao().isEmpty()) {
			throw new RuntimeException("A descrição deve ser informada");
		}
		if (request.getMedida()==null) {
			throw new RuntimeException("A medida deve ser informada");
		}
		if (request.getValorMedida()<0) {
			throw new RuntimeException("O valor da medida não pode ser negativa");
		}
		if (request.getPrecoMin()<0) {
			throw new RuntimeException("O valor do preço mínimo não pode ser negativo");
		}
		if (request.getPrecoMax()<0) {
			throw new RuntimeException("O valor do preço máximo não pode ser negativo");
		}
		Categoria categoria = categoriaRepository.findById(request.getIdCategoria()).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
		Produto produto = new Produto();
		produto.setDescricao(request.getDescricao());
		produto.setCategoria(categoria);
		produto.setMedida(request.getMedida());
		produto.setValorMedida(request.getValorMedida());
		produto.setPrecoMin(request.getPrecoMin());
		produto.setPrecoMax(request.getPrecoMax());
		produto.setFigura(request.getFigura());
		produto.setId(id);
		return produtoRepository.save(produto);
	}
	public void excluir(Long id) {
		produtoRepository.deleteById(id);
	}
}

