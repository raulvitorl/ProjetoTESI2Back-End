package br.ufac.academico.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="produtos")
public class Produtos {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long proCodigo;
	@Column(name="pro_for_codigo")
	private Fornecedores fornecedor;
	@Column(name="pro_cat_ccodigo")
	private CategoriasProdutos categoria;
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
	

public Produtos(){
		
	}
	
	
	public Produtos(int proCodigo, Fornecedores fornecedor, CategoriasProdutos categoria, String proDescricao,
			int proQntDisponivel, String proUltimaAquisicao, float proValorUnitario, String proFabricante,
			String proDetalhes) {
		super();
		this.proCodigo = proCodigo;
		this.fornecedor = fornecedor;
		this.categoria = categoria;
		this.proDescricao = proDescricao;
		this.proQntDisponivel = proQntDisponivel;
		this.proUltimaAquisicao = proUltimaAquisicao;
		this.proValorUnitario = proValorUnitario;
		this.proFabricante = proFabricante;
		this.proDetalhes = proDetalhes;
	}
	
	
	public Fornecedores getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedores fornecedor) {
		this.fornecedor = fornecedor;
	}
	public CategoriasProdutos getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriasProdutos categoria) {
		this.categoria = categoria;
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
