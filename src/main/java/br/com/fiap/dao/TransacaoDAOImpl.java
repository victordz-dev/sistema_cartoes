package br.com.fiap.dao;

import br.com.fiap.model.Transacao;
import br.com.fiap.singleton.Conexao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransacaoDAOImpl implements TransacaoDAO {

    @Override
    public void inserir(Transacao t) throws Exception {
        String sql = "INSERT INTO transacao (cartao_id, data, descricao, valor, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, t.getCartaoId());
            stmt.setTimestamp(2, Timestamp.valueOf(t.getData()));
            stmt.setString(3, t.getDescricao());
            stmt.setDouble(4, t.getValor());
            stmt.setString(5, t.getStatus());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Transacao> listarPorCartao(int cartaoId) throws Exception {
        List<Transacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacao WHERE cartao_id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartaoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        }
        return lista;
    }

    @Override
    public List<Transacao> listarAbertasPorCartao(int cartaoId) throws Exception {
        List<Transacao> lista = new ArrayList<>();
        String sql = """
            SELECT * FROM transacao 
            WHERE cartao_id = ? 
              AND status = 'ABERTA'
              AND MONTH(data) = MONTH(CURDATE())
              AND YEAR(data) = YEAR(CURDATE())
        """;
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cartaoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(mapResultSet(rs));
            }
        }
        return lista;
    }

    @Override
    public void atualizarStatus(int transacaoId, String status) throws Exception {
        String sql = "UPDATE transacao SET status = ? WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, transacaoId);
            stmt.executeUpdate();
        }
    }

    // Helper para reaproveitar mapeamento
    private Transacao mapResultSet(ResultSet rs) throws SQLException {
        return new Transacao(
                rs.getInt("id"),
                rs.getInt("cartao_id"),
                rs.getTimestamp("data").toLocalDateTime(),
                rs.getString("descricao"),
                rs.getDouble("valor"),
                rs.getString("status")
        );
    }
}
