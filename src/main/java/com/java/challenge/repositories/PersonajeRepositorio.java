package com.java.challenge.repositories;

import com.java.challenge.models.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, Integer>{
    
}
