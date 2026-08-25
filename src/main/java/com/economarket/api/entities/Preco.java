package com.economarket.api.entities;

import java.util.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Preco {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="idProdutoMarca")
	private ProdutoMarca produtoMarca;
	@ManyToOne
	@JoinColumn(name="idMercado")
	private Mercado mercado;
	private Float preco;
	private Date data;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProdutoMarca getProdutoMarca() {
		return produtoMarca;
	}
	public void setProdutoMarca(ProdutoMarca produtoMarca) {
		this.produtoMarca = produtoMarca;
	}
	public Mercado getMercado() {
		return mercado;
	}
	public void setMercado(Mercado mercado) {
		this.mercado = mercado;
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
