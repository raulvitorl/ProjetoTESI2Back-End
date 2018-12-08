package br.ufac.academico.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="mensagens")
//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="Mensagens.todos", 
		query="SELECT m FROM Mensagem m")
})

public class Mensagem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigo;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="men_ate_codigo")	
	private Atendente atendente;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="men_tms_codigo")	
	private TipoMensagem tipo;
	@Column(name="men_texto")
	private String texto;
	@Column(name="men_data_envio")
	private Date dataEnvio;
	
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public Atendente getAtendente() {
		return atendente;
	}
	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}
	public TipoMensagem getTipo() {
		return tipo;
	}
	public void setTipo(TipoMensagem tipo) {
		this.tipo = tipo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Date getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public Mensagem() {
		super();
	}
	@Override
	public String toString() {
		return "Mensagens [codigo=" + codigo + ", atendenteMensagem=" + atendente + ", tipoMensagem="
				+ tipo + ", texto=" + texto + ", dataEnvio=" + dataEnvio + "]";
	}
	
	
	
	
	
}
