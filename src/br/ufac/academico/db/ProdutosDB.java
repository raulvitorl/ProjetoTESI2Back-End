package br.ufac.academico.db;

import java.sql.*;
import java.util.*;

import br.ufac.academico.entity.Fornecedores;
import br.ufac.academico.entity.CategoriasProdutos;
import br.ufac.academico.entity.Produtos;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;
import br.ufac.academico.exception.EntityAlreadyExistException;
import br.ufac.academico.exception.EntityNotExistException;
import br.ufac.academico.exception.EntityTableIsEmptyException;

public class ProdutosDB {
	
	private Conexao cnx;
	private ResultSet rs;
	
	//IMPORTANTE REFENCIAR A CAMADA DE CONEXÃO DAS 
	//TABELAS QUE VOU REFERENCIAR
	
	private FornecedoresDB fdp;
	private CategoriasDeProdutosDB cpd;
	
	public ProdutosDB(Conexao cnx) {
		this.cnx = cnx;
		fdp = new FornecedoresDB(this.cnx);
		cpd = new CategoriasDeProdutosDB(this.cnx);
	}

	public boolean addProduto(Produtos p) throws 
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityAlreadyExistException
	{

		String strInsercao = 
				"INSERT INTO produtos (PRO_CODIGO,PRO_FOR_CODIGO,PRO_CAT_CODIGO,PRO_DESCRICAO,PRO_QNT_DISPONIVEL,PRO_ULTIMA_AQUISICAO,PRO_VALOR_UNIT,PRO_FABRICANTE,PRO_DETALHES)"
				+ "VALUES ("+p.getProCodigo()+", "
				+p.getFornecedor().getForCodigo()+", "
				+p.getCategoria().getCatCodigo()+", '"
				+p.getProDescricao()+"', "+p.getProQntDisponivel()+", '"
				+p.getProUltimaAquisicao()+"', "+p.getProValorUnitario()+", '"
				+p.getProFabricante()+"', '"+p.getProDetalhes()+"');";
		try {
			getProduto(p.getProCodigo());
			throw new EntityAlreadyExistException("Produto (codigo='" + p.getProCodigo() + "')"); 
		} catch (EntityNotExistException e) {
			return cnx.atualize(strInsercao) > 0;
		}
		
	}
	
	public Produtos getProduto (int codigo) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{

		Produtos p = null;
		Fornecedores f = null;
		CategoriasProdutos c = null;
		
		String strBusca = "SELECT * "
				+ "FROM produtos "
				+ "WHERE PRO_CODIGO = '" + codigo + "';";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()){
				
				f = fdp.getFornecedor(rs.getInt(2));
				c = cpd.getCategoriaDeProduto(rs.getInt(3));
				
				p = new Produtos(
				rs.getInt(1), 
				f,
				c,
				rs.getString(4),
				rs.getInt(5),
				rs.getString(6),
				rs.getFloat(7), 
				rs.getString(8),
				rs.getString(9));  
			}else {
				throw new EntityNotExistException("Produto (codigo='" + codigo + "')");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return p;
	}
	
	public boolean updProduto(Produtos p) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityNotExistException
	{
		
		String strAtualizao = 
				"UPDATE produtos "
				+ "SET PRO_CODIGO = " +p.getProCodigo() + ","
				+"PRO_FOR_CODIGO = "+p.getFornecedor().getForCodigo()+","
				+"PRO_CAT_CODIGO = "+p.getCategoria().getCatCodigo()+","
				+"PRO_DESCRICAO = '"+p.getProDescricao()+"', "
				+"PRO_QNT_DISPONIVEL = "+p.getProQntDisponivel()+", "
				+"PRO_ULTIMA_AQUISICAO = '"+p.getProUltimaAquisicao()+"', "
				+"PRO_VALOR_UNIT = "+p.getProValorUnitario()+", "
				+"PRO_FABRICANTE = '"+p.getProFabricante()+"', "
				+"PRO_DETALHES = '"+p.getProDetalhes()+"' "	
				+ "WHERE PRO_CODIGO =" + p.getProCodigo()+" ;";

		getProduto(p.getProCodigo());
		return cnx.atualize(strAtualizao) > 0;
		
	}
	
	public boolean delProduto(Produtos 	p) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException 
	{
		
		String strDelecao = 
				"DELETE FROM produtos "
				+ "WHERE PRO_CODIGO = '" + p.getProCodigo() + "';";
		
		getProduto(p.getProCodigo());		
		return cnx.atualize(strDelecao) > 0;
		
	}		
	
