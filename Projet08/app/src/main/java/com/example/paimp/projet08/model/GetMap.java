package com.example.paimp.projet08.model;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.paimp.projet08.R;
import com.example.paimp.projet08.controller.Game;
import com.example.paimp.projet08.controller.SelectClasse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Paimp on 18-12-17.
 */

public class GetMap extends AsyncTask<String , Void , String> {
    Game activite;

    public GetMap(Game activite){
        this.activite=activite;
    }

    @Override
    protected String doInBackground(String...data) {
        String url_map = "http://10.0.2.2:8080/android/map.php";
        String reponse="";
        try{
            URL url = new URL(url_map);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            ///////////////// Propriété de ma connexion  /////////////////
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            ///////////////// Connexion  /////////////////
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200)
            {
                /*
                ///////////////// requête  /////////////////
                connection.setRequestProperty("Content-Type", "text/plain");
                connection.setRequestProperty("charset", "utf-8");

                ///////////////// réponse  /////////////////
                InputStream inputStream;
                inputStream=connection.getInputStream();
                InputStreamReader inputStreamReader;
                inputStreamReader=new InputStreamReader(inputStream, "UTF-8");
                Scanner scanner = new Scanner(inputStreamReader);
                reponse = scanner.toString();
                */
                reponse = "map chargée";
            }
            else
            {
                reponse = "map non chargée";
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return reponse;
    }

    @Override
    protected void onPostExecute(String result) {
        // Callback
        if(result != null){
            Toast.makeText(activite, result,Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(activite, R.string.login_failed,Toast.LENGTH_SHORT).show();
        }
    }
}
