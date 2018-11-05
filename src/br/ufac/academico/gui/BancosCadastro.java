package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing

import br.ufac.academico.db.*;
import br.ufac.academico.entity.Banco;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;
import br.ufac.academico.exception.EntityAlreadyExistException;
import br.ufac.academico.exception.EntityNotExistException;
import br.ufac.academico.exception.InvalidFieldException;
import br.ufac.academico.logic.*;

import java.awt.*; 						//importando classes do AWT
import java.awt.event.*; 				//importando classes de EVENTOS do AWT
import java.sql.*;						//importando classes do JDBC

class BancosCadastro extends JFrame {

	private final int INCLUSAO = 0;
	private final int EDICAO = 1;
	private final int EXCLUSAO = 2;

	private int acao;
	private BancosConsulta pai;
	private Conexao cnx;
	private ResultSet rs;
	private BancosLogic bl;

	private JPanel pnlControles, pnlOperacoes, pnlRotulos, pnlCampos;
	private JComboBox cmbCentro;
	private JTextField fldBanCodigo, fldBanNome;
	private JButton btnConfirmar, btnCancelar;

	AcaoConfirmar actConfirmar = new AcaoConfirmar();
	AcaoCancelar actCancelar = new AcaoCancelar();

	static final String imagesPath = new String("images/");	

	BancosCadastro(JFrame framePai, Conexao conexao){ // método construtor
		super(""); // chamando construtor da classe mãe
		setSize(800, 600);				// definindo dimensões da janela		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		pai = (BancosConsulta)framePai;
		cnx = conexao;
		bl = new BancosLogic(cnx);

		pnlRotulos = new JPanel(new GridLayout(2,1,5,5));
		pnlRotulos.add(new JLabel("Codigo"));
		pnlRotulos.add(new JLabel("Nome"));

		fldBanCodigo = new JTextField();
		fldBanNome = new JTextField();		
		
		
		pnlCampos = new JPanel(new GridLayout(3,1,5,5));
		pnlCampos.add(fldBanCodigo);
		pnlCampos.add(fldBanNome);

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

	} //Fim do método construtor

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
					bl.addBanco(Integer.parseInt(fldBanCodigo.getText()),fldBanNome.getText());
					break;
				case EDICAO:
					bl.updBanco(Integer.parseInt(fldBanCodigo.getText()),fldBanNome.getText());
					break;
				case EXCLUSAO:
					bl.delBanco(Integer.parseInt(fldBanCodigo.getText()),fldBanNome.getText());
					break;
				}
				limparCampos();
				BancosCadastro.this.setVisible(false);
				pai.setVisible(true);
				pai.buscar();				
			} catch (DataBaseGenericException | 
					DataBaseNotConnectedException | 
					EntityAlreadyExistException| 
					InvalidFieldException | 
					EntityNotExistException e1) 
			{
				JOptionPane.showMessageDialog(BancosCadastro.this, e1.getMessage(), 
						"Cadastro de Bancos", JOptionPane.ERROR_MESSAGE);
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
			BancosCadastro.this.setVisible(false);
			pai.setVisible(true);
			pai.buscar();
		}

	}

	public void incluir() {

		acao = INCLUSAO;
		setTitle("Inclusao de Banco");

		fldBanCodigo.setEnabled(true);
		fldBanNome.setEnabled(true);

		limparCampos();

		pai.setVisible(false);
		setVisible(true);

	}

	public void editar(int codigo) {

		acao = EDICAO;
		setTitle("Edicao de Banco");

		fldBanCodigo.setEnabled(false);
		fldBanNome.setEnabled(true);

		carregarCampos(codigo);

		pai.setVisible(false);
		setVisible(true);

	}

	//PRATICAMENTE IGUAL AO editar
	public void excluir(int codigo) {

		acao = EXCLUSAO;
		setTitle("Exclus�o de Categoria");

		fldBanCodigo.setEnabled(false);
		fldBanNome.setEnabled(false);

		carregarCampos(codigo);

		pai.setVisible(false);
		setVisible(true);

	}	


	public void limparCampos() {
		fldBanCodigo.setText("");
		fldBanNome.setText("");

	}

	public void carregarCampos(int codigo) {

		Banco b;
		try {
			b = bl.getBanco(codigo);
			fldBanCodigo.setText(String.valueOf(b.getCodigo()));
			fldBanNome.setText(String.valueOf(b.getNome()));
		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityNotExistException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Cadastro de Categorias de Produtos", JOptionPane.ERROR_MESSAGE);
		}


	}


}//Fim da classe ProfessorCadastro
