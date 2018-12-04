package br.ufac.academico.testes;

import java.util.List;

import br.ufac.academico.entidades.CategoriaProduto;
import br.ufac.academico.repositorios.CategoriaProdutoRepositorio;

public class CategoriaProdutoTeste {
	
	public static void main(String[] args) {
		CategoriaProdutoRepositorio cpr = new CategoriaProdutoRepositorio();
		List<CategoriaProduto> categorias;
		CategoriaProduto cp1,cp2,cp3;
		
		cp1 = new CategoriaProduto();
		cp1.setDescricao("Informativo");
		cp1.setIdentificador(1);
		
		cp2 = new CategoriaProduto();
		cp2.setDescricao("Solicitação");
		cp2.setIdentificador(2);
		
		cp3 = new CategoriaProduto();
		cp3.setDescricao("Comunicado");
		cp3.setIdentificador(3);
		System.out.println("TESTE DE INCLUSÃO");
		cpr.adicionar(cp1);
		cpr.adicionar(cp2);
		cpr.adicionar(cp3);
		
		categorias = cpr.recuperarTodos();
		System.out.println("TESTE DE LISTAGEM");
		for(CategoriaProduto categoria: categorias){
			System.out.println(categoria);
		}
		
		System.out.println("TESTE DE EDIÇÃO");
		cp1.setDescricao(cp2.getDescricao());
		cp2.setDescricao(cp3.getDescricao());
		cp3.setDescricao(cp1.getDescricao());
		cpr.atualizar(cp1);
		cpr.atualizar(cp2);
		cpr.atualizar(cp3);
		categorias = cpr.recuperarTodos();
		for(CategoriaProduto categoria: categorias){
			System.out.println(categoria);
		}
		System.out.println("TESTE DE EXCLUSÃO");
		for(CategoriaProduto categoria: categorias){
			cpr.remover(categoria);
		}
		categorias = cpr.recuperarTodos();
		if(categorias.isEmpty()){
			System.out.println("Todos os registros foram excluidos");
		}
		
		
		
		
		
		
		
	}

}
