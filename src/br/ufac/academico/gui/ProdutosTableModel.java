package br.ufac.academico.gui;
import javax.swing.table.*;

import br.ufac.academico.entity.*;
import java.util.*;

@SuppressWarnings("serial")
public class ProdutosTableModel extends AbstractTableModel{

	List<Produtos> listaDeProdutos;

	public ProdutosTableModel(List<Produtos> produtos){
		this.listaDeProdutos = produtos;
	}
	
	@Override
	public int getRowCount() {
		return listaDeProdutos.size();
	}

	@Override
	public int getColumnCount() {		
		return 9;	
	}
	@Override	
	public Object getValueAt(int rowIndex, int columnIndex) {		

		Produtos p;
		Object dado = null;
		
		p = listaDeProdutos.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = p.getProCodigo(); break;
		case 1: dado = p.getFornecedor().getForCodigo(); break;		
		case 2: dado = p.getCategoria().getCatCodigo(); break;
		case 3: dado = p.getProDescricao(); break;
		case 4 : dado = p.getProQntDisponivel(); break;
		case 5 : dado = p.getProUltimaAquisicao(); break;
		case 6 : dado = p.getProValorUnitario(); break;
		case 7 : dado = p.getProFabricante(); break;
		case 8 : dado = p.getProDetalhes(); break;
		}
		
		return dado;		
	}

	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "Codigo"; break;
		case 1: nome = "Codigo do Fornecedor"; break;		
		case 2: nome = "Codigo da Categoria"; break;
		case 3: nome = "Descricao"; break;
		case 4: nome = "Quantidade Disponivel"; break;
		case 5: nome = "Ultima Aquisicao"; break;		
		case 6: nome = "Valor Unitario"; break;
		case 7: nome = "Fabricante"; break;
		case 8: nome = "Detalhes"; break;
		
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
		case 3: obj = String.class; break;	
		case 4: obj = Integer.class; break;
		case 5: obj = String.class; break;
		case 6: obj = Float.class; break;
		case 7: obj = String.class; break;
		case 8: obj = String.class; break;
		default: obj = null; break;
		}		

		return obj.getClass();
	}
}








