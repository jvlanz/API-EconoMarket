package com.economarket.api.dto;

public class FavoritoRequest {
	private Long id;
	private Long idCliente;
	private Long idProdutoMarca;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Long getIdProdutoMarca() {
		return idProdutoMarca;
	}
	public void setIdProdutoMarca(Long idProdutoMarca) {
		this.idProdutoMarca = idProdutoMarca;
	}
}
