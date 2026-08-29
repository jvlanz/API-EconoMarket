package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.entities.Cliente;
import com.economarket.api.entities.Produto;
import com.economarket.api.entities.ListaCompra;
import com.economarket.api.repositories.ListaCompraRepository;
import com.economarket.api.repositories.ClienteRepository;
import com.economarket.api.repositories.ProdutoRepository;
import com.economarket.api.dto.ListaCompraTransfer;

@Service
public class ListaCompraService {
	private final ListaCompraRepository listaCompraRepository;
	private final ClienteRepository clienteRepository;
	private final ProdutoRepository produtoRepository;

	@Autowired
	public ListaCompraService(
		ListaCompraRepository listaCompraRepository,
		ClienteRepository clienteRepository,
		ProdutoRepository produtoRepository
	) {
		this.listaCompraRepository = listaCompraRepository;
		this.clienteRepository = clienteRepository;
		this.produtoRepository = produtoRepository;
	}

	public ListaCompra salvar(ListaCompraTransfer listaTransfer) {
		if (listaTransfer.getIdCliente()==null) {
			throw new RuntimeException("idCliente deve ser informado");
		}
		if (listaTransfer.getIdProduto()==null) {
			throw new RuntimeException("idProduto deve ser informado");
		}
		if (listaTransfer.getQuantidade()==0) { // TODO se quantidade for 0, remover item da lista de compras
			throw new RuntimeException("A quantidade não pode ser zero");
		}
		Cliente cliente = clienteRepository.findById(listaTransfer.getIdCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrada"));
		Produto produto = produtoRepository.findById(listaTransfer.getIdProduto()) .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
		ListaCompra listaCompra = new ListaCompra();
		listaCompra.setCliente(cliente);
		listaCompra.setProduto(produto);
		listaCompra.setQuantidade(listaTransfer.getQuantidade());
		listaCompraRepository.save(listaCompra);
		return listaCompra;
	}

	public List<ListaCompra> consultar() {
		return listaCompraRepository.findAll();
	}
	public ListaCompra consultarUm(Long id) {
		Optional<ListaCompra> opt = listaCompraRepository.findById(id);
		ListaCompra lista = opt.orElseThrow(() -> new RuntimeException("produto/marca não encontrado"));
		return lista;
	}
	public ListaCompra alterar(Long id, ListaCompra listaCompra) {
		ListaCompra lista = consultarUm(id);
		lista.setCliente(listaCompra.getCliente());
		lista.setProduto(listaCompra.getProduto());
		lista.setQuantidade(listaCompra.getQuantidade());
		return listaCompraRepository.save(lista);
	}
	public void excluir(Long id) {
		listaCompraRepository.deleteById(id);
	}
}
