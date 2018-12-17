package br.ufac.academico.domain.enums;

public enum StatusAtendente {
	AUSENTE(1,"Ausente"),
	DISPONIVEL(2,"Disponivel");
	
	private int cod;
	private String descricao;
	
	private StatusAtendente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static StatusAtendente toEnum(Integer cod){
		if(cod==null)
			return null;
		for(StatusAtendente x : StatusAtendente.values()) {
			if(cod.equals(x.getCod())){
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}
