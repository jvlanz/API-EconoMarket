package com.economarket.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.economarket.api.entities.Compra;
import com.economarket.api.entities.Preco;
import com.economarket.api.repositories.PrecoRepository;

@Service
public class PrecoService {
	@Autowired
	private PrecoRepository repository;
// id
// preco
// data
// mercado
// produtoMarca

	public Preco salvar(Preco preco) {
		if (preco.getProdutoMarca()==null) {
			throw new RuntimeException("O produto/marca deve ser informado");
		}
		if (preco.getMercado()==null) {
			throw new RuntimeException("O mercado deve ser informado");
		}
		if (preco.getPreco()==0) {
			throw new RuntimeException("O preco não deve ser nulo");
		}
		repository.save(preco);
		return preco;
	}

	public List<Preco> consultar() {
		return repository.findAll();
	}
	public Preco consultarUm(Long id) {
		Optional<Preco> opt = repository.findById(id);
		Preco pre = opt.orElseThrow(() -> new RuntimeException("produto não encontrado"));
		return pre;
	}
	public List<Preco> consultarPrecos(Long id) {
		List<Preco> precos = new ArrayList();
		for(Preco pre : repository.findAll()) {
			if (pre.getProdutoMarca().getId()==id) { // retorna PRIMEIRO carrinho aberto de cliente
				precos.add(pre);
			}
		}
		return precos;
	}
	public Preco alterar(Long id, Preco preco) {
		Preco pre = consultarUm(id);
		pre.setMercado(preco.getMercado());
		pre.setProdutoMarca(preco.getProdutoMarca());
		pre.setPreco(preco.getPreco());
		return repository.save(pre);
	}
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}
