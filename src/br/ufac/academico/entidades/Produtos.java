package br.ufac.academico.entidades;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="produtos")
public class Produtos {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long proCodigo;
	@Column(name="pro_descricao")
	private String proDescricao;
	@Column(name="pro_qnt_disponivel")
	private int proQntDisponivel;
	@Column(name="pro_ultima_aquisicao")
	private String proUltimaAquisicao;
	@Column(name="pro_valor_unit")
	private float proValorUnitario;
	@Column(name="pro_fabricante")
	private String proFabricante;
	@Column(name="pro_detalhes")
	private String proDetalhes;

	@ManyToMany(mappedBy="ven_pro_codigo")
	private Collection<Vendas> ven_pro_codigo;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="pro_for_codigo")	
	private Fornecedores pro_for_codigo;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="pro_cat_codigo")	
	private CategoriasProdutos pro_cat_codigo;
	

public Produtos(){
		
	}
	
	
	public Produtos(int proCodigo, Fornecedores fornecedor, CategoriasProdutos categoria, String proDescricao,
			int proQntDisponivel, String proUltimaAquisicao, float proValorUnitario, String proFabricante,
			String proDetalhes) {
		super();
		this.proCodigo = proCodigo;
		this.pro_for_codigo = fornecedor;
		this.pro_cat_codigo = categoria;
		this.proDescricao = proDescricao;
		this.proQntDisponivel = proQntDisponivel;
		this.proUltimaAquisicao = proUltimaAquisicao;
		this.proValorUnitario = proValorUnitario;
		this.proFabricante = proFabricante;
		this.proDetalhes = proDetalhes;
	}
	
	
	public Fornecedores getFornecedor() {
		return pro_for_codigo;
	}
	public void setFornecedor(Fornecedores fornecedor) {
		this.pro_for_codigo = fornecedor;
	}
	public CategoriasProdutos getCategoria() {
		return pro_cat_codigo;
	}
	public void setCategoria(CategoriasProdutos categoria) {
		this.pro_cat_codigo = categoria;
	}
	public long getProCodigo() {
		return proCodigo;
	}
	public void setProCodigo(int proCodigo) {
		this.proCodigo = proCodigo;
	}

	public String getProDescricao() {
		return proDescricao;
	}
	public void setProDescricao(String proDescricao) {
		this.proDescricao = proDescricao;
	}
	public int getProQntDisponivel() {
		return proQntDisponivel;
	}
	public void setProQntDisponivel(int proQntDisponivel) {
		this.proQntDisponivel = proQntDisponivel;
	}
	public String getProUltimaAquisicao() {
		return proUltimaAquisicao;
	}
	public void setProUltimaAquisicao(String proUltimaAquisicao) {
		this.proUltimaAquisicao = proUltimaAquisicao;
	}
	public float getProValorUnitario() {
		return proValorUnitario;
	}
	public void setProValorUnitario(float proValorUnitario) {
		this.proValorUnitario = proValorUnitario;
	}
	public String getProFabricante() {
		return proFabricante;
	}
	public void setProFabricante(String proFabricante) {
		this.proFabricante = proFabricante;
	}
	public String getProDetalhes() {
		return proDetalhes;
	}
	public void setProDetalhes(String proDetalhes) {
		this.proDetalhes = proDetalhes;
	}
	
	
	
	
	
	
}
