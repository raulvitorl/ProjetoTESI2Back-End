package br.ufac.academico.repositorios;

import java.util.*;
import javax.persistence.*;
import br.ufac.academico.entidades.*;

public class CategoriaProdutoRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public CategoriaProdutoRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}

	public void adicionar(CategoriaProduto categoriadeproduto) {	
		em.getTransaction().begin();
		em.persist(categoriadeproduto);
		em.getTransaction().commit();
		
	}
	
	public CategoriaProduto recuperar(long id) {
		return em.find(CategoriaProduto.class, id);
	}
	
	public void atualizar (CategoriaProduto CategoriasProdutos) {
		em.getTransaction().begin();
		em.merge(CategoriasProdutos);
		em.getTransaction().commit();
	}
	
	public void remover(CategoriaProduto CategoriasProdutos) {
		em.getTransaction().begin();
		em.remove(CategoriasProdutos);
		em.getTransaction().commit();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoriaProduto> recuperarTodos(){
		Query query = em.createNamedQuery("CategoriasProdutos.todos");
		return query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CategoriaProduto> recuperarTodosPorNome(){
		Query query = em.createNamedQuery("CategoriasProdutos.todosPorNome");
		return query.getResultList();
		
	}

	
	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
