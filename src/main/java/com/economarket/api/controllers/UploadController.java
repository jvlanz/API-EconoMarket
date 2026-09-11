package com.economarket.api.controllers;

import java.util.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

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
import com.economarket.api.entities.Marca;
import com.economarket.api.entities.Preco;
import com.economarket.api.entities.Produto;
import com.economarket.api.entities.ProdutoMarca;
import com.economarket.api.services.MarcaService;
import com.economarket.api.services.PrecoService;
import com.economarket.api.services.ProdutoService;
import com.economarket.api.services.ProdutoMarcaService;

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
			File savedFile = new File(dir, file.getOriginalFilename());
			file.transferTo(savedFile);
			parseCSV(savedFile.getAbsolutePath());
			return ResponseEntity.ok("Arquivo enviado com sucesso: " + savedFile.getAbsolutePath());
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro salvando arquivo: " + e.getMessage());
		}
	}
	
	public void parseCSV(String csvFile) {
		Long mercadoId = 0L;
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
					System.out.println(descricao+marca+medida+valorMedida+preco+mercadoId+data); // remover alertas do compilador
// checar se produto existe; senão criar um; obter produtoId
					Produto novoProduto = new Produto();
					novoProduto.setDescricao(descricao);
					novoProduto.setMedida(medida);
					novoProduto.setValorMedida(valorMedida);
					List<Produto> produtos = produtoService.consultar();
					for (Produto produtoDB : produtos) {
						novoProduto.setId(produtoDB.getId());
						break;
					}
					produtoService.salvar(novoProduto);
// checar se marca existe; senão criar uma; obter marcaId
					Marca novaMarca = new Marca();
					novaMarca.setMarca(marca);
					List<Marca> marcas = marcaService.consultar();
					for (Marca marcaDB : marcas) {
						novaMarca.setId(marcaDB.getId());
						break;
					}
					marcaService.salvar(novaMarca);
// checar se produtoMarca existe; senão criar um; obter produtoMarcaId; precisa DTO
// checar se produtoMarca existe em mercado; senão criar um; obter precoId; precisa DTO
					Preco novoPreco = new Preco();
					List<Preco> precos = precoService.consultar();
					Boolean existe = false;
					for (Preco precoDB : precos) {
						continue;
					}
					if (existe) {
						System.out.println("editar produto existente");
					} else {
						System.out.println("cadastrar novo produto"); // deve requerer mais um DTO...
					}
//					precoService.salvar(novoPreco);
				} else {
					System.out.println("Erro ao ler valores na linha: " + line);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
		}
	}
}

