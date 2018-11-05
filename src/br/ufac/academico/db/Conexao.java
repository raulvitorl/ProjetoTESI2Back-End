package br.ufac.academico.db;
import java.sql.*;

import br.ufac.academico.exception.AccessDeniedForUserException;
import br.ufac.academico.exception.DataBaseAlreadyConnectedException;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;

public class Conexao {

	private Connection con = null;
	private Statement smt = null;
	private boolean conectado = false;
	
	public Conexao() throws DataBaseGenericException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new DataBaseGenericException(0, e.getMessage());
		}
		
	}
	
	public boolean isConectado() {
		return conectado;
	}
		
	public boolean conecte (String urlBanco, String userName, String userPasswd) 
		throws DataBaseAlreadyConnectedException, 
		AccessDeniedForUserException,
		DataBaseGenericException
	{

		if (conectado) {
			throw new DataBaseAlreadyConnectedException("academico");
		}else{
			try {
				con = DriverManager.getConnection(urlBanco, userName, userPasswd);
				conectado = true;
			} catch (SQLException sqle) {
				conectado = false;				
				if (sqle.getErrorCode() == 1045) {
					throw new AccessDeniedForUserException(userName);
				}else {
					throw new DataBaseGenericException(sqle.getErrorCode(), 
							sqle.getMessage());
				}
			}
		}
		return conectado;
	}
	
	public void desconecte () throws 
		DataBaseNotConnectedException,
		DataBaseGenericException	
	{

		if(!conectado) {
			throw new DataBaseNotConnectedException("academico");
		}else {
			try {
				con.close();
				conectado = false;
			} catch (SQLException sqle) {
				throw new DataBaseGenericException(sqle.getErrorCode(), 
						sqle.getMessage());
			}
		}
	}

	public ResultSet consulte (String sqlQuery) throws 
		DataBaseGenericException, 
		DataBaseNotConnectedException
	{

		if(!conectado) {
			throw new DataBaseNotConnectedException("academico");
		}else {
			try {
				smt = con.createStatement();
				return smt.executeQuery(sqlQuery);
			} catch (SQLException sqle) {
				throw new DataBaseGenericException(sqle.getErrorCode(), 
						sqle.getMessage());
			}
		}
	}
	
	public int atualize (String sqlUpdate) throws 
		DataBaseGenericException, 
		DataBaseNotConnectedException
	{

		if(!conectado) {
			throw new DataBaseNotConnectedException("academico");
		}else {
			try {
				smt = con.createStatement();				
				return smt.executeUpdate(sqlUpdate);
			} catch (SQLException sqle) {
				throw new DataBaseGenericException(sqle.getErrorCode(), 
						sqle.getMessage());
			}
		}
	}	

}
