package br.ufac.academico.entity;

public class Municipios {


private int munCodigo;
private String munNome;
private String munUfEstado;
private String  munCep;


public Municipios(){
	
}


public Municipios(int munCodigo, String munNome, String munUfEstado, String munCep) {
	super();
	this.munCodigo = munCodigo;
	this.munNome = munNome;
	this.munUfEstado = munUfEstado;
	this.munCep = munCep;
}

public int getMunCodigo() {
	return munCodigo;
}
public void setMunCodigo(int munCodigo) {
	this.munCodigo = munCodigo;
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