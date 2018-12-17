package br.ufac.academico.tests;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufac.academico.domain.Fornecedor;
import br.ufac.academico.domain.Municipio;
import br.ufac.academico.repositories.FornecedorRepositorio;
import br.ufac.academico.repositories.MunicipioRepositorio;

public class FornecedorTeste {
	
	public static void main(String[] args) {
		
		MunicipioRepositorio mr = new MunicipioRepositorio();
		FornecedorRepositorio fr = new FornecedorRepositorio();
		List<Fornecedor> fornecedores;
		
		
		@SuppressWarnings("unused")
		Date d1,d2,d3;
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
		
		System.out.println("INCLUINDO OS MUNICIPIOS");
				
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
		f2.setDataCadastro(d2 = new Date());
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
		f3.setDataCadastro(d3 = new Date());
		f3.setEmail("giovannithomascaiosales_@vemter.com.br");
		f3.setEndereco("Travessa Anjo Caliel 415 Colônia Terra Nova");
		f3.setFone("(92) 99653-3086");
		f3.setMunicipio(m3);
		f3.setNomeContato("Firma");
		f3.setNomeFantasia("Firma1");
		f3.setRazaoSocial("Firma2");
		f3.setWebSite("httphs://faacebook.com.br");
		
		System.out.println("TESTE DE INCLUSÇAO");
		fr.adicionar(f1);
		fr.adicionar(f2);
		fr.adicionar(f3);
		System.out.println("TESTE DE LISTAGEM");
		fornecedores = fr.recuperarTodos();
		for(Fornecedor f : fornecedores){
			System.out.println(f);
		}
		JOptionPane.showMessageDialog(null, "Confira se os dados foram gerados no banco");
		JOptionPane.showMessageDialog(null, "Agora eles serão excluidos");
		System.out.println("TESTE DE EXCLUSÃO");
		fornecedores = fr.recuperarTodos();
		for(Fornecedor f : fornecedores){
			fr.remover(f);;
		}
		fornecedores = fr.recuperarTodos();
		if(fornecedores.isEmpty()){
			System.out.println("TODOS OS REGISTROS FORAM EXCLUIDOS");
		}
		
		mr.encerrar();
		fr.encerrar();
		
	}

}
