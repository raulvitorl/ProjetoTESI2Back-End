package br.ufac.academico.gui;
import javax.swing.table.*;

import br.ufac.academico.entity.*;
import java.util.*;

@SuppressWarnings("serial")
public class VendasTableModel extends AbstractTableModel{

	List<Vendas> listaDeVendas;

	public VendasTableModel(List<Vendas> Vendas){
		this.listaDeVendas = Vendas;
	}
	
	@Override
	public int getRowCount() {
		return listaDeVendas.size();
	}

	@Override
	public int getColumnCount() {		
		return 7;	
	}
	@Override	
	public Object getValueAt(int rowIndex, int columnIndex) {		

		Vendas v;
		Object dado = null;
		
		v = listaDeVendas.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = v.getVenCodigo(); break;
		case 1: dado = v.getCliente().getCodigo(); break;		
		case 2: dado = v.getAtendente().getCodigo(); break;
		case 3: dado = v.getBanco().getCodigo(); break;
		case 4 : dado = v.getVenValorTotal(); break;
		case 5 : dado = v.getVenFormaPagamento(); break;
		case 6 : dado = v.getVenObservacoes(); break;
		}
		
		return dado;		
	}

	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "Codigo"; break;
		case 1: nome = "Cliente"; break;		
		case 2: nome = "Atendente"; break;
		case 3: nome = "Banco"; break;
		case 4: nome = "Valor Total"; break;
		case 5: nome = "Forma de Pagamento"; break;		
		case 6: nome = "Observações"; break;		
		}		
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {

		Object obj;
		
		switch (columnIndex) {
		case 0: obj = Integer.class; break;
		case 1: obj = Integer.class; break;
		case 2: obj = Integer.class; break;	
		case 3: obj = Integer.class; break;	
		case 4: obj = Float.class; break;
		case 5: obj = String.class; break;
		case 6: obj = String.class; break;
		default: obj = null; break;
		}		

		return obj.getClass();
	}
}








