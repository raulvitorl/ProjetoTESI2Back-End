package br.ufac.academico.exception;

public class AccessDeniedForUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccessDeniedForUserException(String usuario){
		super("Usuario '"+ usuario + "' ou senha incorretos!");
	}
}
