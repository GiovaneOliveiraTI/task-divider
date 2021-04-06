package com.br.pos.taskdivider.repository;

import com.br.pos.taskdivider.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    Optional<Usuario> findByNome(String username);
}
