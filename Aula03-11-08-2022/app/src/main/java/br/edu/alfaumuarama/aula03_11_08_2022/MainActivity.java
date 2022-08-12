package br.edu.alfaumuarama.aula03_11_08_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnTela2, btnTela3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //vinculando os objetos da tela (XML) com os do JAVA
        btnTela2 = findViewById(R.id.btnTela2);
        btnTela3 = findViewById(R.id.btnTela3);

        //criando o listener do clique do botao
        btnTela2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //criando um caminho para a segunda tela (Tela2)
                Intent tela02 = new Intent(
                    MainActivity.this, Tela2Activity.class);

                //abrindo a tela2
                startActivity(tela02);
            }
        });

        btnTela3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(
                    MainActivity.this, Tela3Activity.class));
            }
        });
    }

}