package br.ufac.academico.tests;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufac.academico.domain.TipoMensagem;
import br.ufac.academico.repositories.TipoMensagemRepositorio;

public class TipoMensagemTeste {
	
	public static void main(String[] args) {
		TipoMensagemRepositorio tmr = new TipoMensagemRepositorio();
		List<TipoMensagem> tipos;
		TipoMensagem tms1,tms2,tms3;
		
		tms1 = new TipoMensagem();
		tms1.setDescricao("Informativo");
		
		tms2 = new TipoMensagem();
		tms2.setDescricao("Memorando");
		
		tms3 = new TipoMensagem();
		tms3.setDescricao("Solicitação");
	
		System.out.println("TESTE DE INCLUSÃO");
		tmr.adicionar(tms1);
		tmr.adicionar(tms2);
		tmr.adicionar(tms3);
		System.out.println("TESTE DE LISTAGEM");
		tipos = tmr.recuperarTodos();
		for(TipoMensagem tm: tipos){
			System.out.println(tm);
		}
		
		JOptionPane.showMessageDialog(null,"Confira os registros no banco de dados");
		
		System.out.println("TESTE DE EXCLUSÃO");
		for(TipoMensagem tm: tipos){
			tmr.remover(tm);
		}
		
		tipos = tmr.recuperarTodos();
		if(tipos.isEmpty()){
			System.out.println("TODOS OS REGISTROS FORAM EXCLUIDOS");
		}
		
	}

}
