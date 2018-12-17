package br.ufac.academico.repositories;

import java.util.*;
import javax.persistence.*;

import br.ufac.academico.domain.*;

public class ClienteRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public ClienteRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}
	
	

	public void adicionar(Cliente cliente) {
		@SuppressWarnings("unused")
		Date d1;
		cliente.setCadastro(d1 = new Date());
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		
	}
	
	public Cliente recuperar(Integer id) {
		return em.find(Cliente.class, id);
	}
	
	public void atualizar (Cliente Clientes) {
		em.getTransaction().begin();
		em.merge(Clientes);
		em.getTransaction().commit();
	}
	
	public void remover(Cliente Clientes) {
		try {
			em.getTransaction().begin();
			em.remove(Clientes);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}	
		
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
