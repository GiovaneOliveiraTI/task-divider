package com.br.pos.taskdivider.controller;

import com.br.pos.taskdivider.controller.request.TarefaRequest;
import com.br.pos.taskdivider.controller.response.TarefaResponse;
import com.br.pos.taskdivider.model.Tarefa;
import com.br.pos.taskdivider.services.TarefaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

    @GetMapping
    public List<TarefaResponse> buscarTodas(@RequestParam Map<String, String> parametros) {
        List<Tarefa> tarefas = new ArrayList<>();

        if (parametros.isEmpty()) {
            tarefas = service.getBuscaTodas();
        } else {
            String descricao = parametros.get("descricao");
            tarefas = service.getTarefasPorDecricao(descricao);
        }
        return tarefas
                .stream()
                .map(tarefa -> mapper.map(tarefa, TarefaResponse.class))
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public EntityModel<TarefaResponse> buscarPorId(@PathVariable Integer id) {
        Tarefa tarefa = service.getTarefaPorId(id);
        TarefaResponse tarefaRsp = mapper.map(tarefa, TarefaResponse.class);

        EntityModel<TarefaResponse> tarefaModel =  EntityModel.of(tarefaRsp,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).buscarPorId(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).buscarTodas(new HashMap<>())).withRel("tarefas"));

        return tarefaModel;
    }

    @PostMapping
    public TarefaResponse salvarTarefa(@Valid @RequestBody TarefaRequest tarefaReq) {
        Tarefa tarefa = mapper.map(tarefaReq, Tarefa.class);

        return mapper.map(service.salvarTarefa(tarefa), TarefaResponse.class);
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Integer id) {
        service.deleteTarefa(id);
    }


}
