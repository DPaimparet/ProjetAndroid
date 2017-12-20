package com.example.paimp.projet08.controller;

import android.content.Intent;
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
import android.widget.Toast;

import com.example.paimp.projet08.R;
import com.example.paimp.projet08.model.GetMap;
import com.example.paimp.projet08.model.Joueur;
import com.example.paimp.projet08.model.MapJeu;

public class Game extends AppCompatActivity {

    int map [][]= {
            {0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,2,2,2,2,2,2,2,2,2,2,2,0},
            {0,2,2,4,1,1,1,1,2,4,2,2,0},
            {0,2,2,1,1,2,2,1,1,1,2,2,0},
            {0,2,2,1,1,2,1,3,1,1,2,2,0},
            {0,2,2,1,4,1,1,1,0,1,2,2,0},
            {0,2,2,1,1,1,1,1,0,4,2,2,0},
            {0,2,2,1,1,1,1,1,1,1,2,2,0},
            {0,2,2,1,1,1,1,1,2,1,2,2,0},
            {0,2,2,4,1,1,4,1,2,4,2,2,0},
            {0,2,2,2,2,2,2,2,2,2,2,2,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0}
    };
    private MapJeu mMap = new MapJeu();
    private Joueur mJoueur;
    private int coordLig;
    private int coordCol;
    private final static int POTIONFORCE = 2;
    private final static int  POTIONPV = 2;
    private final static int  POTIONPM = 2;
    private TableLayout layoutLabirynthe;
    private TableLayout layoutCharacter;
    private TableLayout layoutPotion;
    private Intent i;
    public final static int NUM_REQUETE = 1;
    private int exp=0;

    // Différents actionner
    ImageButton btnTop;
    ImageButton btnRight;
    ImageButton btnLeft;
    ImageButton btnBottom;
    ImageView btnPerso;

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

