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
		query="SELECT f FROM Fornecedor f"),
	
	@NamedQuery(name="Fornecedores.todosPorNome", 
		query="SELECT f FROM Fornecedor f ORDER BY f.NomeContato")
})
public class Fornecedor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigo;
	@Column(name="for_razao_social")
	private String RazaoSocial;
	@Column(name="for_nome_contato")
	private	String NomeContato;
	@Column(name="for_nome_fantasia")
	private String NomeFantasia;
	@Column(name="for_cnpj")
	private String cnpj;
	@Column(name="for_endereco")
	private String endereco;
	@Column(name="for_data_cadastro")
	private String dataCadastro;
	@Column(name="for_fone")
	private String fone;
	@Column(name="for_email")
	private String email;
	@Column(name="for_website")
	private String webSite;
	
	@OneToMany(mappedBy = "fornecedor", targetEntity = Produto.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Produto> produtos;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="for_mun_codigo")	
	private Municipio municipio;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getRazaoSocial() {
		return RazaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		RazaoSocial = razaoSocial;
	}

	public String getNomeContato() {
		return NomeContato;
	}

	public void setNomeContato(String nomeContato) {
		NomeContato = nomeContato;
	}

	public String getNomeFantasia() {
		return NomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		NomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public Collection<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Collection<Produto> produtos) {
		this.produtos = produtos;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@Override
	public String toString() {
		return "Fornecedores [codigo=" + codigo + ", RazaoSocial=" + RazaoSocial + ", NomeContato=" + NomeContato
				+ ", NomeFantasia=" + NomeFantasia + ", cnpj=" + cnpj + ", endereco=" + endereco + ", dataCadastro="
				+ dataCadastro + ", fone=" + fone + ", email=" + email + ", webSite=" + webSite + ", produtos="
				+ produtos + ", municipio=" + municipio + "]";
	}	
	
	

}
