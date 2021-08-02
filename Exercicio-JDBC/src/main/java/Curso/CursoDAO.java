package Curso;

import MySQL.Aluno;
import MySQL.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    // 1 - Consulta
    public List<Curso> list() {
        List<Curso> curso = new ArrayList<>();

        try (java.sql.Connection conn = ConnectionFactory.getConnection()) {

            String sql = "SELECT * FROM curso";


            PreparedStatement stmt = conn.prepareStatement(sql);


            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int duracao_horas = rs.getInt("duracao_horas");


                curso.add(new Curso(

                        id,
                        nome,
                        duracao_horas
                ));
            }
        } catch (SQLException e) {
            System.out.println("Listagem de cursos falhou!");
            e.printStackTrace();
        }


        return curso;
    }




//-------------------------------------------------------------------


    // Consulta com filtro
    public Curso getById(int id) {

        Curso curso = new Curso();

        try (java.sql.Connection conn = ConnectionFactory.getConnection()) {

            String sql = "SELECT * FROM curso WHERE id = ?";


            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);


            ResultSet rs = stmt.executeQuery();


            if (rs.next()){
                curso.setId(rs.getInt("id"));
                curso.setNome(rs.getString("nome"));
                curso.setDuracao_horas(rs.getInt("duracao_horas"));

            }

        } catch (SQLException e) {
            System.out.println("Listagem de cursos falhou!");
            e.printStackTrace();
        }


        return curso;
    }


    //------------------------------------------------------------------------------------


    // Inserção de dados
    public void create(Curso curso) {
        try (java.sql.Connection conn = ConnectionFactory.getConnection()) {

            String sql = "INSERT INTO curso(nome, duracao_horas) VALUES(?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , curso.getNome());
            stmt.setInt(2, curso.getDuracao_horas());


            int rowsAffected = stmt.executeUpdate();

            System.out.println("Inserção bem sucedida!. Foi adicionado " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Inserção falhou!");
            e.printStackTrace();
        }
    }



    //-----------------------------------------------------------------------------------

    // Deleta dados por id.
    public void delete(int id) {
        try (java.sql.Connection conn = ConnectionFactory.getConnection()) {


            String sql = "DELETE FROM curso WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1 , id);

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Delete bem sucedido! Foi deletado " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Delete falhou!");
            e.printStackTrace();
        }
    }



    //-----------------------------------------------------------------------------


    // Atualização de dados.
    public void update(Curso curso) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            String sql = "UPDATE curso SET nome = ?, duracao_horas = ? WHERE id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(3, curso.getId());
            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getDuracao_horas());


            int rowsAffected = stmt.executeUpdate();

            System.out.println("Atualização bem sucedida! Foi atualizado: " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Atualização falhou!");
            e.printStackTrace();
        }
    }
}
