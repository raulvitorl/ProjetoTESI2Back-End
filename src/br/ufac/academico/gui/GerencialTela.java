package br.ufac.academico.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class GerencialTela extends JFrame {
	
	AtendentesTela at;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerencialTela frame = new GerencialTela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public GerencialTela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 452);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnProdutos = new JMenu("Produtos");
		menuBar.add(mnProdutos);
		
		JMenuItem mntmCategoriasDeProdutos = new JMenuItem("Categorias de Produtos");
		mnProdutos.add(mntmCategoriasDeProdutos);
		
		JSeparator separator_1 = new JSeparator();
		mnProdutos.add(separator_1);
		
		JMenuItem mntmFornecedores = new JMenuItem("Fornecedores");
		mnProdutos.add(mntmFornecedores);
		
		JSeparator separator_2 = new JSeparator();
		mnProdutos.add(separator_2);
		
		JMenuItem mntmProdutos = new JMenuItem("Produtos");
		mnProdutos.add(mntmProdutos);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem mntmPessoaFsica = new JMenuItem("Pessoa F\u00EDsica");
		mnClientes.add(mntmPessoaFsica);
		
		JSeparator separator_3 = new JSeparator();
		mnClientes.add(separator_3);
		
		JMenuItem mntmPessoaJurdica = new JMenuItem("Pessoa Jur\u00EDdica");
		mnClientes.add(mntmPessoaJurdica);
		
		JMenu mnFinanceiro = new JMenu("Financeiro");
		menuBar.add(mnFinanceiro);
		
		JMenuItem mntmBancos = new JMenuItem("Bancos");
		mntmBancos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mnFinanceiro.add(mntmBancos);
		
		JSeparator separator = new JSeparator();
		mnFinanceiro.add(separator);
		
		JMenuItem mntmVendas = new JMenuItem("Vendas");
		mnFinanceiro.add(mntmVendas);
		
		JMenu mnAtendentes = new JMenu("Atendentes");
		menuBar.add(mnAtendentes);
		mnAtendentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				at = new AtendentesTela();
				at.setVisible(true);
			}
		});
		
		JMenuItem mntmCadastro = new JMenuItem("Cadastro");
		mnAtendentes.add(mntmCadastro);
		
		JSeparator separator_4 = new JSeparator();
		mnAtendentes.add(separator_4);
		
		JMenuItem mntmComunicao = new JMenuItem("Comunica\u00E7\u00E3o");
		mnAtendentes.add(mntmComunicao);
		
		JMenuItem mntmMunicipios = new JMenuItem("Municipios");
		menuBar.add(mntmMunicipios);
		
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
