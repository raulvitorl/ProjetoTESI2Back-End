package br.ufac.academico.exception;

public class EntityTableIsEmptyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EntityTableIsEmptyException(String entidade){
//		super("Tabela da entidade '" + entidade + "' esta vazia!");
		super("Nada para mostrar aqui!");
	}
}
