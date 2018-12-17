package br.ufac.academico.repositories;

import java.util.*;
import javax.persistence.*;

import br.ufac.academico.domain.*;

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
	
	public TipoMensagem recuperar(Integer id) {
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
	@SuppressWarnings("unchecked")
	public List<TipoMensagem> recuperarTodos(){
		Query query = em.createNamedQuery("TiposMensagens.todos");
		return query.getResultList();
		
	}


	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
