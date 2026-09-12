package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.dto.PromocaoRequest;
import com.economarket.api.entities.Preco;
import com.economarket.api.entities.Promocao;
import com.economarket.api.repositories.PromocaoRepository;
import com.economarket.api.repositories.PrecoRepository;

@Service
public class PromocaoService {
	private final PromocaoRepository promocaoRepository;
	private final PrecoRepository precoRepository;

	@Autowired
	public PromocaoService(
		PromocaoRepository promocaoRepository,
		PrecoRepository precoRepository
	) {
		this.promocaoRepository = promocaoRepository;
		this.precoRepository = precoRepository;
	}

// id
// idProduto
// idMarca

	public Promocao salvar(PromocaoRequest request) {
		if (request.getIdPreco()==null) {
			throw new RuntimeException("idPreco deve ser informado");
		}
		Preco preco = precoRepository.findById(request.getIdPreco()).orElseThrow(() -> new RuntimeException("Preco não encontrado"));
		Promocao promocao = new Promocao();
		promocao.setPreco(preco);
		promocaoRepository.save(promocao);
		return promocao;
	}

	public List<Promocao> consultar() {
		return promocaoRepository.findAll();
	}
	public Promocao consultarUm(Long id) {
		Optional<Promocao> opt = promocaoRepository.findById(id);
		Promocao promocao = opt.orElseThrow(() -> new RuntimeException("promoção não encontrada"));
		return promocao;
	}
	public Promocao alterar(Long id, PromocaoRequest request) {
		if (request.getIdPreco()==null) {
			throw new RuntimeException("idPreco deve ser informado");
		}
		Preco preco = precoRepository.findById(request.getIdPreco()).orElseThrow(() -> new RuntimeException("Preco não encontrado"));
		Promocao promocao = new Promocao();
		promocao.setPreco(preco);
		promocao.setId(id);
		promocaoRepository.save(promocao);
		return promocao;
	}
	public void excluir(Long id) {
		promocaoRepository.deleteById(id);
	}
}

