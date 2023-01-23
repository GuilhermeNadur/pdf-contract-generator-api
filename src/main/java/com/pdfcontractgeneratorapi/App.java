package com.pdfcontractgeneratorapi;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.pdfcontractgeneratorapi.util.ConstantsUtils.STORAGE_DIR;
import static com.pdfcontractgeneratorapi.util.ConstantsUtils.TEMP_DIR;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@PostConstruct
	public void setup() throws IOException {
		Files.createDirectories(Paths.get(TEMP_DIR));
		Files.createDirectories(Paths.get(STORAGE_DIR));
	}
}
