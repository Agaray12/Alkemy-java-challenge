package com.java.challenge.services;

import com.java.challenge.dto.PeliculaDTO;
import com.java.challenge.models.Pelicula;
import com.java.challenge.models.Personaje;
import com.java.challenge.repositories.PeliculaRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    
    public Optional<Pelicula> findById(Integer id) {
        return peliculaRepo.findById(id);
    }
    
    public List<Pelicula> filterByTitle(String name){
        return peliculaRepo.filterByTitle(name);
    }
    
    public List<Pelicula> ascOrder(){
        return peliculaRepo.ascOrder();
    }
    
    public List<Pelicula> descOrder() {
        return peliculaRepo.descOrder();
    }
    
    public List<PeliculaDTO> setToDTO(List<Pelicula> peliculas) {
        List<PeliculaDTO> peliculasDTO = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            PeliculaDTO peliculaDTO = new PeliculaDTO();
            peliculaDTO.setId(pelicula.getId());
            peliculaDTO.setImagen(pelicula.getImagen());
            peliculaDTO.setTitulo(pelicula.getTitulo());
            peliculaDTO.setFechaCreacion(pelicula.getFechaCreacion());
            peliculasDTO.add(peliculaDTO);
        }
        return peliculasDTO;
    }
    
    public Pelicula update(Pelicula pelicula, Pelicula peliculaDB) {
        
        if(pelicula.getTitulo() != null){
            peliculaDB.setTitulo(pelicula.getTitulo());
        }
        if(pelicula.getImagen() != null) {
            peliculaDB.setImagen(pelicula.getImagen());
        }
        if(pelicula.getFechaCreacion() != null) {
            peliculaDB.setFechaCreacion(pelicula.getFechaCreacion());
        }
        if(pelicula.getCalificacion() != null) {
            peliculaDB.setCalificacion(pelicula.getCalificacion());
        }
        
        return peliculaRepo.save(peliculaDB);
    }
    
    public void deleteById(Integer id) {
        peliculaRepo.deleteById(id);
    }
    
    public void addCharacterToMovie(Pelicula pelicula, Personaje personaje) {
        pelicula.getPersonajes().add(personaje);
        peliculaRepo.save(pelicula);
    }
    
    public void deleteCharacterFromMovie(Pelicula pelicula, Personaje personaje) {
        pelicula.getPersonajes().remove(personaje);
        peliculaRepo.save(pelicula);
    }
}
