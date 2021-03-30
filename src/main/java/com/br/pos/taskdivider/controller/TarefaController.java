package com.br.pos.taskdivider.controller;

import com.br.pos.taskdivider.controller.assembler.TarefaModelAssembler;
import com.br.pos.taskdivider.controller.request.TarefaRequest;
import com.br.pos.taskdivider.controller.response.TarefaResponse;
import com.br.pos.taskdivider.model.Tarefa;
import com.br.pos.taskdivider.services.TarefaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TarefaModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<TarefaResponse>> buscarTodas(@RequestParam Map<String, String> parametros) {
        List<Tarefa> tarefas = new ArrayList<>();

        if (parametros.isEmpty()) {
            tarefas = service.getBuscaTodas();
        } else {
            String descricao = parametros.get("descricao");
            tarefas = service.getTarefasPorDecricao(descricao);
        }

        List<EntityModel<TarefaResponse>> tarefasModel = tarefas
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(tarefasModel,
                WebMvcLinkBuilder.linkTo(methodOn(TarefaController.class).buscarTodas(new HashMap<>()))
                        .withSelfRel()
        );
    }

    @GetMapping("/{id}")
    public EntityModel<TarefaResponse> buscarPorId(@PathVariable Integer id) {
        Tarefa tarefa = service.getTarefaPorId(id);
        return assembler.toModel(tarefa);
    }

    @PostMapping
    public ResponseEntity<EntityModel<TarefaResponse>> salvarTarefa(@Valid @RequestBody TarefaRequest tarefaReq) {
        Tarefa tarefa = mapper.map(tarefaReq, Tarefa.class);
        Tarefa tarefaSalva = service.salvarTarefa(tarefa);

        EntityModel<TarefaResponse> tarefaModel = assembler.toModel(tarefaSalva);

        return ResponseEntity
                .created(tarefaModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(tarefaModel);
    }

    @DeleteMapping("/{id}")
    public void excluirTarefa(@PathVariable Integer id) {
        service.deleteTarefa(id);
    }

    @PutMapping("/{id}/iniciar")
    public EntityModel<TarefaResponse> iniciarTarefa(@PathVariable Integer id) {
        Tarefa tarefa = service.iniciarTarefaPorId(id);
        return assembler.toModel(tarefa);
    }

    @PutMapping("/{id}/concluir")
    public EntityModel<TarefaResponse> concluirTarefa(@PathVariable Integer id) {
        Tarefa tarefa = service.concluirTarefaPorId(id);
        return assembler.toModel(tarefa);
    }

    @PutMapping("/{id}/cancelar")
    public EntityModel<TarefaResponse> cancelarTarefa(@PathVariable Integer id) {
        Tarefa tarefa = service.cancelarTarefaPorId(id);
        return assembler.toModel(tarefa);
    }


}
