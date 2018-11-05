package br.ufac.academico.exception;

import java.util.*;

public class InvalidFieldException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFieldException(String entidade, List<String> listaDeCampos){
		super("Campos invalidos para '"
				+ entidade + "' " 
				+ listaDeCampos.toString());
	}
}
