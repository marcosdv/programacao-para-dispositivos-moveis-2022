package br.edu.alfaumuarama.aula05_25_08_2022.datasources;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class DownloadImagem extends AsyncTask<String, Void, Bitmap> {

    ImageView imagem;

    public DownloadImagem(ImageView imagem) {
        this.imagem = imagem;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String link = strings[0];
        Bitmap bitmap = null;

        try {
            //criando a URL a partir do link recebido
            URL url = new URL(link);

            //buscando os dados da URL e salvando em memoria
            InputStream inputStream = url.openStream();

            //criando um bitmap a partir dos dados salvos em memoria
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        //carregando o bitmap baixado acima, no objeto de imagem da tela
        imagem.setImageBitmap(bitmap);
    }
}