package br.ufac.academico.tests;

import java.util.*;

import javax.swing.JOptionPane;

import br.ufac.academico.domain.*;
import br.ufac.academico.repositories.*;;

public class ProdutoTeste {
	
	public static void main(String[] args) {
		ProdutoRepositorio pr = new ProdutoRepositorio();
		CategoriaProdutoRepositorio cpr = new CategoriaProdutoRepositorio();
		List<Produto> produtos;
		
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
				
		
		Municipio m1,m2,m3;
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
		f1.setDataCadastro("2018-10-10");
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
		f2.setDataCadastro("2018-11-11");
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
		f3.setDataCadastro("2018-12-12");
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
		
		System.out.println("TESTE DE INCLUSÃO");
		pr.adicionar(p1);
		pr.adicionar(p2);
		pr.adicionar(p3);
		System.out.println("TESTE DE LISTAGEM");
		produtos = pr.recuperarTodos();
		for(Produto p: produtos){
			System.out.println(p);
		}
		JOptionPane.showMessageDialog(null, "Confira se os dados foram gerados no banco");
		JOptionPane.showMessageDialog(null, "Agora eles serão excluidos");
		System.out.println("TESTE DE EXCLUSÃO");
		for(Produto p: produtos){
			pr.remover(p);
		}
		
		produtos = pr.recuperarTodos();
		if(produtos.isEmpty()){
			System.out.println("TODOS OS ELEMENTOS FORAM REMOVIDOS");
		}
		
		pr.encerrar();
		cpr.encerrar();
		
		
		
	}

}
