package br.ufac.academico.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="atendentes")
public class Atendentes {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long codigo;
	@Column(name="ate_nome")
	private String nome;
	@Column(name="ate_ulltimo_acesso")
	private String ultimoAcesso;
	@Column(name="ate_ramal")
	private String ramal;
	@Column(name="ate_email")
	private String email;
	@Column(name="ate_status")
	private char status;
	
	
	public Atendentes() {
		
	}
	
	
	
	public Atendentes(int codigo, String nome, String ultimoAcesso, String ramal, String email,
			char status) {
		this.codigo = codigo;
		this.nome = nome;
		this.ultimoAcesso = ultimoAcesso;
		this.ramal = ramal;
		this.email = email;
		this.status = status;
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
	public String getUltimoAcesso() {
		return ultimoAcesso;
	}
	public void setUltimoAcesso(String ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	







}