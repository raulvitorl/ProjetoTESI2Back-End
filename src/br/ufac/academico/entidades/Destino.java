package br.ufac.academico.entidades;

import javax.persistence.*;

@Entity
public class Destino {
	
	@Id
	private int id;
	private String cidade;
	private String cep;
	
	@OneToOne(mappedBy="destino")
	private Encomenda encomenda;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public Encomenda getEncomenda() {
		return encomenda;
	}
	public void setEncomenda(Encomenda encomenda) {
		this.encomenda = encomenda;
	}
	public String toString() {

		return String.format("Destino [id=%d, cidade=\"%s\", cep=\"%s\"]", 
				id, cidade, cep);
	}

}
