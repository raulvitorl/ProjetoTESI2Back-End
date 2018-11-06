package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing

import br.ufac.academico.db.*;
import br.ufac.academico.entity.Clientes;
import br.ufac.academico.exception.DataBaseGenericException;
import br.ufac.academico.exception.DataBaseNotConnectedException;
import br.ufac.academico.exception.EntityNotExistException;
import br.ufac.academico.exception.EntityTableIsEmptyException;
import br.ufac.academico.logic.*;

import java.awt.*; 						//importando classes do AWT
import java.awt.event.*; 				//importando classes de EVENTOS do AWT
import java.util.List;						//importando classes do JDBC
import java.util.ArrayList;						//importando classes do JDBC

class ClientesConsulta extends JFrame {

	private Conexao cnx = null;
	Mercado pai;
	ClientesLogic cl;

	ClientesCadastro clientesCadastro;

	JTable tblQuery;
	JPanel pnlSuperior, pnlControles, pnlBotoes, pnlOperacoes, pnlRotulos, pnlChaves,pnlBotaoGroup;
	//JComboBox cmbChaves;
	JTextField fldValor;
	JButton btnBuscar, btnSair, btnIncluir, btnEditar, btnExcluir;

	ButtonGroup gbOpcoes = new ButtonGroup();
	JRadioButton rbCodigo = new JRadioButton("Codigo");
	JRadioButton rbNome = new JRadioButton("Nome");
	JRadioButton rbCpf = new JRadioButton("CPF");
	JRadioButton rbMunicipio = new JRadioButton("Municipio");
	JRadioButton rbEmail = new JRadioButton("Email");
	JRadioButton rbSexo = new JRadioButton("Sexo");
	JRadioButton rbStatus = new JRadioButton("Status");
	
	
	
	AcaoBuscar actBuscar = new AcaoBuscar();
	AcaoIncluir actIncluir = new AcaoIncluir();
	AcaoEditar actEditar = new AcaoEditar();	
	AcaoExcluir actExcluir = new AcaoExcluir();	
	AcaoSair actSair = new AcaoSair();	

	static final String imagesPath = new String("images/");	

	ClientesConsulta(JFrame framePai, Conexao conexao){ // método construtor
		super("Consulta de Clientes"); // chamando construtor da classe mãe
		setSize(800, 400);				// definindo dimensões da janela
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		pai = (Mercado) framePai;		
		cnx = conexao;
		cl = new ClientesLogic(cnx);
		setLocationRelativeTo(null);
		clientesCadastro = new ClientesCadastro(this, cnx);
		tblQuery = new JTable(0,0);
		tblQuery.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblQuery.addMouseListener(new HabilitarEdicaoExclusao());
		setLocationRelativeTo(null);
		pnlBotaoGroup = new JPanel(new GridLayout(2,4,6,6));
		pnlBotaoGroup.add(rbCodigo);
		pnlBotaoGroup.add(rbCpf);
		pnlBotaoGroup.add(rbEmail);
		pnlBotaoGroup.add(rbMunicipio);
		pnlBotaoGroup.add(rbNome);
		pnlBotaoGroup.add(rbSexo);
		pnlBotaoGroup.add(rbStatus);
		
		
		pnlRotulos = new JPanel(new GridLayout(2,2,5,5));
		pnlRotulos.add(new JLabel("Buscar por"));
		pnlRotulos.add(pnlBotaoGroup);
		pnlRotulos.add(new JLabel("Valor"));
		fldValor = new JTextField();
		pnlChaves = new JPanel(new GridLayout(1,1,5,5));
		pnlChaves.add(fldValor);
		pnlRotulos.add(pnlChaves);
		//pnlChaves.add(fldValor);

		pnlControles = new JPanel(new BorderLayout(5,5));
		pnlControles.add(pnlRotulos, BorderLayout.WEST);
		//pnlControles.add(pnlChaves);
		gbOpcoes.add(rbCodigo);
		gbOpcoes.add(rbCpf);
		gbOpcoes.add(rbEmail);
		gbOpcoes.add(rbMunicipio);
		gbOpcoes.add(rbNome);
		gbOpcoes.add(rbSexo);
		gbOpcoes.add(rbStatus);

		btnBuscar = new JButton(actBuscar);
		btnSair = new JButton(actSair);
		btnIncluir = new JButton(actIncluir);
		btnEditar = new JButton(actEditar);
		btnEditar.setEnabled(false);
		btnExcluir = new JButton(actExcluir);
		btnExcluir.setEnabled(false);

		pnlOperacoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlOperacoes.add(btnIncluir);
		pnlOperacoes.add(btnEditar);
		pnlOperacoes.add(btnExcluir);

		pnlBotoes = new JPanel(new GridLayout(2,1));
		pnlBotoes.add(btnBuscar);
		pnlBotoes.add(btnSair);

		buscar();
		pnlSuperior = new JPanel(new BorderLayout());
		pnlSuperior.add(pnlBotoes, BorderLayout.EAST);
		pnlSuperior.add(pnlControles);
		buscar();

		add(pnlSuperior, BorderLayout.NORTH);
		add(new JScrollPane(tblQuery));
		add(pnlOperacoes, BorderLayout.SOUTH);		

	} //Fim do método construtor

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

			clientesCadastro.incluir();

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
				clientesCadastro.editar(codigo);
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
				clientesCadastro.excluir(codigo);
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
					"Fecha consulta de professores!");
			putValue(SMALL_ICON, 
					new ImageIcon(imagesPath+"general/Stop24.gif"));

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			ClientesConsulta.this.setVisible(false);
			pai.setVisible(true);

		}

	}

	public void buscar() {

		List<Clientes> listaDeClientes = new ArrayList<Clientes>();
		int busca;

		try {

			if(fldValor.getText().equals("")) {
				listaDeClientes = cl.getClientes();
			}else{
				
					if(rbCodigo.isSelected()){
						
						try {
							busca = Integer.parseInt(fldValor.getText());
							listaDeClientes.add(cl.getCliente(busca));
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Entrada inv�lida para o filtro selecionado");
						}
					}
					
					if(rbCpf.isSelected()){
						listaDeClientes = cl.getClientesPorCpf(fldValor.getText());
					}
					
					if(rbEmail.isSelected()){
						listaDeClientes = cl.getClientesPorEmail(fldValor.getText());
					}
					if(rbMunicipio.isSelected()){
						listaDeClientes = cl.getClientesPorMunicipio(fldValor.getText());
					}
					
					if(rbNome.isSelected()){
						listaDeClientes.add(cl.getClientePorNome(fldValor.getText()));
					}
					
					if(rbSexo.isSelected()){
						listaDeClientes = cl.getClientesPorSexo(fldValor.getText());	
					}
					
					if(rbStatus.isSelected()){
						listaDeClientes = cl.getClientesPorStatus(fldValor.getText());	
					}
		
				}

			

			tblQuery.setModel(new ClienteTableModel(listaDeClientes));
			btnEditar.setEnabled(false);
			btnExcluir.setEnabled(false);

		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityTableIsEmptyException | 
				EntityNotExistException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Consulta de Clientes", JOptionPane.ERROR_MESSAGE);
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



