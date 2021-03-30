package com.br.pos.taskdivider.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.br.pos.taskdivider.controller.response.TarefaCategoriaResponse;
import com.br.pos.taskdivider.model.Categoria;
import com.br.pos.taskdivider.services.TarefaCategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.tarefas.controller.request.TarefaCategoriaRequest;

@RestController
public class TarefaCategoriaController {

	@Autowired
	private TarefaCategoriaService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping("/categoria")
	public List<TarefaCategoriaResponse> todasCategorias() {
		List<Categoria> categorias = service.getTodasCategorias();
		return categorias
			.stream()
			.map(categoria -> mapper.map(categoria, TarefaCategoriaResponse.class))
			.collect(Collectors.toList());
	}
	
	@GetMapping("/categoria/{id}")
	public TarefaCategoriaResponse umaCategoria(@PathVariable Integer id) {
		return mapper.map(
				service.getCategoriaPorId(id), 
				TarefaCategoriaResponse.class);
	}
	
	@PostMapping("/categoria")
	public TarefaCategoriaResponse salvarCategoria(@RequestBody TarefaCategoriaRequest categoriaReq) {
		Categoria categoria = mapper.map(categoriaReq, Categoria.class);
		return mapper.map(service.salvar(categoria), TarefaCategoriaResponse.class);
	}
	
	@DeleteMapping("/categoria/{id}")
	public void excluirTarefa(@PathVariable Integer id) {
		service.deleteById(id);
	}
	
}
