package br.ufac.academico.gui;
import javax.swing.table.*;

import br.ufac.academico.entity.*;
import java.util.*;

@SuppressWarnings("serial")
public class BancosTableModel extends AbstractTableModel{

	List<Banco> listBancos;

	public BancosTableModel(List<Banco> bancos){
		this.listBancos = bancos;
	}
	
	@Override
	public int getRowCount() {
		return listBancos.size();
	}

	@Override
	public int getColumnCount() {		
		return 2;	
	}
	@Override	
	public Object getValueAt(int rowIndex, int columnIndex) {		

		Banco b;
		Object dado = null;
		
		b = listBancos.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = b.getCodigo(); break;
		case 1: dado = b.getNome(); break;		
		}
		
		return dado;		
	}

	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "Codigo"; break;
		case 1: nome = "Nome"; break;				
		}		
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		Object obj;
		
		switch (columnIndex) {
		case 0: obj = Integer.class; break;	
		case 1: obj = String.class; break;	
		default: obj = null; break;
		}		

		return obj.getClass();
	}
}








