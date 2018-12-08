package br.ufac.academico.tests;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufac.academico.domain.Municipio;
import br.ufac.academico.repositories.MunicipioRepositorio;

public class MunicipioTeste {
	
	public static void main(String[] args) {
		MunicipioRepositorio mr = new MunicipioRepositorio();
		List<Municipio> municipios;
		
		Municipio m1,m2,m3;
		
		m1 = new Municipio();
		m1.setCep("69911036");
		m1.setNome("Rio Branco");
		m1.setUfEstado("AC");
		
		m2 = new Municipio();
		m2.setCep("82736439");
		m2.setNome("Rio de Janeiro");
		m2.setUfEstado("RJ");
		
		m3 = new Municipio();
		m3.setCep("6379272637");
		m3.setNome("São Paulo");
		m3.setUfEstado("SP");
		
		System.out.println("TESTE DE INCLUSÃO");
		mr.adicionar(m1);
		mr.adicionar(m2);
		mr.adicionar(m3);
		System.out.println("TESTE DE LISTAGEM");
		municipios = mr.recuperarTodos();
		for(Municipio m: municipios) {
			System.out.println(m);
		}
		JOptionPane.showMessageDialog(null, "Confira se os dados foram gerados no banco");
		JOptionPane.showMessageDialog(null, "Agora eles serão excluidos");
		System.out.println("TESTE DE EXCLUSÃO");
		for(Municipio m: municipios) {
			mr.remover(m);
		}
		
		municipios = mr.recuperarTodos();
		if(municipios.isEmpty()){
			System.out.println("TODOS OS REGISTROS FORAM EXCLUIDOS");
		}		
		
		mr.encerrar();
		
		
	}

}
