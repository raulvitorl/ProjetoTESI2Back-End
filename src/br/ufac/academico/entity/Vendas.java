package br.ufac.academico.entity;

import javax.persistence.Entity;

@Entity
public class Vendas {
	
	private int venCodigo;
	private Cliente cliente;
	private Atendente atendente;
	private Banco banco;
	private float venValorTotal;
	private String venFormaPagamento;
	private String venObservacoes;
		
	
	public Vendas(){
		
	}
	
	
	public Vendas(int venCodigo, Cliente cliente, Atendente atendente, Banco banco, float venValorTotal,
			String venFormaPagamento, String venObservacoes) {
		super();
		this.venCodigo = venCodigo;
		this.cliente = cliente;
		this.atendente = atendente;
		this.banco = banco;
		this.venValorTotal = venValorTotal;
		this.venFormaPagamento = venFormaPagamento;
		this.venObservacoes = venObservacoes;
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
	public int getVenCodigo() {
		return venCodigo;
	}
	public void setVenCodigo(int venCodigo) {
		this.venCodigo = venCodigo;
	}
	public float getVenValorTotal() {
		return venValorTotal;
	}
	public void setVenValorTotal(float venValorTotal) {
		this.venValorTotal = venValorTotal;
	}
	public String getVenFormaPagamento() {
		return venFormaPagamento;
	}
	public void setVenFormaPagamento(String venFormaPagamento) {
		this.venFormaPagamento = venFormaPagamento;
	}
	public String getVenObservacoes() {
		return venObservacoes;
	}
	public void setVenObservacoes(String venObservacoes) {
		this.venObservacoes = venObservacoes;
	}
	
	
	
	
	
	

}
