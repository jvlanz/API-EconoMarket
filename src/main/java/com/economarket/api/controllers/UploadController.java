package com.economarket.api.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.economarket.api.Medida;
import com.economarket.api.dto.PrecoRequest;
import com.economarket.api.dto.ProdutoMarcaRequest;
import com.economarket.api.dto.ProdutoRequest;
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

@RestController
@RequestMapping("/files")
@CrossOrigin(origins = "*")
public class UploadController {
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
	public Date data = new Date();

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
// Validar arquivo
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("Nenhum arquivo informado");
		}

		try {
// Salvar arquivo em um diretório local
			String uploadDir = System.getProperty("user.dir") + "/uploads";
			File dir = new File(uploadDir);
			if (!dir.exists()) dir.mkdirs();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(data);
			File savedFile = new File(dir, dateString+ " Koch.csv"); //file.getOriginalFilename()); // TODO salvar como idMercado+data.csv
			file.transferTo(savedFile);
			parseCSV(savedFile.getAbsolutePath());
			return ResponseEntity.ok("Arquivo enviado com sucesso: " + savedFile.getAbsolutePath());
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro salvando arquivo: " + e.getMessage());
		}
	}
	
	public void parseCSV(String csvFile) {
		Long idMercado = 1L;
		Long idProduto = 1L;
		Long idCategoria = 1L;
		Long idMarca = 1L;
		Long idProdutoMarca = 1L;
		String descricao;
		Medida medida;
		Float valorMedida;
		String marca;
		String categoria;
		Float preco;
		String line;
		try (BufferedReader reader = new BufferedReader(
			new FileReader(csvFile, StandardCharsets.UTF_8))) {
			while ((line = reader.readLine()) != null) {
// produto , marca , categoria, medida , valorMedida , preço
				if (line.split(",").length==6) {
					descricao = line.split(",")[0].strip();
					marca = line.split(",")[1].strip();
					categoria = line.split(",")[2].strip();
					medida = Medida.valueOf(line.split(",")[3].strip());
					valorMedida = Float.parseFloat(line.split(",")[4]);
					preco = Float.parseFloat(line.split(",")[5]);
					System.out.println("ADICIONAR PRODUTO: "+descricao+" "+marca+" "+medida+" "+valorMedida+" "+preco+" "+idMercado+" "+data); // remover alertas do compilador
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
					produtoRequest.setMedida(medida);
					produtoRequest.setValorMedida(valorMedida);
					produtoRequest.setPrecoMin(preco); // FIXME
					produtoRequest.setPrecoMax(preco); // FIXME
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
						if(precoDB.getProdutoMarca().getId()==idProdutoMarca
								&& precoDB.getMercado().getId()==idMercado) {
							precoRequest.setId(precoDB.getId());
							break;
						}
					}
					precoService.salvar(precoRequest);
				} else {
					System.out.println("Erro ao ler valores na linha: " + line);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
		}
	}
}

