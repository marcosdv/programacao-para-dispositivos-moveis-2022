package br.edu.alfaumuarama.aula09_bancodedados.datasources;

import android.content.ContentValues;
import android.content.Context;

import br.edu.alfaumuarama.aula09_bancodedados.models.Aluno;

public class TbAluno {

    public TbAluno(Context context) {
        //abrindo/criando a conexao com o banco de dados
        BancoDados.getInstance().abrirBanco(context);

        //montando o SCRIPT para criar a tabela no banco
        String sql = "CREATE TABLE IF NOT EXISTS tbAluno (" +
                     " id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                     " nome TEXT, ra TEXT, cidade TEXT)";

        //criando a tabela no banco, caso a mesma nao exista
        BancoDados.getInstance().executarSQL(sql);
    }

    private void inserir(Aluno aluno) {
        //String sql = "INSERT INTO tbAluno (nome, ra, cidade) VALUES (" +
        //        aluno.nome + ", " + aluno.ra + ", " + aluno.cidade + ")";
        //BancoDados.getInstance().executarSQL(sql);

        BancoDados.getInstance().getDb().insert("tbAluno", null, aluno.toDados());
    }

    private void alterar(Aluno aluno) {
        BancoDados.getInstance().getDb().update(
            "tbAluno", //nome da tabela
            aluno.toDados(), //dados a serem atualizados
            "id = ?", //condicao WHERE para executar o UPDATE
            new String[] { String.valueOf(aluno.id) } //parametros do WHERE
        );
    }
}
