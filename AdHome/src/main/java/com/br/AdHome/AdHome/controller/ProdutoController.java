package com.br.AdHome.AdHome.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.AdHome.AdHome.Jpa.dto.ProdutoDto;
import com.br.AdHome.AdHome.models.Produto;
import com.br.AdHome.AdHome.services.ProdutoService;

/*
 * Controlador: responder interações do usuśrio
 * No caso de uma API REST "interações" são as requisições.
 * Passos das requisições que serão feitas pelo usúario
 * 1º controller envia solicitação para o service
 * 2º service envia a solicitação para o repository
 * 3º repository envia para o banco
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/adhome/produto")
public class ProdutoController {
	final ProdutoService produtoService;
	public ProdutoController(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	@PostMapping
	public ResponseEntity <Object> salvarProdutos(@RequestBody @Valid ProdutoDto produtoDto){
		var produto = new Produto();
		produto.setDataProduto(LocalDateTime.now(ZoneId.of("UTC")));
		BeanUtils.copyProperties(produtoDto, produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvarProduto(produto));
	}
	@GetMapping
	public ResponseEntity <Page<Produto>> getAllProdutos(@PageableDefault(page = 0, size = 10,
	sort = "produtoId", direction = Sort.Direction.ASC)Pageable Pageable){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.findAll(Pageable));
		
	}
	@GetMapping("/{produtoId}")
	public ResponseEntity <Object> getOneProdutos(@PathVariable(value = "produtoId")Long id){
		Optional <Produto> produtoOptional = produtoService.findById(id);
		if(!produtoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O produto não foi localizado!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoOptional.get());
	}
	@DeleteMapping("/{produtoId}")
	public ResponseEntity <Object> deleteProdutos(@PathVariable(value = "produtoId")Long id){
		Optional <Produto> produtoOptional = produtoService.findById(id);
		if(!produtoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O produto não pode ser deletado!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoOptional.get());
	}
	@PutMapping("/{produtoId}")
	public ResponseEntity <Object> updateProdutos(@PathVariable(value = "produtoId")Long id,
			@RequestBody @Valid ProdutoDto produtoDto){
		Optional <Produto> produtoOptional = produtoService.findById(id);
		if(!produtoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O produto não pode ser atualizado");
		}
		var produto = new Produto();
		produto.setDataProduto(produtoOptional.get().getDataProduto());
		produto.setProdutoId(produtoOptional.get().getProdutoId());
		BeanUtils.copyProperties(produtoDto, produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvarProduto(produto));
	}
}
