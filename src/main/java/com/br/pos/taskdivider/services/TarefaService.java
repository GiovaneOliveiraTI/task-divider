package com.br.pos.taskdivider.services;

import com.br.pos.taskdivider.controller.response.TarefaResponse;
import com.br.pos.taskdivider.exception.TarefaStatusException;
import com.br.pos.taskdivider.model.Tarefa;
import com.br.pos.taskdivider.model.enums.StatusTarefa;
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

    public  Tarefa iniciarTarefaPorId(Integer id) {
        Tarefa tarefa = getTarefaPorId(id);

        if (StatusTarefa.ABERTO.equals(tarefa.getStatus()))
            throw new TarefaStatusException();


        tarefa.setStatus(StatusTarefa.EM_ANDAMENTO);
        repository.save(tarefa);
        return tarefa;
    }

    public  Tarefa concluirTarefaPorId(Integer id) {
        Tarefa tarefa = getTarefaPorId(id);

        if (StatusTarefa.CONCLUIDA.equals(tarefa.getStatus()))
            throw new TarefaStatusException();


        tarefa.setStatus(StatusTarefa.CONCLUIDA);
        repository.save(tarefa);
        return tarefa;
    }


}
