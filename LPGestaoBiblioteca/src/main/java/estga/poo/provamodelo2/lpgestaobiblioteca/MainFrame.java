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
 * Janela Principal da aplicação de Gestão da Biblioteca.
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        super("Gestão da Biblioteca"); // Título da janela

        // Definir tamanho da janela
        setSize(800, 600);

        // Fechar a aplicação ao clicar no X
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centralizar a janela no ecrã
        setLocationRelativeTo(null);

        // Criar a barra de menus
        JMenuBar menuBar = new JMenuBar();

        // Criar o menu "Gestão"
        JMenu menuGestao = new JMenu("Gestão");

        // Criar os itens do menu
        JMenuItem menuLivros = new JMenuItem("Livros");
        JMenuItem menuMembros = new JMenuItem("Membros");
        JMenuItem menuEmprestimos = new JMenuItem("Empréstimos");

        // Adicionar os itens ao menu
        menuGestao.add(menuLivros);
        menuGestao.add(menuMembros);
        menuGestao.add(menuEmprestimos);

        // Adicionar o menu à barra de menus
        menuBar.add(menuGestao);

        // Definir a barra de menus na janela
        setJMenuBar(menuBar);

        // Criar um label de boas-vindas
        JLabel labelBoasVindas = new JLabel("Bem-vindo à Gestão da Biblioteca!", JLabel.CENTER);
        add(labelBoasVindas, BorderLayout.CENTER);

        // Definir as ações ao clicar nos menus
        menuLivros.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        GestaoLivrosFrame janelaLivros = new GestaoLivrosFrame();
        janelaLivros.setVisible(true);
    }
});

        menuMembros.addActionListener(e -> {
    GestaoMembrosFrame janelaMembros = new GestaoMembrosFrame();
    janelaMembros.setVisible(true);
});


        menuEmprestimos.addActionListener(e -> {
    GestaoEmprestimosFrame janelaEmprestimos = new GestaoEmprestimosFrame();
    janelaEmprestimos.setVisible(true);
});

    }

    /**
     * Método main para iniciar o programa.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
