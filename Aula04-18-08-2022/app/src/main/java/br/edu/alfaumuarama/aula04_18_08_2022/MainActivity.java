package br.edu.alfaumuarama.aula04_18_08_2022;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtUrl;
    Button btnAbrir;
    WebView webSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUrl = findViewById(R.id.edtUrl);
        btnAbrir = findViewById(R.id.btnAbrir);
        webSite = findViewById(R.id.webSite);

        webSite.loadUrl("https://www.alfaumuarama.edu.br/fau/");

        btnAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //parseInt - transforma o texto do edtUrl em Integer
                int numeroPokemon = Integer.parseInt(edtUrl.getText().toString());

                if (numeroPokemon <= 0) {
                    mostrarMensagem(getString(R.string.numero_poke_invalido));
                    return;
                }

                if (numeroPokemon > 151) {
                    mostrarMensagem(getString(R.string.apenas_poke_gen_1));
                    return;
                }

                String link = "https://pokemon.gameinfo.io/pt-br/pokemon/";
                webSite.loadUrl(link + edtUrl.getText());
            }
        });
    }

    private void mostrarMensagem(String texto) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle(getString(R.string.atencao));
        alerta.setMessage(texto);
        alerta.setNeutralButton("OK", null);
        alerta.show();
    }
}