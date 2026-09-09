package com.economarket.api.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "O nome precisa ser informado")
	@Size(min = 3, message = "O nome precisa ter pelo menos 3 caracteres")
	private String nome;
	@NotBlank(message = "O e-mail precisa ser informado")
	@Email(message = "O e-mail informado é inválido")
	@Column(unique=true)
	private String email;
	@NotBlank(message = "A senha precisa ser informada")
	@Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres")
	private String senha;
	@Pattern(regexp = "^[+]?[0-9\\s()\\-]{10,20}$",
		message = "O telefone informado é inválido")
	private String telefone;
	@OneToMany(mappedBy="cliente") // espelhamento de ListaCompra
	@JsonManagedReference
	private List<ListaCompra> listaCompra;
	@OneToMany(mappedBy="cliente") // espelhamento de ListaCompra
	@JsonManagedReference
	private List<Favorito> favoritos;
	private String role;
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public List<ListaCompra> getListaCompra() {
		return listaCompra;
	}
	public void setListaCompra(List<ListaCompra> listaCompra) {
		this.listaCompra = listaCompra;
	}
	public List<Favorito> getFavoritos() {
		return favoritos;
	}
	public void setFavoritos(List<Favorito> favoritos) {
		this.favoritos = favoritos;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
