package br.gov.cesarschool.poo.bonusvendas.telas;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.text.ParseException;

public class TelaManutencaoVendedor {

    private JFrame frame;
    private JTextField txtCPF;
    private JTextField txtNomeCompleto;
    private JTextField txtDataNascimento;
    private JTextField txtRenda;
    private JTextField txtLogradouro;
    private JTextField txtNumero;
    private JTextField txtComplemento;
    private JTextField txtCEP;
    private JTextField txtCidade;
    private JComboBox<String> comboEstado;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaManutencaoVendedor window = new TelaManutencaoVendedor();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaManutencaoVendedor() {
        initialize();
    }

  private void initialize() {
    frame = new JFrame();
    frame.setBounds(100, 100, 600, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    try {
      JLabel lblCPF = new JLabel("CPF:");
      lblCPF.setBounds(50, 30, 150, 25);
      frame.getContentPane().add(lblCPF);

      MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
      JFormattedTextField txtCPFFormatted = new JFormattedTextField(cpfMask);
      txtCPFFormatted.setBounds(210, 30, 250, 25);
      frame.getContentPane().add(txtCPFFormatted);

      JLabel lblDataNascimento = new JLabel("Data de nascimento:");
      lblDataNascimento.setBounds(50, 70, 150, 25);
      frame.getContentPane().add(lblDataNascimento);

      MaskFormatter dateMask = new MaskFormatter("##/##/####");
      JFormattedTextField txtDataNascimentoFormatted = new JFormattedTextField(dateMask);
      txtDataNascimentoFormatted.setBounds(210, 70, 250, 25);
      frame.getContentPane().add(txtDataNascimentoFormatted);

      JLabel lblCEP = new JLabel("CEP:");
      lblCEP.setBounds(50, 110, 150, 25);
      frame.getContentPane().add(lblCEP);

      MaskFormatter cepMask = new MaskFormatter("#####-###");
      JFormattedTextField txtCEPFormatted = new JFormattedTextField(cepMask);
      txtCEPFormatted.setBounds(210, 110, 250, 25);
      frame.getContentPane().add(txtCEPFormatted);

      JLabel lblNomeCompleto = new JLabel("Nome completo:");
      lblNomeCompleto.setBounds(50, 90, 150, 25);
      frame.getContentPane().add(lblNomeCompleto);

      txtNomeCompleto = new JTextField();
      txtNomeCompleto.setBounds(210, 90, 250, 25);
      frame.getContentPane().add(txtNomeCompleto);

      JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
      rdbtnMasculino.setBounds(210, 130, 100, 25);
      frame.getContentPane().add(rdbtnMasculino);

      JRadioButton rdbtnFeminino = new JRadioButton("Feminino");
      rdbtnFeminino.setBounds(320, 130, 100, 25);
      frame.getContentPane().add(rdbtnFeminino);

      // Agrupar botões de rádio para que somente um possa ser selecionado
      ButtonGroup grupoSexo = new ButtonGroup();
      grupoSexo.add(rdbtnMasculino);
      grupoSexo.add(rdbtnFeminino);

      // ... (Continue com outros campos de acordo com a descrição fornecida)

      JLabel lblEstado = new JLabel("Estado:");
      lblEstado.setBounds(50, 390, 150, 25);
      frame.getContentPane().add(lblEstado);

      comboEstado = new JComboBox<>(new String[]{
              "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA",
              "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN",
              "RO", "RS", "SC", "SP", "SE", "TO"
      });
      comboEstado.setBounds(210, 390, 60, 25);
      frame.getContentPane().add(comboEstado);
      
      // JLabel lblDataNascimento = new JLabel("Data de nascimento:");
      // lblDataNascimento.setBounds(50, 170, 150, 25);
      // frame.getContentPane().add(lblDataNascimento);

      // txtDataNascimento = new JTextField();
      // txtDataNascimento.setBounds(210, 170, 250, 25);
      // frame.getContentPane().add(txtDataNascimento);

      JLabel lblRenda = new JLabel("Renda:");
      lblRenda.setBounds(50, 210, 150, 25);
      frame.getContentPane().add(lblRenda);

      txtRenda = new JTextField();
      txtRenda.setBounds(210, 210, 250, 25);
      frame.getContentPane().add(txtRenda);

      JLabel lblLogradouro = new JLabel("Logradouro:");
      lblLogradouro.setBounds(50, 250, 150, 25);
      frame.getContentPane().add(lblLogradouro);

      txtLogradouro = new JTextField();
      txtLogradouro.setBounds(210, 250, 250, 25);
      frame.getContentPane().add(txtLogradouro);

      JLabel lblNumero = new JLabel("Número:");
      lblNumero.setBounds(50, 290, 150, 25);
      frame.getContentPane().add(lblNumero);

      txtNumero = new JTextField();
      txtNumero.setBounds(210, 290, 250, 25);
      frame.getContentPane().add(txtNumero);

      JLabel lblComplemento = new JLabel("Complemento:");
      lblComplemento.setBounds(50, 330, 150, 25);
      frame.getContentPane().add(lblComplemento);

      txtComplemento = new JTextField();
      txtComplemento.setBounds(210, 330, 250, 25);
      frame.getContentPane().add(txtComplemento);

      // JLabel lblCEP = new JLabel("CEP:");
      // lblCEP.setBounds(50, 370, 150, 25);
      // frame.getContentPane().add(lblCEP);

      // txtCEP = new JTextField();
      // txtCEP.setBounds(210, 370, 250, 25);
      // frame.getContentPane().add(txtCEP);

      JLabel lblCidade = new JLabel("Cidade:");
      lblCidade.setBounds(50, 410, 150, 25);
      frame.getContentPane().add(lblCidade);

      txtCidade = new JTextField();
      txtCidade.setBounds(210, 410, 250, 25);
      frame.getContentPane().add(txtCidade);

      JButton btnSalvar = new JButton("Salvar");
      btnSalvar.setBounds(210, 450, 100, 30);
      btnSalvar.addActionListener(e -> salvarDados());
      frame.getContentPane().add(btnSalvar);

      JButton btnLimpar = new JButton("Limpar");
      btnLimpar.setBounds(320, 450, 100, 30);
      btnLimpar.addActionListener(e -> limparCampos());
      frame.getContentPane().add(btnLimpar);
      
    } catch (ParseException e) {
        e.printStackTrace();
    }
  }

  private void salvarDados() {
    // Aqui você pode incluir a lógica para salvar os dados inseridos, como, por exemplo, 
    // validação dos dados e salvamento em banco de dados.
    JOptionPane.showMessageDialog(frame, "Dados salvos com sucesso!");
  }
  
  private void limparCampos() {
      txtCPF.setText("");
      txtNomeCompleto.setText("");
      txtDataNascimento.setText("");
      txtRenda.setText("");
      txtLogradouro.setText("");
      txtNumero.setText("");
      txtComplemento.setText("");
      txtCEP.setText("");
      txtCidade.setText("");
      comboEstado.setSelectedIndex(0);
  }
}
