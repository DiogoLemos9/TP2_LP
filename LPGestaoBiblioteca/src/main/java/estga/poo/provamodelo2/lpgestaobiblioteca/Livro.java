/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estga.poo.provamodelo2.lpgestaobiblioteca;

/**
 * Classe que representa um Livro na biblioteca.
 */
public class Livro {
    private int id; // Identificador único do livro
    private String isbn; // ISBN único do livro
    private String titulo; // Título do livro
    private String autor; // Autor do livro
    private boolean disponivel; // Estado do livro: true = disponível; false = emprestado

    /**
     * Construtor para inicializar o livro com todos os atributos.
     */
    public Livro(int id, String isbn, String titulo, String autor, boolean disponivel) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = disponivel;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * Método para mostrar os dados do livro em formato de texto.
     * @return 
     */
    @Override
    public String toString() {
        return "Livro [id=" + id + ", ISBN=" + isbn + ", Título=" + titulo + ", Autor=" + autor + ", Disponível=" + disponivel + "]";
    }
}

