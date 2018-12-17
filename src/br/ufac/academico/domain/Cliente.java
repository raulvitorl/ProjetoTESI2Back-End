package br.ufac.academico.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Collection;

import javax.persistence.*;

import br.ufac.academico.domain.enums.SexoCliente;
import br.ufac.academico.domain.enums.StatusCliente;
import br.ufac.academico.domain.enums.TipoCliente;

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

	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="cli_mun_codigo")	
	private Municipio municipio;	
	private String nome;
	@Column(length=14)
	private String cpf;
	private Integer sexo;
	private String endereco;
	private String email;
	private Integer tipo;
	private Integer status;
	@Column(length=15)
	private String fone;
	private String contato;
	private Date nascimento;
	private Date cadastro;

	@OneToMany(mappedBy = "cliente", targetEntity = Venda.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Venda> vendas;

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

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}
	
	public SexoCliente getSexo() {
		return SexoCliente.toEnum(sexo);
	}

	public void setSexo(SexoCliente sexo) {
		this.sexo = sexo.getCod();
	}

	
	public StatusCliente getStatus() {
		return StatusCliente.toEnum(status);
	}

	public void setStatus(StatusCliente status) {
		this.status = status.getCod();
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

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", municipio=" + municipio + ", nome=" + nome + ", nascimento="
				+ nascimento + ", sexo=" + sexo + ", endereco=" + endereco + ", email=" + email + ", cadastro="
				+ cadastro + ", tipo=" + tipo + ", status=" + status + ", fone=" + fone + ", contato=" + contato
				+ ", cpf=" + cpf + "]";
	}

	
	
	

}
