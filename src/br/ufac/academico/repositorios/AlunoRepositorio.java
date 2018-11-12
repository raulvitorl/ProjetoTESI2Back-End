package br.ufac.academico.repositorios;

import java.util.*;
import javax.persistence.*;
import br.ufac.academico.entidades.*;

public class AlunoRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public AlunoRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}

	public void adicionar(Aluno aluno) {
	
		em.getTransaction().begin();
		em.persist(aluno);
		em.getTransaction().commit();
		
	}
	
	public Aluno recuperar(long id) {
		return em.find(Aluno.class, id);
	}
	
	public void atualizar (Aluno aluno) {
		
		em.getTransaction().begin();
		em.merge(aluno);
		em.getTransaction().commit();
		
	}
	
	public void remover(Aluno aluno) {
		
		em.getTransaction().begin();
		em.remove(aluno);
		em.getTransaction().commit();
		
	}
	
	public List<Aluno> recuperarTodos(){
		
		Query query = em.createNamedQuery("Aluno.todos");
		return query.getResultList();
		
	}

	public List<Aluno> recuperarTodosPorNome(){
		
		Query query = em.createNamedQuery("Aluno.todosPorNome");
		return query.getResultList();
		
	}
	
	public List<Aluno> recuperarMenoresDeIdade(){
		
		Query query = em.createNamedQuery("Aluno.menoresDeIdade");
		return query.getResultList();
		
	}	
	
	public List<Aluno> recuperarMaioresDeIdade(){
		
		Query query = em.createNamedQuery("Aluno.maioresDeIdade");
		return query.getResultList();
		
	}	

	public List<Aluno> recuperarAdolescentes(){
		
		Query query = em.createNamedQuery("Aluno.adolescentes");
		return query.getResultList();
		
	}	

	public List<Aluno> recuperarPorFaixaEtaria(int min, int max){
		
		Query query = em.createNamedQuery("Aluno.porFaixaEtaria");
		query.setParameter("min", min);
		query.setParameter("max", max);		
		return query.getResultList();
		
	}	
	
	public List<Aluno> recuperarNomeContendo(String termo){
		
		Query query = em.createNamedQuery("Aluno.nomeContendo");
		query.setParameter("termo", "%" + termo + "%");		
		return query.getResultList();
		
	}
	
	
	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
