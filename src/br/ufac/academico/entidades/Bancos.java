package br.ufac.academico.entidades;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bancos")
public class Bancos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long codigo;
	@Column(name="ban_nome")
	private String nome;
	
	@OneToMany(mappedBy = "ven_ban_codigo", targetEntity = Vendas.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Vendas> vendas;
	
	public Bancos(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public Bancos(){
		
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return nome; 
	}
	
}
