package com.example.paimp.projet08.model;

import android.os.AsyncTask;
import android.util.JsonReader;

import com.example.paimp.projet08.controller.Game;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Paimp on 18-12-17.
 */

public class GetMap extends AsyncTask<MapJeu, Void, MapJeu> {
    Game activite;
    String result="";
    int[][] mTabMap;

    public GetMap(Game activite){
        this.activite=activite;
    }

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
            connection.setRequestProperty("Content-Type", "text/plain");
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
                Scanner scanner = new Scanner(inputStreamReader);
                //scanner.useDelimiter(",");
                while (scanner.hasNext()) {
                    result += scanner.next();
                }
                System.out.println(result);
                scanner.close();

                ///////////////////// Récupération du JSON ///////////////////////

                JsonReader jsonReader = new JsonReader(inputStreamReader);
                jsonReader.beginArray();
                int i,j;
                i=0;
                j=0;
                while(jsonReader.hasNext()){
                    mTabMap[i][j] = jsonReader.nextInt();
                    result += jsonReader.nextInt();
                }
                jsonReader.endArray();
                jsonReader.close();
                System.out.println(result);

            }
            else
            {
                result = "map non chargée";


            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return new MapJeu();
    }

    @Override
    protected void onPostExecute(MapJeu result) {
        // Callback
        if(result != null) {
            System.out.println("pas vide");
        } else {
            System.out.println("vide");
        }
    }
}
