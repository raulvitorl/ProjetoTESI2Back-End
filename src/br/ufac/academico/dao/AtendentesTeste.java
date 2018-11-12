package br.ufac.academico.dao;

import java.util.*;
import br.ufac.academico.entidades.*;
import br.ufac.academico.repositorios.*;

public class AtendentesTeste {

	public static void main(String[] args) {
		
		AtendentesRepositorio ar = new AtendentesRepositorio();
		List<Atendentes> Atendentess;
		
		Atendentes a1;
		Atendentes a2;
		Atendentes a3;
		Atendentes a4;
		Atendentes a5;
		
		
		
		a1 = new Atendentes();
		a1.setNome("Paula");
		
		a2 = new Atendentes();
		a2.setNome("Carlos");
		
		a3 = new Atendentes();
		a3.setNome("João");

		a4 = new Atendentes();
		a4.setNome("pedro");
		
		a5 = new Atendentes();
		a5.setNome("BATATA");
		
		ar.adicionar(a1);
		ar.adicionar(a2);
		ar.adicionar(a3);
		ar.adicionar(a4);
		ar.adicionar(a5);

		System.out.println("---------------------------------------------------------------");
		Atendentess = ar.recuperarTodos();
		System.out.println("Listando os vagabundos");
		for (Atendentes Atendentes : Atendentess) {
			System.out.println(Atendentes.getNome());
		}
		System.out.println("---------------------------------------------------------------");
		Atendentess = ar.recuperarTodosPorNome();
		System.out.println("Listando os vagabundos pelo nome de ordem de prisao");
		for (Atendentes Atendentes : Atendentess) {
			System.out.println(Atendentes.getNome());
		}
		System.out.println("---------------------------------------------------------------");
		ar.encerrar();
		
	}

}
