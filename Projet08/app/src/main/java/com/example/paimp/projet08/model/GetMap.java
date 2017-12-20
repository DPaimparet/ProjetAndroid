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
import java.util.List;
import java.util.Scanner;

/**
 * Created by Paimp on 18-12-17.
 */

public class GetMap extends AsyncTask<MapJeu, Void, MapJeu> {
    Game activite;
    String result="";
    List<Integer> tabMap = new ArrayList<>();
    int[][]tabMapJeu;

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
                int i;
                try (JsonReader jsonReader = new JsonReader(inputStreamReader)) {
                    jsonReader.beginArray();
                    i = 0;
                    while (jsonReader.hasNext()) {
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) tabMap.add(jsonReader.nextInt());
                        jsonReader.endArray();
                        ///////////////////// converti l'Array en int[]  ///////////////////////
                        int[] ligne = toIntArray(tabMap);
                        tabMapJeu[i] = ligne;
                        i++;
                    }
                    jsonReader.endArray();
                    jsonReader.close();
                }

                for(i=0;i<tabMapJeu.length;i++){
                    for(int j = 0 ; j < tabMapJeu[i].length;j++){
                        System.out.println(tabMapJeu[i][j]);
                    }
                }

            }
            else
            {
                tabMapJeu = null;
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
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

    public int[] toIntArray(List<Integer> list)  {
        int[] ret = new int[list.size()];
        int i = 0;
        for (Integer e : list)
            ret[i++] = e.intValue();
        return ret;
    }
}
