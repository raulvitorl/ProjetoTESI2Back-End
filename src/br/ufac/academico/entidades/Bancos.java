package br.ufac.academico.entidades;


import java.util.Collection;
import javax.persistence.*;
@Entity
@Table(name="bancos")
//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="Bancos.todos", 
	query="SELECT b FROM Bancos b"),

	@NamedQuery(name="Bancos.todosPorNome", 
	query="SELECT b FROM Bancos b ORDER BY b.nome")
})
public class Bancos {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigo;
	@Column(name="ban_nome")
	private String nome;

	@OneToMany(mappedBy = "ven_ban_codigo", targetEntity = Vendas.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Vendas> vendas;

	public Bancos(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public Bancos(){

	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString() {
		return nome; 
	}

}
