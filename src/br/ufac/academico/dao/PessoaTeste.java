package br.ufac.academico.dao;

import javax.persistence.*;
import br.ufac.academico.entidades.*;

public class PessoaTeste {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("AcademicoJPA");

		EntityManager em = emf.createEntityManager();
	
		ClientesFisicos pf = new ClientesFisicos();
		ClientesJuridicos pj = new ClientesJuridicos();
	
		//pf.setId(1);
		pf.setNome("Macilon Araújo");
		pf.setCpf("12345678900");

		//pj.setId(2);
		pj.setNome("Casa da Sogra S/A");
		pj.setCnpj("12345678901234");
		
		em.getTransaction().begin();
		em.persist(pf);
		em.persist(pj);
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}

}
