package com.example.paimp.projet08.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paimp.projet08.R;
import com.example.paimp.projet08.model.CheckLoginTask;
import com.example.paimp.projet08.model.Login;

public class LoginActivity extends AppCompatActivity{

    private EditText mUserView = null;
    private EditText mPasswordView = null;
    private Button mConnectButton;
    private Login mLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mConnectButton = findViewById(R.id.button_login);
        mConnectButton.setOnClickListener(listener_btnLogin);

        mUserView = findViewById(R.id.editText_username);
        mPasswordView = findViewById(R.id.editText_password);

    }

    /***
     *  Listener permettant de vérifier si les champs pour le login sont correctements remplis
     *  et vérifie si l'utilisateur est connu
     */
    private View.OnClickListener listener_btnLogin = new View.OnClickListener() {
        public void onClick(View v){

            String username = mUserView.getText().toString();
            String password = mPasswordView.getText().toString();

            if(!TextUtils.isEmpty(username) && checkPassword(password)){
                mLogin = new Login(username,password);
                new CheckLoginTask(LoginActivity.this).execute(mLogin);
            }else if (!TextUtils.isEmpty(username) && !checkPassword(password)){
                Toast.makeText(LoginActivity.this,R.string.fields_password,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(LoginActivity.this,R.string.fields_login,Toast.LENGTH_SHORT).show();
            }
        }
    };

    /***
     * Vérifie que le mot de passe soit de minimum : 5 caractères
     * in @param String password
     * out @return boolean
     */
    private boolean checkPassword(String password){
       return password.length() > 4;
    }

}
