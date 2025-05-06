/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estga.poo.provamodelo2.lpgestaobiblioteca;

/**
 * Classe que representa um Membro da biblioteca.
 */
public class Membro {
    private int id; // Identificador único do membro
    private String numeroSocio; // Número de sócio único
    private String primeiroNome; // Primeiro nome do membro
    private String apelido; // Apelido do membro
    private String email; // Email de contacto

    /**
     * Construtor para inicializar o membro com todos os atributos.
     */
    public Membro(int id, String numeroSocio, String primeiroNome, String apelido, String email) {
        this.id = id;
        this.numeroSocio = numeroSocio;
        this.primeiroNome = primeiroNome;
        this.apelido = apelido;
        this.email = email;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(String numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método para mostrar os dados do membro em formato de texto.
     */
    @Override
    public String toString() {
        return "Membro [id=" + id + ", NºSócio=" + numeroSocio + ", Nome=" + primeiroNome + " " + apelido + ", Email=" + email + "]";
    }
}

