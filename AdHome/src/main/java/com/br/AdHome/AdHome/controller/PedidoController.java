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
import com.br.AdHome.AdHome.Jpa.dto.PedidoDto;
import com.br.AdHome.AdHome.models.Pedido;
import com.br.AdHome.AdHome.services.PedidoService;

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
@RequestMapping("/adhome/pedido")
public class PedidoController {
	final PedidoService pedidoService;
	public PedidoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	@PostMapping
	public ResponseEntity <Object> salavarPedido(@RequestBody @Valid PedidoDto pedidoDto){
		var pedido = new Pedido();
		pedido.setDataPedido(LocalDateTime.now(ZoneId.of("UTC")));
		BeanUtils.copyProperties(pedidoDto, pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.savePedido(pedido));
	}
	@GetMapping
	public ResponseEntity <Page<Pedido>> getAllPagamentos(@PageableDefault(page = 0,
	size = 10, direction = Sort.Direction.ASC)Pageable pageable){
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.findAll(pageable));
		
	}
	@GetMapping("/{pedidoId}")
	public ResponseEntity <Object> getOnePedidos(@PathVariable(value = "pedidoId")Long id){
		Optional<Pedido> pedidoOptional = pedidoService.findById(id);
		if(!pedidoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O pedido não foi localizado");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoOptional.get());
	}
	@DeleteMapping("/{pedidoId}")
	public ResponseEntity <Object> deletePedido(@PathVariable(value = "pedidoId")Long id){
		Optional<Pedido> pedidoOptional = pedidoService.findById(id);
		if(!pedidoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O pedido não pode ser deletado!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoOptional.get());
	}
	@PutMapping("/{pedidoId}")
	public ResponseEntity <Object> updatePedido(@PathVariable(value = "pedidoId")Long id,
			@RequestBody @Valid PedidoDto pedidoDto){
		Optional<Pedido> pedidoOptional = pedidoService.findById(id);
		if(!pedidoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento não encontrado para atualização!");
		}
		var pedido = new Pedido();
		pedido.setDataPedido(pedidoOptional.get().getDataPedido());
		pedido.setPedidoId(pedidoOptional.get().getPedidoId());
		BeanUtils.copyProperties(pedidoDto, pedido);
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.savePedido(pedido));
	}
}
