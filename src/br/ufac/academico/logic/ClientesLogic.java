package br.ufac.academico.logic;

import java.util.*;
import br.ufac.academico.db.*;
import br.ufac.academico.entity.*;
import br.ufac.academico.exception.*;

public class ClientesLogic {

	private ClientesDB cdb;
	private MunicipiosLogic ml;

	public ClientesLogic(Conexao cnx) {
		cdb = new ClientesDB(cnx);
		ml = new MunicipiosLogic(cnx);
	}
	
	public ClientesLogic() {
		cdb = new ClientesDB();
		ml = new MunicipiosLogic();
	}
	
	public void setConexao(Conexao cnx){
		cdb.setConexao(cnx);
	}
	

	public boolean addCliente(int codigo, long cliMunCodigo, String nome, String dataNascimento, char sexo, String cpf,
			String rg, String cnpj, String endereco, String email, String dataCadastro, char tipo, char status,
			String fone, String nomeContato)
	throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityAlreadyExistException,
	InvalidFieldException{

		Municipios m = null;
		List<String> camposInvalidos = new ArrayList<String>();
		boolean haCamposInvalidos = false;

		if(codigo < 1 || String.valueOf(codigo).length() > 5){
			haCamposInvalidos = true;
			camposInvalidos.add("Codigo");
		}

		if(dataNascimento.isEmpty()){
			haCamposInvalidos = true;
			camposInvalidos.add("Data da Nascimento");
		}
		
		if(nome.isEmpty() || nome.length() > 50){
			haCamposInvalidos = true;
			camposInvalidos.add("Nome");
		}
		
		if(String.valueOf(tipo).isEmpty() || String.valueOf(tipo).length() > 1){
			haCamposInvalidos = true;
			camposInvalidos.add("Tipo");
		}
		
		if(String.valueOf(status).isEmpty() || String.valueOf(status).length() > 1){
			haCamposInvalidos = true;
			camposInvalidos.add("Status");
		}

		// VALIDAR OS DEMAIS CAMPOS DE OUTRAS TABELAS

		try {
			m = ml.getMunicipio(cliMunCodigo);
		} catch (EntityNotExistException e) {
			haCamposInvalidos = true;
			camposInvalidos.add("Municipio");
		}		

		if (haCamposInvalidos){
			throw new InvalidFieldException("Cliente", camposInvalidos);
		}


		Clientes c = new Clientes(codigo, m, nome, dataNascimento, sexo, cpf, rg, cnpj, endereco, email, dataCadastro, tipo, status, fone, nomeContato);
		return cdb.addCliente(c);

	}

	public Clientes getCliente(long cliCodigo) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException 
	{

		return cdb.getCliente(cliCodigo);

	}

	public Clientes getClientePorNome(String nome) throws
	DataBaseGenericException,
	DataBaseNotConnectedException,
	EntityNotExistException 
	{

		return cdb.getClientePorNome(nome);

	}
	
	public boolean updCliente(int codigo, long cliMunCodigo, String nome, String dataNascimento, char sexo, String cpf,
			String rg, String cnpj, String endereco, String email, String dataCadastro, char tipo, char status,
			String fone, String nomeContato) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException,
		InvalidFieldException 
	{
		Municipios m = null;
		List<String> camposInvalidos = new ArrayList<String>();
		boolean haCamposInvalidos = false;

		if(codigo < 1 || String.valueOf(codigo).length() > 5){
			haCamposInvalidos = true;
			camposInvalidos.add("Codigo");
		}

		if(dataNascimento.isEmpty()){
			haCamposInvalidos = true;
			camposInvalidos.add("Data da Nascimento");
		}
		
		if(nome.isEmpty() || nome.length() > 50){
			haCamposInvalidos = true;
			camposInvalidos.add("Nome");
		}
		
		if(String.valueOf(tipo).isEmpty() || String.valueOf(tipo).length() > 1){
			haCamposInvalidos = true;
			camposInvalidos.add("Tipo");
		}
		
		if(String.valueOf(status).isEmpty() || String.valueOf(status).length() > 1){
			haCamposInvalidos = true;
			camposInvalidos.add("Status");
		}

		// VALIDAR OS DEMAIS CAMPOS DE OUTRAS TABELAS

		try {
			m = ml.getMunicipio(cliMunCodigo);
		} catch (EntityNotExistException e) {
			haCamposInvalidos = true;
			camposInvalidos.add("Municipio");
		}		

		if (haCamposInvalidos){
			throw new InvalidFieldException("Cliente", camposInvalidos);
		}


		Clientes c = new Clientes(codigo, m, nome, dataNascimento, sexo, cpf, rg, cnpj, endereco, email, dataCadastro, tipo, status, fone, nomeContato);
		return cdb.updCliente(c);

	}

	public boolean delCliente(int codigo, long cliMunCodigo, String nome, String dataNascimento, char sexo, String cpf,
			String rg, String cnpj, String endereco, String email, String dataCadastro, char tipo, char status,
			String fone, String nomeContato) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityNotExistException
	{		
		
		Municipios municipio = ml.getMunicipio(cliMunCodigo);		

		Clientes c = new Clientes(codigo, municipio, nome, dataNascimento, sexo, cpf, rg, cnpj, endereco, email, dataCadastro, tipo, status, fone, nomeContato);

		return cdb.delClientes(c);

	}

	public List<Clientes> getClientes() throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException, 
		EntityNotExistException
	{
		return cdb.getClientes();
	}
	
	public List<String> getNomesClientes() throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return cdb.getNomesClientes();
}

	public List<Clientes> getClientesPorNome(String nome) throws
		DataBaseGenericException, 
		DataBaseNotConnectedException, 
		EntityTableIsEmptyException, 
		EntityNotExistException
	{
		return cdb.getClientesPorNome(nome);
	}
	
	public List<Clientes> getClientesPorCpf(String cnpj) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return cdb.getClientesPorCpf(cnpj);
}
	
	public List<Clientes> getClientesPorMunicipio(String municipio) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return cdb.getClientesPorMunicipio(municipio);
}

	public List<Clientes> getClientesPorEmail(String municipio) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return cdb.getClientesPorEmail(municipio);
}

	public List<Clientes> getClientesPorSexo(String municipio) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return cdb.getClientesPorSexo(municipio);
}

	public List<Clientes> getClientesPorStatus(String municipio) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return cdb.getClientesPorStatus(municipio);
}

	public List<Clientes> getClientesPorRg(String municipio) throws
	DataBaseGenericException, 
	DataBaseNotConnectedException, 
	EntityTableIsEmptyException, 
	EntityNotExistException
{
	return cdb.getClientesPorRG(municipio);
}
}










