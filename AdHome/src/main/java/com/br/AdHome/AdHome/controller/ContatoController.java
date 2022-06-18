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
import com.br.AdHome.AdHome.Jpa.dto.ContatoDto;
import com.br.AdHome.AdHome.models.Contato;
import com.br.AdHome.AdHome.services.ContatoService;

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
@RequestMapping("/adhome/contato")
public class ContatoController {
	//Injeção de dependencia
	final ContatoService contatoService;
	public ContatoController(ContatoService contatoService) {
		this.contatoService = contatoService;
	}
	@PostMapping
	public ResponseEntity<Object> salveContato(@RequestBody @Valid ContatoDto contatoDto){
		var contato = new Contato();
		BeanUtils.copyProperties(contatoDto, contato);
		return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.saveContato(contato));
		
	}
	@GetMapping
	public ResponseEntity<Page<Contato>> getAllContatos(@PageableDefault
			(page = 0, size = 10, sort = "contatoId", direction = Sort.Direction.ASC)Pageable pageable){
		return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.findAll(pageable));
	}
	@GetMapping("/{contatoId}")
	public ResponseEntity<Object> getOneContato(@PathVariable(value = "contatoId")Long id){
		Optional<Contato> contatoOptional = contatoService.findById(id);
		if(!contatoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(contatoOptional.get());
	}
	@DeleteMapping("/{contatoId}")
	public ResponseEntity<Object> deleteContato(@PathVariable(value = "contatoId")Long id){
		Optional<Contato> contatoOptional = contatoService.findById(id);
		if(!contatoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não localizado para ser deletado");
		}
		contatoService.deleteContato(contatoOptional.get());
		return ResponseEntity.status(HttpStatus.CREATED).body(contatoOptional.get());
	}
	@PutMapping("/{contatoId}")
	public ResponseEntity<Object> updateContato(@PathVariable(value = "contatoId")Long id, @RequestBody @Valid ContatoDto contatoDto){
		Optional<Contato> contatoOptional = contatoService.findById(id);
		if(!contatoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não localizado para ser atualização");
		}
		var contato = new Contato();
		contato.setContatoId(contatoOptional.get().getContatoId());
		BeanUtils.copyProperties(contatoDto, contato);
		return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.saveContato(contato));
	}
}






















