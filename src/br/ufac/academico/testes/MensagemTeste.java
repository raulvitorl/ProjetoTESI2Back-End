package br.ufac.academico.testes;

import br.ufac.academico.entidades.Atendente;
import br.ufac.academico.entidades.Mensagem;
import br.ufac.academico.entidades.TipoMensagem;
import br.ufac.academico.repositorios.AtendenteRepositorio;
import br.ufac.academico.repositorios.MensagemRepositorio;
import br.ufac.academico.repositorios.TipoMensagemRepositorio;

public class MensagemTeste {
		
	public static void main(String[] args) {
		
		AtendenteRepositorio ar = new AtendenteRepositorio();
		TipoMensagemRepositorio tmsr = new TipoMensagemRepositorio();
		MensagemRepositorio mr = new MensagemRepositorio();
		
		Atendente a = ar.recuperar(4);
		TipoMensagem tms = tmsr.recuperar(5);
		Mensagem m = mr.recuperar(1);
		System.out.println(m.toString());
		
	}

}
