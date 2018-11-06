package br.ufac.academico.db;

import java.sql.*;
import java.util.*;

import br.ufac.academico.entity.Fornecedores;
import br.ufac.academico.entity.Municipios;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;
import br.ufac.academico.exception.EntityAlreadyExistException;
import br.ufac.academico.exception.EntityNotExistException;
import br.ufac.academico.exception.EntityTableIsEmptyException;

public class FornecedoresDB {
	
	private Conexao cnx;
	private ResultSet rs;
	private MunicipiosDB mdb;
	
	public FornecedoresDB(Conexao cnx) {
		this.cnx = cnx;
		mdb = new MunicipiosDB(this.cnx);
	}
	
	public FornecedoresDB(){}

	public void setConexao(Conexao cnx){
		this.cnx = cnx;
	}
	
	public boolean addFornecedor(Fornecedores f) throws 
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityAlreadyExistException
	{

		String strInsercao = 
				"INSERT INTO fornecedores ("
				+ "FOR_CODIGO, "
				+ "FOR_MUN_CODIGO,"
				+ "FOR_RAZAO_SOCIAL,"
				+ "FOR_NOME_FANTASIA,"
				+ "FOR_NOME_CONTATO,"
				+ "FOR_CNPJ,"
				+ "FOR_ENDERECO,"
				+ "FOR_DATA_CADASTRO,"
				+ "FOR_FONE,"
				+ "FOR_EMAIL,"
				+ "FOR_WEBSITE) "
				+ "VALUES (" 
				+ f.getForCodigo() + ", "
				+"'"+f.getMunicipio().getMunCodigo()            
				+"','"+f.getForRazaoSocial()
				+"','"+f.getForNomeFantasia()
				+"','"+f.getForNomeContato()
				+"','"+f.getForCnpj()
				+"','"+f.getForEndereco()
				+"','"+f.getForDataCadastro()
				+"','"+f.getForFone()
				+"','"+f.getForEmail()
				+"','"+f.getForWebSite()+ "');";
		try {
			getFornecedor(f.getForCodigo());
			throw new EntityAlreadyExistException("Fornecedor (codigo='" + f.getForCodigo() + "')"); 
		} catch (EntityNotExistException e) {
			return cnx.atualize(strInsercao) > 0;
		}
		
	}
	
	public Fornecedores getFornecedor (long l) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{

		Fornecedores f = null;
		Municipios m = null;
		
		String strBusca = "SELECT * "
				+ "FROM fornecedores "
				+ "WHERE FOR_CODIGO = '" + l + "';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()){
				
				m = mdb.getMunicipio(rs.getInt(2));
				
				f = new Fornecedores(
				rs.getInt(1), 
				m,
				rs.getString(3),
				rs.getString(4),
				rs.getString(5),
				rs.getString(6),
				rs.getString(7), 
				rs.getString(8),
				rs.getString(9),
				rs.getString(10),
				rs.getString(11));  
			}else {
				throw new EntityNotExistException("Municipio (codigo='" + l + "')");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return f;
	}
	
	public Fornecedores getFornecedorPorNomeFantasia (String nome) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityNotExistException
{

	Fornecedores f = null;
	Municipios m = null;
	
	String strBusca = "SELECT * "
			+ "FROM fornecedores "
			+ "WHERE FOR_NOME_CONTATO = '" + nome + "';";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()){
			
			m = mdb.getMunicipio(rs.getInt(2));
			
			f = new Fornecedores(
			rs.getInt(1), 
			m,
			rs.getString(3),
			rs.getString(4),
			rs.getString(5),
			rs.getString(6),
			rs.getString(7), 
			rs.getString(8),
			rs.getString(9),
			rs.getString(10),
			rs.getString(11));  
		}else {
			throw new EntityNotExistException("Fornecedor (Nome Fantasia='" + nome + "')");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return f;
}
	
	public boolean updFornecedor(Fornecedores f) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{
		
		String strAtualizao = 
				"UPDATE fornecedores "
				+ "SET FOR_CODIGO = " +f.getForCodigo() + ","
				+"FOR_MUN_CODIGO = "+f.getMunicipio().getMunCodigo()+","
				+"FOR_RAZAO_SOCIAL = '"+f.getForRazaoSocial()+"',"
				+"FOR_NOME_FANTASIA = '"+f.getForNomeFantasia()+"', "
				+"FOR_NOME_CONTATO = '"+f.getForNomeContato()+"' "
				+",FOR_CNPJ = '"+f.getForCnpj()+"' "
				+",FOR_ENDERECO = '"+f.getForEndereco()+"' "
				+",FOR_DATA_CADASTRO = '"+f.getForDataCadastro()+"' "
				+",FOR_FONE = '"+f.getForFone()+"' "
				+",FOR_EMAIL = '"+f.getForEmail()+"' "
				+",FOR_WEBSITE = '"+f.getForWebSite()+"' "	
				+ "WHERE FOR_CODIGO =" + f.getForCodigo()+" ;";

		getFornecedor(f.getForCodigo());
		return cnx.atualize(strAtualizao) > 0;
		
	}
	
