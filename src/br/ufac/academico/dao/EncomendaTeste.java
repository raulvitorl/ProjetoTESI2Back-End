package br.ufac.academico.dao;

import javax.persistence.*;
import br.ufac.academico.entidades.*;

public class EncomendaTeste {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("AcademicoJPA");
		EntityManager em =
				emf.createEntityManager();
		
		Encomenda e1, e2;
		Destino d1, d2;
//		
//		e1 = new Encomenda();
//		e2 = new Encomenda();
//
//		d1 = new Destino();
//		d2 = new Destino();
//
//		d1.setId(1);
//		d1.setCidade("Rio Branco");
//		d1.setCep("69900");
//		
//		d2.setId(2);
//		d2.setCidade("Boca do Acre");
//		d2.setCep("12345");
//		
//		e1.setId(11);
//		e1.setDescricao("Televisor 4k");
//		e1.setDestino(d1);
//		
//		e2.setId(22);
//		e2.setDescricao("Saco de Farinha");
//		e2.setDestino(d2);
//		
//		em.getTransaction().begin();
//		em.persist(e1);
//		em.persist(e2);
//		em.getTransaction().commit();


		d1 = em.find(Destino.class, 1);
		e1 = d1.getEncomenda();
		
		System.out.println(d1);
		System.out.println(e1);
		
		em.close();
		emf.close();
		
	}

}
