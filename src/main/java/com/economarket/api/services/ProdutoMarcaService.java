package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.dto.ProdutoMarcaRequest;
import com.economarket.api.entities.Marca;
import com.economarket.api.entities.Produto;
import com.economarket.api.entities.ProdutoMarca;
import com.economarket.api.repositories.MarcaRepository;
import com.economarket.api.repositories.ProdutoMarcaRepository;
import com.economarket.api.repositories.ProdutoRepository;

@Service
public class ProdutoMarcaService {
	private final ProdutoMarcaRepository produtoMarcaRepository;
	private final ProdutoRepository produtoRepository;
	private final MarcaRepository marcaRepository;

	@Autowired
	public ProdutoMarcaService(
		ProdutoMarcaRepository produtoMarcaRepository,
		ProdutoRepository produtoRepository,
		MarcaRepository marcaRepository
	) {
		this.produtoMarcaRepository = produtoMarcaRepository;
		this.produtoRepository = produtoRepository;
		this.marcaRepository = marcaRepository;
	}

// id
// idProduto
// idMarca

	public ProdutoMarca salvar(ProdutoMarcaRequest request) {
		if (request.getIdProduto()==null) {
			throw new RuntimeException("idProduto deve ser informado");
		}
		if (request.getIdMarca()==null) {
			throw new RuntimeException("idMarca deve ser informada");
		}
		Produto produto = produtoRepository.findById(request.getIdProduto()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		Marca marca = marcaRepository.findById(request.getIdMarca()).orElseThrow(() -> new RuntimeException("Marca não encontrada"));
		ProdutoMarca produtoMarca = new ProdutoMarca();
		produtoMarca.setProduto(produto);
		produtoMarca.setMarca(marca);
		produtoMarcaRepository.save(produtoMarca);
		return produtoMarca;
	}

	public List<ProdutoMarca> consultar() {
		return produtoMarcaRepository.findAll();
	}
	public ProdutoMarca consultarUm(Long id) {
		Optional<ProdutoMarca> opt = produtoMarcaRepository.findById(id);
		ProdutoMarca produtoMarca = opt.orElseThrow(() -> new RuntimeException("produto/marca não encontrado"));
		return produtoMarca;
	}
	public ProdutoMarca alterar(Long id, ProdutoMarcaRequest request) {
		if (request.getIdProduto()==null) {
			throw new RuntimeException("idProduto deve ser informado");
		}
		if (request.getIdMarca()==null) {
			throw new RuntimeException("idMarca deve ser informada");
		}
		Produto produto = produtoRepository.findById(request.getIdProduto()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		Marca marca = marcaRepository.findById(request.getIdMarca()).orElseThrow(() -> new RuntimeException("Marca não encontrada"));
		ProdutoMarca produtoMarca = new ProdutoMarca();
		produtoMarca.setProduto(produto);
		produtoMarca.setMarca(marca);
		produtoMarca.setId(id);
		produtoMarcaRepository.save(produtoMarca);
		return produtoMarca;
	}
	public void excluir(Long id) {
		produtoMarcaRepository.deleteById(id);
	}
}