	public boolean delMunicispios(Fornecedores f) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException 
	{
		
		String strDelecao = 
				"DELETE FROM fornecedores "
				+ "WHERE FOR_CODIGO = '" + f.getForCodigo() + "';";
		
		getFornecedor(f.getForCodigo());		
		return cnx.atualize(strDelecao) > 0;
		
	}		
	
	public List<Fornecedores> getFornecedores () throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException,
		EntityNotExistException
	{

		Fornecedores f = null;
		Municipios m = null;
		List<Fornecedores> listaDeFornecedores = new ArrayList<Fornecedores>();
		
		String strBusca = "SELECT * "
				+ "FROM fornecedores;";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					m = mdb.getMunicipio(rs.getInt(2));
					
					f = new Fornecedores(
					rs.getInt(1), 
					m,
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7), 
					rs.getString(8),
					rs.getString(9),
					rs.getString(10),
					rs.getString(11));   
					listaDeFornecedores.add(f);
				}
			}else {
				throw new EntityTableIsEmptyException("Fornecedores");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaDeFornecedores;
	}	

	
	public List<String> getNomesFornecedores () throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException
{

	Fornecedores f = null;
	Municipios m = null;
	List<String> listaDeFornecedores = new ArrayList<String>();
	
	String strBusca = "SELECT * "
			+ "FROM fornecedores;";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				m = mdb.getMunicipio(rs.getInt(2));
				
				f = new Fornecedores(
				rs.getInt(1), 
				m,
				rs.getString(3),
				rs.getString(4),
				rs.getString(5),
				rs.getString(6),
				rs.getString(7), 
				rs.getString(8),
				rs.getString(9),
				rs.getString(10),
				rs.getString(11));   
				listaDeFornecedores.add(f.getForNomeFantasia());
			}
		}else {
			throw new EntityTableIsEmptyException("Fornecedores");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeFornecedores;
}	
	
	
	public List<Fornecedores> getFornecedoresPorNomeFantasia (String nome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException,
		EntityNotExistException
	{

		Fornecedores f = null;
		Municipios m = null;
		List<Fornecedores> listaDeFornecedores = new ArrayList<Fornecedores>();
		
		String strBusca = "SELECT * "
				+ "FROM fornecedores "
				+ "WHERE FOR_NOME_FANTASIA LIKE '%" + nome + "%';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
				m = mdb.getMunicipio(rs.getInt(2));
					
					f = new Fornecedores(
					rs.getInt(1), 
					m,
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getString(6),
					rs.getString(7), 
					rs.getString(8),
					rs.getString(9),
					rs.getString(10),
					rs.getString(11)); 
					listaDeFornecedores.add(f);
				}
			}else {
				throw new EntityTableIsEmptyException("Fornecedores");
			}
		} catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaDeFornecedores;
	}	

	public List<Fornecedores> getFornecedoresPorCnpj (String cnpj) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException, 
	EntityNotExistException
{

	Fornecedores f = null;
	Municipios m = null;
	List<Fornecedores> listaDeFornecedores = new ArrayList<Fornecedores>();
	
	String strBusca = "SELECT * "
			+ "FROM fornecedores "
			+ "WHERE FOR_CNPJ LIKE '%" + cnpj + "%';";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				m = mdb.getMunicipio(rs.getInt(2));
				
				f = new Fornecedores(
				rs.getInt(1), 
				m,
				rs.getString(3),
				rs.getString(4),
				rs.getString(5),
				rs.getString(6),
				rs.getString(7), 
				rs.getString(8),
				rs.getString(9),
				rs.getString(10),
				rs.getString(11)); 
				listaDeFornecedores.add(f);
			}
		}else {
			throw new EntityTableIsEmptyException("Municipios");
		}
	} catch (SQLException sqle) {
		System.out.printf("Erro # %d (%s)\n", 
				sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeFornecedores;
}	
	
	public List<Fornecedores> getFornecedoresPorMunicipio (String municipio) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException, 
	EntityNotExistException
{

	Fornecedores f = null;
	Municipios m = null;
	List<Fornecedores> listaDeFornecedores = new ArrayList<Fornecedores>();	
	
	String strBusca = "SELECT f.*\n" + 
			"FROM municipios as m, fornecedores as f\n" + 
			"WHERE f.FOR_MUN_CODIGO = m.MUN_CODIGO\n" + 
			"AND m.MUN_NOME LIKE '%"+municipio+"%';";	
	
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				m = mdb.getMunicipio(rs.getInt(2));
				
				f = new Fornecedores(
				rs.getInt(1), 
				m,
				rs.getString(3),
				rs.getString(4),
				rs.getString(5),
				rs.getString(6),
				rs.getString(7), 
				rs.getString(8),
				rs.getString(9),
				rs.getString(10),
				rs.getString(11)); 
				listaDeFornecedores.add(f);
			}
		}else {
			throw new EntityTableIsEmptyException("Municipios");
		}
	} catch (SQLException sqle) {
		System.out.printf("Erro # %d (%s)\n", 
				sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeFornecedores;
}	

	
}
















