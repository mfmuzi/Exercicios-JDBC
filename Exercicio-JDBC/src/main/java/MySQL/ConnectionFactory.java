package MySQL;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    //Classe para conexão com o BD

    private ConnectionFactory() {
        throw new UnsupportedOperationException();
    }

    public static Connection getConnection() {


        // Objeto conexão (irá receber uma conexão após executar os passos abaixo)
        Connection connection = null;

        // Arquivo de propriedades para pegar parâmetros de conexão.
        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("connection.properties")) {

        // Parâmetros para se conectar ao BD MySQL.
            Properties prop = new Properties();
            prop.load(input);

            String driver = prop.getProperty("jdbc.driver");
            String dataBaseAddress = prop.getProperty("db.address");
            String dataBaseName = prop.getProperty("db.name");
            String user = prop.getProperty("db.user.login");
            String password = prop.getProperty("db.user.password");

        // String de conexão.
            StringBuilder sb = new StringBuilder("jdbc:")
                    .append(driver).append("://")
                    .append(dataBaseAddress).append("/")
                    .append(dataBaseName);

            String connectionUrl = sb.toString(); //sb.toString() == "jdbc:mysql://localhost/digital_innovation_one"

        // Conexão usando o DriverManager.
            try {
                connection = DriverManager.getConnection(connectionUrl, user, password);
            } catch (SQLException e) {
                System.out.println("Falha ao criar conexão");
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            System.out.println("Falha ao carregar arquivos de propriedades");
            e.printStackTrace();
        }

        return connection;
    }
}
