package com.example.paimp.projet08.model;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.JsonReader;

import com.example.paimp.projet08.controller.Game;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Paimp on 18-12-17.
 */

public class GetMap extends AsyncTask<MapJeu, Void, MapJeu> {
    Game activite;
    String result="";
    List<Integer> tabMap = new ArrayList<>();
    int[][]tabMapJeu = new int [12][13];
    int ligne,col,valeur;

    public GetMap(Game activite){
        this.activite=activite;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected MapJeu doInBackground(MapJeu...map) {
        String url_map = "http://10.0.2.2:8080/android/map.php";

        try{
            URL url = new URL(url_map);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            ///////////////// Propriété de ma connexion  /////////////////
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            ///////////////// requête  /////////////////
            connection.setRequestProperty("Content-Type", "application/json");
            //connection.setRequestProperty("Content-Type", "text/plain");
            connection.setRequestProperty("charset", "utf-8");

            ///////////////// Connexion  /////////////////
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode == 200)
            {
                ///////////////// réponse  /////////////////
                InputStream inputStream;
                inputStream=connection.getInputStream();
                InputStreamReader inputStreamReader;
                inputStreamReader = new InputStreamReader(inputStream, "UTF-8");

                ///////////////////// Récupération avec Scanner ///////////////////////
                /*
                Scanner scanner = new Scanner(inputStreamReader);
                //scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    result += scanner.next();
                }
                scanner.close();
                System.out.println(result);
                */
                ///////////////////// Récupération du JSON ///////////////////////

                JsonReader jsonReader = new JsonReader(inputStreamReader);
                jsonReader.beginArray();
                System.out.println("//////// Début tableau 2 dim ////////");
                ligne=0;
                while (jsonReader.hasNext()) {
                    jsonReader.beginArray();
                    System.out.println("///// Début du tableau " + (ligne+1) + " à 1 dim //////  ");
                    col=0;
                    while (jsonReader.hasNext()) {
                        valeur = jsonReader.nextInt();
                        System.out.println("Ligne => " + ligne + " Colonne => " + col + " Valeur => "+valeur);
                        tabMapJeu[ligne][col]=valeur;
                        col++;
                    }
                    System.out.println("///// Fin du tableau " + (ligne+1) + " à 1 dim //////  ");
                    jsonReader.endArray();
                    ligne++;
                }
                System.out.println("///// Fin tableau 2 dim //////  ");
                jsonReader.endArray();
                jsonReader.close();

            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (InputMismatchException e){
            e.printStackTrace();
        }
        MapJeu mapJeu = new MapJeu(tabMapJeu);
        return mapJeu;
    }

    @Override
    protected void onPostExecute(MapJeu result) {
        // Callback
        if(result != null) {

        } else {

        }
    }
}
