package br.ufac.academico.testes;

import java.util.Date;
import java.util.List;

import br.ufac.academico.entidades.Cliente;
import br.ufac.academico.entidades.Municipio;
import br.ufac.academico.repositorios.ClienteRepositorio;
import br.ufac.academico.repositorios.MunicipioRepositorio;

public class ClienteTeste {
	
	public static void main(String[] args) {
		
		ClienteRepositorio cr = new ClienteRepositorio();
		MunicipioRepositorio mr = new MunicipioRepositorio();
		Date d1,d2,d3;
		List<Cliente> clientes;
		
		Municipio m1,m2,m3;
		Cliente c1,c2,c3;
		
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
		
		
		c1= new Cliente();
		c1.setCadastro(d1 = new Date());
		c1.setContato("Francisco H.");
		c1.setCpf("791.886.159-01");
		c1.setEmail("franciscohugorobertogalvao@rodrigofranco.com");
		c1.setEndereco("Rua Catuipe 279 Parque dos Anjos");
		c1.setFone("(51) 99803-6615");
		c1.setNome("Francisco Hugo Roberto Galvão");
		c1.setSexo("M");
		c1.setStatus("A");
		c1.setTipo(1);
		c1.setMunicipio(m1);
		
		c2= new Cliente();
		c2.setCadastro(d2 = new Date());
		c2.setContato("Natália F.");
		c2.setCpf("393.962.574-43");
		c2.setEmail("nnataliaoliviafogaca@alanamaral.com.br");
		c2.setEndereco("Rua Teresinha Lages Visgueira 508 Vale Quem Tem");
		c2.setFone("(86) 98215-5693");
		c2.setNome("Natália Olivia Fogaça");
		c2.setSexo("F");
		c2.setStatus("I");
		c2.setTipo(1);
		c2.setMunicipio(m2);
		
		c3= new Cliente();
		c3.setCadastro(d3 = new Date());
		c3.setContato("Francisco H.");
		c3.setCpf("791.886.159-01");
		c3.setEmail("franciscohugorobertogalvao@rodrigofranco.com");
		c3.setEndereco("Rua Catuipe 279 Parque dos Anjos");
		c3.setFone("(51) 99803-6615");
		c3.setNome("Francisco Hugo Roberto Galvão");
		c3.setSexo("M");
		c3.setStatus("A");
		c3.setTipo(1);
		c3.setMunicipio(m3);
		
		System.out.println("TESTANDO INSERÇÃO");
		cr.adicionar(c1);
		cr.adicionar(c2);
		cr.adicionar(c3);
		
		
		System.out.println("TESTANDO LISTAGEM");
		clientes = cr.recuperarTodos();
		for(Cliente cliente: clientes){
			System.out.println(cliente);
		}
		
		System.out.println("TESTANDO EXCLUSÃO");
		for(Cliente cliente: clientes){
			cr.remover(cliente);
		}
		clientes = cr.recuperarTodos();
		if(clientes.isEmpty()){
			System.out.println("TODOS OS REGISTRO FORAM REMOVIDOS");
		}
		
		
		
		
	}

}
