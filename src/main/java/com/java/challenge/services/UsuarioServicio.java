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

@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    private UsuarioRepositorio usuarioRepo;
    
    @Autowired
    private MailSenderServicio mailSender;
    
    public Usuario save(Usuario usuario) {
        if(!is_valid(usuario)){
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuario.setRole(Role.USER);
        mailSender.sendMail(usuario.getEmail());
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
    
    public boolean existsByUsername(String username){
        if(findByUsername(username) != null){
            return true;
        }
        return false;
    }
    
    public boolean existsByEmail(String email){
        if(findByEmail(email) != null) {
            return true;
        }
        return false;
    }
    public boolean is_valid (Usuario usuario){
        if(usuario.getPassword().isEmpty() || usuario.getPassword() == null){
            return false;
        }
        if(usuario.getUsername().isEmpty() || usuario.getUsername() == null){
            return false;
        }
        if(usuario.getEmail().isEmpty() || usuario.getEmail() == null){
            return false;
        }
        if(existsByEmail(usuario.getEmail())){
            return false;
        }
        if(existsByUsername(usuario.getUsername())){
            return false;
        }
        if(!usuario.getEmail().contains("@")){
            return false;
        }
        return true;
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
