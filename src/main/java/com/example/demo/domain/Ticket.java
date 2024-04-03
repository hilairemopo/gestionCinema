package com.example.demo.domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double prix;
    @Column(unique = false,nullable = true)
    private Integer codePayement;
    private String nom;
    private boolean reserve;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  ProjectionFilm projectionFilm;
    @ManyToOne
    private Place place;
}
