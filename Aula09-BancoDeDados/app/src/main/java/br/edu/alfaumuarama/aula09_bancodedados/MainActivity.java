package br.edu.alfaumuarama.aula09_bancodedados;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import br.edu.alfaumuarama.aula09_bancodedados.datasources.TbAluno;
import br.edu.alfaumuarama.aula09_bancodedados.models.Aluno;

public class MainActivity extends ListActivity {

    ArrayList<Aluno> listaAlunos;

    Button btnNovo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNovo = findViewById(R.id.btnNovo);
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //abrindo a tela de cadastro para adicionar um novo aluno
                startActivity(new Intent(MainActivity.this, CadastroActivity.class));
            }
        });
    }

    //onResume -> executado toda vez que a tela é apresentada
    @Override
    protected void onResume() {
        super.onResume();

        //carrega a listagem de alunos cadastrados na tela
        buscarAlunos();
    }

    private void buscarAlunos() {
        TbAluno tbAluno = new TbAluno(MainActivity.this);
        listaAlunos = tbAluno.buscar();

        ListAdapter adapter = new SimpleAdapter(this,
            listaToMap(listaAlunos), //lista com os dados no formato HashMap
            R.layout.listview_modelo, //layout de modelo da celula/item da lista
            new String[] { "nome", "ra", "cidade" }, //campos da lista dos dados
            new int[] { R.id.txtNome, R.id.txtRa, R.id.txtCidade } //campos do layout
        );

        setListAdapter(adapter);
    }

    private ArrayList<HashMap<String,String>> listaToMap(ArrayList<Aluno> lista) {
        ArrayList<HashMap<String,String>> listaRetorno = new ArrayList<>();
        for (Aluno aluno : lista) {
            listaRetorno.add(aluno.toMap());
        }
        return  listaRetorno;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //carregando os dados do aluno selecionado na lista da tela
        Aluno aluno = listaAlunos.get(position);

        //carregando os dados do aluno nos parametros que vao para a outra tela
        Bundle parametros = new Bundle();
        parametros.putInt("id", aluno.id);
        parametros.putString("nome", aluno.nome);
        parametros.putString("ra", aluno.ra);
        parametros.putString("cidade", aluno.cidade);

        //criando o caminho de tela para abrir a tela de cadastro
        Intent telaCadastro = new Intent(MainActivity.this, CadastroActivity.class);

        //adicionando os parametros no caminho de tela
        telaCadastro.putExtras(parametros);

        //abrindo a tela de cadastro
        startActivity(telaCadastro);
    }
}