package br.ufac.academico.entidades;

import javax.persistence.*;

@Entity
public class Encomenda {

	@Id
	private int id;
	private String descricao;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="destino_fk")
	private Destino destino;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Destino getDestino() {
		return destino;
	}
	public void setDestino(Destino destino) {
		this.destino = destino;
	}
	
	public String toString() {
		
		return String.format("Encomenda [id=%d, descricao=\"%s\", destino=%s]", 
				id, descricao, destino);
	}
	
}
