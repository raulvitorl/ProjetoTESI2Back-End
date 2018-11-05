package br.ufac.academico.entity;

import javax.persistence.Entity;

@Entity
public class Fornecedores {
	
	private int forCodigo;
	private Municipios municipio;
	private String forRazaoSocial;
	private	String forNomeContato;
	private String forNomeFantasia;
	private String forCnpj;
	private String forEndereco;
	private String forDataCadastro;
	private String forFone;
	private String forEmail;
	private String forWebSite;
	
	
	public Fornecedores(){
		
	}
	
	
	
	public Fornecedores(int forCodigo, Municipios municipio, String forRazaoSocial, String forNomeContato,
			String forNomeFantasia, String forCnpj, String forEndereco, String forDataCadastro, String forFone,
			String forEmail, String forWebSite) {
		super();
		this.forCodigo = forCodigo;
		this.municipio = municipio;
		this.forRazaoSocial = forRazaoSocial;
		this.forNomeContato = forNomeContato;
		this.forNomeFantasia = forNomeFantasia;
		this.forCnpj = forCnpj;
		this.forEndereco = forEndereco;
		this.forDataCadastro = forDataCadastro;
		this.forFone = forFone;
		this.forEmail = forEmail;
		this.forWebSite = forWebSite;
	}
	public int getForCodigo() {
		return forCodigo;
	}
	public void setForCodigo(int forCodigo) {
		this.forCodigo = forCodigo;
	}
	
		
	public Municipios getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipios municipio) {
		this.municipio = municipio;
	}
	public String getForRazaoSocial() {
		return forRazaoSocial;
	}
	public void setForRazaoSocial(String forRazaoSocial) {
		this.forRazaoSocial = forRazaoSocial;
	}
	public String getForNomeContato() {
		return forNomeContato;
	}
	public void setForNomeContato(String forNomeContato) {
		this.forNomeContato = forNomeContato;
	}
	public String getForNomeFantasia() {
		return forNomeFantasia;
	}
	public void setForNomeFantasia(String forNomeFantasia) {
		this.forNomeFantasia = forNomeFantasia;
	}
	public String getForCnpj() {
		return forCnpj;
	}
	public void setForCnpj(String forCnpj) {
		this.forCnpj = forCnpj;
	}
	public String getForEndereco() {
		return forEndereco;
	}
	public void setForEndereco(String forEndereco) {
		this.forEndereco = forEndereco;
	}
	public String getForDataCadastro() {
		return forDataCadastro;
	}
	public void setForDataCadastro(String forDataCadastro) {
		this.forDataCadastro = forDataCadastro;
	}
	public String getForFone() {
		return forFone;
	}
	public void setForFone(String forFone) {
		this.forFone = forFone;
	}
	public String getForEmail() {
		return forEmail;
	}
	public void setForEmail(String forEmail) {
		this.forEmail = forEmail;
	}
	public String getForWebSite() {
		return forWebSite;
	}
	public void setForWebSite(String forWebSite) {
		this.forWebSite = forWebSite;
	}
	
	
	
	






}
