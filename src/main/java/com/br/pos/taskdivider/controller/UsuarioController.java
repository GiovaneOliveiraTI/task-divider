package com.br.pos.taskdivider.controller;

import com.br.pos.taskdivider.controller.assembler.UsuarioModelAssembler;
import com.br.pos.taskdivider.controller.response.UsuarioResponse;
import com.br.pos.taskdivider.model.Usuario;
import com.br.pos.taskdivider.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioModelAssembler assembler;
	
	@GetMapping("/{id}")
	public EntityModel<UsuarioResponse> umUsuario(@PathVariable Integer id) {
		Usuario usuario = usuarioService.getUsuarioPorId(id);
		EntityModel<UsuarioResponse> usuarioResponse = assembler.toModel(usuario);
		return usuarioResponse;
	}
	
}
