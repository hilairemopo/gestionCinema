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
public class Ville implements  Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String  nom;
    private Double longitude,latitude;
    @OneToMany(mappedBy = "ville")
    private Collection<Cinema> cinemas;


}
