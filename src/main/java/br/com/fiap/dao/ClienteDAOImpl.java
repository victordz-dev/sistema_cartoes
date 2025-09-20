package br.com.fiap.dao;

import br.com.fiap.model.Cliente;
import br.com.fiap.singleton.Conexao;
import java.sql.*;
import java.util.*;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public void inserir(Cliente cliente) throws Exception {
        String sql = "INSERT INTO cliente (cpf, nome, email, renda) VALUES (?, ?, ?, ?)";
        Connection conn = Conexao.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cliente.getCpf());
        stmt.setString(2, cliente.getNome());
        stmt.setString(3, cliente.getEmail());
        stmt.setDouble(4, cliente.getRenda());
        stmt.executeUpdate();
    }

    @Override
    public Cliente buscarPorCpf(String cpf) throws Exception {
        String sql = "SELECT * FROM cliente WHERE cpf = ?";
        Connection conn = Conexao.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpf);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Cliente(
                    rs.getInt("id"),
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getDouble("renda")
            );
        }
        return null;
    }

    @Override
    public List<Cliente> listar() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        Connection conn = Conexao.getConexao();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            clientes.add(new Cliente(
                    rs.getInt("id"),
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getDouble("renda")
            ));
        }
        return clientes;
    }
}

