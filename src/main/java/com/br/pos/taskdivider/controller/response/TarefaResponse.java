package com.br.pos.taskdivider.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TarefaResponse {

	private Integer id;
	
	private String descricao;
	
	private String status;
	
	private LocalDate dataEntrega;
	
	private Integer categoriaId;
	
	private Integer usuarioId;
}
