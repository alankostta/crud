package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_fornecedor")
public class Fornecedor implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fornecedor_id",nullable = false, length = 10)
	private Long fornecedorId;
	@Column(name = "nome_fornecedor",nullable = false, length = 60)
	private String nome;
	
	@ManyToMany
	@JoinTable(
			name = "Fornecedor_Endereco",
			joinColumns = @JoinColumn(name = "fornecedor_fk"),
			inverseJoinColumns = @JoinColumn(name = "endereco_fk"))
	private Set<Endereco> endereco;
	/*
	 * Quando e criado um Set<> ele ao invés de criar uma lista de objetos
	 * ele cria um grupo unico se objetos evitando ser criado varias instancias 
	 * ao mesmo objeto
	 */
	@OneToMany(mappedBy = "fornecedor",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Contato> contatos;
	
	@OneToMany(mappedBy = "fornecedor",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Produto> produtos;
	
	public Fornecedor() {
		
	}
	public Fornecedor(String nome) {
		super();
		this.setNome(nome);
	}
	@Override
	public int hashCode() {
		return Objects.hash(fornecedorId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		return Objects.equals(fornecedorId, other.fornecedorId);
	}
	public Long getFornecedorId() {
		return fornecedorId;
	}
	public void setFornecedorId(Long fornecedorId) {
		this.fornecedorId = fornecedorId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Set<Endereco> getEndereco() {
		return endereco;
	}
	public void setEndereco(Set<Endereco> endereco) {
		this.endereco = endereco;
	}
	public Set<Contato> getContatos() {
		return contatos;
	}
	public void setContatos(Set<Contato> contatos) {
		this.contatos = contatos;
	}
	public Set<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}
	@Override
	public String toString() {
		return "Fornecedor [fornecedorId=" + fornecedorId + ", nome=" + nome + ", endereco=" + endereco + ", contatos="
				+ contatos + ", produtos=" + produtos + "]";
	}
	
}
