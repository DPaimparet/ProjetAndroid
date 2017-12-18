package com.example.paimp.projet08.model;

/**
 * Created by Paimp on 16-12-17.
 */

public class Login {
    private String mUserName;
    private String mPassword;

    //////////////////////////   Constructeur   ////////////////////////
    public Login(String userName, String password) {
        mUserName = userName;
        mPassword = password;
    }

    //////////////////////////     Getter      //////////////////////////
    public String getUserName() {
        return mUserName;
    }
    public String getPassword() {
        return mPassword;
    }
}
