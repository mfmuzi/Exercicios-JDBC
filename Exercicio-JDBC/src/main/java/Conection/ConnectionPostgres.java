package Conection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConnectionPostgres {
    public static void main(String[] args) {

        String driver = "org.postgresql.Driver";
        String dataBaseAddress = "localhost";
        String dataBaseName = "digital_innovation_one";
        String user = "postgres";
        String password = "";

        // Construção da string de conexão.
        StringBuilder sb = new StringBuilder("jdbc:")
                .append(driver).append("://")
                .append(dataBaseAddress).append("/")
                .append(dataBaseName);

        String connectionUrl = "jdbc:postgresql://localhost:5432/digital_innovation_one";

        try (Connection conn = DriverManager.getConnection(connectionUrl, "postgres", "")) {
            System.out.println("SUCESSO ao se conectar ao banco Postgres!");

        } catch (Exception e) {
            System.out.println("FALHA ao se conectar ao banco Postgres!");
            e.printStackTrace();
        }


    }
}
