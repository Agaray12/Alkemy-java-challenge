package com.java.challenge.services;

import com.java.challenge.models.Pelicula;
import com.java.challenge.repositories.PeliculaRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaServicio {
    
    @Autowired
    private PeliculaRepositorio peliculaRepo;
    
    public Pelicula save(Pelicula pelicula) {
        return peliculaRepo.save(pelicula);
    }
    
    public List<Pelicula> findAll(){
        return peliculaRepo.findAll();
    }
    
    public Pelicula update(Pelicula pelicula) {
        return peliculaRepo.save(pelicula);
    }
    
    public void delteById(Integer id) {
        peliculaRepo.deleteById(id);
    }
}
