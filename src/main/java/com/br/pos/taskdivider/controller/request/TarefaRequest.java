package com.br.pos.taskdivider.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TarefaRequest {

	private Integer id;
	
	@NotBlank(message = "{tarefa.descricao.not-blank}")
	@Size(min = 5, max = 150, message = "{tarefa.descricao.size}")
	private String descricao;

	@FutureOrPresent(message = "{tarefa.descricao.future-or-present}")
	private LocalDate dataEntrega;
	
	@NotNull(message = "{tarefa.categoria.not-null}")
	@Min(value = 1, message = "{tarefa.categoria.min}")
	private Integer categoriaId;
	
	@NotNull(message = "{tarefa.usuario.not-null}")
	@Min(value = 1, message = "{tarefa.usuario.min}")
	private Integer usuarioId;

}
