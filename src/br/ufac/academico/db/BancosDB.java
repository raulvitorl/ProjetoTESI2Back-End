package br.ufac.academico.db;

import java.sql.*;
import java.util.*;

import br.ufac.academico.entity.Banco;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;
import br.ufac.academico.exception.EntityAlreadyExistException;
import br.ufac.academico.exception.EntityNotExistException;
import br.ufac.academico.exception.EntityTableIsEmptyException;

public class BancosDB {
	
	private Conexao cnx;
	private ResultSet rs;
	
	public BancosDB(Conexao cnx) {
		this.cnx = cnx;
	}
	
	public BancosDB(){
		
	}
	
	public void setConexao(Conexao cnx){
		this.cnx = cnx;
	}

	public boolean addBanco(Banco b) throws 
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityAlreadyExistException
	{

		String strInsercao ="INSERT INTO bancos (BAN_CODIGO, BAN_NOME) VALUES ("+b.getCodigo()+",'"+b.getNome()+"');";

		try {
			getBanco(b.getCodigo());
			throw new EntityAlreadyExistException("Banco (codigo='" + b.getCodigo() + "')"); 
		} catch (EntityNotExistException e) {
			return cnx.atualize(strInsercao) > 0;
		}
		
	}
	
	public Banco getBancoPorNome (String nome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{

		Banco b = null;
		
		String strBusca = "SELECT * FROM bancos WHERE BAN_NOME = " + nome + "";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				b = new Banco(rs.getInt(1), rs.getString(2)); 
			}else {
				throw new EntityNotExistException("Banco (nome='" + nome + "')");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return b;
	}

	
	public Banco getBanco (long l) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityNotExistException
{

	Banco b = null;
	
	String strBusca = "SELECT * FROM bancos WHERE BAN_CODIGO = " + l + "";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			b = new Banco(rs.getInt(1), rs.getString(2)); 
		}else {
			throw new EntityNotExistException("Banco (codigo='" + l + "')");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return b;
}
	
	
	public boolean updBanco(Banco b) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{
		
		String strAtualizao = 
				"UPDATE bancos "
				+ "SET BAN_CODIGO = '" + b.getCodigo() + "' "
				+",BAN_NOME = '"+b.getNome()+"'"
				+ "WHERE BAN_CODIGO = '" + b.getCodigo() + "';";

		getBanco(b.getCodigo());
		return cnx.atualize(strAtualizao) > 0;
		
	}
	
	public boolean delBanco(Banco cdp) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException 
	{
		
		String strDelecao = 
				"DELETE FROM bancos "
				+ "WHERE BAN_CODIGO = '" + cdp.getCodigo() + "';";
		
		getBanco(cdp.getCodigo());		
		return cnx.atualize(strDelecao) > 0;
		
	}		
	
	public List<Banco> getBancos () throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException
	{

		Banco b = null;
		List<Banco> listaDeBancos = new ArrayList<Banco>();
		
		String strBusca = "SELECT * "
				+ "FROM bancos;";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					b = new Banco(rs.getInt(1), rs.getString(2));
					listaDeBancos.add(b);
				}
			}else {
				throw new EntityTableIsEmptyException("Bancos");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaDeBancos;
	}	
	
	public List<Banco> getBancosPorNome (String banNome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException
	{

		Banco b = null;
		List<Banco> listaDeBancos = new ArrayList<Banco>();
		
		String strBusca = "SELECT *"
				+ "FROM bancos "
				+ "WHERE BAN_NOME LIKE '%" + banNome + "%';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					b = new Banco(rs.getInt(1),rs.getString(2));
					listaDeBancos.add(b);
				}
			}else {
				throw new EntityTableIsEmptyException("Bancos");
			}
		} catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaDeBancos;
	}	

	public List<String> getBancosNomes () throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException
{

	Banco b = null;
	List<String> listaNomesDeBancos = new ArrayList<String>();
	
	String strBusca = "SELECT *"
			+ "FROM bancos ;";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				b = new Banco(rs.getInt(1),rs.getString(2));
				listaNomesDeBancos.add(b.getNome());
			}
		}else {
			throw new EntityTableIsEmptyException("Bancos");
		}
	} catch (SQLException sqle) {
		System.out.printf("Erro # %d (%s)\n", 
				sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaNomesDeBancos;
}	


	
		
}