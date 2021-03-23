package com.br.pos.taskdivider.controller;

import com.br.pos.taskdivider.model.Tarefa;
import com.br.pos.taskdivider.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TarefaController {

    @Autowired
    private TarefaRepository repository;

    @GetMapping("/tarefa")
    public List<Tarefa> buscarTodas() {
        return repository.findAll();
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
