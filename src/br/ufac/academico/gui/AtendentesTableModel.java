package br.ufac.academico.gui;
import javax.swing.table.*;

import br.ufac.academico.entity.*;
import java.util.*;

@SuppressWarnings("serial")
public class AtendentesTableModel extends AbstractTableModel{

	List<Atendentes> atendentes;

	public AtendentesTableModel(List<Atendentes> atendentes){
		this.atendentes = atendentes;
	}
	
	@Override
	public int getRowCount() {
		return atendentes.size();
	}

	@Override
	public int getColumnCount() {		
		return 6;	
	}
	@Override	
	public Object getValueAt(int rowIndex, int columnIndex) {		

		Atendentes a;
		Object dado = null;
		
		a = atendentes.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = a.getCodigo(); break;
		case 1: dado = a.getNome(); break;		
		case 2: dado = a.getUltimoAcesso(); break;
		case 3: dado = a.getRamal(); break;
		case 4: dado = a.getEmail(); break;
		case 5: dado = a.getStatus(); break;
		}
		
		return dado;		
	}

	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "Codigo"; break;
		case 1: nome = "Nome"; break;		
		case 2: nome = "Ultimo Acesso"; break;
		case 3: nome = "Ramal"; break;
		case 4: nome = "Email"; break;
		case 5: nome = "Status"; break;
		}		
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		Object obj;
		
		switch (columnIndex) {
		case 0: obj = Integer.class; break;
		case 1: obj = String.class; break;
		case 2: obj = String.class; break;		
		case 3: obj = String.class; break;
		case 4: obj = String.class; break;		
		case 5: obj = char.class; break;	
		default: obj = null; break;
		}		

		return obj.getClass();
	}
}








