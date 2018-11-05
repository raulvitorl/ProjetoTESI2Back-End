package br.ufac.academico.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vendas {
	
	@Id
	private long ven_codigo;
	private Cliente ven_cli_codigo;
	private Atendente ven_ate_codigo;
	private Banco ven_ban_codigo;
	private float ven_valor_total;
	private String ven_forma_pagamento;
	private String ven_observacoes;
	private Produtos ven_pro_codigo;
	
	public Vendas(){
		
	}
	
	
	public Vendas(int venCodigo, Cliente cliente, Atendente atendente, Banco banco, float venValorTotal,
			String venFormaPagamento, String venObservacoes) {
		super();
		this.ven_codigo = venCodigo;
		this.ven_cli_codigo = cliente;
		this.ven_ate_codigo = atendente;
		this.ven_ban_codigo = banco;
		this.ven_valor_total = venValorTotal;
		this.ven_forma_pagamento = venFormaPagamento;
		this.ven_observacoes = venObservacoes;
	}
	public Cliente getCliente() {
		return ven_cli_codigo;
	}
	public void setCliente(Cliente cliente) {
		this.ven_cli_codigo = cliente;
	}
	public Atendente getAtendente() {
		return ven_ate_codigo;
	}
	public void setAtendente(Atendente atendente) {
		this.ven_ate_codigo = atendente;
	}
	public Banco getBanco() {
		return ven_ban_codigo;
	}
	public void setBanco(Banco banco) {
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
