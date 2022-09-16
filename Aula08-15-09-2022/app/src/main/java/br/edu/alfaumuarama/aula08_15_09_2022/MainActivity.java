package br.edu.alfaumuarama.aula08_15_09_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtNome;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //findViewById - busca no XML o objeto e salva no objeto do java
        edtNome = findViewById(R.id.edtNome);
        btnEnviar = findViewById(R.id.btnEnviar);

        //criando um ouvinte para o clique do botao
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviar();
            }
        });
    }

    private void enviar() {
        if (edtNome.getText().toString().isEmpty()) {
            mostrarMensagem("Campo nome é obrigatório!");
            return;
        }

        mostrarMensagem("Seja bem vindo " + edtNome.getText());
    }

    private void mostrarMensagem(String textoMensagem) {
        //criando um obejeto para exibir o alerta
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        //adicionando a mensagem de texto do alerta
        alerta.setMessage(textoMensagem);

        //adicionando o titulo do alerta
        alerta.setTitle("Atenção");

        //adicionando o botão de OK para fechar o alerta
        alerta.setNeutralButton("OK", null);

        //exibindo a mensagem de alerta na tela
        alerta.show();
    }
}