	public List<Produtos> getProdutos () throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException,
		EntityNotExistException
	{


		Produtos p = null;
		Fornecedores f = null;
		CategoriasProdutos c = null;
		
		List<Produtos> listaDeProdutos = new ArrayList<Produtos>();
		
		String strBusca = "SELECT * "
				+ "FROM produtos;";
		
		rs = cnx.consulte(strBusca);
		
		try {
			if (rs.next()){
				rs.beforeFirst();
				while(rs.next()) {
					f = fdp.getFornecedor(rs.getInt(2));
					c = cpd.getCategoriaDeProduto(rs.getInt(3));
					
					p = new Produtos(
					rs.getInt(1), 
					f,
					c,
					rs.getString(4),
					rs.getInt(5),
					rs.getString(6),
					rs.getFloat(7), 
					rs.getString(8),
					rs.getString(9));  
					listaDeProdutos.add(p);
				}
			}else {
				throw new EntityTableIsEmptyException("Produtos");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		
		
		return listaDeProdutos;
	}	
	
	public List<Produtos> getProdutosPorDescricao (String descricao) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException,
		EntityTableIsEmptyException,
		EntityNotExistException
	{

		Produtos p = null;
		Fornecedores f = null;
		CategoriasProdutos c = null;
		List<Produtos> listaDeProdutos = new ArrayList<Produtos>();
		
		String strBusca = "SELECT * "
				+ "FROM produtos "
				+ "WHERE PRO_DESCRICAO LIKE '%" + descricao + "%';";
		
		rs = cnx.consulte(strBusca);
		
		
		try {
			if (rs.next()){
				rs.beforeFirst();
				while(rs.next()) {
					f = fdp.getFornecedor(rs.getInt(2));
					c = cpd.getCategoriaDeProduto(rs.getInt(3));
					
					p = new Produtos(
					rs.getInt(1), 
					f,
					c,
					rs.getString(4),
					rs.getInt(5),
					rs.getString(6),
					rs.getFloat(7), 
					rs.getString(8),
					rs.getString(9));  
					listaDeProdutos.add(p);
				}
			}else {
				throw new EntityTableIsEmptyException("Produtos");
			}
		} catch (SQLException sqle) {
			throw new DataBaseGenericException(sqle.getErrorCode(), 
					sqle.getMessage());
		}
		
		return listaDeProdutos;
	}	

	public List<Produtos> getProdutosIntervaloPreco (String v1,String v2) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException, 
	EntityNotExistException
{

		Produtos p = null;
		Fornecedores f = null;
		CategoriasProdutos c = null;
		List<Produtos> listaDeProdutos = new ArrayList<Produtos>();
	
	String strBusca = "SELECT * "
			+ "FROM produtos "
			+ "WHERE PRO_VALOR_UNIT BETWEEN " + v1 + " AND "+v2+";";
	
	rs = cnx.consulte(strBusca);
	
	
	try {
		if (rs.next()){
			rs.beforeFirst();
			while(rs.next()) {
				f = fdp.getFornecedor(rs.getInt(2));
				c = cpd.getCategoriaDeProduto(rs.getInt(3));
				
				p = new Produtos(
				rs.getInt(1), 
				f,
				c,
				rs.getString(4),
				rs.getInt(5),
				rs.getString(6),
				rs.getFloat(7), 
				rs.getString(8),
				rs.getString(9));  
				listaDeProdutos.add(p);
			}
		}else {
			throw new EntityTableIsEmptyException("Produtos");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeProdutos;
}	
	
	public List<Produtos> getProdutosIntervaloQuantidade (String v1,String v2) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException,
	EntityTableIsEmptyException, 
	EntityNotExistException
{

		Produtos p = null;
		Fornecedores f = null;
		CategoriasProdutos c = null;
		List<Produtos> listaDeProdutos = new ArrayList<Produtos>();
	
	String strBusca = "SELECT * "
			+ "FROM produtos "
			+ "WHERE PRO_QNT_DISPONIVEL BETWEEN " + v1 + " AND "+v2+";";
	
	rs = cnx.consulte(strBusca);
	
	
	try {
		if (rs.next()){
			rs.beforeFirst();
			while(rs.next()) {
				f = fdp.getFornecedor(rs.getInt(2));
				c = cpd.getCategoriaDeProduto(rs.getInt(3));
				
				p = new Produtos(
				rs.getInt(1), 
				f,
				c,
				rs.getString(4),
				rs.getInt(5),
				rs.getString(6),
				rs.getFloat(7), 
				rs.getString(8),
				rs.getString(9));  
				listaDeProdutos.add(p);
			}
		}else {
			throw new EntityTableIsEmptyException("Produtos");
		}
	} catch (SQLException sqle) {
		throw new DataBaseGenericException(sqle.getErrorCode(), 
				sqle.getMessage());
	}
	
	return listaDeProdutos;
}	

	
}
















