package com.example.paimp.project4;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int map [][]= {
            {0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,2,2,2,2,2,2,2,2,2,2,2,0},
            {0,2,2,1,1,1,1,1,2,1,2,2,0},
            {0,2,2,1,1,2,2,1,1,1,2,2,0},
            {0,2,2,1,1,2,1,3,1,1,2,2,0},
            {0,2,2,1,1,1,1,1,2,1,2,2,0},
            {0,2,2,2,2,2,2,2,2,2,2,2,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0}
    };

    // Caractéristique du joueur
    int force = 5;
    int defence = 5;
    int pv = 10;
    int pm = 5;

    // Coordonnées du joueur
    int x;
    int y;

    // Potion disponible
    private final static int POTIONFORCE = 2;
    private final static int  POTIONPV = 2;
    private final static int  POTIONPM = 2;

    int potionForce;
    int potionPV;
    int potionPM;

    private TableLayout layoutLabirynthe;
    private TableLayout layoutCharacter;
    private TableLayout tabMove;
    private TableLayout layoutPotion;


    ImageButton btnTop;
    ImageButton btnRight;
    ImageButton btnLeft;
    ImageButton btnBottom;

    Button btnPotion;
    Button btnPotionForce;
    Button btnPotionPV;
    Button btnPotionPM;
    Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Déclaration
         */
        btnTop = (ImageButton) findViewById(R.id.moveTop);
        btnTop.setOnClickListener(listener_btnTop);

        btnRight = (ImageButton) findViewById(R.id.moveRight);
        btnRight.setOnClickListener(listener_btnRight);

        btnLeft = (ImageButton) findViewById(R.id.moveLeft);
        btnLeft.setOnClickListener(listener_btnLeft);

        btnBottom = (ImageButton) findViewById(R.id.moveBottom);
        btnBottom.setOnClickListener(listener_btnBottom);

        btnPotion = (Button) findViewById(R.id.potion);
        btnPotion.setOnClickListener(listener_btnPotion);

        init();


    }
    /*****************************************************************
     *                      Initialisation du jeu
     ****************************************************************/
    public void init(){
        x=4;
        y=7;
        potionForce = POTIONFORCE;
        potionPV = POTIONPV;
        potionPM = POTIONPM;
        drawMap(x,y,1);
        chargerCaractere();
    }

    /*****************************************************************
     * Layout des caractéristiques
     * Affiche le tableau des caractéristiques du joueur
     ****************************************************************/
    public void chargerCaractere(){
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
        tv_force_info.setText(Integer.toString(force));
        line_force.addView(tv_force_info);
        layoutCharacter.addView(line_force);


        //Ligne de la défence

        TextView tv_defence = new TextView(this);
        tv_defence.setText(R.string.defence);
        ligne_defence.addView(tv_defence);
        TextView tv_defence_info = new TextView(this);
        tv_defence_info.setText(Integer.toString(defence));
        ligne_defence.addView(tv_defence_info);
        layoutCharacter.addView(ligne_defence);

        //Ligne de la vie

        TextView tv_pv = new TextView(this);
        tv_pv.setText(R.string.pv);
        ligne_pv.addView(tv_pv);
        TextView tv_pv_info = new TextView(this);
        tv_pv_info.setText(Integer.toString(pv));
        ligne_pv.addView(tv_pv_info);
        layoutCharacter.addView(ligne_pv);

        //Ligne de la mana

        TextView tv_pm = new TextView(this);
        tv_pm.setText(R.string.pm);
        ligne_pm.addView(tv_pm);
        TextView tv_pm_info = new TextView(this);
        tv_pm_info.setText(Integer.toString(pm));
        ligne_pm.addView(tv_pm_info);
        layoutCharacter.addView(ligne_pm);
    }


    /*****************************************************************
     * Layout de la map
     *
     * 1. Dessine le labyrinthe
     * 2. Vérifie si le déplacement est possible
     * 3. Effectue le déplacement
     ****************************************************************/
    //////////////////////////////////////////////// 1. Dessiner Map  ///////////////////////////////////////////////////////////
    public void drawMap(int x, int y, int orientation){
        /**
         * Layout du labyrinthe
         */
        TableRow tr;
        layoutLabirynthe = (TableLayout) findViewById(R.id.labyrinthe);
        for(int i=x-2;i<=x+2;i++) {
            tr = new TableRow(this);
            layoutLabirynthe.addView(tr);
            for (int j = y-3; j <= y+3; j++) {
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
                    default:
                        switch(orientation){
                            case 1 :
                                image.setImageResource(R.drawable.link_bas01);
                                break;
                            case 2 :
                                image.setImageResource(R.drawable.link_gauche);
                                break;
                            case 3 :
                                image.setImageResource(R.drawable.link_right);
                                break;
                            case 4 :
                                image.setImageResource(R.drawable.link_haut);
                                break;
                        }
                        break;
                }
                tr.addView(image);
                image.getLayoutParams().height=60;
                image.getLayoutParams().width=60;
            }
        }
    }

    ////////////////////////////////////////////// 2. Puis-je me déplacer    ///////////////////////////////////////////////////
    public boolean  checkMove(){
        if(map[x][y] == 1)
            return true;
        else
            return  false;
    }
    ////////////////////////////////////////////// 3. Deplacement    ///////////////////////////////////////////////////////////

    private View.OnClickListener listener_btnTop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            x--;
            if(checkMove()){
                map[x+1][y]=1;
                map [x][y]=3;
                layoutLabirynthe.removeAllViews();
                drawMap(x,y,4);
            }
            else{
                x++;
                layoutLabirynthe.removeAllViews();
                drawMap(x,y,4);
            }
        }
    };

    private View.OnClickListener listener_btnRight = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            y++;
            if(checkMove()){
                map[x][y-1]=1;
                map [x][y]=3;
                layoutLabirynthe.removeAllViews();
                drawMap(x,y,3);
            }
            else{
                y--;
                layoutLabirynthe.removeAllViews();
                drawMap(x,y,3);
            }

        }
    };

    private View.OnClickListener listener_btnLeft = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            y--;
            if(checkMove()){
                map[x][y+1]=1;
                map [x][y]=3;
                layoutLabirynthe.removeAllViews();
                drawMap(x,y,2);
            }
            else{
                y++;
                layoutLabirynthe.removeAllViews();
                drawMap(x,y,2);
            }

        }
    };

    private View.OnClickListener listener_btnBottom = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            x++;
            if(checkMove()){
                map[x-1][y]=1;
                map [x][y]=3;
                layoutLabirynthe.removeAllViews();
                drawMap(x,y,1);
            }
            else{
                x--;
                layoutLabirynthe.removeAllViews();
                drawMap(x,y,1);
            }
        }
    };

    /**
     * Layout des potions
     *
     * Fonctions permettant au joueur de prendre des potions afin d'augmenter ses caractèristiques
     * Gestion des differents boutons qui permettent la mise à jours des caractéristiques du joueur
     * et la gestion du stock de potion disponible
     */

    private View.OnClickListener listener_btnPotion = new  View.OnClickListener(){
        @Override
        public void onClick(View v){
            layoutPotion = (TableLayout) findViewById(R.id.layoutPotion);
            layoutPotion.removeAllViews();
            vuePotions();
        }
    };

    private View.OnClickListener listener_btnPotion_force = new  View.OnClickListener(){
        @Override
        public void onClick(View v){
            layoutPotion = (TableLayout) findViewById(R.id.layoutPotion);
            layoutPotion.removeAllViews();
            if(potionForce > 0){
                force++;
                potionForce--;
            }
            layoutCharacter.removeAllViews();
            chargerCaractere();
            vuePotions();
        }
    };

    private View.OnClickListener listener_btnPotion_pv = new  View.OnClickListener(){
        @Override
        public void onClick(View v){
            layoutPotion = (TableLayout) findViewById(R.id.layoutPotion);
            layoutPotion.removeAllViews();
            if(potionPV > 0){
                pv++;
                potionPV--;
            }
            layoutCharacter.removeAllViews();
            chargerCaractere();
            vuePotions();
        }
    };

    private View.OnClickListener listener_btnPotion_pm = new  View.OnClickListener(){
        @Override
        public void onClick(View v){
            layoutPotion = (TableLayout) findViewById(R.id.layoutPotion);
            layoutPotion.removeAllViews();
            if(potionPM > 0){
                pm++;
                potionPM--;
            }
            layoutCharacter.removeAllViews();
            chargerCaractere();
            vuePotions();
        }
    };

    private View.OnClickListener listener_close = new  View.OnClickListener(){
        @Override
        public void onClick(View v){
            layoutPotion = (TableLayout) findViewById(R.id.layoutPotion);
            layoutPotion.removeAllViews();
            boutonPotions();
        }
    };

    private void vuePotions(){
        layoutPotion = (TableLayout) findViewById(R.id.layoutPotion);

        TableRow LignePotionForce = new TableRow(this);
        layoutPotion.addView(LignePotionForce);
        btnPotionForce = new Button(this);
        btnPotionForce.setText("Potion de Force " + potionForce + " / " + POTIONFORCE);
        LignePotionForce.addView(btnPotionForce);
        btnPotionForce.setOnClickListener(listener_btnPotion_force);

        TableRow LignePotionPV = new TableRow(this);
        layoutPotion.addView(LignePotionPV);
        btnPotionPV = new Button(this);
        btnPotionPV.setText("Potion de Vie " + potionPV + " / " + POTIONPV);
        LignePotionPV.addView(btnPotionPV);
        btnPotionPV.setOnClickListener(listener_btnPotion_pv);

        TableRow LignePotionPM = new TableRow(this);
        layoutPotion.addView(LignePotionPM);
        btnPotionPM = new Button(this);
        btnPotionPM.setText("Potion de Mana " + potionPM + " / " + POTIONPM);
        LignePotionPM.addView(btnPotionPM);
        btnPotionPM.setOnClickListener(listener_btnPotion_pm);

        TableRow LigneClose = new TableRow(this);
        layoutPotion.addView(LigneClose);
        btnClose = new Button(this);
        btnClose.setText("Fermer");
        LigneClose.addView(btnClose);
        btnClose.setOnClickListener(listener_close);
    }

    private void boutonPotions(){

        layoutPotion = (TableLayout) findViewById(R.id.layoutPotion);
        TableRow LigneBoutonPotion = new TableRow(this);
        layoutPotion.addView(LigneBoutonPotion);
        btnPotion = new Button(this);
        btnPotion.setText("Potion");
        LigneBoutonPotion.addView(btnPotion);
        btnPotion.setOnClickListener(listener_btnPotion);
    }
}
