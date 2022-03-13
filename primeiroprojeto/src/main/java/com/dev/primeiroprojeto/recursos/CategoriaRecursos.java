package com.dev.primeiroprojeto.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.primeiroprojeto.entidades.Categoria;
import com.dev.primeiroprojeto.repositorio.CategoriaRepositorio;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaRecursos {
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> finalAll(){
		List<Categoria> list = categoriaRepositorio.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Categoria> finalById(@PathVariable Long id){
		Categoria cat = categoriaRepositorio.findById(id).get();
		return ResponseEntity.ok().body(cat);
	}
}
