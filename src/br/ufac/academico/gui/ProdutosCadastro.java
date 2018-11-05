package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import br.ufac.academico.db.*;
import br.ufac.academico.entity.Municipios;
import br.ufac.academico.entity.Produtos;
import br.ufac.academico.entity.CategoriasProdutos;
import br.ufac.academico.entity.Fornecedores;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;
import br.ufac.academico.exception.EntityAlreadyExistException;
import br.ufac.academico.exception.EntityNotExistException;
import br.ufac.academico.exception.EntityTableIsEmptyException;
import br.ufac.academico.exception.InvalidFieldException;
import br.ufac.academico.logic.*;

import java.awt.*; 						//importando classes do AWT
import java.awt.event.*; 				//importando classes de EVENTOS do AWT
import java.text.ParseException;

class ProdutosCadastro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int INCLUSAO = 0;
	private final int EDICAO = 1;
	private final int EXCLUSAO = 2;

	private int acao;
	private ProdutosConsulta pai;
	private Conexao cnx;
	
	
	private CategoriasDeProdutosLogic cpl;
	private ProdutosLogic pl;
	private FornecedoresLogic fl;

	private JPanel pnlControles, pnlOperacoes, pnlRotulos, pnlCampos;
	private JComboBox<Object> cmbFornecedores,cmbCategorias;
	private JTextField fldProCodigo, fldDescricao, 
	fldQntDisponivel,fldValorUnit,fldFabricante,fldDetalhes;
	
	private JFormattedTextField fldUltimaAquisicao;
	
	private JButton btnConfirmar, btnCancelar;

	AcaoConfirmar actConfirmar = new AcaoConfirmar();
	AcaoCancelar actCancelar = new AcaoCancelar();

	static final String imagesPath = new String("images/");	

	ProdutosCadastro(JFrame framePai, Conexao conexao){ // método construtor
		super(""); // chamando construtor da classe mãe
		setSize(800, 600);				// definindo dimensões da janela		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		
		pai = (ProdutosConsulta)framePai;
		cnx = conexao;
		cpl = new CategoriasDeProdutosLogic(cnx);
		fl = new FornecedoresLogic(cnx);
		pl = new ProdutosLogic(cnx);

		pnlRotulos = new JPanel(new GridLayout(9,1,5,5));
		pnlRotulos.add(new JLabel("Codigo"));
		pnlRotulos.add(new JLabel("Codigo Fornecedor"));
		pnlRotulos.add(new JLabel("Codigo Categoria"));
		pnlRotulos.add(new JLabel("Descri��o"));
		pnlRotulos.add(new JLabel("Quantidade"));
		pnlRotulos.add(new JLabel("Data da Aquisi��o"));
		pnlRotulos.add(new JLabel("Valor Unitario"));
		pnlRotulos.add(new JLabel("Fabricante"));
		pnlRotulos.add(new JLabel("Detalhes"));
	

		fldProCodigo = new JTextField();
		fldDescricao = new JTextField();
		fldQntDisponivel = new JTextField();
		fldValorUnit = new JTextField();
		fldFabricante = new JTextField();
		fldDetalhes = new JTextField();
		
		fldUltimaAquisicao = new JFormattedTextField();
		try {
			fldUltimaAquisicao.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("####-##-##")));
		} catch (ParseException e) {
			
		}
		
		try {
			cmbCategorias = new JComboBox<>(cpl.getCategoriasProdutosDescricao().toArray());
		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityTableIsEmptyException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Cadastro de Municipios", JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			cmbFornecedores = new JComboBox<>(fl.getNomesFornecedores().toArray());
		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityTableIsEmptyException |
				EntityNotExistException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Cadastro de Fornecedores", JOptionPane.ERROR_MESSAGE);
		}
		

		pnlCampos = new JPanel(new GridLayout(9,1,5,5));
		pnlCampos.add(fldProCodigo);
		pnlCampos.add(cmbFornecedores);
		pnlCampos.add(cmbCategorias);
		pnlCampos.add(fldDescricao);
		pnlCampos.add(fldQntDisponivel);
		pnlCampos.add(fldUltimaAquisicao);
		pnlCampos.add(fldValorUnit);
		pnlCampos.add(fldFabricante);
		pnlCampos.add(fldDetalhes);
		
		

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

	@SuppressWarnings("serial")
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

			int proCodigo = -1;
			Fornecedores fornecedor = null;
			int proForCodigo;
			CategoriasProdutos categoria= null;
			int proCatCodigo;
			String proDescricao;
			int proQntDisponivel;
			String proUltimaAquisicao; 
			float proValorUnit; 
			String proFabricante; 
			String proDetalhes;
			
			try {
			proCodigo = Integer.parseInt(fldProCodigo.getText());
			}catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null,"CODIGO INVALIDO!");
				fldProCodigo.setText("");
			}
				
			
			
			try {
				fornecedor = fl.getFornecedor(cmbFornecedores.getSelectedIndex()+1);
			} catch (DataBaseGenericException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (DataBaseNotConnectedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (EntityNotExistException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			try {
				categoria = cpl.getCategoria(cmbCategorias.getSelectedIndex()+100);
			} catch (DataBaseGenericException e2) {
				e2.printStackTrace();
			} catch (DataBaseNotConnectedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (EntityNotExistException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			proForCodigo = fornecedor.getForCodigo();
			proCatCodigo =categoria.getCatCodigo();
			proDescricao = fldDescricao.getText();
			proQntDisponivel = 0;
			proValorUnit = 0;
			try {
				proQntDisponivel = Integer.parseInt(fldQntDisponivel.getText());
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Quantidade Invalida");
				limparCampos();
			}
			
			proUltimaAquisicao = fldUltimaAquisicao.getText();
			
			try {
				proValorUnit = Float.parseFloat(fldValorUnit.getText());
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Valor Invalido");
				limparCampos();
			}
			proFabricante = fldFabricante.getText();
			proDetalhes = fldDetalhes.getText();
			

			

			try {			

				switch (acao) {
				case INCLUSAO:
					pl.addProduto(proCodigo, proForCodigo, proCatCodigo, proDescricao, proQntDisponivel, proUltimaAquisicao, proValorUnit, proFabricante, proDetalhes);
					break;
				case EDICAO:
					pl.updProduto(proCodigo, proForCodigo, proCatCodigo, proDescricao, proQntDisponivel, proUltimaAquisicao, proValorUnit, proFabricante, proDetalhes);
					break;
				case EXCLUSAO:
					pl.delProfuto(proCodigo);			
					break;
				}
				limparCampos();
				ProdutosCadastro.this.setVisible(false);
				pai.setVisible(true);
				pai.buscar();				
			} catch (DataBaseGenericException | 
					DataBaseNotConnectedException | 
					EntityAlreadyExistException| 
					InvalidFieldException /*|*/ | EntityNotExistException 
					/*EntityNotExistException*/ e1) 
			{
				JOptionPane.showMessageDialog(ProdutosCadastro.this, e1.getMessage(), 
						"Cadastro de Centro", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}

	@SuppressWarnings("serial")
	class AcaoCancelar extends AbstractAction{

		AcaoCancelar(){
			super("Cancelar");
			putValue(MNEMONIC_KEY, KeyEvent.VK_L);
			putValue(SHORT_DESCRIPTION, 
					"Cancelar operação!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Stop24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			limparCampos();
			ProdutosCadastro.this.setVisible(false);
			pai.setVisible(true);
			pai.buscar();
		}

	}

	public void incluir() {

		acao = INCLUSAO;
		setTitle("Inclusão de Fornecedor");

		fldProCodigo.setEnabled(true);
		fldDescricao.setEnabled(true);
		fldQntDisponivel.setEnabled(true);
		fldUltimaAquisicao.setEnabled(true);
		cmbFornecedores.setEnabled(true);

		limparCampos();

		pai.setVisible(false);
		setVisible(true);

	}

	public void editar(int matricula) throws EntityTableIsEmptyException {

		acao = EDICAO;
		setTitle("Edição de Professor");

		fldProCodigo.setEnabled(false);
		fldDescricao.setEnabled(true);
		fldQntDisponivel.setEnabled(true);
		fldUltimaAquisicao.setEnabled(true);
		cmbFornecedores.setEnabled(true);

		carregarCampos(matricula);

		pai.setVisible(false);
		setVisible(true);

	}

	//PRATICAMENTE IGUAL AO editar
	public void excluir(int matricula) throws EntityTableIsEmptyException {

		acao = EXCLUSAO;
		setTitle("Exclusao de Professor");

		fldProCodigo.setEnabled(false);
		fldDescricao.setEnabled(false);
		fldQntDisponivel.setEnabled(false);
		fldUltimaAquisicao.setEnabled(false);
		cmbFornecedores.setEnabled(false);

		carregarCampos(matricula);

		pai.setVisible(false);
		setVisible(true);

	}	

	public void limparCampos() {

		fldProCodigo.setText("");
		fldDescricao.setText("");
		fldQntDisponivel.setText("");
		fldUltimaAquisicao.setText("");
		cmbFornecedores.setSelectedIndex(0);
		cmbCategorias.setSelectedIndex(0);
		fldDetalhes.setText("");
		fldProCodigo.setText("");
		fldValorUnit.setText("");
		fldFabricante.setText("");
		
	}

	public void carregarCampos(int codigo) throws EntityTableIsEmptyException {

		Produtos p;
		CategoriasProdutos cdp;
		Fornecedores f;
		
		try {
			p = pl.getProduto(codigo);
			
			fldProCodigo.setText(String.valueOf(p.getProCodigo()));
			fldDescricao.setText(p.getProDescricao());
			fldQntDisponivel.setText(String.valueOf(p.getProQntDisponivel()));
			fldUltimaAquisicao.setText(p.getProUltimaAquisicao());
			fldDetalhes.setText(p.getProDetalhes());
			fldFabricante.setText(p.getProFabricante());
			fldDescricao.setText(p.getProDescricao());
			fldValorUnit.setText(String.valueOf(p.getProValorUnitario()));

			cmbCategorias.setSelectedItem(p.getCategoria().getCatDescricao());
			

			cmbFornecedores.setSelectedItem(p.getFornecedor().getForNomeFantasia());
			
			
		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityNotExistException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Cadastro de Centro", JOptionPane.ERROR_MESSAGE);
		}



	}


}//Fim da classe ProfessorCadastro
