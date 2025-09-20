package br.com.fiap.dao;

import br.com.fiap.model.Transacao;
import java.util.List;

public interface TransacaoDAO {
    void inserir(Transacao t) throws Exception;

    List<Transacao> listarPorCartao(int cartaoId) throws Exception;

    List<Transacao> listarAbertasPorCartao(int cartaoId) throws Exception;

    void atualizarStatus(int transacaoId, String status) throws Exception;
}
