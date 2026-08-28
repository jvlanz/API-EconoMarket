package com.economarket.api.dto;

public class ListaCompraTransfer {
	private Long id;
	private Long idCompra;
	private Long idProdutoMarca;
	private Float quantidade;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(long idCompra) {
		this.idCompra = idCompra;
	}
	public Long getIdProdutoMarca() {
		return idProdutoMarca;
	}
	public void setIdProdutoMarca(Long idProdutoMarca) {
		this.idProdutoMarca = idProdutoMarca;
	}
	public Float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}
}
