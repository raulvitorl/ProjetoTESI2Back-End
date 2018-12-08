package br.ufac.academico.domain.enums;

public enum PerfilAtendente {
	SUPERVISOR(1,"Supervisor"),
	BALCONISTA(2,"Balconista");
	
	private int cod;
	private String descricao;
	
	private PerfilAtendente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static PerfilAtendente toEnum(Integer cod){
		if(cod==null)
			return null;
		for(PerfilAtendente x : PerfilAtendente.values()) {
			if(cod.equals(x.getCod())){
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}
