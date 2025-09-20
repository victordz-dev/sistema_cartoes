package br.com.fiap.bo;
import br.com.fiap.dao.CartaoDAO;
import br.com.fiap.dao.TransacaoDAO;
import br.com.fiap.exception.EntidadeNaoEncontradaException;
import br.com.fiap.exception.LimiteInsuficienteException;
import br.com.fiap.model.Cartao;
import br.com.fiap.model.Transacao;

import java.time.LocalDateTime;
import java.util.List;

public class TransacaoBO {
    private TransacaoDAO transacaoDAO;
    private CartaoDAO cartaoDAO;

    public TransacaoBO(TransacaoDAO transacaoDAO, CartaoDAO cartaoDAO) {
        this.transacaoDAO = transacaoDAO;
        this.cartaoDAO = cartaoDAO;
    }

    public void registrarCompra(String numeroCartao, double valor, String descricao)
            throws Exception {
        Cartao cartao = cartaoDAO.buscarPorNumero(numeroCartao);
        if (cartao == null) {
            throw new EntidadeNaoEncontradaException("Cartão não encontrado!");
        }

        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero!");
        }
        if (valor > cartao.getLimiteDisponivel()) {
            throw new LimiteInsuficienteException("Limite insuficiente!");
        }

        // Cria transação
        Transacao t = new Transacao(
                5,
                cartao.getId(),
                LocalDateTime.now(),
                descricao,
                valor,
                "ABERTA"
        );


        transacaoDAO.inserir(t);
        cartao.setLimiteDisponivel(cartao.getLimiteDisponivel() - valor);
        cartaoDAO.atualizar(cartao);
    }

    public List<Transacao> consultarFatura(String numeroCartao) throws Exception {
        Cartao cartao = cartaoDAO.buscarPorNumero(numeroCartao);
        if (cartao == null) {
            throw new EntidadeNaoEncontradaException("Cartão não encontrado!");
        }
        return transacaoDAO.listarAbertasPorCartao(cartao.getId());
    }

    public void pagarFatura(String numeroCartao) throws Exception {
        Cartao cartao = cartaoDAO.buscarPorNumero(numeroCartao);
        if (cartao == null) {
            throw new EntidadeNaoEncontradaException("Cartão não encontrado!");
        }

        List<Transacao> abertas = transacaoDAO.listarAbertasPorCartao(cartao.getId());
        double totalPago = 0;

        for (Transacao t : abertas) {
            totalPago += t.getValor();
            transacaoDAO.atualizarStatus(t.getId(), "PAGA");
        }

        cartao.setLimiteDisponivel(cartao.getLimiteDisponivel() + totalPago);
        cartaoDAO.atualizar(cartao);
    }
}

