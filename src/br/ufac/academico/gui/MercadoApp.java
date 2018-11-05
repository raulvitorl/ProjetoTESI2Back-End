package br.ufac.academico.gui;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import br.ufac.academico.db.Conexao;
import br.ufac.academico.exception.AccessDeniedForUserException;
import br.ufac.academico.exception.DataBaseAlreadyConnectedException;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.EntityNotExistException;

class MercadoApp {

	static final String DB_URL = "jdbc:mysql://localhost/bd2018";

	public static void main(String args[]) throws DataBaseGenericException{
		
		String dbUsuario, dbSenha;
		int qtdTentativas=0;
		boolean conectado=false;
		
		Conexao cnx =  new Conexao();
		
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
		
		do {
			dbUsuario = JOptionPane.showInputDialog("USER:");
			dbSenha = JOptionPane.showInputDialog("PSSWD:");
			try {
				conectado = cnx.conecte(DB_URL, dbUsuario, dbSenha);
			} catch (DataBaseAlreadyConnectedException | 
					AccessDeniedForUserException | 
					DataBaseGenericException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), 
						"Falha na autenticao", JOptionPane.ERROR_MESSAGE);
			}	
			qtdTentativas ++;
		} while (!conectado && qtdTentativas < 3);
		
		if (conectado){
			// Criado o objeto pc do tipo Academico.
			Mercado a = new Mercado(cnx);
			// Exibindo a janela
			a.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null, "Voce excedeu o numero de tentativas!", 
					"Falha na autenticao", JOptionPane.ERROR_MESSAGE);		}
   } 
}


