package com.java.challenge.services;

import com.java.challenge.dto.PersonajeDTO;
import com.java.challenge.models.Pelicula;
import com.java.challenge.models.Personaje;
import com.java.challenge.repositories.PersonajeRepositorio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeServicio {
    
    @Autowired
    private PersonajeRepositorio personajeRepo;
    
    public Personaje save(Personaje personaje) {
        return personajeRepo.save(personaje);
    }
    
    public List<Personaje> findAll(){
        return personajeRepo.findAll();
    }
    
    public List<Personaje> filterByName(String name) {
        return personajeRepo.filterByName(name);
    }
    
    public List<Personaje> filterByAge(Integer age) {
        return personajeRepo.filterByAge(age);
    }
    
    public List<PersonajeDTO> setToDTO(List<Personaje> personajes) {
        List<PersonajeDTO> personajesDTO = new ArrayList<>();
        for (Personaje personaje : personajes) {
            PersonajeDTO personajeDTO = new PersonajeDTO();
            personajeDTO.setId(personaje.getId());
            personajeDTO.setImagen(personaje.getImagen());
            personajeDTO.setNombre(personaje.getNombre());
            personajesDTO.add(personajeDTO);
        }
        return personajesDTO;
    }
    
    public Personaje update(Personaje personaje) {
        return personajeRepo.save(personaje);
    }
    
    public void delteById(Integer id) {
        personajeRepo.deleteById(id);
    }
    
    public void addMovieToCharacter(Personaje personaje, Pelicula pelicula) {
        personaje.getPeliculas().add(pelicula);
    }
}