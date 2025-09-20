package br.com.fiap.controller;

import br.com.fiap.bo.TransacaoBO;
import br.com.fiap.dao.CartaoDAO;
import br.com.fiap.dao.TransacaoDAO;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Transacao;

import java.util.List;

public class TransacaoController {
    private TransacaoBO transacaoBO;
    private TransacaoDAO transacaoDAO;

    public TransacaoController() {
        CartaoDAO cartaoDAO = DAOFactory.getCartaoDAO();
        this.transacaoDAO = DAOFactory.getTransacaoDAO();
        this.transacaoBO = new TransacaoBO(this.transacaoDAO, cartaoDAO);
    }

    public void registrarCompra(String numeroCartao, double valor, String descricao) {
        try {
            transacaoBO.registrarCompra(numeroCartao, valor, descricao);
            System.out.println("Compra registrada!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void consultarFatura(String numeroCartao) {
        try {
            List<Transacao> abertas = transacaoBO.consultarFatura(numeroCartao);
            double total = 0;
            for (Transacao t : abertas) {
                System.out.println(t.getData() + " - " + t.getDescricao() + " - R$" + t.getValor());
                total += t.getValor();
            }
            System.out.println("Total da fatura: R$" + total);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void pagarFatura(String numeroCartao) {
        try {
            transacaoBO.pagarFatura(numeroCartao);
            System.out.println("Fatura paga com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public List<Transacao> listarTodasPorCartao(int cartaoId) throws Exception {
        return transacaoDAO.listarPorCartao(cartaoId);
    }
}