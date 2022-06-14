package com.java.challenge.controllers;

import com.java.challenge.dto.PersonajeDTO;
import com.java.challenge.models.Pelicula;
import com.java.challenge.models.Personaje;
import com.java.challenge.services.PeliculaServicio;
import com.java.challenge.services.PersonajeServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class PersonajeController {
    
    @Autowired
    private PersonajeServicio personajeServicio;
    
    @Autowired
    private PeliculaServicio peliculaServicio;
    
    @PostMapping("/create")
    public ResponseEntity<Personaje> crear(@RequestBody Personaje personaje) {
        return new ResponseEntity<>(personajeServicio.save(personaje), HttpStatus.CREATED);
    }
    
    @PostMapping("/update")
    public ResponseEntity<Personaje> actualizar(@RequestBody Personaje personaje) {
        return new ResponseEntity<>(personajeServicio.update(personaje), HttpStatus.FOUND);
    }
    
    @GetMapping("")
    public List<PersonajeDTO> mostrar(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age, @RequestParam(required = false) Integer movies){
        if(name != null) {
            return personajeServicio.setToDTO(personajeServicio.filterByName(name));
        }
        if(age != null) {
            return personajeServicio.setToDTO(personajeServicio.filterByAge(age));
        }
        if(movies != null) {
            Optional<Pelicula> respuesta = peliculaServicio.findById(movies);
            if (respuesta.isPresent()) {
                Pelicula pelicula = respuesta.get();
                return personajeServicio.setToDTO(pelicula.getPersonajes());
            }
        }
        
        return personajeServicio.setToDTO(personajeServicio.findAll());
    }
}
