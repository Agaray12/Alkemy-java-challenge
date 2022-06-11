package com.java.challenge.repositories;

import com.java.challenge.models.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepositorio extends JpaRepository<Genero, Integer>{
    
}
