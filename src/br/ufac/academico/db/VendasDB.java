package br.ufac.academico.db;

import java.sql.*;
import java.util.*;
import br.ufac.academico.entity.Atendente;
import br.ufac.academico.entity.Banco;
import br.ufac.academico.entity.Cliente;
import br.ufac.academico.entity.Vendas;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;
import br.ufac.academico.exception.EntityAlreadyExistException;
import br.ufac.academico.exception.EntityNotExistException;
import br.ufac.academico.exception.EntityTableIsEmptyException;

public class VendasDB {
	
	private Conexao cnx;
	private ResultSet rs;
	
	//IMPORTANTE REFENCIAR A CAMADA DE CONEXÃO DAS 
	//TABELAS QUE VOU REFERENCIAR
	
	private ClientesDB cdb;
	private AtendenteDB adb;
	private BancosDB bdb;
	
	public VendasDB(Conexao cnx) {
		this.cnx = cnx;
		cdb = new ClientesDB(this.cnx);
		adb = new AtendenteDB(this.cnx);
		bdb = new BancosDB(this.cnx);
	}

	public boolean addVenda(Vendas v) throws 
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityAlreadyExistException
	{

		String strInsercao = 
				"INSERT INTO vendas (VEN_CODIGO, VEN_CLI_CODIGO, VEN_ATE_CODIGO, VEN_BAN_CODIGO, VEN_VALOR_TOTAL, VEN_FORMA_PAGAMENTO,VEN_OBSERVACOES) "
				+ "VALUES ("+v.getVenCodigo()+","+v.getCliente().getCodigo()+", "+v.getAtendente().getCodigo()+", "+v.getBanco().getCodigo()+", "+v.getVenValorTotal()+", '"+v.getVenFormaPagamento()+"', '"+v.getVenObservacoes()+"');";
		try {
			getVenda(v.getVenCodigo());
			throw new EntityAlreadyExistException("Venda (codigo='" + v.getVenCodigo() + "')"); 
		} catch (EntityNotExistException e) {
			return cnx.atualize(strInsercao) > 0;
		}
		
	}
	
	public Vendas getVenda (long l) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{

		Vendas v = null;
		Cliente c = null;
		Atendente a = null;
		Banco b = null;
		
		String strBusca = "SELECT * "
				+ "FROM vendas "
				+ "WHERE VEN_CODIGO = '" + l + "';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()){
				
				c = cdb.getCliente(rs.getInt(2));
				a = adb.getAtendente(rs.getInt(3));
				b = bdb.getBanco(rs.getInt(4));
				
				v = new Vendas(
				rs.getInt(1), 
				c,
				a,
				b,
				rs.getFloat(5),
				rs.getString(6),
				rs.getString(7));  
			}else {
				throw new EntityNotExistException("Produto (codigo='" + l + "')");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return v;
	}
	
	public boolean updVenda(Vendas v) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{
		
		String strAtualizao = 
				"UPDATE vendas "
				+ "SET VEN_CODIGO = " +v.getVenCodigo() + ","
				+"VEN_CLI_CODIGO = "+v.getCliente().getCodigo()+","
				+"VEN_ATE_CODIGO = "+v.getAtendente().getCodigo()+","
				+"VEN_BAN_CODIGO = '"+v.getBanco().getCodigo()+"', "
				+"VEN_VALOR_TOTAL = "+v.getVenValorTotal()+", "
				+"VEN_FORMA_PAGAMENTO = '"+v.getVenFormaPagamento()+"', "
				+"VEN_OBSERVACOES = '"+v.getVenObservacoes()+"' "	
				+ "WHERE VEN_CODIGO =" + v.getVenCodigo()+" ;";

		getVenda(v.getVenCodigo());
		return cnx.atualize(strAtualizao) > 0;
		
	}
	
	public boolean delVenda(Vendas 	v) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException 
	{
		
		String strDelecao = 
				"DELETE FROM vendas "
				+ "WHERE VEN_CODIGO = '" + v.getVenCodigo() + "';";
		
		getVenda(v.getVenCodigo());		
		return cnx.atualize(strDelecao) > 0;
		
	}		
	
