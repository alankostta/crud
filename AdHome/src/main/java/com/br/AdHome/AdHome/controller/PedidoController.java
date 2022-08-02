package com.br.AdHome.AdHome.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.AdHome.AdHome.Jpa.dto.ClienteDto;
import com.br.AdHome.AdHome.Jpa.dto.PedidoDto;
import com.br.AdHome.AdHome.Jpa.dto.ProdutoDto;
import com.br.AdHome.AdHome.models.Cliente;
import com.br.AdHome.AdHome.models.Endereco;
import com.br.AdHome.AdHome.models.Pedido;
import com.br.AdHome.AdHome.models.Produto;
import com.br.AdHome.AdHome.services.ClienteService;
import com.br.AdHome.AdHome.services.PedidoService;
import com.br.AdHome.AdHome.services.ProdutoService;

/*
 * Controlador: responder interações do usuśrio
 * No caso de uma API REST "interações" são as requisições.
 * Passos das requisições que serão feitas pelo usúario
 * 1º controller envia solicitação para o service
 * 2º service envia a solicitação para o repository
 * 3º repository envia para o banco
 */
@Controller
public class PedidoController {
	
	final PedidoService pedidoService;
	final ProdutoService produtoService;
	final ClienteService clienteService;
	
	public PedidoController(PedidoService pedidoService, ProdutoService produtoService, ClienteService clienteService) {
		this.pedidoService = pedidoService;
		this.produtoService = produtoService;
		this.clienteService = clienteService;
	}
	@GetMapping("/produto")
	public ModelAndView exibirPedido(ProdutoDto produtoDto, PedidoDto pedidoDto, ClienteDto clienteDto) {
		
		var mv = new ModelAndView("pedido/pedido");
		return mv;
	}
	// Criando os metodos getPost onde irá receber as requisições
	// que serão persistidas no banco
	@PostMapping("/pedido")
	public ModelAndView savePedido(@Valid ClienteDto clienteDto, BindingResult resultCliente,
			@Valid PedidoDto pedidoDto, BindingResult resultPedido,
			@Valid ProdutoDto produtoDto, BindingResult resultProduto ) {
		
		ModelAndView mv = new ModelAndView("pedido/pedido");
		
		
		if (resultCliente.hasErrors()&& resultPedido.hasErrors()&& resultProduto.hasErrors()) {
			
			this.retornaErroPedido("ERRO AO SALVAR: esse cadastro!, verifique se não há compos vazios");
			return mv;
		}
		else {
				Pedido pedido = pedidoDto.toPedido();
				Produto produto = produtoDto.toProduto();
				pedido.setDataPedido(LocalDateTime.now(ZoneId.of("UTC")));
				Set<Produto> produtos = new HashSet<Produto>();
				produtos.add(produto);
				pedido.setProduto(produtos);
				pedidoService.savePedido(pedido);
				
				return new ModelAndView("redirect:/cliente/listar");
		}
		// método BeanUtils está sendo usado para realizar um cast de clienteDto para
		// cliente
	}
	private ModelAndView retornaErroPedido(String msg) {
		ModelAndView mv = new ModelAndView("redirect:/cliente/listar");
		mv.addObject("mensagem", msg);
		mv.addObject("erro", true);
		return mv;
	}
}
