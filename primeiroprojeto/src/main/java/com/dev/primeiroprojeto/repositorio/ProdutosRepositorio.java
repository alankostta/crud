package com.dev.primeiroprojeto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.primeiroprojeto.entidades.Produtos;

@Repository
public interface ProdutosRepositorio extends JpaRepository<Produtos, Long>{
	
}

