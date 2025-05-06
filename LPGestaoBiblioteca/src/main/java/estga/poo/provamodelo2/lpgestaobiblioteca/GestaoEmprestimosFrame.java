/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estga.poo.provamodelo2.lpgestaobiblioteca;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Janela de Gestão de Empréstimos.
 */
public class GestaoEmprestimosFrame extends JFrame {

    private JTable tabelaEmprestimos;
    private DefaultTableModel tabelaModelo;
    private List<Emprestimo> listaEmprestimos;

    public GestaoEmprestimosFrame() {
        super("Gestão de Empréstimos");

        setSize(700, 400);
        setLocationRelativeTo(null);

        listaEmprestimos = new ArrayList<>();

        // Dados de teste
        listaEmprestimos.add(new Emprestimo(1, 1, 1, new Date(), new Date(System.currentTimeMillis() + 7L*24*60*60*1000), null));
        listaEmprestimos.add(new Emprestimo(2, 2, 2, new Date(System.currentTimeMillis() - 10L*24*60*60*1000), new Date(System.currentTimeMillis() - 3L*24*60*60*1000), null));
        listaEmprestimos.add(new Emprestimo(3, 3, 1, new Date(System.currentTimeMillis() - 20L*24*60*60*1000), new Date(System.currentTimeMillis() - 10L*24*60*60*1000), new Date(System.currentTimeMillis() - 9L*24*60*60*1000)));

        tabelaModelo = new DefaultTableModel(new Object[]{"ID", "LivroID", "MembroID", "Data Empréstimo", "Data Prevista", "Data Devolução", "Estado"}, 0);
        atualizarTabela();

        tabelaEmprestimos = new JTable(tabelaModelo);
        JScrollPane scrollPane = new JScrollPane(tabelaEmprestimos);

        // Painel de Botões
        JPanel painelBotoes = new JPanel();
        JButton btnNovoEmprestimo = new JButton("Novo Empréstimo");
        JButton btnDevolver = new JButton("Devolver");

        painelBotoes.add(btnNovoEmprestimo);
        painelBotoes.add(btnDevolver);

        // Ações
        btnNovoEmprestimo.addActionListener(e -> {
    NovoEmprestimoDialog dialog = new NovoEmprestimoDialog(this, obterLivrosDisponiveis(), obterMembros());
    dialog.setVisible(true);

    if (dialog.isConfirmado()) {
        try {
            int novoId = listaEmprestimos.size() + 1;
            Livro livroSelecionado = dialog.getLivroSelecionado();
            Membro membroSelecionado = dialog.getMembroSelecionado();
            Date dataEmprestimo = new Date();
            Date dataPrevista = dialog.getDataPrevista();

            Emprestimo novoEmprestimo = new Emprestimo(novoId, livroSelecionado.getId(), membroSelecionado.getId(), dataEmprestimo, dataPrevista, null);
            listaEmprestimos.add(novoEmprestimo);

            livroSelecionado.setDisponivel(false); // Marca o livro como emprestado

            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Empréstimo criado com sucesso.");
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Data inválida! Use o formato yyyy-MM-dd.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
});


        btnDevolver.addActionListener(e -> {
            int linhaSelecionada = tabelaEmprestimos.getSelectedRow();
            if (linhaSelecionada != -1) {
                Emprestimo emprestimoSelecionado = listaEmprestimos.get(linhaSelecionada);
                if (emprestimoSelecionado.getDataDevolucaoEfetiva() == null) {
                    emprestimoSelecionado.setDataDevolucaoEfetiva(new Date());
                    atualizarTabela();
                    JOptionPane.showMessageDialog(this, "Livro devolvido com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(this, "Este empréstimo já foi devolvido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleciona um empréstimo para devolver.");
            }
        });
        
        JButton btnExportar = new JButton("Exportar CSV");
painelBotoes.add(btnExportar);

        btnExportar.addActionListener(e -> {
    JFileChooser fileChooser = new JFileChooser();
    int resultado = fileChooser.showSaveDialog(this);
    if (resultado == JFileChooser.APPROVE_OPTION) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(fileChooser.getSelectedFile())) {
            // Cabeçalho
            writer.println("ID,TituloLivro,NomeCompletoMembro,DataEmprestimo,DataDevolucaoPrevista,DataDevolucaoEfetiva,Estado");

            for (Emprestimo emprestimo : listaEmprestimos) {
                String tituloLivro = encontrarTituloLivro(emprestimo.getIdLivro());
                String nomeMembro = encontrarNomeMembro(emprestimo.getIdMembro());

                writer.println(emprestimo.getId() + "," +
                        tituloLivro + "," +
                        nomeMembro + "," +
                        emprestimo.getDataEmprestimo() + "," +
                        emprestimo.getDataDevolucaoPrevista() + "," +
                        emprestimo.getDataDevolucaoEfetiva() + "," +
                        emprestimo.getEstado());
            }

            JOptionPane.showMessageDialog(this, "Exportação concluída!");
        } catch (java.io.IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao exportar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
});
  
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private void atualizarTabela() {
        tabelaModelo.setRowCount(0);
        for (Emprestimo e : listaEmprestimos) {
            tabelaModelo.addRow(new Object[]{
                    e.getId(),
                    e.getIdLivro(),
                    e.getIdMembro(),
                    e.getDataEmprestimo(),
                    e.getDataDevolucaoPrevista(),
                    e.getDataDevolucaoEfetiva(),
                    e.getEstado()
            });
        }
    }
    
    private List<Livro> obterLivrosDisponiveis() {
    List<Livro> lista = new ArrayList<>();
    lista.add(new Livro(1, "978-0547928227", "The Hobbit", "J.R.R. Tolkien", true));
    lista.add(new Livro(2, "978-0321765723", "Effective Java", "Joshua Bloch", true));
    lista.add(new Livro(3, "978-0132350884", "Clean Code", "Robert C. Martin", true));
    return lista;
}

private List<Membro> obterMembros() {
    List<Membro> lista = new ArrayList<>();
    lista.add(new Membro(1, "S001", "Maria", "Santos", "maria.s@email.com"));
    lista.add(new Membro(2, "S002", "Pedro", "Almeida", "pedro.a@email.com"));
    return lista;
}

private String encontrarTituloLivro(int idLivro) {
    for (Livro livro : obterLivrosDisponiveis()) { // usa a lista de teste ou adapta
        if (livro.getId() == idLivro) {
            return livro.getTitulo();
        }
    }
    return "Desconhecido";
}

private String encontrarNomeMembro(int idMembro) {
    for (Membro membro : obterMembros()) { // usa a lista de teste ou adapta
        if (membro.getId() == idMembro) {
            return membro.getPrimeiroNome() + " " + membro.getApelido();
        }
    }
    return "Desconhecido";
}


}

