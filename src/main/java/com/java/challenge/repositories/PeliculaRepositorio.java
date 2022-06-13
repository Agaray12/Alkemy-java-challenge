package com.java.challenge.repositories;

import com.java.challenge.models.Pelicula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula, Integer>{
    
    @Query("SELECT p FROM Pelicula p WHERE p.titulo = :name")
    public List<Pelicula> filterByTitle(@Param("name")String name);
    
    @Query("SELECT p FROM Pelicula p ORDER BY p.fechaCreacion ASC")
    public List<Pelicula> ascOrder();
    
    @Query("SELECT p FROM Pelicula p ORDER BY p.fechaCreacion DESC")
    public List<Pelicula> descOrder();
}
