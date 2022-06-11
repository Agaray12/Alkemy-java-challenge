package com.java.challenge.services;

import com.java.challenge.models.Personaje;
import com.java.challenge.repositories.PersonajeRepositorio;
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
    
    public Personaje update(Personaje personaje) {
        return personajeRepo.save(personaje);
    }
    
    public void delteById(Integer id) {
        personajeRepo.deleteById(id);
    }
}