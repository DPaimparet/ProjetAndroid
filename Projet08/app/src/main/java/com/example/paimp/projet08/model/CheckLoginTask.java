package com.example.paimp.projet08.model;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.paimp.projet08.R;
import com.example.paimp.projet08.controller.LoginActivity;
import com.example.paimp.projet08.controller.SelectClasse;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by Paimp on 16-12-17.
 */

public class CheckLoginTask extends AsyncTask<Login , Void , String> {
    LoginActivity activite;


    public CheckLoginTask(LoginActivity activite){
        this.activite=activite;
    }

    @Override
    protected String doInBackground(Login... login) {
        String url_login = "http://10.0.2.2:8080/android/login.php";
        String reponse="";
        Login mLogin = login[0];
        try{
            URL url = new URL(url_login);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            ///////////////// Propriété de ma connexion  /////////////////
            connection.setRequestMethod("POST");

            ///////////////// requête  /////////////////

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String parametres_post="txtUsername="+mLogin.getUserName()+"&txtPassword="+mLogin.getPassword();
            writer.write(parametres_post);
            writer.flush();
            writer.close();
            os.close();

            ///////////////// Connexion  /////////////////
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == 200)
            {
                ///////////////// réponse  /////////////////
                InputStream inputStream;
                inputStream=connection.getInputStream();
                InputStreamReader inputStreamReader;
                inputStreamReader=new InputStreamReader(inputStream, "UTF-8");
                Scanner scanner = new Scanner(inputStreamReader);
                reponse = scanner.next();
            }
            else
            {
                reponse = "autre";
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
        if(result.equals("success")){
            Intent intent = new Intent(activite,SelectClasse.class);
            activite.startActivity(intent);
        }
        else {
            Toast.makeText(activite, R.string.login_failed,Toast.LENGTH_SHORT).show();
        }
    }
}
