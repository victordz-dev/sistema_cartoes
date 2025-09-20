package br.com.fiap.controller;

import br.com.fiap.bo.CartaoBO;
import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.factory.DAOFactory;
import br.com.fiap.model.Cliente;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = DAOFactory.getClienteDAO();
    }

    public void cadastrarCliente(String cpf, String nome, String email, double renda) {
        try {
            Cliente cliente = new Cliente(0, cpf, nome, email, renda);
            clienteDAO.inserir(cliente);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }

    public Cliente buscarCliente(String cpf) {
        try {
            return clienteDAO.buscarPorCpf(cpf);
        } catch (Exception e) {
            System.out.println("Erro ao buscar cliente: " + e.getMessage());
            return null;
        }
    }
}
