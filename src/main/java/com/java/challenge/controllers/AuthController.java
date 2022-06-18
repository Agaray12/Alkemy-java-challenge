package com.java.challenge.controllers;

import com.java.challenge.dto.UsuarioDTO;
import com.java.challenge.models.Usuario;
import com.java.challenge.services.JwtUtilServicio;
import com.java.challenge.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @Autowired 
    private JwtUtilServicio jwtUtilServicio;
    
    @Autowired
    private AuthenticationManager authenticacionManager;
    
    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        if(usuario != null){
            usuarioServicio.save(usuario);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO request) {
        Authentication authentication = authenticacionManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        
        UserDetails userDetails = usuarioServicio.loadUserByUsername(request.getUsername());
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt = jwtUtilServicio.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }
    
}
