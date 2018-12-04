package br.ufac.academico.repositorios;

import java.util.*;
import javax.persistence.*;
import br.ufac.academico.entidades.*;

public class TipoMensagemRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public TipoMensagemRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}

	public void adicionar(TipoMensagem tiposmensagens) {	
		em.getTransaction().begin();
		em.persist(tiposmensagens);
		em.getTransaction().commit();
		
	}
	
	public TipoMensagem recuperar(long id) {
		return em.find(TipoMensagem.class, id);
	}
	
	public void atualizar (TipoMensagem TiposMensagens) {
		em.getTransaction().begin();
		em.merge(TiposMensagens);
		em.getTransaction().commit();
	}
	
	public void remover(TipoMensagem TiposMensagens) {
		em.getTransaction().begin();
		em.remove(TiposMensagens);
		em.getTransaction().commit();
		
	}
	
	public List<TipoMensagem> recuperarTodos(){
		Query query = em.createNamedQuery("TiposMensagens.todos");
		return query.getResultList();
		
	}


	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
