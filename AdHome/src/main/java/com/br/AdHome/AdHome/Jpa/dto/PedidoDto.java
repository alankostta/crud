package com.br.AdHome.AdHome.Jpa.dto;

import javax.validation.constraints.NotNull;
/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class PedidoDto {
	
	@NotNull
	private Integer qtdItens;
	
	public Integer getQtdItens() {
		return qtdItens;
	}
	public void setQtdItensDto(Integer qtdItens) {
		this.qtdItens = qtdItens;
	}
}
