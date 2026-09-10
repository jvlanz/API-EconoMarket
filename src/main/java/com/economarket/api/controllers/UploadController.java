package com.economarket.api.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@CrossOrigin(origins = "*")
public class UploadController {

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
		String mercado = "Koch";
		Date data = new Date();
		String produto;
		String marca;
		String medida;
		Float valorMedida;
		Float preco;
		String line;
		try (BufferedReader reader = new BufferedReader(
			new FileReader(csvFile, StandardCharsets.UTF_8))) {
			while ((line = reader.readLine()) != null) {
// produto , marca , medida , valorMedida , preço
				if (line.split(",").length==5) {
					produto = line.split(",")[0].strip();
					marca = line.split(",")[1].strip();
					medida = line.split(",")[2].strip();
					valorMedida = Float.parseFloat(csvFile.split(",")[3]);
					preco = Float.parseFloat(line.split(",")[4]);
					System.out.println(produto+marca+medida+valorMedida+preco+mercado+data); // remover alertas do compilador
// buscar produto/marca/valorMedida/mercado no banco de dados
// se encontrar alterar preço
// senão, inserir novo preço
				} else {
					System.out.println("Erro ao ler valores na linha: " + line);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
		}
	}
}

