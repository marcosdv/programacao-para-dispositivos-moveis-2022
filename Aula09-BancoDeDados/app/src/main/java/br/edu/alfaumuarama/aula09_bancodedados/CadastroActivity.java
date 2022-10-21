package br.edu.alfaumuarama.aula09_bancodedados;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.edu.alfaumuarama.aula09_bancodedados.datasources.TbAluno;
import br.edu.alfaumuarama.aula09_bancodedados.models.Aluno;

public class CadastroActivity extends AppCompatActivity {

    int id = 0;
    EditText edtNome, edtRA, edtCidade;
    Button btnSalvar, btnExcluir, btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtNome = findViewById(R.id.edtNome);
        edtRA = findViewById(R.id.edtRA);
        edtCidade = findViewById(R.id.edtCidade);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnExcluir = findViewById(R.id.btnExcluir);
        btnVoltar = findViewById(R.id.btnVoltar);

        //capturando o caminho de tela
        Intent caminhoTela = getIntent();
        if (caminhoTela != null) {
            //capturando os dados que foram enviados da tela principal
            Bundle parametros = caminhoTela.getExtras();

            //verifica se veio algum parametro, se sim, é edicao
            if (parametros != null) {
                id = parametros.getInt("id");
                edtNome.setText(parametros.getString("nome"));
                edtRA.setText(parametros.getString("ra"));
                edtCidade.setText(parametros.getString("cidade"));
            }
            else { //senao veio parametros é um novo aluno
                //oculta o botao de excluir quando estiver inserindo
                btnExcluir.setVisibility(View.INVISIBLE);
            }
        }

        //onBackPressed -> chama o evento do botao voltar do android
        btnVoltar.setOnClickListener(view -> onBackPressed());

        btnSalvar.setOnClickListener(view -> {
            //instanciando a classe aluno com os dados da tela
            Aluno aluno = new Aluno();
            aluno.id = id;
            aluno.nome = edtNome.getText().toString();
            aluno.ra = edtRA.getText().toString();
            aluno.cidade = edtCidade.getText().toString();

            //salvando os dados do aluno no banco de dados
            TbAluno tbAluno = new TbAluno(CadastroActivity.this);
            tbAluno.gravar(aluno);

            //voltando para a tela inicial
            onBackPressed();
        });

        btnExcluir.setOnClickListener(view -> {
            //criando o alerta de exclusao
            AlertDialog.Builder alerta = new AlertDialog.Builder(CadastroActivity.this);
            alerta.setTitle("Atenção"); //titulo do alerta
            alerta.setMessage("Deseja realmente excluir este aluno?"); //mensagem

            //adicionando botao do NAO que cancela a acao
            alerta.setNegativeButton("Não", null);

            //adicionando botao do SIM que confirma a exclusao do aluno
            alerta.setPositiveButton("Sim", (dialogInterface, i) -> {
                //excluindo o aluno selecionado
                TbAluno tbAluno = new TbAluno(CadastroActivity.this);
                tbAluno.excluir(id);

                //voltando para a tela inicial
                onBackPressed();
            });
        });
    }
}