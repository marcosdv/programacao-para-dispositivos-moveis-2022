package br.edu.alfaumuarama.aula09_bancodedados;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
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
                Aluno aluno = new Aluno();
                aluno.nome = "Nome";
                aluno.ra = "123";
                aluno.cidade = "Umuarama";

                TbAluno tbAluno = new TbAluno(MainActivity.this);
                tbAluno.gravar(aluno);

                buscarAlunos();
            }
        });
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
}