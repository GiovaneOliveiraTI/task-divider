package com.br.pos.taskdivider.config;

import com.br.pos.taskdivider.model.Categoria;
import com.br.pos.taskdivider.model.Tarefa;
import com.br.pos.taskdivider.model.Usuario;
import com.br.pos.taskdivider.model.enums.StatusTarefa;
import com.br.pos.taskdivider.repository.CategoriaRepository;
import com.br.pos.taskdivider.repository.TarefaRepository;
import com.br.pos.taskdivider.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class CarregaBaseDeDados {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Bean
    CommandLineRunner executar() {
        return args -> {
            Usuario usuario = new Usuario();
            usuario.setNome("Admin");
            usuario.setSenha("1234");
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

            Tarefa tarefa3 = new Tarefa();
            tarefa3.setDescricao("Aprender JPA ");
            tarefa3.setDataEntrega(LocalDate.now().plusDays(1));
            tarefa3.setStatus(StatusTarefa.ABERTO);
            tarefa3.setVisivel(true);
            tarefa3.setCategoria(categoria);
            tarefa3.setUsuario(usuario);
            tarefaRepository.save(tarefa3);
        };
    }
}
