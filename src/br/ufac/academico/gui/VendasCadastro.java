package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing
import javax.swing.UIManager.LookAndFeelInfo;

import br.ufac.academico.db.*;
import br.ufac.academico.entity.Municipios;
import br.ufac.academico.entity.Produtos;
import br.ufac.academico.entity.Vendas;
import br.ufac.academico.entity.Atendente;
import br.ufac.academico.entity.Banco;
import br.ufac.academico.entity.CategoriasProdutos;
import br.ufac.academico.entity.Cliente;
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

class VendasCadastro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int INCLUSAO = 0;
	private final int EDICAO = 1;
	private final int EXCLUSAO = 2;

	private int acao;
	private VendasConsulta pai;
	private Conexao cnx;
	
	
	private ClientesLogic cpl;
	private VendasLogic pl;
	private AtendentesLogic fl;
	private BancosLogic bl;

	private JPanel pnlControles, pnlOperacoes, pnlRotulos, pnlCampos;
	private JComboBox<Object> cmbBancos,cmbClientes,cmbAtendentes;
	private JTextField fldCodigo, fldVenValorTotal, 
	fldFormaPagamento, fldObservacoes;
	private JButton btnConfirmar, btnCancelar;

	AcaoConfirmar actConfirmar = new AcaoConfirmar();
	AcaoCancelar actCancelar = new AcaoCancelar();

	static final String imagesPath = new String("images/");	

	VendasCadastro(JFrame framePai, Conexao conexao){ // método construtor
		super(""); // chamando construtor da classe mãe
		setSize(800, 600);				// definindo dimensões da janela		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		
		pai = (VendasConsulta)framePai;
		cnx = conexao;
		cpl = new ClientesLogic(cnx);
		fl = new AtendentesLogic(cnx);
		pl = new VendasLogic(cnx);
		bl = new BancosLogic(cnx);

		pnlRotulos = new JPanel(new GridLayout(7,1,5,5));
		pnlRotulos.add(new JLabel("Codigo"));
		pnlRotulos.add(new JLabel("Cliente"));
		pnlRotulos.add(new JLabel("Atendente"));
		pnlRotulos.add(new JLabel("Banco"));
		pnlRotulos.add(new JLabel("Valor Da Venda"));
		pnlRotulos.add(new JLabel("Forma de Pagamento"));
		pnlRotulos.add(new JLabel("Observa��es"));

	

		fldCodigo = new JTextField();
		fldVenValorTotal = new JTextField();
		fldFormaPagamento = new JTextField();
		fldObservacoes = new JTextField();
		try {
			cmbClientes = new JComboBox<>(cpl.getNomesClientes().toArray());
		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityTableIsEmptyException | EntityNotExistException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Cadastro de Clientes", JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			cmbAtendentes = new JComboBox<>(fl.getNomesAtendentes().toArray());
		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityTableIsEmptyException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Cadastro de Venda", JOptionPane.ERROR_MESSAGE);
		}
		
		try {
			cmbBancos = new JComboBox<>(bl.getBancosNomes().toArray());
		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityTableIsEmptyException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Cadastro de Venda", JOptionPane.ERROR_MESSAGE);
		}
		

		pnlCampos = new JPanel(new GridLayout(7,1,5,5));
		pnlCampos.add(fldCodigo);
		pnlCampos.add(cmbClientes);
		pnlCampos.add(cmbAtendentes);
		pnlCampos.add(cmbBancos);
		pnlCampos.add(fldVenValorTotal);
		pnlCampos.add(fldFormaPagamento);
		pnlCampos.add(fldObservacoes);
		
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

			int venCodigo = 0;
			Cliente cliente = null;
			long cliCodigo;
			Atendente atendente=null;
			long ateCodigo;
			Banco banco = null;
			long banCodigo;
			float venValorTotal;
			String venFormaPagamento;
			String venObservacoes;
			
			try {
			venCodigo = Integer.parseInt(fldCodigo.getText());
			}catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null,"CODIGO INVALIDO!");
				fldCodigo.setText("");
			}
				
			
			
			try {
				banco = bl.getBanco(cmbBancos.getSelectedIndex()+1);
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
				cliente = cpl.getCliente(cmbClientes.getSelectedIndex()+1);
			} catch (DataBaseGenericException e2) {
				e2.printStackTrace();
			} catch (DataBaseNotConnectedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (EntityNotExistException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			try {
				atendente = fl.getAtendente(cmbClientes.getSelectedIndex()+1);
			} catch (DataBaseGenericException e2) {
				e2.printStackTrace();
			} catch (DataBaseNotConnectedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (EntityNotExistException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			cliCodigo = cliente.getCodigo();
			ateCodigo =atendente.getCodigo();
			banCodigo = banco.getCodigo();
			venValorTotal = Float.parseFloat(fldVenValorTotal.getText());
			venFormaPagamento = fldFormaPagamento.getText();
			venObservacoes = fldObservacoes.getText();
			
			

			try {			

				switch (acao) {
				case INCLUSAO:
					pl.addProduto(venCodigo, cliCodigo, ateCodigo, banCodigo, venValorTotal, venFormaPagamento, venObservacoes);
					break;
				case EDICAO:
					pl.updVenda(venCodigo, cliCodigo, ateCodigo, banCodigo, venValorTotal, venFormaPagamento, venObservacoes);
					break;
				case EXCLUSAO:
					pl.delVenda(venCodigo);			
					break;
				}
				limparCampos();
				VendasCadastro.this.setVisible(false);
				pai.setVisible(true);
				pai.buscar();				
			} catch (DataBaseGenericException | 
					DataBaseNotConnectedException | 
					EntityAlreadyExistException| 
					InvalidFieldException /*|*/ | EntityNotExistException 
					/*EntityNotExistException*/ e1) 
			{
				JOptionPane.showMessageDialog(VendasCadastro.this, e1.getMessage(), 
						"Cadastro de Venda", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}

	@SuppressWarnings("serial")
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
			VendasCadastro.this.setVisible(false);
			pai.setVisible(true);
			pai.buscar();
		}

	}

	public void incluir() {

		acao = INCLUSAO;
		setTitle("Inclusao de Venda");
		fldCodigo.setEnabled(true);
		fldVenValorTotal.setEnabled(true);
		fldFormaPagamento.setEnabled(true);
		fldObservacoes.setEnabled(true);
		cmbBancos.setEnabled(true);

		limparCampos();

		pai.setVisible(false);
		setVisible(true);

	}

	public void editar(int matricula) throws EntityTableIsEmptyException {

		acao = EDICAO;
		setTitle("Edicao de Venda");

		fldCodigo.setEnabled(false);
		fldVenValorTotal.setEnabled(true);
		fldFormaPagamento.setEnabled(false);
		fldObservacoes.setEnabled(true);
		cmbBancos.setEnabled(false);
		cmbAtendentes.setEnabled(false);
		cmbClientes.setEnabled(false);
		carregarCampos(matricula);

		pai.setVisible(false);
		setVisible(true);

	}

	//PRATICAMENTE IGUAL AO editar
	public void excluir(int matricula) throws EntityTableIsEmptyException {

		acao = EXCLUSAO;
		setTitle("Exclusao de Venda");

		fldCodigo.setEnabled(false);
		fldVenValorTotal.setEnabled(true);
		fldFormaPagamento.setEnabled(false);
		fldObservacoes.setEnabled(true);
		cmbBancos.setEnabled(false);
		cmbAtendentes.setEnabled(false);
		cmbClientes.setEnabled(false);
		
		carregarCampos(matricula);

		pai.setVisible(false);
		setVisible(true);

	}	

	public void limparCampos() {

		fldCodigo.setText("");
		fldVenValorTotal.setText("");
		fldFormaPagamento.setText("");
		fldObservacoes.setText("");
		cmbBancos.setSelectedIndex(0);
		cmbClientes.setSelectedIndex(0);
		cmbAtendentes.setSelectedIndex(0);
		
	}

	public void carregarCampos(int codigo) throws EntityTableIsEmptyException {

		Vendas v = null;
		
		try {
			v = pl.getVenda(codigo);
			
			fldCodigo.setText(String.valueOf(v.getVenCodigo()));
			fldVenValorTotal.setText(Float.toString(v.getVenValorTotal()));
			fldFormaPagamento.setText(String.valueOf(v.getVenFormaPagamento()));
			fldObservacoes.setText(v.getVenObservacoes());
			cmbClientes.setSelectedItem(v.getCliente().getNome());
			cmbBancos.setSelectedItem(v.getBanco().getNome());
			
			
		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityNotExistException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Cadastro de Centro", JOptionPane.ERROR_MESSAGE);
		}

	}


}//Fim da classe ProfessorCadastro
