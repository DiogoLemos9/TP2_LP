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
 * Janela de Gestão de Livros.
 */
public class GestaoLivrosFrame extends JFrame {

    private JTable tabelaLivros;
    private DefaultTableModel tabelaModelo;
    private List<Livro> listaLivros;

    public GestaoLivrosFrame() {
    super("Gestão de Livros");

    setSize(600, 400);
    setLocationRelativeTo(null);

    listaLivros = new ArrayList<>();
    listaLivros.add(new Livro(1, "978-0547928227", "The Hobbit", "J.R.R. Tolkien", true));
    listaLivros.add(new Livro(2, "978-0321765723", "Effective Java", "Joshua Bloch", false));
    listaLivros.add(new Livro(3, "978-0132350884", "Clean Code", "Robert C. Martin", true));

    tabelaModelo = new DefaultTableModel(new Object[]{"ID", "ISBN", "Título", "Autor", "Disponível"}, 0);
    atualizarTabela();

    tabelaLivros = new JTable(tabelaModelo);
    JScrollPane scrollPane = new JScrollPane(tabelaLivros);

    // PAINEL DE BOTÕES
    JPanel painelBotoes = new JPanel();
    JButton btnAdicionar = new JButton("Adicionar");
    JButton btnEditar = new JButton("Editar");
    JButton btnRemover = new JButton("Remover");

    painelBotoes.add(btnAdicionar);
    painelBotoes.add(btnEditar);
    painelBotoes.add(btnRemover);

    // AÇÕES DOS BOTÕES 
    btnAdicionar.addActionListener(e -> {
    LivroDialog dialog = new LivroDialog(this, null);
    dialog.setVisible(true);

    if (dialog.isConfirmado()) {
        // Criar um novo livro e adicionar à lista
        int novoId = listaLivros.size() + 1;
        Livro novoLivro = new Livro(novoId, dialog.getIsbn(), dialog.getTitulo(), dialog.getAutor(), true);
        listaLivros.add(novoLivro);
        atualizarTabela();
    }
});


    btnEditar.addActionListener(e -> {
    int linhaSelecionada = tabelaLivros.getSelectedRow();
    if (linhaSelecionada != -1) {
        Livro livroSelecionado = listaLivros.get(linhaSelecionada);
        LivroDialog dialog = new LivroDialog(this, livroSelecionado);
        dialog.setVisible(true);

        if (dialog.isConfirmado()) {
            // Atualizar os dados do livro
            livroSelecionado.setIsbn(dialog.getIsbn());
            livroSelecionado.setTitulo(dialog.getTitulo());
            livroSelecionado.setAutor(dialog.getAutor());
            atualizarTabela();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Seleciona um livro para editar.");
    }
});


    btnRemover.addActionListener(e -> {
        int linhaSelecionada = tabelaLivros.getSelectedRow();
        if (linhaSelecionada != -1) {
            int opcao = JOptionPane.showConfirmDialog(this, "Tens a certeza que queres remover este livro?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                listaLivros.remove(linhaSelecionada);
                atualizarTabela();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleciona um livro para remover.");
        }
    });

    // Layout da janela
    setLayout(new BorderLayout());
    add(scrollPane, BorderLayout.CENTER);
    add(painelBotoes, BorderLayout.SOUTH);
}
        

    /**
     * Atualiza os dados da tabela com a lista de livros.
     */
    private void atualizarTabela() {
        tabelaModelo.setRowCount(0); // Limpar a tabela
        for (Livro livro : listaLivros) {
            tabelaModelo.addRow(new Object[]{
                    livro.getId(),
                    livro.getIsbn(),
                    livro.getTitulo(),
                    livro.getAutor(),
                    livro.isDisponivel() ? "Sim" : "Não"
            });
        }
    }
}
