package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "tb_cliente")
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_id",nullable = false,  length = 10)
	private Long clienteId;
	@Column(name = "nome_clie",nullable = false,  length = 70)
	private String nome;
	@Column(name = "sexo_clie",nullable = false,  length = 2)
	private String sexo;
	@Column(name = "dataNasci_clie",nullable = false, length = 15)
	private String dataNasci;
	@Column(name = "data",nullable = false, length = 30)
	private LocalDateTime data;
	
	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Contato> contato;
	
	@ManyToMany
	@JoinTable(
			name = "Cliente_Endereco",
			joinColumns = @JoinColumn(name = "cliente_fk", nullable = true),
			inverseJoinColumns = @JoinColumn(name = "endereco_fk", nullable = true))
	private Set<Endereco> endereco;

	@OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Pedido> pedido;
	
	public Cliente() {
		
	}
	public Cliente(String nome, String sexo, String dataNasci, LocalDateTime data) {
		super();
		this.setNome(nome);
		this.setSexo(sexo);
		this.setDataNasci(dataNasci);
		this.setData(data);

	}
	@Override
	public int hashCode() {
		return Objects.hash(clienteId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(clienteId, other.clienteId);
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
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
	public Set<Contato> getContato() {
		return contato;
	}
	public void setContato(Set<Contato> contato) {
		this.contato = contato;
	}
	public Set<Endereco> getEndereco() {
		return endereco;
	}
	public void setEndereco(Set<Endereco> endereco) {
		this.endereco = endereco;
	}
	public Set<Pedido> getPedido() {
		return pedido;
	}
	public void setPedido(Set<Pedido> pedido) {
		this.pedido = pedido;
	}
	@Override
	public String toString() {
		return "Cliente [clienteId=" + clienteId + ", nome=" + nome + ", sexo=" + sexo + ", dataNasci=" + dataNasci
				+ ", data=" + data + ", contato=" + contato + ", endereco=" + endereco + ", pedido=" + pedido + "]";
	}
	/*
	@PreRemove
	public void updateOnDeleteContato() {
		for(Contato contato: this.getContato()) {
			contato.setCliente(null);
		}
	}
	@PreRemove
	public void updateOndeDeleteEndereco() {
		for(Endereco endereco: this.getEndereco()) {
			endereco.setCliente(null);
		}
	}
	@PreRemove
	public void updateOnDeletePedido() {
		for(Pedido pedido: this.getPedido()) {
			pedido.setCliente(null);
		}
	}
	*/
}
