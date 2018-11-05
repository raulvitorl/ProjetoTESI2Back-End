package br.ufac.academico.entity;

public class Produtos {

	
	
	private int proCodigo;
	private Fornecedores fornecedor;
	private CategoriasProdutos categoria;
	private String proDescricao;
	private int proQntDisponivel;
	private String proUltimaAquisicao;
	private float proValorUnitario;
	private String proFabricante;
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
	public int getProCodigo() {
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
