package br.com.fiap.singleton;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static Connection conexao;

    private Conexao() {}

    public static Connection getConexao() throws Exception {
        if (conexao == null || conexao.isClosed()) {
            conexao = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/siscartao",
                    "root", "1234"
            );
        }
        return conexao;
    }
}

