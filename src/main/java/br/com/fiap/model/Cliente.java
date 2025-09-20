package br.com.fiap.model;

public class Cliente {
    private int id;
    private String cpf;
    private String nome;
    private String email;
    private double renda;

    public Cliente(int id, String cpf, String nome, String email, double renda) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.renda = renda;
    }

    public int getId() { return id; }

    public String getCpf() { return cpf; }

    public String getNome() { return nome; }

    public String getEmail() { return email; }

    public double getRenda() { return renda; }

}