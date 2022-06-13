package com.java.challenge.services;

import com.java.challenge.dto.GeneroDTO;
import com.java.challenge.models.Genero;
import com.java.challenge.models.Pelicula;
import com.java.challenge.repositories.GeneroRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServicio {
    
    @Autowired
    private GeneroRepositorio generoRepo;
    
    public Genero save(Genero genero) {
        return generoRepo.save(genero);
    }
    
    public List<Genero> findAll(){
        return generoRepo.findAll();
    }
    
    public Optional<Genero> findById(Integer id) {
        return generoRepo.findById(id);
    }
    
    public Genero update(Genero genero) {
        return generoRepo.save(genero);
    }
    
    public List<GeneroDTO> setToDTO(List<Genero> generos){
        List<GeneroDTO> generosDTO = new ArrayList<>();
        for (Genero genero : generos) {
            GeneroDTO generoDTO = new GeneroDTO();
            generoDTO.setId(genero.getId());
            generoDTO.setImagen(genero.getImagen());
            generoDTO.setNombre(genero.getNombre());
            generosDTO.add(generoDTO);
        }
        return generosDTO;
    }
    
    public void deleteById(Integer id) {
        generoRepo.deleteById(id);
    }
    
    public void addMovieToGenre(Genero genero, Pelicula pelicula) {
        genero.getPeliculas().add(pelicula);
        generoRepo.save(genero);
    }
    
    public void deleteMovieFromGenre(Genero genero, Pelicula pelicula){
        genero.getPeliculas().remove(pelicula);
        generoRepo.save(genero);
    }
}
