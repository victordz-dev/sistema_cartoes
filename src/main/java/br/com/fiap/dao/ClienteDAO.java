package br.com.fiap.dao;

import br.com.fiap.model.Cliente;
import java.util.List;

public interface ClienteDAO {
    void inserir(Cliente cliente) throws Exception;
    Cliente buscarPorCpf(String cpf) throws Exception;
    List<Cliente> listar() throws Exception;
}

