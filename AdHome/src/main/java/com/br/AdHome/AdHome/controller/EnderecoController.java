
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
import com.br.AdHome.AdHome.Jpa.dto.EnderecoDto;
import com.br.AdHome.AdHome.models.Endereco;
import com.br.AdHome.AdHome.services.EnderecoService;

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
@RequestMapping("/adhome/endereco")
public class EnderecoController {
	
	final EnderecoService enderecoService;
	public EnderecoController(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}
	@PostMapping 
	public ResponseEntity <Object> salveEnderecos(@RequestBody @Valid EnderecoDto enderecoDto){
		var endereco = new Endereco();
		BeanUtils.copyProperties(enderecoDto, endereco);
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.saveEndereco(endereco));
		
	}
	@GetMapping
	public ResponseEntity <Page<Endereco>> getAllEnderecos(@PageableDefault
			(page = 0, size = 10, sort = "enderecoId",direction = Sort.Direction.ASC)Pageable pageable){
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.findAllEndereco(pageable));
		
	}
	@GetMapping("/{enderecoId}")
	public ResponseEntity <Object> getOneEnderecos(@PathVariable(value = "enderecoId")Long id){
		Optional<Endereco> enderecoOptional = enderecoService.findByIdEndereco(id);
		if(!enderecoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O endereço pesquisado não foi encontrado!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoOptional.get());
	}
	@DeleteMapping("/{enderecoId}")
	public ResponseEntity <Object> deleteEndereco(@PathVariable(value = "enderecoId")Long id){
		Optional<Endereco> enderecoOptional = enderecoService.findByIdEndereco(id);
		if(!enderecoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O endereço não pode ser excluido!");
		}
		enderecoService.deleteEndereco(enderecoOptional.get());
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoOptional.get());
	}
	@PutMapping("/{enderecoId}")
	public ResponseEntity <Object> updateEndereco(@PathVariable(value = "enderecoId")Long id,
			@RequestBody @Valid EnderecoDto enderecoDto){
		Optional<Endereco> enderecoOptional = enderecoService.findByIdEndereco(id);
		if(!enderecoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O endereço não pode ser alterado!");
		}
		
		var endereco = new Endereco();
		endereco.setEnderecoId(enderecoOptional.get().getEnderecoId());
		BeanUtils.copyProperties(enderecoDto, endereco);
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoService.saveEndereco(endereco));
		
	}
}











