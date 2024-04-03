package com.example.demo;

import com.example.demo.domain.Film;
import com.example.demo.service.CinemaService;
import com.example.demo.service.impl.CinemaServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.*;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.stereotype.Repository;

@AllArgsConstructor

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {
	private CinemaService cinemaService;
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Film.class);
		cinemaService.initVille();
		cinemaService.initCinema();
		cinemaService.initSalles();
		cinemaService.initPlaces();
		cinemaService.initseances();
		cinemaService.initCategories();
		cinemaService.initFilms();
		cinemaService.initProjection();
		cinemaService.initTickets();


	}
}
