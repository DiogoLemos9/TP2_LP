/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estga.poo.provamodelo2.lpgestaobiblioteca;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Janela de Gestão de Membros.
 */
public class GestaoMembrosFrame extends JFrame {

    private JTable tabelaMembros;
    private DefaultTableModel tabelaModelo;
    private List<Membro> listaMembros;

    public GestaoMembrosFrame() {
        super("Gestão de Membros");

        setSize(600, 400);
        setLocationRelativeTo(null);

        listaMembros = new ArrayList<>();
        listaMembros.add(new Membro(1, "S001", "Maria", "Santos", "maria.s@email.com"));
        listaMembros.add(new Membro(2, "S002", "Pedro", "Almeida", "pedro.a@email.com"));

        tabelaModelo = new DefaultTableModel(new Object[]{"ID", "Nº Sócio", "Primeiro Nome", "Apelido", "Email"}, 0);
        atualizarTabela();

        tabelaMembros = new JTable(tabelaModelo);
        JScrollPane scrollPane = new JScrollPane(tabelaMembros);

        // PAINEL DE BOTÕES
        JPanel painelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnRemover = new JButton("Remover");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);

        // AÇÕES
        btnAdicionar.addActionListener(e -> {
            MembroDialog dialog = new MembroDialog(this, null);
            dialog.setVisible(true);

            if (dialog.isConfirmado()) {
                int novoId = listaMembros.size() + 1;
                Membro novoMembro = new Membro(novoId, dialog.getNumeroSocio(), dialog.getPrimeiroNome(), dialog.getApelido(), dialog.getEmail());
                listaMembros.add(novoMembro);
                atualizarTabela();
            }
        });

        btnEditar.addActionListener(e -> {
            int linhaSelecionada = tabelaMembros.getSelectedRow();
            if (linhaSelecionada != -1) {
                Membro membroSelecionado = listaMembros.get(linhaSelecionada);
                MembroDialog dialog = new MembroDialog(this, membroSelecionado);
                dialog.setVisible(true);

                if (dialog.isConfirmado()) {
                    membroSelecionado.setNumeroSocio(dialog.getNumeroSocio());
                    membroSelecionado.setPrimeiroNome(dialog.getPrimeiroNome());
                    membroSelecionado.setApelido(dialog.getApelido());
                    membroSelecionado.setEmail(dialog.getEmail());
                    atualizarTabela();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleciona um membro para editar.");
            }
        });

        btnRemover.addActionListener(e -> {
            int linhaSelecionada = tabelaMembros.getSelectedRow();
            if (linhaSelecionada != -1) {
                int opcao = JOptionPane.showConfirmDialog(this, "Tens a certeza que queres remover este membro?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_OPTION) {
                    listaMembros.remove(linhaSelecionada);
                    atualizarTabela();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleciona um membro para remover.");
            }
        });

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void atualizarTabela() {
        tabelaModelo.setRowCount(0);
        for (Membro membro : listaMembros) {
            tabelaModelo.addRow(new Object[]{
                    membro.getId(),
                    membro.getNumeroSocio(),
                    membro.getPrimeiroNome(),
                    membro.getApelido(),
                    membro.getEmail()
            });
        }
    }
}

