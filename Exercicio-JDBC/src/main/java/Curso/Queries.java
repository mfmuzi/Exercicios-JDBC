package Curso;

import MySQL.Aluno;
import MySQL.AlunoDAO;

import java.util.List;

public class Queries {

    public static void main(String[] args) {

        CursoDAO cursoDAO = new CursoDAO();

        // Consulta.
        List<Curso> cursos = cursoDAO.list();

        cursos.stream().forEach(System.out::println);



        // Consulta com filtro.
        Curso cursoParaConsulta = cursoDAO.getById(2);

        System.out.println(cursoParaConsulta);



        // Inserção de dados.
        Curso cursoParaInsercao = new Curso(
                "Quimica Forense",
                30
        );

        cursoDAO.create(cursoParaInsercao);
        cursos.stream().forEach(System.out::println);


        // Deletar dados por id.
        cursoDAO.list().stream().forEach(System.out::println);

        cursoDAO.delete(3);

        cursoDAO.list().stream().forEach(System.out::println);



        // Atualizar dados.
        cursoDAO.list().stream().forEach(System.out::println);

        Curso cursoParaAtualizar = cursoDAO.getById(4);
        cursoParaAtualizar.setNome("Bioquimica");
        cursoParaAtualizar.setDuracao_horas(60);


        cursoDAO.update(cursoParaAtualizar);

        cursoDAO.list().stream().forEach(System.out::println);


    }

}