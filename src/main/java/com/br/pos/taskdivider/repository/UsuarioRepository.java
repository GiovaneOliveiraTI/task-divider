package com.br.pos.taskdivider.repository;

import com.br.pos.taskdivider.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

}
