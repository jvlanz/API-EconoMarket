package com.economarket.api.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
import com.economarket.api.entities.Marca;
import com.economarket.api.entities.Preco;
import com.economarket.api.entities.Produto;
import com.economarket.api.entities.ProdutoMarca;
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
	ProdutoMarcaService produtoMarcaService;
	@Autowired
	PrecoService precoService;

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
			File savedFile = new File(dir, file.getOriginalFilename()); // TODO salvar como idMercado+data.csv
			file.transferTo(savedFile);
			parseCSV(savedFile.getAbsolutePath());
			return ResponseEntity.ok("Arquivo enviado com sucesso: " + savedFile.getAbsolutePath());
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro salvando arquivo: " + e.getMessage());
		}
	}
	
	public void parseCSV(String csvFile) {
		Long idMercado = 0L;
		Long idProduto = 0L;
		Long idMarca = 0L;
		Long idProdutoMarca = 0L;
		Date data = new Date();
		String descricao;
		Medida medida;
		Float valorMedida;
		String marca;
		Float preco;
		String line;
		try (BufferedReader reader = new BufferedReader(
			new FileReader(csvFile, StandardCharsets.UTF_8))) {
			while ((line = reader.readLine()) != null) {
// produto , marca , medida , valorMedida , preço
				if (line.split(",").length==5) {
					descricao = line.split(",")[0].strip();
					medida = Medida.valueOf(line.split(",")[2].strip());
					valorMedida = Float.parseFloat(csvFile.split(",")[3]);
					marca = line.split(",")[1].strip();
					preco = Float.parseFloat(line.split(",")[4]);
					System.out.println(descricao+marca+medida+valorMedida+preco+idMercado+data); // remover alertas do compilador
// checar se produto existe; senão criar um; obter produtoId
					Produto novoProduto = new Produto();
					novoProduto.setDescricao(descricao);
					novoProduto.setMedida(medida);
					novoProduto.setValorMedida(valorMedida);
					List<Produto> produtos = produtoService.consultar();
					for (Produto produtoDB : produtos) {
						if (produtoDB.getDescricao()==descricao) {
							novoProduto.setId(produtoDB.getId());
							break;
						}
					}
					idProduto = produtoService.salvar(novoProduto).getId();
// checar se marca existe; senão criar uma; obter marcaId
					Marca novaMarca = new Marca();
					novaMarca.setMarca(marca);
					List<Marca> marcas = marcaService.consultar();
					for (Marca marcaDB : marcas) {
						if (marcaDB.getMarca()==marca) {
							novaMarca.setId(marcaDB.getId());
							break;
						}
					}
					idMarca = marcaService.salvar(novaMarca).getId();
// checar se produtoMarca existe; senão criar um; obter produtoMarcaId; precisa DTO
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
// checar se produtoMarca existe em mercado; senão criar um; obter precoId; precisa DTO
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

