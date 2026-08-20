package com.economarket.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String descricao;
	private String marca;
	private Long fkUnidade;
	private Long fkMercado;

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
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Long getFkUnidade() {
		return fkUnidade;
	}
	public void setFkUnidade(Long fkUnidade) {
		this.fkUnidade = fkUnidade;
	}
	public Long getFkMercado() {
		return fkMercado;
	}
	public void setFk_mercado(Long fkMercado) {
		this.fkMercado = fkMercado;
	}
}
