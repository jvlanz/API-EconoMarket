package com.economarket.api.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

				lerDados.lerDados(descricao, marca, "", medida, Float.parseFloat(valorMedida), preco, mercado);
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

