package com.example.demo.domain;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Double longitude;
    private Double latitude;
    private int nbreSalles;
    @OneToMany(mappedBy ="cinema" )
    private Collection<Salle> salles;
    @ManyToOne
    private Ville ville;

}
