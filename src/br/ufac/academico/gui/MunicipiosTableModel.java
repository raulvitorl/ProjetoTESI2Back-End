package br.ufac.academico.gui;
import javax.swing.table.*;

import br.ufac.academico.entity.*;
import java.util.*;

@SuppressWarnings("serial")
public class MunicipiosTableModel extends AbstractTableModel{

	List<Municipios> municipios;

	public MunicipiosTableModel(List<Municipios> municipios){
		this.municipios = municipios;
	}
	
	@Override
	public int getRowCount() {
		return municipios.size();
	}

	@Override
	public int getColumnCount() {		
		return 4;	
	}
	@Override	
	public Object getValueAt(int rowIndex, int columnIndex) {		

		Municipios m;
		Object dado = null;
		
		m = municipios.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = m.getMunCodigo(); break;
		case 1: dado = m.getMunNome(); break;		
		case 2: dado = m.getMunUfEstado(); break;
		case 3: dado = m.getMunCep(); break;
		}
		
		return dado;		
	}

	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "Codigo"; break;
		case 1: nome = "Nome"; break;		
		case 2: nome = "Estado"; break;
		case 3: nome = "CEP"; break;		
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
		default: obj = null; break;
		}		

		return obj.getClass();
	}
}








