package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.economarket.api.entities.Compra;
import com.economarket.api.repositories.CompraRepository;

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
		repository.save(compra);
		return compra;
	}

	public List<Compra> consultar() {
		return repository.findAll();
	}
	public Compra consultarUm(Long id) {
		Optional<Compra> opt = repository.findById(id);
		Compra compra = opt.orElseThrow(() -> new RuntimeException("Compra não encontrada"));
		return compra;
	}
	public Compra alterar(Long id, Compra compra) {
		Compra co = consultarUm(id);
		co.setCliente(compra.getCliente());
		co.setListaCompra(compra.getListaCompra());
		co.setData(compra.getData());
		return repository.save(co);
	}
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}
