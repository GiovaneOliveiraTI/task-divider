package com.br.pos.taskdivider.controller;

import com.br.pos.taskdivider.model.Tarefa;
import com.br.pos.taskdivider.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class TarefaController {

   @Autowired
   private TarefaService tarefaService;

    @GetMapping("/tarefa")
    public List<Tarefa> buscarTodas(@RequestParam Map<String, String> parametros) {
        if (parametros.isEmpty()) {
            return tarefaService.getBuscaTodas();
        }
        String descricao = parametros.get("descricao");
        return tarefaService.getTarefasPorDecricao(descricao);
    }

    @GetMapping("/tarefa/{id}")
    public Tarefa buscarPorId(@PathVariable Integer id) {
        return tarefaService.getTarefaPorId(id);
    }

    @PostMapping("/tarefa")
    public Tarefa salvarTarefa(@Valid @RequestBody Tarefa tarefa) {
        return tarefaService.salvarTarefa(tarefa);
    }

    @DeleteMapping("/tarefa/{id}")
    public void deletarTarefa(@PathVariable Integer id) {
        tarefaService.deleteTarefa(id);
    }


}
