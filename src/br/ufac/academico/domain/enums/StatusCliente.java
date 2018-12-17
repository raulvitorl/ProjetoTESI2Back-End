package br.ufac.academico.domain.enums;

public enum StatusCliente {
	DEVEDOR(1,"Devedor"),
	QUITADO(2,"Quitado"),
	CREDOR(2,"Credor");
	
	private int cod;
	private String descricao;
	
	private StatusCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static StatusCliente toEnum(Integer cod){
		if(cod==null)
			return null;
		for(StatusCliente x : StatusCliente.values()) {
			if(cod.equals(x.getCod())){
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}
