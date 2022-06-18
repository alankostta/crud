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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pedido_id", nullable = false, length = 10)
	private Long pedidoId;
	@Column(name = "data_pedido",nullable = false)
	private LocalDateTime dataPedido;
	@Column(name = "qtd_itens",nullable = false)
	private Integer qtdItens;
	
	@OneToOne(mappedBy = "pedido",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Pagamento pagamanto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id",nullable = true)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Produto> produto;
	
	public Pedido() {
		
	}
	public Pedido(LocalDateTime dataPedido, Integer qtdItens, Cliente cliente) {
		super();
		this.setDataPedido(dataPedido);
		this.setQtdItens(qtdItens);
	}
	@Override
	public int hashCode() {
		return Objects.hash(dataPedido, pedidoId, qtdItens);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(dataPedido, other.dataPedido) && Objects.equals(pedidoId, other.pedidoId)
				&& Objects.equals(qtdItens, other.qtdItens);
	}
	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	public LocalDateTime getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(LocalDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Integer getQtdItens() {
		return qtdItens;
	}
	public void setQtdItens(Integer qtdItens) {
		this.qtdItens = qtdItens;
	}
	public Set<Produto> getProduto() {
		return produto;
	}
	public void setProduto(Set<Produto> produto) {
		this.produto = produto;
	}
	public Pagamento getPagamanto() {
		return pagamanto;
	}
	public void setPagamanto(Pagamento pagamanto) {
		this.pagamanto = pagamanto;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return "Pedido [pedidoId=" + pedidoId + ", dataPedido=" + dataPedido + ", qtdItens=" + qtdItens + ", pagamanto="
				+ pagamanto + ", cliente=" + cliente + ", produto=" + produto + "]";
	}
	
}
