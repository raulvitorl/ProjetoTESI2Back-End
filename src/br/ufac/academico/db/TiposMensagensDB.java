package br.ufac.academico.db;

import java.sql.*;
import java.util.*;

import br.ufac.academico.entity.TipoMensagens;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;
import br.ufac.academico.exception.EntityAlreadyExistException;
import br.ufac.academico.exception.EntityNotExistException;
import br.ufac.academico.exception.EntityTableIsEmptyException;

public class TiposMensagensDB {
	
	private Conexao cnx;
	private ResultSet rs;
	
	public TiposMensagensDB(Conexao cnx) {
		this.cnx = cnx;
	}
	
	

	public TiposMensagensDB() {
	
	}
	
	public void setConexao(Conexao cnx){
		this.cnx = cnx;
	}



	public boolean addTipoMensagens(TipoMensagens tm) throws 
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityAlreadyExistException
	{

		String strInsercao ="INSERT INTO tipo_mensagens (TMS_CODIGO, TMS_DESCRICAO) VALUES ("+tm.getTmsCodigo()+",'"+tm.getTmsDescricao()+"');";

		try {
			getTipoMensagens(tm.getTmsCodigo());
			throw new EntityAlreadyExistException("TipoMensagens (codigo='" + tm.getTmsCodigo() + "')"); 
		} catch (EntityNotExistException e) {
			return cnx.atualize(strInsercao) > 0;
		}
		
	}
	
	public TipoMensagens getTipoMensagensPorDescricao (String tipoMensagemCodigo) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{

		TipoMensagens b = null;
		
		String strBusca = "SELECT * FROM tipo_mensagens WHERE TMS_DESCRICAO = " + tipoMensagemCodigo + "";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				b = new TipoMensagens(rs.getInt(1), rs.getString(2)); 
			}else {
				throw new EntityNotExistException("TipoMensagens (codigo='" + tipoMensagemCodigo + "')");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return b;
	}
	
	public TipoMensagens getTipoMensagens (long l) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityNotExistException
{

	TipoMensagens b = null;
	
	String strBusca = "SELECT * FROM tipo_mensagens WHERE TMS_CODIGO = " + l + "";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			b = new TipoMensagens(rs.getInt(1), rs.getString(2)); 
		}else {
			throw new EntityNotExistException("TipoMensagens (codigo='" + l + "')");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return b;
}
	
	public boolean updTipoMensagens(TipoMensagens b) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{
		
		String strAtualizao = 
				"UPDATE tipo_mensagens "
				+ "SET TMS_CODIGO = '" + b.getTmsCodigo() + "' "
				+",TMS_DESCRICAO = '"+b.getTmsDescricao()+"'"
				+ "WHERE TMS_CODIGO = '" + b.getTmsCodigo() + "';";

		getTipoMensagens(b.getTmsCodigo());
		return cnx.atualize(strAtualizao) > 0;
		
	}
	
	public boolean delTipoMensagens(TipoMensagens cdp) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException 
	{
		
		String strDelecao = 
				"DELETE FROM tipo_mensagens "
				+ "WHERE TMS_CODIGO = '" + cdp.getTmsCodigo() + "';";
		
		getTipoMensagens(cdp.getTmsCodigo());		
		return cnx.atualize(strDelecao) > 0;
		
	}		
	
	public List<TipoMensagens> getTiposMensagens () throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException
	{

		TipoMensagens b = null;
		List<TipoMensagens> listaDeTipoMensagenss = new ArrayList<TipoMensagens>();
		
		String strBusca = "SELECT * "
				+ "FROM tipo_mensagens;";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					b = new TipoMensagens(rs.getInt(1), rs.getString(2));
					listaDeTipoMensagenss.add(b);
				}
			}else {
				throw new EntityTableIsEmptyException("Tipos de Mensagens");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaDeTipoMensagenss;
	}	
	
	public List<String> getTipoMensagensDescricoes () throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException
{

	TipoMensagens b = null;
	List<String> listaNomesDeTipoMensagens = new ArrayList<String>();
	
	String strBusca = "SELECT *"
			+ "FROM tipo_mensagenss ;";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				b = new TipoMensagens(rs.getInt(1),rs.getString(2));
				listaNomesDeTipoMensagens.add(b.getTmsDescricao());
			}
		}else {
			throw new EntityTableIsEmptyException("Tipos Mensagens");
		}
	} catch (SQLException sqle) {
		System.out.printf("Erro # %d (%s)\n", 
				sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaNomesDeTipoMensagens;
}	


	
		
}
















