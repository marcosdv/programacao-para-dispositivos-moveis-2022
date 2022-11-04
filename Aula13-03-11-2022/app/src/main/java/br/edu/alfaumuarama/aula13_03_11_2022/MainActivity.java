package br.edu.alfaumuarama.aula13_03_11_2022;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    EditText edtTexto;
    TextView txtResultado;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTexto = findViewById(R.id.edtTexto);
        txtResultado = findViewById(R.id.txtResultado);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(view -> {
            //validando para nao aceitar o campo de texto vazio
            if (edtTexto.getText().toString().trim().isEmpty()) {
                Toast.makeText(MainActivity.this,
                    "Digite o campo texto", Toast.LENGTH_LONG).show();
                return;
            }

            //Instanciando a classe da thread
            EnviarDadosWeb enviar = new EnviarDadosWeb();

            //Envia o parametro da tela para a API Web e pega o resultado
            try {
                String resultado = enviar.execute(edtTexto.getText().toString()).get();

                txtResultado.setText(resultado);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}