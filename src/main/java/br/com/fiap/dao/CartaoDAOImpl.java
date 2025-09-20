package br.com.fiap.dao;

import br.com.fiap.model.Cartao;
import br.com.fiap.singleton.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaoDAOImpl implements CartaoDAO {

    @Override
    public void inserir(Cartao cartao) throws Exception {
        String sql = "INSERT INTO cartao (numero, bandeira, limite_total, limite_disponivel, cliente_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cartao.getNumero());
            stmt.setString(2, cartao.getBandeira());
            stmt.setDouble(3, cartao.getLimiteTotal());
            stmt.setDouble(4, cartao.getLimiteDisponivel());
            stmt.setInt(5, cartao.getClienteId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Cartao buscarPorNumero(String numero) throws Exception {
        String sql = "SELECT * FROM cartao WHERE numero = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numero);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cartao(
                        rs.getInt("id"),
                        rs.getString("numero"),
                        rs.getString("bandeira"),
                        rs.getDouble("limite_total"),
                        rs.getDouble("limite_disponivel"),
                        rs.getInt("cliente_id")
                );
            }
        }
        return null;
    }

    @Override
    public void atualizar(Cartao cartao) throws Exception {
        String sql = "UPDATE cartao SET limite_disponivel = ? WHERE id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, cartao.getLimiteDisponivel());
            stmt.setInt(2, cartao.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Cartao> listarPorCliente(int clienteId) throws Exception {
        List<Cartao> lista = new ArrayList<>();
        String sql = "SELECT * FROM cartao WHERE cliente_id = ?";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Cartao(
                        rs.getInt("id"),
                        rs.getString("numero"),
                        rs.getString("bandeira"),
                        rs.getDouble("limite_total"),
                        rs.getDouble("limite_disponivel"),
                        rs.getInt("cliente_id")
                ));
            }
        }
        return lista;
    }
}

