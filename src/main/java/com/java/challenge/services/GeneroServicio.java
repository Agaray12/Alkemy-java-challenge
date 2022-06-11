package com.java.challenge.services;

import com.java.challenge.models.Genero;
import com.java.challenge.repositories.GeneroRepositorio;
import java.util.List;
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
    
    public Genero update(Genero genero) {
        return generoRepo.save(genero);
    }
    
    public void delteById(Integer id) {
        generoRepo.deleteById(id);
    }
}
