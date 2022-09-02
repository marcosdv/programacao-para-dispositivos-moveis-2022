package br.edu.alfaumuarama.aula05_25_08_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import br.edu.alfaumuarama.aula05_25_08_2022.datasources.BuscarDadosWeb;

public class MainActivity extends ListActivity {

    private ArrayList<HashMap<String,String>> listaDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            listaDados = new BuscarDadosWeb().execute(Config.urlApi).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String texto = listaDados.get(0).get("nome");
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show();

        //criando o adapter que ir configrar como os dados sao carregados
        ListAdapter adapter = new SimpleAdapter(
            this,                      //contexto que o onjeto esta
            listaDados,                //local onde estao os dados
            R.layout.listview_modelo,  //item que servira de modelo para cada celula
            new String[] { "nome" },   //quais campos dos dados serao carregados
            new int[] { R.id.txtNome } //objetos de tela onde dados vao ser carregados
        );

        //adicionando o adaptador criado na listView da tela
        setListAdapter(adapter);
    }
}