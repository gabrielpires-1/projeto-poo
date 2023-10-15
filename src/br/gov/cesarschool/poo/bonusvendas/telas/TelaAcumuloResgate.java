package br.gov.cesarschool.poo.bonusvendas.telas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.gov.cesarschool.poo.bonusvendas.dao.CaixaDeBonusDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.CaixaDeBonus;
import br.gov.cesarschool.poo.bonusvendas.entidade.TipoResgate;
import br.gov.cesarschool.poo.bonusvendas.negocio.AcumuloResgateMediator;

public class TelaAcumuloResgate {
    private JFrame frame;
    private JTextField inputCaixaDeBonus;
    private JTextField inputSaldo;
    private JTextField inputValor;
    private JComboBox<String> comboBoxTipoDeResgate = new JComboBox<>();
    private JButton btnBuscar;
    private JButton btnVoltar;
    private JRadioButton operacaoAcumular = new JRadioButton("Acumular");
    private JRadioButton operacaoResgatar;
    private double saldoCaixaDeBonus = 0;

    private CaixaDeBonusDAO caixaDeBonusDAO;
    private AcumuloResgateMediator acumuloResgateMediator;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                TelaAcumuloResgate window = new TelaAcumuloResgate();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaAcumuloResgate() {
        initialize();
        caixaDeBonusDAO = new CaixaDeBonusDAO();
        acumuloResgateMediator = AcumuloResgateMediator.getInstancia();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Swing Application");

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel labelCaixaDeBonus = new JLabel("Caixa de bônus");
        labelCaixaDeBonus.setBounds(20, 20, 100, 20);
        panel.add(labelCaixaDeBonus);

        inputCaixaDeBonus = new JTextField();
        inputCaixaDeBonus.setBounds(130, 20, 100, 20);
        panel.add(inputCaixaDeBonus);

        // Outros componentes da interface (Operação, Saldo, Tipo de Resgate, Valor)

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(130, 120, 100, 30);
        btnBuscar.setEnabled(true);
        panel.add(btnBuscar);

        JLabel labelSaldo = new JLabel("Saldo");
        labelSaldo.setBounds(20, 160, 100, 20);
        panel.add(labelSaldo);

        inputSaldo = new JTextField();
        inputSaldo.setBounds(130, 160, 100, 20);
        inputSaldo.setEditable(false);
        panel.add(inputSaldo);

        // Outros componentes da interface (Tipo de Resgate, Valor)

        JButton btnAcumular = new JButton("Acumular");
        btnAcumular.setBounds(130, 280, 100, 30);
        panel.add(btnAcumular);

        JButton btnResgatar = new JButton("Resgatar");
        btnResgatar.setBounds(240, 280, 100, 30);
        panel.add(btnResgatar);

        // Atualize os ActionListeners para cada botão separadamente
        btnAcumular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                acumular();
            }
        });

        btnResgatar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resgatar();
            }
        });

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(40, 280, 80, 30);
        panel.add(btnVoltar);

        // Adicione ActionListeners para os botões
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarCaixaDeBonus();
            }
        });

        // btnAcumularResgatar.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         acumularOuResgatar();
        //     }
        // });

        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
    }

    private void buscarCaixaDeBonus() {
        try {
            long numeroCaixa = Long.parseLong(inputCaixaDeBonus.getText());
            CaixaDeBonus caixa = caixaDeBonusDAO.buscar(numeroCaixa);

            if (caixa != null) {
                saldoCaixaDeBonus = caixa.getSaldo();
                inputSaldo.setText(String.valueOf(saldoCaixaDeBonus));
                inputSaldo.setEditable(true);
                operacaoAcumular.setEnabled(true);
                operacaoResgatar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Caixa de bônus inexistente.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Número de Caixa de Bônus inválido.");
        }
    }

    private void acumular() {
        try {
            long numeroCaixa = Long.parseLong(inputCaixaDeBonus.getText());
            double valor = Double.parseDouble(inputSaldo.getText());
            
            String resultado = AcumuloResgateMediator.getInstancia().acumularBonus(numeroCaixa, valor);
    
            if (resultado == null) {
                CaixaDeBonus caixa = caixaDeBonusDAO.buscar(numeroCaixa);
                saldoCaixaDeBonus = caixa.getSaldo();
                inputSaldo.setText(String.valueOf(saldoCaixaDeBonus));
                JOptionPane.showMessageDialog(frame, "Operação de Acúmulo concluída com sucesso!");
            } else {
                JOptionPane.showMessageDialog(frame, resultado);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Número de Caixa de Bônus ou valor inválido.");
        }
    }
    
    private void resgatar() {
        try {
            long numeroCaixa = Long.parseLong(inputCaixaDeBonus.getText());
            double valor = Double.parseDouble(inputSaldo.getText()); 
            TipoResgate tipoResgate = obterTipoResgateSelecionado();
    
            String resultado = AcumuloResgateMediator.getInstancia().resgatar(numeroCaixa, valor, TipoResgate.CASH);
    
            if (resultado == null) {
                CaixaDeBonus caixa = caixaDeBonusDAO.buscar(numeroCaixa);
                saldoCaixaDeBonus = caixa.getSaldo();
                inputSaldo.setText(String.valueOf(saldoCaixaDeBonus));
                JOptionPane.showMessageDialog(frame, "Operação de Resgate concluída com sucesso!");
            } else {
                JOptionPane.showMessageDialog(frame, resultado);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Número de Caixa de Bônus ou valor inválido.");
        }
    }
    
    private void acumularOuResgatar() {
        if (operacaoAcumular.isSelected()) {
            try {
                double valor = Double.parseDouble(inputValor.getText());
                String resultado = acumuloResgateMediator.acumularBonus(Long.parseLong(inputCaixaDeBonus.getText()), valor);

                if (resultado == null) {
                    CaixaDeBonus caixa = caixaDeBonusDAO.buscar(Long.parseLong(inputCaixaDeBonus.getText()));
                    saldoCaixaDeBonus = caixa.getSaldo();
                    inputSaldo.setText(String.valueOf(saldoCaixaDeBonus));
                    JOptionPane.showMessageDialog(frame, "Operação de Acúmulo concluída com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, resultado);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Valor inválido.");
            }
        } else if (operacaoResgatar.isSelected()) {
            if (comboBoxTipoDeResgate.getSelectedIndex() != 0) {
                try {
                    double valor = Double.parseDouble(inputValor.getText());
                    TipoResgate tipoResgate = obterTipoResgateSelecionado();
                    
                    String resultado = acumuloResgateMediator.resgatar(Long.parseLong(inputCaixaDeBonus.getText()), valor, tipoResgate);
                    
                    if (resultado == null) {
                        CaixaDeBonus caixa = caixaDeBonusDAO.buscar(Long.parseLong(inputCaixaDeBonus.getText()));
                        saldoCaixaDeBonus = caixa.getSaldo();
                        inputSaldo.setText(String.valueOf(saldoCaixaDeBonus));
                        JOptionPane.showMessageDialog(frame, "Operação de Resgate concluída com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(frame, resultado);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Valor inválido.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um Tipo de Resgate.");
            }
        }
    }

    private TipoResgate obterTipoResgateSelecionado() {
        int selectedIndex = comboBoxTipoDeResgate.getSelectedIndex();
        switch (selectedIndex) {
            case 1:
                return TipoResgate.CASH;
            case 2:
                return TipoResgate.SERVICO;
            case 3:
                return TipoResgate.PRODUTO;
            default:
                return null;
        }
    }

    private void limparCampos() {
        inputCaixaDeBonus.setText("");
        inputSaldo.setText("");
        inputValor.setText("");
        comboBoxTipoDeResgate.setSelectedIndex(0);
        operacaoAcumular.setSelected(false);
        operacaoResgatar.setSelected(false);
        inputSaldo.setEditable(false);
        operacaoAcumular.setEnabled(false);
        operacaoResgatar.setEnabled(false);
    }
}