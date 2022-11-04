package br.edu.alfaumuarama.aula13_03_11_2022;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class EnviarDadosWeb extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        //criando o link da API
        String link = "http://www.marcosdiasvendramini.com.br/Set/Aula.aspx?texto=";

        //Pegando o parametro que foi enviado para a thread
        String parametro = strings[0];

        //juntando o parametro com o link
        link += parametro;

        try {
            //criando a URL apartir do link montado acima
            URL url = new URL(link);

            //abrindo a conexao apartir da URL
            URLConnection connection = url.openConnection();

            //capturando o retorno que veio do link da API
            InputStreamReader input = new InputStreamReader(connection.getInputStream());

            //criando o leitor(reader) dos dados retornados pela API
            BufferedReader reader = new BufferedReader(input);

            //retornando a linha/string resultante da chamada da API
            return reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        return null;
    }
}