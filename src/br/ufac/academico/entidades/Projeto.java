package br.ufac.academico.entidades;

import java.util.*;
import javax.persistence.*;

@Entity
public class Projeto {

	@Id
	private int id;
	private String descricao;
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="PROJETO_COLABORADOR", 
		joinColumns={@JoinColumn(name="projeto_id")},
		inverseJoinColumns={@JoinColumn(name="colaborador_id")}
	)
	private List<Colaborador> colaboradores = 
			new ArrayList<Colaborador>();
	
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
	
	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}
	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}
	
	public void addColaborador(Colaborador c) {
		colaboradores.add(c);
	}
	
	public void delColaborador(Colaborador c) {
		colaboradores.remove(c);
	}
}
