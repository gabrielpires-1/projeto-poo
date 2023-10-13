package br.gov.cesarschool.poo.bonusvendas.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAdicao {

    private JFrame frame;
    private JTextField txtPrimeiroNumero;
    private JTextField txtSegundoNumero;
    private JTextField txtResultado;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaAdicao window = new TelaAdicao();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaAdicao() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 556, 330);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblPrimeiroNmero = new JLabel("Primeiro número");
        lblPrimeiroNmero.setBounds(41, 40, 121, 20);
        frame.getContentPane().add(lblPrimeiroNmero);

        txtPrimeiroNumero = new JTextField();
        txtPrimeiroNumero.setBounds(183, 40, 78, 26);
        frame.getContentPane().add(txtPrimeiroNumero);
        txtPrimeiroNumero.setColumns(10);

        JLabel lblSegundoNmero = new JLabel("Segundo número");
        lblSegundoNmero.setBounds(41, 102, 121, 20);
        frame.getContentPane().add(lblSegundoNmero);

        txtSegundoNumero = new JTextField();
        txtSegundoNumero.setBounds(183, 102, 78, 26);
        frame.getContentPane().add(txtSegundoNumero);
        txtSegundoNumero.setColumns(10);

        JLabel lblResultado = new JLabel("Resultado");
        lblResultado.setBounds(41, 163, 70, 20);
        frame.getContentPane().add(lblResultado);

        txtResultado = new JTextField();
        txtResultado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtResultado.setEditable(false);
        txtResultado.setBounds(183, 163, 78, 37);
        frame.getContentPane().add(txtResultado);
        txtResultado.setColumns(10);

        JButton btnSomar = new JButton("Somar");
        btnSomar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double n1 = Double.parseDouble(txtPrimeiroNumero.getText());
                double n2 = Double.parseDouble(txtSegundoNumero.getText());
                double soma = n1 + n2;
                txtResultado.setText("" + soma);
            }
        });
        btnSomar.setBounds(130, 220, 90, 30);
        frame.getContentPane().add(btnSomar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtPrimeiroNumero.setText("");
                txtSegundoNumero.setText("");
                txtResultado.setText("");
            }
        });
        btnLimpar.setBounds(254, 220, 90, 30);
        frame.getContentPane().add(btnLimpar);
    }
}
