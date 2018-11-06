package br.ufac.academico.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_mensagens")
public class TiposMensagens {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long tmsCodigo;
	@Column(name="tmsdescricao")
	private String tmsDescricao;
	
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
