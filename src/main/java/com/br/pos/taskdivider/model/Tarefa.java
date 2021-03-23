package com.br.pos.taskdivider.model;

import com.br.pos.taskdivider.model.enums.StatusTarefa;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "ds_descricao")
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

    @Column(name = "dtEntrega")
    private LocalDate dataEntrega;

    @Column(name = "visivel")
    private Boolean visivel;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

}
