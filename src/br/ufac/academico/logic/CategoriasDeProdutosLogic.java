package br.ufac.academico.logic;

import java.util.*;
import br.ufac.academico.db.*;
import br.ufac.academico.entity.*;
import br.ufac.academico.exception.*;

public class CategoriasDeProdutosLogic {

	private CategoriasDeProdutosDB cdb;
	
	public CategoriasDeProdutosLogic(Conexao cnx) {
		cdb = new CategoriasDeProdutosDB(cnx);
	}
	
	public CategoriasDeProdutosLogic(){
		cdb = new CategoriasDeProdutosDB();
	}
	
	public void setConexao(Conexao cnx) {
		cdb.setConexao(cnx);
	}
	
	public boolean addCategoria(int catCodigo, int catIdentificador, String catDescricao) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityAlreadyExistException,
		InvalidFieldException 
	{

		List<String> camposInvalidos = new ArrayList<String>();
		boolean haCamposInvalidos = false;

		if(String.valueOf(catCodigo).length() > 3){
			haCamposInvalidos = true;
			camposInvalidos.add("Codigo");
		}
		
		if(catDescricao.length() > 100){
			haCamposInvalidos = true;
			camposInvalidos.add("Descrição");
		}
		
		

		if (haCamposInvalidos){
			throw new InvalidFieldException("Categorias de Produtos", camposInvalidos);
		}			
		
		CategoriasProdutos cdp = new CategoriasProdutos(catCodigo, catIdentificador, catDescricao);
		return cdb.addCategoriaDeProduto(cdp);
		
	}
	
	public CategoriasProdutos getCategoria(long proCatCodigo) throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityNotExistException 
	{

		return cdb.getCategoriaDeProduto(proCatCodigo);
		
	}

	public CategoriasProdutos getCategoriaDeProdutoPorNome(String descricao) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException 
{

	return cdb.getCategoriaDeProdutoPorNome(descricao);
	
}
	
	
	public boolean updCategoria(int catCodigo, int catIdentificador, String catDescricao) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException,
		InvalidFieldException
	{

	List<String> camposInvalidos = new ArrayList<String>();
	boolean haCamposInvalidos = false;

	if(String.valueOf(catCodigo).length() > 3){
		haCamposInvalidos = true;
		camposInvalidos.add("Codigo");
	}
	
	if(catDescricao.length() > 100){
		haCamposInvalidos = true;
		camposInvalidos.add("Descrição");
	}

	if (haCamposInvalidos){
		throw new InvalidFieldException("Categorias de Produtos", camposInvalidos);
	}	
	CategoriasProdutos cdp = new CategoriasProdutos(catCodigo, catIdentificador, catDescricao);
	return cdb.updCategoriaDeProduto(cdp);
	}
	
	public boolean delCategoria(int catCodigo, int catIdentificador, String catDescricao) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException 
	{
		CategoriasProdutos cdp = new CategoriasProdutos(catCodigo, catIdentificador, catDescricao);
		return cdb.delCategoriaDeProdutos(cdp);
	}
	
	public List<CategoriasProdutos> getCategoriasProdutos() throws
		DataBaseGenericException,
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException
	{
		return cdb.getCategoriasProdutos();
	}
	
	public List<String> getCategoriasProdutosDescricao() throws
	DataBaseGenericException,
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException
{
	return cdb.getCategoriasDeProdutosDescricao();
}
	
	
	public List<CategoriasProdutos> getCategoriasPorDescricao(String descricao) throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityTableIsEmptyException
	{
		return cdb.getCategoriasPorDescricao(descricao);
	}
	
	public List<CategoriasProdutos> getCategoriasPorIdentificador(int identificador) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException
{
	return cdb.getCategoriasPorIdentificador(identificador);
}
	
	
}










