package br.ufac.academico.repositorios;

import java.util.*;
import javax.persistence.*;
import br.ufac.academico.entidades.*;

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
	
	public Municipio recuperar(long id) {
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
	
	public List<Municipio> recuperarTodos(){
		Query query = em.createNamedQuery("Municipios.todos");
		return query.getResultList();
		
	}

	public List<Municipio> recuperarTodosPorNome(){
		Query query = em.createNamedQuery("Municipios.todosPorNome");
		return query.getResultList();
		
	}

	public void encerrar() {
		em.close();
		emf.close();
	}
	
}
