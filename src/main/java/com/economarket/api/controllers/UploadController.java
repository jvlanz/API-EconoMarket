package com.economarket.api.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
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

import com.economarket.api.scrapers.LerDados;

@RestController
@RequestMapping("/files")
@CrossOrigin(origins = "*")
public class UploadController {
	@Autowired
	LerDados lerDados;
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
		String descricao;
		String medida; // será convertido de String para Medida em lerDados()
		Float valorMedida;
		String marca;
		String categoria;
		String mercado = "Koch";
		Float preco;
		String linha;
		try (BufferedReader reader = new BufferedReader(
			new FileReader(csvFile, StandardCharsets.UTF_8))) {
			while ((linha = reader.readLine()) != null) {
// produto , marca , categoria, medida , valorMedida , preço
				if (linha.split(",").length==6) {
					descricao = linha.split(",")[0].strip();
					marca = linha.split(",")[1].strip();
					categoria = linha.split(",")[2].strip();
					medida = linha.split(",")[3].strip();
					valorMedida = Float.parseFloat(linha.split(",")[4]);
					preco = Float.parseFloat(linha.split(",")[5]);
					lerDados.lerDados(descricao, marca, categoria, medida, valorMedida, preco, mercado);
				} else {
					System.out.println("Erro ao ler valores na linha: " + linha);
				}
			}
		} catch (IOException e) {
			System.err.println("Error ao ler arquivo: " + e.getMessage());
		}
	}
}

