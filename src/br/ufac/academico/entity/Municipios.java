package br.ufac.academico.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="municipios")
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


public Municipios(){
	
}


public Municipios(int munCodigo, String munNome, String munUfEstado, String munCep) {
	super();
	this.mun_codigo = munCodigo;
	this.munNome = munNome;
	this.munUfEstado = munUfEstado;
	this.munCep = munCep;
}

public long getMunCodigo() {
	return mun_codigo;
}
public void setMunCodigo(int munCodigo) {
	this.mun_codigo = munCodigo;
}
public String getMunNome() {
	return munNome;
}
public void setMunNome(String munNome) {
	this.munNome = munNome;
}
public String getMunUfEstado() {
	return munUfEstado;
}
public void setMunUfEstado(String munUfEstado) {
	this.munUfEstado = munUfEstado;
}
public String getMunCep() {
	return munCep;
}
public void setMunCep(String munCep) {
	this.munCep = munCep;
}











}