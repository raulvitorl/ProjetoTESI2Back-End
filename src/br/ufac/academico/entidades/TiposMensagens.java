package br.ufac.academico.entidades;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tipo_mensagens")
//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="TiposMensagens.todos", 
		query="SELECT tms FROM TiposMensagens tms"),
	
	@NamedQuery(name="TiposMensagens.todosPorDescricao", 
		query="SELECT tms FROM TiposMensagens tms ORDER BY tms.tmsdescricao")
})
public class TiposMensagens {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long tmsCodigo;
	@Column(name="tmsdescricao")
	private String tmsDescricao;
	
	@OneToMany(mappedBy = "men_tms_codigo", targetEntity = Mensagens.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Mensagens> mensagens;
	
public TiposMensagens(){
		
	}
	
	public TiposMensagens(int tmsCodigo, String tmsDescricao) {
		super();
		this.tmsCodigo = tmsCodigo;
		this.tmsDescricao = tmsDescricao;
	}
	
	public long getTmsCodigo() {
		return tmsCodigo;
	}
	public void setTmsCodigo(int tmsCodigo) {
		this.tmsCodigo = tmsCodigo;
	}
	public String getTmsDescricao() {
		return tmsDescricao;
	}
	public void setTmsDescricao(String tmsDescricao) {
		this.tmsDescricao = tmsDescricao;
	}

	
	
	
}
