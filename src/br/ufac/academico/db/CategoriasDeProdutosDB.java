package br.ufac.academico.db;

import java.sql.*;
import java.util.*;

import br.ufac.academico.entity.CategoriasProdutos;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;
import br.ufac.academico.exception.EntityAlreadyExistException;
import br.ufac.academico.exception.EntityNotExistException;
import br.ufac.academico.exception.EntityTableIsEmptyException;

public class CategoriasDeProdutosDB {
	
	private Conexao cnx;
	private ResultSet rs;
	
	public CategoriasDeProdutosDB(Conexao cnx) {
		this.cnx = cnx;
	}
	
	

	public CategoriasDeProdutosDB() {
		
	}

	public void setConexao(Conexao cnx){
		this.cnx = cnx;
	}

	public boolean addCategoriaDeProduto(CategoriasProdutos cdp) throws 
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityAlreadyExistException
	{

		String strInsercao = 
				"INSERT INTO categorias_produtos (CAT_CODIGO, CAT_IDENTIFICADOR, CAT_DESCRICAO) "
				+ "VALUES (" + cdp.getCatCodigo() + ", "
				+ "" + cdp.getCatIdentificador() +","
				+"'"+cdp.getCatDescricao()+"');";

		try {
			getCategoriaDeProduto(cdp.getCatCodigo());
			throw new EntityAlreadyExistException("Categoria (codigo='" + cdp.getCatCodigo() + "')"); 
		} catch (EntityNotExistException e) {
			return cnx.atualize(strInsercao) > 0;
		}
		
	}
	
	public CategoriasProdutos getCategoriaDeProdutoPorNome (String nome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{

		CategoriasProdutos cdp = null;
		
		String strBusca = "SELECT * FROM categorias_produtos WHERE CAT_DESCRICAO = " + nome + "";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				cdp = new CategoriasProdutos(rs.getInt(1), rs.getInt(2), rs.getString(3)); 
			}else {
				throw new EntityNotExistException("Categoria (codigo='" + nome + "')");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return cdp;
	}

	
	public CategoriasProdutos getCategoriaDeProduto (int catCodigo) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityNotExistException
{

	CategoriasProdutos cdp = null;
	
	String strBusca = "SELECT * FROM categorias_produtos WHERE CAT_CODIGO = " + catCodigo + "";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			cdp = new CategoriasProdutos(rs.getInt(1), rs.getInt(2), rs.getString(3)); 
		}else {
			throw new EntityNotExistException("Categoria (codigo='" + catCodigo + "')");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return cdp;
}
	
	
	public boolean updCategoriaDeProduto(CategoriasProdutos cdp) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{
		
		String strAtualizao = 
				"UPDATE categorias_produtos "
				+ "SET CAT_IDENTIFICADOR = '" + cdp.getCatIdentificador() + "' "
				+",CAT_DESCRICAO = '"+cdp.getCatDescricao()+"'"
				+ "WHERE CAT_CODIGO = '" + cdp.getCatCodigo() + "';";

		getCategoriaDeProduto(cdp.getCatCodigo());
		return cnx.atualize(strAtualizao) > 0;
		
	}
	
	public boolean delCategoriaDeProdutos(CategoriasProdutos cdp) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException 
	{
		
		String strDelecao = 
				"DELETE FROM categorias_produtos "
				+ "WHERE CAT_CODIGO = '" + cdp.getCatCodigo() + "';";
		
		getCategoriaDeProduto(cdp.getCatCodigo());		
		return cnx.atualize(strDelecao) > 0;
		
	}		
	
	public List<CategoriasProdutos> getCategoriasProdutos () throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException
	{

		CategoriasProdutos cdp = null;
		List<CategoriasProdutos> listaDeCategorias = new ArrayList<CategoriasProdutos>();
		
		String strBusca = "SELECT * "
				+ "FROM categorias_produtos;";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					cdp = new CategoriasProdutos(rs.getInt(1), rs.getInt(2), rs.getString(3));
					listaDeCategorias.add(cdp);
				}
			}else {
				throw new EntityTableIsEmptyException("Categoria de Produto");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaDeCategorias;
	}	
	
	public List<CategoriasProdutos> getCategoriasPorDescricao (String descricao) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException
	{

		CategoriasProdutos cdp = null;
		List<CategoriasProdutos> listaDeCategorias = new ArrayList<CategoriasProdutos>();
		
		String strBusca = "SELECT *"
				+ "FROM categorias_produtos "
				+ "WHERE CAT_DESCRICAO LIKE '%" + descricao + "%';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					cdp = new CategoriasProdutos(rs.getInt(1), rs.getInt(2), rs.getString(3));
					listaDeCategorias.add(cdp);
				}
			}else {
				throw new EntityTableIsEmptyException("Categorias de Produtos");
			}
		} catch (SQLException sqle) {
			System.out.printf("Erro # %d (%s)\n", 
					sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaDeCategorias;
	}	

	public List<CategoriasProdutos> getCategoriasPorIdentificador (int identificador) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException
{

	CategoriasProdutos cdp = null;
	List<CategoriasProdutos> listaDeCategorias = new ArrayList<CategoriasProdutos>();
	
	String strBusca = "SELECT *"
			+ "FROM categorias_produtos "
			+ "WHERE CAT_IDENTIFICADOR LIKE '%" + identificador + "%';";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				cdp = new CategoriasProdutos(rs.getInt(1), rs.getInt(2), rs.getString(3));
				listaDeCategorias.add(cdp);
			}
		}else {
			throw new EntityTableIsEmptyException("Categorias De Produtos");
		}
	} catch (SQLException sqle) {
		System.out.printf("Erro # %d (%s)\n", 
				sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeCategorias;
}	
	
	public List<String> getCategoriasDeProdutosDescricao () throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException
{

	CategoriasProdutos cdp = null;
	List<String> listaDeCategorias = new ArrayList<String>();
	
	String strBusca = "SELECT *"
			+ "FROM categorias_produtos; ";
	
	rs = cnx.consulte(strBusca);
	
	try {
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				cdp = new CategoriasProdutos(rs.getInt(1), rs.getInt(2), rs.getString(3));
				listaDeCategorias.add(cdp.getCatDescricao());
			}
		}else {
			throw new EntityTableIsEmptyException("Categorias De Produtos");
		}
	} catch (SQLException sqle) {
		System.out.printf("Erro # %d (%s)\n", 
				sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeCategorias;
}	
	
	
}