package br.ufac.academico.entidades;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo")
//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="Clientes.todos", 
		query="SELECT c FROM Clientes c"),
	
	@NamedQuery(name="Clientes.todosPorNome", 
		query="SELECT c FROM Clientes c ORDER BY c.nome")
})
public abstract class Clientes {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cli_codigo;

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="cli_mun_codigo")	
	private Municipios cli_mun_codigo;	
	
	@Column(length=50)
	private String nome;
	
	private String cli_nome;
	private Date cli_data_nascimento;
	private String cli_sexo;
	private String cli_endereco;
	private String cli_email;
	private Date cli_data_cadastro;
	private String cli_tipo;
	private String cli_status;
	private String cli_fone;
	private String cli_nome_contato;
	
	@OneToMany(mappedBy = "ven_cli_codigo", targetEntity = Vendas.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Vendas> vendas;
	
	public int getCli_codigo() {
		return cli_codigo;
	}
	public void setCli_codigo(int cli_codigo) {
		this.cli_codigo = cli_codigo;
	}
	public Municipios getCli_mun_codigo() {
		return cli_mun_codigo;
	}
	public void setCli_mun_codigo(Municipios cli_mun_codigo) {
		this.cli_mun_codigo = cli_mun_codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCli_nome() {
		return cli_nome;
	}
	public void setCli_nome(String cli_nome) {
		this.cli_nome = cli_nome;
	}
	public Date getCli_data_nascimento() {
		return cli_data_nascimento;
	}
	public void setCli_data_nascimento(Date cli_data_nascimento) {
		this.cli_data_nascimento = cli_data_nascimento;
	}
	public String getCli_sexo() {
		return cli_sexo;
	}
	public void setCli_sexo(String cli_sexo) {
		this.cli_sexo = cli_sexo;
	}
	public String getCli_endereco() {
		return cli_endereco;
	}
	public void setCli_endereco(String cli_endereco) {
		this.cli_endereco = cli_endereco;
	}
	public String getCli_email() {
		return cli_email;
	}
	public void setCli_email(String cli_email) {
		this.cli_email = cli_email;
	}
	public Date getCli_data_cadastro() {
		return cli_data_cadastro;
	}
	public void setCli_data_cadastro(Date cli_data_cadastro) {
		this.cli_data_cadastro = cli_data_cadastro;
	}
	public String getCli_tipo() {
		return cli_tipo;
	}
	public void setCli_tipo(String cli_tipo) {
		this.cli_tipo = cli_tipo;
	}
	public String getCli_status() {
		return cli_status;
	}
	public void setCli_status(String cli_status) {
		this.cli_status = cli_status;
	}
	public String getCli_fone() {
		return cli_fone;
	}
	public void setCli_fone(String cli_fone) {
		this.cli_fone = cli_fone;
	}
	public String getCli_nome_contato() {
		return cli_nome_contato;
	}
	public void setCli_nome_contato(String cli_nome_contato) {
		this.cli_nome_contato = cli_nome_contato;
	}
	
}
