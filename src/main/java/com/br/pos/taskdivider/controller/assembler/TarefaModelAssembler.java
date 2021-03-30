package com.br.pos.taskdivider.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;

import com.br.pos.taskdivider.controller.TarefaCategoriaController;
import com.br.pos.taskdivider.controller.TarefaController;
import com.br.pos.taskdivider.controller.UsuarioController;
import com.br.pos.taskdivider.controller.response.TarefaResponse;
import com.br.pos.taskdivider.model.Tarefa;
import com.br.pos.taskdivider.model.enums.StatusTarefa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
public class TarefaModelAssembler implements 
	RepresentationModelAssembler<Tarefa, EntityModel<TarefaResponse>> {

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public EntityModel<TarefaResponse> toModel(Tarefa tarefa) {
		TarefaResponse tarefaResp = mapper.map(tarefa, TarefaResponse.class);
		
		EntityModel<TarefaResponse> tarefaModel = EntityModel.of(tarefaResp,
				linkTo(methodOn(TarefaController.class).buscarPorId(tarefaResp.getId())).withSelfRel(),
				linkTo(methodOn(TarefaController.class).buscarTodas(new HashMap<>())).withRel("tarefas"),
				linkTo(methodOn(TarefaCategoriaController.class).umaCategoria(tarefaResp.getCategoriaId())).withRel("categoria"),
				linkTo(methodOn(UsuarioController.class).umUsuario(tarefaResp.getUsuarioId())).withRel("usuario"));
		
		if (StatusTarefa.EM_ANDAMENTO.equals(tarefa.getStatus())) {
			tarefaModel.add(
					linkTo(methodOn(TarefaController.class).concluirTarefa(tarefa.getId())).withRel("concluir"),
					linkTo(methodOn(TarefaController.class).cancelarTarefa(tarefa.getId())).withRel("cancelar")
					);
		}
		
		if (StatusTarefa.ABERTO.equals(tarefa.getStatus())) {
			tarefaModel.add(
					linkTo(methodOn(TarefaController.class).iniciarTarefa(tarefa.getId())).withRel("iniciar")
					);
		}
		
		return tarefaModel;
	}

}
