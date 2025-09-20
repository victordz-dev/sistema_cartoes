package br.com.fiap.factory;

import br.com.fiap.dao.*;

public class DAOFactory {
    public static ClienteDAO getClienteDAO() {
        return new ClienteDAOImpl();
    }

    public static CartaoDAO getCartaoDAO() {
        return new CartaoDAOImpl();
    }

    public static TransacaoDAO getTransacaoDAO() {
        return new TransacaoDAOImpl();
    }
}

