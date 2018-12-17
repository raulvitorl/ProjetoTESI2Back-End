package br.ufac.academico.repositories;

import java.util.*;
import javax.persistence.*;

import br.ufac.academico.domain.*;

public class VendaRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public VendaRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}

	public void adicionar(Venda tiposmensagens) {	
		em.getTransaction().begin();
		em.persist(tiposmensagens);
		em.getTransaction().commit();
		
	}
	
	public Venda recuperar(long id) {
		return em.find(Venda.class, id);
	}
	
	public void atualizar (Venda Vendas) {
		em.getTransaction().begin();
		em.merge(Vendas);
		em.getTransaction().commit();
	}
	
	public void remover(Venda Vendas) {
		em.getTransaction().begin();
		em.remove(Vendas);
		em.getTransaction().commit();
		
	}
	@SuppressWarnings("unchecked")
	public List<Venda> recuperarTodos(){
		Query query = em.createNamedQuery("Vendas.todos");
		return query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Venda> recuperarTodosPorCliente(int termo){
		return em.createNamedQuery("Vendas.todosPorCliente")
				.setParameter("termo", "%" + termo + "%")
				.getResultList();
	}



	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
