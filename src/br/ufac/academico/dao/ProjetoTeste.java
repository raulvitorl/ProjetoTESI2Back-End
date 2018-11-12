package br.ufac.academico.dao;

import java.util.Iterator;

import javax.persistence.*;

import br.ufac.academico.entidades.*;

public class ProjetoTeste {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("AcademicoJPA");
		EntityManager em = emf.createEntityManager();
		
		Projeto p1, p2;
		Colaborador c1, c2, c3;
		
//		p1 = new Projeto();
//		p1.setId(1);
//		p1.setDescricao("Tesi1");
//		
//		p2 = new Projeto();
//		p2.setId(2);
//		p2.setDescricao("Tesi2");
//		
//		c1 = new Colaborador();
//		c1.setId(1);
//		c1.setNome("Manoel");
//		
//		c2 = new Colaborador();
//		c2.setId(2);
//		c2.setNome("Joaquim");
//		
//		c3 = new Colaborador();
//		c3.setId(3);
//		c3.setNome("José");
//	
//		p1.addColaborador(c1);
//		p1.addColaborador(c2);
//		
//		p2.addColaborador(c2);
//		p2.addColaborador(c3);
//		
//		em.getTransaction().begin();
//		em.persist(p1);
//		em.persist(p2);
//		em.getTransaction().commit();

		p1 = em.find(Projeto.class, 1);
		System.out.printf("Projeto id=%d, descricao=%s, colaboradores=%d\n",
				p1.getId(), p1.getDescricao(), p1.getColaboradores().size());
		
		for (Colaborador c : p1.getColaboradores()) {
			System.out.printf("Colaborador id=%d, nome=%s\n",
					c.getId(), c.getNome());
		}
		
		p2 = em.find(Projeto.class, 2);
		System.out.printf("Projeto id=%d, descricao=%s, colaboradores=%d\n",
				p2.getId(), p2.getDescricao(), p2.getColaboradores().size());
		
		for (Colaborador c : p2.getColaboradores()) {
			System.out.printf("Colaborador id=%d, nome=%s\n",
					c.getId(), c.getNome());
		}	
		em.close();
		emf.close();
		
	}

}
