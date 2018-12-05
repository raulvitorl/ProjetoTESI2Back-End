package br.ufac.academico.repositorios;

import java.util.*;
import javax.persistence.*;
import br.ufac.academico.entidades.*;

public class ProdutoRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public ProdutoRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}

	public void adicionar(Produto fornecedor) {	
		em.getTransaction().begin();
		em.persist(fornecedor);
		em.getTransaction().commit();
		
	}
	
	public Produto recuperar(long id) {
		return em.find(Produto.class, id);
	}
	
	public void atualizar (Produto produto) {
		em.getTransaction().begin();
		em.merge(produto);
		em.getTransaction().commit();
	}
	
	public void remover(Produto produto) {
		em.getTransaction().begin();
		em.remove(produto);
		em.getTransaction().commit();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> recuperarTodos(){
		Query query = em.createNamedQuery("Produtos.todos");
		return query.getResultList();
		
	}

	@SuppressWarnings("unchecked")
	public List<Produto> recuperarTodosPorNome(){
		Query query = em.createNamedQuery("Produtos.todosPorNome");
		return query.getResultList();
		
	}

	
	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
