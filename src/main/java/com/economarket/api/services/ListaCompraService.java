package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.entities.Compra;
import com.economarket.api.entities.ProdutoMarca;
import com.economarket.api.entities.ListaCompra;
import com.economarket.api.repositories.ListaCompraRepository;
import com.economarket.api.repositories.CompraRepository;
import com.economarket.api.repositories.ProdutoMarcaRepository;
import com.economarket.api.dto.ListaCompraTransfer;

@Service
public class ListaCompraService {
	private final ListaCompraRepository listaCompraRepository;
	private final CompraRepository compraRepository;
	private final ProdutoMarcaRepository produtoMarcaRepository;

	@Autowired
	public ListaCompraService(
		ListaCompraRepository listaCompraRepository,
		CompraRepository compraRepository,
		ProdutoMarcaRepository produtoMarcaRepository
	) {
		this.listaCompraRepository = listaCompraRepository;
		this.compraRepository = compraRepository;
		this.produtoMarcaRepository = produtoMarcaRepository;
	}

	public ListaCompra salvar(ListaCompraTransfer listaTransfer) {
		if (listaTransfer.getIdCompra()==null) {
			throw new RuntimeException("idCompra deve ser informado");
		}
		if (listaTransfer.getIdProdutoMarca()==null) {
			throw new RuntimeException("idProdutoMarca deve ser informado");
		}
		if (listaTransfer.getQuantidade()==0) { // TODO se quantidade for 0, remover item da lista de compras
			throw new RuntimeException("A quantidade não pode ser zero");
		}
		Compra compra = compraRepository.findById(listaTransfer.getIdCompra()) .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
		ProdutoMarca produtoMarca = produtoMarcaRepository.findById(listaTransfer.getIdProdutoMarca()) .orElseThrow(() -> new RuntimeException("ProdutoMarca não encontrado"));
		ListaCompra listaCompra = new ListaCompra();
		listaCompra.setCompra(compra);
		listaCompra.setProdutoMarca(produtoMarca);
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
		lista.setCompra(listaCompra.getCompra());
		lista.setProdutoMarca(listaCompra.getProdutoMarca());
		lista.setQuantidade(listaCompra.getQuantidade());
		return listaCompraRepository.save(lista);
	}
	public void excluir(Long id) {
		listaCompraRepository.deleteById(id);
	}
}
