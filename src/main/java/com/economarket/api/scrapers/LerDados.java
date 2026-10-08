package com.economarket.api.scrapers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import java.nio.file.Files;
import java.nio.file.Path;

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

// checar se categoria existe; senão atribuir categoria ""
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
		produtoRequest.setPrecoMin(preco);
		produtoRequest.setPrecoMax(preco);
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
				if (preco<precoDB.getPreco()*0.85) { // preço ao menos 15% menor = promoção
					PromocaoRequest promocaoRequest = new PromocaoRequest();
					promocaoRequest.setIdPreco(precoDB.getId());
					promocaoService.salvar(promocaoRequest);   // TODO limpar promoções antes de ler valores diários
				}
				precoRequest.setId(precoDB.getId());
				break;
			}
		}
		precoService.salvar(precoRequest);
	}

	public void lerCSV(String csvFile) {
		List<String> medidas = new ArrayList<>(Arrays.asList("ml", "l", "kg", "g", "un", "unidades"));
		List<String> marcas = new ArrayList<>();
		try {
			String marcasFile = System.getProperty("user.dir") + "/src/main/resources/marcas.txt";
			if (Files.exists(Path.of(marcasFile))) {
				String linha = Files.readString(Path.of(marcasFile));
				for (String marca : linha.toLowerCase().split(", ")) {
					if (!marcas.contains(marca)) {
						marcas.add(marca);
					}
				}
			}
			System.out.println(marcas);
		} catch (IOException e) {
			System.err.println("Error ao ler " + Path.of("marcas.txt"));
		}

		String mercado = "Koch";
		String linha;
		try (BufferedReader reader = new BufferedReader(
				new FileReader(csvFile, StandardCharsets.UTF_8))) {
			boolean primeiraLinha = true;
			while ((linha = reader.readLine()) != null) {
				if (primeiraLinha) {
					primeiraLinha = false;
					continue;
				}
// remover ',' entre aspas duplas, e então remover aspas, mantendo ',' como delimitador de campos; permite ler arquivos/campos com ou sem aspas duplas
				linha = linha.replaceAll("\"([^\"]+),([^\"]+)\"","$1^virgula^$2").replaceAll("\"","");
				String[] parts = linha.split("\",\"");
// id, descricao, preco, ...
				if (parts.length < 3) continue;
				float preco = Float.parseFloat(parts[2].replace("\"", ""));
				String descricao = parts[1].replace("\"", "").toLowerCase();
				String medida = "";
				String valorMedida = "";
				String produto = "";
				String marca = "";

				for (String m : marcas) {
					if (descricao.contains(" " + m)) {
						marca = m;
						descricao = descricao.replaceAll(" " + marca + "\\b", "");
						break;
					}
				}

				if (marca.equals("unidades"))
					marca = "un";
				marca = marca.replace('´', '\'').replace('`', '\'');
				descricao = descricao.replace(" leve mais pague menos", "");

				if (descricao.contains("leve") && descricao.contains("pague"))
					descricao = descricao.replaceAll(" leve (.*) pague.*", "$1");

				for (String m : medidas) {
					if (Pattern.matches(".*?[0-9,]+" + m + "\\b.*", descricao)) {
						produto = descricao.replaceAll("(.*?) [0-9,]+" + m + "\\b.*", "$1");
						valorMedida = descricao.replaceAll(".*? ([0-9,]+)" + m + "\\b.*", "$1");
						medida = m;
						break;
					} else if (Pattern.matches(".*?[0-9,]+ " + m + "\\b.*", descricao)) {
						produto = descricao.replaceAll("(.*?) [0-9,]+ " + m + "\\b.*", "$1");
						valorMedida = descricao.replaceAll(".*? ([0-9,]+) " + m + "\\b.*", "$1");
						medida = m;
						break;
					} else if (Pattern.matches(".*? " + m + "\\b.*", descricao)) {
						produto = descricao.replaceAll("(.*?) " + m + "\\b.*", "$1");
						valorMedida = "1";
						medida = m;
						break;
					}
				}

				if (produto.isEmpty()) {
					if (Pattern.matches(".* [0-9]+ .*", descricao)) {
						produto = descricao.replaceAll("(.*) [0-9]+ .*", "$1");
						valorMedida = descricao.replaceAll(".* ([0-9]+) .*", "$1");
						medida = descricao.replaceAll(".* [0-9]+ ([^ ]*).*", "$1");
						medidas.add(medida);
					}
				}

				produto = produto.replace(" pacote", "").replace(" frasco", "").replace(" squeeze", "")
								.replace(" cada", "").replace(" caixa", "").replace(" bandeja", "");

				marca = titleCase(marca);
				produto = titleCase(produto);
				produto = produto.replace(" E ", " e ").replace(" De ", " de ");
				medida = medida.replace('l', 'L');
				valorMedida = valorMedida.replace(',', '.');

				lerDados(descricao, marca, "", medida, Float.parseFloat(valorMedida), preco, mercado);
			}
		} catch (IOException e) {
			System.err.println("Error ao ler arquivo: " + e.getMessage());
		}
	}

	public String titleCase(String text) {
		if (!text.isEmpty()) {
			String[] words = text.split(" ");
			StringBuilder sb = new StringBuilder();
			for (String w : words) {
				if (!w.isEmpty()) {
					sb.append(Character.toUpperCase(w.charAt(0))).append(w.substring(1)).append(" ");
				}
			}
			text = sb.toString().trim();
		}
		return text;
	}

}