     //////////////////////// Vérification de l'instance de l'activité  ///////////////////////////
        if(savedInstanceState != null){
            mJoueur = (Joueur)savedInstanceState.getSerializable("statusJoueur");
            map = (int[][]) savedInstanceState.getSerializable("map");
            exp = savedInstanceState.getInt("exp");
            coordCol = mJoueur.getCoordX();
            coordLig = mJoueur.getCoordY();
            dessinerMap(coordLig,coordCol,1);
            chargerVueCaracteristiqueJoueur();
        }
        else{
            new GetMap(Game.this).execute(mMap);
            //map=mMap.getMap();
            i = getIntent();
            init(i);
        }
    }
    /////////////////////////////////// Sauvegarde de l'instance /////////////////////////////////
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putSerializable("map",map);
        outState.putSerializable("statusJoueur",mJoueur);
        outState.putInt("exp",exp);
        super.onSaveInstanceState(outState);
    }
    /////////////////////////// Retour des résultats de l'activité infoJoueur //////////////////////
    @Override
    protected void onActivityResult(int num_requete,
                                    int code_retour,
                                    Intent data)
    {
        if (num_requete == NUM_REQUETE && code_retour == RESULT_OK )
        {
            mJoueur = (Joueur)data.getSerializableExtra("player");
            layoutCharacter.removeAllViews();
            chargerVueCaracteristiqueJoueur();
            exp = data.getIntExtra("exp",0);
        }
    }

    /*****************************************************************
     *                      Initialisation du jeu
     ****************************************************************/
    public void init(Intent intent){
        initJoueur(intent);
        //new GetMap(Game.this).execute(mMap);
        dessinerMap(coordLig,coordCol,1);
        chargerVueCaracteristiqueJoueur();
    }


    /***********************************************************************************
     *              Instanciation et initialisation du mJoueur et des coordonnées
     ***********************************************************************************/
    public void initJoueur(Intent intent){
        mJoueur = (Joueur) intent.getSerializableExtra("joueur");
        coordCol = mJoueur.getCoordX();
        coordLig = mJoueur.getCoordY();
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
        tv_force_info.setText(Integer.toString(mJoueur.getForce()));
        line_force.addView(tv_force_info);
        layoutCharacter.addView(line_force);


        //Ligne de la défence

        TextView tv_defence = new TextView(this);
        tv_defence.setText(R.string.defence);
        ligne_defence.addView(tv_defence);
        TextView tv_defence_info = new TextView(this);
        tv_defence_info.setText(Integer.toString(mJoueur.getDefense()));
        ligne_defence.addView(tv_defence_info);
        layoutCharacter.addView(ligne_defence);

        //Ligne de la vie

        TextView tv_pv = new TextView(this);
        tv_pv.setText(R.string.health);
        ligne_pv.addView(tv_pv);
        TextView tv_pv_info = new TextView(this);
        tv_pv_info.setText(Integer.toString(mJoueur.getPv()));
        ligne_pv.addView(tv_pv_info);
        layoutCharacter.addView(ligne_pv);

        //Ligne de la mana

        TextView tv_pm = new TextView(this);
        tv_pm.setText(R.string.magic);
        ligne_pm.addView(tv_pm);
        TextView tv_pm_info = new TextView(this);
        tv_pm_info.setText(Integer.toString(mJoueur.getMagie()));
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
                        tr.addView(image);
                        image.getLayoutParams().height= (int) getResources().getDimension(R.dimen.imageview_height);
                        image.getLayoutParams().width=(int) getResources().getDimension(R.dimen.imageview_width);
                        break;
                    case 1:
                        image.setImageResource(R.drawable.tile_chemin);
                        tr.addView(image);
                        image.getLayoutParams().height=(int) getResources().getDimension(R.dimen.imageview_height);
                        image.getLayoutParams().width=(int) getResources().getDimension(R.dimen.imageview_width);
                        break;
                    case 2:
                        image.setImageResource(R.drawable.tile_arbre);
                        tr.addView(image);
                        image.getLayoutParams().height=(int) getResources().getDimension(R.dimen.imageview_height);
                        image.getLayoutParams().width=(int) getResources().getDimension(R.dimen.imageview_width);
                        break;
                    case 4:
                        image.setImageResource(R.drawable.perle_petite);
                        tr.addView(image);
                        image.getLayoutParams().height=(int) getResources().getDimension(R.dimen.imageview_height);
                        image.getLayoutParams().width=(int) getResources().getDimension(R.dimen.imageview_width);
                        break;
                    default:
                        btnPerso = new ImageView(this);
                        switch(orientation){
                            case 1 :
                                btnPerso.setImageResource(R.drawable.link_bas01);
                                break;
                            case 2 :
                                btnPerso.setImageResource(R.drawable.link_gauche);
                                break;
                            case 3 :
                                btnPerso.setImageResource(R.drawable.link_right);
                                break;
                            case 4 :
                                btnPerso.setImageResource(R.drawable.link_haut);
                                break;
                        }
                        tr.addView(btnPerso);
                        btnPerso.setOnClickListener(listener_btnPerso);
                        btnPerso.getLayoutParams().height=(int) getResources().getDimension(R.dimen.imageview_height);
                        btnPerso.getLayoutParams().width=(int) getResources().getDimension(R.dimen.imageview_width);
                        break;
                }
            }
        }
    }

    ////////////////////////////////////////////// 2. Puis-je me déplacer    ///////////////////////////////////////////////////
    public boolean  checkMove(){
        if(map[coordLig][coordCol] == 1)
            return true;
        else if (map[coordLig][coordCol] == 4){
            mJoueur.addPerle();
            checkExp();
            return true;
        }else
            return  false;
    }
    ////////////////////////////////////////////// 3. Deplacement    ///////////////////////////////////////////////////////////

    private View.OnClickListener listener_btnTop = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            coordLig--;
            if(checkMove()){
                mJoueur.upPerso();
                map[coordLig+1][coordCol]=1;
                map [coordLig][coordCol]=3;
                layoutLabirynthe.removeAllViews();
                dessinerMap(coordLig,coordCol,4);
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
                mJoueur.rightPerso();
                map[coordLig][coordCol-1]=1;
                map [coordLig][coordCol]=3;
                layoutLabirynthe.removeAllViews();
                dessinerMap(coordLig,coordCol,3);
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
                mJoueur.leftPerso();
                map[coordLig][coordCol+1]=1;
                map [coordLig][coordCol]=3;
                layoutLabirynthe.removeAllViews();
                dessinerMap(coordLig,coordCol,2);
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
                mJoueur.downPerso();
                map[coordLig-1][coordCol]=1;
                map [coordLig][coordCol]=3;
                layoutLabirynthe.removeAllViews();
                dessinerMap(coordLig,coordCol,1);
            }
            else{
                coordLig--;
                layoutLabirynthe.removeAllViews();
                dessinerMap(coordLig,coordCol,1);
            }
        }
    };

    /**
     * Listener des potions
     *
     * Fonctions permettant au joueur de prendre des potions afin d'augmenter ses caractèristiques
     * Gestion des differents boutons qui permettent la mise à jours des caractéristiques du joueur
     * et la gestion du stock de potion disponible
     */

    private View.OnClickListener listener_btnPotion = new  View.OnClickListener(){
        @Override
        public void onClick(View v){
            layoutPotion = findViewById(R.id.layoutPotion);
            layoutPotion.removeAllViews();
            vuePotions();
        }
    };

    private View.OnClickListener listener_btnPotion_force = new  View.OnClickListener(){
        @Override
        public void onClick(View v){
            layoutPotion = findViewById(R.id.layoutPotion);
            layoutPotion.removeAllViews();
            if(mJoueur.getPot_force() > 0){
                mJoueur.addForce();
            }
            layoutCharacter.removeAllViews();
            chargerVueCaracteristiqueJoueur();
            vuePotions();
        }
    };

    private View.OnClickListener listener_btnPotion_pv = new  View.OnClickListener(){
        @Override
        public void onClick(View v){
            layoutPotion = findViewById(R.id.layoutPotion);
            layoutPotion.removeAllViews();
            if(mJoueur.getPot_pv() > 0){
                mJoueur.addPv();
            }
            layoutCharacter.removeAllViews();
            chargerVueCaracteristiqueJoueur();
            vuePotions();
        }
    };

    private View.OnClickListener listener_btnPotion_pm = new  View.OnClickListener(){
        @Override
        public void onClick(View v){
            layoutPotion = findViewById(R.id.layoutPotion);
            layoutPotion.removeAllViews();
            if(mJoueur.getPot_pm() > 0){
                mJoueur.addPm();
            }
            layoutCharacter.removeAllViews();
            chargerVueCaracteristiqueJoueur();
            vuePotions();
        }
    };

    private View.OnClickListener listener_close = new  View.OnClickListener(){
        @Override
        public void onClick(View v){
            layoutPotion = findViewById(R.id.layoutPotion);
            layoutPotion.removeAllViews();
            boutonPotions();
        }
    };

    /***
     * Crée la vue des potions
     */
    private void vuePotions(){
        layoutPotion = findViewById(R.id.layoutPotion);

        TableRow LignePotionForce = new TableRow(this);
        layoutPotion.addView(LignePotionForce);
        btnPotionForce = new Button(this);
        btnPotionForce.setText("Force " + mJoueur.getPot_force() + " / " + POTIONFORCE);
        LignePotionForce.addView(btnPotionForce);
        btnPotionForce.setOnClickListener(listener_btnPotion_force);

        TableRow LignePotionPV = new TableRow(this);
        layoutPotion.addView(LignePotionPV);
        btnPotionPV = new Button(this);
        btnPotionPV.setText("Vie " + mJoueur.getPot_pv() + " / " + POTIONPV);
        LignePotionPV.addView(btnPotionPV);
        btnPotionPV.setOnClickListener(listener_btnPotion_pv);

        TableRow LignePotionPM = new TableRow(this);
        layoutPotion.addView(LignePotionPM);
        btnPotionPM = new Button(this);
        btnPotionPM.setText("Mana " + mJoueur.getPot_pm() + " / " + POTIONPM);
        LignePotionPM.addView(btnPotionPM);
        btnPotionPM.setOnClickListener(listener_btnPotion_pm);

        TableRow LigneClose = new TableRow(this);
        layoutPotion.addView(LigneClose);
        btnClose = new Button(this);
        btnClose.setText("Fermer");
        LigneClose.addView(btnClose);
        btnClose.setOnClickListener(listener_close);
    }

    /***
     * Recrée le bouton des potions
     */
    private void boutonPotions(){
        layoutPotion = findViewById(R.id.layoutPotion);
        TableRow LigneBoutonPotion = new TableRow(this);
        layoutPotion.addView(LigneBoutonPotion);
        btnPotion = new Button(this);
        btnPotion.setText("Potion");
        LigneBoutonPotion.addView(btnPotion);
        btnPotion.setOnClickListener(listener_btnPotion);
    }

    /***
     * Envoie les informations du joueur vers sa page de caractéristiques (infoJoueur)
     */
    private View.OnClickListener listener_btnPerso = new  View.OnClickListener(){
        @Override
        public void onClick(View v){
            Intent intent = new Intent(Game.this,InfoJoueur.class);
            intent.putExtra("exp",exp);
            intent.putExtra("player",mJoueur);
            startActivityForResult(intent, NUM_REQUETE);
        }
    };

    /***
     * Affiche au joueur quand il a assez d'expérience pour augmenter une caractéristique
     *  in @Param = néant
     *  out @Param = néant
     */
    private void checkExp(){
        if(mJoueur.getNbr_perle() > 0 && mJoueur.getNbr_perle()%3 == 0){
            exp++;
            Toast.makeText(Game.this,R.string.exp_up,Toast.LENGTH_SHORT).show();
        }
    }
}
