package br.ufac.academico.logic;

import java.util.*;
import br.ufac.academico.db.*;
import br.ufac.academico.entity.*;
import br.ufac.academico.exception.*;

public class ProdutosLogic {

	private ProdutosDB pdb;
	private FornecedoresLogic fl;
	private CategoriasDeProdutosLogic cpl;
	

	public ProdutosLogic(Conexao cnx) {
		pdb = new ProdutosDB(cnx);
		fl = new FornecedoresLogic(cnx);
		cpl = new CategoriasDeProdutosLogic(cnx);
	}

	public boolean addProduto(int proCodigo, int proForCodigo, int proCatCodigo, String proDescricao,
			int proQntDisponivel, String proUltimaAquisicao, float proValorUnitario, String proFabricante,
			String proDetalhes)
	throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityAlreadyExistException,
	InvalidFieldException{

		Fornecedores f = null;
		CategoriasProdutos cdp = null;
		
		List<String> camposInvalidos = new ArrayList<String>();
		boolean haCamposInvalidos = false;

		if(proCodigo < 1 || String.valueOf(proCodigo).length() > 5){
			haCamposInvalidos = true;
			camposInvalidos.add("Codigo");
		}
		
		if(proDescricao.isEmpty() || proDescricao.length() > 100){
			haCamposInvalidos = true;
			camposInvalidos.add("Nome Fantasia");
		}
		
		// VALIDAR OS DEMAIS CAMPOS DE OUTRAS TABELAS

		try {
			f = fl.getFornecedor(proForCodigo);
		} catch (EntityNotExistException e) {
			haCamposInvalidos = true;
			camposInvalidos.add("Fornencedor");
		}		

		
		try {
			cdp = cpl.getCategoria(proCatCodigo);
		} catch (EntityNotExistException e) {
			haCamposInvalidos = true;
			camposInvalidos.add("Categoria");
		}		

		if (haCamposInvalidos){
			throw new InvalidFieldException("Produtos", camposInvalidos);
		}
		
		


		Produtos p = new Produtos(proCodigo, f, cdp, proDescricao, proQntDisponivel, proUltimaAquisicao, proValorUnitario, proFabricante, proDetalhes);
		return pdb.addProduto(p);

	}

	public Produtos getProduto(int codigo) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException 
	{

		return pdb.getProduto(codigo);

	}

	public boolean updProduto(int proCodigo, int proForCodigo, int proCatCodigo, String proDescricao,
			int proQntDisponivel, String proUltimaAquisicao, float proValorUnitario, String proFabricante,
			String proDetalhes)
	throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityNotExistException,
	InvalidFieldException{

		Fornecedores f = null;
		CategoriasProdutos cdp = null;
		
		List<String> camposInvalidos = new ArrayList<String>();
		boolean haCamposInvalidos = false;

		if(proCodigo < 1 || String.valueOf(proCodigo).length() > 5){
			haCamposInvalidos = true;
			camposInvalidos.add("Codigo");
		}
		
		if(proDescricao.isEmpty() || proDescricao.length() > 100){
			haCamposInvalidos = true;
			camposInvalidos.add("Nome Fantasia");
		}
		
		// VALIDAR OS DEMAIS CAMPOS DE OUTRAS TABELAS

		try {
			f = fl.getFornecedor(proForCodigo);
		} catch (EntityNotExistException e) {
			haCamposInvalidos = true;
			camposInvalidos.add("Fornencedor");
		}		

		
		try {
			cdp = cpl.getCategoria(proCatCodigo);
		} catch (EntityNotExistException e) {
			haCamposInvalidos = true;
			camposInvalidos.add("Categoria");
		}		

		if (haCamposInvalidos){
			throw new InvalidFieldException("Produtos", camposInvalidos);
		}
		
		


		Produtos p = new Produtos(proCodigo, f, cdp, proDescricao, proQntDisponivel, proUltimaAquisicao, proValorUnitario, proFabricante, proDetalhes);
		return pdb.updProduto(p);


	}

	public boolean delProfuto(int proCodigo) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException
	{		
		Produtos p = pdb.getProduto(proCodigo);
		return pdb.delProduto(p);

	}

	public List<Produtos> getProdutos() throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException, 
		EntityNotExistException
	{
		return pdb.getProdutos();
	}

	public List<Produtos> getProdutosPorDescricao(String descricao) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException, 
		EntityNotExistException
	{
		return pdb.getProdutosPorDescricao(descricao);
	}
	
	public List<Produtos> getProdutosIntervaloPreco(String v1,String v2) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return pdb.getProdutosIntervaloPreco(v1,v2);
}
	
	public List<Produtos> getProdutosIntervaloQuantidade(String v1,String v2) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return pdb.getProdutosIntervaloQuantidade(v1,v2);
}
	
	
}










