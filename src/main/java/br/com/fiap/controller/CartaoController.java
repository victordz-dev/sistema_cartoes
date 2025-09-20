package br.com.fiap.controller;

import br.com.fiap.bo.CartaoBO;
import br.com.fiap.dao.CartaoDAO;
import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Cartao;
import br.com.fiap.model.Cliente;

import java.util.List;

public class CartaoController {
    private CartaoBO cartaoBO;
    private CartaoDAO cartaoDAO;


    public CartaoController() {
        this.cartaoDAO = DAOFactory.getCartaoDAO();
        this.cartaoBO = new CartaoBO(this.cartaoDAO);
    }

    public void solicitarCartao(Cliente cliente, String bandeira) {
        try {
            cartaoBO.solicitarCartao(cliente, bandeira);
            System.out.println("Cartão aprovado para " + cliente.getNome());
        } catch (Exception e) {
            System.out.println("Erro ao solicitar cartão: " + e.getMessage());
        }
    }
    public Cartao buscarPorNumero(String numero) throws Exception {
        return cartaoDAO.buscarPorNumero(numero);
    }
    public List<Cartao> listarPorCliente(int clienteId) throws Exception {
        return cartaoDAO.listarPorCliente(clienteId);
    }
}

