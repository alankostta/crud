package com.dev.primeiroprojeto;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dev.primeiroprojeto.entidades.Categoria;
import com.dev.primeiroprojeto.entidades.Produtos;
import com.dev.primeiroprojeto.repositorio.CategoriaRepositorio;
import com.dev.primeiroprojeto.repositorio.ProdutosRepositorio;

@SpringBootApplication
public class PrimeiroprojetoApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	@Autowired
	private ProdutosRepositorio produtosRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(PrimeiroprojetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Eletronicos");
		Categoria cat2 = new Categoria(null,"Livros");
		
		Produtos p1 = new Produtos(null,"TV Samsung 65",2200.00,cat1);
		Produtos p2 = new Produtos(null,"Dominio Driver Designer",120.00,cat2);
		Produtos p3 = new Produtos(null,"PS5",6500.00,cat1);
		Produtos p4 = new Produtos(null,"Docker",100.00,cat2);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		
		categoriaRepositorio.save(cat1);
		categoriaRepositorio.save(cat2);
		
		produtosRepositorio.save(p1);
		produtosRepositorio.save(p2);
		produtosRepositorio.save(p3);
		produtosRepositorio.save(p4);
	}

}
