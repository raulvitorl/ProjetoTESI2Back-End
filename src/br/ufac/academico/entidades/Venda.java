package br.ufac.academico.entidades;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="vendas")
//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="Vendas.todos", 
		query="SELECT v FROM Venda v")
})

public class Venda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigo;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ven_cli_codigo")	
	private Cliente cliente;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ven_ate_codigo")	
	private Atendente atendente;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ven_ban_codigo")	
	private Banco banco;
	
	@Column(name="ven_valor_total")
	private float valorTotal;
	
	@Column(name="ven_forma_pagamento")
	private String formaPagamento;
	
	private Integer statusPagamento;
	
	@Column(name="ven_observacoes")
	private String observacoes;
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="produtosVenda")
	private Collection<Produto> produtos;
	
	public Venda(){
		
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Atendente getAtendente() {
		return atendente;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Collection<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Collection<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
	public Integer getStatusPagamento() {
		return statusPagamento;
	}

	public void setStatusPagamento(Integer statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

	@Override
	public String toString() {
		return "Venda [codigo=" + codigo + ", cliente=" + cliente + ", atendente=" + atendente + ", banco=" + banco
				+ ", valorTotal=" + valorTotal + ", formaPagamento=" + formaPagamento + ", statusPagamento="
				+ statusPagamento + ", observacoes=" + observacoes + "]";
	}


	
	
	
	
	

}
