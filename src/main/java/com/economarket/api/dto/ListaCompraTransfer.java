package com.economarket.api.dto;

public class ListaCompraTransfer {
	private Long id;
	private Long idCliente;
	private Long idProduto;
	private Float quantidade;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public Float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}
}
