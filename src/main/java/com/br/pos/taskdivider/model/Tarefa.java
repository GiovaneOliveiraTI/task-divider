package com.br.pos.taskdivider.model;

import com.br.pos.taskdivider.model.enums.StatusTarefa;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Tarefa {
    
    private String descricao;

    private StatusTarefa status;

    private LocalDate dataEntrega;

    private Boolean visivel;

    private Categoria categoria;

    private Usuario usuario;

}
