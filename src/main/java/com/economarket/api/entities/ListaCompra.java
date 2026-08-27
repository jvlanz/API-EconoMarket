package com.economarket.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ListaCompra {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="idCompra")
	@JsonBackReference
	private Compra compra;
	@ManyToOne
	@JoinColumn(name="idProdutoMarca")
	ProdutoMarca produtoMarca;
	private Float quantidade;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Compra getCompra() {
		return compra;
	}
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	public ProdutoMarca getProdutoMarca() {
		return produtoMarca;
	}
	public void setProdutoMarca(ProdutoMarca produtoMarca) {
		this.produtoMarca = produtoMarca;
	}
	public Float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Float quantidade) {
		this.quantidade =quantidade;
	}
}
