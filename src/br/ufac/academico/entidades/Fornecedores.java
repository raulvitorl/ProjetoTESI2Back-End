package br.ufac.academico.entidades;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="fornecedores")
//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="Fornecedores.todos", 
		query="SELECT f FROM Fornecedores c"),
	
	@NamedQuery(name="Fornecedores.todosPorNome", 
		query="SELECT f FROM Fornecedores f ORDER BY f.for_nome_contato")
})
public class Fornecedores {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long forCodigo;
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
	
	@OneToMany(mappedBy = "pro_for_codigo", targetEntity = Produtos.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Produtos> produtos;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="for_mun_codigo")	
	private Municipios for_mun_codigo;	
	
	public long getForCodigo() {
		return forCodigo;
	}
	public void setForCodigo(int forCodigo) {
		this.forCodigo = forCodigo;
	}
	
		
	public Municipios getMunicipio() {
		return for_mun_codigo;
	}
	public void setMunicipio(Municipios municipio) {
		this.for_mun_codigo = municipio;
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
