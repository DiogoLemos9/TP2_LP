/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estga.poo.provamodelo2.lpgestaobiblioteca;

import java.util.Date;

/**
 * Classe que representa um Empréstimo na biblioteca.
 */
public class Emprestimo {
    private int id; // Identificador único do empréstimo
    private int idLivro; // Id do livro emprestado
    private int idMembro; // Id do membro que requisitou
    private Date dataEmprestimo; // Data em que o empréstimo foi feito
    private Date dataDevolucaoPrevista; // Data prevista de devolução
    private Date dataDevolucaoEfetiva; // Data efetiva de devolução (null se ainda não foi devolvido)

    /**
     * Construtor com todos os atributos.
     */
    public Emprestimo(int id, int idLivro, int idMembro, Date dataEmprestimo, Date dataDevolucaoPrevista, Date dataDevolucaoEfetiva) {
        this.id = id;
        this.idLivro = idLivro;
        this.idMembro = idMembro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getIdMembro() {
        return idMembro;
    }

    public void setIdMembro(int idMembro) {
        this.idMembro = idMembro;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public Date getDataDevolucaoEfetiva() {
        return dataDevolucaoEfetiva;
    }

    public void setDataDevolucaoEfetiva(Date dataDevolucaoEfetiva) {
        this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
    }

    /**
     * Método que retorna o estado do empréstimo:
     * - "Ativo" se ainda não foi devolvido e está dentro do prazo
     * - "Atrasado" se ainda não devolvido e já passou do prazo
     * - "Devolvido" se já foi devolvido
     */
    public String getEstado() {
        if (dataDevolucaoEfetiva != null) {
            return "Devolvido";
        } else {
            Date hoje = new Date();
            if (hoje.after(dataDevolucaoPrevista)) {
                return "Atrasado";
            } else {
                return "Ativo";
            }
        }
    }

    /**
     * Método para mostrar os dados do empréstimo em formato de texto.
     */
    @Override
    public String toString() {
        return "Emprestimo [id=" + id + ", LivroID=" + idLivro + ", MembroID=" + idMembro +
                ", DataEmprestimo=" + dataEmprestimo + ", DataPrevista=" + dataDevolucaoPrevista +
                ", DataEfetiva=" + dataDevolucaoEfetiva + ", Estado=" + getEstado() + "]";
    }
}

