package br.ufac.academico.gui;
import javax.swing.table.*;

import br.ufac.academico.entity.*;
import java.util.*;

public class FornecedoresTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Fornecedores> fornecedores;
	public FornecedoresTableModel(List<Fornecedores> fornecedores){
		this.fornecedores = fornecedores;		
	}
	
	@Override
	public int getRowCount() {
		return fornecedores.size();
	}

	@Override
	public int getColumnCount() {		
		return 11;	
	}
	@Override	
	public Object getValueAt(int rowIndex, int columnIndex) {		

		Fornecedores f;
		Object dado = null;
		
		f = fornecedores.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = f.getForCodigo(); break;
		case 1: dado = f.getMunicipio().getMunCodigo(); break;		
		case 2: dado = f.getForRazaoSocial(); break;
		case 3: dado = f.getForNomeFantasia(); break;
		case 4: dado = f.getForNomeContato(); break;
		case 5: dado = f.getForCnpj(); break;
		case 6: dado = f.getForEndereco(); break;
		case 7: dado = f.getForDataCadastro(); break;
		case 8: dado = f.getForFone(); break;
		case 9: dado = f.getForEmail(); break;
		case 10: dado = f.getForWebSite(); break;
		}
		
		return dado;		
	}

	@Override
	public String getColumnName(int columnIndex) {

		String nome="";
		
		switch (columnIndex) {
		case 0: nome = "Codigo"; break;
		case 1: nome = "Codigo Municipio"; break;		
		case 2: nome = "Razão Social"; break;
		case 3: nome = "Nome Fantasia"; break;		
		case 4: nome = "Nome Contato"; break;
		case 5: nome = "CNPJ"; break;
		case 6: nome = "Endereco"; break;
		case 7: nome = "Data Cadastro"; break;
		case 8: nome = "Fone"; break;
		case 9: nome = "Email"; break;
		case 10: nome = "Website"; break;
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
		case 3: obj = String.class; break;
		case 4: obj = String.class; break;
		case 5: obj = String.class; break;
		case 6: obj = String.class; break;
		case 7: obj = String.class; break;
		case 8: obj = String.class; break;
		case 9: obj = String.class; break;
		case 10: obj = String.class; break;
		default: obj = null; break;
		}		

		return obj.getClass();
	}
}







