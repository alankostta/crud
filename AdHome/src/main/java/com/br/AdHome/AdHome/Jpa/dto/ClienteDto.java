package com.br.AdHome.AdHome.Jpa.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/*Classe responsável por validações de campos que 
 * receberão os dados de entrada
 * dos usúarios tipos de validação{campos vazios ou nulos, limita o campo 
 * onde serão introduzidos os dados entre outras anotações como @email @Cpf @NotNull @Empty}
 */
public class ClienteDto{
	
	@NotBlank
	@Size(max = 60)
	private String nome;
	@NotBlank
	@Size(max = 2)
	private String sexo;
	@NotBlank
	@Size(max = 15)
	private String dataNasci;
	
	public String getNome() {
		return nome;
	}
	public void setNomeDto(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDataNasci() {
		return dataNasci;
	}
	public void setDataNasci(String dataNasci) {
		this.dataNasci = dataNasci;
	}
}
