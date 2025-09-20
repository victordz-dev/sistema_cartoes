package br.com.fiap.model;

public class Cartao {
    private int id;
    private String numero;
    private String bandeira; // VISA ou MASTERCARD
    private double limiteTotal;
    private double limiteDisponivel;
    private int clienteId;

    public Cartao(int id, String numero, String bandeira, double limite, double limite1, int cli_id) {
        this.id = id;
        this.clienteId = cli_id;
        this.numero = numero;
        this.bandeira = bandeira;
        this.limiteTotal = limite;
        this.limiteDisponivel = limite1;
    }

    public int getId() { return id; }

    public String getNumero() { return numero; }

    public String getBandeira() { return bandeira; }

    public double getLimiteTotal() { return limiteTotal; }

    public double getLimiteDisponivel() { return limiteDisponivel; }

    public void setLimiteDisponivel(double limiteDisponivel) { this.limiteDisponivel = limiteDisponivel; }

    public int getClienteId() { return clienteId; }
}

