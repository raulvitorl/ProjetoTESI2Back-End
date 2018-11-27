package br.ufac.academico.entidades;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="municipios")

//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="Municipios.todos", 
		query="SELECT m FROM Municipios m"),
	
	@NamedQuery(name="Municipios.todosPorNome", 
		query="SELECT m FROM Municipios m ORDER BY m.munNome")
})
public class Municipios {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private long mun_codigo;
	@Column(name="mun_nome")	
private String munNome;
	@Column(name="mun_uf_estado")
private String munUfEstado;
	@Column(name="mun_cep")
private String  munCep;

@OneToMany(mappedBy = "for_mun_codigo", targetEntity = Fornecedores.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
private Collection<Fornecedores> fornecedores;
@OneToMany(mappedBy = "cli_mun_codigo", targetEntity = Clientes.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
private Collection<Clientes> clientes;
public Municipios(){}

public long getMunCodigo() {return mun_codigo;}

public void setMunCodigo(int munCodigo) {this.mun_codigo = munCodigo;}

public String getMunNome() {return munNome;}

public void setMunNome(String munNome) {this.munNome = munNome;}

public String getMunUfEstado(){return munUfEstado;}

public void setMunUfEstado(String munUfEstado) {this.munUfEstado = munUfEstado;}

public String getMunCep() {return munCep;}

public void setMunCep(String munCep) {this.munCep = munCep;}}