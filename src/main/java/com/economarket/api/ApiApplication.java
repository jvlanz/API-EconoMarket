package com.economarket.api;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.economarket.api.scrapers.ObterPrecos;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);

		ObterPrecos precosDia = new ObterPrecos();
		ScheduledExecutorService scheduler =
				Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(
				precosDia::run,
				0,      // delay inicial
				3600,   // intervalo de repetição (TODO: ajustar para ser todo dia, às 6AM)
				TimeUnit.SECONDS
		);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			scheduler.shutdown();
		}));
	}
}

