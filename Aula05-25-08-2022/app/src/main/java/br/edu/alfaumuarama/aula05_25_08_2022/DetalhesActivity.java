package br.edu.alfaumuarama.aula05_25_08_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.alfaumuarama.aula05_25_08_2022.datasources.DownloadImagem;

public class DetalhesActivity extends AppCompatActivity {

    TextView nome;
    ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        nome = findViewById(R.id.nome);
        imagem = findViewById(R.id.imagem);

        //capturando o caminho de tela utilizado para abrir esta tela
        Intent dadosRecebidos = getIntent();
        if (dadosRecebidos != null) {
            //capturando os dados recebidos no caminho de tela
            Bundle params = dadosRecebidos.getExtras();
            if (params != null) {
                //adicionando o nome do pokemon no texto da tela
                nome.setText(params.getString("nome"));

                //carregando a imagem da web no objeto imagem da tela
                new DownloadImagem(imagem).execute(params.getString("imagem"));
            }
        }
    }
}