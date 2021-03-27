package com.br.pos.taskdivider.model;

import com.br.pos.taskdivider.model.enums.StatusTarefa;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "tarefas")
@NamedQuery(name = "Tarefa.tarefasPorCategoria", query = "select t from Tarefa t inner join t.categoria c where c.nome = ?1 ")
public class Tarefa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @NotBlank(message = "{tarefa.descricao.not-blank}")
    @Size(min = 5, max = 150, message = "{tarefa.descricao.size}")
    @Column(name = "ds_tarefa", nullable = false, length = 150)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private StatusTarefa status;

    @FutureOrPresent( message = "{tarefa.descricao.future-or-present}")
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
