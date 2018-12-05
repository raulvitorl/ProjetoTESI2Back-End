package br.ufac.academico.repositorios;

import java.util.*;
import javax.persistence.*;

import br.ufac.academico.entidades.*;

public class ClienteRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public ClienteRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}
	
	

	public void adicionar(Cliente cliente) {
		
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		
	}
	
	public Cliente recuperar(long id) {
		return em.find(Cliente.class, id);
	}
	
	public void atualizar (Cliente Clientes) {
		em.getTransaction().begin();
		em.merge(Clientes);
		em.getTransaction().commit();
	}
	
	public void remover(Cliente Clientes) {
		em.getTransaction().begin();
		em.remove(Clientes);
		em.getTransaction().commit();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> recuperarTodos(){
		Query query = em.createNamedQuery("Clientes.todos");
		return query.getResultList();
		
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> recuperarTodosPorNome(){
		Query query = em.createNamedQuery("Clientes.todosPorNome");
		return query.getResultList();
		
	}

	public void encerrar() {
		em.close();
		emf.close();
	}
		
}
