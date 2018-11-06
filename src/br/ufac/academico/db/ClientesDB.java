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

public class ClientesDB {
	
	private Conexao cnx;
	private ResultSet rs;
	private MunicipiosDB mdb;
	
	public ClientesDB(Conexao cnx) {
		this.cnx = cnx;
		mdb = new MunicipiosDB(cnx);
	}
	
		

	public ClientesDB(){
	}
	
	
	public void setConexao(Conexao cnx){
		this.cnx = cnx;
	}



	public boolean addCliente(Cliente a) throws 
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityAlreadyExistException
	{
		System.out.println(a.getCodigo());
		String strInsercao = 
				"INSERT INTO clientes " 
				+ "VALUES (" 
				+ a.getCodigo() + ", "
				+"'"+a.getMunicipio().getMunCodigo()            
				+"','"+a.getNome()
				+"','"+a.getDataNascimento()
				+"','"+a.getSexo()
				+"','"+a.getCpf()
				+"','"+a.getRg()
				+"','"+a.getCnpj()
				+"','"+a.getEndereco()
				+"','"+a.getEmail()
				+"','"+a.getDataCadastro()
				+"','"+a.getTipo()
				+"','"+a.getStatus()
				+"','"+a.getFone()
				+"','"+a.getNomeContato()+ "');";
		try {
			getCliente(a.getCodigo());
			throw new EntityAlreadyExistException("Cliente FOI AQUI (codigo='" + a.getCodigo() + "')"); 
		} catch (EntityNotExistException e) {
			return cnx.atualize(strInsercao) > 0;
		}
		
	}
	
	public Cliente getCliente (long l) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{

		Cliente c = null;
		Municipios m = null;
		
		String strBusca = "SELECT * "
				+ "FROM clientes "
				+ "WHERE CLI_CODIGO = '" + l + "';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()){
				
				m = mdb.getMunicipio(rs.getInt(2));
				
				c = new Cliente(
				rs.getInt(1), 
				m,
				rs.getString(3),
				rs.getString(4),
				rs.getString(5).charAt(0),
				rs.getString(6),
				rs.getString(7), 
				rs.getString(8),
				rs.getString(9),
				rs.getString(10),
				rs.getString(11),
				rs.getString(12).charAt(0),
				rs.getString(13).charAt(0),
				rs.getString(14),
				rs.getString(15)
						);  
			}else {
				throw new EntityNotExistException("Cliente (codigo='" + l + "')");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return c;
	}
	
	public Cliente getClientePorNome (String nome) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityNotExistException
{

	Cliente c = null;
	Municipios m = null;
	
	String strBusca = "SELECT * "
			+ "FROM clientes "
			+ "WHERE CLI_NOME = '" + nome + "';";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()){
			
			m = mdb.getMunicipio(rs.getInt(2));
			
			c = new Cliente(
					rs.getInt(1), 
					m,
					rs.getString(3),
					rs.getString(4),
					rs.getString(5).charAt(0),
					rs.getString(6),
					rs.getString(7), 
					rs.getString(8),
					rs.getString(9),
					rs.getString(10),
					rs.getString(11),
					rs.getString(12).charAt(0),
					rs.getString(13).charAt(0),
					rs.getString(14),
					rs.getString(15)
							);  
		}else {
			throw new EntityNotExistException("Cliente (Nome='" + nome + "')");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return c;
}
	
	public boolean updCliente(Cliente c) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{
		
		String strAtualizao = 
				"UPDATE clientes "
				+ "SET CLI_CODIGO = " +c.getCodigo() + ","
				+"CLI_MUN_CODIGO = "+c.getMunicipio().getMunCodigo()+","
				+"CLI_NOME = '"+c.getNome()+"',"
				+"CLI_DATA_NASCIMENTO = '"+c.getDataNascimento()+"', "
				+"CLI_SEXO = '"+c.getSexo()+"' "
				+",CLI_CPF = '"+c.getCpf()+"' "
				+",CLI_RG = '"+c.getRg()+"' "
				+",CLI_CNPJ = '"+c.getCnpj()+"' "
				+",CLI_ENDERECO = '"+c.getEndereco()+"' "
				+",CLI_EMAIL = '"+c.getEmail()+"' "
				+",CLI_DATA_CADASTRO = '"+c.getDataCadastro()+"' "
				+",CLI_TIPO = '"+c.getTipo()+"' "
				+",CLI_STATUS = '"+c.getStatus()+"' "
				+",CLI_FONE = '"+c.getFone()+"' "
				+",CLI_NOME_CONTATO = '"+c.getNomeContato()+"' "				
				+ "WHERE CLI_CODIGO =" + c.getCodigo()+" ;";

		getCliente(c.getCodigo());
		return cnx.atualize(strAtualizao) > 0;
		
	}
	
	public boolean delClientes(Cliente c) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException 
	{
		
		String strDelecao = 
				"DELETE FROM clientes "
				+ "WHERE CLI_CODIGO = '" + c.getCodigo() + "';";
		
		getCliente(c.getCodigo());		
		return cnx.atualize(strDelecao) > 0;
		
	}		
	
