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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="produtos")

//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="Produtos.todos", 
		query="SELECT p FROM Produto p"),
	
	@NamedQuery(name="Produtos.todosPorDescricao", 
		query="SELECT p FROM Produto p ORDER BY p.descricao")
})
public class Produto {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigo;
	@Column(name="pro_descricao")
	private String descricao;
	@Column(name="pro_qnt_disponivel")
	private int qntDisponivel;
	@Column(name="pro_ultima_aquisicao")
	private String ultimaAquisicao;
	@Column(name="pro_valor_unit")
	private float valorUnitario;
	@Column(name="pro_fabricante")
	private String fabricante;
	@Column(name="pro_detalhes")
	private String detalhes;

	@ManyToMany(mappedBy="produtos")
	private Collection<Venda> vendas;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="pro_for_codigo")	
	private Fornecedor fornecedor;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="categoriaProduto")	
	private CategoriaProduto categoria;
	

public Produto(){
		
	}


public long getCodigo() {
	return codigo;
}


public void setCodigo(long codigo) {
	this.codigo = codigo;
}


public String getDescricao() {
	return descricao;
}


public void setDescricao(String descricao) {
	this.descricao = descricao;
}


public int getQntDisponivel() {
	return qntDisponivel;
}


public void setQntDisponivel(int qntDisponivel) {
	this.qntDisponivel = qntDisponivel;
}


public String getUltimaAquisicao() {
	return ultimaAquisicao;
}


public void setUltimaAquisicao(String ultimaAquisicao) {
	this.ultimaAquisicao = ultimaAquisicao;
}


public float getValorUnitario() {
	return valorUnitario;
}


public void setValorUnitario(float valorUnitario) {
	this.valorUnitario = valorUnitario;
}


public String getFabricante() {
	return fabricante;
}


public void setFabricante(String fabricante) {
	this.fabricante = fabricante;
}


public String getDetalhes() {
	return detalhes;
}


public void setDetalhes(String detalhes) {
	this.detalhes = detalhes;
}




public Fornecedor getFornecedor() {
	return fornecedor;
}


public void setFornecedor(Fornecedor fornecedor) {
	this.fornecedor = fornecedor;
}


public CategoriaProduto getCategoria() {
	return categoria;
}


public void setCategoria(CategoriaProduto categoria) {
	this.categoria = categoria;
}


public Collection<Venda> getVendas() {
	return vendas;
}


public void setVendas(Collection<Venda> vendas) {
	this.vendas = vendas;
}


@Override
public String toString() {
	return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", qntDisponivel=" + qntDisponivel
			+ ", ultimaAquisicao=" + ultimaAquisicao + ", valorUnitario=" + valorUnitario + ", fabricante=" + fabricante
			+ ", detalhes=" + detalhes + ", vendas=" + vendas + ", fornecedor=" + fornecedor + ", categoria="
			+ categoria + "]";
}





	
	
}
