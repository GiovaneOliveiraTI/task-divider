package com.br.pos.taskdivider.repository;

import com.br.pos.taskdivider.model.Tarefa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TarefaRepositoryTest {

    @Autowired
    private TarefaRepository repository;

    @Test
    void testFindByNomeCategoria() {
        List<Tarefa> tarefas = repository.findByNomeCategoria("Estudos");
        Assertions.assertEquals(3, tarefas.size());
    }

    @Test
    void testFindByPorCategoria() {
        List<Tarefa> tarefas = repository.tarefasPorCategoria("Estudos");
        Assertions.assertEquals(3, tarefas.size());
    }





}
