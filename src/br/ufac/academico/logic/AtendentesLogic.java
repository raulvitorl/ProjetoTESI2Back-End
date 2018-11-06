package br.ufac.academico.logic;

import java.util.*;
import br.ufac.academico.db.*;
import br.ufac.academico.entity.*;
import br.ufac.academico.exception.*;

public class AtendentesLogic {

	private AtendenteDB adb;
	public AtendentesLogic(Conexao cnx) {
		adb = new AtendenteDB(cnx);
	}
	
	public AtendentesLogic(){
		adb = new AtendenteDB();
	}
	
	public void setConexao(Conexao cnx){
		adb.setConexao(cnx);
	}
	
	public boolean addMensagem(int codigo, String nome, String ultimoAcesso, String ramal, String email,
			char status) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityAlreadyExistException,
		InvalidFieldException 
	{

		List<String> camposInvalidos = new ArrayList<String>();
		boolean haCamposInvalidos = false;

		if(nome.isEmpty() || nome.length() > 50){
			haCamposInvalidos = true;
			camposInvalidos.add("Nome");
		}
		
		if(String.valueOf(codigo).isEmpty() || String.valueOf(codigo).length() > 5){
			haCamposInvalidos = true;
			camposInvalidos.add("Código");
		}
		
		if(ultimoAcesso.isEmpty()){
			haCamposInvalidos = true;
			camposInvalidos.add("Ultimo Acesso");
		}	

		if (haCamposInvalidos){
			throw new InvalidFieldException("Atendentes", camposInvalidos);
		}			
		
		Atendente a = new Atendente(codigo, nome, ultimoAcesso, ramal, email, status);
		return adb.addAtendente(a);
		
	}
	public Atendente getAtendente(long ateCodigo) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException 
{

	return adb.getAtendente(ateCodigo);
	
}
	
	public boolean updAtendente(int codigo, String nome, String ultimoAcesso, String ramal, String email,
			char status) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException,
		InvalidFieldException
	{

		List<String> camposInvalidos = new ArrayList<String>();
		boolean haCamposInvalidos = false;

		if(nome.isEmpty() || nome.length() > 50){
			haCamposInvalidos = true;
			camposInvalidos.add("Nome");
		}
		
		if(String.valueOf(codigo).isEmpty() || String.valueOf(codigo).length() > 5){
			haCamposInvalidos = true;
			camposInvalidos.add("Código");
		}
		
		if(ultimoAcesso.isEmpty()){
			haCamposInvalidos = true;
			camposInvalidos.add("Ultimo Acesso");
		}	

		if (haCamposInvalidos){
			throw new InvalidFieldException("Atendentes", camposInvalidos);
		}			
		
		Atendente a = new Atendente(codigo, nome, ultimoAcesso, ramal, email, status);
		return adb.updAtendente(a);
	}
	
	public boolean delMunicipio(int codigo, String nome, String ultimoAcesso, String ramal, String email,
			char status) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException 
	{
		Atendente a = new Atendente(codigo, nome, ultimoAcesso, ramal, email, status);
		return adb.delAtendente(a);
	}
	
	public List<Atendente> getAtendentes() throws
		DataBaseGenericException,
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException
	{
		return adb.getAtendentes();
	}
	
	
	public Atendente getAtendentePorNome(String nome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return adb.getAtendentePorNome(nome);
}
	
	public List<String> getNomesAtendentes() throws
	DataBaseGenericException,
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException
{
	return adb.getNomesAtendentes();
}
		
	public List<Atendente> getAtendentesPorNome(String nome) throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityTableIsEmptyException
	{
		return adb.getAtendentesPorNome(nome);
	}
	
}










