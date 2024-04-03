package com.example.demo.service.impl;

import com.example.demo.domain.*;
import com.example.demo.repository.*;
import com.example.demo.service.CinemaService;
import org.springframework.stereotype.Service;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@AllArgsConstructor
@Data

@Service
@Transactional
public class CinemaServiceImpl implements CinemaService {
    private CinemaRepository cinemaRepository;
    private VilleRepository villeRepository;
    private SalleRepository salleRepository;
    private PlaceRepository placeRepository;
    private FilmRepository filmRepository;
    private TicketRepository ticketRepository;
    private SeanceRepository seanceRepository;
    private ProjectionFilmRepository projectionFilmRepository;
    private CathegorieRepository cathegorieRepository;

    @Override
    public void initVille() {
        Stream.of("yaounde","Douala","Garoua","Maroua","Bafoussam").forEach(vill->{
            Ville ville=new Ville();
            ville.setNom(vill);
            villeRepository.save(ville);
        });
    }


    @Override
    public void initCinema() {
        villeRepository.findAll().forEach(ville -> {
            Stream.of("fougerole","soa","pk10","bandjoun","ebenga").forEach(cin->{
                Cinema cinema=new Cinema();
                cinema.setNom(cin);
                cinema.setNbreSalles(3+(int)Math.random()*7);
                cinema.setVille(ville);
                cinemaRepository.save(cinema);
            });
        });

    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for(int i=0;i<cinema.getNbreSalles();i++){
                Salle salle=new Salle();
                salle.setCinema(cinema);
                salle.setNom("salle"+(i+1));
                salle.setNbreplace(15+(int)Math.random()*20);
                salleRepository.save(salle);
            }
        });

    }

    @Override
    public void initPlaces() {salleRepository.findAll().forEach(salle -> {
        for(int i =0 ;i<salle.getNbreplace();i++){
            Place place = new Place();
            place.setSalle(salle);
            place.setNumero(i+1);
            placeRepository.save(place);

        }
    });

    }

    @Override
    public void initseances() {
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
        Stream.of("12:00","15:00","17:00","20:00","22:00").forEach(timr->{
            Seance seance= new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(timr));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Override
    public void initCategories() {
        Stream.of("Histoire","Action","Drame","religieu").forEach(cat->{
            Cathegorie cathegorie=new Cathegorie();
            cathegorie.setName(cat);
            cathegorieRepository.save(cathegorie);
        });

    }

    @Override
    public void initFilms() {
        Integer [] duree =new Integer[] {1,2,3,4,5};
        List<Cathegorie> cathegorieList=cathegorieRepository.findAll();
        Stream.of("la bataille des cheries"," Le Tombeau des lucioles"," De battre mon cœur sest arrete "," Le Cercle des poetes disparus", "Les Autres").forEach(titre->{
            Film film= new Film();
            film.setNom(titre);
            film.setDuree(new Random().nextInt(duree.length));
            film.setCathegorie(cathegorieList.get(new Random().nextInt(cathegorieList.size())));
            film.setPhoto(titre.replaceAll(" ","")+".jpg");
            filmRepository.save(film);
        });

    }


    @Override
    public void initProjection() {
        double [] prices=new double[]{30,40,50,60,70,100};
        filmRepository.findAll().forEach(film -> {
            salleRepository.findAll().forEach(salle -> {
                seanceRepository.findAll().forEach(seance -> {
                    ProjectionFilm projectionFilm=new ProjectionFilm();
                    projectionFilm.setSalle(salle);
                    projectionFilm.setFilm(film);
                    projectionFilm.setPrix(prices[new Random().nextInt(prices.length)]);
                    projectionFilmRepository.save(projectionFilm);
                });
            });
        });

    }

    @Override
    public void initTickets() {
        double [] prices=new double[]{30,40,50,60,70,100};
        projectionFilmRepository.findAll().forEach(p -> {
     p.getSalle().getPlaces().forEach(place -> {
         Ticket  ticket =new Ticket();
         ticket.setPlace(place);
         ticket.setPrix(p.getPrix());
         ticket.setProjectionFilm(p);
         ticket.setReserve(false);
         ticketRepository.save(ticket);

     });
        });

    }
}
