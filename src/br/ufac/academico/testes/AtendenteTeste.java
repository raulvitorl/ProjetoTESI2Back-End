package br.ufac.academico.testes;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufac.academico.entidades.Atendente;
import br.ufac.academico.entidades.enums.PerfilAtendente;
import br.ufac.academico.repositorios.AtendenteRepositorio;

public class AtendenteTeste {
	
	public static void main(String[] args) {
		
		AtendenteRepositorio ar = new AtendenteRepositorio();
		List<Atendente> atendentes;
		Atendente a1,a2,a3;
		
		a1 = new Atendente();
		a1.setCpf("027.725.062-58");
		a1.setEmail("raulawliet@gmail.com");
		a1.setNome("Raul Vitor Lopes da Costa");
		a1.setPerfil(PerfilAtendente.SUPERVISOR);
		a1.setRamal("7034");
		a1.setStatus('A');
		a1.setUltimoAcesso("2018-10-10");
		
		a2 = new Atendente();
		a2.setCpf("021.590.732-96");
		a2.setEmail("will_menezes@gmail.com");
		a2.setNome("José William Menezes Ribeiro");
		a2.setPerfil(PerfilAtendente.BALCONISTA);
		a2.setRamal("9735");
		a2.setStatus('A');
		a2.setUltimoAcesso("2017-05-04");
		
		a3 = new Atendente();
		a3.setCpf("043.892.560-25");
		a3.setEmail("e151845839@mailox.fun");
		a3.setNome("Anderson Thomas Corte Real");
		a3.setPerfil(PerfilAtendente.BALCONISTA);
		a3.setRamal("2845");
		a3.setStatus('I');
		a3.setUltimoAcesso("2015-12-12");
		
		System.out.println("TESTE DE ADIÇÃO");
		ar.adicionar(a1);
		ar.adicionar(a2);
		ar.adicionar(a3);
		
		atendentes = ar.recuperarTodos();
		System.out.println("TESTE DE LISTAGEM");
		for(Atendente atendente: atendentes){
			System.out.println(atendente);
		}
		
		JOptionPane.showMessageDialog(null, "Confira se os dados foram gerados no banco");
		JOptionPane.showMessageDialog(null, "Agora eles serão excluidos");
		atendentes = ar.recuperarTodos();
		System.out.println("TESTE DE EXCLUSÃO");
		for(Atendente atendente: atendentes){
			ar.remover(atendente);
		}
		atendentes = ar.recuperarTodos();
		if(atendentes.isEmpty()){
			System.out.println("TODOS OS REGISTROS FORAM EXCLUIDOS");
		}
		ar.encerrar();
	}
	
	

}
