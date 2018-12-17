package br.ufac.academico.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="fornecedores")
//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="Fornecedores.todos", 
		query="SELECT f FROM Fornecedor f"),
	
	@NamedQuery(name="Fornecedores.todosPorNome", 
		query="SELECT f FROM Fornecedor f ORDER BY f.nomeContato")
})
public class Fornecedor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	@Column(name="for_razao_social")
	private String razaoSocial;
	@Column(name="for_nome_contato")
	private	String nomeContato;
	@Column(name="for_nome_fantasia")
	private String nomeFantasia;
	@Column(name="for_cnpj",length=18)
	private String cnpj;
	@Column(name="for_endereco")
	private String endereco;
	@Column(name="for_data_cadastro")
	private Date dataCadastro;
	@Column(name="for_fone")
	private String fone;
	@Column(name="for_email")
	private String email;
	@Column(name="for_website")
	private String webSite;
	
	@OneToMany(mappedBy = "fornecedor", targetEntity = Produto.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Collection<Produto> produtos;
	
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="for_mun_codigo")	
	private Municipio municipio;

	
	
	
	public String getNomeContato() {
		return nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
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
		return "Fornecedores [codigo=" + codigo + ", RazaoSocial=" + razaoSocial + ", NomeContato=" + nomeContato
				+ ", NomeFantasia=" + nomeFantasia + ", cnpj=" + cnpj + ", endereco=" + endereco + ", dataCadastro="
				+ dataCadastro + ", fone=" + fone + ", email=" + email + ", webSite=" + webSite + ", produtos="
				+ produtos + ", municipio=" + municipio + "]";
	}	
	
	

}
