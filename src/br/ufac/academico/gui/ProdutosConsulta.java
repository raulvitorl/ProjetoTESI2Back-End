package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing

import br.ufac.academico.db.*;
import br.ufac.academico.entity.Produtos;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;
import br.ufac.academico.exception.EntityNotExistException;
import br.ufac.academico.exception.EntityTableIsEmptyException;
import br.ufac.academico.logic.*;

import java.awt.*; 						//importando classes do AWT
import java.awt.event.*; 				//importando classes de EVENTOS do AWT
import java.util.List;						//importando classes do JDBC
import java.util.ArrayList;						//importando classes do JDBC

class ProdutosConsulta extends JFrame {

	private Conexao cnx = null;
	Mercado pai;
	ProdutosLogic pl;

	ProdutosCadastro produtosCadastro;

	JTable tblQuery;
	JPanel pnlSuperior, pnlControles, pnlBotoes, pnlOperacoes, pnlRotulos, pnlChaves,pnlBotaoGroup,pnlCampos;
	//JComboBox cmbChaves;
	ButtonGroup gbOpcoes = new ButtonGroup();
	JRadioButton rbCodigo = new JRadioButton("Codigo");
	JRadioButton rbDescricao = new JRadioButton("DescriÁ„o");
	JRadioButton rbIntervaloPreco = new JRadioButton("PreÁo");
	JRadioButton rbIntervaloQuantidade = new JRadioButton("Quantidade");
	
	
	JTextField fldValor,fldValor2;
	JButton btnBuscar, btnSair, btnIncluir, btnEditar, btnExcluir;

	AcaoBuscar actBuscar = new AcaoBuscar();
	AcaoIncluir actIncluir = new AcaoIncluir();
	AcaoEditar actEditar = new AcaoEditar();	
	AcaoExcluir actExcluir = new AcaoExcluir();	
	AcaoSair actSair = new AcaoSair();	

	static final String imagesPath = new String("images/");	

