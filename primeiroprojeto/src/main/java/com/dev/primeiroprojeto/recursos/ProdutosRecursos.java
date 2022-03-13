package com.dev.primeiroprojeto.recursos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.primeiroprojeto.entidades.Produtos;
import com.dev.primeiroprojeto.repositorio.ProdutosRepositorio;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutosRecursos {
	
	@Autowired
	private ProdutosRepositorio categoriaRepositorio;
	
	@GetMapping
	public ResponseEntity<List<Produtos>> finalAll(){
		List<Produtos> list = categoriaRepositorio.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Produtos> finalById(@PathVariable Long id){
		Produtos cat = categoriaRepositorio.findById(id).get();
		return ResponseEntity.ok().body(cat);
	}
}