	public List<Cliente> getClientes () throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException,
		EntityNotExistException
	{

		Cliente c = null;
		Municipios m = null;
		List<Cliente> listaDeCliente = new ArrayList<Cliente>();
		
		String strBusca = "SELECT * "
				+ "FROM clientes;";
		
		rs = cnx.consulte(strBusca);
	
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					m = mdb.getMunicipio(rs.getInt(2));
					
					c = new Cliente(
					rs.getInt(1), 
					m,
					rs.getString(3),
					rs.getString(4),
					rs.getString(5).charAt(0),
					rs.getString(6),
					rs.getString(7), 
					rs.getString(8),
					rs.getString(9),
					rs.getString(10),
					rs.getString(11),
					rs.getString(12).charAt(0),
					rs.getString(13).charAt(0),
					rs.getString(14),
					rs.getString(15));	
					listaDeCliente.add(c);
					}
			}else {
				throw new EntityTableIsEmptyException("Clientes");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaDeCliente;
	}	
	
	public List<String> getNomesClientes () throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException
{

	Cliente c = null;
	Municipios m = null;
	List<String> listaDeClientes = new ArrayList<String>();
	
	String strBusca = "SELECT * "
			+ "FROM clientes;";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				m = mdb.getMunicipio(rs.getInt(2));
				
							
				c = new Cliente(
				rs.getInt(1), 
				m,
				rs.getString(3),
				rs.getString(4),
				rs.getString(5).charAt(0),
				rs.getString(6),
				rs.getString(7), 
				rs.getString(8),
				rs.getString(9),
				rs.getString(10),
				rs.getString(11),
				rs.getString(12).charAt(0),
				rs.getString(13).charAt(0),
				rs.getString(14),
				rs.getString(15)	
				);
				listaDeClientes.add(c.getNome());
			}
		}else {
			throw new EntityTableIsEmptyException("Clientes");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeClientes;
}	
	
	public List<Cliente> getClientesPorNome (String nome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException,
		EntityNotExistException
	{

		Cliente c = null;
		Municipios m = null;
		List<Cliente> listaDeCliente = new ArrayList<Cliente>();
		
		String strBusca = "SELECT * "
				+ "FROM clientes "
				+ "WHERE CLI_NOME LIKE '%" + nome + "%';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					m = mdb.getMunicipio(rs.getInt(2));
					
					c = new Cliente(
					rs.getInt(1), 
					m,
					rs.getString(3),
					rs.getString(4),
					rs.getString(5).charAt(0),
					rs.getString(6),
					rs.getString(7), 
					rs.getString(8),
					rs.getString(9),
					rs.getString(10),
					rs.getString(11),
					rs.getString(12).charAt(0),
					rs.getString(13).charAt(0),
					rs.getString(14),
					rs.getString(15)
					);	
					listaDeCliente.add(c);
				}
			}else {
				throw new EntityTableIsEmptyException("Clientes");
			}
		} catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaDeCliente;
	}	

	public List<Cliente> getClientesPorCpf (String cpf) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException, 
	EntityNotExistException
{

	Cliente c = null;
	Municipios m = null;
	List<Cliente> listaDeCliente = new ArrayList<Cliente>();
	
	String strBusca = "SELECT * "
			+ "FROM  clientes "
			+ "WHERE CLI_CPF LIKE '%" + cpf + "%';";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				m = mdb.getMunicipio(rs.getInt(2));
				
				c = new Cliente(
				rs.getInt(1), 
				m,
				rs.getString(3),
				rs.getString(4),
				rs.getString(5).charAt(0),
				rs.getString(6),
				rs.getString(7), 
				rs.getString(8),
				rs.getString(9),
				rs.getString(10),
				rs.getString(11),
				rs.getString(12).charAt(0),
				rs.getString(13).charAt(0),
				rs.getString(14),
				rs.getString(15)
				);	
				listaDeCliente.add(c);
			}
		}else {
			throw new EntityTableIsEmptyException("Clientes");
		}
	} catch (SQLException sqle) {
		System.out.printf("Erro # %d (%s)\n", 
				sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeCliente;
}	
	
	public List<Cliente> getClientesPorMunicipio (String municipio) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException, 
	EntityNotExistException
{

	Cliente c = null;
	Municipios m = null;
	List<Cliente> listaDeCliente = new ArrayList<Cliente>();
	
	String strBusca = "SELECT *\n" + 
			"FROM clientes as c, municipios as m\n" + 
			"WHERE c.CLI_MUN_CODIGO=m.MUN_CODIGO\n" + 
			"AND m.MUN_NOME = '"+municipio+"'";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				m = mdb.getMunicipio(rs.getInt(2));
				
				c = new Cliente(
				rs.getInt(1), 
				m,
				rs.getString(3),
				rs.getString(4),
				rs.getString(5).charAt(0),
				rs.getString(6),
				rs.getString(7), 
				rs.getString(8),
				rs.getString(9),
				rs.getString(10),
				rs.getString(11),
				rs.getString(12).charAt(0),
				rs.getString(13).charAt(0),
				rs.getString(14),
				rs.getString(15)
				);	
				listaDeCliente.add(c);
			}
		}else {
			throw new EntityTableIsEmptyException("Municipios");
		}
	} catch (SQLException sqle) {
		System.out.printf("Erro # %d (%s)\n", 
				sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeCliente;
}	
		
	public List<Cliente> getClientesPorEmail (String email) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException, 
	EntityNotExistException
{

	Cliente c = null;
	Municipios m = null;
	List<Cliente> listaDeCliente = new ArrayList<Cliente>();
	
	String strBusca = "SELECT * "
			+ "FROM  clientes "
			+ "WHERE CLI_EMAIL LIKE '%" + email + "%';";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				m = mdb.getMunicipio(rs.getInt(2));
				
				c = new Cliente(
				rs.getInt(1), 
				m,
				rs.getString(3),
				rs.getString(4),
				rs.getString(5).charAt(0),
				rs.getString(6),
				rs.getString(7), 
				rs.getString(8),
				rs.getString(9),
				rs.getString(10),
				rs.getString(11),
				rs.getString(12).charAt(0),
				rs.getString(13).charAt(0),
				rs.getString(14),
				rs.getString(15)
				);	
				listaDeCliente.add(c);
			}
		}else {
			throw new EntityTableIsEmptyException("Municipios");
		}
	} catch (SQLException sqle) {
		System.out.printf("Erro # %d (%s)\n", 
				sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeCliente;
}	
	
	public List<Cliente> getClientesPorSexo (String sexo) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException, 
	EntityNotExistException
{

	Cliente c = null;
	Municipios m = null;
	List<Cliente> listaDeCliente = new ArrayList<Cliente>();
	
	String strBusca = "SELECT * "
			+ "FROM  clientes "
			+ "WHERE CLI_SEXO LIKE '%" + sexo + "%';";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				m = mdb.getMunicipio(rs.getInt(2));
				
				c = new Cliente(
				rs.getInt(1), 
				m,
				rs.getString(3),
				rs.getString(4),
				rs.getString(5).charAt(0),
				rs.getString(6),
				rs.getString(7), 
				rs.getString(8),
				rs.getString(9),
				rs.getString(10),
				rs.getString(11),
				rs.getString(12).charAt(0),
				rs.getString(13).charAt(0),
				rs.getString(14),
				rs.getString(15)
				);	
				listaDeCliente.add(c);
			}
		}else {
			throw new EntityTableIsEmptyException("Municipios");
		}
	} catch (SQLException sqle) {
		System.out.printf("Erro # %d (%s)\n", 
				sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeCliente;
}	
	public List<Cliente> getClientesPorStatus (String status) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException, 
	EntityNotExistException
{

	Cliente c = null;
	Municipios m = null;
	List<Cliente> listaDeCliente = new ArrayList<Cliente>();
	
	String strBusca = "SELECT * "
			+ "FROM  clientes "
			+ "WHERE CLI_STATUS LIKE '%" + status + "%';";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				m = mdb.getMunicipio(rs.getInt(2));
				
				c = new Cliente(
				rs.getInt(1), 
				m,
				rs.getString(3),
				rs.getString(4),
				rs.getString(5).charAt(0),
				rs.getString(6),
				rs.getString(7), 
				rs.getString(8),
				rs.getString(9),
				rs.getString(10),
				rs.getString(11),
				rs.getString(12).charAt(0),
				rs.getString(13).charAt(0),
				rs.getString(14),
				rs.getString(15)
				);	
				listaDeCliente.add(c);
			}
		}else {
			throw new EntityTableIsEmptyException("Municipios");
		}
	} catch (SQLException sqle) {
		System.out.printf("Erro # %d (%s)\n", 
				sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeCliente;
}	
	
	public List<Cliente> getClientesPorRG (String rg) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException, 
	EntityNotExistException
{

	Cliente c = null;
	Municipios m = null;
	List<Cliente> listaDeCliente = new ArrayList<Cliente>();
	
	String strBusca = "SELECT * "
			+ "FROM  clientes "
			+ "WHERE CLI_RG LIKE '%" + rg + "%';";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				m = mdb.getMunicipio(rs.getInt(2));
				
				c = new Cliente(
				rs.getInt(1), 
				m,
				rs.getString(3),
				rs.getString(4),
				rs.getString(5).charAt(0),
				rs.getString(6),
				rs.getString(7), 
				rs.getString(8),
				rs.getString(9),
				rs.getString(10),
				rs.getString(11),
				rs.getString(12).charAt(0),
				rs.getString(13).charAt(0),
				rs.getString(14),
				rs.getString(15)
				);	
				listaDeCliente.add(c);
			}
		}else {
			throw new EntityTableIsEmptyException("Municipios");
		}
	} catch (SQLException sqle) {
		System.out.printf("Erro # %d (%s)\n", 
				sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeCliente;
}	
	


	
}
















