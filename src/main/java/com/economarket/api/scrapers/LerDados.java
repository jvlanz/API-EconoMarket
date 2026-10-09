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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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
		Long idCategoria = 1L; // id=1 para categoria=""
		Long idMarca = 1L;
		Long idProdutoMarca = 1L;
		Long idMercado = 1L;
		System.out.println("ADICIONAR PRODUTO: "+descricao+", "+marca+", "+categoria+", "+medida+", "+valorMedida+", "+preco+", "+idMercado+", "+data);

// checar se categoria existe; senão manter categoria ""
		Categoria novaCategoria = new Categoria();
		novaCategoria.setCategoria(categoria);
		List<Categoria> categorias = categoriaService.consultar();
		for (Categoria categoriaDB : categorias) {
			if (categoriaDB.getCategoria().equals(categoria)) {
//				novaCategoria.setId(categoriaDB.getId());
				idCategoria = categoriaDB.getId(); // se encontrar 'categoria', usar seu id; senão, ficará nula
				break;
			}
		}
//		idCategoria = categoriaService.salvar(novaCategoria).getId();
		if (idCategoria==1L) // se categoria não for reconhecida
			categoria = "";

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
			if (produtoDB.getDescricao().toLowerCase().equals(descricao.toLowerCase())) {
				produtoRequest.setId(produtoDB.getId());
				produtoRequest.setIdCategoria(produtoDB.getCategoria().getId());
				categoria = produtoDB.getCategoria().getCategoria();
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
			if (marcaDB.getMarca().toLowerCase().equals(marca.toLowerCase())) {
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
// usar marca=categoria para marcar novos produtos (sem marca) que requerem revisão
	//	if (marca.equals("") && produtoMarcaRequest.getId()==null) {
	//		Marca tempMarca = new Marca();
	//		tempMarca.setMarca(marca);
	//		for (Marca marcaDB : marcas) { // marcas já foram lidas acima
	//			if (marcaDB.getMarca().toLowerCase().equals(marca.toLowerCase())) {
	//				tempMarca.setId(marcaDB.getId());
	//				break;
	//			}
	//		}
	//		produtoMarcaRequest.setIdMarca(marcaService.salvar(tempMarca).getId());
	//	}
// enfim, gravar novo produtoMarca
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



	public void lerCSV(String mercado, String csvFile) {
		List<String> medidas = new ArrayList<>(Arrays.asList("ml", "l", "kg", "gr", "g", "unidades", "unidade", "un"));
		List<String[]> traducoes = new ArrayList<>();
		List<Marca> marcas = marcaService.consultar();

		String linha;
		String tradFile = System.getProperty("user.dir") + "/src/main/resources/traducoes.txt";
		try (BufferedReader reader = new BufferedReader(
				new FileReader(tradFile, StandardCharsets.UTF_8))) {
			while ((linha = reader.readLine()) != null) {
				traducoes.add(linha.replace("\n", "").split("\\|"));
			}
		} catch (IOException e) {
			System.err.println("Erro ao ler " + Path.of("traducoes.txt"));
		}

		try (CSVParser parser = CSVParser.parse(Path.of(csvFile), StandardCharsets.UTF_8, CSVFormat.DEFAULT)) {
			boolean primeiraLinha = true;
			for (CSVRecord registro : parser) {
				if (primeiraLinha) {
					primeiraLinha = false;
					continue;
				}
// id, descricao, preco, ...
				if (registro.size() < 3) continue;
				float preco = Float.parseFloat(registro.get(2).replace("\"", ""));
				String descricao = registro.get(1).replace("\"", "").replaceAll("  ", " ").toLowerCase()+" ";
				String medida = "";
				String valorMedida = "";
				String produto = "";
				String marca = "";
				String categoria = "";
				if (registro.size() > 5)
					categoria = registro.get(5).split(" >")[0].replace("\"", "");
				if (categoria.equals("Eletro")) continue;
				if (categoria.equals("Bebidas Alcoólicas")) categoria = "Bebidas";
				if (categoria.equals("Casa & Utilidades")) categoria = "Utilidades";
				if (categoria.equals("Padaria & Confeitaria")) categoria = "Padaria";
				if (categoria.equals("Pet Care")) categoria = "Pets";
				categoria = categoria.replace("&", "e");

				if (descricao.contains("leve") && descricao.contains("pague"))
					descricao = descricao.replaceAll(" leve (.*) pague [^ ]*", "$1");
				for (String[] trad : traducoes) {
					if (trad.length>1)
						descricao = descricao.replaceAll(trad[0], trad[1]);
					else
						descricao = descricao.replaceAll(trad[0], "");
				}
				descricao = descricao.replaceAll("  ", " ");

				for (Marca mar : marcas) {
					if (descricao.contains(mar.getMarca().toLowerCase())) {
						marca = mar.getMarca().toLowerCase();
						descricao = descricao.replaceAll("\\b" + marca + "\\b", "");
						break;
					}
				}

				for (String m : medidas) {
					if (Pattern.matches(".*? [0-9,\\.]+" + m + "\\b.*", descricao)) {
						produto = descricao.replaceAll("(.*?) [0-9,\\.]+" + m + "\\b.*", "$1");
						valorMedida = descricao.replaceAll(".*? ([0-9,\\.]+)" + m + "\\b.*", "$1");
						medida = m;
						break;
					} else if (Pattern.matches(".*? [0-9,\\.]+ " + m + "\\b.*", descricao)) {
						produto = descricao.replaceAll("(.*?) [0-9,\\.]+ " + m + "\\b.*", "$1");
						valorMedida = descricao.replaceAll(".*? ([0-9,\\.]+) " + m + "\\b.*", "$1");
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
						if (!medidas.contains(medida)) // unidade estranha, ou vazia
							medida = "un";
					} else {
						produto = descricao;
						valorMedida = "1";
						medida = "un";
					}
				}

				marca = titleCase(marca);
				produto = titleCase(produto);
				medida = medida.replace('l', 'L').replace("gr", "g").replace("unidades", "un").replace("unidade", "un");
				valorMedida = valorMedida.replace(',', '.');

				lerDados(produto, marca, categoria, medida, Float.parseFloat(valorMedida), preco, mercado);
			}
		} catch (IOException e) {
			System.err.println("Erro ao ler arquivo: " + e.getMessage());
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
			text = text.replace(" E ", " e ").replace(" Da ", " da ").replace(" De ", " de ").replace(" Do ", " do ").replace(" Em ", " em ").replace(" Com ", " com ").replace(" Sem ", " sem ").replace(" para ", " para ");
		}
		return text;
	}

}

