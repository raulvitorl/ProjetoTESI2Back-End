package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing

import br.ufac.academico.db.*;
import br.ufac.academico.entity.Municipios;
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

class FornecedorCadastro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int INCLUSAO = 0;
	private final int EDICAO = 1;
	private final int EXCLUSAO = 2;

	private int acao;
	private FornecedoresConsulta pai;
	private Conexao cnx;
	private MunicipiosLogic ml;
	private FornecedoresLogic fl;

	private JPanel pnlControles, pnlOperacoes, pnlRotulos, pnlCampos;
	private JComboBox<Object> cmbMunicipios;
	private JTextField fldForCodigo, fldForRazaoSocial, fldNomeForFantasia, fldNomeForContato, 
	fldForCnpj, fldForEndereco,fldForDataCadastro,fldForFone,fldForEmail,fldForWebsite;
	private JButton btnConfirmar, btnCancelar;

	AcaoConfirmar actConfirmar = new AcaoConfirmar();
	AcaoCancelar actCancelar = new AcaoCancelar();

	static final String imagesPath = new String("images/");	

	FornecedorCadastro(JFrame framePai, Conexao conexao){ // método construtor
		super(""); // chamando construtor da classe mãe
		setSize(800, 600);				// definindo dimensões da janela		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		pai = (FornecedoresConsulta)framePai;
		cnx = conexao;
		ml = new MunicipiosLogic(cnx);
		fl = new FornecedoresLogic(cnx);

		pnlRotulos = new JPanel(new GridLayout(11,1,5,5));
		pnlRotulos.add(new JLabel("Codigo"));
		pnlRotulos.add(new JLabel("Municipio"));
		pnlRotulos.add(new JLabel("Raz�o Social"));
		pnlRotulos.add(new JLabel("Nome Fantasia"));
		pnlRotulos.add(new JLabel("Nome Contato"));
		pnlRotulos.add(new JLabel("CNPJ"));
		pnlRotulos.add(new JLabel("Endere�o"));
		pnlRotulos.add(new JLabel("Data Cadastro"));
		pnlRotulos.add(new JLabel("Fone"));
		pnlRotulos.add(new JLabel("Email"));
		pnlRotulos.add(new JLabel("Site"));
		

		fldForCodigo = new JTextField();
		fldForRazaoSocial = new JTextField();	
		fldNomeForFantasia = new JTextField();
		fldNomeForContato = new JTextField();
		fldForCnpj = new JTextField();
		fldForEndereco = new JTextField();
		fldForDataCadastro = new JTextField();
		fldForFone = new JTextField();
		fldForEmail = new JTextField();
		fldForWebsite = new JTextField();
		try {
			cmbMunicipios = new JComboBox<>(ml.getNomesMunicipios().toArray());
		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityTableIsEmptyException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Cadastro de Municipios", JOptionPane.ERROR_MESSAGE);
		}
		

		pnlCampos = new JPanel(new GridLayout(11,1,5,5));
		pnlCampos.add(fldForCodigo);
		pnlCampos.add(cmbMunicipios);
		pnlCampos.add(fldForRazaoSocial);
		pnlCampos.add(fldNomeForFantasia);
		pnlCampos.add(fldNomeForContato);
		pnlCampos.add(fldForCnpj);
		pnlCampos.add(fldForEndereco);
		pnlCampos.add(fldForDataCadastro);
		pnlCampos.add(fldForFone);
		pnlCampos.add(fldForEmail);
		pnlCampos.add(fldForWebsite);
		

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

			int forCodigo = -1;
			Municipios municipio=null;
			int forMunCodigo;
			String forRazaoSocial;
			String forNomeContato;
			String forNomeFantasia; 
			String forCnpj; 
			String forEndereco; 
			String forDataCadastro;
			String forFone;
			String forEmail;
			String forWebSite;
			
			try {
			forCodigo = Integer.parseInt(fldForCodigo.getText());
			}catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null,"CODIGO INVALIDO!");
				fldForCodigo.setText("");
			}
				
			
			
			try {
				municipio = ml.getMunicipio(cmbMunicipios.getSelectedIndex()+1);
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
			forMunCodigo = municipio.getMunCodigo();
			forRazaoSocial = fldForRazaoSocial.getText();
			forNomeContato = fldNomeForFantasia.getText();
			forNomeFantasia = fldNomeForContato.getText();
			forCnpj = fldForCnpj.getText();
			forEndereco = fldForEndereco.getText();
			forDataCadastro = fldForDataCadastro.getText();
			forFone = fldForFone.getText();
			forEmail = fldForEmail.getText();
			forWebSite = fldForWebsite.getText();
			

			

			try {			

				switch (acao) {
				case INCLUSAO:
					fl.addFornecedor(forCodigo, forMunCodigo, forRazaoSocial, forNomeContato, forNomeFantasia, forCnpj, forEndereco, forDataCadastro, forFone, forEmail, forWebSite);
					break;
				case EDICAO:
					fl.updFornecedores(forCodigo, forMunCodigo, forRazaoSocial, forNomeContato, forNomeFantasia, forCnpj, forEndereco, forDataCadastro, forFone, forEmail, forWebSite);
					break;
				case EXCLUSAO:
					fl.delFornecedores(forCodigo, forMunCodigo, forRazaoSocial, forNomeContato, forNomeFantasia, forCnpj, forEndereco, forDataCadastro, forFone, forEmail, forWebSite);			
					break;
				}
				limparCampos();
				FornecedorCadastro.this.setVisible(false);
				pai.setVisible(true);
				pai.buscar();				
			} catch (DataBaseGenericException | 
					DataBaseNotConnectedException | 
					EntityAlreadyExistException| 
					InvalidFieldException | 
					EntityNotExistException e1) 
			{
				JOptionPane.showMessageDialog(FornecedorCadastro.this, e1.getMessage(), 
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
			FornecedorCadastro.this.setVisible(false);
			pai.setVisible(true);
			pai.buscar();
		}

	}

	public void incluir() {

		acao = INCLUSAO;
		setTitle("Inclusão de Fornecedor");

		fldForCodigo.setEnabled(true);
		fldForRazaoSocial.setEnabled(true);
		fldNomeForFantasia.setEnabled(true);
		fldNomeForContato.setEnabled(true);
		fldForCnpj.setEnabled(true);
		fldForEndereco.setEnabled(true);
		cmbMunicipios.setEnabled(true);

		limparCampos();

		pai.setVisible(false);
		setVisible(true);

	}

	public void editar(int matricula) throws EntityTableIsEmptyException {

		acao = EDICAO;
		setTitle("Edicao de Fornecedorr");

		fldForCodigo.setEnabled(false);
		fldForRazaoSocial.setEnabled(true);
		fldNomeForFantasia.setEnabled(true);
		fldNomeForContato.setEnabled(true);
		fldForCnpj.setEnabled(false);
		fldForEndereco.setEnabled(true);
		cmbMunicipios.setEnabled(true);
		fldForDataCadastro.setEnabled(false);
		fldForFone.setEnabled(true);
		fldForEmail.setEnabled(true);
		fldForWebsite.setEnabled(true);
		
		carregarCampos(matricula);

		pai.setVisible(false);
		setVisible(true);

	}

	//PRATICAMENTE IGUAL AO editar
	public void excluir(int matricula) throws EntityTableIsEmptyException {

		acao = EXCLUSAO;
		setTitle("Exclusao de Professor");

		fldForCodigo.setEnabled(false);
		fldForRazaoSocial.setEnabled(false);
		fldNomeForFantasia.setEnabled(false);
		fldNomeForContato.setEnabled(false);
		fldForCnpj.setEnabled(false);
		fldForEndereco.setEnabled(false);
		cmbMunicipios.setEnabled(false);
		fldForDataCadastro.setEnabled(false);
		fldForFone.setEnabled(false);
		fldForEmail.setEnabled(false);
		fldForWebsite.setEnabled(false);
		

		carregarCampos(matricula);

		pai.setVisible(false);
		setVisible(true);

	}	

	public void limparCampos() {

		fldForCodigo.setText("");
		fldForRazaoSocial.setText("");
		fldNomeForFantasia.setText("");
		fldNomeForContato.setText("");
		fldForCnpj.setText("");
		fldForEndereco.setText("");
		cmbMunicipios.setSelectedIndex(0);

	}

	public void carregarCampos(int codigo) throws EntityTableIsEmptyException {

		Fornecedores f;
		Municipios m;		
		
		try {
			f = fl.getFornecedor(codigo);
			
			fldForCodigo.setText(String.valueOf(f.getForCodigo()));
			fldForRazaoSocial.setText(f.getForRazaoSocial());
			fldNomeForFantasia.setText(f.getForNomeFantasia());
			fldNomeForContato.setText(f.getForNomeContato());
			fldForCnpj.setText(f.getForCnpj());
			fldForEndereco.setText(f.getForEndereco());
			fldForDataCadastro.setText(f.getForDataCadastro());
			fldForFone.setText(f.getForFone());
			fldForEmail.setText(f.getForEmail());
			fldForWebsite.setText(f.getForWebSite());

			for (int i = 0; i < cmbMunicipios.getItemCount(); i++) {
				m =  (Municipios) ml.getMunicipioPorNome(cmbMunicipios.getItemAt(i).toString());
				if (m.getMunCodigo()==f.getMunicipio().getMunCodigo()) {
					cmbMunicipios.setSelectedItem(f.getMunicipio().getMunNome());
				}

			}
			
		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityNotExistException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Cadastro de Centro", JOptionPane.ERROR_MESSAGE);
		}



	}


}//Fim da classe ProfessorCadastro
