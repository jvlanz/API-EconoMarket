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
		if (listaCompra.getCompra()==null) {
			throw new RuntimeException("A compra deve ser informada");
		}
		if (listaCompra.getProdutoMarca()==null) {
			throw new RuntimeException("O produto deve ser informado");
		}
		if (listaCompra.getQuantidade()==0) { // TODO se quantidade for 0, remover item da lista de compras
			throw new RuntimeException("A quantidade não pode ser zero");
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
		lista.setCompra(listaCompra.getCompra());
		lista.setProdutoMarca(listaCompra.getProdutoMarca());
		lista.setQuantidade(listaCompra.getQuantidade());
		return repository.save(lista);
	}
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}
