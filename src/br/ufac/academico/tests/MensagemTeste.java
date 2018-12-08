package br.ufac.academico.tests;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufac.academico.domain.*;
import br.ufac.academico.domain.enums.PerfilAtendente;
import br.ufac.academico.repositories.*;

public class MensagemTeste {
		
	public static void main(String[] args) {
		
		AtendenteRepositorio ar = new AtendenteRepositorio();
		TipoMensagemRepositorio tmsr = new TipoMensagemRepositorio();
		MensagemRepositorio mr = new MensagemRepositorio();
		List<Mensagem> mensagens;
		
		Atendente a1,a2,a3;
		TipoMensagem tms1,tms2,tms3;
		Mensagem m1,m2,m3;
		@SuppressWarnings("unused")
		Date d1,d2,d3;
		
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
				
		tms1 = new TipoMensagem();
		tms1.setDescricao("Informativo");
		
		tms2 = new TipoMensagem();
		tms2.setDescricao("Memorando");
		
		tms3 = new TipoMensagem();
		tms3.setDescricao("Solicitação");
		
		m1 = new Mensagem();
		m1.setAtendente(a1);
		m1.setTexto("Pulei o mmuro e sai correndo, pau no cu de quem ta lendo");
		m1.setDataEnvio(d1 = new Date());
		m1.setTipo(tms1);
		
		m2 = new Mensagem();
		m2.setAtendente(a2);
		m2.setTexto("Corno é foda, tudo que vê lê");
		m2.setDataEnvio(d2 = new Date());
		m2.setTipo(tms2);
		
		m3 = new Mensagem();
		m3.setAtendente(a3);
		m3.setTexto("Além de gay é curioso");
		m3.setDataEnvio(d3 = new Date());
		m3.setTipo(tms3);
		
		System.out.println("TESTE DE INCLUSÃO");
		mr.adicionar(m1);
		mr.adicionar(m2);
		mr.adicionar(m3);
		
		System.out.println("TESTE DE LISTAGEM");
		mensagens = mr.recuperarTodos();
		for(Mensagem m: mensagens){
			System.out.println(m);
		}
		JOptionPane.showMessageDialog(null,"Confira os registros no banco de dados");
		System.out.println("TESTE DE EXCLUSÃO");
		mensagens = mr.recuperarTodos();
		for(Mensagem m: mensagens){
			mr.remover(m);
		}
		mensagens = mr.recuperarTodos();
		if(mensagens.isEmpty()){
			System.out.println("TODOS OS REGISTROS FORAM EXCLUIDOS");
		}
		
		mr.encerrar();
		ar.encerrar();
		tmsr.encerrar();
	}

}
