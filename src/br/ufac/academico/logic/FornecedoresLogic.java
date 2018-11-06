package br.ufac.academico.logic;

import java.util.*;
import br.ufac.academico.db.*;
import br.ufac.academico.entity.*;
import br.ufac.academico.exception.*;

public class FornecedoresLogic {

	private FornecedoresDB fdb;
	private MunicipiosLogic ml;

	public FornecedoresLogic(Conexao cnx) {
		fdb = new FornecedoresDB(cnx);
		ml = new MunicipiosLogic(cnx);
	}

	public FornecedoresLogic(){
		fdb = new FornecedoresDB();
		ml = new MunicipiosLogic();
	}
	
	public void setConexao(Conexao cnx){
		fdb.setConexao(cnx);
		ml.setConexao(cnx);
	}
	
	public boolean addFornecedor(
	int forCodigo, long forMunCodigo, String forRazaoSocial, String forNomeContato,
	String forNomeFantasia, String forCnpj, String forEndereco, String forDataCadastro, String forFone,
	String forEmail, String forWebSite)
	throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityAlreadyExistException,
	InvalidFieldException{

		Municipios municipio = null;
		List<String> camposInvalidos = new ArrayList<String>();
		boolean haCamposInvalidos = false;

		if(forCodigo < 1 || String.valueOf(forCodigo).length() > 5){
			haCamposInvalidos = true;
			camposInvalidos.add("Codigo");
		}

		if(forRazaoSocial.isEmpty() || forRazaoSocial.length() > 100){
			haCamposInvalidos = true;
			camposInvalidos.add("Razão Social");
		}
		
		if(forNomeFantasia.isEmpty() || forNomeFantasia.length() > 50){
			haCamposInvalidos = true;
			camposInvalidos.add("Nome Fantasia");
		}
		
		if(forNomeContato.isEmpty() || forNomeContato.length() > 100){
			haCamposInvalidos = true;
			camposInvalidos.add("Nome Contato");
		}
		
		if(forCnpj.isEmpty() || forCnpj.length() > 14){
			haCamposInvalidos = true;
			camposInvalidos.add("CNPJ");
		}

		// VALIDAR OS DEMAIS CAMPOS DE OUTRAS TABELAS

		try {
			municipio = ml.getMunicipio(forMunCodigo);
		} catch (EntityNotExistException e) {
			haCamposInvalidos = true;
			camposInvalidos.add("Municipio");
		}		

		if (haCamposInvalidos){
			throw new InvalidFieldException("Fornecedor", camposInvalidos);
		}


		Fornecedores f = new Fornecedores(forCodigo, municipio, forRazaoSocial, forNomeContato, forNomeFantasia, forCnpj, forEndereco, forDataCadastro, forFone, forEmail, forWebSite);
		return fdb.addFornecedor(f);

	}

	public Fornecedores getFornecedor(long proForCodigo) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException 
	{

		return fdb.getFornecedor(proForCodigo);

	}

	public Fornecedores getFornecedorPorNomeFantasia(String nome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException 
	{

		return fdb.getFornecedorPorNomeFantasia(nome);

	}
	
	public boolean updFornecedores(
			int forCodigo, long forMunCodigo, String forRazaoSocial, String forNomeContato,
			String forNomeFantasia, String forCnpj, String forEndereco, String forDataCadastro, String forFone,
			String forEmail, String forWebSite) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException,
		InvalidFieldException 
	{

		Municipios municipio = null;
		List<String> camposInvalidos = new ArrayList<String>();
		boolean haCamposInvalidos = false;

		if(forCodigo < 1 || String.valueOf(forCodigo).length() > 5){
			haCamposInvalidos = true;
			camposInvalidos.add("Codigo");
		}

		if(forRazaoSocial.isEmpty() || forRazaoSocial.length() > 100){
			haCamposInvalidos = true;
			camposInvalidos.add("Razão Social");
		}
		
		if(forNomeFantasia.isEmpty() || forNomeFantasia.length() > 50){
			haCamposInvalidos = true;
			camposInvalidos.add("Nome Fantasia");
		}
		
		if(forNomeContato.isEmpty() || forNomeContato.length() > 100){
			haCamposInvalidos = true;
			camposInvalidos.add("Nome Contato");
		}
		
		if(forCnpj.isEmpty() || forCnpj.length() > 14){
			haCamposInvalidos = true;
			camposInvalidos.add("CNPJ");
		}

		// VALIDAR OS DEMAIS CAMPOS DE OUTRAS TABELAS

		try {
			municipio = ml.getMunicipio(forMunCodigo);
		} catch (EntityNotExistException e) {
			haCamposInvalidos = true;
			camposInvalidos.add("Municipio");
		}		

		if (haCamposInvalidos){
			throw new InvalidFieldException("Fornecedor", camposInvalidos);
		}


		Fornecedores f = new Fornecedores(forCodigo, municipio, forRazaoSocial, forNomeContato, forNomeFantasia, forCnpj, forEndereco, forDataCadastro, forFone, forEmail, forWebSite);
		return fdb.updFornecedor(f);

	}

	public boolean delFornecedores(int forCodigo, long forMunCodigo, String forRazaoSocial, String forNomeContato,
			String forNomeFantasia, String forCnpj, String forEndereco, String forDataCadastro, String forFone,
			String forEmail, String forWebSite) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException
	{		
		
		Municipios municipio = ml.getMunicipio(forMunCodigo);		

		Fornecedores f = new Fornecedores(forCodigo, municipio, forRazaoSocial, forNomeContato, forNomeFantasia, forCnpj, forEndereco, forDataCadastro, forFone, forEmail, forWebSite);

		return fdb.delMunicispios(f);

	}

	public List<Fornecedores> getFornecedores() throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException, 
		EntityNotExistException
	{
		return fdb.getFornecedores();
	}
	
	public List<String> getNomesFornecedores() throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return fdb.getNomesFornecedores();
}

	public List<Fornecedores> getFornecedoresPorNomeFantasia(String nome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException, 
		EntityNotExistException
	{
		return fdb.getFornecedoresPorNomeFantasia(nome);
	}
	
	public List<Fornecedores> getFornecedoresPorCnpj(String cnpj) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return fdb.getFornecedoresPorCnpj(cnpj);
}
	
	public List<Fornecedores> getFornecedoresPorMunicipio(String municipio) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return fdb.getFornecedoresPorMunicipio(municipio);
}
	
	
}










