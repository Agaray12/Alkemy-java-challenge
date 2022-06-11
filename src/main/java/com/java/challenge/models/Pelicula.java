package com.java.challenge.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imagen;
    private String titulo;
    private Date fechaCreacion;
    private Integer calificacion;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Personaje> personajes;
}
