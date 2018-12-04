package br.ufac.academico.testes;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufac.academico.entidades.Banco;
import br.ufac.academico.repositorios.BancoRepositorio;

public class BancoTeste {
	
	public static void main(String[] args) {
		
		BancoRepositorio br = new BancoRepositorio();
		List<Banco> bancos;
		Banco b1,b2,b3;
		
		b1 = new Banco();
		b1.setNome("Banco do Brasil");
		
		b2 = new Banco();
		b2.setNome("Nubank");
		
		b3 = new Banco();
		b3.setNome("Digio");
		
		System.out.println("TESTE DE INCLUSÃO");
		br.adicionar(b1);
		br.adicionar(b2);
		br.adicionar(b3);
		
		bancos = br.recuperarTodos();
		System.out.println("TESTE DE LISTAGEM");
		for(Banco banco: bancos){
			System.out.println(banco);
		}
		
		System.out.println("TESTE DE EDIÇÃO");
		b1.setNome("Digio");
		b2.setNome("Banco do Brasil");
		b3.setNome("Nubank");
		br.atualizar(b1);
		br.atualizar(b2);
		br.atualizar(b3);
		for(Banco banco: bancos){
			System.out.println(banco);
		}
		System.out.println("TESTE DE EXCLUSÃO");
		for(Banco banco: bancos){
			br.remover(banco);
		}
		bancos = br.recuperarTodos();
		if(bancos.isEmpty()){
			System.out.println("Todos os registros foram excluidos");
		}
		
		
		
		
	}

}
