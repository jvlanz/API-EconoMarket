package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.dto.FavoritoRequest;
import com.economarket.api.entities.Cliente;
import com.economarket.api.entities.Favorito;
import com.economarket.api.entities.Produto;
import com.economarket.api.repositories.ClienteRepository;
import com.economarket.api.repositories.FavoritoRepository;
import com.economarket.api.repositories.ProdutoRepository;

@Service
public class FavoritoService {
	private final FavoritoRepository favoritoRepository;
	private final ClienteRepository clienteRepository;
	private final ProdutoRepository produtoRepository;

	@Autowired
	public FavoritoService(
		FavoritoRepository favoritoRepository,
		ClienteRepository clienteRepository,
		ProdutoRepository produtoRepository
	) {
		this.favoritoRepository = favoritoRepository;
		this.clienteRepository = clienteRepository;
		this.produtoRepository = produtoRepository;
	}
// id
// idCliente
// idProdutoMarca

	public Favorito salvar(FavoritoRequest request) {
		if (request.getIdCliente()==null) {
			throw new RuntimeException("O cliente deve ser informado");
		}
		if (request.getIdProduto()==null) {
			throw new RuntimeException("O produto deve ser informado");
		}
		Cliente cliente = clienteRepository.findById(request.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrada"));
		Produto produto = produtoRepository.findById(request.getIdProduto()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		Favorito favorito = new Favorito();
		favorito.setCliente(cliente);
		favorito.setProduto(produto);
		favoritoRepository.save(favorito);

		favoritoRepository.save(favorito);
		return favorito;
	}

	public List<Favorito> consultar() {
		return favoritoRepository.findAll();
	}
	public Favorito consultarUm(Long id) {
		Optional<Favorito> opt = favoritoRepository.findById(id);
		Favorito fav = opt.orElseThrow(() -> new RuntimeException("favorito não encontrado"));
		return fav;
	}
	public Favorito alterar(Long id, Favorito favorito) {
		Favorito fav = consultarUm(id);
		fav.setCliente(favorito.getCliente());
		fav.setProduto(favorito.getProduto());
		return favoritoRepository.save(fav);
	}
	public void excluir(Long id) {
		favoritoRepository.deleteById(id);
	}
}
