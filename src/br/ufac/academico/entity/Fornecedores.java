package br.ufac.academico.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fornecedores")
public class Fornecedores {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long forCodigo;
	@Column(name="for_mun_codigo")
	private Municipios municipio;
	@Column(name="for_razao_social")
	private String forRazaoSocial;
	@Column(name="for_nome_contato")
	private	String forNomeContato;
	@Column(name="for_nome_fantasia")
	private String forNomeFantasia;
	@Column(name="for_cnpj")
	private String forCnpj;
	@Column(name="for_endereco")
	private String forEndereco;
	@Column(name="for_data_cadastro")
	private String forDataCadastro;
	@Column(name="for_fone")
	private String forFone;
	@Column(name="for_email")
	private String forEmail;
	@Column(name="for_website")
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
	public long getForCodigo() {
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
