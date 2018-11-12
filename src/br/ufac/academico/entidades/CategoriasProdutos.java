package br.ufac.academico.entidades;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categorias_produtos")
public class CategoriasProdutos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
