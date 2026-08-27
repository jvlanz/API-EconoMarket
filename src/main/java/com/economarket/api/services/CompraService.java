package com.economarket.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.entities.Compra;
import com.economarket.api.repositories.CompraRepository;

@Service
public class CompraService {
	@Autowired
	private CompraRepository repository;
// id
// listaCompra
// data
// cliente

	public Compra salvar(Compra compra) {
		if (compra.getCliente()==null) {
			throw new RuntimeException("O cliente deve ser informado");
		}
		if (compra.getListaCompra()==null) {
			throw new RuntimeException("A lista de compras deve ser informada");
		}
		if (compra.getStatus()==null) {
			throw new RuntimeException("O status da compra deve ser informado");
		}
		repository.save(compra);
		return compra;
	}

	public List<Compra> consultar() {
		return repository.findAll();
	}
	public Compra consultarUm(Long id) {
//		Optional<Compra> opt = repository.findById(id);
//		Compra compra = opt.orElseThrow(() -> new RuntimeException("Compra não encontrada"));
		for(Compra compra : repository.findAll()) {
			if (compra.getCliente().getId()==id && compra.getStatus().getId()==1) { // retorna PRIMEIRO carrinho aberto de cliente
				return compra;
			}
		}
		new RuntimeException("Usuário não possui compra em aberto");
		return null;
	}
	public Compra alterar(Long id, Compra compra) {
		Compra co = consultarUm(id);
		co.setCliente(compra.getCliente());
		co.setListaCompra(compra.getListaCompra());
		co.setData(compra.getData());
		co.setStatus(compra.getStatus());
		return repository.save(co);
	}
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}
