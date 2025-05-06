/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estga.poo.provamodelo2.lpgestaobiblioteca;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Janela de diálogo para Adicionar ou Editar um Livro.
 */
public class LivroDialog extends JDialog {

    private JTextField txtIsbn;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private boolean confirmado;
    private Livro livro;

    public LivroDialog(Frame parent, Livro livroExistente) {
        super(parent, "Adicionar/Editar Livro", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        // Criar os campos
        txtIsbn = new JTextField(20);
        txtTitulo = new JTextField(20);
        txtAutor = new JTextField(20);

        // Se for edição, preencher os campos
        if (livroExistente != null) {
            txtIsbn.setText(livroExistente.getIsbn());
            txtTitulo.setText(livroExistente.getTitulo());
            txtAutor.setText(livroExistente.getAutor());
            this.livro = livroExistente;
        }

        // Layout
        JPanel painelCampos = new JPanel(new GridLayout(3, 2));
        painelCampos.add(new JLabel("ISBN:"));
        painelCampos.add(txtIsbn);
        painelCampos.add(new JLabel("Título:"));
        painelCampos.add(txtTitulo);
        painelCampos.add(new JLabel("Autor:"));
        painelCampos.add(txtAutor);

        // Botões
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        // Ações dos botões
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtIsbn.getText().trim().isEmpty() || txtTitulo.getText().trim().isEmpty() || txtAutor.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(LivroDialog.this, "Preenche todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                confirmado = true;
                dispose();
            }
        });

        btnCancelar.addActionListener(e -> {
            confirmado = false;
            dispose();
        });

        // Adicionar componentes à janela
        setLayout(new BorderLayout());
        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public String getIsbn() {
        return txtIsbn.getText().trim();
    }

    public String getTitulo() {
        return txtTitulo.getText().trim();
    }

    public String getAutor() {
        return txtAutor.getText().trim();
    }
}

