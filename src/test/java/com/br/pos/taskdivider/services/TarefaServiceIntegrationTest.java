package com.br.pos.taskdivider.services;

import com.br.pos.taskdivider.exception.TarefaStatusException;
import com.br.pos.taskdivider.model.Tarefa;
import com.br.pos.taskdivider.model.enums.StatusTarefa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TarefaServiceIntegrationTest {

	@Autowired
	private TarefaService service;
	
	@Test
	void deveIniciarTarefa() {
		Tarefa tarefa = service.iniciarTarefaPorId(353);
		Assertions.assertEquals(StatusTarefa.EM_ANDAMENTO, tarefa.getStatus());
	}
	
	@Test
	void naoDeveIniciarTarefaConcluida() {
		Tarefa tarefa = service.getTarefaPorId(370);
		tarefa.setStatus(StatusTarefa.EM_ANDAMENTO);
		service.salvarTarefa(tarefa);

		Assertions.assertThrows(TarefaStatusException.class,
				() -> service.iniciarTarefaPorId(3));
	}

}
