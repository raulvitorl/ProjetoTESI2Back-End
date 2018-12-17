package br.ufac.academico.domain;

import java.util.List;

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

import br.ufac.academico.domain.enums.FormaPagamento;
import br.ufac.academico.domain.enums.StatusPagamento;

@Entity
@Table(name="vendas")
//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="Vendas.todos", 
		query="SELECT v FROM Venda v"),
	
	@NamedQuery(name="Vendas.todosPorCliente", 
	query="SELECT v FROM Venda v WHERE :termo=v.cliente.codigo"),
})

public class Venda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="ven_cli_codigo")	
	private Cliente cliente;
	
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="ven_ate_codigo")	
	private Atendente atendente;
	
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="ven_ban_codigo")	
	private Banco banco;
	
	@Column(name="ven_valor_total")
	private float valorTotal;
	
	@Column(name="ven_forma_pagamento")
	private Integer formaPagamento;
	
	private Integer statusPagamento;
	
	@Column(name="ven_observacoes")
	private String observacoes;
	
	@ManyToMany(cascade={CascadeType.MERGE})
	@JoinColumn(name="produtosVenda")
	private List<Produto> produtos;
	
	public Venda(){
		
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
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

	public void setValorTotal(float d) {
		this.valorTotal = d;
	}

	public FormaPagamento getFormaPagamento() {
		return FormaPagamento.toEnum(formaPagamento);
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento.getCod();
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
	public StatusPagamento getStatusPagamento() {
		return StatusPagamento.toEnum(statusPagamento);
	}

	public void setStatusPagamento(StatusPagamento status) {
		this.statusPagamento = status.getCod();
	}

	@Override
	public String toString() {
		return "Venda [codigo=" + codigo + ", cliente=" + cliente + ", atendente=" + atendente + ", banco=" + banco
				+ ", valorTotal=" + valorTotal + ", formaPagamento=" + formaPagamento + ", statusPagamento="
				+ statusPagamento + ", observacoes=" + observacoes + "]";
	}


	
	
	
	
	

}
