package br.edu.alfaumuarama.aula02_04_08_2022;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtEtanol, edtGasolina;
    Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //diz para a classe JAVA qual tela (XML) ela vai controlar
        setContentView(R.layout.activity_main);

        //vinculando os campos da tela (XML) com os objetos do JAVA
        edtEtanol = findViewById(R.id.edtEtanol);
        edtGasolina = findViewById(R.id.edtGasolina);
        btnCalcular = findViewById(R.id.btnCalcular);

        //criando o click do botao
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
            }
        });
    }

    private void calcular() {
        double valorEtanol =
            Double.parseDouble(edtEtanol.getText().toString());

        double valorGasolina =
            Double.parseDouble(edtGasolina.getText().toString());

        double resultado = valorEtanol / valorGasolina;

        if (resultado <= 0.7) {
            mostrarMensagem("Resultado",
                    "Abastecer com etanol");
        }
        else {
            mostrarMensagem("Resultado",
                    "Abastecer com gasolina");
        }
    }

    private void mostrarMensagem(
                    String titulo, String texto) {
        //criando uma mensagem do tipo TOAST
        Toast.makeText(this,
            texto, Toast.LENGTH_LONG).show();

        //Criando o objeto de mensagem do tipo alerta modal
        AlertDialog.Builder alerta =
            new AlertDialog.Builder(this);

        //adicionando o titulo na mensagem
        alerta.setTitle(titulo);

        //adicionando o texto da mensagem
        alerta.setMessage(texto);

        //adicionando um botao de OK para fechar a mensagem
        alerta.setNeutralButton("OK", null);

        //exibindo a mensagem na tela
        alerta.show();
    }
}