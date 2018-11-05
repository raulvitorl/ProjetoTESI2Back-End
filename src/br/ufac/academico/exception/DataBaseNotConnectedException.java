package br.ufac.academico.exception;

public class DataBaseNotConnectedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataBaseNotConnectedException(String db){
		super("Banco de dados '"+ db + "' nao esta conectado!");
	}
}
