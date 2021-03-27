package com.br.pos.taskdivider.services;

import java.util.Optional;

import com.br.pos.taskdivider.exception.TarefaStatusException;
import com.br.pos.taskdivider.model.Tarefa;
import com.br.pos.taskdivider.model.enums.StatusTarefa;
import com.br.pos.taskdivider.repository.TarefaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

	@Mock
	private TarefaRepository repositorio;
	
	@InjectMocks
	private TarefaService service;

	@Test
	void naoDeveConcluirTarefaCancelada() {

		Integer idExemplo = 370;

		Tarefa tarefa = new Tarefa();
		tarefa.setId(idExemplo);
		tarefa.setDescricao("Teste 01");
		tarefa.setStatus(StatusTarefa.CANCELADA);

		Mockito.when(repositorio.findById(idExemplo)).thenReturn(Optional.of(tarefa));

		Assertions.assertThrows(TarefaStatusException.class,
				() -> service.concluirTarefaPorId(idExemplo));

	}
	
}
