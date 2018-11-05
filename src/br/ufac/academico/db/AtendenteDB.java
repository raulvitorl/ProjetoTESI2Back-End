package br.ufac.academico.db;

import java.sql.*;
import java.util.*;

import br.ufac.academico.entity.Atendente;
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
	
	public AtendenteDB() {
		// TODO Auto-generated constructor stub
	}
	
	public void setConexao(Conexao cnx){
		this.cnx = cnx;
		
	}

	public boolean addAtendente(Atendente a) throws 
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
	
	public Atendente getAtendente (int codigo) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{

		Atendente a = null;
		
		String strBusca = "SELECT * "
				+ "FROM atendentes "
				+ "WHERE ATE_CODIGO = '" + codigo + "';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				a = new Atendente(
						rs.getInt(1),
						rs.getString(2), 
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6).charAt(0)
						);  
			}else {
				throw new EntityNotExistException("Atendente (codigo='" + codigo + "')");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return a;
	}
	
	public Atendente getAtendentePorNome (String nome) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityNotExistException
{

	Atendente a = null;
	
	String strBusca = "SELECT * "
			+ "FROM atendentes "
			+ "WHERE ATE_NOME = '" + nome + "';";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			a = new Atendente(
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
	
	
	public boolean updAtendente(Atendente a) throws
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
	
	public boolean delAtendente(Atendente a) throws
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
	
	public List<Atendente> getAtendentes () throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException
	{

		Atendente a = null;
		List<Atendente> listaAtendentes = new ArrayList<Atendente>();
		
		String strBusca = "SELECT * "
				+ "FROM atendentes;";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					a = new Atendente(
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

	Atendente a = null;
	List<String> listaDeNomes = new ArrayList<String>();
	
	String strBusca = "SELECT * "
			+ "FROM atendentes;";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				a = new Atendente(
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
	
	public List<Atendente> getAtendentesPorNome (String nome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException
	{

		Atendente a = null;
		List<Atendente> listaDeAtendentes = new ArrayList<Atendente>();
		
		String strBusca = "SELECT * "
				+ "FROM atendentes "
				+ "WHERE ATE_NOME LIKE '%" + nome + "%';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					a = new Atendente(
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