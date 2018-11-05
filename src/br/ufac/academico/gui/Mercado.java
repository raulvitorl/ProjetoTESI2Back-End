package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing
import java.awt.BorderLayout;
import java.awt.event.*; 				//importando classes de EVENTOS do AWT

import br.ufac.academico.db.*;
import br.ufac.academico.exception.*;

class Mercado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Conexao cnx = null;
	ImageIcon fundo = new ImageIcon("images/general/background.jpg");
	JLabel lbFundo = new JLabel(fundo);
	
	FornecedoresConsulta fornecedoresConsulta;
	MunicipiosConsulta municipiosConsulta;
	CategoriasDeProdutosConsulta categoriasDeProdutosConsulta;
	ProdutosConsulta produtosConsulta;
	AtendentesConsulta atendentesConsulta;
	BancosConsulta bancosConsulta;
	ClientesConsulta clientesConsulta;
	VendasConsulta vendasConsulta;
	TiposMensagensConsulta tiposMesagensConsulta;
	
	AcaoFornecedor actFornecedor = new AcaoFornecedor();
	AcaoMunicipios actMunicipios = new AcaoMunicipios(); 
	AcaoCategoriasDeProdutos actCategoriasDeProdutos = new AcaoCategoriasDeProdutos();
	AcaoProdutos actProdutos = new AcaoProdutos();
	AcaoAtendentes actAtendentes = new AcaoAtendentes();
	AcaoBancos actBancos = new  AcaoBancos();
	AcaoVendas actVendas = new AcaoVendas();
	AcaoClientes actClientes = new AcaoClientes();
	AcaoTiposMensagens actTiposMensagens = new AcaoTiposMensagens();
	AcaoSair actSair = new AcaoSair();	
	
	

	static final String imagesPath = new String("images/");

	JMenuBar mbOpcoes;
	JMenu mnCadastro,mnSistema,mnEstoque,mnFinanceiro,mnComunicacaoInterna;

	Mercado(Conexao conexao){ 
		super("Sistema de Controle de Estoque");
		setSize(750, 400);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		cnx = conexao;
		this.add(lbFundo);	
		fornecedoresConsulta = new FornecedoresConsulta(this, cnx);
		municipiosConsulta = new MunicipiosConsulta(this, cnx);
		categoriasDeProdutosConsulta = new CategoriasDeProdutosConsulta(this, cnx);
		produtosConsulta = new ProdutosConsulta(this, cnx);
		atendentesConsulta = new AtendentesConsulta(this, cnx);
		bancosConsulta = new BancosConsulta(this, cnx);
		clientesConsulta =  new ClientesConsulta(this, cnx);
		vendasConsulta = new VendasConsulta(this,cnx);
		tiposMesagensConsulta = new TiposMensagensConsulta(this,cnx);
		
		
		mbOpcoes = new JMenuBar();
		
		mnCadastro = new JMenu("Cadastro");
		mnEstoque = new JMenu("Estoque");
		mnFinanceiro = new JMenu("Financeiro");
		mnComunicacaoInterna = new JMenu("Comunicacao Interna");
		mnSistema = new JMenu("Sistema");
		mnCadastro.setMnemonic(KeyEvent.VK_D);
		
		//mnCadastro.add(actCentro);
		//mnCadastro.add(actProfessor);
		mnEstoque.add(actFornecedor);
		mnEstoque.add(actCategoriasDeProdutos);
		mnEstoque.add(actProdutos);
		
		mnFinanceiro.add(actBancos);
		mnFinanceiro.add(actVendas);
		
		mnCadastro.add(actMunicipios);
		mnCadastro.add(actAtendentes);
		mnCadastro.add(actClientes);
		
	
		mnComunicacaoInterna.add(actTiposMensagens);
		
		
		mnSistema.add(actSair);
		
		mbOpcoes.add(mnSistema,BorderLayout.WEST);
		mbOpcoes.add(mnFinanceiro);
		mbOpcoes.add(mnEstoque);
		mbOpcoes.add(mnCadastro);
		mbOpcoes.add(mnComunicacaoInterna);
		setJMenuBar(mbOpcoes);

		
	} //Fim do mÃ©todo construtor
	
	@SuppressWarnings("serial")
	class AcaoFornecedor extends AbstractAction{
		AcaoFornecedor(){
			super("Fornecedor");
			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
			putValue(SHORT_DESCRIPTION, 
					"Gerenciar Fornecedores");

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			fornecedoresConsulta.setVisible(true);
			Mercado.this.setVisible(false);
		}
	}
	
	
	@SuppressWarnings("serial")
	class AcaoVendas extends AbstractAction{
		AcaoVendas(){
			super("Vendas");
			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
			putValue(SHORT_DESCRIPTION, 
					"Gerenciar Vendas");

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			vendasConsulta.setVisible(true);
			Mercado.this.setVisible(false);
		}
	}
	
	@SuppressWarnings("serial")
	class AcaoTiposMensagens extends AbstractAction{
		AcaoTiposMensagens(){
			super("Tipos de Mensagens");
			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
			putValue(SHORT_DESCRIPTION, 
					"Gerenciar Tipos de Mensagens");

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			tiposMesagensConsulta.setVisible(true);
			Mercado.this.setVisible(false);
		}
	}
		@SuppressWarnings("serial")
	class AcaoCategoriasDeProdutos extends AbstractAction{
		AcaoCategoriasDeProdutos(){
			super("Categorias De Produtos");
			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
			putValue(SHORT_DESCRIPTION, 
					"Gerenciar Categorias de Produtos");

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			categoriasDeProdutosConsulta.setVisible(true);
			Mercado.this.setVisible(false);
		}
	}
	
	
	
	@SuppressWarnings("serial")
	class AcaoProdutos extends AbstractAction{
		AcaoProdutos(){
			super("Produtos");
			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
			putValue(SHORT_DESCRIPTION, 
					"Gerenciar Produtos");

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			produtosConsulta.setVisible(true);
			Mercado.this.setVisible(false);
		}
	}
	
	
	
	@SuppressWarnings("serial")
	class AcaoAtendentes extends AbstractAction{
		AcaoAtendentes(){
			super("Atendentes");
			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
			putValue(SHORT_DESCRIPTION, 
					"Gerenciar Atendentes");

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			atendentesConsulta.setVisible(true);
			Mercado.this.setVisible(false);
		}
	}
	
	
	

	@SuppressWarnings("serial")
	class AcaoClientes extends AbstractAction{
		AcaoClientes(){
			super("Clientes");
			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
			putValue(SHORT_DESCRIPTION, 
					"Gerenciar Clientes");

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			clientesConsulta.setVisible(true);
			Mercado.this.setVisible(false);
		}
	}
	
	
	
	@SuppressWarnings("serial")
	class AcaoBancos extends AbstractAction{
		AcaoBancos(){
			super("Bancos");
			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
			putValue(SHORT_DESCRIPTION, 
					"Gerenciar Bancos");

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			bancosConsulta.setVisible(true);
			Mercado.this.setVisible(false);
		}
	}
	
	
	
	@SuppressWarnings("serial")
	class AcaoMunicipios extends AbstractAction{
		AcaoMunicipios(){
			super("Municipios");
			putValue(MNEMONIC_KEY, KeyEvent.VK_C);
			putValue(SHORT_DESCRIPTION, 
					"Gerenciar Municipios");

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			municipiosConsulta.setVisible(true);
			Mercado.this.setVisible(false);
		}
	}
	
	
	
	@SuppressWarnings("serial")
	class AcaoSair extends AbstractAction{

		AcaoSair(){
			super("Sair");
			putValue(MNEMONIC_KEY, KeyEvent.VK_R);
			putValue(SHORT_DESCRIPTION, 
					"Sair da aplicacao!");

		}

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				cnx.desconecte();
			} catch (DataBaseNotConnectedException | DataBaseGenericException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), 
						"Falha ao autenticar", JOptionPane.ERROR_MESSAGE);

			}
			System.exit(0);
		}
	}

}//Fim da classe ProfessorConsulta



