package com.br.AdHome.AdHome.Jpa.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */

public class FornecedorDto {
	@NotBlank
	@Size(max = 60)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
