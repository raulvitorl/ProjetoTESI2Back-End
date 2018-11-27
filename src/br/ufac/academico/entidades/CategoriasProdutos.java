package br.ufac.academico.entidades;

import java.util.Collection;

import javax.persistence.*;
@Entity
@Table(name="categorias_produtos")
//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="CategoriasProudutos.todos", 
	query="SELECT cp FROM CategoriasProudutos cp"),

	@NamedQuery(name="CategoriasProudutos.todosPorNome", 
	query="SELECT CategoriasProudutos.catDescricao FROM CategoriasProudutos cp ORDER BY cp.nome"),
	
	@NamedQuery(name="Bancos.todos", 
	query="SELECT b FROM Bancos b"),

	@NamedQuery(name="Bancos.todosPorNome", 
	query="SELECT b FROM Bancos b ORDER BY b.nome")
})
public class CategoriasProdutos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long catCodigo;
	@Column(name="cat_identificador")
	private int catIdentificador;
	@Column(name="cat_descricao")
	private String catDescricao;
	@OneToMany(mappedBy = "pro_cat_codigo", targetEntity = Produtos.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Produtos> produtos;

public CategoriasProdutos(){
		
	}
	
	public CategoriasProdutos(int catCodigo, int catIdentificador, String catDescricao) {
		super();
		this.catCodigo = catCodigo;
		this.catIdentificador = catIdentificador;
		this.catDescricao = catDescricao;
	}
	
	
	
	
	public long getCatCodigo() {
		return catCodigo;
	}
	public void setCatCodigo(int catCodigo) {
		this.catCodigo = catCodigo;
	}
	public int getCatIdentificador() {
		return catIdentificador;
	}
	public void setCatIdentificador(int catIdentificador) {
		this.catIdentificador = catIdentificador;
	}
	public String getCatDescricao() {
		return catDescricao;
	}
	public void setCatDescricao(String catDescricao) {
		this.catDescricao = catDescricao;
	}
	
	
	
	
	

}
