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
	@JoinColumn(name="idCliente")
	@JsonBackReference
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name="idProduto")
	Produto produto;
	private Float quantidade;

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
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Float getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Float quantidade) {
		this.quantidade =quantidade;
	}
}
