package br.ufac.academico.entidades;

import javax.persistence.*;

@Entity
//Nome da tabela
@Table(name="alunos")

//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os alunos,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="Aluno.todos", 
		query="SELECT a FROM Aluno a"),
	
	@NamedQuery(name="Aluno.todosPorNome", 
		query="SELECT a FROM Aluno a ORDER BY a.nome"),
	
	@NamedQuery(name="Aluno.menoresDeIdade", 
		query="SELECT a FROM Aluno a WHERE a.idade < 18 ORDER BY a.nome"),	
	
	@NamedQuery(name="Aluno.maioresDeIdade", 
		query="SELECT a FROM Aluno a WHERE a.idade >= 18 ORDER BY a.nome"),
	
	@NamedQuery(name="Aluno.adolescentes", 
		query="SELECT a FROM Aluno a WHERE a.idade BETWEEN 13 AND 19 ORDER BY a.nome"),
	
	@NamedQuery(name="Aluno.porFaixaEtaria", 
		query="SELECT a FROM Aluno a WHERE a.idade BETWEEN :min AND :max ORDER BY a.nome"),
	
	@NamedQuery(name="Aluno.nomeContendo",
		query="SELECT a FROM Aluno a WHERE a.nome LIKE :termo ORDER BY a.nome")
})
public class Aluno {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(length=50)
	private String nome;
	private int idade;

	public Aluno(long id, String nome, int idade) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
	}

	public Aluno() {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String toString() {
		return String.format("Aluno [id=%d, nome=\"%s\", idade=%d]",
				id, nome, idade);
	}

}
