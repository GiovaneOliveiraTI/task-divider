package com.br.pos.taskdivider.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.br.pos.taskdivider.model.Categoria;
import com.br.pos.taskdivider.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TarefaCategoriaService {

	@Autowired
	private CategoriaRepository repositorio;
	
	public List<Categoria> getTodasCategorias() {
		return repositorio.findAll();
	}

	public Categoria getCategoriaPorId(Integer id) {
		return repositorio.findById(id).orElseThrow(() -> new EntityNotFoundException());
	}

	public Categoria salvar(Categoria categoria) {
		return repositorio.save(categoria);
	}

	public void deleteById(Integer id) {
		repositorio.deleteById(id);
	}

}
