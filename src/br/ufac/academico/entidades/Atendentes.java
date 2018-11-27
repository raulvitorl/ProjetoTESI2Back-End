package br.ufac.academico.entidades;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="atendentes")
//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="Atendentes.todos", 
		query="SELECT a FROM Atendentes a"),
	
	@NamedQuery(name="Atendentes.todosPorNome", 
		query="SELECT a FROM Atendentes a ORDER BY a.nome")
})

public class Atendentes {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigo;
	@Column(name="ate_nome",length=50)
	private String nome;
	@Column(name="ate_ulltimo_acesso")
	private String ultimoAcesso;
	@Column(name="ate_ramal")
	private String ramal;
	@Column(name="ate_email")
	private String email;
	@Column(name="ate_status")
	private char status;
	@Column(name="ate_perfil")
	private String ate_perfil;
	
	@OneToMany(mappedBy = "ven_ate_codigo", targetEntity = Vendas.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Vendas> vendas;
	
	@OneToMany(mappedBy = "men_ate_codigo", targetEntity = Mensagens.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Mensagens> mensagens;
	
	public Atendentes() {}
		
	public String getAte_perfil() {return ate_perfil;}

	public void setAte_perfil(String ate_perfil){this.ate_perfil = ate_perfil;}

	public Collection<Vendas> getVendas() {return vendas;}

	public void setVendas(Collection<Vendas> vendas){this.vendas = vendas;}

	public Collection<Mensagens> getMensagens(){return mensagens;}
	
	public void setMensagens(Collection<Mensagens> mensagens){this.mensagens = mensagens;}

	public void setCodigo(long codigo){this.codigo = codigo;}

	public long getCodigo() {return codigo;}
	
	public void setCodigo(int codigo){this.codigo = codigo;}
	
	public String getNome() {return nome;}
	
	public void setNome(String nome){this.nome = nome;}
	
	public String getUltimoAcesso() {return ultimoAcesso;}
	
	public void setUltimoAcesso(String ultimoAcesso){this.ultimoAcesso = ultimoAcesso;}
	
	public String getRamal(){return ramal;}
	
	public void setRamal(String ramal){this.ramal = ramal;}
	
	public String getEmail(){return email;}
	
	public void setEmail(String email) {this.email = email;}
	
	public char getStatus(){return status;}
	
	public void setStatus(char status){this.status = status;}

}