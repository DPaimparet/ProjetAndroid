package com.example.paimp.projet08.model;

import java.io.Serializable;

/**
 * Created by Paimp on 13-12-17.
 */

public class Joueur implements Serializable {
    private static final long serialVersionUID = 1;
    private int id;
    private String classe;
    private int level;
    private int force;
    private int magie;
    private int pv;
    private int defense;
    private int pot_force;
    private int pot_pv;
    private int pot_pm;
    private int nbr_perle;
    private int coordX;
    private int coordY;
    private int map;
    //////////////////////////   Constructeur   ////////////////////////
    public Joueur(){}
    public Joueur(int id, String classe, int level, int force, int magie, int pv, int defense, int pot_force, int pot_pv, int pot_pm, int nbr_perle, int coordX, int coordY, int map) {
        this.id = id;
        this.classe = classe;
        this.level = level;
        this.force = force;
        this.magie = magie;
        this.pv = pv;
        this.defense = defense;
        this.pot_force = pot_force;
        this.pot_pv = pot_pv;
        this.pot_pm = pot_pm;
        this.nbr_perle = nbr_perle;
        this.coordX = coordX;
        this.coordY = coordY;
        this.map = map;
    }

    //////////////////////////     Getter      //////////////////////////
    public int getId() {
        return id;
    }
    public String getClasse() {
        return classe;
    }

    ////////////////////////// Getter & Setter //////////////////////////
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public int getForce() {
        return force;
    }
    public void setForce(int force) {
        this.force = force;
    }
    public int getMagie() {
        return magie;
    }
    public void setMagie(int magie) {
        this.magie = magie;
    }
    public int getPv() {
        return pv;
    }
    public void setPv(int pv) {
        this.pv = pv;
    }
    public int getDefense() {
        return defense;
    }
    public void setDefense(int defense) {
        this.defense = defense;
    }
    public int getPot_force() {
        return pot_force;
    }
    public void setPot_force(int pot_force) {
        this.pot_force = pot_force;
    }
    public int getPot_pv() {
        return pot_pv;
    }
    public void setPot_pv(int pot_pv) {
        this.pot_pv = pot_pv;
    }
    public int getPot_pm() {
        return pot_pm;
    }
    public void setPot_pm(int pot_pm) {
        this.pot_pm = pot_pm;
    }
    public int getNbr_perle() {
        return nbr_perle;
    }
    public void setNbr_perle(int nbr_perle) {
        this.nbr_perle = nbr_perle;
    }
    public int getCoordX() {
        return coordX;
    }
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }
    public int getCoordY() {
        return coordY;
    }
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
    public int getMap() {
        return map;
    }
    public void setMap(int map) {
        this.map = map;
    }

    //////////////////////////    Methodes     //////////////////////////
    /********************  Récupération du joueur  *********************/
    public void getJoueur(){

    }
    /*************  Mise à jour des coordonnées du joueur **************/
    public void upPerso(){
        coordY--;
    }
    public void downPerso(){
        coordY++;
    }
    public void leftPerso(){
        coordX--;
    }
    public void rightPerso(){
        coordX++;
    }
    /*****************  Mise à jour des stats du joueur ****************/
    public void addForce(){
        setForce(getForce()+1);
        lessPotForce();
    }
    public void addPv(){
        setPv(getPv()+10);
        lessPotPv();
    }
    public void addPm(){
        setMagie(getMagie()+10);
        lessPotPm();
    }
    public void addNiveau(){
        setLevel(getLevel()+1);
    }
    public void addXpForce(){
        setForce(getForce()+1);
    }
    public void addXpSante(){
        setPv(getPv()+1);
    }
    public void addXpMagie(){
        setMagie(getMagie()+1);
    }
    public void addXpDefense(){
        setDefense(getDefense()+1);
    }
    public void lessPotForce(){
        setPot_force(getPot_force()-1);
    }
    public void lessPotPv(){
        setPot_pv(getPot_pv()-1);
    }
    public void lessPotPm(){
        setPot_pm(getPot_pm()-1);
    }
    public  void addPerle(){
        setNbr_perle(getNbr_perle()+1);
    }
}
