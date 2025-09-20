package br.com.fiap.model;

import java.time.LocalDateTime;

public class Transacao {
    private int id;
    private int cartaoId;
    private LocalDateTime data;
    private String descricao;
    private double valor;
    private String status;

    public Transacao(int id, int cart_id, LocalDateTime now, String descricao, double valor, String aberta) {
        this.id = id;
        this.cartaoId = cart_id;
        this.data = now;
        this.descricao = descricao;
        this.valor = valor;
        this.status = aberta;
    }

    public int getId() { return id; }
    public int getCartaoId() { return cartaoId; }
    public LocalDateTime getData() { return data; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public String getStatus() { return status; }
}

