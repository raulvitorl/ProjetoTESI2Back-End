package br.ufac.academico.domain;

import java.util.Collection;

import javax.persistence.*;
@Entity
@Table(name="categorias_produtos")
//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="CategoriasProdutos.todos", 
	query="SELECT cp FROM CategoriaProduto cp"),

	@NamedQuery(name="CategoriasProdutos.todosPorNome", 
	query="SELECT cp.descricao FROM CategoriaProduto cp ORDER BY cp.descricao"),
})
public class CategoriaProduto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	@Column(name="identificador")
	private int identificador;
	@Column(name="descricao")
	private String descricao;
	@OneToMany(mappedBy = "categoria", targetEntity = Produto.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Collection<Produto> produtos;

	public CategoriaProduto(){
		
	}

	
	
	
	public Collection<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(Collection<Produto> produtos) {
		this.produtos = produtos;
	}




	public Integer getCodigo() {
		return codigo;
	}




	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}




	public int getIdentificador() {
		return identificador;
	}




	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}




	public String getDescricao() {
		return descricao;
	}




	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}




	@Override
	public String toString() {
		return "CategoriasProdutos [codigo=" + codigo + ", identificador=" + identificador + ", descricao=" + descricao
				+ "]";
	}
	
	

	
}