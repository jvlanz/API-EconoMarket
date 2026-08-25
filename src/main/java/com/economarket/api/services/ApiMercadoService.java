package com.economarket.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.economarket.api.entities.ApiMercado;
import com.economarket.api.repositories.ApiMercadoRepository;

@Service
public class ApiMercadoService {
	@Autowired
	private ApiMercadoRepository repository;
// id
// mercado
// apiLink

	public ApiMercado salvar(ApiMercado apiMercado) {
		if (apiMercado.getApiLink().isEmpty()) {
			throw new RuntimeException("O link da API deve ser informado");
		}
		repository.save(apiMercado);
		return apiMercado;
	}

	public List<ApiMercado> consultar() {
		return repository.findAll();
	}
	public ApiMercado consultarUm(Long id) {
		Optional<ApiMercado> opt = repository.findById(id);
		ApiMercado api = opt.orElseThrow(() -> new RuntimeException("link da API não encontrado"));
		return api;
	}
	public ApiMercado alterar(Long id, ApiMercado apiMercado) {
		ApiMercado api = consultarUm(id);
		api.setMercado(apiMercado.getMercado());
		api.setApiLink(apiMercado.getApiLink());
		return repository.save(api);
	}
	public void excluir(Long id) {
		repository.deleteById(id);
	}
}

