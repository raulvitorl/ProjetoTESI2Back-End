package br.ufac.academico.entidades;


import java.util.Collection;
import javax.persistence.*;
@Entity
@NamedQueries({
	@NamedQuery(name="Bancos.todos", 
	query="SELECT b FROM Banco b"),
	@NamedQuery(name="Bancos.todosPorNome", 
	query="SELECT b FROM Banco b ORDER BY b.nome"),
	@NamedQuery(name="Bancos.todosPorNomeContendo",
	query="SELECT b FROM Banco b WHERE b.nome LIKE :termo ORDER BY b.nome")
})
@Table(name="bancos")
//Consultas que serão realizadas no repositóri
public class Banco {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigo;
	@Column(name="nome")
	private String nome;

	@OneToMany(mappedBy = "banco", targetEntity = Venda.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Venda> vendas;

	public Banco(long codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public Banco(){

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

	public Collection<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(Collection<Venda> vendas) {
		this.vendas = vendas;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	

}
