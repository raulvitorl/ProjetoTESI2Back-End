package br.ufac.academico.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoMensagens {
	
	@Id
	private long tmsCodigo;
	private String tmsDescricao;
	
public TipoMensagens(){
		
	}
	
	public TipoMensagens(int tmsCodigo, String tmsDescricao) {
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
