package MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    //MÉTODO MUITO UTILIZADO!!!! DAO: Data Access Object.

        // 1 - Consulta
        public List<Aluno> list() {
            List<Aluno> alunos = new ArrayList<>(); //Retorna a lista de alunos criada.

            try (Connection conn = ConnectionFactory.getConnection()) {

                String sql = "SELECT * FROM aluno";  //Consulta realizada na tabela aluno.

                //Preparar statement com os parâmetros recebidos.
                PreparedStatement stmt = conn.prepareStatement(sql);

                //Executa consulta e armazena o retorno da consulta no objeto "rs".
                ResultSet rs = stmt.executeQuery();

                //Criar um objeto aluno e guardar na lista de alunos.
                while (rs.next()) { //vai para a primeira linha da coluna.
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    int idade = rs.getInt("idade");
                    String estado = rs.getString("estado");

                    alunos.add(new Aluno(

                            id,
                            nome,
                            idade,
                            estado
                    ));
                }
            } catch (SQLException e) {
                System.out.println("Listagem de alunos FALHOU!");
                e.printStackTrace();
            }

            //Retornar todos os alunos encontrados no banco de dados.
            return alunos;
        }




//-------------------------------------------------------------------


    // Consulta com filtro
    public Aluno getById(int id) {

        Aluno aluno = new Aluno(); //Objeto aluno para receber os valores do banco de dados.

        try (Connection conn = ConnectionFactory.getConnection()) {

            String sql = "SELECT * FROM aluno WHERE id = ?"; //Consulta realizada na tabela aluno por id.

            //Prepara statement com os parâmetros recebidos
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            //Executa consulta e armazena o retorno da consulta no objeto "rs".
            ResultSet rs = stmt.executeQuery();

            //Guardar valores retornados da tabela aluno no objeto aluno
            if (rs.next()){
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setEstado(rs.getString("estado"));
            }

        } catch (SQLException e) {
            System.out.println("Listagem de alunos falhou!");
            e.printStackTrace();
        }

        //Retorna aluno encontrado no banco de dados.
        return aluno;
    }


 //------------------------------------------------------------------------------------


    // Inserção de dados
    public void create(Aluno aluno) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            String sql = "INSERT INTO aluno(nome, idade, estado) VALUES(?, ?, ?)"; //Comando para inserção de dados.

            //Preparar statement com os parâmetros recebidos
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1 , aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3 , aluno.getEstado());

            //Executa inserção e armazena o número de linhas afetadas
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Inserção bem sucedida!. Foi adicionada " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Inserção falhou!");
            e.printStackTrace();
        }
    }



    //-----------------------------------------------------------------------------------

    // Deleta dados por id.
    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection()) {


            String sql = "DELETE FROM aluno WHERE id = ?"; //Comando para deletar uma linha.

            //Prepara statement com os parâmetros recebidos.
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1 , id);

            //Executa delete e armazena o número de linhas afetadas.
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Delete bem sucedida! Foi deletada " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Delete falhou!");
            e.printStackTrace();
        }
    }



    //-----------------------------------------------------------------------------


    // Atualização de dados.
    public void update(Aluno aluno) {
        try (Connection conn = ConnectionFactory.getConnection()) {

           String sql = "UPDATE aluno SET nome = ?, idade = ?, estado = ? WHERE id = ?"; //Comando para atualizar linhas.

            //Prepara statement com os parâmetros recebidos,
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getEstado());
            stmt.setInt(4, aluno.getId());

            //Executa atualização e armazena o número de linhas afetadas,
            int rowsAffected = stmt.executeUpdate();

            System.out.println("Atualização bem sucedida! Foi atualizada: " + rowsAffected + " linha");
        } catch (SQLException e) {
            System.out.println("Atualização falhou!");
            e.printStackTrace();
        }
    }

}
