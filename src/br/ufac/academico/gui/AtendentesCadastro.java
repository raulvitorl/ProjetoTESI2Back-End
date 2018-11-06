package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import br.ufac.academico.db.*;
import br.ufac.academico.entity.Atendentes;
import br.ufac.academico.entity.Municipios;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;
import br.ufac.academico.exception.EntityAlreadyExistException;
import br.ufac.academico.exception.EntityNotExistException;
import br.ufac.academico.exception.InvalidFieldException;
import br.ufac.academico.logic.*;

import java.awt.*; 						//importando classes do AWT
import java.awt.event.*; 				//importando classes de EVENTOS do AWT
import java.sql.*;						//importando classes do JDBC
import java.text.ParseException;

class AtendentesCadastro extends JFrame {

	private final int INCLUSAO = 0;
	private final int EDICAO = 1;
	private final int EXCLUSAO = 2;

	private int acao;
	private AtendentesConsulta pai;
	private Conexao cnx;
	private AtendentesLogic al;

	private JPanel pnlControles, pnlOperacoes, pnlRotulos, pnlCampos;
	private JTextField fldCodigo, fldNome,fldRamal,fldEmail,fldStatus;
	private JButton btnConfirmar, btnCancelar;

	private JFormattedTextField fldUltimoAcesso;
	
	
	AcaoConfirmar actConfirmar = new AcaoConfirmar();
	AcaoCancelar actCancelar = new AcaoCancelar();

	static final String imagesPath = new String("images/");	

	AtendentesCadastro(JFrame framePai, Conexao conexao){ 
		super(""); 
		setSize(800, 600);		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		pai = (AtendentesConsulta)framePai;
		cnx = conexao;
		al = new AtendentesLogic(cnx);

		pnlRotulos = new JPanel(new GridLayout(6,1,5,5));
		pnlRotulos.add(new JLabel("Codigo"));
		pnlRotulos.add(new JLabel("Nome"));
		pnlRotulos.add(new JLabel("Ultimo Acesso"));
		pnlRotulos.add(new JLabel("Ramal"));
		pnlRotulos.add(new JLabel("Email"));
		pnlRotulos.add(new JLabel("Status"));
		
		fldCodigo = new JTextField();
		fldNome = new JTextField();		
		
		fldUltimoAcesso = new JFormattedTextField();
		try {
			fldUltimoAcesso.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("####-##-##")));
		} catch (ParseException e) {
			
		}
		
		fldRamal = new JTextField();
		fldEmail = new JTextField();
		fldStatus = new JTextField();
		
		
		pnlCampos = new JPanel(new GridLayout(6,1,5,5));
		pnlCampos.add(fldCodigo);
		pnlCampos.add(fldNome);
		pnlCampos.add(fldUltimoAcesso);
		pnlCampos.add(fldRamal);
		pnlCampos.add(fldEmail);
		pnlCampos.add(fldStatus);
		
		pnlControles = new JPanel(new BorderLayout(5,5));
		pnlControles.add(pnlRotulos, BorderLayout.WEST);
		pnlControles.add(pnlCampos);

		btnConfirmar = new JButton(actConfirmar);
		btnCancelar = new JButton(actCancelar);

		pnlOperacoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlOperacoes.add(btnConfirmar);
		pnlOperacoes.add(btnCancelar);

		add(pnlControles);
		add(pnlOperacoes, BorderLayout.SOUTH);		

		pack();

	} 

	class AcaoConfirmar extends AbstractAction{

		AcaoConfirmar(){
			super("Confirmar");
			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
			putValue(SHORT_DESCRIPTION, 
					"Confirmar operacao!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Save24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				switch (acao) {
				case INCLUSAO:

					al.addMensagem(Integer.parseInt(fldCodigo.getText()), fldNome.getText(), fldUltimoAcesso.getText(), fldRamal.getText(), fldEmail.getText(), fldStatus.getText().charAt(0));
					break;
				case EDICAO:
					al.updAtendente(Integer.parseInt(fldCodigo.getText()), fldNome.getText(), fldUltimoAcesso.getText(), fldRamal.getText(), fldEmail.getText(), fldStatus.getText().charAt(0));
					break;
				case EXCLUSAO:
					al.delMunicipio(Integer.parseInt(fldCodigo.getText()), fldNome.getText(), fldUltimoAcesso.getText(), fldRamal.getText(), fldEmail.getText(), fldStatus.getText().charAt(0));
					break;
				}
				limparCampos();
				AtendentesCadastro.this.setVisible(false);
				pai.setVisible(true);
				pai.buscar();				
			} catch (DataBaseGenericException | 
					DataBaseNotConnectedException | 
					EntityAlreadyExistException| 
					InvalidFieldException | 
					EntityNotExistException e1) 
			{
				JOptionPane.showMessageDialog(AtendentesCadastro.this, e1.getMessage(), 
						"Cadastro de Atendente", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class AcaoCancelar extends AbstractAction{

		AcaoCancelar(){
			super("Cancelar");
			putValue(MNEMONIC_KEY, KeyEvent.VK_L);
			putValue(SHORT_DESCRIPTION, 
					"Cancelar operacao!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Stop24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			limparCampos();
			AtendentesCadastro.this.setVisible(false);
			pai.setVisible(true);
			pai.buscar();
		}

	}

	public void incluir() {

		acao = INCLUSAO;
		setTitle("Inclusao de Atendente");

		fldCodigo.setEnabled(true);
		fldNome.setEnabled(true);

		limparCampos();

		pai.setVisible(false);
		setVisible(true);

	}

	public void editar(int codigo) {

		acao = EDICAO;
		setTitle("Edicao de Atendente");

		fldCodigo.setEnabled(false);
		fldNome.setEnabled(true);

		carregarCampos(codigo);

		pai.setVisible(false);
		setVisible(true);

	}

	//PRATICAMENTE IGUAL AO editar
	public void excluir(int codigo) {

		acao = EXCLUSAO;
		setTitle("Exclusao de Atendente");

		fldCodigo.setEnabled(false);
		fldNome.setEnabled(false);

		carregarCampos(codigo);

		pai.setVisible(false);
		setVisible(true);

	}	


	public void limparCampos() {
		fldCodigo.setText("");
		fldNome.setText("");
		fldRamal.setText("");
		fldUltimoAcesso.setText("");
		fldEmail.setText("");
		fldStatus.setText("");

	}

	public void carregarCampos(int codigo) {

		Atendentes a;
		try {
			a = al.getAtendente(codigo);
			fldCodigo.setText(String.valueOf(a.getCodigo()));
			fldNome.setText(a.getNome());
			fldUltimoAcesso.setText(a.getUltimoAcesso());
			
			if(a.getRamal().equals(" ")) {
				fldRamal.setText("");
			}else{
				fldRamal.setText(a.getRamal());
			}
			
			fldEmail.setText(a.getEmail());
			fldStatus.setText(String.valueOf(a.getStatus()));
		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityNotExistException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Cadastro de Atendente", JOptionPane.ERROR_MESSAGE);
		}


	}


}
