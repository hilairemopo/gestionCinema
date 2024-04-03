package com.example.demo.web;

import com.example.demo.domain.Film;
import com.example.demo.domain.Ticket;
import com.example.demo.repository.FilmRepository;
import com.example.demo.repository.TicketRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import lombok.*;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("http://localhost:4200")
public class CinemaController {
    private FilmRepository filmRepository;
    private TicketRepository ticketRepository;


    @GetMapping(path = "/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id")Long id) throws Exception{
       Film f = filmRepository.findById(id).get() ;
       String photo=f.getPhoto();
        File fichier = new File(System.getProperty("user.home")+"/Cinema/images/"+photo);
        Path path= Paths.get(fichier.toURI());
        return Files.readAllBytes(path);

    }
    @PostMapping("/payerTicket")
    public  List<Ticket> payerTicket(@RequestBody Tickeform tickeform){
        List<Ticket> tickets=new ArrayList<>();
        tickeform.getTickets().forEach(idticket->{
        Ticket ticket=ticketRepository.findById(idticket.getId()).get();
        ticket.setNom(tickeform.getNomClient());
        ticketRepository.save(ticket);
        tickets.add(ticket);
        });

        return tickets;
    }
    @Data
     public  static class Tickeform{
        private Long id;
        private String nomClient;
        private List<Ticket> tickets;
        public Tickeform(String nomClient,List<Ticket> tickets){
            this.nomClient=nomClient;
            this.tickets=tickets;
        }
    }

}
