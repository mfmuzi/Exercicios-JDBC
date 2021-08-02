package Conection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionJDBC {


    //Formato atual não precisa fechar a conexão.
    public static void main(String[] args) {

        //Conexão com o BD sem especificação dos parâmetros.
/*        String urlConnection = "jdbc:mysql://localhost/digital_innovation_one";


         try (Connection conn = DriverManager.getConnection(urlConnection, "root", "9707")){
               System.out.println("Sucesso ao se conectar ao banco MySQL!");

             } catch (Exception e) {
                   System.out.println("Falha ao se conectar ao banco MySQL!");
        }
}

}*/

        // Conexão com o BD com a especificação dos parâmetros (melhor prática).
        String driver = "mysql";
        String dataBaseAddress = "localhost";
        String dataBaseName = "digital_innovation_one";
        String user = "root";
        String password = "";

        // Construção da string de conexão.
        StringBuilder sb = new StringBuilder("jdbc:")
                .append(driver).append("://")
                .append(dataBaseAddress).append("/")
                .append(dataBaseName);

        String connectionUrl = sb.toString(); //sb.toString() == "jdbc:mysql://localhost/digital_innovation_one"

        try (Connection conn = DriverManager.getConnection(connectionUrl, "root", "")) {
            System.out.println("SUCESSO ao se conectar ao banco MySQL!");

        } catch (Exception e) {
            System.out.println("FALHA ao se conectar ao banco MySQL!");
            e.printStackTrace();
        }


}
}
