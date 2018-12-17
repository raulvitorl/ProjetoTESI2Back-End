package br.ufac.academico.repositories;

import java.util.*;
import javax.persistence.*;

import br.ufac.academico.domain.*;

public class FornecedorRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public FornecedorRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}

	public void adicionar(Fornecedor fornecedor) {
		@SuppressWarnings("unused")
		Date d1;
		fornecedor.setDataCadastro(d1 = new Date());  
		em.getTransaction().begin();
		em.persist(fornecedor);
		em.getTransaction().commit();
		
	}
	
	public Fornecedor recuperar(Integer id) {
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
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> recuperarTodos(){
		Query query = em.createNamedQuery("Fornecedores.todos");
		return query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Fornecedor> recuperarTodosPorNome(){
		Query query = em.createNamedQuery("Fornecedores.todosPorNome");
		return query.getResultList();
		
	}

	
	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
