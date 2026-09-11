package com.economarket.api.dto;

import com.economarket.api.Medida;

public class ProdutoRequest {
	private Long id;
	private String descricao;
	private Long idCategoria;
	private Medida medida;
	private Float valorMedida;
	private Float precoMin;
	private Float precoMax;
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
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
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
	public Float getPrecoMin() {
		return precoMin;
	}
	public void setPrecoMin(Float precoMin) {
		this.precoMin = precoMin;
	}
	public Float getPrecoMax() {
		return precoMax;
	}
	public void setPrecoMax(Float precoMax) {
		this.precoMax = precoMax;
	}
	public String getFigura() {
		return figura;
	}
	public void setFigura(String figura) {
		this.figura = figura;
	}
}
