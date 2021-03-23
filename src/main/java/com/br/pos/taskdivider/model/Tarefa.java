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
@NamedQuery(name = "Tarefa.tarefasPorCategoria", query = "select t from Tarefa t inner join t.categoria c where c.nome = ?1 ")
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
