package br.ufac.academico.entidades;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

import javax.persistence.*;

@Entity
//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="Clientes.todos", 
		query="SELECT c FROM Cliente c"),
	
	@NamedQuery(name="Clientes.todosPorNome", 
		query="SELECT c FROM Cliente c ORDER BY c.nome")
})
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="cli_mun_codigo")	
	private Municipio municipio;	
	private String nome;
	private Date nascimento;
	private String sexo;
	private String endereco;
	private String email;
	private Date cadastro;
	private Integer tipo;
	private String status;
	private String fone;
	private String contato;
	private String cpf;

	@OneToMany(mappedBy = "cliente", targetEntity = Venda.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Venda> vendas;

	public Cliente() {
		super();
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Collection<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(Collection<Venda> vendas) {
		this.vendas = vendas;
	}

	

}
