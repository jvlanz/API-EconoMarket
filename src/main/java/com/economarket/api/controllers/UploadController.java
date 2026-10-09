package com.economarket.api.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.economarket.api.scrapers.LerDados;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "*")
public class UploadController {
	@Autowired
	LerDados lerDados;
	public Date data = new Date();

	@PostMapping("/{mercado}")
	public ResponseEntity<String> uploadFile(@PathVariable String mercado, @RequestParam("file") MultipartFile file) {
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
			File savedFile = new File(dir, dateString+ " " + mercado + ".csv"); //file.getOriginalFilename()); // TODO salvar como idMercado+data.csv
			file.transferTo(savedFile);
			lerDados.lerCSV(mercado, savedFile.getAbsolutePath());
			return ResponseEntity.ok("Arquivo enviado com sucesso: " + savedFile.getAbsolutePath());
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro salvando arquivo: " + e.getMessage());
		}
	}
}

