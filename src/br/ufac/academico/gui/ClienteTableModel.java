package br.ufac.academico.gui;
import javax.swing.table.*;

import br.ufac.academico.entity.*;
import java.sql.*;
import java.util.*;

public class ClienteTableModel extends AbstractTableModel{

	List<Clientes> clientes;

	public ClienteTableModel(List<Clientes> clientes){

		this.clientes = clientes;
		
	}
	
	@Override
	public int getRowCount() {
		return clientes.size();
	}

	@Override
	public int getColumnCount() {		
		return 15;	
	}
	@Override	
	public Object getValueAt(int rowIndex, int columnIndex) {		

		Clientes c;
		Object dado = null;
		
		c = clientes.get(rowIndex);
		
		switch (columnIndex) {
		case 0: dado = c.getCodigo(); break;
		case 1: dado = c.getMunicipio().getMunCodigo(); break;		
		case 2: dado = c.getNome(); break;
		case 3: dado = c.getDataNascimento(); break;
		case 4: dado = c.getSexo(); break;
		case 5: dado = c.getCpf(); break;
		case 6: dado = c.getRg(); break;
		case 7: dado = c.getCnpj(); break;
		case 8: dado = c.getEndereco(); break;		
		case 9: dado = c.getEmail(); break;
		case 10: dado = c.getDataCadastro(); break;
		case 11: dado = c.getTipo(); break;
		case 12: dado = c.getStatus(); break;
		case 13: dado = c.getFone(); break;
		case 14: dado = c.getNomeContato(); break;
		}
		
		return dado;		
	}

	@Override
	public String getColumnName(int columnIndex) {
		String nome="";
		switch (columnIndex) {
		case 0: nome = "Codigo"; break;
		case 1: nome = "Codigo do Municipio"; break;		
		case 2: nome = "Nome"; break;
		case 3: nome = "Data Nascimento"; break;		
		case 4: nome = "Sexo"; break;
		case 5: nome = "CPF"; break;
		case 6: nome = "RG"; break;
		case 7: nome = "CNPJ"; break;
		case 8: nome = "Endereço"; break;
		case 9: nome = "Email"; break;
		case 10: nome = "Data Cadastro"; break;
		case 11: nome = "Tipo"; break;
		case 12: nome = "Status"; break;
		case 13: nome = "Fone"; break;
		case 14: nome = "Nome Contato"; break;
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
		case 4: obj = char.class; break;
		case 5: obj = String.class; break;
		case 6: obj = String.class; break;		
		case 7: obj = String.class; break;
		case 8: obj = String.class; break;
		case 9: obj = String.class; break;		
		case 10: obj = String.class; break;
		case 11: obj = char.class; break;
		case 12: obj = char.class; break;
		case 13: obj = String.class; break;
		case 14: obj = String.class; break;
		default: obj = null; break;
		}		

		return obj.getClass();
	}
}