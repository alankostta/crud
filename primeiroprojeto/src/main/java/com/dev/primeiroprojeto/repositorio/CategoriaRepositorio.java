package com.dev.primeiroprojeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.primeiroprojeto.entidades.Categoria;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria,Long> {
	
}
