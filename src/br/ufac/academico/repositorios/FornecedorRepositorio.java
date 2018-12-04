package br.ufac.academico.repositorios;

import java.util.*;
import javax.persistence.*;
import br.ufac.academico.entidades.*;

public class FornecedorRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public FornecedorRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}

	public void adicionar(Fornecedor fornecedor) {	
		em.getTransaction().begin();
		em.persist(fornecedor);
		em.getTransaction().commit();
		
	}
	
	public Fornecedor recuperar(long id) {
		return em.find(Fornecedor.class, id);
	}
	
	public void atualizar (Fornecedor Fornecedores) {
		em.getTransaction().begin();
		em.merge(Fornecedores);
		em.getTransaction().commit();
	}
	
	public void remover(Fornecedor Fornecedores) {
		em.getTransaction().begin();
		em.remove(Fornecedores);
		em.getTransaction().commit();
		
	}
	
	public List<Fornecedor> recuperarTodos(){
		Query query = em.createNamedQuery("Fornecedores.todos");
		return query.getResultList();
		
	}

	public List<Fornecedor> recuperarTodosPorNome(){
		Query query = em.createNamedQuery("Fornecedores.todosPorNome");
		return query.getResultList();
		
	}

	
	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
