package com.br.AdHome.AdHome.Jpa.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.br.AdHome.AdHome.models.Produto;

/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */

public class ProdutoDto {
	@NotBlank
	@Size(max = 255)
	private String descricao;
	@NotNull
	private Double valorEntrada;
	@NotNull
	private Double valorSaida;
	@NotNull
	private Integer estoqueQtd;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getValorEntrada() {
		return valorEntrada;
	}
	public void setValorEntrada(Double valorEntrada) {
		this.valorEntrada = valorEntrada;
	}
	public Double getValorSaida() {
		return valorSaida;
	}
	public void setValorSaida(Double valorSaida) {
		this.valorSaida = valorSaida;
	}
	public Integer getEstoqueQtd() {
		return estoqueQtd;
	}
	public void setEstoqueQtd(Integer estoqueQtd) {
		this.estoqueQtd = estoqueQtd;
	}
	public Produto toProduto() {
		Produto produto = new Produto();
		produto.setDescricao(descricao);
		produto.setValorEntrada(valorEntrada);
		produto.setValorSaida(valorSaida);
		produto.setEstoqueQtd(estoqueQtd);
		return produto;
		
	}
	public Produto toProduto(Produto produto) {
		
		produto.setDescricao(descricao);
		produto.setValorEntrada(valorEntrada);
		produto.setValorSaida(valorSaida);
		produto.setEstoqueQtd(estoqueQtd);
		return produto;
		
	}
	public void fromProduto(Produto produto) {
		
		this.descricao = produto.getDescricao();
		this.valorEntrada = produto.getValorEntrada();
		this.valorSaida = produto.getValorSaida();
		this.estoqueQtd = produto.getEstoqueQtd();
	}
}
