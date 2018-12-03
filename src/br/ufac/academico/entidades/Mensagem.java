package br.ufac.academico.entidades;

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
	private Atendente atendenteMensagem;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="men_tms_codigo")	
	private TipoMensagem tipoMensagem;
	
	@Column(name="men_texto")
	private String texto;
	@Column(name="men_data_envio")
	private String dataEnvio;
	public Atendente getMen_ate_codigo() {
		return atendenteMensagem;
	}
	public void setMen_ate_codigo(Atendente men_ate_codigo) {
		this.atendenteMensagem = men_ate_codigo;
	}

	
	public Atendente getAtendenteMensagem() {
		return atendenteMensagem;
	}
	public void setAtendenteMensagem(Atendente atendenteMensagem) {
		this.atendenteMensagem = atendenteMensagem;
	}
	public TipoMensagem getTipoMensagem() {
		return tipoMensagem;
	}
	public void setTipoMensagem(TipoMensagem tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}
	
	public Mensagem() {
		super();
	}
	@Override
	public String toString() {
		return "Mensagens [codigo=" + codigo + ", atendenteMensagem=" + atendenteMensagem + ", tipoMensagem="
				+ tipoMensagem + ", texto=" + texto + ", dataEnvio=" + dataEnvio + "]";
	}
	
	
	
	
	
}
