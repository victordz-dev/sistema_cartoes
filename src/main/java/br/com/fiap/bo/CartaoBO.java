package br.com.fiap.bo;

import br.com.fiap.dao.CartaoDAO;
import br.com.fiap.exception.RegraNegocioException;
import br.com.fiap.model.Cartao;
import br.com.fiap.model.Cliente;

import java.util.Random;

public class CartaoBO {
    private CartaoDAO cartaoDAO;

    public CartaoBO(CartaoDAO cartaoDAO) {
        this.cartaoDAO = cartaoDAO;
    }

    public void solicitarCartao(Cliente cliente, String bandeira) throws Exception {
        if (bandeira.equalsIgnoreCase("VISA") && cliente.getRenda() < 1500) {
            throw new RegraNegocioException("Renda insuficiente para VISA");
        }
        if (bandeira.equalsIgnoreCase("MASTERCARD") && cliente.getRenda() < 2000) {
            throw new RegraNegocioException("Renda insuficiente para MASTERCARD");
        }

        double limite = bandeira.equalsIgnoreCase("VISA")
                ? cliente.getRenda() * 2
                : cliente.getRenda() * 2.5;

        String numero = String.valueOf(System.currentTimeMillis())
                + new Random().nextInt(1000);

        Cartao c = new Cartao(0, numero, bandeira, limite, limite, cliente.getId());
        cartaoDAO.inserir(c);
    }
}
