package br.ufac.academico.domain.enums;

public enum SexoCliente {
	MASCULINO(1,"Masculino"),
	FEMININO(2,"Feminono");
	
	private int cod;
	private String descricao;
	
	private SexoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static SexoCliente toEnum(Integer cod){
		if(cod==null)
			return null;
		for(SexoCliente x : SexoCliente.values()) {
			if(cod.equals(x.getCod())){
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}
