package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing

import br.ufac.academico.db.*;
import br.ufac.academico.entity.CategoriasProdutos;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;
import br.ufac.academico.exception.EntityAlreadyExistException;
import br.ufac.academico.exception.EntityNotExistException;
import br.ufac.academico.exception.InvalidFieldException;
import br.ufac.academico.logic.*;

import java.awt.*; 						//importando classes do AWT
import java.awt.event.*; 				//importando classes de EVENTOS do AWT
import java.sql.*;						//importando classes do JDBC

class CategoriasDeProdutosCadastro extends JFrame {

	private final int INCLUSAO = 0;
	private final int EDICAO = 1;
	private final int EXCLUSAO = 2;

	private int acao;
	private CategoriasDeProdutosConsulta pai;
	private Conexao cnx;
	private ResultSet rs;
	private CategoriasDeProdutosLogic cpl;

	private JPanel pnlControles, pnlOperacoes, pnlRotulos, pnlCampos;
	private JComboBox cmbCentro;
	private JTextField fldCatCodigo, fldCatIdentificador,fldDescricao;
	private JButton btnConfirmar, btnCancelar;

	AcaoConfirmar actConfirmar = new AcaoConfirmar();
	AcaoCancelar actCancelar = new AcaoCancelar();

	static final String imagesPath = new String("images/");	

	CategoriasDeProdutosCadastro(JFrame framePai, Conexao conexao){ // m√©todo construtor
		super(""); // chamando construtor da classe m√£e
		setSize(800, 600);				// definindo dimens√µes da janela		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		pai = (CategoriasDeProdutosConsulta)framePai;
		cnx = conexao;
		cpl = new CategoriasDeProdutosLogic(cnx);

		pnlRotulos = new JPanel(new GridLayout(3,1,5,5));
		pnlRotulos.add(new JLabel("Codigo"));
		pnlRotulos.add(new JLabel("Identificador"));
		pnlRotulos.add(new JLabel("DescriÁ„o"));

		fldCatCodigo = new JTextField();
		fldCatIdentificador = new JTextField();		
		fldDescricao = new JTextField();
		
		
		pnlCampos = new JPanel(new GridLayout(3,1,5,5));
		pnlCampos.add(fldCatCodigo);
		pnlCampos.add(fldCatIdentificador);
		pnlCampos.add(fldDescricao);

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

	} //Fim do m√©todo construtor

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
					
					int catIdentificador=0;
					
					try {
						Integer.parseInt(fldCatIdentificador.getText());
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "Valor invalido para o campo IDENTIFICADOR");
						limparCampos();
						break;
					}
					
					
					

					cpl.addCategoria(Integer.parseInt(fldCatCodigo.getText()),catIdentificador, fldDescricao.getText());
					break;
				case EDICAO:
					cpl.updCategoria(Integer.parseInt(fldCatCodigo.getText()),Integer.parseInt(fldCatIdentificador.getText()), fldDescricao.getText());
					break;
				case EXCLUSAO:
					cpl.delCategoria(Integer.parseInt(fldCatCodigo.getText()),Integer.parseInt(fldCatIdentificador.getText()), fldDescricao.getText());
					break;
				}
				limparCampos();
				CategoriasDeProdutosCadastro.this.setVisible(false);
				pai.setVisible(true);
				pai.buscar();				
			} catch (DataBaseGenericException | 
					DataBaseNotConnectedException | 
					EntityAlreadyExistException| 
					InvalidFieldException | 
					EntityNotExistException e1) 
			{
				JOptionPane.showMessageDialog(CategoriasDeProdutosCadastro.this, e1.getMessage(), 
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
			CategoriasDeProdutosCadastro.this.setVisible(false);
			pai.setVisible(true);
			pai.buscar();
		}

	}

	public void incluir() {

		acao = INCLUSAO;
		setTitle("Inclusao de Categoria");

		fldCatCodigo.setEnabled(true);
		fldCatIdentificador.setEnabled(true);

		limparCampos();

		pai.setVisible(false);
		setVisible(true);

	}

	public void editar(int codigo) {

		acao = EDICAO;
		setTitle("Edicao de Categoria");

		fldCatCodigo.setEnabled(false);
		fldCatIdentificador.setEnabled(true);

		carregarCampos(codigo);

		pai.setVisible(false);
		setVisible(true);

	}

	//PRATICAMENTE IGUAL AO editar
	public void excluir(int codigo) {

		acao = EXCLUSAO;
		setTitle("Exclus„o de Categoria");

		fldCatCodigo.setEnabled(false);
		fldCatIdentificador.setEnabled(false);

		carregarCampos(codigo);

		pai.setVisible(false);
		setVisible(true);

	}	


	public void limparCampos() {
		fldCatCodigo.setText("");
		fldCatIdentificador.setText("");
		fldDescricao.setText("");

	}

	public void carregarCampos(int codigo) {

		CategoriasProdutos cdp;
		try {
			cdp = cpl.getCategoria(codigo);
			fldCatCodigo.setText(String.valueOf(cdp.getCatCodigo()));
			fldCatIdentificador.setText(String.valueOf(cdp.getCatIdentificador()));
			fldDescricao.setText(cdp.getCatDescricao());
		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityNotExistException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Cadastro de Categorias de Produtos", JOptionPane.ERROR_MESSAGE);
		}


	}


}//Fim da classe ProfessorCadastro
