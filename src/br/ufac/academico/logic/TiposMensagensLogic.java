package br.ufac.academico.logic;

import java.util.*;
import br.ufac.academico.db.*;
import br.ufac.academico.entity.*;
import br.ufac.academico.exception.*;

public class TiposMensagensLogic {

	private TiposMensagensDB bdb;
	
	public TiposMensagensLogic(Conexao cnx) {
		bdb = new TiposMensagensDB(cnx);
	}
	
	
	
	
	public TiposMensagensLogic() {
		bdb = new TiposMensagensDB();
	}
	
	public void setConexao(Conexao cnx) {
		bdb.setConexao(cnx);
	}




	public boolean addTipoMensagem(int tmsCodigo, String tmsDescricao) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityAlreadyExistException,
		InvalidFieldException 
	{

		List<String> camposInvalidos = new ArrayList<String>();
		boolean haCamposInvalidos = false;

		if(String.valueOf(tmsCodigo).length() > 3){
			haCamposInvalidos = true;
			camposInvalidos.add("Codigo");
		}
		
		if(tmsDescricao.isEmpty() || tmsDescricao.length() > 50){
			haCamposInvalidos = true;
			camposInvalidos.add("Descrição");
		}
		
		

		if (haCamposInvalidos){
			throw new InvalidFieldException("Tipos de Mensagem", camposInvalidos);
		}			
		
		TipoMensagens tm = new TipoMensagens(tmsCodigo, tmsDescricao);
		return bdb.addTipoMensagens(tm);
		
	}
	
	public TipoMensagens getTipoMensagem(int tmsCodigo) throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityNotExistException 
	{

		return bdb.getTipoMensagens(tmsCodigo);
		
	}

	public TipoMensagens getTipoMensagensPorDescricao(String descricao) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException 
{

	return bdb.getTipoMensagensPorDescricao(descricao);
	
}
	
	
	public boolean updTipoMensagens(int tmsCodigo, String tmsDescricao) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException,
		InvalidFieldException
	{

	List<String> camposInvalidos = new ArrayList<String>();
	boolean haCamposInvalidos = false;

	if(String.valueOf(tmsCodigo).length() > 3){
		haCamposInvalidos = true;
		camposInvalidos.add("Codigo");
	}
	
	if(tmsDescricao.isEmpty() || tmsDescricao.length() > 50){
		haCamposInvalidos = true;
		camposInvalidos.add("Descricao");
	}

	if (haCamposInvalidos){
		throw new InvalidFieldException("Tipo Mensagens", camposInvalidos);
	}	
	TipoMensagens tm = new TipoMensagens(tmsCodigo, tmsDescricao);
	return bdb.updTipoMensagens(tm);
	}
	
	public boolean delTipoMensagem(int tmsCodigo, String tmsDescricao) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException 
	{
		TipoMensagens tm = new TipoMensagens(tmsCodigo, tmsDescricao);
		return bdb.delTipoMensagens(tm);
	}
	
	public List<TipoMensagens> getTiposMensagens() throws
		DataBaseGenericException,
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException
	{
		return bdb.getTiposMensagens();
	}
	
	public List<String> getTipoMensagensDescricoes() throws
	DataBaseGenericException,
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException
{
	return bdb.getTipoMensagensDescricoes();
}
	
	
	
}
	
	











