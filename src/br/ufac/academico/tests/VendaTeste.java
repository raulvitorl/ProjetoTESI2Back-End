package br.ufac.academico.tests;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufac.academico.domain.*;
import br.ufac.academico.domain.enums.FormaPagamento;
import br.ufac.academico.domain.enums.PerfilAtendente;
import br.ufac.academico.domain.enums.SexoCliente;
import br.ufac.academico.domain.enums.StatusAtendente;
import br.ufac.academico.domain.enums.StatusCliente;
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
		a1.setStatus(StatusAtendente.DISPONIVEL);
		a1.setUltimoAcesso(d1 = new Date());
		
		a2 = new Atendente();
		a2.setCpf("021.590.732-96");
		a2.setEmail("will_menezes@gmail.com");
		a2.setNome("José William Menezes Ribeiro");
		a2.setPerfil(PerfilAtendente.BALCONISTA);
		a2.setRamal("9735");
		a2.setStatus(StatusAtendente.DISPONIVEL);
		a2.setUltimoAcesso(d2 = new Date());
		
		a3 = new Atendente();
		a3.setCpf("043.892.560-25");
		a3.setEmail("e151845839@mailox.fun");
		a3.setNome("Anderson Thomas Corte Real");
		a3.setPerfil(PerfilAtendente.BALCONISTA);
		a3.setRamal("2845");
		a3.setStatus(StatusAtendente.DISPONIVEL);;
		a3.setUltimoAcesso(d3 = new Date());
				
		c1= new Cliente();
		c1.setCadastro(d1 = new Date());
		c1.setContato("Francisco H.");
		c1.setCpf("791.886.159-01");
		c1.setEmail("franciscohugorobertogalvao@rodrigofranco.com");
		c1.setEndereco("Rua Catuipe 279 Parque dos Anjos");
		c1.setFone("(51) 99803-6615");
		c1.setNome("Francisco Hugo Roberto Galvão");
		c1.setSexo(SexoCliente.MASCULINO);
		c1.setStatus(StatusCliente.CREDOR);
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
		c2.setStatus(StatusCliente.DEVEDOR);
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
		c3.setStatus(StatusCliente.QUITADO);
		c3.setTipo(TipoCliente.PESSOAFISICA);
		c3.setMunicipio(m3);

		v1 = new Venda();
		v1.setAtendente(a1);
		v1.setBanco(b1);
		v1.setCliente(c1);
		v1.setFormaPagamento(FormaPagamento.DINHEIRO);
		v1.setObservacoes("Nenhuma Observação");
		v1.setStatusPagamento(StatusPagamento.PENDENTE);
		v1.setValorTotal((float) 170.10);

		v2 = new Venda();
		v2.setAtendente(a2);
		v2.setBanco(b2);
		v2.setCliente(c2);
		v2.setFormaPagamento(FormaPagamento.CARTAO);
		v2.setObservacoes("Nenhuma Observação");
		v2.setStatusPagamento(StatusPagamento.PAGO);
		v2.setValorTotal((float) 45.00);

		v3 = new Venda();
		v3.setAtendente(a3);
		v3.setBanco(b3);
		v3.setCliente(c3);
		v3.setFormaPagamento(FormaPagamento.DINHEIRO);
		v3.setObservacoes("Nenhuma Observação");
		v3.setStatusPagamento(StatusPagamento.PENDENTE);
		v3.setValorTotal((float) 170.10);
		
		CategoriaProduto cp1,cp2,cp3;
		
		
		
		cp1 = new CategoriaProduto();
		cp1.setDescricao("Higiene Pessoal");
		cp1.setIdentificador(1);
		
		cp2 = new CategoriaProduto();
		cp2.setDescricao("Móveis");
		cp2.setIdentificador(2);
		
		cp3 = new CategoriaProduto();
		cp3.setDescricao("Eletrônicos");
		cp3.setIdentificador(3);
				
		
		
		m1 =new Municipio();
		m1.setCep("69911036");
		m1.setNome("Rio Branco");
		m1.setUfEstado("AC");
		
		m2 =new Municipio();
		m2.setCep("69736374");
		m2.setNome("Sea Madureira");
		m2.setUfEstado("AC");
		
		m3 =new Municipio();
		m3.setCep("87299365");
		m3.setNome("Maragogi");
		m3.setUfEstado("PE");
		
		Fornecedor f1,f2,f3;
		f1 = new Fornecedor();
		f1.setCnpj("52.933.955/0001-03");
		f1.setDataCadastro(d1 = new Date());
		f1.setEmail("kevinemanuelrodrigoaragao_@ligananet.com.br");
		f1.setEndereco("Avenida Delmiro Gouveia 366 Paratibe");
		f1.setFone("(81) 99234-5159");
		f1.setMunicipio(m1);
		f1.setNomeContato("Empresa Junior");
		f1.setNomeFantasia("FlorestAcre");
		f1.setRazaoSocial("FlorestAce LTDA");
		f1.setWebSite("httphs://google.com.br");
		
		f2 = new Fornecedor();
		f2.setCnpj("27.877.495/0001-98");
		f1.setDataCadastro(d2 = new Date());
		f2.setEmail("luccapedromiguellimaluccapedromiguellima@eccofibra.com.br");
		f2.setEndereco("Rua Lagoa da Prata 962 Jardim Centenário");
		f2.setFone("(67) 99702-8676");
		f2.setMunicipio(m2);
		f2.setNomeContato("Empresa");
		f2.setNomeFantasia("Empresa1");
		f2.setRazaoSocial("Empresa2");
		f2.setWebSite("httphs://xvideos.com.br");
		
		f3 = new Fornecedor();
		f3.setCnpj("64.483.286/0001-96");
		f1.setDataCadastro(d3 = new Date());
		f3.setEmail("giovannithomascaiosales_@vemter.com.br");
		f3.setEndereco("Travessa Anjo Caliel 415 Colônia Terra Nova");
		f3.setFone("(92) 99653-3086");
		f3.setMunicipio(m3);
		f3.setNomeContato("Firma");
		f3.setNomeFantasia("Firma1");
		f3.setRazaoSocial("Firma2");
		f3.setWebSite("httphs://faacebook.com.br");
				
		Produto p1,p2,p3;
		
		p1 = new Produto();
		p1.setCategoria(cp1);
		p1.setDescricao("Sabonete");
		p1.setDetalhes("Aroma de flores");
		p1.setFabricante("Sua Mãe");
		p1.setFornecedor(f1);
		p1.setQntDisponivel(12);
		p1.setUltimaAquisicao("2018-10-10");
		p1.setValorUnitario((float) 10.50);
		
		p2 = new Produto();
		p2.setCategoria(cp2);
		p2.setDescricao("Sofá");
		p2.setDetalhes("Sofá Cama");
		p2.setFabricante("Seu Pai");
		p2.setFornecedor(f2);
		p2.setQntDisponivel(10);
		p2.setUltimaAquisicao("2018-11-11");
		p2.setValorUnitario((float) 13.50);
		
		p3 = new Produto();
		p3.setCategoria(cp3);
		p3.setDescricao("Teclado");
		p3.setDetalhes("Brilha no escuro");
		p3.setFabricante("Seu Tio");
		p3.setFornecedor(f3);
		p3.setQntDisponivel(20);
		p3.setUltimaAquisicao("2018-12-12");
		p3.setValorUnitario((float) 30.50);
		
		f1.setProdutos(Arrays.asList(p1));
		f2.setProdutos(Arrays.asList(p2));
		v1.setProdutos(Arrays.asList(p1,p2));

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
