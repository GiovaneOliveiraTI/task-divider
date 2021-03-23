package com.br.pos.taskdivider.controller;

import com.br.pos.taskdivider.model.Tarefa;
import com.br.pos.taskdivider.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TarefaController {

    @Autowired
    private TarefaRepository repository;

    @GetMapping("/tarefa")
    public List<Tarefa> buscarTodas(@RequestParam Map<String, String> parametros) {
        if (parametros.isEmpty()) {
            return repository.findAll();
        }
        String descricao = parametros.get("descricao");
        return repository.findByDescricaoLike("%" + descricao + "%");
    }

    @GetMapping("/tarefa/{id}")
    public Tarefa buscarPorId(@PathVariable Integer id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/tarefa")
    public Tarefa salvarTarefa(@RequestBody Tarefa tarefa) {
        return repository.save(tarefa);
    }

    @DeleteMapping("/tarefa/{id}")
    public void deletarTarefa(@PathVariable Integer id) {
        repository.deleteById(id);
    }


}