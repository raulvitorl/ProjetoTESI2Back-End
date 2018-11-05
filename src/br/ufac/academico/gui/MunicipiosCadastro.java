package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing

import br.ufac.academico.db.*;
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

class MunicipiosCadastro extends JFrame {

	private final int INCLUSAO = 0;
	private final int EDICAO = 1;
	private final int EXCLUSAO = 2;

	private int acao, numeroDeMunicipios;
	private String[] idCentros;

	private MunicipiosConsulta pai;
	private Conexao cnx;
	private ResultSet rs;
	private MunicipiosLogic ml;

	private JPanel pnlControles, pnlOperacoes, pnlRotulos, pnlCampos;
	private JComboBox cmbCentro;
	private JTextField fldMunCodigo, fldMunNome,fldUfEstado,fldCep;
	private JButton btnConfirmar, btnCancelar;

	AcaoConfirmar actConfirmar = new AcaoConfirmar();
	AcaoCancelar actCancelar = new AcaoCancelar();

	static final String imagesPath = new String("images/");	

	MunicipiosCadastro(JFrame framePai, Conexao conexao){ // método construtor
		super(""); // chamando construtor da classe mãe
		setSize(800, 600);				// definindo dimensões da janela		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		pai = (MunicipiosConsulta)framePai;
		cnx = conexao;
		ml = new MunicipiosLogic(cnx);

		pnlRotulos = new JPanel(new GridLayout(4,1,5,5));
		pnlRotulos.add(new JLabel("Codigo"));
		pnlRotulos.add(new JLabel("Nome"));
		pnlRotulos.add(new JLabel("UF"));
		pnlRotulos.add(new JLabel("CEP"));

		fldMunCodigo = new JTextField();
		fldMunNome = new JTextField();		
		fldUfEstado = new JTextField();
		fldCep = new JTextField();
		
		
		pnlCampos = new JPanel(new GridLayout(4,1,5,5));
		pnlCampos.add(fldMunCodigo);
		pnlCampos.add(fldMunNome);
		pnlCampos.add(fldUfEstado);
		pnlCampos.add(fldCep);

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

					ml.addMunicipio(Integer.parseInt(fldMunCodigo.getText()),fldMunNome.getText(), fldUfEstado.getText(),fldCep.getText());
					break;
				case EDICAO:
					ml.updMunicipio(Integer.parseInt(fldMunCodigo.getText()),fldMunNome.getText(), fldUfEstado.getText(),fldCep.getText());
					break;
				case EXCLUSAO:
					ml.delMunicipio(Integer.parseInt(fldMunCodigo.getText()),fldMunNome.getText(), fldUfEstado.getText(),fldCep.getText());
					break;
				}
				limparCampos();
				MunicipiosCadastro.this.setVisible(false);
				pai.setVisible(true);
				pai.buscar();				
			} catch (DataBaseGenericException | 
					DataBaseNotConnectedException | 
					EntityAlreadyExistException| 
					InvalidFieldException | 
					EntityNotExistException e1) 
			{
				JOptionPane.showMessageDialog(MunicipiosCadastro.this, e1.getMessage(), 
						"Cadastro de Municipios", JOptionPane.ERROR_MESSAGE);
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
			MunicipiosCadastro.this.setVisible(false);
			pai.setVisible(true);
			pai.buscar();
		}

	}

	public void incluir() {

		acao = INCLUSAO;
		setTitle("Inclusao de Municipio");

		fldMunCodigo.setEnabled(true);
		fldMunNome.setEnabled(true);

		limparCampos();

		pai.setVisible(false);
		setVisible(true);

	}

	public void editar(int codigo) {

		acao = EDICAO;
		setTitle("Edicao de Municipio");

		fldMunCodigo.setEnabled(false);
		fldMunNome.setEnabled(true);

		carregarCampos(codigo);

		pai.setVisible(false);
		setVisible(true);

	}

	//PRATICAMENTE IGUAL AO editar
	public void excluir(int codigo) {

		acao = EXCLUSAO;
		setTitle("Exclusão de Centro");

		fldMunCodigo.setEnabled(false);
		fldMunNome.setEnabled(false);

		carregarCampos(codigo);

		pai.setVisible(false);
		setVisible(true);

	}	


	public void limparCampos() {
		fldMunCodigo.setText("");
		fldMunNome.setText("");
		fldCep.setText("");
		fldUfEstado.setText("");

	}

	public void carregarCampos(int codigo) {

		Municipios m;
		try {
			m = ml.getMunicipio(codigo);
			fldMunCodigo.setText(String.valueOf(m.getMunCodigo()));
			fldMunNome.setText(m.getMunNome());
			fldUfEstado.setText(m.getMunUfEstado());
			fldCep.setText(m.getMunCep());
		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityNotExistException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Cadastro de Centro", JOptionPane.ERROR_MESSAGE);
		}


	}


}//Fim da classe ProfessorCadastro
