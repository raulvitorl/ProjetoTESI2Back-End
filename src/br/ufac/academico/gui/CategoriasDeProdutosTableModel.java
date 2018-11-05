package br.ufac.academico.gui;
import javax.swing.table.*;

import br.ufac.academico.entity.*;
import java.util.*;

@SuppressWarnings("serial")
public class CategoriasDeProdutosTableModel extends AbstractTableModel{

	List<CategoriasProdutos> listaDeProdutos;

	public CategoriasDeProdutosTableModel(List<CategoriasProdutos> produtos){
		this.listaDeProdutos = produtos;
	}
	
	@Override
	public int getRowCount() {
		return listaDeProdutos.size();
	}

	@Override
	public int getColumnCount() {		
		return 3;	
	}
	@Override	
	public Object getValueAt(int rowIndex, int columnIndex) {		

		CategoriasProdutos cdp;
		Object dado = null;
		
		cdp = listaDeProdutos.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = cdp.getCatCodigo(); break;
		case 1: dado = cdp.getCatIdentificador(); break;		
		case 2: dado = cdp.getCatDescricao(); break;
		}
		
		return dado;		
	}

	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "Codigo"; break;
		case 1: nome = "Indentificador"; break;		
		case 2: nome = "Descrição"; break;		
		}		
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		Object obj;
		
		switch (columnIndex) {
		case 0: obj = Integer.class; break;
		case 1: obj = Integer.class; break;
		case 2: obj = String.class; break;			
		default: obj = null; break;
		}		

		return obj.getClass();
	}
}








