package br.ufac.academico.repositorios;

import java.util.*;
import javax.persistence.*;
import br.ufac.academico.entidades.*;

public class AtendentesRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public AtendentesRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}

	public void adicionar(Atendentes atendente) {	
		em.getTransaction().begin();
		em.persist(atendente);
		em.getTransaction().commit();
		
	}
	
	public Atendentes recuperar(long id) {
		return em.find(Atendentes.class, id);
	}
	
	public void atualizar (Atendentes Atendentes) {
		em.getTransaction().begin();
		em.merge(Atendentes);
		em.getTransaction().commit();
	}
	
	public void remover(Atendentes Atendentes) {
		em.getTransaction().begin();
		em.remove(Atendentes);
		em.getTransaction().commit();
		
	}
	
	public List<Atendentes> recuperarTodos(){
		Query query = em.createNamedQuery("Atendentes.todos");
		return query.getResultList();
		
	}

	public List<Atendentes> recuperarTodosPorNome(){
		Query query = em.createNamedQuery("Atendentes.todosPorNome");
		return query.getResultList();
		
	}

	
	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
