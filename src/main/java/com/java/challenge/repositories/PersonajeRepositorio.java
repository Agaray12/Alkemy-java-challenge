package com.java.challenge.repositories;

import com.java.challenge.models.Personaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje, Integer>{
        
    @Query("SELECT p FROM Personaje p WHERE p.nombre = :name")
    public List<Personaje> filterByName(@Param("name")String name);
    
    @Query("SELECT p FROM Personaje p WHERE p.edad = :age")
    public List<Personaje> filterByAge(@Param("age") Integer age);
}
