package com.economarket.api.entities;

import com.economarket.api.Medida;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	@ManyToOne
	@JoinColumn(name="idCategoria")
	private Categoria categoria;
	@Enumerated(EnumType.STRING)
	private Medida medida;
	private Float valorMedida;  // ex. '300' para '300g'
	private String figura;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Medida getMedida() {
		return medida;
	}
	public void setMedida(Medida medida) {
		this.medida = medida;
	}
	public Float getValorMedida() {
		return valorMedida;
	}
	public void setValorMedida(Float valorMedida) {
		this.valorMedida = valorMedida;
	}
	public String getFigura() {
		return figura;
	}
	public void setFigura(String figura) {
		this.figura = figura;
	}
}
