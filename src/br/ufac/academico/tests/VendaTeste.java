package br.ufac.academico.tests;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufac.academico.domain.*;
import br.ufac.academico.domain.enums.PerfilAtendente;
import br.ufac.academico.domain.enums.SexoCliente;
import br.ufac.academico.domain.enums.StatusPagamento;
import br.ufac.academico.domain.enums.TipoCliente;
import br.ufac.academico.repositories.*;

public class VendaTeste {

	public static void main(String[] args) {

		VendaRepositorio vr = new VendaRepositorio();
		BancoRepositorio br = new BancoRepositorio();
		AtendenteRepositorio ar = new AtendenteRepositorio();
		MunicipioRepositorio mr = new MunicipioRepositorio();
		List<Venda> vendas;

		@SuppressWarnings("unused")
		Date d1,d2,d3;
		Banco b1,b2,b3;
		Atendente a1,a2,a3;
		Cliente c1,c2,c3;
		Municipio m1,m2,m3;
		Venda v1,v2,v3;

		b1 = new Banco();
		b1.setNome("Banco do Brasil");

		b2 = new Banco();
		b2.setNome("Nubank");

		b3 = new Banco();
		b3.setNome("Digio");

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

		c1= new Cliente();
		c1.setCadastro(d1 = new Date());
		c1.setContato("Francisco H.");
		c1.setCpf("791.886.159-01");
		c1.setEmail("franciscohugorobertogalvao@rodrigofranco.com");
		c1.setEndereco("Rua Catuipe 279 Parque dos Anjos");
		c1.setFone("(51) 99803-6615");
		c1.setNome("Francisco Hugo Roberto Galvão");
		c1.setSexo(SexoCliente.MASCULINO);
		c1.setStatus("A");
		c1.setTipo(TipoCliente.PESSOAFISICA);
		c1.setMunicipio(m1);

		c2= new Cliente();
		c2.setCadastro(d2 = new Date());
		c2.setContato("Natália F.");
		c2.setCpf("393.962.574-43");
		c2.setEmail("nnataliaoliviafogaca@alanamaral.com.br");
		c2.setEndereco("Rua Teresinha Lages Visgueira 508 Vale Quem Tem");
		c2.setFone("(86) 98215-5693");
		c2.setNome("Natália Olivia Fogaça");
		c2.setSexo(SexoCliente.FEMININO);
		c2.setStatus("I");
		c2.setTipo(TipoCliente.PESSOAFISICA);
		c2.setMunicipio(m2);

		c3= new Cliente();
		c3.setCadastro(d3 = new Date());
		c3.setContato("Francisco H.");
		c3.setCpf("791.886.159-01");
		c3.setEmail("franciscohugorobertogalvao@rodrigofranco.com");
		c3.setEndereco("Rua Catuipe 279 Parque dos Anjos");
		c3.setFone("(51) 99803-6615");
		c3.setNome("Francisco Hugo Roberto Galvão");
		c3.setSexo(SexoCliente.MASCULINO);
		c3.setStatus("A");
		c3.setTipo(TipoCliente.PESSOAFISICA);
		c3.setMunicipio(m3);

		v1 = new Venda();
		v1.setAtendente(a1);
		v1.setBanco(b1);
		v1.setCliente(c1);
		v1.setFormaPagamento("A Vista");
		v1.setObservacoes("Nenhuma Observação");
		v1.setStatusPagamento(StatusPagamento.PENDENTE);
		v1.setValorTotal(170.10);

		v2 = new Venda();
		v2.setAtendente(a2);
		v2.setBanco(b2);
		v2.setCliente(c2);
		v2.setFormaPagamento("Cartão");
		v2.setObservacoes("Nenhuma Observação");
		v2.setStatusPagamento(StatusPagamento.PAGO);
		v2.setValorTotal(45.00);

		v3 = new Venda();
		v3.setAtendente(a3);
		v3.setBanco(b3);
		v3.setCliente(c3);
		v3.setFormaPagamento("A Vista");
		v3.setObservacoes("Nenhuma Observação");
		v3.setStatusPagamento(StatusPagamento.PENDENTE);
		v3.setValorTotal(170.10);

		System.out.println("TESTE DE INCLUSÃO");
		vr.adicionar(v1);
		vr.adicionar(v2);
		vr.adicionar(v3);
		vendas = vr.recuperarTodos();
		System.out.println("TESTE DE LISTAGEM");
		for(Venda v: vendas){
			System.out.println(v);
		}		
		JOptionPane.showMessageDialog(null, "Confira se os dados foram gerados no banco");
		JOptionPane.showMessageDialog(null, "Agora eles serão excluidos");
		for(Venda v: vendas){
			vr.remover(v);
		}
		vendas = vr.recuperarTodos();
		if(vendas.isEmpty()){
			System.out.println("Todos os registros foram excluidos!");
		}
		
		vr.encerrar();
		br.encerrar();
		ar.encerrar();
		mr.encerrar();


	}

}
