package br.ufac.academico.dao;

import javax.persistence.*;

import br.ufac.academico.entidades.*;

public class PedidoTeste {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("AcademicoJPA");
		EntityManager em = emf.createEntityManager();

		Pedido p1, p2;
		Item i11, i12, i13, i21, i22;

		p1 = new Pedido();
		p1.setId(1);
		p1.setStatus("Em processamento");
		
		p2 = new Pedido();
		p2.setId(2);
		p2.setStatus("Em trânsito");
		
		i11 = new Item();
		i11.setId(11);
		i11.setQuantidate(10);
		
		i12 = new Item();
		i12.setId(12);
		i12.setQuantidate(15);
		
		i13 = new Item();
		i13.setId(13);
		i13.setQuantidate(20);
		
		i21 = new Item();
		i21.setId(21);
		i21.setQuantidate(30);
		
		i22 = new Item();
		i22.setId(22);
		i22.setQuantidate(60);

		p1.addItem(i11);
		p1.addItem(i12);
		p1.addItem(i13);
		
		p2.addItem(i21);
		p2.addItem(i22);
		
		em.getTransaction().begin();
		em.persist(p1);
		em.persist(p2);
		em.getTransaction().commit();

//		p1 = em.find(Pedido.class, 1);
//		i13 = em.find(Item.class, 13);
//		
//		p1.delItem(i13);
//		
//		em.getTransaction().begin();
//		em.merge(p1);
//		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}

}
