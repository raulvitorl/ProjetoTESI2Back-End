package br.ufac.academico.repositorios;

import java.util.*;
import javax.persistence.*;
import br.ufac.academico.entidades.*;

public class BancosRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public BancosRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}

	public void adicionar(Bancos atendente) {	
		em.getTransaction().begin();
		em.persist(atendente);
		em.getTransaction().commit();
		
	}
	
	public Bancos recuperar(long id) {
		return em.find(Bancos.class, id);
	}
	
	public void atualizar (Bancos Bancos) {
		em.getTransaction().begin();
		em.merge(Bancos);
		em.getTransaction().commit();
	}
	
	public void remover(Bancos Bancos) {
		em.getTransaction().begin();
		em.remove(Bancos);
		em.getTransaction().commit();
		
	}
	
	public List<Bancos> recuperarTodos(){
		Query query = em.createNamedQuery("Bancos.todos");
		return query.getResultList();
		
	}

	public List<Bancos> recuperarTodosPorNome(){
		Query query = em.createNamedQuery("Bancos.todosPorNome");
		return query.getResultList();
		
	}

	
	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
