package com.br.pos.taskdivider.services;

import com.br.pos.taskdivider.model.Tarefa;
import com.br.pos.taskdivider.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public List<Tarefa> getBuscaTodas() {
        return repository.findAll();
    }

    public List<Tarefa> getTarefasPorDecricao(String descricao) {
        return repository.findByDescricaoLike("%" + descricao + "%");
    }

    public Tarefa getTarefaPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Tarefa salvarTarefa(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public void deleteTarefa(Integer id) {
        repository.deleteById(id);
    }


}
