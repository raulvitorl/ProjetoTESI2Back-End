package br.ufac.academico.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mensagens")
public class Mensagens {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long men_codigo;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="men_ate_codigo")	
	private Atendentes men_ate_codigo;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="men_tms_codigo")	
	private TiposMensagens men_tms_codigo;
	
	@Column(name="men_texto")
	String men_texto;
	@Column(name="men_data_envio")
	String men_data_envio;
	public Atendentes getMen_ate_codigo() {
		return men_ate_codigo;
	}
	public void setMen_ate_codigo(Atendentes men_ate_codigo) {
		this.men_ate_codigo = men_ate_codigo;
	}
	public TiposMensagens getMen_tms_codigo() {
		return men_tms_codigo;
	}
	public void setMen_tms_codigo(TiposMensagens men_tms_codigo) {
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
	
	
	public long getMen_codigo() {
		return men_codigo;
	}
	public void setMen_codigo(long men_codigo) {
		this.men_codigo = men_codigo;
	}
	
	
	
	public Mensagens(long men_codigo, Atendentes men_ate_codigo, TiposMensagens men_tms_codigo, String men_texto,
			String men_data_envio) {
		super();
		this.men_codigo = men_codigo;
		this.men_ate_codigo = men_ate_codigo;
		this.men_tms_codigo = men_tms_codigo;
		this.men_texto = men_texto;
		this.men_data_envio = men_data_envio;
	}
	public Mensagens() {
		super();
	}
	
	
	
	
}
