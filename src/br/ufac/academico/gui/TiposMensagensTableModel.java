package br.ufac.academico.gui;
import javax.swing.table.*;

import br.ufac.academico.entity.*;
import java.util.*;

@SuppressWarnings("serial")
public class TiposMensagensTableModel extends AbstractTableModel{

	List<TipoMensagens> listTipoMensagens;

	public TiposMensagensTableModel(List<TipoMensagens> TipoMensagenss){
		this.listTipoMensagens = TipoMensagenss;
	}
	
	@Override
	public int getRowCount() {
		return listTipoMensagens.size();
	}

	@Override
	public int getColumnCount() {		
		return 2;	
	}
	@Override	
	public Object getValueAt(int rowIndex, int columnIndex) {		

		TipoMensagens tm;
		Object dado = null;
		
		tm = listTipoMensagens.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = tm.getTmsCodigo(); break;
		case 1: dado = tm.getTmsDescricao(); break;		
		}
		
		return dado;		
	}

	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "Codigo"; break;
		case 1: nome = "Descrição"; break;				
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








