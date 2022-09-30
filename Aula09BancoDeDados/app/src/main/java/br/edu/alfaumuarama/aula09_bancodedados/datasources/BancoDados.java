package br.edu.alfaumuarama.aula09_bancodedados.datasources;

import android.content.Context;
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
}