package com.java.challenge.repositories;

import com.java.challenge.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{
    
    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    public Usuario findByEmail(@Param("email") String email);
    
    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    public Usuario findByUsername(@Param("username") String username);
}
