package br.ufac.academico.exception;

public class EntityNotExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityNotExistException(String entidade){
		super("A entidade "+ entidade+" não existe!");
	}
}
