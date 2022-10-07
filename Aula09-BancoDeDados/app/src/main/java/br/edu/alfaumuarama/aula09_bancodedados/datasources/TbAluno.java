package br.edu.alfaumuarama.aula09_bancodedados.datasources;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

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

    public void excluir(int id) {
        BancoDados.getInstance().getDb().delete(
            "tbAluno", //nome da tabela
            "id = ?", //condicao WHERE para executar o UPDATE
            new String[] { String.valueOf(id) } //parametros do WHERE
        );
    }

    public void gravar(Aluno aluno) {
        if (aluno.id > 0) { //se o id do aluno veio preenchido
            alterar(aluno); // altera o aluno enviado
        }
        else { //senao, insere um novo aluno
            inserir(aluno);
        }
    }

    public ArrayList<Aluno> buscar() {
        /*
        Cursor cursor = BancoDados.getInstance().getDb().rawQuery(
            "SELECT * FROM tbAluno", //SELECT para buscar os dados
            null //parametros utilizados no WHERE
        );
        */
        Cursor cursor = BancoDados.getInstance().getDb().query(
            "tbAluno", //nome da tabela
            new String[] { "id", "nome", "ra", "cidade"}, //colunas retornadas
            "", //condicao - filtro - WHERE
            null, //parametros da condicao acima
            null, //group by
            null, //having
            "nome", //ORDER BY
            null //LIMIT
        );

        return retornarLista(cursor);
    }

    private ArrayList<Aluno> retornarLista(Cursor cursor) {
        ArrayList<Aluno> listaRetorno = new ArrayList<>();

        if (cursor.getCount() > 0) { //verifica se existe dados no cursor
            //pegando o indice dos campos dentro do SELECT
            int indiceCampoId = cursor.getColumnIndex("id");
            int indiceCampoNome = cursor.getColumnIndex("nome");
            int indiceCampoRa = cursor.getColumnIndex("ra");
            int indiceCampoCidade = cursor.getColumnIndex("cidade");

            cursor.moveToFirst(); //mover o cursor para a primeira posicao

            for (int i = 0; i < cursor.getCount(); i++) {
                Aluno aluno = new Aluno();
                aluno.id = cursor.getInt(indiceCampoId);
                aluno.nome = cursor.getString(indiceCampoNome);
                aluno.ra = cursor.getString(indiceCampoRa);
                aluno.cidade = cursor.getString(indiceCampoCidade);

                listaRetorno.add(aluno); //adicionando o aluno do cursor no retorno

                cursor.moveToNext(); //movendo o cursor para o proiximo registro
            }
        }

        return listaRetorno;
    }
}