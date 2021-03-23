package com.br.pos.taskdivider.repository;

import com.br.pos.taskdivider.model.Categoria;
import com.br.pos.taskdivider.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    public List<Tarefa> findByDescricaoLike(String descricao);

    public List<Tarefa> findByCategoria(Categoria categoria);

    //Usando @Query
    @Query("select t from Tarefa t inner join t.categoria c where c.nome = ?1 ")
    public List<Tarefa> findByNomeCategoria(String categoria);

    //Usando namedQuery
    public List<Tarefa> tarefasPorCategoria(String nomeCategoria);

}


