package com.br.pos.taskdivider.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;

    private String senha;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Tarefa> tarefas;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "usuarios_roles",
//    joinColumns = @JoinColumn(name = "usuario_id"),
//    inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles = new HashSet<>();

}
