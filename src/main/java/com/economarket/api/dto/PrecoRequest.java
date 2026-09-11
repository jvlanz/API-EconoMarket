package com.economarket.api.dto;

import java.util.Date;

public class PrecoRequest {
	private Long id;
	private Long idProdutoMarca;
	private Long idMercado;
	private Float preco;
	private Date data;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdProdutoMarca() {
		return idProdutoMarca;
	}
	public void setIdProdutoMarca(Long idProdutoMarca) {
		this.idProdutoMarca = idProdutoMarca;
	}
	public Long getIdMercado() {
		return idMercado;
	}
	public void setIdMercado(Long idMercado) {
		this.idMercado = idMercado;
	}
	public Float getPreco() {
		return preco;
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
