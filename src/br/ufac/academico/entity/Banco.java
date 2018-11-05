package br.ufac.academico.entity;

import javax.persistence.Entity;

@Entity
public class Banco {

	private int codigo;
	private String nome;
	
	public Banco(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public Banco(){
		
	}

	public int getCodigo() {
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
