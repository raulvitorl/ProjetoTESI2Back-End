package br.ufac.academico.db;

import java.sql.*;
import java.util.*;

import br.ufac.academico.entity.Atendentes;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;
import br.ufac.academico.exception.EntityAlreadyExistException;
import br.ufac.academico.exception.EntityNotExistException;
import br.ufac.academico.exception.EntityTableIsEmptyException;

public class AtendenteDB {
	
	private Conexao cnx;
	private ResultSet rs;
	
	public AtendenteDB(Conexao cnx) {
		this.cnx = cnx;
	}
	
	public AtendenteDB(){	}
	
	public void setConexao(Conexao cnx){
		this.cnx = cnx;
		
	}

	public boolean addAtendente(Atendentes a) throws 
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityAlreadyExistException
	{

		String strInsercao = 
				"INSERT INTO atendentes (ATE_CODIGO,ATE_NOME,ATE_ULTIMO_ACESSO,ATE_RAMAL,ATE_EMAIL,ATE_STATUS) "
				+ "VALUES (" + a.getCodigo() + ", "
				+"'"+a.getNome()
				+"','"+a.getUltimoAcesso()
				+"','"+a.getRamal()
				+"','"+a.getEmail()
				+ "','" + a.getStatus() + "');";

		try {
			getAtendente(a.getCodigo());
			throw new EntityAlreadyExistException("Atendente (codigo='" + a.getCodigo() + "')"); 
		} catch (EntityNotExistException e) {
			return cnx.atualize(strInsercao) > 0;
		}
		
	}
	
	public Atendentes getAtendente (long l) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{

		Atendentes a = null;
		
		String strBusca = "SELECT * "
				+ "FROM atendentes "
				+ "WHERE ATE_CODIGO = '" + l + "';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				a = new Atendentes(
						rs.getInt(1),
						rs.getString(2), 
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6).charAt(0)
						);  
			}else {
				throw new EntityNotExistException("Atendente (codigo='" + l + "')");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return a;
	}
	
	public Atendentes getAtendentePorNome (String nome) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityNotExistException
{

	Atendentes a = null;
	
	String strBusca = "SELECT * "
			+ "FROM atendentes "
			+ "WHERE ATE_NOME = '" + nome + "';";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			a = new Atendentes(
					rs.getInt(1),
					rs.getString(2), 
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6).charAt(0)
					);    
		}else {
			throw new EntityNotExistException("Atedente (nome='" + nome + "')");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return a;
}
	
	
	public boolean updAtendente(Atendentes a) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{
		
		String strAtualizao = 
				"UPDATE atendentes "
				+ "SET ATE_CODIGO = " + a.getCodigo() + ","
				+"ATE_NOME = '"+a.getNome()+"',"
				+"ATE_ULTIMO_ACESSO = '"+a.getUltimoAcesso()+"',"
				+"ATE_RAMAL = '"+a.getRamal()+"', "
				+"ATE_EMAIL = '"+a.getEmail()+"',"
				+"ATE_STATUS = '"+a.getStatus()+"' "
				+ "WHERE ATE_CODIGO =" + a.getCodigo()+" ;";

		getAtendente(a.getCodigo());
		return cnx.atualize(strAtualizao) > 0;
		
	}
	
	public boolean delAtendente(Atendentes a) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException 
	{
		
		String strDelecao = 
				"DELETE FROM atendentes "
				+ "WHERE ATE_CODIGO = '" + a.getCodigo() + "';";
		
		getAtendente(a.getCodigo());		
		return cnx.atualize(strDelecao) > 0;
		
	}		
	
	public List<Atendentes> getAtendentes () throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException
	{

		Atendentes a = null;
		List<Atendentes> listaAtendentes = new ArrayList<Atendentes>();
		
		String strBusca = "SELECT * "
				+ "FROM atendentes;";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					a = new Atendentes(
							rs.getInt(1),
							rs.getString(2), 
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6).charAt(0)
							);  
					listaAtendentes.add(a);
				}
			}else {
				throw new EntityTableIsEmptyException("Atendentes");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaAtendentes;
	}	

	public List<String> getNomesAtendentes () throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException
{

	Atendentes a = null;
	List<String> listaDeNomes = new ArrayList<String>();
	
	String strBusca = "SELECT * "
			+ "FROM atendentes;";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				a = new Atendentes(
						rs.getInt(1),
						rs.getString(2), 
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6).charAt(0)
						);  
				listaDeNomes.add(a.getNome());
			}
		}else {
			throw new EntityTableIsEmptyException("Atendentes");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeNomes;
}	
	
	public List<Atendentes> getAtendentesPorNome (String nome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException
	{

		Atendentes a = null;
		List<Atendentes> listaDeAtendentes = new ArrayList<Atendentes>();
		
		String strBusca = "SELECT * "
				+ "FROM atendentes "
				+ "WHERE ATE_NOME LIKE '%" + nome + "%';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					a = new Atendentes(
							rs.getInt(1),
							rs.getString(2), 
							rs.getString(3),
							rs.getString(4),
							rs.getString(5),
							rs.getString(6).charAt(0)
							);  
					listaDeAtendentes.add(a);
				}
			}else {
				throw new EntityTableIsEmptyException("Atendentes");
			}
		} catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaDeAtendentes;
	}	


}