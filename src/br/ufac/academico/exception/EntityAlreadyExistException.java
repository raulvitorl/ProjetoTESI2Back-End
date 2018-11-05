package br.ufac.academico.exception;

public class EntityAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityAlreadyExistException(String entidade){
		super("A entidade "+entidade+" ja existe");
	}
}
