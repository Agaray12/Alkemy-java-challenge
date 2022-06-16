package com.java.challenge.services;

import com.java.challenge.enums.Role;
import com.java.challenge.models.Usuario;
import java.util.List;
import com.java.challenge.repositories.UsuarioRepositorio;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    private UsuarioRepositorio usuarioRepo;
    
    public Usuario save(Usuario usuario) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuario.setRole(Role.USER);
        return usuarioRepo.save(usuario);
    }
    
    public List<Usuario> findAll(){
        return usuarioRepo.findAll();
    }
    
    public Usuario findByEmail(String email) {
        return usuarioRepo.findByEmail(email);
    }
    
    public Usuario findByUsername(String username) {
        return usuarioRepo.findByUsername(username);
    }
    
    public Usuario update(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }
    
    public void deleteById(Integer id) {
        usuarioRepo.deleteById(id);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = findByUsername(username);
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + usuario.getRole());
            permisos.add(p1);

            User user = new User(usuario.getUsername(), usuario.getPassword(), permisos);
            return user;
        } else {
            return null;
        }
    }
}