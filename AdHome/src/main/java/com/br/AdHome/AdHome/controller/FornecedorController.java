package com.br.AdHome.AdHome.controller;

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
import com.br.AdHome.AdHome.Jpa.dto.FornecedorDto;
import com.br.AdHome.AdHome.models.Fornecedor;
import com.br.AdHome.AdHome.services.FornecedorService;
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
@RequestMapping("/adhome/fornecedor")
public class FornecedorController {
	
	final FornecedorService fornecedorService;
	public FornecedorController(FornecedorService fornecedorService) {
		this.fornecedorService = fornecedorService;
	}
	@PostMapping
	public ResponseEntity <Object> salvarFornecedor(@RequestBody @Valid FornecedorDto fornecedorDto){
		var fornecedor = new Fornecedor();
		BeanUtils.copyProperties(fornecedorDto, fornecedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.saveFornecedor(fornecedor));
	}
	@GetMapping
	public ResponseEntity <Page<Fornecedor>> getAllFornecedor(@PageableDefault(
			page  = 0, size = 10, sort = "fornecedorId", direction = Sort.Direction.ASC)Pageable pageable){
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.findAll(pageable));
	}
	@GetMapping("/{fornecedorId}")
	public ResponseEntity <Object> getOneFornecedor(@PathVariable(value = "fornecedorId")Long id){
		Optional <Fornecedor> fornecedorOptional = fornecedorService.findById(id);
		if(!fornecedorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não encontrado");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorOptional.get());
	}
	@DeleteMapping("/{fornecedorId}")
	public ResponseEntity <Object> deleteFornecedor(@PathVariable(value = "fornecedorId")Long id){
		Optional<Fornecedor> fornecedorOptional = fornecedorService.findById(id);
		if(!fornecedorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor não pode ser deletado!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorOptional.get());
	}
	@PutMapping
	public ResponseEntity <Object> updateFornecedor(@PathVariable(value = "fornecedorId")Long id, @RequestBody @Valid 
			FornecedorDto fornecedorDto){
		Optional<Fornecedor> fornecedorOptional = fornecedorService.findById(id);
		if(!fornecedorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O fornecedor não pode ser atualizado");
		}
		var fornecedor = new Fornecedor();
		fornecedor.setFornecedorId(fornecedorOptional.get().getFornecedorId());
		BeanUtils.copyProperties(fornecedorDto, fornecedor);
		return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.saveFornecedor(fornecedor));
	}
}
