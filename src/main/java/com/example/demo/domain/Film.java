package com.example.demo.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String realisateur;
    private String description;
    private Date dateSortie;
    private String photo;
    private int duree;
    @OneToMany(mappedBy = "film")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<ProjectionFilm> projectionFilms;
    @ManyToOne
    private Cathegorie cathegorie;
}
