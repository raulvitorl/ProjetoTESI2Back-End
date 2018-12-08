package br.ufac.academico.entidades;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tipo_mensagens")
//Consultas que serão realizadas no repositório
@NamedQueries({
	//Essa named querry retorna todos os Atendentes,
	//e vai ser chamada de dentro do repositório
	@NamedQuery(name="TiposMensagens.todos", 
		query="SELECT tms FROM TipoMensagem tms"),
	
	@NamedQuery(name="TiposMensagens.todosPorDescricao", 
		query="SELECT tms FROM TipoMensagem tms ORDER BY tms.descricao")
})
public class TipoMensagem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigo;
	@Column(name="tmsdescricao")
	private String descricao;
	
	@OneToMany(mappedBy = "tipo", targetEntity = Mensagem.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Mensagem> mensagens;
	
public TipoMensagem(){
		
	}

public long getCodigo() {
	return codigo;
}

public void setCodigo(long codigo) {
	this.codigo = codigo;
}

public String getDescricao() {
	return descricao;
}

public void setDescricao(String descricao) {
	this.descricao = descricao;
}

public Collection<Mensagem> getMensagens() {
	return mensagens;
}

public void setMensagens(Collection<Mensagem> mensagens) {
	this.mensagens = mensagens;
}

@Override
public String toString() {
	return "TiposMensagens [codigo=" + codigo + ", descricao=" + descricao + ", mensagens=" + mensagens + "]";
}
	
	
	
}
