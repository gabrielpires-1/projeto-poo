package br.gov.cesarschool.poo.bonusvendas.telas;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class TelaExemploEntidade {

	protected Shell shell;
	private EntidadeMediator mediator = new EntidadeMediator(); 
	private Text txtCodigo;
	private Button btnNovo;
	private Button btnBuscar;
	private Label lblNome;
	private Text txtNome;
	private Label lblRenda;
	private Text txtRenda;
	private Button btnIncluirAlterar;
	private Button btnCancelar;
	private Button btnLimpar;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TelaExemploEntidade window = new TelaExemploEntidade();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(607, 369);
		shell.setText("SWT Application");
		
		Label lblCdigo = new Label(shell, SWT.NONE);
		lblCdigo.setBounds(36, 46, 70, 20);
		lblCdigo.setText("C\u00F3digo");
		
		txtCodigo = new Text(shell, SWT.BORDER);
		txtCodigo.setBounds(129, 46, 113, 26);
		
		btnNovo = new Button(shell, SWT.NONE);

		btnNovo.setBounds(264, 42, 90, 30);
		btnNovo.setText("Novo");
		
		btnBuscar = new Button(shell, SWT.NONE);

		btnBuscar.setBounds(360, 42, 90, 30);
		btnBuscar.setText("Buscar");
		
		lblNome = new Label(shell, SWT.NONE);
		lblNome.setBounds(36, 109, 70, 20);
		lblNome.setText("Nome");
		
		txtNome = new Text(shell, SWT.BORDER);
		txtNome.setEnabled(false);
		txtNome.setBounds(129, 109, 225, 26);
		
		lblRenda = new Label(shell, SWT.NONE);
		lblRenda.setBounds(36, 167, 70, 20);
		lblRenda.setText("Renda");
		
		txtRenda = new Text(shell, SWT.BORDER);
		txtRenda.setEnabled(false);
		txtRenda.setBounds(129, 164, 113, 26);
		
		btnIncluirAlterar = new Button(shell, SWT.NONE);

		btnIncluirAlterar.setEnabled(false);
		btnIncluirAlterar.setBounds(131, 258, 90, 30);
		btnIncluirAlterar.setText("Incluir");
		
		btnCancelar = new Button(shell, SWT.NONE);

		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(239, 258, 90, 30);
		btnCancelar.setText("Cancelar");
		
		btnLimpar = new Button(shell, SWT.NONE);

		btnLimpar.setBounds(347, 258, 90, 30);
		btnLimpar.setText("Limpar");
		btnNovo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Entidade ent = mediator.buscar(txtCodigo.getText());
				if (ent != null) {
					JOptionPane.showMessageDialog(null, 
					"Entidade j� existente!");
				} else {
					btnIncluirAlterar.setEnabled(true);
					btnCancelar.setEnabled(true);
					txtNome.setEnabled(true);
					txtRenda.setEnabled(true);
					btnNovo.setEnabled(false);
					btnBuscar.setEnabled(false);
					txtCodigo.setEnabled(false);
				}
			}
		});
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Entidade ent = mediator.buscar(txtCodigo.getText());
				if (ent == null) {
					JOptionPane.showMessageDialog(null, 
					"Entidade n�o existente!");
				} else {
					txtNome.setText(ent.getNome());
					txtRenda.setText(ent.getRenda() + "");
					btnIncluirAlterar.setText("Alterar");
					btnIncluirAlterar.setEnabled(true);
					btnCancelar.setEnabled(true);
					txtNome.setEnabled(true);
					txtRenda.setEnabled(true);
					btnNovo.setEnabled(false);
					btnBuscar.setEnabled(false);
					txtCodigo.setEnabled(false);
				}
			}
		});
		btnIncluirAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Entidade ent = new Entidade(txtCodigo.getText(), txtNome.getText(),
						Double.parseDouble(txtRenda.getText()));
				String msg = null;
				if (btnIncluirAlterar.getText().equals("Incluir")) {
					msg = mediator.incluir(ent);
				} else {
					msg = mediator.alterar(ent);
				}
				if (msg != null) {
					JOptionPane.showMessageDialog(null, 
					msg);					
				} else {
					btnIncluirAlterar.setEnabled(false);
					btnCancelar.setEnabled(false);
					txtNome.setEnabled(false);
					txtRenda.setEnabled(false);
					btnNovo.setEnabled(true);
					btnBuscar.setEnabled(true);
					txtCodigo.setEnabled(true);
					txtCodigo.setText("");
					txtRenda.setText("");
					txtNome.setText("");
					btnIncluirAlterar.setText("Incluir");
				}
			}
		});		
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				btnIncluirAlterar.setEnabled(false);
				btnCancelar.setEnabled(false);
				txtNome.setEnabled(false);
				txtRenda.setEnabled(false);
				btnNovo.setEnabled(true);
				btnBuscar.setEnabled(true);
				txtCodigo.setEnabled(true);
				txtCodigo.setText("");
				txtRenda.setText("");
				txtNome.setText("");
				btnIncluirAlterar.setText("Incluir");				
			}
		});
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if (txtCodigo.isEnabled()) {
					txtCodigo.setText("");
				}
				txtRenda.setText("");
				txtNome.setText("");				
			}
		});
	}

}
