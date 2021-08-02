package MySQL;

import java.util.List;

public class QueriesExecution {

    public static void main(String[] args) {

        AlunoDAO alunoDAO = new AlunoDAO();

        // Consulta.
        List<Aluno> alunos = alunoDAO.list();

        alunos.stream().forEach(System.out::println);



        // Consulta com filtro.
        Aluno alunoParaConsulta = alunoDAO.getById(2);

        System.out.println(alunoParaConsulta);



        // Inserção de dados.
        Aluno alunoParaInsercao = new Aluno(
                "Matheus",
                43,
                "SP"
        );

        alunoDAO.create(alunoParaInsercao);
        alunos.stream().forEach(System.out::println);



        // Deletar dados por id.
        alunoDAO.list().stream().forEach(System.out::println);

        alunoDAO.delete(4);

        alunoDAO.list().stream().forEach(System.out::println);



        // Atualizar dados.
        alunoDAO.list().stream().forEach(System.out::println);

        Aluno alunoParaAtualizar = alunoDAO.getById(3);
        alunoParaAtualizar.setNome("Joaquim");
        alunoParaAtualizar.setIdade(18);
        alunoParaAtualizar.setEstado("RS");

        alunoDAO.update(alunoParaAtualizar);

        alunoDAO.list().stream().forEach(System.out::println);
    }

}


