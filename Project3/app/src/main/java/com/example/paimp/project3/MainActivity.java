package com.example.paimp.project3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int map [][]= {
            {0,0,0,0,0,0,0,0},
            {0,1,1,1,1,2,2,0},
            {0,1,2,2,1,2,2,0},
            {0,1,2,2,1,1,1,0},
            {0,1,1,1,1,2,1,0},
            {0,0,0,0,0,0,0,0}
    };
    int character [][] = {
            {0,50,30,150,150}
    };
    private TableLayout layoutLabirynthe;
    private TableLayout layoutCharacter;
    private TableLayout tabMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Layout du labyrinthe
         */
        TableRow tr;
        layoutLabirynthe = (TableLayout) findViewById(R.id.labyrinthe);
        for(int i=0;i<map.length;i++) {
            tr = new TableRow(this);
            layoutLabirynthe.addView(tr);
            for (int j = 0; j < map[i].length; j++) {
                ImageView image = new ImageView(this);
                switch (map[i][j]) {
                    case 0:
                        image.setImageResource(R.drawable.tile_rocher);
                        break;
                    case 1:
                        image.setImageResource(R.drawable.tile_chemin);
                        break;
                    case 2:
                        image.setImageResource(R.drawable.tile_arbre);
                        break;
                }
                tr.addView(image);
                image.getLayoutParams().height=80;
                image.getLayoutParams().width=80;
            }
        }

        /**
         * Layout des caractéristiques
         * Affiche le tableau des caractéristiques du joueur
         */

        layoutCharacter = (TableLayout) findViewById(R.id.character);
        TableRow line_force;
        TableRow ligne_defence;
        TableRow ligne_pv;
        TableRow ligne_pm;

        line_force = new TableRow(this);
        ligne_defence = new TableRow(this);
        ligne_pv = new TableRow(this);
        ligne_pm = new TableRow(this);

        //Ligne de la force

        TextView tv_force = new TextView(this);
        tv_force.setText(R.string.force);
        line_force.addView(tv_force);
        TextView tv_force_info = new TextView(this);
        tv_force_info.setText("40");
        line_force.addView(tv_force_info);
        layoutCharacter.addView(line_force);


        //Ligne de la défence

        TextView tv_defence = new TextView(this);
        tv_defence.setText(R.string.defence);
        ligne_defence.addView(tv_defence);
        TextView tv_defence_info = new TextView(this);
        tv_defence_info.setText("50");
        ligne_defence.addView(tv_defence_info);
        layoutCharacter.addView(ligne_defence);

        //Ligne de la vie

        TextView tv_pv = new TextView(this);
        tv_pv.setText(R.string.pv);
        ligne_pv.addView(tv_pv);
        TextView tv_pv_info = new TextView(this);
        tv_pv_info.setText("50");
        ligne_pv.addView(tv_pv_info);
        layoutCharacter.addView(ligne_pv);

        //Ligne de la mana

        TextView tv_pm = new TextView(this);
        tv_pm.setText(R.string.pm);
        ligne_pm.addView(tv_pm);
        TextView tv_pm_info = new TextView(this);
        tv_pm_info.setText("50");
        ligne_pm.addView(tv_pm_info);
        layoutCharacter.addView(ligne_pm);

    }
}
