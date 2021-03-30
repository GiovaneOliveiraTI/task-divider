package com.br.pos.taskdivider.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.br.pos.taskdivider.controller.UsuarioController;
import com.br.pos.taskdivider.controller.response.UsuarioResponse;
import com.br.pos.taskdivider.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UsuarioModelAssembler implements
	RepresentationModelAssembler<Usuario, EntityModel<UsuarioResponse>> {

	@Autowired
	private ModelMapper mapper;

	@Override
	public EntityModel<UsuarioResponse> toModel(Usuario usuario) {
		UsuarioResponse usuarioResp = mapper.map(usuario, UsuarioResponse.class);

		EntityModel<UsuarioResponse> usuarioModel = EntityModel.of(usuarioResp,
				linkTo(methodOn(UsuarioController.class).umUsuario(usuarioResp.getId())).withSelfRel());

		return usuarioModel;
	}

}
