package br.edu.alfaumuarama.aula03_11_08_2022;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Tela2Activity extends AppCompatActivity {

    Button btnFechar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //metodo que informa a classe java, qual o arquivo
        //  de tela (XML) ele vai controlar
        setContentView(R.layout.activity_tela2);

        btnFechar = findViewById(R.id.btnVoltar);

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chamando o evento do botao de volta do Android
                onBackPressed();
            }
        });
    }
}