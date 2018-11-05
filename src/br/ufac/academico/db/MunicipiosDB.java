package br.ufac.academico.db;

import java.sql.*;
import java.util.*;

import br.ufac.academico.entity.Cliente;
import br.ufac.academico.entity.Municipios;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;
import br.ufac.academico.exception.EntityAlreadyExistException;
import br.ufac.academico.exception.EntityNotExistException;
import br.ufac.academico.exception.EntityTableIsEmptyException;

public class MunicipiosDB {
	
	private Conexao cnx;
	private ResultSet rs;
	
	public MunicipiosDB(Conexao cnx) {
		this.cnx = cnx;
	}
	
	public MunicipiosDB() {
	}
	
	public void setConexao(Conexao cnx){
		this.cnx = cnx;
	}

	

	public boolean addMunicipio(Municipios m) throws 
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityAlreadyExistException
	{

		String strInsercao = 
				"INSERT INTO municipios (MUN_CODIGO, MUN_NOME,MUN_UF_ESTADO,MUN_CEP) "
				+ "VALUES (" + m.getMunCodigo() + ", "
				+"'"+m.getMunNome()
				+"','"+m.getMunUfEstado()
				+ "','" + m.getMunCep() + "');";

		try {
			getMunicipio(m.getMunCodigo());
			throw new EntityAlreadyExistException("Municipio (codigo='" + m.getMunCodigo() + "')"); 
		} catch (EntityNotExistException e) {
			return cnx.atualize(strInsercao) > 0;
		}
		
	}
	
	public Municipios getMunicipio (int codigo) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityNotExistException
{
	Municipios m = null;
	
	String strBusca = "SELECT * "
			+ "FROM municipios "
			+ "WHERE MUN_CODIGO = '" + codigo + "';";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()){
			
			m = new Municipios(
			rs.getInt(1),		
			rs.getString(2),		
			rs.getString(3),
			rs.getString(4)
			);
		}else {
			throw new EntityNotExistException("Municipio (codigo='" + codigo + "')");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return m;
}
	
	public Municipios getMunicipioPorNome (String nome) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityNotExistException
{

	Municipios m = null;
	
	String strBusca = "SELECT * "
			+ "FROM municipios "
			+ "WHERE MUN_NOME = '" + nome + "';";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			m = new Municipios(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4));  
			System.out.println(m.getMunCodigo());
			System.out.println(m.getMunUfEstado());
			System.out.println(m.getMunCodigo());
			System.out.println(m.getMunCep());
		}else {
			throw new EntityNotExistException("Munipio (nome='" + nome + "')");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return m;
}
	
	
	public boolean updMunicipio(Municipios m) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{
		
		String strAtualizao = 
				"UPDATE municipios "
				+ "SET MUN_CODIGO = " + m.getMunCodigo() + ","
				+"MUN_NOME = '"+m.getMunNome()+"',"
				+"MUN_UF_ESTADO = '"+m.getMunUfEstado()+"',"
				+"MUN_CEP = '"+m.getMunCep()+"' "
				+ "WHERE MUN_CODIGO =" + m.getMunCodigo()+" ;";

		getMunicipio(m.getMunCodigo());
		return cnx.atualize(strAtualizao) > 0;
		
	}
	
	public boolean delMunicispios(Municipios m) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException 
	{
		
		String strDelecao = 
				"DELETE FROM municipios "
				+ "WHERE MUN_CODIGO = '" + m.getMunCodigo() + "';";
		
		getMunicipio(m.getMunCodigo());		
		return cnx.atualize(strDelecao) > 0;
		
	}		
	
	public List<Municipios> getMunicipios () throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException
	{

		Municipios m = null;
		List<Municipios> listaMunicipios = new ArrayList<Municipios>();
		
		String strBusca = "SELECT * "
				+ "FROM municipios;";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					m = new Municipios(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4));
					listaMunicipios.add(m);
				}
			}else {
				throw new EntityTableIsEmptyException("Municipios");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaMunicipios;
	}	

	public List<String> getNomesMunicipios () throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException
{

	Municipios m = null;
	List<String> listaDeNomes = new ArrayList<String>();
	
	String strBusca = "SELECT * "
			+ "FROM municipios;";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				m = new Municipios(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4));
				listaDeNomes.add(m.getMunNome());
			}
		}else {
			throw new EntityTableIsEmptyException("Municipios");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeNomes;
}	
	
	public List<Municipios> getMunicipiosPorNome (String nome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException
	{

		Municipios m = null;
		List<Municipios> listaDeMunicipios = new ArrayList<Municipios>();
		
		String strBusca = "SELECT * "
				+ "FROM municipios "
				+ "WHERE MUN_NOME LIKE '%" + nome + "%';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					m = new Municipios(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4));
					listaDeMunicipios.add(m);
				}
			}else {
				throw new EntityTableIsEmptyException("Municipios");
			}
		} catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaDeMunicipios;
	}	

	public List<Municipios> getMunicipiosPorUF (String UF) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException
{

	Municipios m = null;
	List<Municipios> listaDeMunicipios = new ArrayList<Municipios>();
	
	String strBusca = "SELECT * "
			+ "FROM municipios "
			+ "WHERE MUN_UF_ESTADO LIKE '%" + UF + "%';";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				m = new Municipios(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4));
				listaDeMunicipios.add(m);
			}
		}else {
			throw new EntityTableIsEmptyException("Municipios");
		}
	} catch (SQLException sqle) {
		System.out.printf("Erro # %d (%s)\n", 
				sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeMunicipios;
}	

}
















