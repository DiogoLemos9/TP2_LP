/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estga.poo.provamodelo2.lpgestaobiblioteca;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Janela de diálogo para criar um novo Empréstimo.
 */
public class NovoEmprestimoDialog extends JDialog {

    private JComboBox<Livro> comboLivros;
    private JComboBox<Membro> comboMembros;
    private JTextField txtDataPrevista;
    private boolean confirmado;

    public NovoEmprestimoDialog(Frame parent, List<Livro> livrosDisponiveis, List<Membro> membros) {
        super(parent, "Novo Empréstimo", true);
        setSize(400, 200);
        setLocationRelativeTo(parent);

        comboLivros = new JComboBox<>();
        for (Livro livro : livrosDisponiveis) {
            if (livro.isDisponivel()) {
                comboLivros.addItem(livro);
            }
        }

        comboMembros = new JComboBox<>();
        for (Membro membro : membros) {
            comboMembros.addItem(membro);
        }

        txtDataPrevista = new JTextField(10);

        JPanel painelCampos = new JPanel(new GridLayout(3, 2));
        painelCampos.add(new JLabel("Livro:"));
        painelCampos.add(comboLivros);
        painelCampos.add(new JLabel("Membro:"));
        painelCampos.add(comboMembros);
        painelCampos.add(new JLabel("Data Prevista (yyyy-MM-dd):"));
        painelCampos.add(txtDataPrevista);

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        btnConfirmar.addActionListener(e -> {
            if (comboLivros.getSelectedItem() == null || comboMembros.getSelectedItem() == null || txtDataPrevista.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preenche todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
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

    public Livro getLivroSelecionado() {
        return (Livro) comboLivros.getSelectedItem();
    }

    public Membro getMembroSelecionado() {
        return (Membro) comboMembros.getSelectedItem();
    }

    public Date getDataPrevista() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(txtDataPrevista.getText().trim());
    }
}
