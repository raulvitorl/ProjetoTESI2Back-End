package br.ufac.academico.repositorios;

import java.util.*;
import javax.persistence.*;
import br.ufac.academico.entidades.*;

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
	
	public List<Venda> recuperarTodos(){
		Query query = em.createNamedQuery("Vendas.todos");
		return query.getResultList();
		
	}


	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
