package com.example.paimp.projet05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoJoueur extends AppCompatActivity {
    Intent intent;
    private Personnage personnage;

    TextView niveau;
    TextView classe;
    TextView sante;
    TextView force;
    TextView magie;
    TextView defense;
    TextView nbr_perle;
    ImageView img_classe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infojoueur);
        intent = getIntent();
        personnage = (Personnage)intent.getSerializableExtra("joueur");

        niveau = findViewById(R.id.result_level);
        classe = findViewById(R.id.result_classe);
        sante = findViewById(R.id.result_sante);
        force = findViewById(R.id.result_force);
        magie = findViewById(R.id.result_magie);
        defense = findViewById(R.id.result_defense);
        nbr_perle = findViewById(R.id.result_nbr_perle);

        niveau.setText(Integer.toString(personnage.getLevel()));
        classe.setText(personnage.getClasse());
        sante.setText(Integer.toString(personnage.getPv()));
        force.setText(Integer.toString(personnage.getForce()));
        magie.setText(Integer.toString(personnage.getMagie()));
        defense.setText(Integer.toString(personnage.getDefense()));
        nbr_perle.setText(Integer.toString(personnage.getNbr_perle()));

        img_classe = findViewById(R.id.img_classe);
        if(personnage.getClasse().equals("chevalier"))
            img_classe.setImageResource(R.drawable.hero_warrior2);
        else
            img_classe.setImageResource(R.drawable.priest);
    }

}
