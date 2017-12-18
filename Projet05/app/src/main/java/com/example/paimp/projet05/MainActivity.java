package com.example.paimp.projet05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox ck_knight;
    private CheckBox ck_priest;
    private Button btn_confirmation;
    private Personnage personnage;
    public static final String BUNDLE_STATE_JOUEUR = "BUNDLE_STATE_JOUEUR";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                                                  Toast.makeText(MainActivity.this,R.string.select_knight,Toast.LENGTH_SHORT).show();
                                              }
                                          }
                                      }
        );

        ck_priest.setOnClickListener( new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              if(((CheckBox)v).isChecked()){
                                                  ck_knight.setChecked(false);
                                                  Toast.makeText(MainActivity.this,R.string.select_priest,Toast.LENGTH_SHORT).show();
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
                                                         personnage = new Personnage("chevalier",1,19,0,35,18,2,2,2,0,7,4,1);
                                                         //initIntent(personnage);
                                                         Intent intent = new Intent(MainActivity.this,ActivityGame.class);
                                                         intent.putExtra("joueur",personnage);
                                                         startActivity(intent);
                                                     }
                                                     else if(ck_priest.isChecked()){
                                                         personnage = new Personnage("pretre",1,5,20,5,10,2,2,2,0,7,4,1);
                                                         //initIntent(personnage);
                                                         Intent intent = new Intent(MainActivity.this,ActivityGame.class);
                                                         intent.putExtra("joueur",personnage);
                                                         startActivity(intent);
                                                     }
                                                     else{
                                                         Toast.makeText(MainActivity.this,R.string.select,Toast.LENGTH_SHORT).show();
                                                     }
                                                 }
                                             }
        );
    }
}
