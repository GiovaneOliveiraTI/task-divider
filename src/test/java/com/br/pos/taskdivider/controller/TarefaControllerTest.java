package com.br.pos.taskdivider.controller;

import com.br.pos.taskdivider.model.Categoria;
import com.br.pos.taskdivider.model.Tarefa;
import com.br.pos.taskdivider.model.Usuario;
import com.br.pos.taskdivider.model.enums.StatusTarefa;
import com.br.pos.taskdivider.services.TarefaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;



@SpringBootTest
public class TarefaControllerTest {

	@Autowired
	private TarefaController controller;
	
	@MockBean
	private TarefaService service;
	

	@Test
	void validaTarefaResponse() {
		int tarefaId = 999;
		
		Tarefa tarefa = new Tarefa();
		tarefa.setId(tarefaId);
		tarefa.setStatus(StatusTarefa.ABERTO);
		
		Usuario usuario = new Usuario();
		usuario.setId(1);
		tarefa.setUsuario(usuario );
		
		Categoria categoria = new Categoria();
		categoria.setId(2);
		tarefa.setCategoria(categoria );
		
		Mockito.when(service.getTarefaPorId(tarefaId)).thenReturn(tarefa);
		
//		TarefaResponse tarefaResp = controller.buscarPorId(tarefaId);
//
//		Assertions.assertEquals(tarefaId, tarefaResp.getId());
//		Assertions.assertEquals(2, tarefaResp.getCategoriaId());
//		Assertions.assertEquals(1, tarefaResp.getUsuarioId());
//		Assertions.assertEquals(StatusTarefa.ABERTO.name(), tarefaResp.getStatus());
	}
	
}
