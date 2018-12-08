package br.ufac.academico.domain;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="municipios")

//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="Municipios.todos", 
		query="SELECT m FROM Municipio m"),
	
	@NamedQuery(name="Municipios.todosPorNome", 
		query="SELECT m FROM Municipio m ORDER BY m.nome")
})
public class Municipio {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer codigo;
@Column(name="mun_nome")	
private String nome;
@Column(name="mun_uf_estado")
private String UfEstado;
@Column(name="mun_cep")
private String  cep;

@OneToMany(mappedBy = "municipio", targetEntity = Fornecedor.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
private Collection<Fornecedor> fornecedores;
@OneToMany(mappedBy = "municipio", targetEntity = Cliente.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
private Collection<Cliente> clientes;
public Municipio(){}
public Integer getCodigo() {
	return codigo;
}
public void setCodigo(Integer codigo) {
	this.codigo = codigo;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getUfEstado() {
	return UfEstado;
}
public void setUfEstado(String ufEstado) {
	UfEstado = ufEstado;
}
public String getCep() {
	return cep;
}
public void setCep(String cep) {
	this.cep = cep;
}
public Collection<Fornecedor> getFornecedores() {
	return fornecedores;
}
public void setFornecedores(Collection<Fornecedor> fornecedores) {
	this.fornecedores = fornecedores;
}
public Collection<Cliente> getClientes() {
	return clientes;
}
public void setClientes(Collection<Cliente> clientes) {
	this.clientes = clientes;
}
@Override
public String toString() {
	return "Municipios [codigo=" + codigo + ", nome=" + nome + ", UfEstado=" + UfEstado + ", cep=" + cep
			;
}





}