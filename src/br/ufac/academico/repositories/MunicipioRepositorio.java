package br.ufac.academico.repositories;

import java.util.*;
import javax.persistence.*;

import br.ufac.academico.domain.*;

public class MunicipioRepositorio {

	private EntityManagerFactory emf;
	private EntityManager em;	
	
	public MunicipioRepositorio() {
		emf = Persistence.createEntityManagerFactory("AcademicoJPA");
		em = emf.createEntityManager();
	}

	public void adicionar(Municipio banco) {	
		em.getTransaction().begin();
		em.persist(banco);
		em.getTransaction().commit();
		
	}
	
	public Municipio recuperar(Integer id) {
		return em.find(Municipio.class, id);
	}
	
	public void atualizar (Municipio Municipios) {
		em.getTransaction().begin();
		em.merge(Municipios);
		em.getTransaction().commit();
	}
	
	public void remover(Municipio Municipios) {
		em.getTransaction().begin();
		em.remove(Municipios);
		em.getTransaction().commit();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Municipio> recuperarTodos(){
		Query query = em.createNamedQuery("Municipios.todos");
		return query.getResultList();
		
	}

	@SuppressWarnings("unchecked")
	public List<Municipio> recuperarTodosPorNome(){
		Query query = em.createNamedQuery("Municipios.todosPorNome");
		return query.getResultList();
		
	}

	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
