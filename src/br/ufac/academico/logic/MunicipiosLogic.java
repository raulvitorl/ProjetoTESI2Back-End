package br.ufac.academico.logic;

import java.util.*;
import br.ufac.academico.db.*;
import br.ufac.academico.entity.*;
import br.ufac.academico.exception.*;

public class MunicipiosLogic {

	private MunicipiosDB mdb;
	public MunicipiosLogic(Conexao cnx) {
		mdb = new MunicipiosDB(cnx);
	}
	
	public MunicipiosLogic() {
		mdb = new MunicipiosDB();
	}
	
	public void setConexao(Conexao cnx){
		mdb.setConexao(cnx);
	}

	
	public boolean addMunicipio(int munCodigo, String munNome, String munUfEstado, String munCep) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityAlreadyExistException,
		InvalidFieldException 
	{

		List<String> camposInvalidos = new ArrayList<String>();
		boolean haCamposInvalidos = false;

		if(munNome.isEmpty() || munNome.length() > 100){
			haCamposInvalidos = true;
			camposInvalidos.add("Nome do Municipio");
		}
		
		if(String.valueOf(munCodigo).isEmpty() || String.valueOf(munCodigo).length() > 5){
			haCamposInvalidos = true;
			camposInvalidos.add("Código");
		}
		
		if(munUfEstado.isEmpty() || munUfEstado.length() > 2){
			haCamposInvalidos = true;
			camposInvalidos.add("UF");
		}
		
		
		if(munCep.isEmpty() || munCep.length() > 8){
			haCamposInvalidos = true;
			camposInvalidos.add("CEP");
		}		

		if (haCamposInvalidos){
			throw new InvalidFieldException("Municipios", camposInvalidos);
		}			
		
		Municipios m = new Municipios(munCodigo, munNome, munUfEstado, munCep);
		return mdb.addMunicipio(m);
		
	}
	public Municipios getMunicipio(int codigo) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException 
{

	return mdb.getMunicipio(codigo);
	
}
	
	public boolean updMunicipio(int munCodigo, String munNome, String munUfEstado, String munCep) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException,
		InvalidFieldException
	{

	List<String> camposInvalidos = new ArrayList<String>();
	boolean haCamposInvalidos = false;

	if(munNome.isEmpty() || munNome.length() > 100){
		haCamposInvalidos = true;
		camposInvalidos.add("Nome do Municipio");
	}
	
	if(String.valueOf(munCodigo).isEmpty() || String.valueOf(munCodigo).length() > 5){
		haCamposInvalidos = true;
		camposInvalidos.add("Código");
	}
	
	if(munUfEstado.isEmpty() || munUfEstado.length() > 2){
		haCamposInvalidos = true;
		camposInvalidos.add("UF");
	}
	
	
	if(munCep.isEmpty() || munCep.length() > 8){
		haCamposInvalidos = true;
		camposInvalidos.add("CEP");
	}		

	if (haCamposInvalidos){
		throw new InvalidFieldException("Municipios", camposInvalidos);
	}			
	
	Municipios m = new Municipios(munCodigo, munNome, munUfEstado, munCep);
	return mdb.updMunicipio(m);
	}
	
	public boolean delMunicipio(int munCodigo, String munNome, String munUfEstado, String munCep) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException 
	{
		Municipios m = new Municipios(munCodigo, munNome, munUfEstado, munCep);
		return mdb.delMunicispios(m);
	}
	
	public List<Municipios> getMunicipios() throws
		DataBaseGenericException,
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException
	{
		return mdb.getMunicipios();
	}
	
	
	public Municipios getMunicipioPorNome(String nome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return mdb.getMunicipioPorNome(nome);
}
	
	
	
	
	public List<String> getNomesMunicipios() throws
	DataBaseGenericException,
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException
{
	return mdb.getNomesMunicipios();
}
	
	
	
	public List<Municipios> getMunicipiosPorNome(String nome) throws
		DataBaseGenericException,
		DataBaseNotConnectedException,
		EntityTableIsEmptyException
	{
		return mdb.getMunicipiosPorNome(nome);
	}
	
	public List<Municipios> getMunicipiosPorUF(String uf) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityTableIsEmptyException
{
	return mdb.getMunicipiosPorUF(uf);
}
	
}










