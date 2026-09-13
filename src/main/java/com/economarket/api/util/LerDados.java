package com.economarket.api.util;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.economarket.api.Medida;
import com.economarket.api.dto.PrecoRequest;
import com.economarket.api.dto.ProdutoMarcaRequest;
import com.economarket.api.dto.ProdutoRequest;
import com.economarket.api.dto.PromocaoRequest;
import com.economarket.api.entities.Categoria;
import com.economarket.api.entities.Marca;
import com.economarket.api.entities.Preco;
import com.economarket.api.entities.Produto;
import com.economarket.api.entities.ProdutoMarca;
import com.economarket.api.services.CategoriaService;
import com.economarket.api.services.MarcaService;
import com.economarket.api.services.PrecoService;
import com.economarket.api.services.ProdutoMarcaService;
import com.economarket.api.services.ProdutoService;
import com.economarket.api.services.PromocaoService;

@Component
public class LerDados {
	@Autowired
	ProdutoService produtoService;
	@Autowired
	MarcaService marcaService;
	@Autowired
	CategoriaService categoriaService;
	@Autowired
	ProdutoMarcaService produtoMarcaService;
	@Autowired
	PrecoService precoService;
	@Autowired
	PromocaoService promocaoService;
	public Date data = new Date();

	public void lerDados(String descricao, String marca, String categoria, String medida, Float valorMedida, Float preco, String mercado) {
		Long idProduto = 1L;
		Long idCategoria = 1L;
		Long idMarca = 1L;
		Long idProdutoMarca = 1L;
		Long idMercado = 1L;
		System.out.println("ADICIONAR PRODUTO: "+descricao+", "+marca+", "+categoria+", "+medida+", "+valorMedida+", "+preco+", "+idMercado+", "+data);

// checar se categoria existe; senão criar uma; obter idCategoria
		Categoria novaCategoria = new Categoria();
		novaCategoria.setCategoria(categoria);
		List<Categoria> categorias = categoriaService.consultar();
		for (Categoria categoriaDB : categorias) {
			if (categoriaDB.getCategoria().equals(categoria)) {
				novaCategoria.setId(categoriaDB.getId());
				break;
			}
		}
		idCategoria = categoriaService.salvar(novaCategoria).getId();

// checar se produto existe; senão criar um; obter idProduto; precisa DTO
		ProdutoRequest produtoRequest = new ProdutoRequest();
		produtoRequest.setDescricao(descricao);
		produtoRequest.setIdCategoria(idCategoria);
		produtoRequest.setMedida(Medida.valueOf(medida));
		produtoRequest.setValorMedida(valorMedida);
		produtoRequest.setPrecoMin(preco); // FIXME precisa resetar a faixa de valores a cada dia, antes de ler dados
		produtoRequest.setPrecoMax(preco); // FIXME precisa resetar a faixa de valores a cada dia, antes de ler dados
		produtoRequest.setFigura(descricao.split(" ")[0].toLowerCase()+".jpg");
		List<Produto> produtos = produtoService.consultar();
		for (Produto produtoDB : produtos) {
			if (produtoDB.getDescricao().equals(descricao)) {
				produtoRequest.setId(produtoDB.getId());
				produtoRequest.setIdCategoria(idCategoria); // para quando a categoria não for informada
				produtoRequest.setFigura(produtoDB.getFigura());
				produtoRequest.setPrecoMin(produtoDB.getPrecoMin());
				produtoRequest.setPrecoMax(produtoDB.getPrecoMax());
				if (preco<produtoDB.getPrecoMin()) produtoRequest.setPrecoMin(preco);
				if (preco>produtoDB.getPrecoMax()) produtoRequest.setPrecoMax(preco);
				break;
			}
		}
		idProduto = produtoService.salvar(produtoRequest).getId();

// checar se marca existe; senão criar uma; obter idMarca
		Marca novaMarca = new Marca();
		novaMarca.setMarca(marca);
		List<Marca> marcas = marcaService.consultar();
		for (Marca marcaDB : marcas) {
			if (marcaDB.getMarca().equals(marca)) {
				novaMarca.setId(marcaDB.getId());
				break;
			}
		}
		idMarca = marcaService.salvar(novaMarca).getId();

// checar se produtoMarca existe; senão criar um; obter idProdutoMarca; precisa DTO
		ProdutoMarcaRequest produtoMarcaRequest = new ProdutoMarcaRequest();
		produtoMarcaRequest.setIdProduto(idProduto);
		produtoMarcaRequest.setIdMarca(idMarca);
		List<ProdutoMarca> produtoMarcas = produtoMarcaService.consultar();
		for (ProdutoMarca produtoMarcaDB : produtoMarcas) {
			if (produtoMarcaDB.getProduto().getId()==idProduto
					&& produtoMarcaDB.getMarca().getId()==idMarca) {
				produtoMarcaRequest.setId(produtoMarcaDB.getId());
				break;
			}
		}
		idProdutoMarca = produtoMarcaService.salvar(produtoMarcaRequest).getId();

// checar se produtoMarca existe em mercado; senão criar um; obter idPreco; precisa DTO
		PrecoRequest precoRequest = new PrecoRequest();
		precoRequest.setIdProdutoMarca(idProdutoMarca);
		precoRequest.setIdMercado(idMercado);
		precoRequest.setPreco(preco);
		precoRequest.setData(data);
		List<Preco> precos = precoService.consultar();
		for (Preco precoDB : precos) {
			if (precoDB.getProdutoMarca().getId()==idProdutoMarca && precoDB.getMercado().getId()==idMercado) {
				if (preco<precoDB.getPreco()*0.95) { // preço ao menos 5% menor = promoção
					PromocaoRequest promocaoRequest = new PromocaoRequest();
					promocaoRequest.setIdPreco(precoDB.getId());
					promocaoService.salvar(promocaoRequest);   // TODO limpar promoções antes de ler valores diários
				}
				precoRequest.setId(precoDB.getId());
// se preco<precoDB.getPreco() (-5%?), cadastrar promocao
				break;
			}
		}
		precoService.salvar(precoRequest);
	}
}

