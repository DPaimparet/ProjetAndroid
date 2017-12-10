package com.example.paimp.projet05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static int ID_REQUETE = 2;
    private CheckBox ck_knight;
    private CheckBox ck_priest;
    private Button btn_confirmation;
    private Personnage personnage;


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
                                                         personnage = new Personnage("chevalier",1,19,0,35,18,2,2,2,0,4,7,1);
                                                         initIntent(personnage);
                                                     }
                                                     else if(ck_priest.isChecked()){
                                                         personnage = new Personnage("pretre",1,5,20,5,10,2,2,2,0,4,7,1);
                                                         initIntent(personnage);
                                                     }
                                                     else{
                                                         Toast.makeText(MainActivity.this,R.string.select,Toast.LENGTH_SHORT).show();
                                                     }
                                                 }
                                             }
        );
    }

    /***
     * Initialise l'objet intent pour envoyer les informations du personnage vers ActivityGame
     * @param personnage
     */
    public void initIntent(Personnage personnage){
        Intent intent = new Intent(MainActivity.this,ActivityGame.class);
        intent.putExtra("classe",personnage.getClasse());
        intent.putExtra("level",personnage.getLevel());
        intent.putExtra("force",personnage.getForce());
        intent.putExtra("magie",personnage.getMagie());
        intent.putExtra("pv",personnage.getMagie());
        intent.putExtra("pv",personnage.getPv());
        intent.putExtra("defense",personnage.getDefense());
        intent.putExtra("pot_force",personnage.getPot_force());
        intent.putExtra("pot_pv",personnage.getPot_pv());
        intent.putExtra("pot_pm",personnage.getPot_pm());
        intent.putExtra("nbr_perle",personnage.getNbr_perle());
        intent.putExtra("coordX",personnage.getCoordX());
        intent.putExtra("coordY",personnage.getCoordY());
        intent.putExtra("map",personnage.getMap());
        startActivity(intent);
        startActivityForResult(intent,ID_REQUETE);
    }
}
