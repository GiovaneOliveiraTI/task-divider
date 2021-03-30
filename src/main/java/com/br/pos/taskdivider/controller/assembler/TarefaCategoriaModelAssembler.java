package com.br.pos.taskdivider.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.br.pos.taskdivider.controller.TarefaCategoriaController;
import com.br.pos.taskdivider.controller.response.TarefaCategoriaResponse;
import com.br.pos.taskdivider.model.Categoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
public class TarefaCategoriaModelAssembler implements
        RepresentationModelAssembler<Categoria, EntityModel<TarefaCategoriaResponse>> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public EntityModel<TarefaCategoriaResponse> toModel(Categoria categoria) {

        TarefaCategoriaResponse categoriaResp = mapper.map(categoria, TarefaCategoriaResponse.class);

        EntityModel<TarefaCategoriaResponse> tarefaModel = EntityModel.of(categoriaResp,
                linkTo(methodOn(TarefaCategoriaController.class).umaCategoria(categoriaResp.getId())).withSelfRel(),
                linkTo(methodOn(TarefaCategoriaController.class).todasCategorias()).withRel("categorias"));

        return tarefaModel;
    }

}
