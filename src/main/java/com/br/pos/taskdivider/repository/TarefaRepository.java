package com.br.pos.taskdivider.repository;

import com.br.pos.taskdivider.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

}
