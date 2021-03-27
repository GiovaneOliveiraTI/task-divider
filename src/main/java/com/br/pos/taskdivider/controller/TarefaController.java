package com.br.pos.taskdivider.controller;

import com.br.pos.taskdivider.controller.request.TarefaRequest;
import com.br.pos.taskdivider.controller.response.TarefaResponse;
import com.br.pos.taskdivider.model.Tarefa;
import com.br.pos.taskdivider.services.TarefaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TarefaController {

    @Autowired
    private TarefaService service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/tarefa")
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

    @GetMapping("/tarefa/{id}")
    public TarefaResponse buscarPorId(@PathVariable Integer id) {
        Tarefa tarefa = service.getTarefaPorId(id);
        return mapper.map(tarefa, TarefaResponse.class);
    }

    @PostMapping("/tarefa")
    public TarefaResponse salvarTarefa(@Valid @RequestBody TarefaRequest tarefaReq) {
        Tarefa tarefa = mapper.map(tarefaReq, Tarefa.class);

        return mapper.map(service.salvarTarefa(tarefa), TarefaResponse.class);
    }

    @DeleteMapping("/tarefa/{id}")
    public void deletarTarefa(@PathVariable Integer id) {
        service.deleteTarefa(id);
    }


}
