package br.ufac.academico.repositories;

import java.util.*;
import javax.persistence.*;

import br.ufac.academico.domain.*;

public class BancoRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public BancoRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}

	public void adicionar(Banco banco) {	
		em.getTransaction().begin();
		em.persist(banco);
		em.getTransaction().commit();
		
	}
	
	public Banco recuperar(long id) {
		return em.find(Banco.class, id);
	}
	
	public void atualizar (Banco Bancos) {
		em.getTransaction().begin();
		em.merge(Bancos);
		em.getTransaction().commit();
	}
	
	public void remover(Banco Bancos) {
		em.getTransaction().begin();
		em.remove(Bancos);
		em.getTransaction().commit();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Banco> recuperarTodos(){
		Query query = em.createNamedQuery("Bancos.todos");
		return query.getResultList();
		
	}

	@SuppressWarnings("unchecked")
	public List<Banco> recuperarTodosPorNome(){
		Query query = em.createNamedQuery("Bancos.todosPorNome");
		return query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Banco> recuperarTodosPorNomeContendo(String termo){
		return em.createNamedQuery("Bancos.todosPorNomeContendo")
				.setParameter("termo", "%"+termo+"%")
				.getResultList();
	}
	
	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
