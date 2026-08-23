package com.economarket.api.entities;

import com.economarket.api.entities.Medida;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String descricao;
	private String marca;
	@ManyToOne
	@JoinColumn(name="idMedida")
	private Medida medida;
//	private Long idMedida;      // ex. 'g'
	private Float valorMedida;  // ex. '300' para '300g'

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
}
