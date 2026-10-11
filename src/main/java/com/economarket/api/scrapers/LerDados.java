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
import com.economarket.api.entities.Mercado;
import com.economarket.api.entities.Preco;
import com.economarket.api.entities.Produto;
import com.economarket.api.entities.ProdutoMarca;
import com.economarket.api.services.CategoriaService;
import com.economarket.api.services.MarcaService;
import com.economarket.api.services.MercadoService;
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
	MercadoService mercadoService;
	@Autowired
	PrecoService precoService;
	@Autowired
	PromocaoService promocaoService;

	List<Produto> produtos;
	List<Marca> marcas;
	List<Categoria> categorias;
	List<ProdutoMarca> produtoMarcas;
	List<Mercado> mercados;
	List<Preco> precos;
	Date data;

/*
	private final ProdutoService produtoService;
	private final MarcaService marcaService;
	private final CategoriaService categoriaService;
	private final ProdutoMarcaService produtoMarcaService;
	private final MercadoService mercadoService;
	private final PrecoService precoService;
	private final PromocaoService promocaoService;
	private final List<Produto> produtos;
	private final List<Marca> marcas;
	private final List<Categoria> categorias;
	private final List<ProdutoMarca> produtoMarcas;
	private final List<Mercado> mercados;
	private final List<Preco> precos;
	public Date data = new Date();

	public LerDados(
		ProdutoService produtoService,
		MarcaService marcaService,
		CategoriaService categoriaService,
		ProdutoMarcaService produtoMarcaService,
		MercadoService mercadoService,
		PrecoService precoService,
		PromocaoService promocaoService
	) {
		this.produtoService = produtoService;
		this.marcaService = marcaService;
		this.categoriaService = categoriaService;
		this.produtoMarcaService = produtoMarcaService;
		this.mercadoService = mercadoService;
		this.precoService = precoService;
		this.promocaoService = promocaoService;
		this.produtos = produtoService.consultar();
		this.marcas = marcaService.consultar();
		this.categorias = categoriaService.consultar();
		this.produtoMarcas = produtoMarcaService.consultar();
		this.mercados = mercadoService.consultar();
		this.precos = precoService.consultar();
	}
*/


	public void lerDados(String descricao, String marca, String categoria, String medida, Float valorMedida, Float preco, String mercado) {
		Long idProduto = 0L;
		Long idCategoria = 1L; // default=""
		Long idMarca = 0L;
		Long idProdutoMarca = 0L;
		Long idMercado = 1L;
		System.out.println("ADICIONAR PRODUTO: "+descricao+", "+marca+", "+categoria+", "+medida+", "+valorMedida+", "+preco+", "+mercado+", "+data);

// checar mercados; senão, default=1; PENSAR NO CASO de inserir novos mercados via frontend (basta colocar outros inputs)
		for (Mercado mercadoDB : mercados) {
			if (mercadoDB.getNome().equals(mercado)) {
				idMercado = mercadoDB.getId();
				break;
			}
		}

// checar se categoria existe; senão manter categoria ""
		Categoria novaCategoria = new Categoria();
		novaCategoria.setCategoria(categoria);
		for (Categoria categoriaDB : categorias) {
			if (categoriaDB.getCategoria().equals(categoria)) {
//				novaCategoria.setId(categoriaDB.getId());
				idCategoria = categoriaDB.getId(); // se encontrar 'categoria', usar seu id; senão, ficará nula
				break;
			}
		}
//		idCategoria = categoriaService.salvar(novaCategoria).getId();
		if (idCategoria==1L) // se categoria não for reconhecida
			System.out.println("Categoria não reconhecida: "+categoria);

// checar se produto existe; senão criar um; obter idProduto; precisa DTO
		ProdutoRequest produtoRequest = new ProdutoRequest();
		produtoRequest.setDescricao(descricao);
		produtoRequest.setIdCategoria(idCategoria);
		produtoRequest.setMedida(Medida.valueOf(medida));
		produtoRequest.setValorMedida(valorMedida);
		produtoRequest.setPrecoMin(preco);
		produtoRequest.setPrecoMax(preco);
		produtoRequest.setFigura(descricao.split(" ")[0].toLowerCase()+".jpg");
		for (Produto produtoDB : produtos) {
			if (produtoDB.getDescricao().toLowerCase().equals(descricao.toLowerCase()) && produtoDB.getValorMedida().equals(produtoRequest.getValorMedida())) {
				idProduto = produtoDB.getId();
				produtoRequest.setId(produtoDB.getId());
				produtoRequest.setIdCategoria(produtoDB.getCategoria().getId());
//				categoria = produtoDB.getCategoria().getCategoria();
				produtoRequest.setFigura(produtoDB.getFigura());
				produtoRequest.setPrecoMin(produtoDB.getPrecoMin());
				produtoRequest.setPrecoMax(produtoDB.getPrecoMax());
				if (preco<produtoDB.getPrecoMin()) produtoRequest.setPrecoMin(preco);
				if (preco>produtoDB.getPrecoMax()) produtoRequest.setPrecoMax(preco);
				break;
			}
		}
		if (idProduto==0L) {
			Produto produtoDB = produtoService.salvar(produtoRequest);
			idProduto = produtoDB.getId();
			produtos.add(produtoDB);
		} else {
			produtoService.alterar(idProduto, produtoRequest); // precisa salvar sempre, para atualizar a faixa de preços
		}

// checar se marca existe; senão criar uma; obter idMarca
		Marca novaMarca = new Marca();
		novaMarca.setMarca(marca);
		for (Marca marcaDB : marcas) {
			if (marcaDB.getMarca().toLowerCase().equals(marca.toLowerCase())) {
				idMarca = marcaDB.getId();
				break;
			}
		}
		if (idMarca==0L) {
			Marca marcaDB = marcaService.salvar(novaMarca);
			marcas.add(marcaDB);
			idMarca = marcaDB.getId();
		}

// checar se produtoMarca existe; senão criar um; obter idProdutoMarca; precisa DTO
		ProdutoMarcaRequest produtoMarcaRequest = new ProdutoMarcaRequest();
		produtoMarcaRequest.setIdProduto(idProduto);
		produtoMarcaRequest.setIdMarca(idMarca);
		for (ProdutoMarca produtoMarcaDB : produtoMarcas) {
			if (produtoMarcaDB.getProduto().getId()==idProduto
					&& produtoMarcaDB.getMarca().getId()==idMarca) {
				idProdutoMarca = produtoMarcaDB.getId();
				break;
			}
		}
// usar marca=categoria para marcar novos produtos (sem marca) que requerem revisão
//		if (marca.equals("") && produtoMarcaRequest.getId()==null) {
//			Marca tempMarca = new Marca();
//			tempMarca.setMarca(marca);
//			for (Marca marcaDB : marcas) { // marcas já foram lidas acima
//				if (marcaDB.getMarca().toLowerCase().equals(marca.toLowerCase())) {
//					tempMarca.setId(marcaDB.getId());
//					break;
//				}
//			}
//			produtoMarcaRequest.setIdMarca(marcaService.salvar(tempMarca).getId());
//		}
// enfim, gravar novo produtoMarca
		if (idProdutoMarca==0L) {
			ProdutoMarca produtoMarcaDB = produtoMarcaService.salvar(produtoMarcaRequest);
			produtoMarcas.add(produtoMarcaDB);
			idProdutoMarca = produtoMarcaDB.getId();
		}

// checar se produtoMarca existe em mercado; senão criar um; obter idPreco; precisa DTO
		PrecoRequest precoRequest = new PrecoRequest();
		precoRequest.setIdProdutoMarca(idProdutoMarca);
		precoRequest.setIdMercado(idMercado);
		precoRequest.setPreco(preco);
		precoRequest.setData(data);
		for (Preco precoDB : precos) {
			if (precoDB.getProdutoMarca().getId()==idProdutoMarca && precoDB.getMercado().getId()==idMercado) {
				if (preco<precoDB.getPreco()*0.85) { // preço ao menos 15% menor = promoção
					PromocaoRequest promocaoRequest = new PromocaoRequest();
					promocaoRequest.setIdPreco(precoDB.getId());
					promocaoService.salvar(promocaoRequest);   // TODO limpar promoções antes de ler valores diários
				}
//				precoRequest.setId(precoDB.getId());
				break;
			}
		}
		precoService.salvar(precoRequest);
	}



	public void lerCSV(String mercado, String csvFile) {
		produtos = produtoService.consultar();
		marcas = marcaService.consultar();
		categorias = categoriaService.consultar();
		produtoMarcas = produtoMarcaService.consultar();
		mercados = mercadoService.consultar();
		precos = precoService.consultar();
		List<String> medidas = new ArrayList<>(Arrays.asList("ml", "l", "kg", "gr", "g", "unidades", "unidade", "un"));
		List<String[]> traducoes = new ArrayList<>();
		data = new Date(System.currentTimeMillis());

		String linha;
		String tradFile = System.getProperty("user.dir") + "/src/main/resources/dicionario_produtos.txt";
		try (BufferedReader reader = new BufferedReader(
				new FileReader(tradFile, StandardCharsets.UTF_8))) {
			while ((linha = reader.readLine()) != null) {
				traducoes.add(linha.replace("\n", "").split("\\|"));
			}
		} catch (IOException e) {
			System.err.println("Erro ao ler " + Path.of(tradFile));
		}

		try (CSVParser parser = CSVParser.parse(Path.of(csvFile), StandardCharsets.UTF_8, CSVFormat.DEFAULT)) {
			boolean primeiraLinha = true;
			for (CSVRecord registro : parser) {
				if (primeiraLinha) {
					primeiraLinha = false;
					continue;
				}
// id, descricao, preco, ..., ..., categoria
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
//					if (!marca.isEmpty() && descricao.contains("\\b"+mar.getMarca().toLowerCase())+"\\b") {
					String novaDescricao = descricao.replaceAll("\\b" + mar.getMarca().toLowerCase() + "\\b", "");
					if (! novaDescricao.equals(descricao)) {
						marca = mar.getMarca().toLowerCase();
						descricao = novaDescricao;
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
			text = text.replace(" E ", " e ") //.replace(" A ", " a ")
				.replace(" Da ", " da ").replace(" De ", " de ").replace(" Do ", " do ")
				.replace(" Com ", " com ").replace(" Sem ", " sem ")
				.replace(" Em ", " em ").replace(" para ", " para ");
		}
		return text;
	}

}

