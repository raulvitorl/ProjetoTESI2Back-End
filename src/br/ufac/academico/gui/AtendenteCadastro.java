package br.ufac.academico.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ufac.academico.entidades.Atendentes;
import br.ufac.academico.repositorios.AtendentesRepositorio;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AtendenteCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField fldNome;
	private JTextField fldUltimoAcesso;
	private JTextField fldRamal;
	private JTextField fldEmail;
	private JTextField fldPerfil;
	private AtendentesTela pai;
	AtendentesRepositorio atr = new AtendentesRepositorio();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtendenteCadastro frame = new AtendenteCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public AtendenteCadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastroDeAtendente = new JLabel("Cadastro de Atendente");
		lblCadastroDeAtendente.setBounds(138, 11, 145, 14);
		contentPane.add(lblCadastroDeAtendente);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 93, 76, 28);
		contentPane.add(lblNome);
		
		JLabel lblUltimoAcesso = new JLabel("Ultimo Acesso");
		lblUltimoAcesso.setBounds(10, 172, 76, 14);
		contentPane.add(lblUltimoAcesso);
		
		JLabel lblRamal = new JLabel("Ramal");
		lblRamal.setBounds(10, 233, 46, 14);
		contentPane.add(lblRamal);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 295, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setBounds(10, 358, 46, 14);
		contentPane.add(lblPerfil);
		
		fldNome = new JTextField();
		fldNome.setBounds(164, 97, 222, 20);
		contentPane.add(fldNome);
		fldNome.setColumns(10);
		
		fldUltimoAcesso = new JTextField();
		fldUltimoAcesso.setColumns(10);
		fldUltimoAcesso.setBounds(164, 169, 222, 20);
		contentPane.add(fldUltimoAcesso);
		
		fldRamal = new JTextField();
		fldRamal.setColumns(10);
		fldRamal.setBounds(164, 230, 222, 20);
		contentPane.add(fldRamal);
		
		fldEmail = new JTextField();
		fldEmail.setColumns(10);
		fldEmail.setBounds(164, 292, 222, 20);
		contentPane.add(fldEmail);
		
		fldPerfil = new JTextField();
		fldPerfil.setColumns(10);
		fldPerfil.setBounds(164, 355, 222, 20);
		contentPane.add(fldPerfil);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Atendentes at = new Atendentes();
				at.setEmail(fldEmail.getText());
				at.setNome(fldNome.getText());
				at.setRamal(fldRamal.getText());
				at.setUltimoAcesso(fldUltimoAcesso.getText());
				at.setAte_perfil(fldPerfil.getText());
				atr.adicionar(at);				
				JOptionPane.showMessageDialog(null,"Salvo!");
			}
		});
		btnSalvar.setBounds(58, 409, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Operação Cancelada!");
				pai.setVisible(true);
			}
		});
		btnCancelar.setBounds(239, 409, 89, 23);
		contentPane.add(btnCancelar);
	}

}
