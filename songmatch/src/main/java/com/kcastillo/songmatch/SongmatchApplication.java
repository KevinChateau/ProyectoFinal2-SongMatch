package com.kcastillo.songmatch;

import com.kcastillo.songmatch.principal.Principal;
import com.kcastillo.songmatch.repository.CancionRepository;
import com.kcastillo.songmatch.repository.CantanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SongmatchApplication implements CommandLineRunner {

	@Autowired
	private CantanteRepository repository;
	@Autowired
	private CancionRepository repositoryCantante;

	public static void main(String[] args) {
		SpringApplication.run(SongmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository, repositoryCantante);
		principal.showMenu();
	}
}
