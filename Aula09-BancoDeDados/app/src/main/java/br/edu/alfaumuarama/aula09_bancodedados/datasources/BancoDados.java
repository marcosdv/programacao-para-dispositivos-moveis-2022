package br.edu.alfaumuarama.aula09_bancodedados.datasources;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoDados {
    private SQLiteDatabase _db;

    //criando um singleton para esta classe
    private static final BancoDados bancoDados = new BancoDados();
    public static BancoDados getInstance() {
        return bancoDados;
    }

    public SQLiteDatabase getDb() {
        return _db;
    }

    public void abrirBanco(Context context) {
        if (_db != null) {
            if (_db.isOpen() == false)
                criarConexao(context);
        }
        else { //se o banco estiver nulo, cria o banco e abre a conexao
            criarConexao(context);
        }
    }

    private void criarConexao(Context context) {
        _db = context.openOrCreateDatabase("bancoDados.db", Context.MODE_PRIVATE, null);
    }

    public void fecharBanco() {
        if (_db != null)
            _db.close();
    }

    public void executarSQL(String sql) {
        try {
            _db.execSQL(sql);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void adicionarNovaColuna(String tabela, String coluna,
                                    String tipoDados, String valorPadrao) {
        if (existeColuna(tabela, coluna) == false) {
            /*
            String padrao = "";
            if (valorPadrao.isEmpty() == false) {
                padrao = " DEFAULT " + valorPadrao;
            }
            */
            String padrao = valorPadrao.isEmpty() ? "" : " DEFAULT " + valorPadrao;

            BancoDados.getInstance().executarSQL(
                "ALTER TABLE " + tabela +
                " ADD COLUMN " + coluna + " " + tipoDados + padrao
            );
        }
    }

    private boolean existeColuna(String tabela, String coluna) {
        //fazendo um SELECT na tabela com limit zero para nao carregar os dados,
        // somente a estrutura da tabela
        Cursor cursor = BancoDados.getInstance().getDb()
            .rawQuery("SELECT * FROM " + tabela + " LIMIT 0", null);

        //verifica se a coluna existe, se existir o getColumnIndex retorna o numero dela
        //  senao existir o getColumnIndex retorna -1
        /*
        if (cursor.getColumnIndex(coluna) >= 0) {
            return true;
        }
        else {
            return false;
        }
        */
        return (cursor.getColumnIndex(coluna) >= 0);
    }
}