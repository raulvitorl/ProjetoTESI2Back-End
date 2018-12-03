package br.ufac.academico.testes;

import br.ufac.academico.entidades.Atendente;
import br.ufac.academico.entidades.Mensagem;
import br.ufac.academico.entidades.TipoMensagem;
import br.ufac.academico.repositorios.AtendentesRepositorio;
import br.ufac.academico.repositorios.MensagensRepositorio;
import br.ufac.academico.repositorios.TiposMensagensRepositorio;

public class MensagemTeste {
		
	public static void main(String[] args) {
		
		AtendentesRepositorio ar = new AtendentesRepositorio();
		TiposMensagensRepositorio tmsr = new TiposMensagensRepositorio();
		MensagensRepositorio mr = new MensagensRepositorio();
		
		Atendente a = ar.recuperar(4);
		TipoMensagem tms = tmsr.recuperar(5);
		Mensagem m = mr.recuperar(1);
		System.out.println(m.toString());
		
	}

}
