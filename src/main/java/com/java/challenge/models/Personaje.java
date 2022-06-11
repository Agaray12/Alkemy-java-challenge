package com.java.challenge.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Double peso;
    @Column(length = 65555)
    private String historia;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Pelicula> peliculas;
}
