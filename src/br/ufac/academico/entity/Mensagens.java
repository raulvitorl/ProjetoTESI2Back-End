package br.ufac.academico.entity;

import javax.persistence.Entity;

@Entity
public class Mensagens {

	Atendente men_ate_codigo;
	TipoMensagens men_tms_codigo;
	String men_texto;
	String men_data_envio;
	public Atendente getMen_ate_codigo() {
		return men_ate_codigo;
	}
	public void setMen_ate_codigo(Atendente men_ate_codigo) {
		this.men_ate_codigo = men_ate_codigo;
	}
	public TipoMensagens getMen_tms_codigo() {
		return men_tms_codigo;
	}
	public void setMen_tms_codigo(TipoMensagens men_tms_codigo) {
		this.men_tms_codigo = men_tms_codigo;
	}
	public String getMen_texto() {
		return men_texto;
	}
	public void setMen_texto(String men_texto) {
		this.men_texto = men_texto;
	}
	public String getMen_data_envio() {
		return men_data_envio;
	}
	public void setMen_data_envio(String men_data_envio) {
		this.men_data_envio = men_data_envio;
	}
	public Mensagens(Atendente men_ate_codigo, TipoMensagens men_tms_codigo, String men_texto, String men_data_envio) {
		super();
		this.men_ate_codigo = men_ate_codigo;
		this.men_tms_codigo = men_tms_codigo;
		this.men_texto = men_texto;
		this.men_data_envio = men_data_envio;
	}
	public Mensagens() {
		super();
	}
	
	
	
	
}
