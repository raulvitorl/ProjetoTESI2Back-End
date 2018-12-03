package br.ufac.academico.repositorios;

import java.util.*;
import javax.persistence.*;
import br.ufac.academico.entidades.*;

public class MensagensRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public MensagensRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}

	public void adicionar(Mensagem mensagem) {	
		em.getTransaction().begin();
		em.persist(mensagem);
		em.getTransaction().commit();
		
	}
	
	public Mensagem recuperar(long id) {
		return em.find(Mensagem.class, id);
	}
	
	public void atualizar (Mensagem Mensagens) {
		em.getTransaction().begin();
		em.merge(Mensagens);
		em.getTransaction().commit();
	}
	
	public void remover(Mensagem Mensagens) {
		em.getTransaction().begin();
		em.remove(Mensagens);
		em.getTransaction().commit();
		
	}
	
	public List<Mensagem> recuperarTodos(){
		Query query = em.createNamedQuery("Mensagens.todos");
		return query.getResultList();
		
	}

	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
