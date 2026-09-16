package com.economarket.api.scrapers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.economarket.api.dto.ProdutoRequest;
import com.economarket.api.entities.Preco;
import com.economarket.api.entities.Produto;
import com.economarket.api.entities.Promocao;
import com.economarket.api.services.PrecoService;
import com.economarket.api.services.ProdutoService;
import com.economarket.api.services.PromocaoService;

@Component
public class ObterPrecos {
	@Autowired
	PrecoService precoService;	@Autowired
	ProdutoService produtoService;

	public void run() {
/*
// 1x ao dia:
// para cada produto, resetar faixa de preços: precoMin=Float.POSITIVE_INFINITY, precoMax=0
		List<Produto> produtos = produtoService.consultar();
		for (Produto produtoDB : produtos) {
			ProdutoRequest produtoRequest = new ProdutoRequest();
			produtoRequest.setPrecoMin(Float.POSITIVE_INFINITY);
			produtoRequest.setPrecoMax(0.0f);
			produtoRequest.setId(produtoDB.getId());
			produtoRequest.setIdCategoria(produtoDB.getCategoria().getId());
			produtoRequest.setFigura(produtoDB.getFigura());
			produtoService.salvar(produtoRequest).getId();
		}

// limpar tabela 'precos'
		List<Preco> precos = precoService.consultar();
		for (Preco precoDB : precos) {
			precoService.excluir(precoDB.getId());
		}

// limpar tabela 'promocoes'
		List<Promocao> promocoes = proomocaoService.consultar();
		for (Promocao promocaoDB : promocoes) {
			promocaoService.excluir(promocaoDB.getId());
		}
*/
// buscar valores para cada mercado
		System.out.println("'ObterPrecos()' rodando periodicamente");
	}
}
