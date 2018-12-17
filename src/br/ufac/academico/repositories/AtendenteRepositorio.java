package br.ufac.academico.repositories;

import java.util.*;
import javax.persistence.*;

import br.ufac.academico.domain.*;

public class AtendenteRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public AtendenteRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}

	public void adicionar(Atendente atendente) {	
		em.getTransaction().begin();
		em.persist(atendente);
		em.getTransaction().commit();
		
	}
	
	public Atendente recuperar(Integer id) {
		return em.find(Atendente.class, id);
	}
	
	public void atualizar (Atendente Atendentes) {
		em.getTransaction().begin();
		em.merge(Atendentes);
		em.getTransaction().commit();
	}
	
	public void remover(Atendente Atendentes) {
		em.getTransaction().begin();
		em.remove(Atendentes);
		em.getTransaction().commit();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Atendente> recuperarTodos(){
		Query query = em.createNamedQuery("Atendentes.todos");
		return query.getResultList();
		
	}

	@SuppressWarnings("unchecked")
	public List<Atendente> recuperarTodosPorNome(){
		Query query = em.createNamedQuery("Atendentes.todosPorNome");
		return query.getResultList();
		
	}

	
	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
