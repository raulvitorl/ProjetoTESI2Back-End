package br.ufac.academico.dao;

import java.util.*;
import br.ufac.academico.entidades.*;
import br.ufac.academico.repositorios.*;

public class AlunoTeste {

	public static void main(String[] args) {
		
		AlunoRepositorio ar = new AlunoRepositorio();
		List<Aluno> alunos;
		
		Aluno a1;
		Aluno a2;
		Aluno a3;
		Aluno a4;
		Aluno a5;
		
		a1 = new Aluno();
		a1.setNome("Paula");
		a1.setIdade(10);
		
		a2 = new Aluno();
		a2.setNome("Carlos");
		a2.setIdade(15);
		
		a3 = new Aluno();
		a3.setNome("João");
		a3.setIdade(12);

		a4 = new Aluno();
		a4.setNome("pedro");
		a4.setIdade(11);
		
		a5 = new Aluno();
		a5.setNome("BATATA");
		a5.setIdade(18);
		
		ar.adicionar(a1);
		ar.adicionar(a2);
		ar.adicionar(a3);
		ar.adicionar(a4);
		ar.adicionar(a5);

//	
//		a1 = em.find(Aluno.class, 1l);
//		System.out.println("Aluno");
//		System.out.println(a1.getId());
//		System.out.println(a1.getNome());
//		
//		a1.setNome("Macilon Araújo");
//		
//		em.getTransaction().begin();
//		em.merge(a1);
//		em.getTransaction().commit();
//		
//		a1 = em.find(Aluno.class, 1l);
//		System.out.println("Aluno");
//		System.out.println(a1.getId());
//		System.out.println(a1.getNome());
//
//		a2 = em.find(Aluno.class, 4l);
//		System.out.println("Aluno");
//		System.out.println(a2.getId());
//		System.out.println(a2.getNome());
//	
//		em.getTransaction().begin();
//		em.remove(a2);
//		em.getTransaction().commit();
//
//		a2 = em.find(Aluno.class, 4l);
//		
//		if (a2 != null) {
//			System.out.println("Aluno");
//			System.out.println(a2.getId());
//			System.out.println(a2.getNome());
//		}else {
//			System.out.printf("Aluno (id=%d) não encontrado!", 4);
//		}
		System.out.println("---------------------------------------------------------------");
		alunos = ar.recuperarTodos();
		System.out.println("Listando os vagabundos");
		for (Aluno aluno : alunos) {
			System.out.println(aluno);
		}
		System.out.println("---------------------------------------------------------------");
		alunos = ar.recuperarTodosPorNome();
		System.out.println("Listando os vagabundos pelo nome de ordem de prisao");
		for (Aluno aluno : alunos) {
			System.out.println(aluno);
		}
		System.out.println("---------------------------------------------------------------");
		alunos = ar.recuperarMenoresDeIdade();
		System.out.println("Listando os Meliantes menores de idade");
		for (Aluno aluno : alunos) {
			System.out.println(aluno);
		}
		System.out.println("---------------------------------------------------------------");
		alunos = ar.recuperarMaioresDeIdade();
		System.out.println("Listando os Meliantes que vao pro xilindro");
		for (Aluno aluno : alunos) {
			System.out.println(aluno);
		}
		System.out.println("---------------------------------------------------------------");
		alunos = ar.recuperarAdolescentes();
		System.out.println("Listando os mulekes trouxas");
		for (Aluno aluno : alunos) {
			System.out.println(aluno);
		}
		System.out.println("---------------------------------------------------------------");
		ar.encerrar();
		
	}

}
