/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estga.poo.provamodelo2.lpgestaobiblioteca;

import javax.swing.*;
import java.awt.*;

/**
 * Janela de diálogo para Adicionar ou Editar um Membro.
 */
public class MembroDialog extends JDialog {

    private JTextField txtNumeroSocio;
    private JTextField txtPrimeiroNome;
    private JTextField txtApelido;
    private JTextField txtEmail;
    private boolean confirmado;
    private Membro membro;

    public MembroDialog(Frame parent, Membro membroExistente) {
        super(parent, "Adicionar/Editar Membro", true);
        setSize(300, 250);
        setLocationRelativeTo(parent);

        txtNumeroSocio = new JTextField(20);
        txtPrimeiroNome = new JTextField(20);
        txtApelido = new JTextField(20);
        txtEmail = new JTextField(20);

        if (membroExistente != null) {
            txtNumeroSocio.setText(membroExistente.getNumeroSocio());
            txtPrimeiroNome.setText(membroExistente.getPrimeiroNome());
            txtApelido.setText(membroExistente.getApelido());
            txtEmail.setText(membroExistente.getEmail());
            this.membro = membroExistente;
        }

        JPanel painelCampos = new JPanel(new GridLayout(4, 2));
        painelCampos.add(new JLabel("Nº Sócio:"));
        painelCampos.add(txtNumeroSocio);
        painelCampos.add(new JLabel("Primeiro Nome:"));
        painelCampos.add(txtPrimeiroNome);
        painelCampos.add(new JLabel("Apelido:"));
        painelCampos.add(txtApelido);
        painelCampos.add(new JLabel("Email:"));
        painelCampos.add(txtEmail);

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        btnConfirmar.addActionListener(e -> {
            if (txtNumeroSocio.getText().trim().isEmpty() || txtPrimeiroNome.getText().trim().isEmpty()
                    || txtApelido.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(MembroDialog.this, "Preenche todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            confirmado = true;
            dispose();
        });

        btnCancelar.addActionListener(e -> {
            confirmado = false;
            dispose();
        });

        setLayout(new BorderLayout());
        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public String getNumeroSocio() {
        return txtNumeroSocio.getText().trim();
    }

    public String getPrimeiroNome() {
        return txtPrimeiroNome.getText().trim();
    }

    public String getApelido() {
        return txtApelido.getText().trim();
    }

    public String getEmail() {
        return txtEmail.getText().trim();
    }
}

