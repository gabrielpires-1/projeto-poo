package br.gov.cesarschool.poo.bonusvendas.telas;

import java.awt.EventQueue;
import javax.swing.text.MaskFormatter;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Endereco;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Sexo;
import br.gov.cesarschool.poo.bonusvendas.negocio.ResultadoInclusaoVendedor;
import br.gov.cesarschool.poo.bonusvendas.negocio.VendedorMediator;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;

public class TelaManutencaoVendedor {
    

    private JFrame frame;
    private JTextField textFieldNome;
    private JRadioButton radioButtonMasculino;
    private JRadioButton radioButtonFeminino;
    private JTextField textFieldRenda;
    private JTextField textFieldLogradouro;
    private JTextField textFieldNumero;
    private JTextField textFieldComplemento;
    private JTextField textFieldCidade;
    private JComboBox<String> comboEstado;
    private JFormattedTextField cpfFormattedField;  // Declare aqui
    private JFormattedTextField dataNascimentoFormattedField;  // Declare aqui
    private JFormattedTextField cepFormattedField;  // Declare aqui
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_3;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
    private JLabel lblNewLabel_6;
    private JLabel lblNewLabel_7;
    private JLabel lblNewLabel_8;
    private JLabel lblNewLabel_9;
    private JLabel lblNewLabel_10;
    private MaskFormatter createFormatter(String format) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(format);
            formatter.setPlaceholderCharacter('0');
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaManutencaoVendedor window = new  TelaManutencaoVendedor();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaManutencaoVendedor() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Manutenção de Vendedor");
        frame.setBounds(100, 100, 450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        cpfFormattedField = new JFormattedTextField(createFormatter("###.###.###-##"));
        cpfFormattedField.setBounds(150, 10, 200, 20);
        frame.getContentPane().add(cpfFormattedField);

// Adicione um DocumentFilter para limitar o campo CPF a números
        ((AbstractDocument) cpfFormattedField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Permite apenas números
                String newValue = text.replaceAll("[^0-9]", "");
                super.replace(fb, offset, length, newValue, attrs);
            }
        });



        textFieldNome = new JTextField();
        textFieldNome.setBounds(150, 40, 200, 20);
        frame.getContentPane().add(textFieldNome);

        ButtonGroup genderButtonGroup = new ButtonGroup();

        radioButtonMasculino = new JRadioButton("Masculino");
        radioButtonMasculino.setBounds(150, 70, 100, 20);
        frame.getContentPane().add(radioButtonMasculino);
        genderButtonGroup.add(radioButtonMasculino);

        radioButtonFeminino = new JRadioButton("Feminino");
        radioButtonFeminino.setBounds(250, 70, 100, 20);
        frame.getContentPane().add(radioButtonFeminino);
        genderButtonGroup.add(radioButtonFeminino);
        
       
        dataNascimentoFormattedField = new JFormattedTextField(createFormatter("##/##/####"));
        dataNascimentoFormattedField.setBounds(150, 100, 200, 20);
        frame.getContentPane().add(dataNascimentoFormattedField);
        
        // Adicione um DocumentFilter para limitar o campo Data de Nascimento a números
        ((AbstractDocument) dataNascimentoFormattedField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Permite apenas números
                String newValue = text.replaceAll("[^0-9]", "");
                super.replace(fb, offset, length, newValue, attrs);
            }
        });

        textFieldRenda = new JTextField();
        textFieldRenda.setBounds(150, 130, 200, 20);
        frame.getContentPane().add(textFieldRenda);
        // Defina a máscara para o campo Renda aqui
        ((AbstractDocument) textFieldRenda.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Permite apenas números
                String newValue = text.replaceAll("[^0-9]", "");
                super.replace(fb, offset, length, newValue, attrs);
            }
        });

        textFieldLogradouro = new JTextField();
        textFieldLogradouro.setBounds(150, 160, 200, 20);
        frame.getContentPane().add(textFieldLogradouro);

        textFieldNumero = new JTextField();
        textFieldNumero.setBounds(150, 190, 200, 20);
        frame.getContentPane().add(textFieldNumero);
        ((AbstractDocument) textFieldNumero.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Permite apenas números
                String newValue = text.replaceAll("[^0-9]", "");
                super.replace(fb, offset, length, newValue, attrs);
            }
        });

        textFieldComplemento = new JTextField();
        textFieldComplemento.setBounds(150, 220, 200, 20);
        frame.getContentPane().add(textFieldComplemento);

        cepFormattedField = new JFormattedTextField(createFormatter("#####-###"));
        cepFormattedField.setBounds(150, 250, 200, 20);
        frame.getContentPane().add(cepFormattedField);
        
        // Adicione um DocumentFilter para limitar o campo CEP a números
        ((AbstractDocument) cepFormattedField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Permite apenas números
                String newValue = text.replaceAll("[^0-9]", "");
                super.replace(fb, offset, length, newValue, attrs);
            }
        });

        textFieldCidade = new JTextField();
        textFieldCidade.setBounds(150, 280, 200, 20);
        frame.getContentPane().add(textFieldCidade);

        comboEstado = new JComboBox<String>();
        comboEstado.setBounds(150, 310, 200, 20);
        // Adicione os estados brasileiros à lista do combo box
        String[] estados = {
            "Selecionar",
            "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará",
            "Distrito Federal", "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso",
            "Mato Grosso do Sul", "Minas Gerais", "Pará", "Paraíba", "Paraná",
            "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte",
            "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina",
            "São Paulo", "Sergipe", "Tocantins"
        };
        comboEstado.setModel(new DefaultComboBoxModel<String>(estados));
        frame.getContentPane().add(comboEstado);

        lblNewLabel = new JLabel("CPF");
        lblNewLabel.setBounds(6, 12, 61, 16);
        frame.getContentPane().add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("NOME COMPLETO");
        lblNewLabel_1.setBounds(6, 42, 117, 16);
        frame.getContentPane().add(lblNewLabel_1);
        
        lblNewLabel_2 = new JLabel("SEXO");
        lblNewLabel_2.setBounds(6, 72, 61, 16);
        frame.getContentPane().add(lblNewLabel_2);
        
        lblNewLabel_3 = new JLabel("DATA NASCIMENTO");
        lblNewLabel_3.setBounds(6, 102, 132, 16);
        frame.getContentPane().add(lblNewLabel_3);
        
        lblNewLabel_4 = new JLabel("RENDA");
        lblNewLabel_4.setBounds(6, 132, 132, 16);
        frame.getContentPane().add(lblNewLabel_4);
        
        lblNewLabel_5 = new JLabel("LOGRADOURO");
        lblNewLabel_5.setBounds(6, 162, 132, 16);
        frame.getContentPane().add(lblNewLabel_5);
        
        lblNewLabel_6 = new JLabel("NÚMERO");
        lblNewLabel_6.setBounds(6, 192, 132, 16);
        frame.getContentPane().add(lblNewLabel_6);
        
        lblNewLabel_7 = new JLabel("COMPLEMENTO");
        lblNewLabel_7.setBounds(6, 222, 132, 16);
        frame.getContentPane().add(lblNewLabel_7);
        
        lblNewLabel_8 = new JLabel("CEP");
        lblNewLabel_8.setBounds(6, 252, 61, 16);
        frame.getContentPane().add(lblNewLabel_8);
        
        lblNewLabel_9 = new JLabel("CIDADE");
        lblNewLabel_9.setBounds(6, 282, 61, 16);
        frame.getContentPane().add(lblNewLabel_9);
        
        lblNewLabel_10 = new JLabel("ESTADO");
        lblNewLabel_10.setBounds(6, 311, 61, 16);
        frame.getContentPane().add(lblNewLabel_10);
        
       JButton btnCadastrarVendedor = new JButton("Cadastrar Vendedor");
        btnCadastrarVendedor.setBounds(150, 350, 200, 30);
        frame.getContentPane().add(btnCadastrarVendedor);

        // Adicione um ActionListener para o botão "Cadastrar Vendedor"
        btnCadastrarVendedor.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              cadastrarVendedor();
          }
      });
    }
    
    private void cadastrarVendedor() {
        // Obtém os valores dos campos da interface gráfica
        String cpf = cpfFormattedField.getText().replaceAll("[.-]", "");

        String nome = textFieldNome.getText();
        Sexo sexo;
        if(radioButtonMasculino.isSelected()) {
          sexo = Sexo.MASCULINO;
        } else {
          sexo = Sexo.FEMININO;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoFormattedField.getText(), formatter);
        double renda = Double.parseDouble(textFieldRenda.getText());
        String logradouro = textFieldLogradouro.getText();
        int numero = Integer.parseInt(textFieldNumero.getText());
        String complemento = textFieldComplemento.getText();
        String cep = cepFormattedField.getText();
        String cidade = textFieldCidade.getText();
        String estado = (String) comboEstado.getSelectedItem();

        // Crie uma instância de Vendedor com os valores obtidos
        Endereco endereco = new Endereco(logradouro, numero, complemento, cep, cidade, estado, "Brasil");
        // Vendedor vendedor = new Vendedor(cpf, nome, Sexo.FEMININO, dataNascimento, renda, endereco);
        Vendedor vendedor = new Vendedor(cpf, nome, sexo, dataNascimento, renda, endereco);

        vendedor.setCpf(cpf);
        vendedor.setNomeCompleto(nome);
        vendedor.setSexo(Sexo.FEMININO);
        // Configure os outros atributos do vendedor com os valores obtidos

        // Chame o método do VendedorMediator para cadastrar o vendedor
        VendedorMediator mediator = VendedorMediator.getInstancia();
        ResultadoInclusaoVendedor resultado = mediator.incluir(vendedor);

        // Verifique o resultado e forneça feedback ao usuário
        if (resultado.getMensagemErroValidacao() != null) {
            JOptionPane.showMessageDialog(frame, resultado.getMensagemErroValidacao(), "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Vendedor cadastrado com sucesso. Número do Caixa de Bônus: " + resultado.getNumeroCaixaDeBonus(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
} 