package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "tb_produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "produto_id",nullable = false, length = 10)
	private Long produtoId;
	@Column(name = "descricao",nullable = false, length = 255)
	private String descricao;
	@Column(name = "valor_Entrada",nullable = false)
	private Double valorEntrada;
	@Column(name = "valor_saida", nullable = false)
	private Double valorSaida;
	@Column(name = "Qtd_estoque", nullable = false)
	private Integer estoqueQtd;
	@Column(name = "data_produto", nullable = false, length = 30)
	private LocalDateTime dataProduto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "pedido_id", nullable = true)
	private Pedido pedido;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fornecedor_id", nullable = true)
	private Fornecedor fornecedor;
	
	public Produto() {
		
	}
	public Produto(String descricao, Double valorEntrada, Double valorSaida, Integer estoqueQtd, LocalDateTime dataProduto) {
		super();
		this.setDescricao(descricao);
		this.setValorEntrada(valorEntrada);
		this.setValorSaida(valorSaida);
		this.setEstoqueQtd(estoqueQtd);
		this.setDataProduto(dataProduto);
	}
	@Override
	public int hashCode() {
		return Objects.hash(dataProduto, descricao, estoqueQtd, produtoId, valorEntrada, valorSaida);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(dataProduto, other.dataProduto) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(estoqueQtd, other.estoqueQtd) && Objects.equals(produtoId, other.produtoId)
				&& Objects.equals(valorEntrada, other.valorEntrada) && Objects.equals(valorSaida, other.valorSaida);
	}
	public Long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId= produtoId;
	}
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
	public LocalDateTime getDataProduto() {
		return dataProduto;
	}
	public void setDataProduto(LocalDateTime dataProduto) {
		this.dataProduto = dataProduto;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Fornecedor getFeronecedor() {
		return fornecedor;
	}
	public void setFeronecedor(Fornecedor feronecedor) {
		this.fornecedor = feronecedor;
	}
	@Override
	public String toString() {
		return "Produto [produtoId=" + produtoId + ", descricao=" + descricao + ", valorEntrada=" + valorEntrada
				+ ", valorSaida=" + valorSaida + ", estoqueQtd=" + estoqueQtd + ", dataProduto=" + dataProduto
				+ ", pedido=" + pedido + ", feronecedor=" + fornecedor + "]";
	}
	
}
