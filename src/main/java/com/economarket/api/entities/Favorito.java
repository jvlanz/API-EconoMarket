package com.economarket.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Favorito {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="idCliente")
	@JsonBackReference
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name="idProdutoMarca")
	private ProdutoMarca produtoMarca;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ProdutoMarca getProdutoMarca() {
		return produtoMarca;
	}
	public void setProdutoMarca(ProdutoMarca produtoMarca) {
		this.produtoMarca = produtoMarca;
	}
}
