package com.example.paimp.projet08.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.paimp.projet08.R;
import com.example.paimp.projet08.model.Joueur;

public class SelectClasse extends AppCompatActivity {

    private CheckBox ck_knight;
    private CheckBox ck_priest;
    private Button btn_confirmation;
    private Joueur mJoueur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_classe);
        onlyCheckBox();
        sendBox();
    }

    /***
     *  onlyCheckBox : vérifie qu'il n'y aie qu'un seul checkbox de selectionné
     */
    public void onlyCheckBox(){
        ck_knight = findViewById(R.id.checkBoxGuerrier);
        ck_priest = findViewById(R.id.checkBoxPretre);

        ck_knight.setOnClickListener( new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              if(((CheckBox)v).isChecked()){
                                                  ck_priest.setChecked(false);
                                                  Toast.makeText(SelectClasse.this,R.string.select_knight,Toast.LENGTH_SHORT).show();
                                              }
                                          }
                                      }
        );

        ck_priest.setOnClickListener( new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              if(((CheckBox)v).isChecked()){
                                                  ck_knight.setChecked(false);
                                                  Toast.makeText(SelectClasse.this,R.string.select_priest,Toast.LENGTH_SHORT).show();
                                              }
                                          }
                                      }
        );
    }

    /***
     *  sendBox : vérifie qu'il y aie un personnage de sélectionné et envoie les informations
     *  du personnage séléctionné vers le jeu
     */
    public void sendBox(){
        ck_knight = findViewById(R.id.checkBoxGuerrier);
        ck_priest = findViewById(R.id.checkBoxPretre);
        btn_confirmation = findViewById(R.id.btn_confirmation);
        btn_confirmation.setOnClickListener( new View.OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {

                                                     if(ck_knight.isChecked()) {
                                                         mJoueur = new Joueur(1,"Chevalier",1,19,0,35,18,2,2,2,0,7,4,1);
                                                         Intent intent = new Intent(SelectClasse.this,Game.class);
                                                         intent.putExtra("joueur",mJoueur);
                                                         startActivity(intent);
                                                     }
                                                     else if(ck_priest.isChecked()){
                                                         mJoueur = new Joueur(1,"Pretre",1,5,20,5,10,2,2,2,0,7,4,1);
                                                         Intent intent = new Intent(SelectClasse.this,Game.class);
                                                         intent.putExtra("joueur",mJoueur);
                                                         startActivity(intent);
                                                     }
                                                     else{
                                                         Toast.makeText(SelectClasse.this,R.string.select,Toast.LENGTH_SHORT).show();
                                                     }
                                                 }
                                             }
        );
    }
}
