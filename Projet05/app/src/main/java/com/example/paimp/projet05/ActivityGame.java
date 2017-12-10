package com.example.paimp.projet05;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by Paimp on 10-12-17.
 */

public class ActivityGame extends AppCompatActivity {

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

    // Joueur
    private Personnage personnage;

     // pour dessiner le tilt
    private int coordLig;
    private int coordCol;

    // Potion disponible
    private final static int POTIONFORCE = 2;
    private final static int  POTIONPV = 2;
    private final static int  POTIONPM = 2;

    // Différents layout
    private TableLayout layoutLabirynthe;
    private TableLayout layoutCharacter;
    private TableLayout tabMove;
    private TableLayout layoutPotion;

    // Différents actionner
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
        setContentView(R.layout.activity_game);

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

        Intent intent = getIntent();
        init(intent);



    }
    /*****************************************************************
     *                      Initialisation du jeu
     ****************************************************************/
    public void init(Intent intent){
        initJoueur(intent);
        dessinerMap(coordCol,coordLig,1);
        chargerVueCaracteristiqueJoueur();
    }
    /***********************************************************************************
     *              Instanciation et initialisation du personnage et des coordonnées
     ***********************************************************************************/
    public void initJoueur(Intent intent){
        String classe = intent.getStringExtra("classe");
        int level = intent.getIntExtra("level",0);
        int force = intent.getIntExtra("force",0);
        int magie = intent.getIntExtra("magie",0);
        int pv = intent.getIntExtra("pv",0);
        int defense = intent.getIntExtra("defense",0);
        int pot_force = intent.getIntExtra("pot_force",0);
        int pot_pv = intent.getIntExtra("pot_pv",0);
        int pot_pm = intent.getIntExtra("pot_pm",0);
        int nbr_perle = intent.getIntExtra("nbr_perle",0);
        int coordX = intent.getIntExtra("coordX",4);
        int coordY = intent.getIntExtra("coordY",7);
        int map = intent.getIntExtra("map",1);
        personnage = new Personnage(classe,level,force,magie,pv,defense,pot_force,pot_pv,pot_pm,nbr_perle,coordX,coordY,map);
        coordCol = personnage.getCoordX();
        coordLig = personnage.getCoordY();
    }


    /*****************************************************************
     * Layout des caractéristiques
     * Affiche le tableau des caractéristiques du joueur
     ****************************************************************/
    public void chargerVueCaracteristiqueJoueur(){
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
        tv_force.setText(R.string.strength);
        line_force.addView(tv_force);
        TextView tv_force_info = new TextView(this);
        tv_force_info.setText(Integer.toString(personnage.getForce()));
        line_force.addView(tv_force_info);
        layoutCharacter.addView(line_force);


        //Ligne de la défence

        TextView tv_defence = new TextView(this);
        tv_defence.setText(R.string.defence);
        ligne_defence.addView(tv_defence);
        TextView tv_defence_info = new TextView(this);
        tv_defence_info.setText(Integer.toString(personnage.getDefense()));
        ligne_defence.addView(tv_defence_info);
        layoutCharacter.addView(ligne_defence);

        //Ligne de la vie

        TextView tv_pv = new TextView(this);
        tv_pv.setText(R.string.health);
        ligne_pv.addView(tv_pv);
        TextView tv_pv_info = new TextView(this);
        tv_pv_info.setText(Integer.toString(personnage.getPv()));
        ligne_pv.addView(tv_pv_info);
        layoutCharacter.addView(ligne_pv);

        //Ligne de la mana

        TextView tv_pm = new TextView(this);
        tv_pm.setText(R.string.magic);
        ligne_pm.addView(tv_pm);
        TextView tv_pm_info = new TextView(this);
        tv_pm_info.setText(Integer.toString(personnage.getMagie()));
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
    public void dessinerMap(int lig, int col, int orientation){
        /**
         * Layout du labyrinthe
         */
        TableRow tr;
        layoutLabirynthe = (TableLayout) findViewById(R.id.labyrinthe);
        for(int i=lig-2;i<=lig+2;i++) {
            tr = new TableRow(this);
            layoutLabirynthe.addView(tr);
            for (int j = col-3; j <= col+3; j++) {
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
        if(map[coordLig][coordCol] == 1 || map[coordLig][coordCol] == 4)
            return true;
        else
            return  false;
    }
    ////////////////////////////////////////////// 3. Deplacement    ///////////////////////////////////////////////////////////

    private View.OnClickListener listener_btnTop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            coordLig--;
            if(checkMove()){
                map[coordLig+1][coordCol]=1;
                map [coordLig][coordCol]=3;
                layoutLabirynthe.removeAllViews();
                dessinerMap(coordLig,coordCol,4);
                personnage.upPerso();
            }
            else{
                coordLig++;
                layoutLabirynthe.removeAllViews();
                dessinerMap(coordLig,coordCol,4);
            }
        }
    };

    private View.OnClickListener listener_btnRight = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            coordCol++;
            if(checkMove()){
                map[coordLig][coordCol-1]=1;
                map [coordLig][coordCol]=3;
                layoutLabirynthe.removeAllViews();
                dessinerMap(coordLig,coordCol,3);
                personnage.rightPerso();
            }
            else{
                coordCol--;
                layoutLabirynthe.removeAllViews();
                dessinerMap(coordLig,coordCol,3);
            }

        }
    };

    private View.OnClickListener listener_btnLeft = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            coordCol--;
            if(checkMove()){
                map[coordLig][coordCol+1]=1;
                map [coordLig][coordCol]=3;
                layoutLabirynthe.removeAllViews();
                dessinerMap(coordLig,coordCol,2);
                personnage.leftPerso();
            }
            else{
                coordCol++;
                layoutLabirynthe.removeAllViews();
                dessinerMap(coordLig,coordCol,2);
            }

        }
    };

    private View.OnClickListener listener_btnBottom = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            coordLig++;
            if(checkMove()){
                map[coordLig-1][coordCol]=1;
                map [coordLig][coordCol]=3;
                layoutLabirynthe.removeAllViews();
                dessinerMap(coordLig,coordCol,1);
                personnage.downPerso();
            }
            else{
                coordLig--;
                layoutLabirynthe.removeAllViews();
                dessinerMap(coordLig,coordCol,1);
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
            if(personnage.getPot_force() > 0){
                personnage.addForce();
            }
            layoutCharacter.removeAllViews();
            chargerVueCaracteristiqueJoueur();
            vuePotions();
        }
    };

    private View.OnClickListener listener_btnPotion_pv = new  View.OnClickListener(){
        @Override
        public void onClick(View v){
            layoutPotion = (TableLayout) findViewById(R.id.layoutPotion);
            layoutPotion.removeAllViews();
            if(personnage.getPot_pv() > 0){
                personnage.addPv();
            }
            layoutCharacter.removeAllViews();
            chargerVueCaracteristiqueJoueur();
            vuePotions();
        }
    };

    private View.OnClickListener listener_btnPotion_pm = new  View.OnClickListener(){
        @Override
        public void onClick(View v){
            layoutPotion = (TableLayout) findViewById(R.id.layoutPotion);
            layoutPotion.removeAllViews();
            if(personnage.getPot_pm() > 0){
                personnage.addPm();
            }
            layoutCharacter.removeAllViews();
            chargerVueCaracteristiqueJoueur();
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
        btnPotionForce.setText("Force " + personnage.getPot_force() + " / " + POTIONFORCE);
        LignePotionForce.addView(btnPotionForce);
        btnPotionForce.setOnClickListener(listener_btnPotion_force);

        TableRow LignePotionPV = new TableRow(this);
        layoutPotion.addView(LignePotionPV);
        btnPotionPV = new Button(this);
        btnPotionPV.setText("Vie " + personnage.getPot_pv() + " / " + POTIONPV);
        LignePotionPV.addView(btnPotionPV);
        btnPotionPV.setOnClickListener(listener_btnPotion_pv);

        TableRow LignePotionPM = new TableRow(this);
        layoutPotion.addView(LignePotionPM);
        btnPotionPM = new Button(this);
        btnPotionPM.setText("Mana " + personnage.getPot_pm() + " / " + POTIONPM);
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
