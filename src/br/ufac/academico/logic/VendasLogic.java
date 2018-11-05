package br.ufac.academico.logic;

import java.util.*;
import br.ufac.academico.db.*;
import br.ufac.academico.entity.*;
import br.ufac.academico.exception.*;

public class VendasLogic {

	private VendasDB vdb;
	private AtendentesLogic al;
	private BancosLogic bl;
	private ClientesLogic cl;	

	public VendasLogic(Conexao cnx) {
		vdb = new VendasDB(cnx);
		al = new AtendentesLogic(cnx);
		bl = new BancosLogic(cnx);
		cl = new ClientesLogic(cnx);
	}

	public boolean addProduto(int venCodigo, int cliCodigo, int ateCodigo, int banCodigo, float venValorTotal,
	String venFormaPagamento, String venObservacoes)
	throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityAlreadyExistException,
	InvalidFieldException{

		Atendente a = null;
		Cliente c = null;
		Banco b = null;
		
		List<String> camposInvalidos = new ArrayList<String>();
		boolean haCamposInvalidos = false;

		if(venCodigo < 1 || String.valueOf(venCodigo).length() > 5){
			haCamposInvalidos = true;
			camposInvalidos.add("Codigo");
		}
		
		if(venValorTotal < 0){
			haCamposInvalidos = true;
			camposInvalidos.add("Valor Total");
		}
		
		// VALIDAR OS DEMAIS CAMPOS DE OUTRAS TABELAS

		try {
			a = al.getAtendente(ateCodigo);
		} catch (EntityNotExistException e) {
			haCamposInvalidos = true;
			camposInvalidos.add("Atendente");
		}		

		
		try {
			c = cl.getCliente(cliCodigo);
		} catch (EntityNotExistException e) {
			haCamposInvalidos = true;
			camposInvalidos.add("Cliente");
		}		
		
		
		try {
			b = bl.getBanco(banCodigo);
		} catch (EntityNotExistException e) {
			haCamposInvalidos = true;
			camposInvalidos.add("Banco");
		}		

		if (haCamposInvalidos){
			throw new InvalidFieldException("Produtos", camposInvalidos);
		}
		
		


		Vendas v = new Vendas(venCodigo, c, a, b, venValorTotal, venFormaPagamento, venObservacoes);
		return vdb.addVenda(v);

	}

	public Vendas getVenda(int codigo) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException 
	{

		return vdb.getVenda(codigo);

	}

	public boolean updVenda(int venCodigo, int cliCodigo, int ateCodigo, int banCodigo, float venValorTotal,
			String venFormaPagamento, String venObservacoes)
	throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityNotExistException,
	InvalidFieldException{

		Atendente a = null;
		Cliente c = null;
		Banco b = null;
		
		List<String> camposInvalidos = new ArrayList<String>();
		boolean haCamposInvalidos = false;

		if(venCodigo < 1 || String.valueOf(venCodigo).length() > 5){
			haCamposInvalidos = true;
			camposInvalidos.add("Codigo");
		}
		
		if(venValorTotal < 0){
			haCamposInvalidos = true;
			camposInvalidos.add("Valor Total");
		}
		
		// VALIDAR OS DEMAIS CAMPOS DE OUTRAS TABELAS

		try {
			a = al.getAtendente(ateCodigo);
		} catch (EntityNotExistException e) {
			haCamposInvalidos = true;
			camposInvalidos.add("Atendente");
		}		

		
		try {
			c = cl.getCliente(cliCodigo);
		} catch (EntityNotExistException e) {
			haCamposInvalidos = true;
			camposInvalidos.add("Cliente");
		}		
		
		
		try {
			b = bl.getBanco(banCodigo);
		} catch (EntityNotExistException e) {
			haCamposInvalidos = true;
			camposInvalidos.add("Banco");
		}		

		if (haCamposInvalidos){
			throw new InvalidFieldException("Produtos", camposInvalidos);
		}
		
		


		Vendas v = new Vendas(venCodigo, c, a, b, venValorTotal, venFormaPagamento, venObservacoes);
		return vdb.updVenda(v);


	}

	public boolean delVenda(int codigo) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException
	{		
		Vendas v = vdb.getVenda(codigo);
		return vdb.delVenda(v);

	}

	public List<Vendas> getVendas() throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException, 
		EntityNotExistException
	{
		return vdb.getVendas();
	}

	public List<Vendas> getVendasPorClienteNome(String descricao) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException, 
		EntityNotExistException
	{
		return vdb.getVendasPorClienteNome(descricao);
	}
	
	public List<Vendas> getVendasPorClienteCodigo(int codigo) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return vdb.getVendasPorClienteCodigo(codigo);
}
	
	public List<Vendas> getVendasPorAtendenteNome(String nome) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return vdb.getVendasPorAtendenteNome(nome);
}

	public List<Vendas> getVendasPorAtendenteCodigo(int codigo) throws
DataBaseGenericException, 
DataBaseNotConnectedException, 
EntityTableIsEmptyException, 
EntityNotExistException
{
return vdb.getVendasPorAtendenteCodigo(codigo);
}

	public List<Vendas> getVendasPorBancoNome(String bancoNome) throws
DataBaseGenericException, 
DataBaseNotConnectedException, 
EntityTableIsEmptyException, 
EntityNotExistException
{
return vdb.getVendasPorBancoNome(bancoNome);
}

	public List<Vendas> getVendasPorBancoCodigo(int codigo) throws
DataBaseGenericException, 
DataBaseNotConnectedException, 
EntityTableIsEmptyException, 
EntityNotExistException
{
return vdb.getVendasPorBancoCodigo(codigo);
}
	
	
}