	public List<Vendas> getVendas () throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException,
		EntityNotExistException
	{

		Vendas v = null;
		Cliente c = null;
		Atendente a = null;
		Banco b = null;
		
		List<Vendas> listaDeVendas = new ArrayList<Vendas>();
		
		String strBusca = "SELECT * "
				+ "FROM vendas;";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()){
				rs.beforeFirst();
				while(rs.next()) {
					c = cdb.getCliente(rs.getInt(2));
					a = adb.getAtendente(rs.getInt(3));
					b = bdb.getBanco(rs.getInt(4));
					
					v = new Vendas(
					rs.getInt(1), 
					c,
					a,
					b,
					rs.getFloat(5),
					rs.getString(6),
					rs.getString(7));  
					listaDeVendas.add(v);
				}
			}else {
				throw new EntityTableIsEmptyException("Vendas");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		
		
		return listaDeVendas;
	}	
	
	public List<Vendas> getVendasPorClienteNome (String cliente) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException,
		EntityNotExistException
	{

		Vendas v = null;
		Cliente c = null;
		Atendente a = null;
		Banco b = null;
		List<Vendas> listaDeVendas = new ArrayList<Vendas>();
		
		String strBusca = "select * from " + 
				"vendas as v, clientes as c " + 
				"where v.VEN_CLI_CODIGO = c.CLI_CODIGO " + 
				"and c.CLI_NOME LIKE '%"+cliente+"%';";
		
		rs = cnx.consulte(strBusca);
		
		
		try {
			if (rs.next()){
				rs.beforeFirst();
				while(rs.next()) {
					c = cdb.getCliente(rs.getInt(2));
					a = adb.getAtendente(rs.getInt(3));
					b = bdb.getBanco(rs.getInt(4));
					
					v = new Vendas(
					rs.getInt(1), 
					c,
					a,
					b,
					rs.getFloat(5),
					rs.getString(6),
					rs.getString(7));  
					listaDeVendas.add(v);
				}
			}else {
				throw new EntityTableIsEmptyException("Vendas");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaDeVendas;
	}	

	public List<Vendas> getVendasPorClienteCodigo (int codigo) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException
{

	Vendas v = null;
	Cliente c = null;
	Atendente a = null;
	Banco b = null;
	List<Vendas> listaDeVendas = new ArrayList<Vendas>();
	
	String strBusca = "select * from " + 
			"vendas as v, clientes as c " + 
			"where v.VEN_CLI_CODIGO = c.CLI_CODIGO " + 
			"and c.CLI_CODIGO = "+codigo+";";
	
	rs = cnx.consulte(strBusca);
	
	
	try {
		if (rs.next()){
			rs.beforeFirst();
			while(rs.next()) {
				c = cdb.getCliente(rs.getInt(2));
				a = adb.getAtendente(rs.getInt(3));
				b = bdb.getBanco(rs.getInt(4));
				
				v = new Vendas(
				rs.getInt(1), 
				c,
				a,
				b,
				rs.getFloat(5),
				rs.getString(6),
				rs.getString(7));  
				listaDeVendas.add(v);
			}
		}else {
			throw new EntityTableIsEmptyException("Vendas");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeVendas;
}	
	
	public List<Vendas> getVendasPorAtendenteNome (String atendente) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException
{

	Vendas v = null;
	Cliente c = null;
	Atendente a = null;
	Banco b = null;
	List<Vendas> listaDeVendas = new ArrayList<Vendas>();
	
	String strBusca = "select * from " + 
			"vendas as v, atendentes as a " + 
			"where v.VEN_ATE_CODIGO = a.ATE_CODIGO " + 
			"and a.ATE_NOME LIKE '%"+atendente+"%';";
	
	rs = cnx.consulte(strBusca);
	
	
	try {
		if (rs.next()){
			rs.beforeFirst();
			while(rs.next()) {
				c = cdb.getCliente(rs.getInt(2));
				a = adb.getAtendente(rs.getInt(3));
				b = bdb.getBanco(rs.getInt(4));
				
				v = new Vendas(
				rs.getInt(1), 
				c,
				a,
				b,
				rs.getFloat(5),
				rs.getString(6),
				rs.getString(7));  
				listaDeVendas.add(v);
			}
		}else {
			throw new EntityTableIsEmptyException("Vendas");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeVendas;
}	

	public List<Vendas> getVendasPorAtendenteCodigo (int codigo) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException
{

	Vendas v = null;
	Cliente c = null;
	Atendente a = null;
	Banco b = null;
	List<Vendas> listaDeVendas = new ArrayList<Vendas>();
	
	String strBusca = "select * from " + 
			"vendas as v, atendentes as a " + 
			"where v.VEN_ATE_CODIGO = a.ATE_CODIGO " + 
			"and a.ATE_CODIGO = "+codigo+";";
	
	rs = cnx.consulte(strBusca);
	
	
	try {
		if (rs.next()){
			rs.beforeFirst();
			while(rs.next()) {
				c = cdb.getCliente(rs.getInt(2));
				a = adb.getAtendente(rs.getInt(3));
				b = bdb.getBanco(rs.getInt(4));
				
				v = new Vendas(
				rs.getInt(1), 
				c,
				a,
				b,
				rs.getFloat(5),
				rs.getString(6),
				rs.getString(7));  
				listaDeVendas.add(v);
			}
		}else {
			throw new EntityTableIsEmptyException("Vendas");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeVendas;
}	
	
	public List<Vendas> getVendasPorBancoNome (String banco) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException
{

	Vendas v = null;
	Cliente c = null;
	Atendente a = null;
	Banco b = null;
	List<Vendas> listaDeVendas = new ArrayList<Vendas>();
	
	String strBusca = "select * from " + 
			"vendas as v, bancos as b " + 
			"where v.VEN_BAN_CODIGO = b.BAN_CODIGO " + 
			"and BAN_NOME LIKE '%"+banco+"%';";
	
	rs = cnx.consulte(strBusca);
	
	
	try {
		if (rs.next()){
			rs.beforeFirst();
			while(rs.next()) {
				c = cdb.getCliente(rs.getInt(2));
				a = adb.getAtendente(rs.getInt(3));
				b = bdb.getBanco(rs.getInt(4));
				
				v = new Vendas(
				rs.getInt(1), 
				c,
				a,
				b,
				rs.getFloat(5),
				rs.getString(6),
				rs.getString(7));  
				listaDeVendas.add(v);
			}
		}else {
			throw new EntityTableIsEmptyException("Vendas");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeVendas;
}	

	public List<Vendas> getVendasPorBancoCodigo (int codigo) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException,
	EntityNotExistException
{

	Vendas v = null;
	Cliente c = null;
	Atendente a = null;
	Banco b = null;
	List<Vendas> listaDeVendas = new ArrayList<Vendas>();
	
	String strBusca = "select * from " + 
			"vendas as v, bancos as b " + 
			"where v.VEN_BAN_CODIGO = b.BAN_CODIGO " + 
			"and b.BAN_CODIGO ="+codigo+";";
	
	rs = cnx.consulte(strBusca);
	
	
	try {
		if (rs.next()){
			rs.beforeFirst();
			while(rs.next()) {
				c = cdb.getCliente(rs.getInt(2));
				a = adb.getAtendente(rs.getInt(3));
				b = bdb.getBanco(rs.getInt(4));
				
				v = new Vendas(
				rs.getInt(1), 
				c,
				a,
				b,
				rs.getFloat(5),
				rs.getString(6),
				rs.getString(7));  
				listaDeVendas.add(v);
			}
		}else {
			throw new EntityTableIsEmptyException("Vendas");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeVendas;
}	
	
}
















