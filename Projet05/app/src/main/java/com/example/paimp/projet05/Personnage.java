package com.example.paimp.projet05;

/**
 * Created by Paimp on 10-12-17.
 */

public class Personnage {

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

    /***
     * Constructeur : initialise le personnage
     * @param classe
     * @param level
     * @param force
     * @param magie
     * @param pv
     * @param defense
     * @param pot_force
     * @param pot_pv
     * @param pot_pm
     * @param nbr_perle
     * @param coordX
     * @param coordY
     * @param map
     */
    public Personnage(String classe, int level, int force, int magie, int pv, int defense, int pot_force, int pot_pv, int pot_pm, int nbr_perle, int coordX, int coordY, int map) {
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

    /***
     *  Getter & Setter
     */

    public String getClasse() {
        return classe;
    }
    public void setClasse(String classe) {
        this.classe = classe;
    }
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

    /***
     *  Méthode du personnage
     */
    //////////////////////////  Partie Potion du personnage ///////////////////////
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
    public void lessPotForce(){
        setPot_force(getPot_force()-1);
    }
    public  void lessPotPv(){
        setPot_pv(getPot_pv()-1);
    }
    public  void lessPotPm(){
        setPot_pm(getPot_pm()-1);
    }

    //////////////////////////  Partie déplacement du personnage ///////////////////////
    public void upPerso(){
        setCoordY(getCoordY()-1);
    }
    public void downPerso(){
        setCoordY(getCoordY()+1);
    }
    public void leftPerso(){
        setCoordX(getCoordX()-1);
    }
    public void rightPerso(){
        setCoordX(getCoordX()+1);
    }
}
