package br.ufac.academico.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente {
	
	@Id
	private long codigo;
	private Municipios municipio;
	private String nome;
	private String dataNascimento;
	private char sexo;
	private String cpf;
	private String rg;
	private String cnpj;
	private String endereco;
	private String email;
	private String dataCadastro;
	private char tipo;
	private char status;
	private String fone;
	private String nomeContato;

	
	public Cliente(){
		
	}
	
	
	
	
	public Cliente(int codigo, Municipios municipio, String nome, String dataNascimento, char sexo, String cpf,
			String rg, String cnpj, String endereco, String email, String dataCadastro, char tipo, char status,
			String fone, String nomeContato) {
		this.codigo = codigo;
		this.municipio = municipio;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.rg = rg;
		this.cnpj = cnpj;
		this.endereco = endereco;
		this.email = email;
		this.dataCadastro = dataCadastro;
		this.tipo = tipo;
		this.status = status;
		this.fone = fone;
		this.nomeContato = nomeContato;
	}
	public Municipios getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipios municipio) {
		this.municipio = municipio;
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
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public char getTipo() {
		return tipo;
	}
	public void setTipo(char tipo) {
		this.tipo = tipo;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getNomeContato() {
		return nomeContato;
	}
	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}




}
