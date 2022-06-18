package com.java.challenge.controllers;

import com.java.challenge.dto.GeneroDTO;
import com.java.challenge.dto.PeliculaDTO;
import com.java.challenge.models.Genero;
import com.java.challenge.models.Pelicula;
import com.java.challenge.models.Personaje;
import com.java.challenge.services.GeneroServicio;
import com.java.challenge.services.PeliculaServicio;
import com.java.challenge.services.PersonajeServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class PeliculaController {
    
    @Autowired
    private PeliculaServicio peliculaServicio;
    
    @Autowired
    private PersonajeServicio personajeServicio;
    
    @Autowired
    private GeneroServicio generoServicio;
    
    @PostMapping("/create")
    public ResponseEntity<Pelicula> crearPelicula(@RequestBody Pelicula pelicula) {
        return new ResponseEntity<>(peliculaServicio.save(pelicula), HttpStatus.CREATED);
    }
    
    @PostMapping("/genre/create")
    public ResponseEntity<Genero> crearGenero(@RequestBody Genero genero) {
        if(generoServicio.findById(genero.getId()) != null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(generoServicio.save(genero), HttpStatus.CREATED);
    }
    
    @PostMapping("{idMovie}/genre/{idGenre}")
    public ResponseEntity<?> agregarGenero(@PathVariable Integer idMovie, @PathVariable Integer idGenre) {
        Optional<Genero> respuesta = generoServicio.findById(idGenre);
        Optional<Pelicula> respuestaPelicula = peliculaServicio.findById(idMovie);
        if(respuesta.isPresent() && respuestaPelicula.isPresent()){
            Genero genero = respuesta.get();
            Pelicula pelicula = respuestaPelicula.get();
            generoServicio.addMovieToGenre(genero, pelicula);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
    
    @DeleteMapping("{idMovie}/genre/{idGenre}")
    public ResponseEntity<?> eliminarGeneroPelicula(@PathVariable Integer idMovie, @PathVariable Integer idGenre){
        Optional<Genero> respuesta = generoServicio.findById(idGenre);
        Optional<Pelicula> respuestaPelicula = peliculaServicio.findById(idMovie);
        if(respuesta.isPresent() && respuestaPelicula.isPresent()){
            Genero genero = respuesta.get();
            Pelicula pelicula = respuestaPelicula.get();
            generoServicio.deleteMovieFromGenre(genero, pelicula);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
    
    @GetMapping("/genre")
    public List<GeneroDTO> mostrarGeneros(){
        return generoServicio.setToDTO(generoServicio.findAll());
    }
    
    @PostMapping("/update")
    public ResponseEntity<Pelicula> actualizarPelicula(@RequestBody Pelicula pelicula){
        Optional<Pelicula> aux = peliculaServicio.findById(pelicula.getId());
        if(aux.isPresent()){
            Pelicula peliculaDB = aux.get();
            return new ResponseEntity(peliculaServicio.update(pelicula, peliculaDB), HttpStatus.FOUND);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("")
    public List<PeliculaDTO> mostrar (@RequestParam(required = false) String name, @RequestParam(required = false) Integer genre, @RequestParam(required = false) String order){
        
        if(name != null) {
            return peliculaServicio.setToDTO(peliculaServicio.filterByTitle(name));
        }
        
        if(genre != null) {
            Optional<Genero> respuesta = generoServicio.findById(genre);
            if (respuesta.isPresent()) {
                Genero genero = respuesta.get();
                return peliculaServicio.setToDTO(genero.getPeliculas());
            }
        }
        
        if(order != null){
            if(order.equals("ASC")) {
                return peliculaServicio.setToDTO(peliculaServicio.ascOrder());
            }
            if(order.equals("DESC")) {
                return peliculaServicio.setToDTO(peliculaServicio.descOrder());
            }
        }   
        
        return peliculaServicio.setToDTO(peliculaServicio.findAll());
    }
    
    @PostMapping("{idMovie}/characters/{idCharacter}")
    public ResponseEntity<?> agregarPersonaje(@PathVariable Integer idMovie, @PathVariable Integer idCharacter) {
        Optional<Personaje> respuesta = personajeServicio.findById(idCharacter);
        Optional<Pelicula> respuestaPelicula = peliculaServicio.findById(idMovie);
        if(respuesta.isPresent() && respuestaPelicula.isPresent()){
            Personaje personaje = respuesta.get();
            Pelicula pelicula = respuestaPelicula.get();
            peliculaServicio.addCharacterToMovie(pelicula, personaje);
            personajeServicio.addMovieToCharacter(personaje, pelicula);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
    
    @DeleteMapping("{idMovie}/characters/{idCharacter}")
    public ResponseEntity<?> removerPersonaje(@PathVariable Integer idMovie, @PathVariable Integer idCharacter) {
        Optional<Personaje> respuesta = personajeServicio.findById(idCharacter);
        Optional<Pelicula> respuestaPelicula = peliculaServicio.findById(idMovie);
        
        if(respuesta.isPresent() && respuestaPelicula.isPresent()) {
            Personaje personaje = respuesta.get();
            Pelicula pelicula = respuestaPelicula.get();
            peliculaServicio.deleteCharacterFromMovie(pelicula, personaje);
            personajeServicio.deleteMovieFromCharacter(personaje, pelicula);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
}
