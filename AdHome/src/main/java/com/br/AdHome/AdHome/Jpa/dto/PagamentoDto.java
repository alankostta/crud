package com.br.AdHome.AdHome.Jpa.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class PagamentoDto {
	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	@DecimalMax(value = "1000000", inclusive = false)
	private Double pagaPix;
	@NotNull
	private Double pagaCartao;
	@NotNull
	private Double pagaDinhe;
	
	public Double getPagaPix() {
		return pagaPix;
	}

	public void setPagaPix(Double pagaPix) {
		this.pagaPix = pagaPix;
	}
	public Double getPagaCartao() {
		return pagaCartao;
	}
	public void setPagaCartao(Double pagaCartao) {
		this.pagaCartao = pagaCartao;
	}
	public Double getPagaDinhe() {
		return pagaDinhe;
	}
	public void setPagaDinhe(Double pagaDinhe) {
		this.pagaDinhe = pagaDinhe;
	}
}
