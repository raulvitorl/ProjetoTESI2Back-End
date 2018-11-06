package br.ufac.academico.gui;

import javax.swing.*; 					//importando classes do Swing
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import br.ufac.academico.db.*;
import br.ufac.academico.entity.Municipios;
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
import java.text.ParseException;

class ClientesCadastro extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int INCLUSAO = 0;
	private final int EDICAO = 1;
	private final int EXCLUSAO = 2;

	private int acao;
	private ClientesConsulta pai;
	private Conexao cnx;
	private MunicipiosLogic ml;
	private ClientesLogic cl;

	private JPanel pnlControles, pnlOperacoes, pnlRotulos, pnlCampos,pnlSexo,pnlTipo,pnlStatus;
	ButtonGroup gbSexo= new ButtonGroup();
	JRadioButton rbMasculino = new JRadioButton("Masculino");
	JRadioButton rbFeminino = new JRadioButton("Feminino");
	ButtonGroup gbTipo= new ButtonGroup();
	JRadioButton rbFisico = new JRadioButton("Fisico");
	JRadioButton rbJuridico = new JRadioButton("Juridico");
	ButtonGroup gbStatus= new ButtonGroup();
	JRadioButton rbAtivo = new JRadioButton("Ativo");
	JRadioButton rbInativo = new JRadioButton("Inativo");
	private JComboBox<Object> cmbMunicipios;
	private JTextField fldCliCodigo, fldNome, fldDataNascimento,
	fldCnpj, fldRg,fldEndereco,fldEmail,fldDataCadastro,fldNomeContato;
	
	JFormattedTextField fldCpf,fldFone;
	
	private JButton btnConfirmar, btnCancelar;

	AcaoConfirmar actConfirmar = new AcaoConfirmar();
	AcaoCancelar actCancelar = new AcaoCancelar();

	static final String imagesPath = new String("images/");	

	ClientesCadastro(JFrame framePai, Conexao conexao){ // método construtor
		super(""); // chamando construtor da classe mãe
		setSize(800, 600);				// definindo dimensões da janela		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		pai = (ClientesConsulta)framePai;
		cnx = conexao;
		ml = new MunicipiosLogic(cnx);
		cl = new ClientesLogic(cnx);

		pnlRotulos = new JPanel(new GridLayout(15,1,5,5));
		pnlRotulos.add(new JLabel("Codigo"));
		pnlRotulos.add(new JLabel("Municipio"));
		pnlRotulos.add(new JLabel("Nome"));
		pnlRotulos.add(new JLabel("Data Nascimento"));
		pnlRotulos.add(new JLabel("Sexo"));
		pnlRotulos.add(new JLabel("CPF"));
		pnlRotulos.add(new JLabel("RG"));
		pnlRotulos.add(new JLabel("CNPJ"));
		pnlRotulos.add(new JLabel("Endere�o"));
		pnlRotulos.add(new JLabel("Email"));
		pnlRotulos.add(new JLabel("Data Cadastro"));
		pnlRotulos.add(new JLabel("Tipo"));
		pnlRotulos.add(new JLabel("Status"));
		pnlRotulos.add(new JLabel("Fone"));
		pnlRotulos.add(new JLabel("Nome Contato"));
		
		pnlSexo = new JPanel(new GridLayout(1,2,5,5));
		pnlSexo.add(rbFeminino);
		pnlSexo.add(rbMasculino);
		gbSexo.add(rbFeminino);
		gbSexo.add(rbMasculino);
		
		pnlTipo = new JPanel(new GridLayout(1,2,5,5));
		pnlTipo.add(rbFisico);
		pnlTipo.add(rbJuridico);
		gbTipo.add(rbFisico);
		gbTipo.add(rbJuridico);
		
		pnlStatus = new JPanel(new GridLayout(1,2,5,5));
		pnlStatus.add(rbAtivo);
		pnlStatus.add(rbInativo);
		gbStatus.add(rbAtivo);
		gbStatus.add(rbInativo);
		
		
	
		fldCliCodigo = new JTextField();
		fldNome = new JTextField();	
		fldDataNascimento = new JTextField();
		
		
		fldCpf = new JFormattedTextField();
		try {
			fldCpf.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
		} catch (ParseException e) {
			
		}
		
		fldFone = new JFormattedTextField();
		try {
			fldFone.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("(##)#####-####")));
		} catch (ParseException e) {
			
		}
		
		
		
		fldRg = new JTextField();
		fldEndereco = new JTextField();
		fldEmail = new JTextField();
		fldDataCadastro = new JTextField();
		fldDataCadastro = new JTextField();
		fldCnpj = new JTextField();
		fldNomeContato =  new JTextField();
		try {
			cmbMunicipios = new JComboBox<>(ml.getNomesMunicipios().toArray());
		} catch (DataBaseGenericException | 
				DataBaseNotConnectedException | 
				EntityTableIsEmptyException e) 
		{
			JOptionPane.showMessageDialog(this, e.getMessage(), 
					"Cadastro de Municipios", JOptionPane.ERROR_MESSAGE);
		}
		

		pnlCampos = new JPanel(new GridLayout(15,1,5,5));
		pnlCampos.add(fldCliCodigo);
		pnlCampos.add(cmbMunicipios);
		pnlCampos.add(fldNome);
		pnlCampos.add(fldDataNascimento);
		pnlCampos.add(pnlSexo);
		pnlCampos.add(fldCpf);
		pnlCampos.add(fldRg);
		pnlCampos.add(fldCnpj);
		pnlCampos.add(fldEndereco);
		pnlCampos.add(fldEmail);
		pnlCampos.add(fldDataCadastro);
		pnlCampos.add(pnlTipo);
		pnlCampos.add(pnlStatus);
		pnlCampos.add(fldFone);
		pnlCampos.add(fldNomeContato);

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

			int codigo = -1;
			Municipios municipio = null;
			long cliMunCodigo;
			String nome;
			String dataNascimento;
			char sexo;
			String cpf;
			String rg;
			String cnpj;
			String endereco;
			String email;
			String dataCadastro;
			char tipo='-';
			char status = '-';
			String fone;
			String nomeContato;
			
			try {
			codigo = Integer.parseInt(fldCliCodigo.getText());
			}catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null,"CODIGO INVALIDO!");
				fldCliCodigo.setText("");
			}
			
			System.out.println(fldCliCodigo.getText());
						
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
			cliMunCodigo = municipio.getMunCodigo();
			nome = fldNome.getText();
			dataNascimento = fldDataNascimento.getText();
			if(rbFeminino.isSelected()){
				sexo='F';
			}else {
				if(rbMasculino.isSelected()){
					sexo = 'M';
				}else {
					sexo='I';
				}
				
			}
			cpf = fldCpf.getText();
			rg = fldRg.getText();
			cnpj = fldCnpj.getText();
			endereco = fldEndereco.getText();
			email= fldEmail.getText();
			dataCadastro = fldDataCadastro.getText();
			if(rbAtivo.isSelected()){
				status='A';
			}else {
				if(rbInativo.isSelected()){
					status = 'I';
				}
				
			}
			if(rbFisico.isSelected()){
				tipo='A';
			}else {
				if(rbJuridico.isSelected()){
					tipo = 'B';
				}
				
			}
			fone = fldFone.getText();
			nomeContato = fldNomeContato.getText();
			

			try {			

				switch (acao) {
				case INCLUSAO:
					cl.addCliente(codigo, cliMunCodigo, nome, dataNascimento, sexo, cpf, rg, cnpj, endereco, email, dataCadastro, tipo, status, fone, nomeContato);
					break;
				case EDICAO:
					cl.updCliente(codigo, cliMunCodigo, nome, dataNascimento, sexo, cpf, rg, cnpj, endereco, email, dataCadastro, tipo, status, fone, nomeContato);
					break;
				case EXCLUSAO:
					cl.delCliente(codigo, cliMunCodigo, nome, dataNascimento, sexo, cpf, rg, cnpj, endereco, email, dataCadastro, tipo, status, fone, nomeContato);
					break;
				}
				limparCampos();
				ClientesCadastro.this.setVisible(false);
				pai.setVisible(true);
				pai.buscar();				
			} catch (DataBaseGenericException | 
					DataBaseNotConnectedException | 
					EntityAlreadyExistException| 
					InvalidFieldException | 
					EntityNotExistException e1) 
			{
				JOptionPane.showMessageDialog(ClientesCadastro.this, e1.getMessage(), 
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
			ClientesCadastro.this.setVisible(false);
			pai.setVisible(true);
			pai.buscar();
		}

	}

	public void incluir() {

		acao = INCLUSAO;
		setTitle("Inclusão de Fornecedor");

		fldCliCodigo.setEnabled(true);
		fldNome.setEnabled(true);
		fldDataNascimento.setEnabled(true);
		fldCpf.setEnabled(true);
		fldRg.setEnabled(true);
		cmbMunicipios.setEnabled(true);
		fldCnpj.setEditable(true);
		fldDataCadastro.setEnabled(true);
		fldEmail.setEnabled(true);
		fldEndereco.setEnabled(true);
		limparCampos();

		pai.setVisible(false);
		setVisible(true);

	}

	public void editar(int matricula) throws EntityTableIsEmptyException {

		acao = EDICAO;
		setTitle("Edicao de Fornecedorr");

		fldCliCodigo.setEnabled(false);
		fldNome.setEnabled(true);
		fldDataNascimento.setEnabled(true);
		fldCpf.setEnabled(false);
		fldRg.setEnabled(true);
		cmbMunicipios.setEnabled(true);
		fldEndereco.setEnabled(false);
		fldEmail.setEnabled(true);
		fldDataCadastro.setEnabled(true);
		
		carregarCampos(matricula);

		pai.setVisible(false);
		setVisible(true);

	}

	//PRATICAMENTE IGUAL AO editar
	public void excluir(int matricula) throws EntityTableIsEmptyException {

		acao = EXCLUSAO;
		setTitle("Exclusao de Professor");

		fldCliCodigo.setEnabled(false);
		fldNome.setEnabled(false);
		fldDataNascimento.setEnabled(false);
		fldCpf.setEnabled(false);
		fldRg.setEnabled(false);
		cmbMunicipios.setEnabled(false);
		fldEndereco.setEnabled(false);
		fldEmail.setEnabled(false);
		fldDataCadastro.setEnabled(false);

		

		carregarCampos(matricula);

		pai.setVisible(false);
		setVisible(true);

	}	

	public void limparCampos() {

		fldCliCodigo.setText("");
		fldNome.setText("");
		fldDataNascimento.setText("");
		fldEndereco.setText("");
		fldDataCadastro.setText("");
		fldEmail.setText("");
		
		rbFeminino.setSelected(false);
		rbMasculino.setSelected(false);
		
		rbAtivo.setSelected(false);
		rbInativo.setSelected(false);
		
		rbJuridico.setSelected(false);
		rbFisico.setSelected(false);
		
		fldCpf.setText("");
		fldRg.setText("");
		cmbMunicipios.setSelectedIndex(0);

	}

	public void carregarCampos(int codigo) throws EntityTableIsEmptyException {

		Cliente c;
		Municipios m;		
		
		try {
			c = cl.getCliente(codigo);
			
			fldCliCodigo.setText(String.valueOf(c.getCodigo()));
			fldNome.setText(c.getNome());
			fldDataNascimento.setText(c.getDataNascimento());
			
			if(c.getSexo()=='F'){
				rbFeminino.setSelected(true);
			}else{
				rbMasculino.setSelected(true);
			}
			
			
			fldCpf.setText(c.getCpf());
			fldRg.setText(c.getRg());
			fldEndereco.setText(c.getEndereco());
			fldEmail.setText(c.getEmail());
			fldDataCadastro.setText(c.getDataCadastro());
			fldFone.setText(c.getFone());
			fldNomeContato.setText(c.getNomeContato());
			
			if(c.getTipo()=='A'){
				rbFisico.setSelected(true);
			}else{
				rbJuridico.setSelected(true);
			}
			
			if(c.getStatus()=='A'){
				rbAtivo.setSelected(true);
			}else{
				rbInativo.setSelected(true);
			}
			

			for (int i = 0; i < cmbMunicipios.getItemCount(); i++) {
				m =  (Municipios) ml.getMunicipioPorNome(cmbMunicipios.getItemAt(i).toString());
				if (m.getMunCodigo()==c.getMunicipio().getMunCodigo()) {
					cmbMunicipios.setSelectedItem(c.getMunicipio().getMunNome());
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
