package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.dto.FavoritoRequest;
import com.economarket.api.entities.Cliente;
import com.economarket.api.entities.Favorito;
import com.economarket.api.entities.ProdutoMarca;
import com.economarket.api.repositories.ClienteRepository;
import com.economarket.api.repositories.FavoritoRepository;
import com.economarket.api.repositories.ProdutoMarcaRepository;

@Service
public class FavoritoService {
	private final FavoritoRepository favoritoRepository;
	private final ClienteRepository clienteRepository;
	private final ProdutoMarcaRepository produtoMarcaRepository;

	@Autowired
	public FavoritoService(
		FavoritoRepository favoritoRepository,
		ClienteRepository clienteRepository,
		ProdutoMarcaRepository produtoMarcaRepository
	) {
		this.favoritoRepository = favoritoRepository;
		this.clienteRepository = clienteRepository;
		this.produtoMarcaRepository = produtoMarcaRepository;
	}
// id
// idCliente
// idProdutoMarca

	public Favorito salvar(FavoritoRequest request) {
		if (request.getIdCliente()==null) {
			throw new RuntimeException("O cliente deve ser informado");
		}
		if (request.getIdProdutoMarca()==null) {
			throw new RuntimeException("O produto/marca deve ser informado");
		}
		Cliente cliente = clienteRepository.findById(request.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrada"));
		ProdutoMarca produtoMarca = produtoMarcaRepository.findById(request.getIdProdutoMarca()).orElseThrow(() -> new RuntimeException("ProdutoMarca não encontrado"));
		Favorito favorito = new Favorito();
		favorito.setCliente(cliente);
		favorito.setProdutoMarca(produtoMarca);
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
		fav.setProdutoMarca(favorito.getProdutoMarca());
		return favoritoRepository.save(fav);
	}
	public void excluir(Long id) {
		favoritoRepository.deleteById(id);
	}
}
