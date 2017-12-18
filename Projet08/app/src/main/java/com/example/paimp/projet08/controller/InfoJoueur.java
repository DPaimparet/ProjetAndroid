package com.example.paimp.projet08.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.paimp.projet08.R;
import com.example.paimp.projet08.model.Joueur;

public class InfoJoueur extends AppCompatActivity {

    Intent intent;
    Intent retour = new Intent();
    private Joueur mJoueur;
    private int exp;
    private int[][] map;

    TextView niveau;
    TextView classe;
    TextView sante;
    TextView force;
    TextView magie;
    TextView defense;
    TextView nbr_perle;
    ImageView img_classe;
    Button btn_retour;

    TableRow tb_force;
    TableRow tb_sante;
    TableRow tb_magie;
    TableRow tb_defense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_joueur);
    ///////////////////////// Récupération des informations du joueur //////////////////////////
        intent = getIntent();
        mJoueur = (Joueur) intent.getSerializableExtra("player");
        exp = intent.getIntExtra("exp",0);

    ///////////////////////// Affichage des caractéristiques  //////////////////////////
        niveau = findViewById(R.id.result_level);
        classe = findViewById(R.id.result_classe);
        sante = findViewById(R.id.result_sante);
        force = findViewById(R.id.result_force);
        magie = findViewById(R.id.result_magie);
        defense = findViewById(R.id.result_defense);
        nbr_perle = findViewById(R.id.result_nbr_perle);

        tb_force = findViewById(R.id.force);
        tb_sante = findViewById(R.id.sante);
        tb_defense = findViewById(R.id.defence);
        tb_magie = findViewById(R.id.magie);

        niveau.setText(Integer.toString(mJoueur.getLevel()));
        classe.setText(mJoueur.getClasse());
        sante.setText(Integer.toString(mJoueur.getPv()));
        force.setText(Integer.toString(mJoueur.getForce()));
        magie.setText(Integer.toString(mJoueur.getMagie()));
        defense.setText(Integer.toString(mJoueur.getDefense()));
        nbr_perle.setText(Integer.toString(mJoueur.getNbr_perle()));

        imageDeClasse();
    //////////////////////// Vérification si un niveau est disponible //////////////////////////
        levelUp(exp);
    ///////////////////////// Bouton de retour vers l'activité game   //////////////////////////
        btn_retour = findViewById(R.id.btn_retour_infoJoueur);
        btn_retour.setOnClickListener(listener_retour);

    }

    /*************************************************************************
     *                           Les méthodes de l'activité
     *************************************************************************/
    /***
     * Affiche l'image correspondant à la classe
     */
    private void imageDeClasse(){
        img_classe = findViewById(R.id.img_classe);
        if(mJoueur.getClasse().equals("Chevalier"))
            img_classe.setImageResource(R.drawable.hero_warrior_normal);
        else
            img_classe.setImageResource(R.drawable.priest);
    }

    /***
     * Reçois en paramètre le nombre de niveau disponible et créé les boutons pour augmenter une
     * caractéristique du personnage
     * @param nbr
     */
    private void levelUp(int nbr){
        if(nbr > 0){
            ImageView img_plus_force = new ImageView(this);
            ImageView img_plus_sante = new ImageView(this);
            ImageView img_plus_defence = new ImageView(this);
            ImageView img_plus_magie = new ImageView(this);

            img_plus_force.setImageResource(R.drawable.plus);
            img_plus_sante.setImageResource(R.drawable.plus);
            img_plus_defence.setImageResource(R.drawable.plus);
            img_plus_magie.setImageResource(R.drawable.plus);

            tb_force.addView(img_plus_force);
            tb_sante.addView(img_plus_sante);
            tb_defense.addView(img_plus_defence);
            tb_magie.addView(img_plus_magie);

            tb_force.setOnClickListener(listener_force_plus);
            tb_sante.setOnClickListener(listener_sante_plus);
            tb_defense.setOnClickListener(listener_defence_plus);
            tb_magie.setOnClickListener(listener_magie_plus);
        }
    }

    /***
     * reçois en paramètre le nombre de niveau disponible; si celui-ci est à 0, on retire les
     * boutons permettant l'ajout de caractéristique
     * @param nbr
     */
    public void removePlus(int nbr){
        if(nbr==0){
            tb_force.removeViewAt(2);
            tb_sante.removeViewAt(2);
            tb_defense.removeViewAt(2);
            tb_magie.removeViewAt(2);
        }
    }
    /*************************************************************************
     *                              Les listeners
     *************************************************************************/
    private View.OnClickListener listener_retour = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            retour = new Intent(InfoJoueur.this,Game.class);
            retour.putExtra("exp",exp);
            retour.putExtra("player",mJoueur);
            setResult(RESULT_OK, retour);
            finish();
        }
    };

    private View.OnClickListener listener_force_plus = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mJoueur.addNiveau();
            mJoueur.addXpForce();
            exp -= 1;
            removePlus(exp);
            niveau.setText(Integer.toString(mJoueur.getLevel()));
            force.setText(Integer.toString(mJoueur.getForce()));
        }
    };

    private View.OnClickListener listener_sante_plus = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mJoueur.addNiveau();
            mJoueur.addXpSante();
            exp -= 1;
            removePlus(exp);
            niveau.setText(Integer.toString(mJoueur.getLevel()));
            sante.setText(Integer.toString(mJoueur.getPv()));
        }
    };

    private View.OnClickListener listener_defence_plus = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mJoueur.addNiveau();
            mJoueur.addXpDefense();
            exp -= 1;
            removePlus(exp);
            niveau.setText(Integer.toString(mJoueur.getLevel()));
            defense.setText(Integer.toString(mJoueur.getDefense()));
        }
    };

    private View.OnClickListener listener_magie_plus = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mJoueur.addNiveau();
            mJoueur.addXpMagie();
            exp -= 1;
            removePlus(exp);
            niveau.setText(Integer.toString(mJoueur.getLevel()));
            magie.setText(Integer.toString(mJoueur.getMagie()));
        }
    };
}