	ProdutosConsulta(JFrame framePai, Conexao conexao){ // m√©todo construtor
		super("Consulta de Produtos"); // chamando construtor da classe m√£e
		setSize(950, 450);				// definindo dimens√µes da janela
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		pai = (Mercado) framePai;		
		cnx = conexao;
		pl = new ProdutosLogic(cnx);
		produtosCadastro = new ProdutosCadastro(this, cnx);
		setLocationRelativeTo(null);
		
		

		tblQuery = new JTable(0,0);
		tblQuery.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblQuery.addMouseListener(new HabilitarEdicaoExclusao());
		setLocationRelativeTo(null);
		
		
		fldValor = new JTextField();
		fldValor2 = new JTextField();
		
		
		pnlBotaoGroup = new JPanel(new GridLayout(1,4,6,6));
		pnlBotaoGroup.add(rbCodigo);
		pnlBotaoGroup.add(rbDescricao);
		pnlBotaoGroup.add(rbIntervaloPreco);
		pnlBotaoGroup.add(rbIntervaloQuantidade);
		
		
		pnlRotulos = new JPanel(new GridLayout(2,2,6,6));
		pnlRotulos.add(new JLabel("Buscar por"));
		pnlRotulos.add(pnlBotaoGroup);
		
		pnlCampos = new JPanel(new GridLayout(1,2,6,6));
		pnlCampos.add(fldValor);
		pnlCampos.add(fldValor2);
		
		pnlRotulos.add(new JLabel("Valores"));
		pnlRotulos.add(pnlCampos);
		
		gbOpcoes.add(rbCodigo);
		gbOpcoes.add(rbDescricao);
		gbOpcoes.add(rbIntervaloPreco);
		gbOpcoes.add(rbIntervaloQuantidade);

		pnlControles = new JPanel(new BorderLayout(6,6));
		pnlControles.add(pnlRotulos/*, BorderLayout.WEST*/);
		//pnlControles.add(pnlChaves);

		btnBuscar = new JButton(actBuscar);
		btnSair = new JButton(actSair);
		btnIncluir = new JButton(actIncluir);
		btnEditar = new JButton(actEditar);
		btnEditar.setEnabled(false);
		btnExcluir = new JButton(actExcluir);
		btnExcluir.setEnabled(false);

		pnlOperacoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 6));
		pnlOperacoes.add(btnIncluir);
		pnlOperacoes.add(btnEditar);
		pnlOperacoes.add(btnExcluir);

		pnlBotoes = new JPanel(new GridLayout(2,1));
		pnlBotoes.add(btnBuscar);
		pnlBotoes.add(btnSair);

		pnlSuperior = new JPanel(new BorderLayout());
		pnlSuperior.add(pnlBotoes, BorderLayout.EAST);
		pnlSuperior.add(pnlControles);

		buscar();
		add(pnlSuperior, BorderLayout.NORTH);
		add(new JScrollPane(tblQuery));
		add(pnlOperacoes, BorderLayout.SOUTH);		

	} //Fim do m√©todo construtor

	class AcaoBuscar extends AbstractAction{

		AcaoBuscar(){
			super("Buscar");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
			putValue(SHORT_DESCRIPTION, 
					"Buscar registros de Fornecedores!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Search24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			buscar();

		}

	}

	class AcaoIncluir extends AbstractAction{

		AcaoIncluir(){
			super("Incluir");
			putValue(MNEMONIC_KEY, KeyEvent.VK_I);
			putValue(SHORT_DESCRIPTION, 
					"Incluir registro de Centro!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/New24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			produtosCadastro.incluir();

		}

	}

	class AcaoEditar extends AbstractAction{

		AcaoEditar(){
			super("Editar");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, 
					"Editar registro de Centro!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Edit24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			int codigo;
			codigo = (int) tblQuery.getValueAt(tblQuery.getSelectedRow(), 0);
			try {
				produtosCadastro.editar(codigo);
			} catch (EntityTableIsEmptyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	class AcaoExcluir extends AbstractAction{

		AcaoExcluir(){
			super("Excluir");
			putValue(MNEMONIC_KEY, KeyEvent.VK_X);
			putValue(SHORT_DESCRIPTION, 
					"Excluir registro de Centro!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Delete24.gif"));

		}

		// PATRICAMENTE IGUAL AO DA AcaoEditar
		@Override
		public void actionPerformed(ActionEvent e) {

			int codigo;
			codigo = (int) tblQuery.getValueAt(tblQuery.getSelectedRow(), 0);
			try {
				produtosCadastro.excluir(codigo);
			} catch (EntityTableIsEmptyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}	

	class AcaoSair extends AbstractAction{

		AcaoSair(){
			super("Sair");
			putValue(MNEMONIC_KEY, KeyEvent.VK_R);
			putValue(SHORT_DESCRIPTION, 
					"Fecha consulta de produtos!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Stop24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			ProdutosConsulta.this.setVisible(false);
			pai.setVisible(true);

		}

	}

	public void buscar() {

		List<Produtos> listaDeProdutos = new ArrayList<Produtos>();
		int busca;

		try {

			if(fldValor.getText().equals("")) {
				listaDeProdutos = pl.getProdutos();
			}else{
				if(rbDescricao.isSelected()){
					listaDeProdutos = pl.getProdutosPorDescricao(fldValor.getText());
				}else{
					if(rbIntervaloPreco.isSelected()){
						
						try {
							listaDeProdutos = pl.getProdutosIntervaloPreco(fldValor.getText(), fldValor2.getText());
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Entrada inv·lida para o filtro selecionado");
						}
		
					}else{
						if(rbIntervaloQuantidade.isSelected()){
							
							try {
								listaDeProdutos = pl.getProdutosIntervaloQuantidade(fldValor.getText(), fldValor2.getText());
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "Entrada inv·lida para o filtro selecionado");
							}
						}else{
							try {
								listaDeProdutos.add(pl.getProduto(Integer.parseInt(fldValor.getText())));
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(null, "Entrada inv·lida para o filtro selecionado");
							}
							
						}
					}
				}

			}

			tblQuery.setModel(new ProdutosTableModel(listaDeProdutos));
			btnEditar.setEnabled(false);
			btnExcluir.setEnabled(false);

		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityTableIsEmptyException | 
				EntityNotExistException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Consulta de produtos", JOptionPane.ERROR_MESSAGE);
		}

	}

	class HabilitarEdicaoExclusao extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			if (tblQuery.getSelectedRow() >= 0) {
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
			}else {
				btnEditar.setEnabled(false);
				btnExcluir.setEnabled(false);
			}

		}

	}	

}//Fim da classe ProfessorConsulta



