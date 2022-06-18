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
import com.br.AdHome.AdHome.Jpa.dto.ClienteDto;
import com.br.AdHome.AdHome.models.Cliente;
import com.br.AdHome.AdHome.services.ClienteService;


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
@RequestMapping("/adhome/cliente")
public class ClienteController {
	// insere a classe e ápos isso cria o metodo construtor
	final ClienteService clienteService;

	public ClienteController(ClienteService clienteSerice) {
		this.clienteService = clienteSerice;
	}

	// Criando os metodos getPost onde irá receber as requisições 
	// que serão persistidas no banco
	@PostMapping
	public ResponseEntity<Object> saveCliente(@RequestBody @Valid ClienteDto clienteDto) {
		var cliente = new Cliente();
		// método BeanUtils está sendo usado para realizar um cast de clienteDto para
		// cliente
		BeanUtils.copyProperties(clienteDto, cliente);
        cliente.setData(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveCliente(cliente));
	}
	@GetMapping
	public ResponseEntity<Page<Cliente>> getAllCliente(@PageableDefault(page = 0,
	size = 10, sort = "clienteId", direction = Sort.Direction.ASC)Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.findAll(pageable));
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Object> getOneCliente(@PathVariable(value = "clienteId") Long id){
		Optional<Cliente> clienteOptional = clienteService.findById(id);
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());
	}
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Object> deleteCliente(@PathVariable(value = "clienteId") Long id){
		Optional<Cliente> clienteOptional = clienteService.findById(id);
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado!");
		}
		clienteService.deleteCliente(clienteOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Cadastro deletado com sucesso!!!");
	}
	//para testar no postman precisa usar o Id
	@PutMapping("/{clienteId}")
	public ResponseEntity<Object> updateClinte(@PathVariable(value = "clienteId")Long id, @RequestBody @Valid ClienteDto clienteDto){
		Optional<Cliente> clienteOptional = clienteService.findById(id);
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cadastro não pode ser atualizado!!!");
		}
		var cliente = new Cliente();
		
		BeanUtils.copyProperties(clienteDto, cliente);
		cliente.setData(clienteOptional.get().getData());		
		cliente.setClienteId(clienteOptional.get().getClienteId());
		return ResponseEntity.status(HttpStatus.OK).body(clienteService.saveCliente(cliente));
	}
}
 


















