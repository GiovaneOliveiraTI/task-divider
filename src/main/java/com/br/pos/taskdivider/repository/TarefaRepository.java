package com.br.pos.taskdivider.repository;

import com.br.pos.taskdivider.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    public List<Tarefa> findByDescricaoLike(String descricao);

}


