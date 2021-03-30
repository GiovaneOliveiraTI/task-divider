package com.br.pos.taskdivider.services;

import javax.persistence.EntityNotFoundException;

import com.br.pos.taskdivider.model.Usuario;
import com.br.pos.taskdivider.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepositorio;

	public Usuario getUsuarioPorId(Integer id) {
		return usuarioRepositorio.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}
	
}
