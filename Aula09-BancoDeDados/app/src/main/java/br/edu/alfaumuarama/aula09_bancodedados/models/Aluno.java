package br.edu.alfaumuarama.aula09_bancodedados.models;

import android.content.ContentValues;

import java.util.HashMap;

public class Aluno {
    public int id;
    public String nome;
    public String ra;
    public String cidade;

    public ContentValues toDados() {
        ContentValues dados = new ContentValues();
        dados.put("id", id);
        dados.put("nome", nome);
        dados.put("ra", ra);
        dados.put("cidade", cidade);
        return dados;
    }

    public HashMap<String, String> toMap() {
        HashMap<String, String> dados = new HashMap<>();
        dados.put("id", String.valueOf(id));
        dados.put("nome", nome);
        dados.put("ra", ra);
        dados.put("cidade", cidade);
        return dados;
    }
}