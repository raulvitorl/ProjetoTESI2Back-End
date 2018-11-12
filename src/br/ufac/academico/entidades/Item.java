package br.ufac.academico.entidades;

import javax.persistence.*;

@Entity
public class Item {

	@Id
	private int id;
	private int quantidade;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantidate() {
		return quantidade;
	}

	public void setQuantidate(int quantidade) {
		this.quantidade = quantidade;
	}

}
