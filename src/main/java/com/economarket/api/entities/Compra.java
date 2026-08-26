package com.economarket.api.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Compra {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date data;
	@ManyToOne
	@JoinColumn(name="idListaCompra")
	private ListaCompra listaCompra;
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name="idStatus")
	private Status status;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public ListaCompra getListaCompra() {
		return listaCompra;
	}
	public void setListaCompra(ListaCompra listaCompra) {
		this.listaCompra = listaCompra;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
