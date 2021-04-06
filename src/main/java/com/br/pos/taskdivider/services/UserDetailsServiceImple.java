package com.br.pos.taskdivider.services;

import com.br.pos.taskdivider.model.Usuario;
import com.br.pos.taskdivider.repository.UsuarioRepository;
import com.br.pos.taskdivider.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImple implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Usuario usuario = usuarioRepository.findByNome(username)
        .orElseThrow(() -> new UsernameNotFoundException("usuário não encontrado!"));
        return UserDetailsImpl.build(usuario);
    }
}
