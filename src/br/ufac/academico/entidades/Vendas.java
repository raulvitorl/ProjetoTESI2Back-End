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
import javax.persistence.Table;

@Entity
@Table(name="vendas")
public class Vendas {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long ven_codigo;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ven_cli_codigo")	
	private Clientes ven_cli_codigo;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ven_ate_codigo")	
	private Atendentes ven_ate_codigo;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="ven_ban_codigo")	
	private Bancos ven_ban_codigo;
	
	@Column(name="ven_valor_total")
	private float ven_valor_total;
	
	@Column(name="ven_forma_pagamento")
	private String ven_forma_pagamento;
	
	@Column(name="ven_observacoes")
	private String ven_observacoes;
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinColumn(name="ven_pro_codigo")
	private Collection<Produtos> ven_pro_codigo;;
	
	public Vendas(){
		
	}
	
	
	public Vendas(int venCodigo, Clientes cliente, Atendentes atendente, Bancos banco, float venValorTotal,
			String venFormaPagamento, String venObservacoes) {
		super();
		this.ven_codigo = venCodigo;
		//this.ven_cli_codigo = cliente;
		this.ven_ate_codigo = atendente;
		this.ven_ban_codigo = banco;
		this.ven_valor_total = venValorTotal;
		this.ven_forma_pagamento = venFormaPagamento;
		this.ven_observacoes = venObservacoes;
	}
	public Clientes getCliente() {
		return ven_cli_codigo;
	}
	public void setCliente(Clientes cliente) {
		this.ven_cli_codigo = cliente;
	}
	public Atendentes getAtendente() {
		return ven_ate_codigo;
	}
	public void setAtendente(Atendentes atendente) {
		this.ven_ate_codigo = atendente;
	}
	public Bancos getBanco() {
		return ven_ban_codigo;
	}
	public void setBanco(Bancos banco) {
		this.ven_ban_codigo = banco;
	}
	public long getVenCodigo() {
		return ven_codigo;
	}
	public void setVenCodigo(int venCodigo) {
		this.ven_codigo = venCodigo;
	}
	public float getVenValorTotal() {
		return ven_valor_total;
	}
	public void setVenValorTotal(float venValorTotal) {
		this.ven_valor_total = venValorTotal;
	}
	public String getVenFormaPagamento() {
		return ven_forma_pagamento;
	}
	public void setVenFormaPagamento(String venFormaPagamento) {
		this.ven_forma_pagamento = venFormaPagamento;
	}
	public String getVenObservacoes() {
		return ven_observacoes;
	}
	public void setVenObservacoes(String venObservacoes) {
		this.ven_observacoes = venObservacoes;
	}
	
	
	
	
	
	

}
