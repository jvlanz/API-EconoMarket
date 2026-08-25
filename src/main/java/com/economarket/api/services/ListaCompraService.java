package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.entities.ListaCompra;
import com.economarket.api.repositories.ListaCompraRepository;

@Service
public class ListaCompraService {
	@Autowired
	private ListaCompraRepository repository;

	public ListaCompra salvar(ListaCompra listaCompra) {
		if (listaCompra.getProdutoMarca()==null) {
			throw new RuntimeException("O produto deve ser informado");
		}
		if (listaCompra.getMercado()==null) {
			throw new RuntimeException("A marca deve ser informada");
		}
		repository.save(listaCompra);
		return listaCompra;
	}

	public List<ListaCompra> consultar() {
		return repository.findAll();
	}
	public ListaCompra consultarUm(Long id) {
		Optional<ListaCompra> opt = repository.findById(id);
		ListaCompra lista = opt.orElseThrow(() -> new RuntimeException("produto/marca não encontrado"));
		return lista;
	}
	public ListaCompra alterar(Long id, ListaCompra listaCompra) {
		ListaCompra lista = consultarUm(id);
		lista.setProdutoMarca(listaCompra.getProdutoMarca());
		lista.setMercado(listaCompra.getMercado());
		return repository.save(lista);
	}
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}
