package com.br.pos.taskdivider.services;

import com.br.pos.taskdivider.controller.response.JwtResponse;
import com.br.pos.taskdivider.model.Role;
import com.br.pos.taskdivider.model.Usuario;
import com.br.pos.taskdivider.repository.RoleRepository;
import com.br.pos.taskdivider.repository.UsuarioRepository;
import com.br.pos.taskdivider.security.JwtUtils;
import com.br.pos.taskdivider.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UsuarioService {


    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UsuarioRepository usuarioRepositorio;
    @Autowired
    private RoleRepository roleRepositorio;
    @Autowired
    private PasswordEncoder encoder;

    public Usuario getUsuarioPorId(Integer id) {
        return usuarioRepositorio.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Usuario> getTodosUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public Usuario salvar(Usuario usuario) {
        Set<Role> roles = getRoles(usuario);
        usuario.setRoles(roles);
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return usuarioRepositorio.save(usuario);
    }

    public Usuario atualizar(Integer id, Usuario usuario) {
        if (!usuarioRepositorio.existsById(id))
            throw new EntityNotFoundException();

        usuario.setId(id);

        return salvar(usuario);
    }

    public void deleteById(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    private Set<Role> getRoles(Usuario usuario) {
        Set<Role> rolesBanco = usuario.getRoles()
                .stream()
                .map(role -> roleRepositorio.findByName(role.getName()))
                .collect(Collectors.toSet());

        return rolesBanco;
    }

    public JwtResponse autenticaUsuario(String nome, String senha) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(nome, senha));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl)
                authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);
    }

}
