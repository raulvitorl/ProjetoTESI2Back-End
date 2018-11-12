package br.ufac.academico.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import br.ufac.academico.entidades.Atendentes;
import br.ufac.academico.repositorios.AtendentesRepositorio;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AtendentesTela extends JFrame {
	private AtendentesRepositorio arp = new AtendentesRepositorio();
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JButton btnRemover;
	private JButton btnBuscar;
	private JButton btnCadastrar;
	AtendenteCadastro atc;
	private List<Atendentes> listaAtendentes = arp.recuperarTodos(); 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtendentesTela frame = new AtendentesTela();
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
	public AtendentesTela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGerenciamentoDeCadastro = new JLabel("Gerenciamento de Cadastro de Atendentes");
		lblGerenciamentoDeCadastro.setBounds(239, 11, 229, 14);
		contentPane.add(lblGerenciamentoDeCadastro);
		
		table = new JTable();
		table.setBounds(10, 107, 686, 295);
		atc = new AtendenteCadastro();
		atc.setVisible(false);
		contentPane.add(table);
		
		textField = new JTextField();
		textField.setBounds(21, 51, 659, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnRemover = new JButton("Remover");
		btnRemover.setBounds(31, 83, 89, 23);
		contentPane.add(btnRemover);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(579, 83, 89, 23);
		contentPane.add(btnBuscar);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				atc.setVisible(true);
			}
		});
		btnCadastrar.setBounds(303, 83, 89, 23);
		contentPane.add(btnCadastrar);
	}
}
