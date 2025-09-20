package br.com.fiap.dao;

import br.com.fiap.model.Cartao;
import java.util.List;

public interface CartaoDAO {
    void inserir(Cartao cartao) throws Exception;

    Cartao buscarPorNumero(String numero) throws Exception;

    void atualizar(Cartao cartao) throws Exception;

    List<Cartao> listarPorCliente(int clienteId) throws Exception;
}
