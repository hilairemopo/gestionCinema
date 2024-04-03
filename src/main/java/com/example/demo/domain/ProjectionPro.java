package com.example.demo.domain;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

@Projection(name = "p1",types =com.example.demo.domain.ProjectionFilm.class )
public interface ProjectionPro {
    public Long getId() ;

    public Date getDateProjection();


    public Double getPrix() ;


    public Salle getSalle();


    public Film getFilm() ;

    public Collection<Ticket> getTickets() ;

    public Seance getSeance() ;


}
