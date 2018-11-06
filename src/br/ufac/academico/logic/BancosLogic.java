package br.ufac.academico.logic;

import java.util.*;
import br.ufac.academico.db.*;
import br.ufac.academico.entity.*;
import br.ufac.academico.exception.*;

public class BancosLogic {

	private BancosDB bdb;
	
	public BancosLogic(Conexao cnx) {
		bdb = new BancosDB(cnx);
	}
	
	public BancosLogic(){
		bdb = new BancosDB();
	}
	
	public void setConexao(Conexao cnx){
		bdb.setConexao(cnx);
	}
	
	public boolean addBanco(int banCodigo, String banNome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityAlreadyExistException,
		InvalidFieldException 
	{

		List<String> camposInvalidos = new ArrayList<String>();
		boolean haCamposInvalidos = false;

		if(String.valueOf(banCodigo).length() > 3){
			haCamposInvalidos = true;
			camposInvalidos.add("Codigo");
		}
		
		if(banNome.isEmpty() || banNome.length() > 50){
			haCamposInvalidos = true;
			camposInvalidos.add("Descrição");
		}
		
		

		if (haCamposInvalidos){
			throw new InvalidFieldException("Bancos", camposInvalidos);
		}			
		
		Banco b = new Banco(banCodigo,banNome);
		return bdb.addBanco(b);
		
	}
	
	public Banco getBanco(long banCodigo) throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityNotExistException 
	{

		return bdb.getBanco(banCodigo);
		
	}

	public Banco getBancoPorNome(String banNome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException 
{

	return bdb.getBancoPorNome(banNome);
	
}
	
	
	public boolean updBanco(int banCodigo, String banNome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException,
		InvalidFieldException
	{

	List<String> camposInvalidos = new ArrayList<String>();
	boolean haCamposInvalidos = false;

	if(String.valueOf(banCodigo).length() > 3){
		haCamposInvalidos = true;
		camposInvalidos.add("Codigo");
	}
	
	if(banNome.isEmpty() || banNome.length() > 50){
		haCamposInvalidos = true;
		camposInvalidos.add("Nome");
	}

	if (haCamposInvalidos){
		throw new InvalidFieldException("Bancos", camposInvalidos);
	}	
	Banco b = new Banco(banCodigo, banNome);
	return bdb.updBanco(b);
	}
	
	public boolean delBanco(int banCodigo, String banNome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException 
	{
		Banco b = new Banco(banCodigo, banNome);
		return bdb.delBanco(b);
	}
	
	public List<Banco> getBancos() throws
		DataBaseGenericException,
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException
	{
		return bdb.getBancos();
	}
	
	public List<Banco> getBancosPorNome(String banNome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException
{
	return bdb.getBancosPorNome(banNome);
}
	
	public List<String> getBancosNomes() throws
	DataBaseGenericException,
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException
{
	return bdb.getBancosNomes();
}
	
	
	
}
	
	











