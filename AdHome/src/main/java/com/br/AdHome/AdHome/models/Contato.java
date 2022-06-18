package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_contato")
public class Contato implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "contato_id", nullable = false, length = 10)
	private Long contatoId;
	@Column(name = "tele_contato", nullable = false, length = 30)
	private String telefone;
	@Column(name = "email_contato", nullable = true, length = 150)
	private String email;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id", nullable = true)
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fornecedor_id", nullable = true)
	private Fornecedor fornecedor;
	
	public Contato() {
		
	}
	public Contato(String telefone, String email, Cliente cliente, Fornecedor fornecedor) {
		super();
		this.setTelefone(telefone);
		this.setEmail(email);
		
	}
	@Override
	public int hashCode() {
		return Objects.hash(contatoId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		return Objects.equals(contatoId, other.contatoId);
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getContatoId() {
		return contatoId;
	}
	public void setContatoId(Long contatoId) {
		this.contatoId = contatoId;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	@Override
	public String toString() {
		return "Contato [contatoId=" + contatoId + ", telefone=" + telefone + ", email=" + email + ", cliente="
				+ cliente + ", fornecedor=" + fornecedor + "]";
	}
	
}
