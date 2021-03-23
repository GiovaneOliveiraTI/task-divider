package com.br.pos.taskdivider.repository;

import com.br.pos.taskdivider.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
