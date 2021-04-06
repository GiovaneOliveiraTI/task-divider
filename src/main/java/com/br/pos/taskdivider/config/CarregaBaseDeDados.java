package com.br.pos.taskdivider.config;

import com.br.pos.taskdivider.model.*;
import com.br.pos.taskdivider.model.enums.StatusTarefa;
import com.br.pos.taskdivider.repository.CategoriaRepository;
import com.br.pos.taskdivider.repository.RoleRepository;
import com.br.pos.taskdivider.repository.TarefaRepository;
import com.br.pos.taskdivider.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Set;


@Configuration
@Profile("dev")
@AllArgsConstructor
@NoArgsConstructor
public class CarregaBaseDeDados {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRePository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Bean
    CommandLineRunner executar() {
        return args -> {

            Role roleAdmin = new Role(ERole.ROLE_ADMIN);
            roleAdmin = roleRePository.save(roleAdmin);

            Usuario usuario = new Usuario();
            usuario.setNome("Admin");
            usuario.setSenha(encoder.encode("123456"));
            usuario.setRoles(Set.of(roleAdmin));

            usuarioRepository.save(usuario);

            Categoria categoria = new Categoria();
            categoria.setNome("Estudos");
            categoriaRepository.save(categoria);


            Tarefa tarefa = new Tarefa();
            tarefa.setDescricao("Aprender Spring boot");
            tarefa.setDataEntrega(LocalDate.now().plusDays(1));
            tarefa.setStatus(StatusTarefa.ABERTO);
            tarefa.setVisivel(true);
            tarefa.setCategoria(categoria);
            tarefa.setUsuario(usuario);
            tarefaRepository.save(tarefa);

            Tarefa tarefa2 = new Tarefa();
            tarefa2.setDescricao("Aprender JAVA puro");
            tarefa2.setDataEntrega(LocalDate.now().plusDays(1));
            tarefa2.setStatus(StatusTarefa.ABERTO);
            tarefa2.setVisivel(true);
            tarefa2.setCategoria(categoria);
            tarefa2.setUsuario(usuario);
            tarefaRepository.save(tarefa2);

        };
    }
}
