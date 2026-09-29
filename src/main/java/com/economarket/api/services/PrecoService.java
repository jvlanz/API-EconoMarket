package com.economarket.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.dto.PrecoRequest;
//import com.economarket.api.entities.Compra;
import com.economarket.api.entities.Preco;
import com.economarket.api.entities.ProdutoMarca;
import com.economarket.api.entities.Mercado;
import com.economarket.api.repositories.MercadoRepository;
import com.economarket.api.repositories.PrecoRepository;
import com.economarket.api.repositories.ProdutoMarcaRepository;

@Service
public class PrecoService {
	private final PrecoRepository precoRepository;
	private final ProdutoMarcaRepository produtoMarcaRepository;
	private final MercadoRepository mercadoRepository;

	@Autowired
	public PrecoService(
		PrecoRepository precoRepository,
		ProdutoMarcaRepository produtoMarcaRepository,
		MercadoRepository mercadoRepository
	) {
		this.precoRepository = precoRepository;
		this.produtoMarcaRepository = produtoMarcaRepository;
		this.mercadoRepository = mercadoRepository;
	}
// id
// preco
// data
// mercado
// produtoMarca

	public Preco salvar(PrecoRequest request) {
		if (request.getIdProdutoMarca()==null) {
			throw new RuntimeException("idProdutoMarca deve ser informado");
		}
		if (request.getIdMercado()==null) {
			throw new RuntimeException("idMercado deve ser informado");
		}
		if (request.getPreco()==0) {
			throw new RuntimeException("O preco não deve ser nulo");
		}
		ProdutoMarca produtoMarca = produtoMarcaRepository.findById(request.getIdProdutoMarca()).orElseThrow(() -> new RuntimeException("ProdutoMarca não encontrado"));
		Mercado mercado = mercadoRepository.findById(request.getIdMercado()).orElseThrow(() -> new RuntimeException("Mercado não encontrado"));
		Preco preco = new Preco();
		preco.setProdutoMarca(produtoMarca);
		preco.setMercado(mercado);
		preco.setPreco(request.getPreco());
		precoRepository.save(preco);
		return preco;
	}

	public List<Preco> consultar() {
		return precoRepository.findAll();
	}
	public Preco consultarUm(Long id) {
		Optional<Preco> opt = precoRepository.findById(id);
		Preco pre = opt.orElseThrow(() -> new RuntimeException("produto não encontrado"));
		return pre;
	}
	public List<Preco> consultarPrecos(Long id) {
//		LocalDate hoje = new LocalDate();
		List<Preco> precos = new ArrayList<Preco>();
//		for(Preco pre : precoRepository.findByData(hoje)) {
		for(Preco pre : precoRepository.findAll()) {
			if (pre.getProdutoMarca().getProduto().getId()==id) {
				precos.add(pre);
			}
		}
		return precos;
	}
	public Preco alterar(Long id, PrecoRequest request) {
		if (request.getIdProdutoMarca()==null) {
			throw new RuntimeException("idProdutoMarca deve ser informado");
		}
		if (request.getIdMercado()==null) {
			throw new RuntimeException("idMercado deve ser informado");
		}
		if (request.getPreco()==0) {
			throw new RuntimeException("O preco não deve ser nulo");
		}
		ProdutoMarca produtoMarca = produtoMarcaRepository.findById(request.getIdProdutoMarca()).orElseThrow(() -> new RuntimeException("ProdutoMarca não encontrado"));
		Mercado mercado = mercadoRepository.findById(request.getIdMercado()).orElseThrow(() -> new RuntimeException("Mercado não encontrado"));
		Preco preco = new Preco();
		preco.setProdutoMarca(produtoMarca);
		preco.setMercado(mercado);
		preco.setPreco(request.getPreco());
		preco.setId(id);
		precoRepository.save(preco);
		return preco;
	}
	public void excluir(Long id) {
		precoRepository.deleteById(id);
	}
}
